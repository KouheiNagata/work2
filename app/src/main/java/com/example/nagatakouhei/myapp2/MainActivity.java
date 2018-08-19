package com.example.nagatakouhei.myapp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // SDカードから取得した情報を保持
    private ArrayList<File> mPrePictureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListView = (ListView)findViewById(R.id.listview);
        Button mButton = (Button)findViewById(R.id.button);
        ArrayList<ListItem> mListItems = new ArrayList<>();
        mPrePictureList = new ArrayList<File>();

        // SDカードにアクセス
        File mfile = new File("/storage/emulated/0/");//SDカードにアクセス
        searchPictureFile(mfile);

        for (File file : mPrePictureList) {
            InputStream stream = null;
            try {
                stream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(stream));
            ListItem item = new ListItem(bmp, getFileName(file.getName()), String.valueOf(file.length()));
            mListItems.add(item);
        }

        ListAdapter adapter = new ListAdapter(this, R.layout.list_item, mListItems);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplication(), Picture.class);
                intent.setType("image/jpeg");
                intent.putExtra("picture", mPrePictureList.get(position));
                startActivity(intent);

            }
        });

    }

    // 指定したパスの中から jpegファイルを取得する
    public void searchPictureFile(File file) {
        if (file.isDirectory()){
            File[] files =file.listFiles();

            for(File file2: files){
                searchPictureFile(file2);
            }

        } else {
            if(file.getName().endsWith("jpeg") || file.getName().endsWith("JPEG")){
                mPrePictureList.add(file);
            }
        }
    }

    // ファイル名から拡張子を除いた文字列を取得する
    public static String getFileName(String fileName) {
        String newName;

        int lastPosition = fileName.lastIndexOf('.');
        if (lastPosition > 0) {
            newName = fileName.substring(0, lastPosition);
        } else {
            newName = fileName;
        }
        return newName;
    }

}
