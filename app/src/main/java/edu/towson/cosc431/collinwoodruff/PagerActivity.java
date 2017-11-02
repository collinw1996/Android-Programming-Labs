package edu.towson.cosc431.collinwoodruff;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

public class PagerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Person"));
        tabLayout.addTab(tabLayout.newTab().setText("Other"));
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() < 1)
            super.onBackPressed();
        else
            viewPager.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    class MyAdapter extends FragmentPagerAdapter {

        int tabCount;

        public MyAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new ContainerFragment();
                case 1:
                    return new HelloFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "People";
                case 1: return "Other";
            }
            return super.getPageTitle(position);
        }
    }
}
