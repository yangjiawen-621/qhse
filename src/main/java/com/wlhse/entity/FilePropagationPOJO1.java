package com.wlhse.entity;

import java.util.List;

/**
 * Description:
 * Author:Coco
 * create:2020-08-04 6:31 PM
 **/
public class FilePropagationPOJO1 extends FilePropagationPOJO {

    private List<String> filePath;

    public List<String> getFilePath() {
        return filePath;
    }

    public void setFilePath(List<String> filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return super.toString()+
                "filePath=" + filePath +
                '}';
    }
}
