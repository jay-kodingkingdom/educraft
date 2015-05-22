package com.kodingkingdom.educraft.resources;

import java.util.HashMap;

import com.kodingkingdom.educraft.Student;

public class Task {

	HashMap<Student,TaskItem> studentTaskMap;

	public final TaskItem give(Student taskStudent){
		TaskItem taskItem = new TaskItem(taskStudent);
		studentTaskMap.put(taskStudent, taskItem);
		assignAction(taskItem);
		return taskItem;}

	public final TaskItem ungive(Student taskStudent){
		TaskItem taskItem = studentTaskMap.get(taskStudent);
		studentTaskMap.remove(taskStudent);
		unassignAction(taskItem);
		return taskItem;}
	
	public final TaskItem take(Student taskStudent){
		TaskItem taskItem = studentTaskMap.get(taskStudent);
		studentTaskMap.remove(taskStudent);
		markAction(taskItem);
		return taskItem;}
	
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
