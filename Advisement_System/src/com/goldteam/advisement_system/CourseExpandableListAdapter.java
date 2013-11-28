package com.goldteam.advisement_system;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class CourseExpandableListAdapter extends BaseExpandableListAdapter {
	
	private ArrayList<Requirement> reqs;
	//The position of the ArrayList corresponds to the requirements that the arraylist is associated to
	private ArrayList<ArrayList<Course>> courses; 
	private Course tempCourse;
	private Context con;
	private LayoutInflater inflater;
	private Activity activity;
	
	public CourseExpandableListAdapter(Context context, ArrayList<Requirement> requirements) {
		this.con = context;
		this.inflater = LayoutInflater.from(context);
		this.reqs = requirements;
		courses = new ArrayList<ArrayList<Course>>();
		//Populate courses (the children of the requirements)
		//This implementation assumes unique courses for now.
		for(int i = 0; i < reqs.size(); i++){
			Requirement req = reqs.get(i);
			courses.add(new ArrayList<Course>());
			for(SubRequirement subReq : req.getSubRequirements()) {
				ArrayList<Course> subReqCourses = subReq.getCourses();
				courses.get(i).addAll(subReqCourses);
			}
		}
	}
	
	public void addRequirement(Requirement req) {
		reqs.add(req);
		int reqPosition = reqs.size() - 1;
		courses.add(new ArrayList<Course>());
		for(SubRequirement subReq : req.getSubRequirements()) {
			ArrayList<Course> subReqCourses = subReq.getCourses();
			courses.get(reqPosition).addAll(subReqCourses);
		}
	}
	
	public void addCourse(int reqPos, Course course){
		ArrayList<Course> reqCourses = courses.get(reqPos);
		reqCourses.add(course);
	}
	
	public void setInflater(LayoutInflater mInflater, Activity act) {
		this.inflater = mInflater;
		activity = act;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<Course> reqCourses = courses.get(groupPosition);
		Course gotCourse = reqCourses.get(childPosition);
		return gotCourse;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Log.d("CourseExpandableListAdapater", "getChildView");
		Log.d("CourseExpandableListAdapter", "childPosition: " + String.valueOf(childPosition));
		Log.d("CourseExpandableListAdapter", "Children length: " + String.valueOf(courses.size()));
		ArrayList<Course> tempChild = courses.get(groupPosition);
		Log.d("CourseExpandableListAdapter", "tempChild size: " + String.valueOf(tempChild.size()));
		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
		}
		Log.d("CourseExpandableAdapter", "covertView class is " + convertView.getClass().toString());
		//Put the children in textViews.
		View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
		Log.d("CourseExpandableListAdapter", "Class is " + view.getClass().toString());
		tempCourse = tempChild.get(childPosition);
		Log.d("CourseExpandableListAdapter", "Checkpoint");
		String courseName = tempCourse.getName();
		((TextView)convertView).setText(courseName);
		convertView.setTag(tempCourse);
		convertView.setOnClickListener(new OnClickListener() {
		   @Override
		public void onClick(View v) {
			   Course course = (Course) v.getTag();
			   Log.d("CourseExpandableListAdapter", course.toString());
		   }
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		ArrayList<Course> reqCourses = courses.get(groupPosition);
		return reqCourses.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return reqs.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return reqs.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Log.d("CourseExpandableListAdapater", "getGroupView");
		if (convertView == null) {
		   convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
		}
		((TextView) convertView).setText(reqs.get(groupPosition).getName());
		//((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
