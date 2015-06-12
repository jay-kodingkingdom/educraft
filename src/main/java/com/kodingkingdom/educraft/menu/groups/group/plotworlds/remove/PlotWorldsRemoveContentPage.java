package com.kodingkingdom.educraft.menu.groups.group.plotworlds.remove;

import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.resources.PlotWorld;

public class PlotWorldsRemoveContentPage extends SelectFunctionSortedAllPage<PlotWorld>{

	public PlotWorldsRemoveContentPage(Group Group, Supplier<ItemStack> PlotsIcon) {
		super(
			()->{
				return Group.getPlots();}
			,plot->{
				return new SelectItem(
						()->{
							Group.removePlots(plot);}
						,Icon.makeIcon(Textures.PlotWorlds).withName(plot.getName()).withCaption(plot.getName()).asIcon());}
			, PlotsIcon, Bible.pollInterval);}}
