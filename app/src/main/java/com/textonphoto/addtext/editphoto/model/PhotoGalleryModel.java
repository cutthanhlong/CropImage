package com.textonphoto.addtext.editphoto.model;

public class PhotoGalleryModel {
    private int id;
    private String path;
    private String size;

    public PhotoGalleryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
