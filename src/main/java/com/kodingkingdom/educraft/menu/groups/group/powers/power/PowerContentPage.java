package com.kodingkingdom.educraft.menu.groups.group.powers.power;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.group.User;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.powers.Power;

public class PowerContentPage extends SelectFunctionSortedPage<User>{

	public PowerContentPage(Power power, Consumer<User> StudentAction) {
		super(
			()->{
				return power.getStudents().stream()
						.collect(Collectors.toList());}
			,(User user)->{
				return new SelectItem(
						()->{
							StudentAction.accept(user);}
						,Icon.makeIcon(user).asIcon());}
			, Bible.pollInterval);}}
