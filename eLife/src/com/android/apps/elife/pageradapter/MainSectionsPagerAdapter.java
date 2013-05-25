package com.android.apps.elife.pageradapter;

import java.util.Locale;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.apps.elife.R;
import com.android.apps.elife.ELife.DummySectionFragment;
import com.android.apps.elife.consts.PageConstants;

/**
 * Main sections pager adapter for the home class.
 * 
 * @author Sora
 *
 */
public class MainSectionsPagerAdapter extends FragmentPagerAdapter {

	/** Application context. */
	private final Context context;
	
	/**
	 * Calls the super.
	 * @param fm - {@link FragmentManager} - Fragment manager passes to the
	 * super.
	 * @param context - {@link Context} - Application context to get resources.
	 */
    public MainSectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        Fragment fragment = new DummySectionFragment();
        Bundle args = new Bundle();
        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case PageConstants.PAGE_1:
                return context.getString(R.string.title_section1);
            case PageConstants.PAGE_2:
                return context.getString(R.string.title_section2);
            case PageConstants.PAGE_3:
                return context.getString(R.string.title_section3);
        }
        return null;
    }

}
