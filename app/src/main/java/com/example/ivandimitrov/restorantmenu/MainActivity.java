package com.example.ivandimitrov.restorantmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.ivandimitrov.restorantmenu.fragments.FoodFragment;
import com.example.ivandimitrov.restorantmenu.fragments.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int SOUP_PAGE       = 0;
    public static final int MAIN_DISH_PAGE  = 1;
    public static final int DESSERT_PAGE    = 2;
    public static final int RESTAURANT_PAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = getFragments();
        final MenuPageAdapter pageAdapter = new MenuPageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        final PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header);
        final Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);
        final Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);

        pagerTabStrip.setBackgroundResource(R.drawable.soup_background);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(pageAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                final int id;
                switch (position) {
                    case SOUP_PAGE:
                        id = R.drawable.soup_background;
                        break;
                    case MAIN_DISH_PAGE:
                        id = R.drawable.main_dish_background;
                        break;
                    case DESSERT_PAGE:
                        id = R.drawable.dessert_background;
                        break;
                    case RESTAURANT_PAGE:
                        id = R.drawable.restaurant_background;
                        break;
                    default:
                        id = 0;
                        break;
                }

                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        pagerTabStrip.startAnimation(fadeInAnimation);
                        pagerTabStrip.setBackgroundResource(id);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                pagerTabStrip.startAnimation(fadeOutAnimation);
            }
        });
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(FoodFragment.newInstance(SOUP_PAGE));
        fList.add(FoodFragment.newInstance(MAIN_DISH_PAGE));
        fList.add(FoodFragment.newInstance(DESSERT_PAGE));
        fList.add(MapFragment.newInstance());
        return fList;
    }
}
