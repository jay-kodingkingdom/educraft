package com.kodingkingdom.educraft.menu.groups.group.areas;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.resources.Area;

public class AreasContentPage extends SelectFunctionSortedPage<Area>{

	public AreasContentPage(Group Group, Consumer<Area> WorldAction) {
		super(
			()->{
				return new ArrayList<Area>();}
			,world->{
				return new SelectItem(
						()->{WorldAction.accept(world);}
						,null);}
			, Bible.pollInterval);}}