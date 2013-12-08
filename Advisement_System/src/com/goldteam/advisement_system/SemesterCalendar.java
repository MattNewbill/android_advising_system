package com.goldteam.advisement_system;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class SemesterCalendar extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.semester_calendar);
		
		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.loadUrl("http://www.csun.edu/anr/soc/calendar.html");
	}
	
}
