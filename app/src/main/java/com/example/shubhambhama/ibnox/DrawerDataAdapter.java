package com.example.shubhambhama.ibnox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHUBHAMBHAMA on 13-03-2017.
 */

public class DrawerDataAdapter extends RecyclerView.Adapter<DrawerDataAdapter.DrawerViewHolder> {

    boolean isHeader = false;
    boolean isFooter = false;
    private LayoutInflater inflater;
//    private ClickListener clickListener;
    private List<DrawerData> data = new ArrayList<>();
    private Context context;

    public DrawerDataAdapter(Context context, List<DrawerData> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        Log.d("TAG", "onCreateViewHolder");
        switch (viewType) {
            case VIEW_TYPE.normal:
                isFooter = false;
                isHeader = false;
                view = inflater.inflate(R.layout.custom_drawer_row, parent, false);
                break;
            case VIEW_TYPE.footer:
                isFooter = true;
                view = inflater.inflate(R.layout.footer, parent, false);
                break;
            case VIEW_TYPE.header:
                isHeader = true;
                view = inflater.inflate(R.layout.header, parent, false);
                break;
            default:
                isFooter = false;
                isHeader = false;
                view = inflater.inflate(R.layout.custom_drawer_row, parent, false);
        }
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder");
        if (!isHeader && !isFooter) {
            holder.mTitle.setText(data.get(position).getTitle());
            holder.mIcon.setImageResource(data.get(position).getIconId());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("TAG", "getItemViewType " + position + " " + isFooter + " " + isHeader);
        if (data.get(position).isFooter())
            return VIEW_TYPE.footer;
        else if (data.get(position).isHeader())
            return VIEW_TYPE.header;
        else
            return VIEW_TYPE.normal;
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        ImageView mIcon;
        ImageView hIcon;
        TextView fPrivacy, fTerms, fCredits;


        public DrawerViewHolder(View itemView) {
            super(itemView);
            if (!isHeader && !isFooter) {
                mTitle = (TextView) itemView.findViewById(R.id.titleId);
                mIcon = (ImageView) itemView.findViewById(R.id.iconId);
            } else if (isHeader && !isFooter) {
                hIcon = (ImageView) itemView.findViewById(R.id.header_image);
            } else if (isFooter && !isHeader) {
                fPrivacy = (TextView) itemView.findViewById(R.id.footer_privacy);
                fTerms = (TextView) itemView.findViewById(R.id.footer_terms);
                fCredits = (TextView) itemView.findViewById(R.id.footer_credits);
            }
        }
    }

    class VIEW_TYPE {
        public static final int header = 1;
        public static final int normal = 2;
        public static final int footer = 3;
    }
}
