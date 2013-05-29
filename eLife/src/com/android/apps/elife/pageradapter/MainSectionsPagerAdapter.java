package com.android.apps.elife.pageradapter;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.android.apps.elife.R;
import com.android.apps.elife.ELife.DummySectionFragment;
import com.android.apps.elife.consts.PageConstants;
import com.android.apps.elife.fragments.CalendarFragment;
import com.android.apps.elife.fragments.EventSearchFragment;

/**
 * Main sections pager adapter for the home class.
 * 
 * @author Sora
 *
 */
public class MainSectionsPagerAdapter extends FragmentPagerAdapter {

	/** Application context. */
	private static Context context;
	
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
        switch (position) {
	        case PageConstants.PAGE_1:
	        	Fragment fragment1 = new CalendarFragment();
	            return fragment1;
	        case PageConstants.PAGE_2:
	        	Fragment fragment2 = new EventSearchFragment();
	            return fragment2;
	        case PageConstants.PAGE_3:
	        	Fragment fragment3 = new DummySectionFragment();
	            Bundle args3 = new Bundle();
	            args3.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
	            fragment3.setArguments(args3);
	            return fragment3;
	    }
        
        return null;
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
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    	super.destroyItem(container, position, object);
    }

}
