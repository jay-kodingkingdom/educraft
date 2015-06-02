package com.kodingkingdom.educraft.menu.groups.group.locks.lock.add;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.powers.Lock;

public class LockAddContentPage extends SelectFunctionSortedAllPage<Student>{

	public LockAddContentPage(Group Group, Lock Lock, Consumer<Student> StudentAction, Supplier<ItemStack> StudentIcon) {
		super(
			()->{
				return Group.getStudents().stream()
						.filter(student->!Lock.getStudents().contains(student))
						.collect(Collectors.toList());}
			,(Student student)->{
				return new SelectItem(
						()->{
							StudentAction.accept(student);}
						,Icon.makeIcon(student).asIcon());}
			, StudentIcon, Bible.pollInterval);}}
