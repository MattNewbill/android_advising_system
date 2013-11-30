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
/**
 * This class is used to put the requirements and courses into the expandable list.
 * The expandable list looks like this:
 * <p>
 * Requirement<br>
 * &nbsp;Course<br>
 * &nbsp;Course<br>
 * Requirement<br>
 * &nbsp;Course<br>
 * etc.
 * <p>
 * The courses are initially hidden until the requirement is clicked which exposes
 * the list of requirements.
 * 
 * @author Jeffrey Limbacher
 */
public class CourseExpandableListAdapter extends BaseExpandableListAdapter {
	
	
	//All the requirements to be in the list
	private ArrayList<Requirement> reqs;
	//The position of the ArrayList corresponds to the requirements that the arraylist is associated to
	private ArrayList<ArrayList<Course>> courses;
	//Used to expand the Views in the List.
	private LayoutInflater inflater;
	
	public CourseExpandableListAdapter(Context context, ArrayList<Requirement> requirements) {
		this.inflater = LayoutInflater.from(context);
		this.reqs = new ArrayList<Requirement>();
		courses = new ArrayList<ArrayList<Course>>();
		//Populate courses (the children of the requirements)
		//This implementation assumes unique courses for now.
		for(int i = 0; i < reqs.size(); i++){
			Requirement req = reqs.get(i);
			//Add requirements
			this.addRequirement(req);
		}
	}
	
	/**
	 * Adds a requirement to the list. The requirement should have all its subrequirements already.
	 * However, the method does not check if any subrequirements exist.
	 * <p>
	 * Will throw a NullPointerException if the Requirement is null but it will not throw an exception
	 * if any of the fields of the Requirement are null. Ignores nulls in the courses.
	 * 
	 * @param req A requirement which contains all its required courses.
	 */
	public void addRequirement(Requirement req) {
		int reqPosition = reqs.size();
		//Keep the number of ArrayList<Course>s in sync with Requirements.
		courses.add(new ArrayList<Course>());
		//Fail here if req is null
		if(req.getSubRequirements() != null){
			for(SubRequirement subReq : req.getSubRequirements()) {
				//The sub-requirements each contain different courses
				//Insert all the courses into the expandable lists under the requirements.
				ArrayList<Course> subReqCourses = subReq.getCourses();
				for(Course course : subReqCourses) {
					//Ignore null values.
					if(course != null) {
						courses.get(reqPosition).add(course);
					}
				}
			}
		}
		//Only add the requirement if the above was successful
		reqs.add(req);
	}
	
	/**
	 * Adds a course to the specified requirement.
	 * @param reqPos The position of the requirement.
	 * @param course The course to add.
	 */
	public void addCourse(int reqPos, Course course){
		ArrayList<Course> reqCourses = courses.get(reqPos);
		reqCourses.add(course);
	}
	
	/**
	 * Sets the LayoutInflater.
	 * @param mInflater The inflater to be set to.
	 */
	public void setInflater(LayoutInflater mInflater) {
		this.inflater = mInflater;
	}

	@Override
	/**
	 * Gets child for a particular Requirement. Will throw an OutOfBoundsException.
	 * 
	 * @param groupPosition The position of the Requirement.
	 * @param childPosition The position of the Course.
	 */
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
		//Retrieve the child requested
		ArrayList<Course> tempChild = courses.get(groupPosition);
		Course tempCourse = tempChild.get(childPosition);
		//Put the children in textViews
		if(convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
		}
		String courseName = tempCourse.getName();
		((TextView) convertView).setText(courseName);
		//Set as tag for click callback to retrieve the object
		convertView.setTag(tempCourse);
		convertView.setOnClickListener(new OnClickListener() {
		   @Override
		   //Listener just logs the name of the string to LogCat
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
		//Make convertView be a simple_list_item
		if (convertView == null) {
		   convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
		}
		((TextView) convertView).setText(reqs.get(groupPosition).getName());
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
