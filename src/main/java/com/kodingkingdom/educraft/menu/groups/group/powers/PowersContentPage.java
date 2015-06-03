package com.kodingkingdom.educraft.menu.groups.group.powers;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.powers.Power;

public class PowersContentPage extends SelectFunctionSortedPage<Power>{

	public PowersContentPage(Group Group, Consumer<Power> LockAction) {
		super(
			()->{
				return Group.getPowers().stream()
						.collect(Collectors.toList());}
			,(Power power)->{
				return new SelectItem(
						()->{
							LockAction.accept(power);}
						,Icon.makeIcon(Textures.Powers).withName(power.getName()).withCaption(power.getName()).asIcon());}
			, Bible.pollInterval);}}
