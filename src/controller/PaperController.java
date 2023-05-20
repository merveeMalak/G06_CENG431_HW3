package controller;

import fileManagement.BibFileManagement;
import fileManagement.CsvFileManagement;
import fileManagement.XmlFileManagement;
import model.Paper;
import view.ICustomObserver;
import view.ICustomSubject;

import java.util.ArrayList;
import java.util.List;

public class PaperController implements ICustomSubject {

    private BibFileManagement bibFileManagement;
    private CsvFileManagement csvFileManagement;
    private List<Paper> papers;
    private List<ICustomObserver> observers;

    public PaperController() {
        this.bibFileManagement = new BibFileManagement();
        this.papers = bibFileManagement.getPapers();
        this.csvFileManagement = new CsvFileManagement(papers);
        this.observers = new ArrayList<>();
        attach(this.csvFileManagement);
    }

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

    public List<Paper> getPapers() {
        return this.papers;
    }

}
