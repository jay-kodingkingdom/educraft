package com.kodingkingdom.educraft.menu.groups.group.powers.remove;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.powers.Power;
import com.kodingkingdom.educraft.powers.powers.LocationTeleportPower;
import com.kodingkingdom.educraft.powers.powers.PlayerTeleportPower;

public class PowersRemoveContentPage extends SelectFunctionSortedAllPage<Power>{

	public PowersRemoveContentPage(Group Group, Supplier<ItemStack> StudentIcon) {
		super(
			()->{
				return Group.getPowers().stream()
						.filter(power->power instanceof PlayerTeleportPower &&
										power instanceof LocationTeleportPower)
						.collect(Collectors.toList());}
			,(Power power)->{
				return new SelectItem(
						()->{
							Group.removePowers(power);}
						,Icon.makeIcon(power.getName()).asIcon());}
			, StudentIcon, Bible.pollInterval);}}
