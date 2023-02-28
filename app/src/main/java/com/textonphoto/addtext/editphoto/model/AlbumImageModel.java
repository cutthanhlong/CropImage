package com.textonphoto.addtext.editphoto.model;

import com.textonphoto.addtext.editphoto.adapter.AdapterListFoder;

import java.util.Vector;

public class AlbumImageModel {
    private Vector<PhotoModel> albumPhotos;
    private String coverUri;
    private long id;
    private String name;
    AdapterListFoder adapterListFoder;

    public long getId() {
        return this.id;
    }

    public void setId(long i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCoverUri() {
        return this.coverUri;
    }

    public void setCoverUri(String str) {
        this.coverUri = str;
    }

    public Vector<PhotoModel> getAlbumPhotos() {
        if (this.albumPhotos == null) {
            this.albumPhotos = new Vector<>();
        }
        return this.albumPhotos;
    }

    public AdapterListFoder getAdapterListFoder() {
        return adapterListFoder;
    }

    public void setAdapterListFoder(AdapterListFoder adapterListFoder) {
        this.adapterListFoder = adapterListFoder;
    }

    public void setAlbumPhotos(Vector<PhotoModel> vector) {
        this.albumPhotos = vector;
    }
}
