package com.example.just_drive;

public class StateStatistic {
    String date;
    String result;
    String correct;
    String mistakes;

    public StateStatistic( String correct,String date,String mistakes,String result){
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
    public String getCorrect(){return  correct;}
    public  void setCorrect(String correct) {
        this.correct=correct;
    }
    public String getMistakes(){return  mistakes;}
    public  void setMistakes(String mistakes) {
        this.mistakes=mistakes;
    }


}
