package controller;

import fileManagement.XmlFileManagement;
import model.Researcher;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private String username;
    private String password;
    private List<Researcher> researchers;
    private XmlFileManagement xmlFileManagement;

    public UserController(String username, String password){
        this.username = username;
        this.password = password;
        this.xmlFileManagement = new XmlFileManagement();
        this.researchers = xmlFileManagement.getResearchers();
    }
    public UserController(){
        this("","");
    }

    public boolean isValidResearcher(){
        for (Researcher researcher: researchers){
            if (username.equals(researcher.getName()) && password.equals(researcher.getPassword())){
                return true;
            }
        }
        return false;
    }

    public List<Researcher> getResearchers(){
        return new ArrayList<>(researchers);
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
