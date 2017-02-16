package com.example.ivandimitrov.restorantmenu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
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


    private int     mCurrentPageBackground = SOUP_PAGE;
    private boolean isToolbarCollapsed     = false;

    private Animation mFadeOutAnimation;
    private Animation mFadeInAnimation;

    //=====LISTENERS=====
    private AppBarLayout.OnOffsetChangedListener mCollapseListener;
    private Palette.PaletteAsyncListener         mPaletteListener;
    private TabLayout.OnTabSelectedListener      mTabSelectedListener;
    private Animation.AnimationListener          mAnimationListener;
    private ViewPager                            viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCurrentPageBackground = R.drawable.soup_background;

        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        final AppBarLayout collapsedBar = (AppBarLayout) findViewById(R.id.htab_appbar);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        final List<Fragment> fragments = getFragments();
        final MenuPageAdapter pageAdapter = new MenuPageAdapter(getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setBackgroundResource(R.drawable.soup_background);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.soup_background);
        mPaletteListener = new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(Color.YELLOW);
                int vibrantDarkColor = palette.getDarkVibrantColor(Color.YELLOW);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        };
        Palette.from(bitmap).generate(mPaletteListener);

        mCollapseListener = new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    isToolbarCollapsed = false;
                } else {
                    isToolbarCollapsed = true;
                }
            }
        };
        collapsedBar.addOnOffsetChangedListener(mCollapseListener);
        mTabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
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
                }
                mAnimationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        collapsingToolbarLayout.startAnimation(mFadeInAnimation);
                        collapsingToolbarLayout.setBackgroundResource(mCurrentPageBackground);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                mFadeOutAnimation.setAnimationListener(mAnimationListener);

                if (!isToolbarCollapsed) {
                    collapsingToolbarLayout.startAnimation(mFadeOutAnimation);
                } else {
                    collapsingToolbarLayout.setBackgroundResource(mCurrentPageBackground);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
        tabLayout.setOnTabSelectedListener(mTabSelectedListener);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(pageAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCollapseListener = null;
        mPaletteListener = null;
        mTabSelectedListener = null;
        mAnimationListener = null;
        viewPager = null;
        mFadeOutAnimation = null;
        mFadeInAnimation = null;

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
