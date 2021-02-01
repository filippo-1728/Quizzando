package com.example.quizzando;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class Activity_Registrazione extends AppCompatActivity {

    private ImageView img1;
    public int i=1, g=1, imagine1=-1,colore1=-1;
    private final String[] colors = {"Rosso", "Verde", "Giallo", "Blu", "Viola"};
    private final String[] immagini = {"Meme Gatto", "Gnomo meme", "Scooby Doo", "Boom"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registrazione);

        Button btn_ScegliImmagine = (Button) findViewById(R.id.btnScegliImmagine);
        Button btn_IndietroInCrea = (Button) findViewById(R.id.btnIndietroInCrea);
        Button btn_AvantiInCrea = (Button) findViewById(R.id.btnAvantiInCrea2);
        Button btn_SceltaDeiColori = (Button) findViewById(R.id.btn_SceltaColors);
        Button btn_ScegliRandom = (Button) findViewById(R.id.btnSceltaRandom);

        img1 = findViewById(R.id.imageView1R);

        btn_ScegliRandom.setOnClickListener(view ->{

            int[] images = new int[] {R.drawable.gnomomeme, R.drawable.gattomeme,R.drawable.scooby3,R.drawable.boom};

                int imageId1 = (int) (Math.random() * images.length);

                img1.setImageResource(images[imageId1]);

                imagine1= images[imageId1];

                Random rnd1 = new Random();
                int colorRandom1 = Color.argb(255, rnd1.nextInt(256), rnd1.nextInt(256), rnd1.nextInt(256));
                img1.setBackgroundColor(colorRandom1);

                colore1 = colorRandom1;
        });

        btn_ScegliImmagine.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Registrazione.this);
                builder.setTitle("Scegli una immagine");
                builder.setCancelable(false);
                builder.setItems(immagini, (dialog, which) -> {
                    switch (which) {
                        case 0: //
                            img1.setImageResource(R.drawable.gattomeme);
                            imagine1 = R.drawable.gattomeme;
                            break;
                        case 1: //
                            img1.setImageResource(R.drawable.gnomomeme);
                            imagine1 = R.drawable.gnomomeme;
                            break;
                        case 2: //
                            img1.setImageResource(R.drawable.scooby3);
                            imagine1 = R.drawable.scooby3;
                            break;
                        case 3: //
                            img1.setImageResource(R.drawable.boom);
                            imagine1 = R.drawable.boom;
                            break;
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
        });

        btn_SceltaDeiColori.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Registrazione.this);
                builder.setTitle("Scegli il colore della "+i+"° immagine");
                builder.setCancelable(false);
                builder.setItems(colors, (dialog, which) -> {
                    switch (which) {
                        case 0: // red
                            img1.setBackgroundColor(getResources().getColor(R.color.red));
                            colore1 = getResources().getColor(R.color.red);
                            break;
                        case 1: // green
                            img1.setBackgroundColor(getResources().getColor(R.color.green));
                            colore1 = getResources().getColor(R.color.green);
                            break;
                        case 2: // yellow
                            img1.setBackgroundColor(getResources().getColor(R.color.yellow));
                            colore1 = getResources().getColor(R.color.yellow);
                            break;
                        case 3: // blue
                            img1.setBackgroundColor(getResources().getColor(R.color.blue));
                            colore1 = getResources().getColor(R.color.blue);
                            break;
                        case 4: // violet
                            img1.setBackgroundColor(getResources().getColor(R.color.violet));
                            colore1 = getResources().getColor(R.color.violet);
                            break;

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
        });


        EditText edit=(EditText)findViewById(R.id.textNomeAutore);
        btn_AvantiInCrea.setOnClickListener (v -> {

            if(imagine1!=-1 && edit.getText().toString().trim().length()>0 ) // || colore1!=-1 || colore2!=-1 || colore3!=-1 || colore4!=-1
            {
                Intent c= new Intent (Activity_Registrazione.this,ActivityGioco.class);
                //c.putExtra("Message",msgNome.getText().toString());
                //c.putExtra("Prov",msgOpera.getText().toString());
                c.putExtra("Png1",imagine1);
                c.putExtra("colore1",colore1);
                c.putExtra("Message",edit.getText().toString());
                startActivity(c);
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Registrazione.this);
                builder.setTitle("Attenzione");
                builder.setMessage("Inserimento non valido");
                builder.setCancelable(false);
                builder.setNeutralButton("Chiudi", (dialog, id) -> {
                    dialog.dismiss(); // quit AlertDialog
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        btn_IndietroInCrea.setOnClickListener(v -> {
            img1.setBackgroundColor(getResources().getColor(R.color.white));
            colore1 = getResources().getColor(R.color.white);
            img1.setImageResource(0);
            imagine1=-1;
        });
    }
}