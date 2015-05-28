package com.kodingkingdom.educraft.powers;

import java.util.HashMap;

import com.kodingkingdom.educraft.group.users.Student;

public class Task {

	HashMap<Student,TaskItem> studentTaskMap;

	public final void give(Student... taskStudents){
		for (Student taskStudent : taskStudents){
			TaskItem taskItem = new TaskItem(taskStudent);
			studentTaskMap.put(taskStudent, taskItem);
			assignAction(taskItem);}}

	public final void ungive(Student... taskStudents){
		for (Student taskStudent : taskStudents){
			TaskItem taskItem = studentTaskMap.get(taskStudent);
			studentTaskMap.remove(taskStudent);
			unassignAction(taskItem);}}
	
	public final void take(Student... taskStudents){
		for (Student taskStudent : taskStudents){
			TaskItem taskItem = studentTaskMap.get(taskStudent);
			studentTaskMap.remove(taskStudent);
			markAction(taskItem);}}
	
	protected void assignAction(TaskItem taskItem){}
	protected void unassignAction(TaskItem taskItem){}
	protected void markAction(TaskItem taskItem){}
	
	public class TaskItem{
		Student taskStudent;
		
		TaskItem(Student TaskStudent){
			taskStudent=TaskStudent;}
		
		public Student getStudent(){
			return taskStudent;}
		public Task getTask(){
			return Task.this;}}}
