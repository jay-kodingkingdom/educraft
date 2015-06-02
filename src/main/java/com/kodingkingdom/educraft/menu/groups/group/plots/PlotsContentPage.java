package com.kodingkingdom.educraft.menu.groups.group.plots;

import java.util.function.Consumer;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;

public class PlotsContentPage extends SelectFunctionSortedPage<Group>{

	public PlotsContentPage(Consumer<Group> GroupAction) {
		super(
			()->{
				return Group.getGroups();}
			,(Group group)->{
				return new SelectItem(
						()->{
							GroupAction.accept(group);}
						,Icon.makeIcon(group.getName()).asIcon());}
			, Bible.pollInterval);}}
