package com.example.quizzando;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ActivityGioco extends AppCompatActivity {

    private Button BtnRisposta1, BtnRisposta2, BtnRisposta3, BtnRisposta4, Btn_IniziaGioco, btn_ScorrDomanda, btn_FineGIoco;
    private TextView VisualizzaDomanda, VisualizzaPunteggio, VisualizzaTempo, TextView_Punteggio, textView_Timer, TextViewDomanda, textView_PreGioco,textNomeGiocatore2,textViewRisultatoFinale2;
    private TableLayout tableLayout2;
    private ImageView imageView1R3;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber;
    private CountDownTimer countDownTimer;
    public int RisposteSbagliate = 0;
    private String NomeGiocatore,Sbagliate,Giuste;


    private static final long START_TIME_IN_MILLIS = 10000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private RaccoltaDomande mQuestionLibrary = new RaccoltaDomande();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioco);
        VisualizzaDomanda = (TextView) findViewById(R.id.TextView_VisualizzaDomanda);
        VisualizzaPunteggio = (TextView) findViewById(R.id.textView_VisualizzaPunteggio);
        VisualizzaTempo = (TextView) findViewById(R.id.textView_VisualizzaTimer);
        BtnRisposta1 = (Button) findViewById(R.id.Btn_Scelta1);
        BtnRisposta2 = (Button) findViewById(R.id.Btn_Scelta2);
        BtnRisposta3 = (Button) findViewById(R.id.Btn_Scelta3);
        BtnRisposta4 = (Button) findViewById(R.id.Btn_Scelta4);
        Btn_IniziaGioco = (Button) findViewById(R.id.Btn_InizioGioco);
        tableLayout2 = (TableLayout) findViewById(R.id.tableLayout2);
        TextView_Punteggio = (TextView) findViewById(R.id.TextView_Punteggio);
        textView_Timer = (TextView) findViewById(R.id.textView_Timer);
        TextViewDomanda = (TextView) findViewById(R.id.TextViewDomanda);
        textView_PreGioco = (TextView) findViewById(R.id.textView_PreGioco);
        btn_ScorrDomanda = (Button) findViewById(R.id.btn_ScorrDomanda);
        btn_ScorrDomanda.setVisibility(View.INVISIBLE);
        textNomeGiocatore2 = (TextView) findViewById(R.id.textNomeGiocatore2);
        textNomeGiocatore2.setVisibility(View.INVISIBLE);
        imageView1R3 = (ImageView) findViewById(R.id.imageView1R3);
        imageView1R3.setVisibility(View.INVISIBLE);
        btn_FineGIoco = (Button) findViewById(R.id.btn_FineGioco);
        textViewRisultatoFinale2 = (TextView) findViewById(R.id.textViewRisultatoFinale2);

        mQuestionLibrary.initQuestions(getApplicationContext());
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);
        Regolamento();



        Intent im1 = getIntent();
        if (im1.hasExtra("Png1")) {
            String msg = im1.getStringExtra("Png1");
            ImageView msgImg1 = (ImageView) findViewById(R.id.imageView1R3);
            msgImg1.setImageResource(im1.getIntExtra("Png1",0));

        }

        Intent colore = getIntent();
        if (colore.hasExtra("colore1")) {
            String msg = colore.getStringExtra("colore1");
            ImageView msgImg1 = (ImageView) findViewById(R.id.imageView1R3);
            msgImg1.setBackgroundColor(colore.getIntExtra("colore1",0));
        }

        Intent t = getIntent();
        if (t.hasExtra("Message")) {
            String msg = t.getStringExtra("Message");
            textNomeGiocatore2.setText(msg);
            NomeGiocatore = msg;
        }

        btn_FineGIoco.setOnClickListener(v ->
        {
            Giuste = "" + mScore;
            Sbagliate = "" + RisposteSbagliate;
            Intent i = new Intent(ActivityGioco.this,Activity_RisultatoFinale.class);
            i.putExtra("Png1",im1.getIntExtra("Png1",0));
            i.putExtra("colore1",colore.getIntExtra("colore1",0));
            i.putExtra("Message",NomeGiocatore);
            i.putExtra("Punteggio",Giuste);
            i.putExtra("Sbagliate",Sbagliate);
            startActivity(i);
            finish();
        });


        btn_ScorrDomanda.setOnClickListener(v -> {

            if (mTimerRunning) {
                pauseTimer();

            } else {
                resetTimer();
                startTimer();
            }
            updateQuestion();

            BtnRisposta1.setBackgroundColor(Color.BLUE);
            BtnRisposta2.setBackgroundColor(Color.BLUE);
            BtnRisposta3.setBackgroundColor(Color.BLUE);
            BtnRisposta4.setBackgroundColor(Color.BLUE);
            BtnRisposta1.setEnabled(true);
            BtnRisposta2.setEnabled(true);
            BtnRisposta3.setEnabled(true);
            BtnRisposta4.setEnabled(true);
            btn_ScorrDomanda.setVisibility(View.INVISIBLE);
        });


        Btn_IniziaGioco.setOnClickListener(v -> {

            if(Btn_IniziaGioco.getText().toString().equals("PRONTO?"))
            {
                Btn_IniziaGioco.setText("INIZIA");
                textViewRisultatoFinale2.setText("Quizzando");

            }
            if(Btn_IniziaGioco.getText().toString().equals("INIZIA"))
            {
                BtnRisposta1.setVisibility(View.VISIBLE);
                BtnRisposta2.setVisibility(View.VISIBLE);
                BtnRisposta3.setVisibility(View.VISIBLE);
                BtnRisposta4.setVisibility(View.VISIBLE);
                tableLayout2.setVisibility(View.VISIBLE);
                VisualizzaTempo.setVisibility(View.VISIBLE);
                VisualizzaPunteggio.setVisibility(View.VISIBLE);
                VisualizzaDomanda.setVisibility(View.VISIBLE);
                TextView_Punteggio.setVisibility(View.VISIBLE);
                textView_Timer.setVisibility(View.VISIBLE);
                TextViewDomanda.setVisibility(View.VISIBLE);
                Btn_IniziaGioco.setVisibility(View.VISIBLE);
                textView_PreGioco.setVisibility(View.INVISIBLE);
                Btn_IniziaGioco.setVisibility(View.INVISIBLE);
            }


            if (mTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });


        BtnRisposta1.setOnClickListener(v -> {

            if (mTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }

            btn_ScorrDomanda.setVisibility(View.VISIBLE);
            if (BtnRisposta1.getText().equals(mAnswer)) {
                mScore = mScore + 1;
                SuonoRispCorretta();
                Toast.makeText(ActivityGioco.this, "Corretto!", Toast.LENGTH_SHORT).show();
                BtnRisposta1.setBackgroundColor(Color.GREEN);
                BtnRisposta1.setEnabled(false);
                updateScore(mScore);
            } else {
                Toast.makeText(ActivityGioco.this, "Sbagliata!", Toast.LENGTH_SHORT).show();
                SuonoRispSbagliata();
                RisposteSbagliate = RisposteSbagliate +1;
                BtnRisposta1.setEnabled(false);
                BtnRisposta1.setBackgroundColor(Color.RED);
            }

            if (BtnRisposta2.getText().equals(mAnswer)) {
                BtnRisposta2.setBackgroundColor(Color.GREEN);
                BtnRisposta2.setEnabled(false);
            } else {
                BtnRisposta2.setBackgroundColor(Color.RED);
                BtnRisposta2.setEnabled(false);
            }

            if (BtnRisposta3.getText().equals(mAnswer)) {
                BtnRisposta3.setBackgroundColor(Color.GREEN);
                BtnRisposta3.setEnabled(false);
            } else {
                BtnRisposta3.setBackgroundColor(Color.RED);
                BtnRisposta3.setEnabled(false);
            }

            if (BtnRisposta4.getText().equals(mAnswer)) {
                BtnRisposta4.setBackgroundColor(Color.GREEN);
                BtnRisposta4.setEnabled(false);
            } else {
                BtnRisposta4.setBackgroundColor(Color.RED);
                BtnRisposta4.setEnabled(false);
            }
            UltimaDomanda();
        });
        BtnRisposta2.setOnClickListener(v -> {

            if (mTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }

            btn_ScorrDomanda.setVisibility(View.VISIBLE);
            if (BtnRisposta2.getText().equals(mAnswer)) {
                mScore = mScore + 1;
                SuonoRispCorretta();
                Toast.makeText(ActivityGioco.this, "Corretto!", Toast.LENGTH_SHORT).show();
                BtnRisposta2.setBackgroundColor(Color.GREEN);
                BtnRisposta2.setEnabled(false);
                updateScore(mScore);
            } else {
                Toast.makeText(ActivityGioco.this, "Sbagliata!", Toast.LENGTH_SHORT).show();
                SuonoRispSbagliata();
                RisposteSbagliate = RisposteSbagliate +1;
                BtnRisposta2.setBackgroundColor(Color.RED);
                BtnRisposta2.setEnabled(false);
            }

            if (BtnRisposta1.getText().equals(mAnswer)) {
                BtnRisposta1.setBackgroundColor(Color.GREEN);
                BtnRisposta1.setEnabled(false);
            } else {
                BtnRisposta1.setBackgroundColor(Color.RED);
                BtnRisposta1.setEnabled(false);
            }

            if (BtnRisposta3.getText().equals(mAnswer)) {
                BtnRisposta3.setBackgroundColor(Color.GREEN);
                BtnRisposta3.setEnabled(false);
            } else {
                BtnRisposta3.setBackgroundColor(Color.RED);
                BtnRisposta3.setEnabled(false);
            }

            if (BtnRisposta4.getText().equals(mAnswer)) {
                BtnRisposta4.setBackgroundColor(Color.GREEN);
                BtnRisposta4.setEnabled(false);
            } else {
                BtnRisposta4.setBackgroundColor(Color.RED);
                BtnRisposta4.setEnabled(false);
            }
            UltimaDomanda();
        });
        BtnRisposta3.setOnClickListener(v -> {

            if (mTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }

            btn_ScorrDomanda.setVisibility(View.VISIBLE);
            if (BtnRisposta3.getText().equals(mAnswer)) {
                mScore = mScore + 1;
                SuonoRispCorretta();
                Toast.makeText(ActivityGioco.this, "Corretto!", Toast.LENGTH_SHORT).show();
                BtnRisposta3.setBackgroundColor(Color.GREEN);
                BtnRisposta3.setEnabled(false);
                updateScore(mScore);
            } else {
                Toast.makeText(ActivityGioco.this, "Sbagliata!", Toast.LENGTH_SHORT).show();
                SuonoRispSbagliata();
                RisposteSbagliate = RisposteSbagliate +1;
                BtnRisposta3.setBackgroundColor(Color.RED);
                BtnRisposta3.setEnabled(false);
            }

            if (BtnRisposta4.getText().equals(mAnswer)) {
                BtnRisposta4.setBackgroundColor(Color.GREEN);
                BtnRisposta4.setEnabled(false);
            } else {
                BtnRisposta4.setBackgroundColor(Color.RED);
                BtnRisposta4.setEnabled(false);
            }

            if (BtnRisposta1.getText().equals(mAnswer)) {
                BtnRisposta1.setBackgroundColor(Color.GREEN);
                BtnRisposta1.setEnabled(false);
            } else {
                BtnRisposta1.setBackgroundColor(Color.RED);
                BtnRisposta1.setEnabled(false);
            }

            if (BtnRisposta2.getText().equals(mAnswer)) {
                BtnRisposta2.setBackgroundColor(Color.GREEN);
                BtnRisposta2.setEnabled(false);
            } else {
                BtnRisposta2.setBackgroundColor(Color.RED);
                BtnRisposta2.setEnabled(false);
            }
            UltimaDomanda();
        });
        BtnRisposta4.setOnClickListener(v -> {

            if (mTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }

            btn_ScorrDomanda.setVisibility(View.VISIBLE);
            if (BtnRisposta4.getText().equals(mAnswer)) {
                mScore = mScore + 1;
                SuonoRispCorretta();
                Toast.makeText(ActivityGioco.this, "Corretto!", Toast.LENGTH_SHORT).show();
                BtnRisposta4.setBackgroundColor(Color.GREEN);
                BtnRisposta4.setEnabled(false);
                updateScore(mScore);
            } else {
                Toast.makeText(ActivityGioco.this, "Sbagliata!", Toast.LENGTH_SHORT).show();
                SuonoRispSbagliata();
                RisposteSbagliate = RisposteSbagliate +1;
                BtnRisposta4.setBackgroundColor(Color.RED);
                BtnRisposta4.setEnabled(false);
            }

            if (BtnRisposta3.getText().equals(mAnswer)) {
                BtnRisposta3.setBackgroundColor(Color.GREEN);
                BtnRisposta3.setEnabled(false);
            } else {
                BtnRisposta3.setBackgroundColor(Color.RED);
                BtnRisposta3.setEnabled(false);
            }

            if (BtnRisposta2.getText().equals(mAnswer)) {
                BtnRisposta2.setBackgroundColor(Color.GREEN);
                BtnRisposta2.setEnabled(false);
            } else {
                BtnRisposta2.setBackgroundColor(Color.RED);
                BtnRisposta2.setEnabled(false);
            }
            if (BtnRisposta1.getText().equals(mAnswer)) {
                BtnRisposta1.setBackgroundColor(Color.GREEN);
                BtnRisposta1.setEnabled(false);
            } else {
                BtnRisposta1.setBackgroundColor(Color.RED);
                BtnRisposta1.setEnabled(false);
            }
            UltimaDomanda();
        });
        updateCountDownText();
    }

        private void startTimer() {
            countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }
                @Override
                public void onFinish() {
                    mTimerRunning = false;
                    btn_ScorrDomanda.setVisibility(View.INVISIBLE);
                    if (mQuestionNumber < mQuestionLibrary.getLength())
                    {
                        updateQuestion();
                        SuonoRispSbagliata();
                        resetTimer();
                        startTimer();
                        RisposteSbagliate = RisposteSbagliate +1;
                        BtnRisposta1.setEnabled(true);
                        BtnRisposta2.setEnabled(true);
                        BtnRisposta3.setEnabled(true);
                        BtnRisposta4.setEnabled(true);
                        BtnRisposta1.setBackgroundColor(Color.BLUE);
                        BtnRisposta2.setBackgroundColor(Color.BLUE);
                        BtnRisposta3.setBackgroundColor(Color.BLUE);
                        BtnRisposta4.setBackgroundColor(Color.BLUE);
                    }
                }
            }.start();
            mTimerRunning = true;
        }
        private void pauseTimer() {
            countDownTimer.cancel();
            mTimerRunning = false;
        }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }
    private void updateCountDownText() {
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        int ms = (int) (mTimeLeftInMillis % 1000);
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d.%03d", seconds,ms);
        VisualizzaTempo.setText(timeLeftFormatted);
    }

    private void updateQuestion() {
        // check if we are not outside array bounds for questions
        if (mQuestionNumber < mQuestionLibrary.getLength()) {
            // set the text for new question,
            // and new 4 alternative to answer on four buttons
            VisualizzaDomanda.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            BtnRisposta1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            BtnRisposta2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            BtnRisposta3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            BtnRisposta4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
    }

    private void SuonoRispCorretta()
    {
        final MediaPlayer SuonoGiusto = MediaPlayer.create(this,R.raw.correct_sound);
        SuonoGiusto.start();

    }

    private void SuonoRispSbagliata()
    {
        final MediaPlayer SuonoSbagliato = MediaPlayer.create(this,R.raw.incorrect_sound);
        SuonoSbagliato.start();
    }

    private void UltimaDomanda()
    {
        if(mQuestionNumber == mQuestionLibrary.getLength()) {
            btn_ScorrDomanda.setEnabled(false);
            BtnRisposta1.setEnabled(false);
            BtnRisposta2.setEnabled(false);
            BtnRisposta3.setEnabled(false);
            BtnRisposta4.setEnabled(false);
            btn_FineGIoco.setVisibility(View.VISIBLE);
            Toast.makeText(ActivityGioco.this, "Era l'ultima domanda!", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGioco.this);
            builder.setTitle("Attenzione");
            builder.setMessage("Quiz finito!");
            builder.setCancelable(false);
            builder.setNeutralButton("Chiudi", (dialog, id) -> {
                dialog.dismiss(); // quit AlertDialog
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void Regolamento()
    {
        String Regole;
        Regole = "1) Per ogni domanda si hanno 10 secondi al termine di questi ultimi si passerà alla domanda successiva\n\n2) Una volta scelta la risposta verranno autamaticamente mostrate quelle sbagliate e quella vera\n\n3) Dopo aver scelto la risposta bisogna cliccare sul pulsante per poter andare avanti\n\n4) Ultima cosa divertirsi!";
        textView_PreGioco.setText(Regole);
    }

    private void updateScore(int point) {
        VisualizzaPunteggio.setText("" + mScore + "/" + mQuestionLibrary.getLength());
    }

}