package com.example.ivandimitrov.restorantmenu;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.ivandimitrov.restorantmenu.fragments.FoodFragment;
import com.example.ivandimitrov.restorantmenu.fragments.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodFragment.ScrollListener {
    public static final int SOUP_PAGE       = 0;
    public static final int MAIN_DISH_PAGE  = 1;
    public static final int DESSERT_PAGE    = 2;
    public static final int RESTAURANT_PAGE = 3;

    private Animation     mFadeOutAnimation;
    private Animation     mFadeInAnimation;
    private PagerTabStrip pagerTabStrip;
    private int mCurrentPageBackground = SOUP_PAGE;
    private ImageView mGradientEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = getFragments();
        final MenuPageAdapter pageAdapter = new MenuPageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header);
        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);

        mGradientEffect = (ImageView) findViewById(R.id.gradient_effect);
        pagerTabStrip.setBackgroundResource(R.drawable.soup_background);
        pagerTabStrip.setTextColor(Color.WHITE);
        mCurrentPageBackground = R.drawable.soup_background;
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(pageAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                switch (position) {
                    case SOUP_PAGE:
                        mCurrentPageBackground = R.drawable.soup_background;
                        break;
                    case MAIN_DISH_PAGE:
                        mCurrentPageBackground = R.drawable.main_dish_background;
                        break;
                    case DESSERT_PAGE:
                        mCurrentPageBackground = R.drawable.dessert_background;
                        break;
                    case RESTAURANT_PAGE:
                        mCurrentPageBackground = R.drawable.restaurant_background;
                        break;
                    default:
                        mCurrentPageBackground = 0;
                        break;
                }

                mFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        pagerTabStrip.startAnimation(mFadeInAnimation);
                        pagerTabStrip.setBackgroundResource(mCurrentPageBackground);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                pagerTabStrip.startAnimation(mFadeOutAnimation);
            }
        });
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(FoodFragment.newInstance(SOUP_PAGE, this));
        fList.add(FoodFragment.newInstance(MAIN_DISH_PAGE, this));
        fList.add(FoodFragment.newInstance(DESSERT_PAGE, this));
        fList.add(MapFragment.newInstance());
        return fList;
    }

    @Override
    public void onShow() {
        pagerTabStrip.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        mGradientEffect.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        pagerTabStrip.setVisibility(View.VISIBLE);
        mGradientEffect.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onHide() {
        pagerTabStrip.animate().translationY(-pagerTabStrip.getHeight()).withEndAction(new Runnable() {
            @Override
            public void run() {
                pagerTabStrip.setVisibility(View.GONE);
                mGradientEffect.setVisibility(View.GONE);
            }
        }).setInterpolator(new AccelerateInterpolator(2));
        mGradientEffect.animate().translationY(-mGradientEffect.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }
}
