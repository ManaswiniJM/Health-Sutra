package com.example.login1.Clinic_Compass;

public class ListItem {
    private int imageResId;
    private String text;

    public ListItem(int imageResId, String text) {
        this.imageResId = imageResId;
        this.text = text;
    }

    public int getImageResId() {
        return this.imageResId;
    }

    public String getText() {
        return this.text;
    }
}
