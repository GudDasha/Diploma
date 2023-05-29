package com.example.just_drive;

public class Results {
    public String date;
    public String result;
    public int correct;
    public int mistakes;

    public Results(String date,String result,int correct,int mistakes){
       this.date = date;
       this.result = result;
       this.correct = correct;
       this.mistakes=mistakes;
    }
    public Results(){

    }
    public String getDate(){
        return date;
    }
    public String getResult(){
        return result;
    }
    public int getCorrect(){
        return correct;
    }
    public int getMistakes(){
        return mistakes;
    }
}
