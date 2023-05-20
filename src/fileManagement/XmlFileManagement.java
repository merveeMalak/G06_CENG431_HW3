package fileManagement;

import fileIO.IFileIO;
import fileIO.XmlFileIO;
import model.Researcher;
import view.ICustomObserver;
import view.ICustomSubject;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class XmlFileManagement implements ICustomObserver {
    private XmlFileIO xmlFileIO;
    private List<Researcher> researchers;

    public XmlFileManagement() {
        this.xmlFileIO = new XmlFileIO();
        this.researchers = new ArrayList<>();
        initializeResearcher();
    }

    public void initializeResearcher() {
        if (xmlFileIO.isFileExist()) {
            createFromFileToResearchers(xmlFileIO.readFile());
        } else {
            createResearchersFirst();
        }

    }

    public void createResearchersFirst() {
        Researcher researcher1 = new Researcher("Tuğkan Tuğlular", "tuğkan123");
        Researcher researcher2 = new Researcher("Dilek Öztürk", "dilek123");
        Researcher researcher3 = new Researcher("Serhat Caner", "serhat123");
        Researcher researcher4 = new Researcher("Nesli Erdoğmuş", "nesli123");
        Researcher researcher5 = new Researcher("Yalın Baştanlar", "yalın123");
        researchers.add(researcher1);
        researchers.add(researcher2);
        researchers.add(researcher3);
        researchers.add(researcher4);
        researchers.add(researcher5);

        for (Researcher researcher : researchers) {
            Map<String, String> researcherMap = new HashMap<>() {{
                put("name", researcher.getName());
                put("password", researcher.getPassword());
                put("following_researcher_names", researcher.getFollowingResearchers().stream().map(Researcher::getName).collect(Collectors.joining(";")));
                put("follower_researcher_names", researcher.getFollowerResearchers().stream().map(Researcher::getName).collect(Collectors.joining(";")));
            }};
            xmlFileIO.writeFileIO(researcherMap);
        }
    }


    private void createFromFileToResearchers(List<String[]> researcherList) {
        for (String[] researcherArray : researcherList) {
            researchers.add(new Researcher(researcherArray[0], researcherArray[1]));
        }
        for (String[] researcherArray : researcherList) {
            String[] followingResearchers = researcherArray[2].split(";");
            if (followingResearchers.length != 0) {
                Researcher researcher = findResearcherByName(researcherArray[0]);
                if (researcher != null) {
                    for (String researcherName : followingResearchers) {
                        Researcher followingResearcher = findResearcherByName(researcherName);
                        if (followingResearcher != null) {
                            researcher.addFollowingResearcher(followingResearcher);
                            followingResearcher.addFollowerResearcher(researcher);
                        }
                    }
                }
            }
        }
    }

    public void setFollowResearcher(Researcher researcher, Researcher followingResearcher) {
        if (!researcher.getFollowingResearchers().contains(followingResearcher) && !followingResearcher.getFollowerResearchers().contains(researcher)) {
            xmlFileIO.updateFile(researcher.getName(), "following_researcher_names", followingResearcher.getName(), true);
            xmlFileIO.updateFile(followingResearcher.getName(), "follower_researcher_names", researcher.getName(), true);
            researcher.addFollowingResearcher(followingResearcher);
            followingResearcher.addFollowerResearcher(researcher);
        }
    }

    public void setUnfollowResearcher(Researcher researcher, Researcher followingResearcher) {
        if (researcher.getFollowingResearchers().contains(followingResearcher) && followingResearcher.getFollowerResearchers().contains(researcher)) {
            xmlFileIO.updateFile(researcher.getName(), "following_researcher_names", followingResearcher.getName(), false);
            xmlFileIO.updateFile(followingResearcher.getName(), "follower_researcher_names", researcher.getName(), false);
            researcher.removeFollowingResearcher(followingResearcher);
            followingResearcher.removeFollowerResearcher(researcher);
        }

    }

    public List<Researcher> getResearchers() {
        return researchers;
    }

    private Researcher findResearcherByName(String name) {
        for (Researcher researcher : researchers) {
            if (researcher.getName().equals(name)) {
                return researcher;
            }
        }
        return null;
    }

    @Override
    public void update(ICustomSubject subject) {
    }
}