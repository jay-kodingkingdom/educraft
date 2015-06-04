package com.kodingkingdom.educraft.menu.groups.group.students.add;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;

public class StudentAddContentPage extends SelectFunctionSortedAllPage<Student>{

//	private static <T extends Collection<U>, U> T debug(T list){
//		for (U item : list){
//			EduCraftPlugin.debug("whats in the list? "+item);
//		}
//		return list;}
	
	public StudentAddContentPage(Group Group, Supplier<ItemStack> StudentIcon) {
		super(
			()->{
				return Bukkit.getOnlinePlayers().stream()
						.map(player->Student.getStudent(player.getUniqueId()))
						.filter(student->!Group.getStudents().contains(student))
						.collect(Collectors.toList());}
			,(Student student)->{
				return new SelectItem(
						()->{
							Group.addStudents(student);}
						,Icon.makeIcon(student).asIcon());}
			, StudentIcon, Bible.pollInterval);}}
