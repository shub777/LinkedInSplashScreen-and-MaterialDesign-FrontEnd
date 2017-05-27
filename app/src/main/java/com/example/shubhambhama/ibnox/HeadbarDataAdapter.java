package com.example.shubhambhama.ibnox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHUB-PC on 25-03-2017.
 */

public class HeadbarDataAdapter extends RecyclerView.Adapter<HeadbarDataAdapter.HeaderbarViewHolder> {

    private List<HeadbarData> headbarData=new ArrayList<>();
    Context context;

    public HeadbarDataAdapter(Context context,List<HeadbarData> headbarData){
        this.headbarData=headbarData;
        this.context=context;
    }

    @Override
    public HeaderbarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_headbar,parent,false);
        return new HeaderbarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeaderbarViewHolder holder, int position) {
        holder.hArticleId.setImageResource(headbarData.get(position).gethArticleId());
        holder.hIconId.setImageResource(headbarData.get(position).gethIconid());
        holder.hArticle.setText(headbarData.get(position).gethArticle());
        holder.hTitle.setText(headbarData.get(position).gethTitle());
        holder.hUpdatetime.setText(headbarData.get(position).gethUpdatetime());
        holder.hReadtime.setText(headbarData.get(position).gethReadtime());
        holder.hWritername.setText(headbarData.get(position).gethWritername());
    }

    @Override
    public int getItemCount() {
        return headbarData.size();
    }

    class HeaderbarViewHolder extends RecyclerView.ViewHolder
    {
        ImageView hArticleId,hIconId,hFacebookId,hTwitterId,hGoogleId;
        TextView hTitle,hReadtime,hUpdatetime,hArticle,hWritername;

        public HeaderbarViewHolder(View itemView) {
            super(itemView);
            hArticle=(TextView) itemView.findViewById(R.id.headbar_article);
            hTitle=(TextView) itemView.findViewById(R.id.headbar_title);
            hReadtime=(TextView) itemView.findViewById(R.id.headbar_readtime);
            hUpdatetime=(TextView) itemView.findViewById(R.id.headbar_updatetime);
            hWritername=(TextView) itemView.findViewById(R.id.headbar_writername);
            hArticleId=(ImageView) itemView.findViewById(R.id.headbar_headbaruserimage);
            hIconId=(ImageView) itemView.findViewById(R.id.headbar_image);
//            hFacebookId=(ImageView) itemView.findViewById(R.id.headbar_facebook);
//            hTwitterId=(ImageView) itemView.findViewById(R.id.headbar_twitter);
//            hGoogleId=(ImageView) itemView.findViewById(R.id.headbar_google);
        }
    }
}
