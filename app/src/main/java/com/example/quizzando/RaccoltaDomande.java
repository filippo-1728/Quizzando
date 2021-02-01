package com.example.quizzando;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RaccoltaDomande {

    List<Domande> lists = new ArrayList<>();
    DatabaseHelper databaseHelper;

    public int getLength() {
        return lists.size();
    }

    public String getQuestion(int a) {
        return lists.get(a).getQuestion();
    }

    public String getChoice(int index, int num) {
        return lists.get(index).getChoice(num - 1);
    }

    public String getCorrectAnswer(int a) {
        return lists.get(a).getAnswer();
    }


    public void initQuestions(Context context) {
        databaseHelper = new DatabaseHelper(context);
        lists = databaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (lists.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            databaseHelper.addInitialQuestion(new Domande(" Chi ha ricevuto per tre volte il Premio Nobel per la Pace? ",
                    new String[]{"Jane Addams", "La croce rossa internazionale", "Martti Ahtisaari", "Norman Angell"}, "La croce rossa internazionale"));
            databaseHelper.addInitialQuestion(new Domande(" Quale animale sarebbe il miglior compagno dell'uomo nello spazio? ",
                    new String[]{"cane", "gatto", "criceto", "capra"}, "capra"));
            databaseHelper.addInitialQuestion(new Domande(" Cosa indica la sigla GPL? ",
                    new String[]{"gas propano liquefatto", "metano", "diesel", "ibrido"}, "gas propano liquefatto"));
            databaseHelper.addInitialQuestion(new Domande(" Quale resina fossilizzata usavano Greci e Romani per fare giolielli? ",
                    new String[]{"simetite", "ammoniti", "ambra", "raffiro"}, "ambra"));
            databaseHelper.addInitialQuestion(new Domande(" Qual è la citta' dell'Olanda che da il nome al tipico formaggio ricoperto da cera rossa? ",
                    new String[]{"Edam", "Amsterdam", "Leida", "Rotterdam"}, "Edam"));
            databaseHelper.addInitialQuestion(new Domande(" Quanti sono i giocatori di una squadra di pallamano? ",
                    new String[]{"otto", "nove", "sette", "dieci"}, "sette"));
            databaseHelper.addInitialQuestion(new Domande(" Il protocollo utilizzato dai Borwser? ",
                    new String[]{"HTTP", "TCP/IP", "ISO/OSI", "PHP"}, "HTTP"));
            databaseHelper.addInitialQuestion(new Domande(" Capitale del Portogallo? ",
                    new String[]{"Porto", "Roma", "Dacca", "lisbona"}, "lisbona"));
            databaseHelper.addInitialQuestion(new Domande(" La lettera a sinistra della L sul vostro PC? ",
                    new String[]{"Z", "K", "H", "E"}, "K"));
            databaseHelper.addInitialQuestion(new Domande(" Quale è il paese più a nord dell'america del Sud? ",
                    new String[]{"Cile", "Lima", "Brasile", "Colombia"}, "Colombia"));
            databaseHelper.addInitialQuestion(new Domande(" Come è chiamato comunemente un pallone da rugby? ",
                    new String[]{"Rotondo", "Ovale", "Quadrato", "Piatto"}, "Ovale"));
            databaseHelper.addInitialQuestion(new Domande(" Con quale sigla è più noto l'Active Server Pages? ",
                    new String[]{"PSA", "SAP", "ASP", "PAS"}, "ASP"));
            databaseHelper.addInitialQuestion(new Domande(" Da chi fu incoronato Napoleone come imperatore francese? ",
                    new String[]{"Dal papa", "Da Hitler", "Da Umberto I", "Se stesso"}, "Se stesso"));
            databaseHelper.addInitialQuestion(new Domande(" Qual è l'ortaggio nazionale della Scozia? ",
                    new String[]{"cipolla", "rapa", "peperone", "tegoline"}, "rapa"));
            databaseHelper.addInitialQuestion(new Domande(" Il più grande Hacker di tutti i Tempi? ",
                    new String[]{"Kevin Mitnick", "Anonymous", "Albert Gonzalez", "Michael Calce"}, "Kevin Mitnick"));
            databaseHelper.addInitialQuestion(new Domande(" Quale dittatore è morto nel 1953 in Unione Sovietica? ",
                    new String[]{"Stalin", "Putin", "Lenin", "Malenkov"}, "Stalin"));
            databaseHelper.addInitialQuestion(new Domande(" Dove si trova la sale degli specchi? ",
                    new String[]{"Versailles", "Dublino", "Vienna", "Lussemburgo"}, "Versailles"));
            databaseHelper.addInitialQuestion(new Domande(" Il protagonista del film Matrix? ",
                    new String[]{"Brad Pitt", "Tom Cruise", "Keanu Reeves", "John Travolta"}, "Keanu Reeves"));
            databaseHelper.addInitialQuestion(new Domande(" Completa il nome di questa sqadra di baseball:I Cleveland...? ",
                    new String[]{"Warriors", "Bucks", "Thunder", "Cavaliers"}, "Cavaliers"));

            lists = databaseHelper.getAllQuestionsList();//get list from database again

        }
    }
}

