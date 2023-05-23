package com.example.just_drive;

public class StateStatistic {
    String date;
    String result;
    int correct;
    int mistakes;

    public StateStatistic( int correct,String date,int mistakes,String result){
        this.date = date;
        this.result = result;
        this.correct = correct;
        this. mistakes = mistakes;
    }
    public StateStatistic(){

    }

    public String getDate(){return  date;}
    public  void setDate(String date) {
        this.date=date;
    }
    public String getResult(){return  result;}
    public  void setResult(String result) {
        this.result=result;
    }
    public Integer getCorrect(){return  correct;}
    public  void setCorrect(Integer correct) {
        this.correct=correct;
    }
    public Integer getMistakes(){return  mistakes;}
    public  void setMistakes(Integer mistakes) {
        this.mistakes=mistakes;
    }


}
