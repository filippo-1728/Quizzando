package com.example.quizzando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_RisultatoFinale extends AppCompatActivity {
    private TextView msgNomeGiocatore, txtRisposteGiusteGiocatore,textErroriRispGiocatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__risultato_finale);
        msgNomeGiocatore = (TextView) findViewById(R.id.textNomeGiocatore);
        txtRisposteGiusteGiocatore = (TextView) findViewById(R.id.textCorretteRispGiocatore);
        textErroriRispGiocatore = (TextView) findViewById(R.id.textErroriRispGiocatore);

        Intent im1 = getIntent();
        if (im1.hasExtra("Png1")) {
            String msg = im1.getStringExtra("Png1");
            ImageView msgImg1 = (ImageView) findViewById(R.id.imageView1R2);
            msgImg1.setImageResource(im1.getIntExtra("Png1",0));
        }

        Intent colore = getIntent();
        if (colore.hasExtra("colore1")) {
            String msg = colore.getStringExtra("colore1");
            ImageView msgImg1 = (ImageView) findViewById(R.id.imageView1R2);
            msgImg1.setBackgroundColor(colore.getIntExtra("colore1",0));
        }

        Intent nome = getIntent();
        if (nome.hasExtra("Message")) {
            String msg = nome.getStringExtra("Message");
            msgNomeGiocatore.setText(msg);
        }

        Intent Giuste = getIntent();
        if (Giuste.hasExtra("Punteggio")) {
            String msgPunteggio = Giuste.getStringExtra("Punteggio");
            //int RispGiusteGioc = Integer.parseInt(msgPunteggio);
            txtRisposteGiusteGiocatore.setText(msgPunteggio);
        }

        Intent RispSbagliate = getIntent();
        if (RispSbagliate.hasExtra("Sbagliate")) {
            String msgErroriRisp = RispSbagliate.getStringExtra("Sbagliate");
            textErroriRispGiocatore.setText(msgErroriRisp);
        }
    }
}