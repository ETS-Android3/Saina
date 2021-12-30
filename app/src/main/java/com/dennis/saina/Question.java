package com.dennis.saina;

public class Question {
    private String mQuestionImage;
    private String mQuestionAnswer;
    private String mQuestionOption1;
    private String mQuestionOption2;

    public Question() {
    }

    public Question(String mQuestionImage, String mQuestionAnswer, String mQuestionOption1, String mQuestionOption2) {
        this.mQuestionImage = mQuestionImage;
        this.mQuestionAnswer = mQuestionAnswer;
        this.mQuestionOption1 = mQuestionOption1;
        this.mQuestionOption2 = mQuestionOption2;
    }

    public String getmQuestionImage() {
        return mQuestionImage;
    }

    public void setmQuestionImage(String mQuestionImage) {
        this.mQuestionImage = mQuestionImage;
    }

    public String getmQuestionAnswer() {
        return mQuestionAnswer;
    }

    public void setmQuestionAnswer(String mQuestionAnswer) {
        this.mQuestionAnswer = mQuestionAnswer;
    }

    public String getmQuestionOption1() {
        return mQuestionOption1;
    }

    public void setmQuestionOption1(String mQuestionOption1) {
        this.mQuestionOption1 = mQuestionOption1;
    }

    public String getmQuestionOption2() {
        return mQuestionOption2;
    }

    public void setmQuestionOption2(String mQuestionOption2) {
        this.mQuestionOption2 = mQuestionOption2;
    }
}
