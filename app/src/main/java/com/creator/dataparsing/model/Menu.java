package com.creator.dataparsing.model;

import java.io.Serializable;

public class Menu implements Serializable{

    private String id;
    private String mall;
    private String menu_item;
    private String menu_item_name;
    private String description;
    private String menu_price;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getMenuItem() {
        return menu_item;
    }

    public void setMenuItem(String menuItem) {
        this.menu_item = menuItem;
    }

    public String getMenuItemName() {
        return menu_item_name;
    }

    public void setMenuItemName(String menuItemName) {
        this.menu_item_name = menuItemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}