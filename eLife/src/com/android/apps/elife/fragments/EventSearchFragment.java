package com.android.apps.elife.fragments;

import com.android.apps.elife.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventSearchFragment extends Fragment {

	/**
	 * Constructor does nothing.
	 */
	public EventSearchFragment() {	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_event_search, container, false);
		//TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
		//dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		return rootView;
	}
	
}
