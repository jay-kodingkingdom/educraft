package com.kodingkingdom.educraft;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import com.kodingkingdom.educraft.assessment.Assessment;
import com.kodingkingdom.educraft.assessment.TestMaker;

public class Clasz {
	public HashMap<UUID,TeacherUser> teachers;
	public HashMap<UUID,StudentUser> students;
	public HashSet<TestMaker> testMakers;
	public HashSet<Assessment> assessments;
	
	public Clasz(){
		teachers=new HashMap<UUID,TeacherUser>();students=new HashMap<UUID,StudentUser>();testMakers=new HashSet<TestMaker>();assessments=new HashSet<Assessment>();}}
