package fileManagement;

import fileIO.IFileIO;
import fileIO.XmlFileIO;
import model.Researcher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlFileManagement {
    private XmlFileIO xmlFileIO;
    private List<Researcher> researchers;
    public XmlFileManagement() {
        this.xmlFileIO = new XmlFileIO();
        this.researchers = new ArrayList<>();
        initializeResearcher();
    }

    public void initializeResearcher(){
        if (xmlFileIO.isFileExist()){
            System.out.println("xml file management");
        }else{
            createResearchersFirst();
        }

    }
    public void createResearchersFirst() {
        Researcher researcher1 = new Researcher("Tuğkan Tuğlular", "tuğkan123",new ArrayList<>(), new ArrayList<>());
        Researcher researcher2 = new Researcher("Dilek Öztürk", "dilek123",new ArrayList<>(), new ArrayList<>());
        Researcher researcher3 = new Researcher("Serhat Caner", "serhat123",new ArrayList<>(), new ArrayList<>());
        Researcher researcher4 = new Researcher("Nesli Erdoğmuş", "nesli123",new ArrayList<>(), new ArrayList<>());
        Researcher researcher5 = new Researcher("Yalın Baştanlar", "yalın123",new ArrayList<>(), new ArrayList<>());
        researchers.add(researcher1);
        researchers.add(researcher2);
        researchers.add(researcher3);
        researchers.add(researcher4);
        researchers.add(researcher5);
        for (Researcher researcher : researchers){
            Map<String, String> researcherMap = new HashMap<>(){{
                put("name",researcher.getName());
                put("password", researcher.getPassword());
            }};
            xmlFileIO.writeFileIO(researcherMap);
        }
    }

    public void setFollowResearcher(Researcher researcher, Researcher followingResearcher){
        if (!researcher.getFollowingResearchers().contains(followingResearcher) && !followingResearcher.getFollowerResearchers().contains(researcher)){
            xmlFileIO.updateFile(researcher.getName(), "following_researcher_names", followingResearcher.getName(), true);
            xmlFileIO.updateFile(followingResearcher.getName(), "follower_researcher_names", researcher.getName(), true);
        }

    }

    public void setUnfollowResearcher(Researcher researcher, Researcher followingResearcher){
        xmlFileIO.updateFile(researcher.getName(), "following_researcher_names", followingResearcher.getName(), false);
        xmlFileIO.updateFile(followingResearcher.getName(), "follower_researcher_names", researcher.getName(), false);
    }

    public List<Researcher> getResearchers() {
        return researchers;
    }
}