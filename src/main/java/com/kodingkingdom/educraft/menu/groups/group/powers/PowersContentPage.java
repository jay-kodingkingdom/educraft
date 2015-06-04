package com.kodingkingdom.educraft.menu.groups.group.powers;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.powers.Power;

public class PowersContentPage extends SelectFunctionSortedPage<Power>{

	public PowersContentPage(Group Group, Consumer<Power> PowerAction, Function<Power, ItemStack> PowerIcon) {
		super(
			()->{
				return Group.getPowers().stream()
						.collect(Collectors.toList());}
			,(Power power)->{
				return new SelectItem(
						()->{
							PowerAction.accept(power);}
						, PowerIcon.apply(power));}
			, Bible.pollInterval);}}
