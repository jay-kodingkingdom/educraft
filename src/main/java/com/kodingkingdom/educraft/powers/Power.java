package com.kodingkingdom.educraft.powers;

import java.util.HashMap;

import com.kodingkingdom.educraft.group.Student;

public class Power {

	protected HashMap<Student,PowerItem> studentPowerMap;

	public final void give(Student powerStudent){
		PowerItem powerItem = new PowerItem(powerStudent);
		studentPowerMap.put(powerStudent, powerItem);
		doAction(powerItem);}
	public final void take(Student powerStudent){
		PowerItem powerItem = new PowerItem(powerStudent);
		studentPowerMap.remove(powerStudent);
		undoAction(powerItem);}
	
	protected void doAction(PowerItem lockItem){}
	protected void undoAction(PowerItem lockItem){}
	
	public class PowerItem{
		Student powerStudent;
		
		PowerItem(Student PowerStudent){
			powerStudent=PowerStudent;}
		
		public Student getStudent(){
			return powerStudent;}
		public Power getpower(){
			return Power.this;}}}
