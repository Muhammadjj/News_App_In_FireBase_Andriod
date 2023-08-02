package org.pk.recyclerview_fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.pk.cas.newsappwithfirebase.Click_Detail_Activity;
import org.pk.cas.newsappwithfirebase.R;


public class Recycler_View_Adapter extends FirebaseRecyclerAdapter<Model_Class_Fragment, Recycler_View_Adapter.ViewHolder> {

    public Recycler_View_Adapter(@NonNull FirebaseRecyclerOptions<Model_Class_Fragment> options) {
        super(options);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_view_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model_Class_Fragment model) {
        holder.news_Name.setText(model.getName());

        holder.AnimateWallpaper(holder.itemView);  // Animation all itemView;
        Glide.with(holder.newsImages.getContext())
                .load(model.getImage())
                .into(holder.newsImages);


        holder.newsImages.setOnClickListener(v -> {
            Intent intent = new Intent(holder.newsImages.getContext(), Click_Detail_Activity.class);
            intent.putExtra("Link",model.getLink());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            holder.newsImages.getContext().startActivity(intent);
        });
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImages;
        TextView news_Name, newsLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsImages = itemView.findViewById(R.id.News_App_Images);
            news_Name = itemView.findViewById(R.id.News_App_Name);
            newsLink = itemView.findViewById(R.id.News_App_Link);
        }

        private void AnimateWallpaper(View viewtoAnimate){
            Animation slide_in_left = AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.slide_in_left);
            viewtoAnimate.startAnimation(slide_in_left);
        }
    }

}
