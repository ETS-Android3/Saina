package com.dennis.saina;

import com.dennis.saina.Question;

public class AnsweredQuestion extends Question {

    private String SelectedOption;
    private String CorrectOption;

    public AnsweredQuestion(String mQuestionImage, String mQuestionAnswer, String mQuestionOption1, String mQuestionOption2, String selectedOption, String correctOption) {
        super(mQuestionImage, mQuestionAnswer, mQuestionOption1, mQuestionOption2);
        SelectedOption = selectedOption;
        CorrectOption = correctOption;
    }

    public String getSelectedOption() {
        return SelectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        SelectedOption = selectedOption;
    }

    public String getCorrectOption() {
        return CorrectOption;
    }

    public void setCorrectOption(String correctOption) {
        CorrectOption = correctOption;
    }
}
