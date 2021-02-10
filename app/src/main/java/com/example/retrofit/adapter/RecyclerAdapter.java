package com.example.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;
import com.example.retrofit.model.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context mContext;

    List<Model> imageList = new ArrayList<>();

    public RecyclerAdapter(Context mContext, List<Model> imageList) {
        this.mContext = mContext;
        this.imageList = imageList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model item = imageList.get(position);

        String authorName = item.getAuthor();
        int authorAge = item.getHeight();

        //take url from item
        String url = item.getDownloadUrl();
        Picasso.get().load(url).into(holder.imv_recycler_list);

        //set the author name
        holder.tv_author__recycler_list.setText(authorName);

        //set the authore age
        holder.tv_age__recycler_list.setText(String.valueOf(authorAge));


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{

        private ImageView imv_recycler_list;
        private TextView tv_author__recycler_list,tv_age__recycler_list;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imv_recycler_list = (ImageView) itemView.findViewById(R.id.recycler_imv);
            tv_author__recycler_list = (TextView) itemView.findViewById(R.id.recycler_tv_authore);
            tv_age__recycler_list = (TextView) itemView.findViewById(R.id.recycler_tv_age);
        }
    }
}
