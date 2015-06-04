package com.kodingkingdom.educraft.menu.groups.group.worlds;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.resources.World;

public class WorldsContentPage extends SelectFunctionSortedPage<World>{

	public WorldsContentPage(Group Group, Consumer<World> WorldAction) {
		super(
			()->{
				return new ArrayList<World>();}
			,world->{
				return new SelectItem(
						()->{WorldAction.accept(world);}
						,null);}
			, Bible.pollInterval);}}