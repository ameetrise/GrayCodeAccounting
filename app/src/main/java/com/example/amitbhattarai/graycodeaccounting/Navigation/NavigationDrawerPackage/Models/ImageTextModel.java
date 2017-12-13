package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

/**
 * Created by amitbhattarai on 12/12/17.
 */

public class ImageTextModel {
    String text;
    Integer image;

    public ImageTextModel(String text, Integer image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
