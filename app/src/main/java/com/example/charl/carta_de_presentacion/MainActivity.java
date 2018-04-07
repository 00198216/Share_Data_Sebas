package com.example.charl.carta_de_presentacion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private Button Button;

    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;

    private ImageView Img;

    private Bitmap BM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Img = (ImageView) findViewById(R.id.Img);

        Button = findViewById(R.id.Bttn);
        A = findViewById(R.id.Name);
        B = findViewById(R.id.Work);
        C = findViewById(R.id.GH);
        D = findViewById(R.id.WA);
        E = findViewById(R.id.Mail);
        F = findViewById(R.id.Tweet);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share(v);
            }
        });
    }


        public void Share(View view){

        BM = getBitmapFromView(Img);

        try{
            File file= new File(this.getExternalCacheDir(),"Sebas.png");

            FileOutputStream Ou = new FileOutputStream(file);

            BM.compress(Bitmap.CompressFormat.PNG,100, Ou);
            Ou.flush();
            Ou.close();

            file.setReadable(true, false);

            final Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TEXT,"Informacion Sobre Mi \n Nombre:"+A.getText().toString()+"\n Oficio:"+B.getText().toString()+"\n Github:"+C.getText().toString()+"\n Telefono:"+D.getText().toString()+"\n Correo:"+E.getText().toString()+"\n Twitter:"+F.getText().toString());
            intent.setType("*/*");

            startActivity(Intent.createChooser(intent, "Enviar a"));

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private Bitmap getBitmapFromView(View view){

            Bitmap  Result = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
            Canvas One = new Canvas(Result);
            Drawable Background = view.getBackground();

            if(Background !=null)
                Background.draw(One);

            else
                One.drawColor(Color.WHITE);

            view.draw(One);
            return Result;
    }


    }

