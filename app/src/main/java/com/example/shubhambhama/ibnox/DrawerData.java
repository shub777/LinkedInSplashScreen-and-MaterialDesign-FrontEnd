package com.example.shubhambhama.ibnox;

/**
 * Created by SHUBHAMBHAMA on 13-03-2017.
 */

public class DrawerData {
    int mIconId;
    String mTitle;
    boolean isHeader;
    boolean isFooter;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isFooter() {
        return isFooter;
    }

    public void setFooter(boolean footer) {
        isFooter = footer;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int mIconId) {
        this.mIconId = mIconId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTiltle) {
        this.mTitle = mTiltle;
    }
}
