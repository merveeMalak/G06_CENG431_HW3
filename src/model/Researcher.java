package model;

import view.ICustomObserver;
import view.ICustomSubject;

import java.util.ArrayList;
import java.util.List;


public class Researcher implements ICustomSubject {
    private String name;
    private String password;
    private List<Researcher> followingResearchers;
    private List<Researcher> followerResearchers;
    private List<ReadingList> readingLists;

    private List<ICustomObserver> observers;


    public Researcher(String name, String password, List<Researcher> followingResearchers, List<Researcher> followerResearchers) {
        this.name = name;
        this.password = password;
        this.followingResearchers = followingResearchers;
        this.followerResearchers = followerResearchers;
        this.readingLists = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Researcher(String name, String password) {
        this(name, password, new ArrayList<>(), new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Researcher> getFollowingResearchers() {
        return this.followingResearchers;
    }

    public List<Researcher> getFollowerResearchers() {
        return this.followerResearchers;
    }

    public void addFollowingResearcher(Researcher followingResearcher) {
        followingResearchers.add(followingResearcher);
    }

    public void removeFollowingResearcher(Researcher followingResearcher){
        followingResearchers.remove(followingResearcher);
    }
    public void addFollowerResearcher(Researcher followerResearcher) {
        followerResearchers.add(followerResearcher);
    }


    public List<ReadingList> getReadingLists() {
        return readingLists;
    }

    public void addReadingList(ReadingList addReadingList) {
        this.readingLists.add(addReadingList);
    }

    public void removeFollowerResearcher(Researcher followerResearcher){
        followerResearchers.remove(followerResearcher);
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
        for (ICustomObserver observer: observers){
            observer.update(this);
        }
    }
}
