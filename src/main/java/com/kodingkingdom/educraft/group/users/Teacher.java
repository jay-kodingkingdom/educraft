package com.kodingkingdom.educraft.group.users;

import java.util.HashMap;
import java.util.UUID;

import com.kodingkingdom.educraft.group.User;

public class Teacher extends User{

	static HashMap<UUID, Teacher> idTeacherMap=new HashMap<UUID, Teacher>(); 
	
	protected Teacher(UUID Id){
		super(Id);}	
	
	public static Teacher getTeacher(UUID id){
		if (!idTeacherMap.containsKey(id))
			idTeacherMap.put(id, new Teacher(id));
		return idTeacherMap.get(id);}}