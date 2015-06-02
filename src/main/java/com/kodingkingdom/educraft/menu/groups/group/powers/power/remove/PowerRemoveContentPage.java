package com.kodingkingdom.educraft.menu.groups.group.powers.power.remove;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.User;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.powers.Power;

public class PowerRemoveContentPage extends SelectFunctionSortedAllPage<User>{

	public PowerRemoveContentPage(Power power, Supplier<ItemStack> StudentIcon) {
		super(
			()->{
				return power.getStudents().stream().collect(Collectors.toList());}
			,(User user)->{
				return new SelectItem(
						()->{
							power.take(user);}
						,Icon.makeIcon(user).asIcon());}
			, StudentIcon, Bible.pollInterval);}}
