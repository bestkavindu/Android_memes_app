package com.sk.kavindu.meme2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class meme_adapter extends RecyclerView.Adapter <meme_adapter.memeviewholder>{
    private Context mcontext;
    private ArrayList<meme_item> mitemlist;

    public meme_adapter(Context context,ArrayList<meme_item> memelist ){
        mcontext= context;
        mitemlist=memelist;
    }

    @NonNull
    @Override
    public memeviewholder onCreateViewHolder(@NonNull ViewGroup parebt , int i) {
        View v= LayoutInflater.from(mcontext).inflate(R.layout.meme_item,parebt,false );
        return new memeviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull memeviewholder memeviewholder, int ii) {
        meme_item currectitem=mitemlist.get(ii);
        String imageurl= currectitem.getImageUrl();
    // String title=currectitem.gettitle();
      // memeviewholder.textView.setText(title);
        Picasso.with(mcontext).load(imageurl).into(memeviewholder.imageView);

    }

    @Override
    public int getItemCount() {
        return mitemlist.size();
    }

    public class memeviewholder extends RecyclerView.ViewHolder{
        public ImageView imageView;
     // public TextView textView;

        public memeviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);


        }
    }
}
