package com.example.ivandimitrov.restorantmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ivandimitrov.restorantmenu.fragments.FoodFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = getFragments();
        MenuPageAdapter pageAdapter = new MenuPageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        final PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header);

        pagerTabStrip.setBackgroundResource(R.drawable.soup_background);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(pageAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                switch (position) {
                    case FoodFragment.SOUP_PAGE:
                        pagerTabStrip.setBackgroundResource(R.drawable.soup_background);
                        break;
                    case FoodFragment.MAIN_DISH_PAGE:
                        pagerTabStrip.setBackgroundResource(R.drawable.main_dish_background);
                        break;
                    case FoodFragment.DESSERT_PAGE:
                        pagerTabStrip.setBackgroundResource(R.drawable.dessert_background);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(FoodFragment.newInstance(FoodFragment.SOUP_PAGE));
        fList.add(FoodFragment.newInstance(FoodFragment.MAIN_DISH_PAGE));
        fList.add(FoodFragment.newInstance(FoodFragment.DESSERT_PAGE));
        return fList;
    }
}
