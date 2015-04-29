package com.kodingkingdom.educraft.assessment;

import com.kodingkingdom.educraft.StudentUser;

public abstract class WorkSheet {
	public abstract StudentUser getStudent();
	public abstract void begin();
	public abstract void end();
	
	public abstract WorkSheet getClone();
	public abstract Result getResult();}
