package com.kodingkingdom.educraft.resources;

import java.util.HashMap;

import com.kodingkingdom.educraft.Student;

public class Lock {

	HashMap<Student,LockItem> studentLockMap;

	public final void lock(Student lockStudent){
		LockItem lockItem = new LockItem(lockStudent);
		studentLockMap.put(lockStudent, lockItem);
		lockAction(lockItem);}
	public final void unlock(Student lockStudent){
		LockItem lockItem = studentLockMap.get(lockStudent);
		studentLockMap.remove(lockStudent);
		unlockAction(lockItem);}
	
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
