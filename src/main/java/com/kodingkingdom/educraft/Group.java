package com.kodingkingdom.educraft;

import java.util.HashSet;

import com.kodingkingdom.educraft.resources.Lock;
import com.kodingkingdom.educraft.resources.Plot;
import com.kodingkingdom.educraft.resources.Task;
import com.kodingkingdom.educraft.resources.World;

public class Group {
	String name;
	
	HashSet<Student> students;
	HashSet<Task> tasks;
	HashSet<World> worlds;
	HashSet<Plot> plots;
	HashSet<Lock> locks;
	
	public Group(String Name){
		groups.add(this);
	}
	
	public String getName(){return name;}
	
	private static HashSet<Group> groups=new HashSet<Group>();
	public static HashSet<Group> getGroups(){
		return new HashSet<Group> (groups);}
	
	public static void copy(Group group){
		Group groupCopy = new Group(group.name);
		groupCopy.students=new HashSet<Student>(group.students);
		groupCopy.tasks=new HashSet<Task>(group.tasks);
		groupCopy.worlds=new HashSet<World>(group.worlds);
		groupCopy.plots=new HashSet<Plot>(group.plots);
		groupCopy.locks=new HashSet<Lock>(group.locks);}
	public static void delete(Group group){
		groups.remove(group);
		//delete all resources
		}
	public static void merge(Group group1, Group group2){
		group1.students.addAll(group2.students);
		group1.tasks.addAll(group2.tasks);
		group1.worlds.addAll(group2.worlds);
		group1.plots.addAll(group2.plots);
		group1.locks.addAll(group2.locks);
		Group.delete(group2);}}
