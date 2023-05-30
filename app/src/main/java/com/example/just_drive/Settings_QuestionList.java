package com.example.just_drive;

public class Settings_QuestionList {
    private String option1, option2, option3, option4, question, answer;
    private String image;
    private String userSelectedAnswer;
    private String help;


    public Settings_QuestionList(String option1, String option2, String option3, String option4, String question, String answer, String image, String userSelectedAnswer, String help) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.question = question;
        this.image = image;
        this.answer = answer;
        this.help = help;

        this.userSelectedAnswer = userSelectedAnswer;
    }
    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getQuestion() {
        return question;
    }

    public String getImage(){ return image;}

    public String getAnswer() {
        return answer;
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }
}
