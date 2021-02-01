package com.example.quizzando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Activity_RisultatoFinale extends AppCompatActivity {
    private TextView msgNomeGiocatore, txtRisposteGiusteGiocatore,textErroriRispGiocatore,textPunteggioGiocatore2;
    private Button btn_FineRisultato,btnScreen;
    private String RispCo,RispSb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__risultato_finale);
        msgNomeGiocatore = (TextView) findViewById(R.id.textNomeGiocatore);
        txtRisposteGiusteGiocatore = (TextView) findViewById(R.id.textCorretteRispGiocatore);
        textErroriRispGiocatore = (TextView) findViewById(R.id.textErroriRispGiocatore);
        btn_FineRisultato = (Button) findViewById(R.id.btn_FineRisultato);
        btnScreen = (Button) findViewById(R.id.btnScreen);
        textPunteggioGiocatore2 = (TextView) findViewById(R.id.textPunteggioGiocatore2);
        VerificaPermessi(this);

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
            txtRisposteGiusteGiocatore.setText(msgPunteggio);
            RispCo = msgPunteggio;
        }

        Intent RispSbagliate = getIntent();
        if (RispSbagliate.hasExtra("Sbagliate")) {
            String msgErroriRisp = RispSbagliate.getStringExtra("Sbagliate");
            textErroriRispGiocatore.setText(msgErroriRisp);
            RispSb = msgErroriRisp;
        }

        textPunteggioGiocatore2.setText(RispCo+"/"+RispSb);

        btn_FineRisultato.setOnClickListener(v ->{
            finish();
        });

        btnScreen.setOnClickListener(view ->{
            btnScreen.setVisibility(View.INVISIBLE);
            saveScreen(getWindow().getDecorView().getRootView(),"result");
            Toast.makeText(Activity_RisultatoFinale.this, "Screen effettuato!", Toast.LENGTH_SHORT).show();
        });

    }

    private File saveScreen(View view, String nomefile) {
        Date date = new Date();
        CharSequence format = DateFormat.format("yyyy-MM-dd_hh:mm:ss",date);
        try {
            String dirPath = Environment.getExternalStorageDirectory().toString()+"/Gallery";
            File dirFile = new File(dirPath);
            if(!dirFile.exists())
            {
                boolean mkDir = dirFile.mkdir();

            }
            String path = dirPath+"/"+nomefile+"-"+format+".jpeg";
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File image = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(image);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return image;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final int REQUEST_EXTERNAL_STORAGE=1;
    private static final String [] PERMISSION_STORAGE={Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    public static void VerificaPermessi(Activity activity)
    {
        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(activity,PERMISSION_STORAGE,REQUEST_EXTERNAL_STORAGE);
        }
    }
}