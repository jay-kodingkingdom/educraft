package com.kodingkingdom.educraft.menu.groups.group.students.remove;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;

public class StudentRemoveContentPage extends SelectFunctionSortedAllPage<Student>{

	public StudentRemoveContentPage(Group Group, Supplier<ItemStack> StudentIcon) {
		super(
			()->{
				return Group.getStudents().stream().collect(Collectors.toList());}
			,(Student student)->{
				return new SelectItem(
						()->{
							Group.removeStudents(student);}
						,Icon.makeIcon(student).asIcon());}
			, StudentIcon, Bible.pollInterval);}}
