package com.watermelon.thanghn.bottomnavigation.screen.personal;

/**
 * Created by thang on 4/28/2018.
 */

public class Newspaper {
    private String link;
    private String image;
    private String name;

    public Newspaper() {
    }

    public Newspaper(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public Newspaper(String link, String image, String name) {
        this.link = link;
        this.image = image;
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
