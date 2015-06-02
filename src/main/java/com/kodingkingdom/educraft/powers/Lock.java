package com.kodingkingdom.educraft.powers;

import java.util.HashMap;
import java.util.HashSet;

import com.kodingkingdom.educraft.group.users.Student;

public class Lock implements Comparable<Lock>{

	protected HashMap<Student,LockItem> studentLockMap=new HashMap<Student,LockItem>();

	public String getName(){
		return "Lock";}
	
	public HashSet<Student> getStudents(){
		return new HashSet<Student> (studentLockMap.keySet());}
	
	public final void give(Student... lockStudents){
		for (Student lockStudent : lockStudents){
			LockItem lockItem = new LockItem(lockStudent);
			studentLockMap.put(lockStudent, lockItem);
			lockAction(lockItem);}}
	public final void take(Student... students){
		for (Student lockStudent : students){
			LockItem lockItem = studentLockMap.get(lockStudent);
			studentLockMap.remove(lockStudent);
			unlockAction(lockItem);}}
	
	protected void lockAction(LockItem lockItem){}
	protected void unlockAction(LockItem lockItem){}
	
	public class LockItem{
		Student lockStudent;
		
		LockItem(Student LockStudent){
			lockStudent=LockStudent;}
		
		public Student getStudent(){
			return lockStudent;}
		public Lock getLock(){
			return Lock.this;}}

	public int compareTo(Lock o) {
		return getName().compareTo(o.getName());}}
