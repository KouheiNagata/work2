package com.example.nagatakouhei.myapp2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Picture extends Activity {
        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.picture);

            ImageView view = findViewById(R.id.picture);

            Intent intent = getIntent();
            File file = (File) intent.getSerializableExtra("picture");

            try {
                InputStream stream = new FileInputStream(file);
                Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(stream));
                view.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
}

