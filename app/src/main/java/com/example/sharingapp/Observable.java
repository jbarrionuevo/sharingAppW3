package com.example.sharingapp;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer){
        System.out.println("Observable.addObserver 1-------------------------");
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        int i = 0;
        while (i<observers.size()){
            observers.get(i).update();
        }
    }
}
