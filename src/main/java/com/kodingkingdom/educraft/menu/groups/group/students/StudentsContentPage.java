package com.kodingkingdom.educraft.menu.groups.group.students;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;

public class StudentsContentPage extends SelectFunctionSortedPage<Student>{

	public StudentsContentPage(Group Group, Consumer<Student> StudentAction) {
		super(
			()->{
				return Group.getStudents().stream()
						.collect(Collectors.toList());}
			,(Student student)->{
				return new SelectItem(
						()->{
							StudentAction.accept(student);}
						,Icon.makeIcon(student).asIcon());}
			, Bible.pollInterval);}}
