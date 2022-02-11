package com.dennis.saina

open class Question {
    private var mQuestionImage: String? = null
    private var mQuestionAnswer: String? = null
    private var mQuestionOption1: String? = null
    private var mQuestionOption2: String? = null

    constructor() {}
    constructor(
        mQuestionImage: String?,
        mQuestionAnswer: String?,
        mQuestionOption1: String?,
        mQuestionOption2: String?
    ) {
        this.mQuestionImage = mQuestionImage
        this.mQuestionAnswer = mQuestionAnswer
        this.mQuestionOption1 = mQuestionOption1
        this.mQuestionOption2 = mQuestionOption2
    }

    fun getmQuestionImage(): String? {
        return mQuestionImage
    }

    fun setmQuestionImage(mQuestionImage: String?) {
        this.mQuestionImage = mQuestionImage
    }

    fun getmQuestionAnswer(): String? {
        return mQuestionAnswer
    }

    fun setmQuestionAnswer(mQuestionAnswer: String?) {
        this.mQuestionAnswer = mQuestionAnswer
    }

    fun getmQuestionOption1(): String? {
        return mQuestionOption1
    }

    fun setmQuestionOption1(mQuestionOption1: String?) {
        this.mQuestionOption1 = mQuestionOption1
    }

    fun getmQuestionOption2(): String? {
        return mQuestionOption2
    }

    fun setmQuestionOption2(mQuestionOption2: String?) {
        this.mQuestionOption2 = mQuestionOption2
    }
}