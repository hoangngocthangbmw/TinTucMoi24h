package com.watermelon.thanghn.bottomnavigation.model.app.database;

/**
 * Created by thang on 2/22/2018.
 */

public class NewsOff {
    private String link;
   private String tittle, date,decription,content, image, author;

    public NewsOff() {
    }

    public NewsOff(String link, String tittle, String date, String decription, String content, String image, String author) {
        this.link = link;
        this.tittle = tittle;
        this.date = date;
        this.decription = decription;
        this.content = content;
        this.image = image;
        this.author = author;
    }

    public NewsOff(String link, String tittle, String date, String content, String image) {
        this.link = link;
        this.tittle = tittle;
        this.date = date;
        this.content = content;
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
