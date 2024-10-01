package com.example.clickimagen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMostrarImagen = findViewById(R.id.btnMostrarImagen);
        imageView = findViewById(R.id.imageView);

        btnMostrarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // URL de la imagen
                            String imageUrl = "https://i.imgur.com/EQXyIaM.jpg";
                            InputStream input = new URL(imageUrl).openStream();
                            final Bitmap bitmap = BitmapFactory.decodeStream(input);

                            // Actualizar la UI en el hilo principal
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                    imageView.setVisibility(View.VISIBLE); // Mostrar la imagen
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
