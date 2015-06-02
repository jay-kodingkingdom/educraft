package com.kodingkingdom.educraft.menu.groups.group.powers.power.add;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.User;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.powers.Power;

public class PowerAddContentPage extends SelectFunctionSortedAllPage<User>{

	public PowerAddContentPage(Group Group, Power power, Supplier<ItemStack> StudentIcon) {
		super(
			()->{
				return Group.getStudents().stream()
						.filter(student->!power.getStudents().contains(student))
						.collect(Collectors.toList());}
			,(User user)->{
				return new SelectItem(
						()->{
								power.give(user);}
						,Icon.makeIcon(user).asIcon());}
			, StudentIcon, Bible.pollInterval);}}
