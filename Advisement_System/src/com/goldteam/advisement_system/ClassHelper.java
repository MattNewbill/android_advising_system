package com.goldteam.advisement_system;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClassHelper extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_helper);
		ListView majorListView = (ListView)findViewById(R.id.listView1);
		//Get the list of all possible majors from the DB
		ArrayList<Major> majors = getMajors();
		//Add the majors to the list view
		ArrayAdapter<Major> adp = new ArrayAdapter<Major>(this, android.R.layout.simple_list_item_1, majors);
		majorListView.setAdapter(adp);
		

	}
	
	private ArrayList<Major> getMajors(){
		//Just return some hard coded majors for now
		//Query the database to get all the available majors
		ArrayList<Major> majors = new ArrayList<Major>();
		majors.add(new Major(0, "Computer Science", 123, new ArrayList<Requirement>()));
		majors.add(new Major(1, "Math", 123, new ArrayList<Requirement>()));
		majors.add(new Major(2, "Underwater Basket Weaving", 123, new ArrayList<Requirement>()));
		majors.add(new Major(3, "Useless Liberal Arts Degree", 123, new ArrayList<Requirement>()));
		return majors;
		
	}
	
	public void onListItemClick(ListView l, View v, int position, long id){
		
	}
}
