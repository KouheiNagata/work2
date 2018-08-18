package com.example.nagatakouhei.myapp2;

import android.graphics.Bitmap;

public class ListItem {
    private Bitmap mThumbnail = null;
    private String mFileName = null;
    private String mFileSize = null;

    public ListItem(){};

    public ListItem(Bitmap thumbnail, String title, String size) {
        mThumbnail = thumbnail;
        mFileName = title;
        mFileSize = size;
    }

    public void setThumbnail(Bitmap thumbnail) {
        mThumbnail = thumbnail;
    }

    public void setFileName(String title) {
        mFileName = title;
    }

    public void setFileSize(String size) {
        mFileSize = size;
    }

    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    public String getFileName() {
        return mFileName;
    }

    public String getFileSize(){
        return mFileSize;
    }


}
