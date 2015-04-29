package com.kodingkingdom.educraft;

import java.util.UUID;


public class TeacherUser extends User {
	UUID teacherId;
	public UUID getID(){return teacherId;}
	public TeacherUser(UUID TeacherId){
		teacherId=TeacherId;}}
