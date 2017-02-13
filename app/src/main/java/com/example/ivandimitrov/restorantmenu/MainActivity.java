package com.example.ivandimitrov.restorantmenu;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MenuItem> mMenuItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MenuAdapter  mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenuList();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mMenuAdapter = new MenuAdapter(mMenuItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMenuAdapter);
    }

    private void initMenuList() {
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup)
                , "CreamSoup", "Traditional soup from a special recipe"
                , "98", "16", "20", 3));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup2)
                , "CreamSoup 2", "Traditional soup from a not so special recipe"
                , "45", "12", "18", 2));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5));
    }
}
