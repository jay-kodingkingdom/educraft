package com.kodingkingdom.educraft.menu.groups.group.locks.lock;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.powers.Lock;

public class LockContentPage extends SelectFunctionSortedPage<Student>{

	public LockContentPage(Lock lock, Consumer<Student> StudentAction) {
		super(
			()->{
				return lock.getStudents().stream()
						.collect(Collectors.toList());}
			,(Student student)->{
				return new SelectItem(
						()->{
							StudentAction.accept(student);}
						,Icon.makeIcon(student).asIcon());}
			, Bible.pollInterval);}}
