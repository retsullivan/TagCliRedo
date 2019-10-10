package com.improving.tagcliredo.Models;

public class Item {


    private String name;
    private int weight;
    private int value;
    private boolean burnable;
    private String magicQuality;

    public Item(String name, int weight, int value, boolean burnable, String magicQuality) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.burnable = burnable;
        this.magicQuality = magicQuality;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public int getBurnable() {
        if (burnable) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getMagicQuality() {
        return magicQuality;
    }
}

