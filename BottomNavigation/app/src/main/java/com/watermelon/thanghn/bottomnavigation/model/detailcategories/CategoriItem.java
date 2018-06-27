package com.watermelon.thanghn.bottomnavigation.model.detailcategories;

/**
 * Created by thang on 3/12/2018.
 */

public class CategoriItem {
    private String name;
    private String link;

    public CategoriItem() {
    }

    public CategoriItem(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
