package com.summer.record.ui.pictures.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.summer.record.R;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.de.PicturesDE;
import com.summer.record.ui.pictures.home.TimeDecoration;
import com.summer.x.GlideApp;
import com.summer.x.base.i.OnProgressI;

import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TestBAct extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_picture_home);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new GridLayoutManager(this, NetConstant.SPANCOUNT));
        PicturesDE picturesDE = new PicturesDE();
        ArrayList<PictureB> picturesDES = new ArrayList<>();
        recyclerView.addItemDecoration(new TimeDecoration(this,picturesDES,NetConstant.SPANCOUNT));
        picturesDE.getDataFromDBByTime(this, new Long[]{0l, System.currentTimeMillis()}, new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case END:
                        picturesDES.addAll((Collection<? extends PictureB>) data);
                        recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
                            @NonNull
                            @Override
                            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                return new MyViewHolder(LayoutInflater.from(TestBAct.this).inflate(R.layout.item_image_image,parent,false));
                            }

                            @Override
                            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                                GlideApp.with(TestBAct.this).asBitmap().centerCrop().load(R.color.black).into(holder.imageView);
//                                if(picturesDES.get(position).getLocpath()!=null){
//                                    GlideApp.with(TestBAct.this).asBitmap().centerCrop().load(picturesDES.get(position).getLocpath()).into(holder.imageView);
//                                }else{
//                                    GlideApp.with(TestBAct.this).asBitmap().centerCrop().load(R.color.white).into(holder.imageView);
//                                }
                            }

                            @Override
                            public int getItemCount() {
                                return picturesDES.size();
                            }
                        });
                        break;
                }
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.iv_video);
        }
    }
}
