package com.example.nagatakouhei.myapp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList<File> mPrePictureList;
    private ArrayList<Map<String, Object>> mPictureList = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listview);
        ArrayList<ListItem> listItems = new ArrayList<>();
        mPrePictureList = new ArrayList<File>();

        // SDカードにアクセス
        File mfile = new File("/storage/emulated/0/");//SDカードにアクセス
        searchPictureFile(mfile);

        for (File file : mPrePictureList) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            ListItem item = new ListItem(bmp, getFileName(file.getName()), String.valueOf(file.length()));
            listItems.add(item);
        }

        ListAdapter adapter = new ListAdapter(this, R.layout.list_item, listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
