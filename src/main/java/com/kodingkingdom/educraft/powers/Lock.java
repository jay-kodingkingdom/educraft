package com.kodingkingdom.educraft.powers;

import java.util.HashMap;

import com.kodingkingdom.educraft.group.users.Student;

public class Lock {

	protected HashMap<Student,LockItem> studentLockMap;

	public final void give(Student... lockStudents){
		for (Student lockStudent : lockStudents){
			LockItem lockItem = new LockItem(lockStudent);
			studentLockMap.put(lockStudent, lockItem);
			lockAction(lockItem);}}
	public final void take(Student... lockStudents){
		for (Student lockStudent : lockStudents){
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
			return Lock.this;}}}
