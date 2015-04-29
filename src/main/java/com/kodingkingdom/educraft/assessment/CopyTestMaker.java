package com.kodingkingdom.educraft.assessment;

import java.util.concurrent.Callable;

import org.bukkit.event.Listener;

import com.kodingkingdom.educraft.TeacherUser;

public class CopyTestMaker extends TestMaker implements Listener{

	Assessment assessment;

	public CopyTestMaker(Assessment Assessment){assessment=Assessment;}
		
	@Override
	public Callable<Assessment> makeTest(TeacherUser teacher) {
		return new Callable<Assessment>(){
			@Override
			public Assessment call(){
				return assessment.getClone();}};}
	@Override
	public String getName() {
		return assessment.getName();}}
