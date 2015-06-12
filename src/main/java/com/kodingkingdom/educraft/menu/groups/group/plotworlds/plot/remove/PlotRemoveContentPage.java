package com.kodingkingdom.educraft.menu.groups.group.plotworlds.plot.remove;

import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.resources.PlotWorld;
import com.kodingkingdom.educraft.resources.PlotWorld.PlotItem;

public class PlotRemoveContentPage extends SelectFunctionSortedAllPage<PlotItem>{

	public PlotRemoveContentPage(PlotWorld PlotWorld, Supplier<ItemStack> PlotsIcon) {
		super(
			()->{
				return PlotWorld.getPlots();}
			,plotItem->{
				return new SelectItem(
						()->{
							PlotWorld.takePlot(plotItem);}
						,Icon.makeIcon(Textures.PlotWorlds).withName(plotItem.getId())
												.withCaption(plotItem.getStudent().getName()).asIcon());}
			, PlotsIcon, Bible.pollInterval);}}
