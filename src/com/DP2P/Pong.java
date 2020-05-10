package com.DP2P;

import java.io.Serializable;
import java.util.ArrayList;

public class Pong implements Serializable {
    private ArrayList<FileInfo> fileList;

    public ArrayList<FileInfo> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<FileInfo> fileList) {
        this.fileList = fileList;
    }
}
