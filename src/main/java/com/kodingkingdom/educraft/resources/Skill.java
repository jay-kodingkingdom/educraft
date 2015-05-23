package com.kodingkingdom.educraft.resources;

import java.util.HashMap;

import com.kodingkingdom.educraft.Student;

public class Skill {

	protected HashMap<Student,SkillItem> studentSkillMap;

	public final void give(Student skillStudent){
		SkillItem skillItem = new SkillItem(skillStudent);
		studentSkillMap.put(skillStudent, skillItem);
		doAction(skillItem);}
	public final void take(Student skillStudent){
		SkillItem skillItem = new SkillItem(skillStudent);
		studentSkillMap.remove(skillStudent);
		undoAction(skillItem);}
	
	protected void doAction(SkillItem lockItem){}
	protected void undoAction(SkillItem lockItem){}
	
	public class SkillItem{
		Student skillStudent;
		
		SkillItem(Student SkillStudent){
			skillStudent=SkillStudent;}
		
		public Student getStudent(){
			return skillStudent;}
		public Skill getSkill(){
			return Skill.this;}}}
