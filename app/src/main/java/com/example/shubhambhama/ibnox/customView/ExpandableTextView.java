package com.example.shubhambhama.ibnox.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.shubhambhama.ibnox.R;

/**
 * Created by SHUB-PC on 30-03-2017.
 */

public class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {

    private int trimLength=80;
    private static final int DEFAULT_TRIM_LENGTH=200;
    private CharSequence originalText;
    private CharSequence trimmedText;
    private boolean trim=true;
    private BufferType bufferType;
    private Context context;
    private String ELLIPSE_MORE=" .....more ";
    private String ELLIPSE_LESS=" .....LESS ";

    public ExpandableTextView(Context context){
        this(context,null);
    }

    public ExpandableTextView (Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        this.context=context;
        TypedArray typedArray=context.obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView);
        typedArray.getInt(R.styleable.ExpandableTextView_trimLength,DEFAULT_TRIM_LENGTH);
        typedArray.recycle();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim=!trim;
                setText();
            }
        });
    }

    private void setText(){
        super.setText(getDisplableText(),bufferType);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        originalText=text;
        trimmedText=getTrimmedText(text);
        bufferType=type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText !=null && originalText.length()>trimLength){
            SpannableStringBuilder spannableStringBuilder= new SpannableStringBuilder(originalText , 0, trimLength+1).append(ELLIPSE_MORE);
            spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),81,91,0);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.color_textview)),81,91,0);
            return spannableStringBuilder;
        }
        else {
            return originalText;
        }
    }

    public CharSequence getDisplableText() {
        return trim ? trimmedText : originalText;
    }

    public int getTrimLength() {
        return trimLength;
    }

    public CharSequence getOriginalText() {
        return originalText;
    }
    private void setTrimLength(int trimLength){
        this.trimLength=trimLength;
        trimmedText=getTrimmedText(originalText);
        setText();
    }

}
