package fileIO;
import model.Researcher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlFileIO implements IFileIO<Map<String, String>> {
    private final String FILE_NAME = "researchers.xml";
    public XmlFileIO(){}
    @Override
    public List<String[]> readFile() {
        List<String[]> researcherList = new ArrayList<>();
        Document doc = getDocument();
        Element root = doc.getDocumentElement();
        NodeList researcherNodes = root.getElementsByTagName("researcher");
        for (int i = 0; i < researcherNodes.getLength(); i++) {
            String[] researcher = new String[4];
            researcher[0]  = ((Element) researcherNodes.item(i)).getElementsByTagName("name").item(0).getTextContent();
            researcher[1]  = ((Element) researcherNodes.item(i)).getElementsByTagName("password").item(0).getTextContent();
            Element followingResearchersElement = (Element)((Element) researcherNodes.item(i)).getElementsByTagName("following_researcher_names").item(0);
            String followingString = "";
            if (followingResearchersElement != null){
                NodeList followingResearcherNodes = followingResearchersElement.getElementsByTagName("following_researcher_name");

                for (int j = 0; j < followingResearcherNodes.getLength(); j++) {
                    Element followingResearcherElement = (Element) followingResearcherNodes.item(j);
                    followingString +=  followingResearcherElement.getTextContent() +  (j == followingResearcherNodes.getLength() -1 ? "" : ";" );
                }
            }
            researcher[2] = followingString;

            // Get follower_researcher_names
            Element followerResearchersElement = (Element)((Element) researcherNodes.item(i)).getElementsByTagName("follower_researcher_names").item(0);
            String followerString = "";
            if (followerResearchersElement != null){
                NodeList followerResearcherNodes = followerResearchersElement.getElementsByTagName("follower_researcher_name");

                for (int j = 0; j < followerResearcherNodes.getLength(); j++) {
                    Element followerResearcherElement = (Element) followerResearcherNodes.item(j);
                    followerString +=  followerResearcherElement.getTextContent() +  (j == followerResearcherNodes.getLength() -1 ? "" : ";" );
                }
            }
            researcher[3] = followerString;
            researcherList.add(researcher);
        }

        return researcherList;
    }

    @Override
    public void writeFileIO(Map<String, String> line)  {
        Document doc = getDocument();
        Element researchers = doc.getDocumentElement();
        if (findResearcherByName(doc, line.get("name")) == null){
            addResearcher(doc, line, researchers);
        }
    }

    private Document getDocument(){
        Document doc = null;
        if (isFileExist()){
            System.out.println("dosya var");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            try {
                docBuilder = docFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            try {
                doc = docBuilder.parse(FILE_NAME);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            Element researchersElement = doc.createElement("researchers");
            doc.appendChild(researchersElement);
        }
        return doc;
    }

    private  void addResearcher(Document doc, Map<String, String> line, Element parentElement) {
        Element researcherElement = doc.createElement("researcher");
        parentElement.appendChild(researcherElement);
        Set<String> keys = line.keySet();
        for (String key : keys) {
            if (key.equals("following_researcher_names") || key.equals("follower_researcher_names")){
                if (!line.get(key).isEmpty()){
                    Element followElement = doc.createElement(key);
                    String[] researchers = line.get(key).split(";");
                    for (String researcher : researchers) {
                        createElement(doc, followElement, key.substring(0,key.length()-1), researcher);
                    }
                    researcherElement.appendChild(followElement);
                }
            }else{
                createElement(doc, researcherElement, key, line.get(key));
            }
        }
        transformFile(doc);
    }
    public boolean isFileExist(){
        File xmlFile = new File(FILE_NAME);
        return (xmlFile.exists() && !xmlFile.isDirectory());

    }
    private void createElement(Document doc, Element parentElement, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parentElement.appendChild(element);
    }
    private void transformFile(Document doc){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");  //????????
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_NAME));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFile(String name, String changedValue, String newContent, boolean isFollow) {
        Document doc = getDocument();
        if (isFollow) {
            try {
                Node followingsearcherNode = findResearcherByName(doc, name);
                Node followerSearchNode = findResearcherByName(doc, newContent);
                if (followingsearcherNode != null && followerSearchNode != null) {
                    Element researcherElement = (Element) followingsearcherNode;
                    Element followingResearchersElement = (Element) researcherElement.getElementsByTagName(changedValue).item(0);
                    if (followingResearchersElement == null) {
                        Element followElement = doc.createElement(changedValue);
                        Element newElement = doc.createElement(changedValue.substring(0, changedValue.length() - 1));
                        followElement.appendChild(newElement);
                        newElement.setTextContent(newContent);
                        researcherElement.appendChild(followElement);
                    } else {
                        Element newElement = doc.createElement(changedValue.substring(0, changedValue.length() - 1));
                        newElement.setTextContent(newContent);
                        followingResearchersElement.appendChild(newElement);
                    }
                    transformFile(doc);
                } else {
                    System.out.println("Array element '" + changedValue + "' not found in the XML file.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Node followingsearcherNode = findResearcherByName(doc, name);
            Node followerSearchNode = findResearcherByName(doc, newContent);
            if (followingsearcherNode != null && followerSearchNode !=null){
                Element arrayElement = (Element) ((Element) followingsearcherNode).getElementsByTagName(changedValue).item(0);
                if (arrayElement != null) {
                    NodeList elementNodes = arrayElement.getElementsByTagName(changedValue.substring(0, changedValue.length()-1));
                    for (int i = 0; i < elementNodes.getLength(); i++) {
                        Element element = (Element) elementNodes.item(i);
                        if (element.getTextContent().equals(newContent)) {
                            arrayElement.removeChild(element);
                            System.out.println("xx " + ((arrayElement.getChildNodes().item(0))).getTextContent());
                            break;
                        }
                    }
                    transformFile(doc);
                }
            }


        }
    }
    private Node findResearcherByName(Document doc, String researcherName) {
        NodeList researcherNodes = doc.getElementsByTagName("researcher");
        for (int i = 0; i < researcherNodes.getLength(); i++) {
            Element researcherElement = (Element) researcherNodes.item(i);
            String currentName = researcherElement.getElementsByTagName("name").item(0).getTextContent();
            if (currentName.equals(researcherName)) {
                return researcherElement;
            }
        }
        return null;
    }
}
