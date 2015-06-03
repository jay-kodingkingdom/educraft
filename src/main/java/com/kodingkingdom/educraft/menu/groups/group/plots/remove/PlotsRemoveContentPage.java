package com.kodingkingdom.educraft.menu.groups.group.plots.remove;

import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.resources.Plot;

public class PlotsRemoveContentPage extends SelectFunctionSortedAllPage<Plot>{

	public PlotsRemoveContentPage(Group Group, Supplier<ItemStack> PlotsIcon) {
		super(
			()->{
				return Group.getPlots();}
			,plot->{
				return new SelectItem(
						()->{
							Group.removePlots(plot);}
						,Icon.makeIcon(Textures.Plots).withName(plot.getName()).withCaption(plot.getName()).asIcon());}
			, PlotsIcon, Bible.pollInterval);}}
