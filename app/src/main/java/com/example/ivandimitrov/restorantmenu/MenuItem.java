package com.example.ivandimitrov.restorantmenu;

import android.graphics.Bitmap;

/**
 * Created by Ivan Dimitrov on 2/13/2017.
 */

public class MenuItem {
    private Bitmap mItemImage;
    private String mName;
    private String mDescription;
    private String mVotesCount;
    private String mCommentsCount;
    private String mTagsCount;
    private int    mRating;

    MenuItem(Bitmap itemImage, String name, String description, String votes, String comments, String tags, int rating) {
        mItemImage = itemImage;
        mName = name;
        mDescription = description;
        mVotesCount = votes;
        mCommentsCount = comments;
        mTagsCount = tags;
        mRating = rating;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Bitmap getItemImage() {
        return mItemImage;
    }

    public void setItemImage(Bitmap itemImage) {
        mItemImage = itemImage;
    }

    public String getCommentsCount() {
        return mCommentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        mCommentsCount = commentsCount;
    }

    public String getTagsCount() {
        return mTagsCount;
    }

    public void setTagsCount(String tagsCount) {
        mTagsCount = tagsCount;
    }

    public String getVotesCount() {
        return mVotesCount;
    }

    public void setVotesCount(String votesCount) {
        mVotesCount = votesCount;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
    }
}
