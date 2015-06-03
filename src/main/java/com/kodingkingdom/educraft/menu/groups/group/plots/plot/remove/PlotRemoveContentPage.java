package com.kodingkingdom.educraft.menu.groups.group.plots.plot.remove;

import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.resources.Plot;
import com.kodingkingdom.educraft.resources.Plot.PlotItem;

public class PlotRemoveContentPage extends SelectFunctionSortedAllPage<PlotItem>{

	public PlotRemoveContentPage(Plot Plot, Supplier<ItemStack> PlotsIcon) {
		super(
			()->{
				return Plot.getPlots();}
			,plotItem->{
				return new SelectItem(
						()->{
							Plot.takePlot(plotItem);}
						,Icon.makeIcon(Textures.Plots).withName(plotItem.getId())
												.withCaption(plotItem.getStudent().getName()).asIcon());}
			, PlotsIcon, Bible.pollInterval);}}
