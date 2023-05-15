package fileIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlFileIO implements IFileIO<Map<String, String>> {
    private final String FILE_NAME = "researchers.xml";
    @Override
    public List<String[]> readFile() {
        return null;
    }

    @Override
    public void writeFileIO(Map<String, String> line)  {
        File xmlFile = new File(FILE_NAME);
        if (!xmlFile.exists()) {
            System.out.println("aa");
            try {
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                Element researchersElement = doc.createElement("researchers");
                doc.appendChild(researchersElement);
                addResearcher(doc, line, researchersElement);
                System.out.println("XML file created successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
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
            // Get the root element
            Element researchers = doc.getDocumentElement();
            addResearcher(doc, line, researchers);
            System.out.println("XML file updated successfully.");
        }

    }
    private  void addResearcher(Document doc, Map<String, String> line, Element parentElement) {
        Element researcherElement = doc.createElement("researcher");
        parentElement.appendChild(researcherElement);
        Set<String> keys = line.keySet();
        for (String key : keys) {
            System.out.println(key);
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
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_NAME));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
