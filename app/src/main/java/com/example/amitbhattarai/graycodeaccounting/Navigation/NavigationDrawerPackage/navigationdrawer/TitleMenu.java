package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Amit on 7/7/17.
 */

public class TitleMenu extends ExpandableGroup<SubTitle> {
    private int imageResource = 0;

    public TitleMenu(String title, List<SubTitle> items, int imageResource) {
        super(title, items);
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }
}