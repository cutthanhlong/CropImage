package com.pic.collage.lib;

import androidx.room.Entity;

import java.util.ArrayList;

@Entity(tableName = "ItemTopic", primaryKeys = {"id", "idParent"})
public class ItemTopic {
    int id;
    int idParent;
    String avatar;
    String linkBackground ="";
    String linkBackgroundCard = "";
    int sizeEditor;
    boolean accept = false;
    boolean recent = false;
    ArrayList<TextTemplate> texts = new ArrayList<>();
    ArrayList<DrawableTemplate> drawables = new ArrayList<>();

    public ItemTopic(int id, int idParent, int sizeEditor, String avatar, String linkBackground) {
        this.id = id;
        this.idParent = idParent;
        this.avatar = avatar;
        this.linkBackground = linkBackground;
        this.sizeEditor = sizeEditor;
    }

    public ItemTopic(int id, int idParent, int sizeEditor, String avatar, String linkBackground, String linkBackgroundCard) {
        this.id = id;
        this.idParent = idParent;
        this.avatar = avatar;
        this.linkBackground = linkBackground;
        this.linkBackgroundCard = linkBackgroundCard;
        this.sizeEditor = sizeEditor;
    }

    public ItemTopic() {
    }

    public ItemTopic(int id, int idParent, int sizeEditor, String avatar) {
        this.id = id;
        this.idParent = idParent;
        this.avatar = avatar;
        this.sizeEditor = sizeEditor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLinkBackground() {
        return linkBackground;
    }

    public void setLinkBackground(String linkBackground) {
        this.linkBackground = linkBackground;
    }

    public int getSizeEditor() {
        return sizeEditor;
    }

    public void setSizeEditor(int sizeEditor) {
        this.sizeEditor = sizeEditor;
    }

    public ArrayList<TextTemplate> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<TextTemplate> texts) {
        this.texts = texts;
    }

    public String getLinkBackgroundCard() {
        return linkBackgroundCard;
    }

    public void setLinkBackgroundCard(String linkBackgroundCard) {
        this.linkBackgroundCard = linkBackgroundCard;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isRecent() {
        return recent;
    }

    public void setRecent(boolean recent) {
        this.recent = recent;
    }

    public ArrayList<DrawableTemplate> getDrawables() {
        return drawables;
    }

    public void setDrawables(ArrayList<DrawableTemplate> drawables) {
        this.drawables = drawables;
    }
}
