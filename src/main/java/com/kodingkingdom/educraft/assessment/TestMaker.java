package com.kodingkingdom.educraft.assessment;

import java.util.concurrent.Callable;

import com.kodingkingdom.educraft.TeacherUser;

public abstract class TestMaker {
	public abstract String getName();

	public abstract Callable<Assessment> makeTest(TeacherUser teacher);
}
