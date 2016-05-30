package com.example.huangxiquan.designlib;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by huangxiquan on 16/5/26.
 */
public class MainActivity extends ActionBarActivity {

    private DrawerLayout mOutterView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOutterView = (DrawerLayout) findViewById(R.id.outter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black);
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationview);
        if(navigationView == null) {
            return;
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mOutterView.closeDrawers();
                return true;
            }
        });


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        if(tabLayout == null) {
            return;
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = null;
                switch (position){
                    case 0 :
                        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.viewpager_first,container,false);
                        final TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.textinputlayout);
                        EditText editText = textInputLayout.getEditText();
                        if(editText == null) {
                            return null;
                        }
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                if(s.length() >= 11) {
                                    textInputLayout.setError("手机号为11位");
                                }
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if(s.length() <= 11) {
                                    textInputLayout.setError("");//看源码得知这一步是必需的,不然达不到效果
                                    textInputLayout.setErrorEnabled(false);
                                }
                            }
                        });

                        FloatingActionButton floatButton = (FloatingActionButton) view.findViewById(R.id.floatbutton);
                        floatButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(mOutterView,"hello",Snackbar.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    default:
                        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.viewppager_default,container,false);
                        TextView textView = (TextView) view.findViewById(R.id.textview);
                        textView.setText("页面" + (position + 1));
                        break;

                }
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "TabTabTab";
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            mOutterView.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
