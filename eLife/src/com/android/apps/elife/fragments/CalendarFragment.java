package com.android.apps.elife.fragments;

import com.android.apps.elife.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Calendar fragment located left of the home page.
 * @author Sora
 *
 */
public class CalendarFragment extends Fragment {
	
	/**
	 * Constructor does nothing.
	 */
	public CalendarFragment() {	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_loggedin_calendar, container, false);
		//TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
		//dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		return rootView;
	}

}
