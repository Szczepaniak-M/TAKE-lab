package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "formBean")
@RequestScoped
public class FormBean {

    private int firstNumber;
    private int secondNumber;
    private int result;
    private String date;
    
    public FormBean() {
        firstNumber = 0;
        secondNumber = 0;
        result = 0;
    }
    
    @PostConstruct
    public void init() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy z");
        Date now = new Date();
        date = sdfDate.format(now);
    }
    
    public void add() {
        result = firstNumber + secondNumber;
        String message = String.format("%d + %d = %d", firstNumber, secondNumber, result);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"New Result", message));
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }
    
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
