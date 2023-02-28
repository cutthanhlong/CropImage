package com.textonphoto.addtext.editphoto.model;

import java.util.List;

public class PhotoModel {
    public String albumName;
    public long id;
    public String photoUri;
    public List<Integer> orientationList;


    public List<Integer> getOrientationList() {
        return orientationList;
    }

    public void setOrientationList(List<Integer> orientationList) {
        this.orientationList = orientationList;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long i) {
        this.id = i;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public String getPhotoUri() {
        return this.photoUri;
    }

    public void setPhotoUri(String str) {
        this.photoUri = str;
    }
}
