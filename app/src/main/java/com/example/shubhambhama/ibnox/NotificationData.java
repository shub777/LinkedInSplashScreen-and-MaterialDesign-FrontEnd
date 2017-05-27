package com.example.shubhambhama.ibnox;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SHUB-PC on 22-03-2017.
 */

public class NotificationData {
    private int nImageView;
    private String nTextView;
    private String nTimeTextView;

    public int getImageView() {
        return nImageView;
    }

    public void setImageView(int nImageView) {
        this.nImageView = nImageView;
    }

    public String getTextView() {
        return nTextView;
    }

    public void setTextView(String nTextView) {
        this.nTextView = nTextView;
    }

    public String getTimeTextView() {
        return nTimeTextView;
    }

    public void setTimeTextView(String nTimeTextView) {
        this.nTimeTextView = nTimeTextView;
    }
}
