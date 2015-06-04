package com.kodingkingdom.educraft.group;

import java.util.Arrays;
import java.util.HashSet;

import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.powers.Lock;
import com.kodingkingdom.educraft.powers.Power;
import com.kodingkingdom.educraft.powers.Task;
import com.kodingkingdom.educraft.powers.locks.FreezeLock;
import com.kodingkingdom.educraft.powers.locks.MuteLock;
import com.kodingkingdom.educraft.powers.locks.NoBuildLock;
import com.kodingkingdom.educraft.powers.powers.GameModeCreativePower;
import com.kodingkingdom.educraft.powers.powers.GameModeSurvivalPower;
import com.kodingkingdom.educraft.powers.powers.SuperSpeedPower;
import com.kodingkingdom.educraft.powers.powers.TimeLockPower;
import com.kodingkingdom.educraft.resources.Plot;
import com.kodingkingdom.educraft.resources.World;

public class Group implements Comparable<Group>{
	String name;
	
	HashSet<Student> students;

	HashSet<World> worlds;
	HashSet<Plot> plots;
	
	HashSet<Task> tasks;
	HashSet<Lock> locks;
	HashSet<Power> powers;


	public HashSet<Student> getStudents(){
		return new HashSet<Student>(students);}
	public HashSet<World> getWorlds(){
		return new HashSet<World>(worlds);}
	public HashSet<Plot> getPlots(){
		return new HashSet<Plot>(plots);}
	public HashSet<Task> getTasks(){
		return new HashSet<Task>(tasks);}
	public HashSet<Lock> getLocks(){
		return new HashSet<Lock>(locks);}
	public HashSet<Power> getPowers(){
		return new HashSet<Power>(powers);}
	
	public void addStudents(Student... Students){
		students.addAll(Arrays.asList(Students));}
	public void addWorlds(World... Worlds){
		worlds.addAll(Arrays.asList(Worlds));}
	public void addPlots(Plot... Plots){
		plots.addAll(Arrays.asList(Plots));}
	public void addTasks(Task... Tasks){
		tasks.addAll(Arrays.asList(Tasks));}
	public void addLocks(Lock... Locks){
		locks.addAll(Arrays.asList(Locks));}
	public void addPowers(Power... Powers){
		powers.addAll(Arrays.asList(Powers));}
	
	public void removeStudents(Student... Students){
		students.removeAll(Arrays.asList(Students));}
	public void removeWorlds(World... Worlds){
		worlds.removeAll(Arrays.asList(Worlds));}
	public void removePlots(Plot... Plots){
		plots.removeAll(Arrays.asList(Plots));}
	public void removeTasks(Task... Tasks){
		tasks.removeAll(Arrays.asList(Tasks));}
	public void removeLocks(Lock... Locks){
		locks.removeAll(Arrays.asList(Locks));}
	public void removePowers(Power... Powers){
		powers.removeAll(Arrays.asList(Powers));}
	
	
	private Group(){}
	
	public String getName(){return name;}
	
	private static HashSet<Group> groups=new HashSet<Group>();
	public static HashSet<Group> getGroups(){
		return new HashSet<Group> (groups);}
	
	public static Group create(String Name){
		Group group = new Group();
		
		group.name=Name;
		group.students=new HashSet<Student>();
		group.tasks=new HashSet<Task>();
		group.worlds=new HashSet<World>();
		group.plots=new HashSet<Plot>();
		group.locks=new HashSet<Lock>();
		group.powers=new HashSet<Power>();
		//
		group.locks.add(new FreezeLock());
		group.locks.add(new MuteLock());
		group.locks.add(new NoBuildLock());
		group.powers.add(new SuperSpeedPower());
		group.powers.add(new GameModeCreativePower());
		group.powers.add(new GameModeSurvivalPower());
		group.powers.add(new TimeLockPower());
		//
		groups.add(group);
		return group;}
	
	public static void copy(Group group){
		Group groupCopy = create(group.name);
		groupCopy.students=new HashSet<Student>(group.students);
		groupCopy.tasks=new HashSet<Task>(group.tasks);
		groupCopy.worlds=new HashSet<World>(group.worlds);
		groupCopy.plots=new HashSet<Plot>(group.plots);
		groupCopy.locks=new HashSet<Lock>(group.locks);}
	public static void delete(Group group){
		groups.remove(group);

		for (World world : group.worlds){
			World.deleteWorld(world);}
		for (Plot plot : group.plots){
			Plot.deletePlot(plot);}
		for (Student student : group.students){
			for (Task task : group.tasks){
				task.ungive(student);}
			for (Lock lock : group.locks){
				lock.take(student);}
			for (Power power : group.powers){
				power.take(student);}}}
	public static void merge(Group group1, Group group2){
		group1.students.addAll(group2.students);
		group1.tasks.addAll(group2.tasks);
		group1.worlds.addAll(group2.worlds);
		group1.plots.addAll(group2.plots);
		group1.locks.addAll(group2.locks);
		Group.delete(group2);}
	public int compareTo(Group o) {
		return this.getName().compareTo(o.getName());}}
