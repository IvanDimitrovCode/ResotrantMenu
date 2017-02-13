package com.example.ivandimitrov.restorantmenu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Dimitrov on 2/13/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private List<MenuItem> mMenuItems;

    public MenuAdapter(List<MenuItem> menuItems) {
        mMenuItems = menuItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MenuItem menuItem = mMenuItems.get(position);
        holder.mItemImage.setImageBitmap(menuItem.getItemImage());
        holder.mName.setText(menuItem.getName());
        holder.mDescription.setText(menuItem.getDescription());
        holder.mComments.setText(menuItem.getCommentsCount());
        holder.mTags.setText(menuItem.getTagsCount());
        holder.mVotes.setText(menuItem.getVotesCount());
        holder.mComments.setText(menuItem.getCommentsCount());
        for (int i = 0; i < holder.mImageStars.size(); i++) {
            if (menuItem.getRating() - 1 < i) {
                holder.mImageStars.get(i).setImageResource(R.drawable.empty_star_icon);
            } else {
                holder.mImageStars.get(i).setImageResource(R.drawable.full_star_icon);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMenuItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<ImageView> mImageStars = new ArrayList<>();
        private ImageView mItemImage;
        private TextView  mName;
        private TextView  mDescription;
        private TextView  mVotes;
        private TextView  mComments;
        private TextView  mTags;

        public MyViewHolder(View view) {
            super(view);
            mImageStars.add((ImageView) view.findViewById(R.id.star_1));
            mImageStars.add((ImageView) view.findViewById(R.id.star_2));
            mImageStars.add((ImageView) view.findViewById(R.id.star_3));
            mImageStars.add((ImageView) view.findViewById(R.id.star_4));
            mImageStars.add((ImageView) view.findViewById(R.id.star_5));

            mItemImage = (ImageView) view.findViewById(R.id.image_dish);
            mName = (TextView) view.findViewById(R.id.dish_name);
            mDescription = (TextView) view.findViewById(R.id.dish_description);
            mVotes = (TextView) view.findViewById(R.id.vote_count);
            mComments = (TextView) view.findViewById(R.id.comment_count);
            mTags = (TextView) view.findViewById(R.id.tag_count);
        }
    }
}
