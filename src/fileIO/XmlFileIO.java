package fileIO;
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
        Document doc = getDocument();
        Element root = doc.getDocumentElement();
        NodeList childElements = root.getChildNodes();
        List<String[]> researcherList = new ArrayList<>();
        for (int i = 0; i < childElements.getLength(); i++) {
            String[] researcher = new String[4];
            Node childNode = childElements.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;
                researcher[0] = childElement.getElementsByTagName("name").item(0).getTextContent();
                researcher[1] = childElement.getElementsByTagName("password").item(0).getTextContent();
                NodeList following = childElement.getElementsByTagName("following_researcher_names");
                System.out.println(following.getLength());
            }
        }

        return null;
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
            //okuyup researchers listesini oluştur
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

    public void updateFile(String name, String changedValue, String newContent, boolean isFollow){
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = null;
        try {
            doc = docBuilder.parse(FILE_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (isFollow){
            try {
                Node followingsearcherNode = findResearcherByName(doc, name);
                Node followerSearchNode = findResearcherByName(doc, newContent);

                if (followingsearcherNode != null && followerSearchNode != null ) {
                    Element researcherElement = (Element) followingsearcherNode;
                    Element newElement = doc.createElement(changedValue.substring(0, changedValue.length()-1));
                    newElement.setTextContent(newContent);
                    researcherElement.appendChild(newElement);
                    transformFile(doc);
                } else {
                    System.out.println("Array element '" + changedValue + "' not found in the XML file.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

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
