package com.kodingkingdom.educraft.menu.groups.group.powers.add.playerteleport;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;

public class PlayerTeleportPowerAddContentPage extends SelectFunctionSortedPage<Student>{
	
	public PlayerTeleportPowerAddContentPage(Group Group, Consumer<Student> StudentAction) {
		super(
			()->{
				return Bukkit.getOnlinePlayers().stream()
						.map(player->Student.getStudent(player.getUniqueId()))
						.collect(Collectors.toList());}
			,(Student student)->{
				return new SelectItem(
						()->{
							StudentAction.accept(student);}
						,Icon.makeIcon(student).withName("Create PlayerTeleportPower on "+student.getName())
												.withCaption("Create PlayerTeleportPower on "+student.getName()).asIcon());}
			, Bible.pollInterval);}}
