package com.DP2P;

import java.io.Serializable;
import java.util.ArrayList;

public class Pong implements Serializable {
    private ArrayList<String> fileList;

    public ArrayList<String> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<String> fileList) {
        this.fileList = fileList;
    }
}
