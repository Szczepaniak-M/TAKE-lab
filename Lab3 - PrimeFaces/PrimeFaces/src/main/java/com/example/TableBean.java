package com.example;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;

@Named(value = "tableBean")
@ViewScoped
public class TableBean implements Serializable {

    private List<Student> students;
    
    public TableBean() {
        students = new LinkedList<>();
    }
    
    @PostConstruct
    public void init() {
        students.add(new Student("Michał", "Szczepaniak", 5.0));
        students.add(new Student("Jan", "Kowalski", 4.5));
        students.add(new Student("Anna", "Nowak", 3.0));
        students.add(new Student("Janusz", "Nowakowski", 4.6));
        students.add(new Student("Jakub", "Szczepaniak", 5.0));
        students.add(new Student("Michał", "Szczepaniak", 5.0));
        students.add(new Student("Jan", "Kowalski", 4.5));
        students.add(new Student("Anna", "Nowak", 3.5));
        students.add(new Student("Janusz", "Nowakowski", 4.7));
        students.add(new Student("Jakub", "Szczepaniak", 3.0));
        students.add(new Student("Michał", "Szczepaniak", 3.6));
        students.add(new Student("Jan", "Kowalski", 4.5));
        students.add(new Student("Anna", "Nowak", 3.5));
        students.add(new Student("Janusz", "Nowakowski", 4.6));
        students.add(new Student("Jakub", "Szczepaniak", 5.6));
        
    }
    
     public List<Student> getStudents() {
        return students;
    }
    
}
