package com.example.ivandimitrov.restorantmenu.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivandimitrov.restorantmenu.MainActivity;
import com.example.ivandimitrov.restorantmenu.MenuAdapter;
import com.example.ivandimitrov.restorantmenu.MenuItem;
import com.example.ivandimitrov.restorantmenu.R;
import com.example.ivandimitrov.restorantmenu.RestaurantNode;

import java.util.ArrayList;

/**
 * Created by Ivan Dimitrov on 2/13/2017.
 */

public class FoodFragment extends Fragment {
    public static final String BUNDLE_KEY_MESSAGE_TYPE = "pageType";

    private ArrayList<MenuItem> mMenuItems = new ArrayList<>();
    RecyclerView mRecyclerView;
    MenuAdapter  mMenuAdapter;

    public static final Fragment newInstance(int message) {
        FoodFragment fragment = new FoodFragment();
        Bundle bdl = new Bundle();
        bdl.putInt(BUNDLE_KEY_MESSAGE_TYPE, message);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.soup_fragment, container, false);

        int pageType = getArguments().getInt(BUNDLE_KEY_MESSAGE_TYPE);
        switch (pageType) {
            case MainActivity.SOUP_PAGE:
                initMenuSoup();
                break;
            case MainActivity.MAIN_DISH_PAGE:
                initMenuMainDish();
                break;
            case MainActivity.DESSERT_PAGE:
                initMenuDesserts();
                break;
            default:
                break;
        }
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mMenuAdapter = new MenuAdapter(getActivity(), mMenuItems);

        if (getResources().getBoolean(R.bool.isTablet)) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMenuAdapter);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mRecyclerView = null;
        mMenuAdapter = null;
        mMenuItems = null;
    }

    private void initMenuSoup() {
        RestaurantNode restaurant = new RestaurantNode("Supa Star", 42.692513, 23.330136, 5);
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup)
                , "CreamSoup", "Traditional soup from a special recipe"
                , "98", "16", "20", 3, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup2)
                , "CreamSoup 2", "Traditional soup from a not so special recipe"
                , "45", "12", "18", 0, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5, restaurant));
    }

    private void initMenuMainDish() {
        RestaurantNode restaurant = new RestaurantNode("Niagara", 42.678944, 23.291101, 4);
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.spageti)
                , "Spageti", "Spaget Boloneze"
                , "98", "16", "20", 1, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.lazania)
                , "Lazania", "Lazania just like Garfield likes it"
                , "45", "12", "18", 2, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.rice)
                , "Rice", "Fresh rice gathered from China"
                , "178", "15", "33", 5, restaurant));
    }

    private void initMenuDesserts() {
        RestaurantNode restaurant = new RestaurantNode("Bordo", 42.679807, 23.239824, 3);

        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.icecream)
                , "IceCream", "As cold as ice"
                , "98", "16", "20", 3, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.brownies)
                , "Brownies 2", "They are not the \"Special\" brownies"
                , "45", "12", "18", 4, restaurant));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cake)
                , "Cake", "Its like diebiteies in a plate"
                , "178", "15", "33", 5, restaurant));
    }
}