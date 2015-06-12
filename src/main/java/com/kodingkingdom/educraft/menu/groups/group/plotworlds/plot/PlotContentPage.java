package com.kodingkingdom.educraft.menu.groups.group.plotworlds.plot;

import java.util.function.Consumer;

import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.resources.PlotWorld;
import com.kodingkingdom.educraft.resources.PlotWorld.PlotItem;

public class PlotContentPage extends SelectFunctionSortedPage<PlotItem>{

	public PlotContentPage(PlotWorld plotWorld, Consumer<PlotItem> PlotAction) {
		super(
			()->{
				return plotWorld.getPlots();}
			, plotItem->{
				return new SelectItem(
						()->{
							PlotAction.accept(plotItem);}
						,Icon.makeIcon(Textures.PlotWorlds)
							.withName(plotItem.getId())
							.withCaption(plotItem.getStudent().getName()).asIcon());}
			, Bible.pollInterval);}}
