package com.example.quizzando;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RaccoltaDomande {

    // declare list of Question objects
    List<Domande> lists = new ArrayList<>();
    DatabaseHelper databaseHelper;

    // method returns number of questions in list
    public int getLength() {
        return lists.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return lists.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return lists.get(index).getChoice(num - 1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return lists.get(a).getAnswer();
    }


    public void initQuestions(Context context) {
        //lists.clear();
        databaseHelper = new DatabaseHelper(context);
        lists = databaseHelper.getAllQuestionsList();//get questions/choices/answers from database
        //lists.clear();

        if (lists.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            databaseHelper.addInitialQuestion(new Domande(" When did Google acquire Android ?",
                    new String[]{"2001", "2003", "2004", "2005"}, "2005"));
            databaseHelper.addInitialQuestion(new Domande(" What is the name of build toolkit for Android Studio?",
                    new String[]{"JVM", "Gradle", "Dalvik", "HAXM"}, "Gradle"));
            databaseHelper.addInitialQuestion(new Domande(" What widget can replace any use of radio buttons?",
                    new String[]{"Toggle Button", "Spinner", "Switch Button", "ImageButton"}, "Spinner"));
            databaseHelper.addInitialQuestion(new Domande(" What is a widget in Android app?",
                    new String[]{"reusable GUI element", "Layout for Activity", "device placed in cans of beer", "build toolkit"}, "reusable GUI element"));
            databaseHelper.addInitialQuestion(new Domande(" Chi è?",
                    new String[]{"rd", "r", "reeea", "rea"}, "r"));
            lists = databaseHelper.getAllQuestionsList();//get list from database again

        }
    }
}

