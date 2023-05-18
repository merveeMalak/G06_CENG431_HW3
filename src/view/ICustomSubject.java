package view;

public interface ICustomSubject {
    public void attach(ICustomObserver observer);
    public void detach(ICustomObserver observer);
    public void notifyObservers();
}
