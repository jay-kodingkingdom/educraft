package com.kodingkingdom.educraft;

import java.util.UUID;

public class StudentUser extends User {
	UUID studentId;
	public UUID getID(){return studentId;}
	public StudentUser(UUID StudentId){studentId=StudentId;}}
