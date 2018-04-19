package com.optic.quizdosi013214.models;

/**
 * Created by User on 19/04/2018.
 */

public class Photo {

    private String title;
    private String thumbnailUrl;

    public Photo(){

    }

    public Photo(String title, String thumbnailUrl) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "Title: " + title + " ThumbnailUrl: " + thumbnailUrl;
    }
}
