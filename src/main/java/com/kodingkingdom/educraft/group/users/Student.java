package com.kodingkingdom.educraft.group.users;

import java.util.HashMap;
import java.util.UUID;

import com.kodingkingdom.educraft.group.User;

public class Student extends User {
	
	static HashMap<UUID, Student> idStudentMap=new HashMap<UUID, Student>(); 
		
	protected Student(UUID Id){
		super(Id);}	
	
	public static Student getStudent(UUID id){
		if (idStudentMap.containsKey(id))
			idStudentMap.put(id, new Student(id));
		return idStudentMap.get(id);}}
