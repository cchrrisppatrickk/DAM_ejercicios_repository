package com.example.helloword;

public class ColorItem {

    //-
    private String colorName;
    private String hexCode;
    private int colorValue;

    public ColorItem(String colorName, String hexCode, int colorValue) {
        this.colorName = colorName;
        this.hexCode = hexCode;
        this.colorValue = colorValue;
    }

    public String getColorName() {
        return colorName;
    }

    public String getHexCode() {
        return hexCode;
    }

    public int getColorValue() {
        return colorValue;
    }
}