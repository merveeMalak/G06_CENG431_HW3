package controller;

import fileManagement.JsonFileManagement;
import model.ReadingList;
import view.ICustomObserver;
import view.ICustomSubject;

import java.util.ArrayList;
import java.util.List;

public class ReadingListController implements ICustomSubject {
    private JsonFileManagement jsonFileManagement;
    private List<ICustomObserver> observers;

    public ReadingListController() {
        this.observers = new ArrayList<>();
        this.jsonFileManagement = new JsonFileManagement();
        this.attach(this.jsonFileManagement);
    }

    public void addReadingList(String readingListName, String creatorName) {
        this.jsonFileManagement.addReadingList(creatorName, readingListName);
        this.notifyObservers();
    }

    public void setReadingList(ReadingList readingList) {
        this.jsonFileManagement.setReadingList(readingList);
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

    public List<ReadingList> getReadingLists(String researcherName) {
        return jsonFileManagement.getReadingListsByName(researcherName);
    }
}
