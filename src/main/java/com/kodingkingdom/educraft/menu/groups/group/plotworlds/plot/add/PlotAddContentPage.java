package com.kodingkingdom.educraft.menu.groups.group.plotworlds.plot.add;

import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.resources.PlotWorld;

public class PlotAddContentPage extends SelectFunctionSortedAllPage<Student>{

	public PlotAddContentPage(Group Group, PlotWorld plotWorld, Supplier<ItemStack> PlotsIcon) {
		super(
			()->{
				return Group.getStudents();}
			,student->{
				return new SelectItem(
						()->{
								plotWorld.givePlot(student)
									.getTeleporter().give(student);
								if (student.getPlayer()!=null) student.getPlayer().sendMessage("You have been given a new plotWorld");}
						,Icon.makeIcon(student).asIcon());}
			, PlotsIcon, Bible.pollInterval);}}
