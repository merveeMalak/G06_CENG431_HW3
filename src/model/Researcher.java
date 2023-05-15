package model;

import java.util.List;

public class Researcher {
    private String name;
    private String password;
    private List<Researcher> followingResearchers;
    private List<Researcher> followerResearchers;


    public Researcher(String name, String password, List<Researcher> followingResearchers, List<Researcher> followerResearchers) {
        this.name = name;
        this.password = password;
        this.followingResearchers = followingResearchers;
        this.followerResearchers = followerResearchers;
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
}
