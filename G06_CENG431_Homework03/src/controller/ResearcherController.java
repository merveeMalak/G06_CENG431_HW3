package controller;

import fileManagement.XmlFileManagement;
import model.Researcher;
import view.ICustomObserver;
import view.ICustomSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResearcherController implements ICustomSubject {

    private String username;
    private String password;
    private List<Researcher> researchers;
    private XmlFileManagement xmlFileManagement;
    private List<ICustomObserver> observers;
    private Boolean isLoggedIn;
    private Researcher currentResearcher;

    public ResearcherController(String username, String password) {
        this.username = username;
        this.password = password;
        this.xmlFileManagement = new XmlFileManagement();
        this.researchers = xmlFileManagement.getResearchers();
        this.observers = new ArrayList<>();
        this.attach(xmlFileManagement);
    }

    public ResearcherController() {
        this("", "");
    }

    public Boolean checkCredentials() {
        for (Researcher researcher : researchers) {
            if (username.equals(researcher.getName()) && password.equals(researcher.getPassword())) {
                this.currentResearcher = researcher;
                return true;
            }
        }
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
        this.notifyObservers();
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsFollowThisResearcher(Researcher checkResearcher) {
        return checkResearcher.getFollowerResearchers().contains(currentResearcher);
    }

    public List<Researcher> getOtherResearchers(String name) {
        return this.researchers.stream().filter(x -> !Objects.equals(x.getName(), name)).toList();
    }

    public void setFollowResearcher(Researcher researcher, Researcher followingResearcher) {
        this.xmlFileManagement.setFollowResearcher(researcher, followingResearcher);
    }

    public void setUnfollowResearcher(Researcher researcher, Researcher followingResearcher) {
        this.xmlFileManagement.setUnfollowResearcher(researcher, followingResearcher);
    }

    @Override
    public void attach(ICustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(ICustomObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ICustomObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getResearcherName() {
        return currentResearcher.getName();
    }

    public Researcher getCurrentResearcher() {
        return currentResearcher;
    }

    public List<Researcher> getFollowers() {
        return currentResearcher.getFollowerResearchers();
    }

    public List<Researcher> getFollowings() {
        return currentResearcher.getFollowingResearchers();
    }
}
