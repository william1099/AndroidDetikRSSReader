package william1099.com.design2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentList;
    ArrayList<String> tabTitle;

    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        tabTitle = new ArrayList<>();
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }

    public void addFragment(Fragment frag, String title) {
        this.fragmentList.add(frag);
        this.tabTitle.add(title);
    }
}
