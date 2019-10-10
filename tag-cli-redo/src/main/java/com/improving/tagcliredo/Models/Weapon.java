package com.improving.tagcliredo.Models;

public class Weapon {

    private String name;
    private String area;
    private String itemType = "weapon";

    public Weapon(String name, String area, String itemType){

        this.name = name;
        this.area = area;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getItemType() {return itemType;
    }
}
