package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.example.amitbhattarai.graycodeaccounting.R;

/**
 * Created by Amit on 7/8/17.
 */

public class NavMenuModel {
    public String menuTitle;
    public int menuIconDrawable;
    public List<SubMenuModel> subMenu;

    public NavMenuModel(String menuTitle, int menuIconDrawable) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.subMenu = new ArrayList<>();
    }

    public NavMenuModel(String menuTitle, int menuIconDrawable, ArrayList<SubMenuModel> subMenu) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.subMenu = new ArrayList<>();
        this.subMenu.addAll(subMenu);
    }

    public static class SubMenuModel {
        public String subMenuTitle;
        public int images;

        public SubMenuModel(String subMenuTitle,int imageid) {
            this.subMenuTitle = subMenuTitle;
            this.images=imageid;
        }
    }
}
