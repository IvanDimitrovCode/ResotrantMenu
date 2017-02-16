package com.example.ivandimitrov.restorantmenu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
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
    public static final String BUNDLE_KEY_LAT   = "Lat";
    public static final String BUNDLE_KEY_LNG   = "Lng";
    public static final String BUNDLE_KEY_TITLE = "Title";
    public static final String BUNDLE_KEY_STARS = "Stars";

    private List<MenuItem> mMenuItems;
    private Activity       mActivity;

    public MenuAdapter(Activity activity, List<MenuItem> menuItems) {
        mMenuItems = menuItems;
        mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MenuItem menuItem = mMenuItems.get(position);
        holder.mItemImage.setImageBitmap(menuItem.getItemImage());
        holder.mName.setText(menuItem.getName());
        holder.mDescription.setText(menuItem.getDescription());
        holder.mComments.setText(menuItem.getCommentsCount());
        holder.mTags.setText(menuItem.getTagsCount());
        holder.mVotes.setText(menuItem.getVotesCount());
        holder.mComments.setText(menuItem.getCommentsCount());
        holder.mRestaurant.setText(menuItem.getRestaurant().getName());
        holder.mRestaurant.setMovementMethod(LinkMovementMethod.getInstance());
        holder.mRestaurant.setPaintFlags(holder.mRestaurant.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        holder.mRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MapsActivity.class);
                intent.putExtra(BUNDLE_KEY_LAT, menuItem.getRestaurant().getLat());
                intent.putExtra(BUNDLE_KEY_LNG, menuItem.getRestaurant().getLng());
                intent.putExtra(BUNDLE_KEY_TITLE, menuItem.getRestaurant().getName());
                intent.putExtra(BUNDLE_KEY_STARS, menuItem.getRestaurant().getStars());
                mActivity.startActivity(intent);
            }
        });
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<ImageView> mImageStars = new ArrayList<>();
        private ImageView mItemImage;
        private TextView  mName;
        private TextView  mDescription;
        private TextView  mVotes;
        private TextView  mComments;
        private TextView  mTags;
        private TextView  mRestaurant;

        MyViewHolder(View view) {
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
            mRestaurant = (TextView) view.findViewById(R.id.restaurant);
        }
    }
}
