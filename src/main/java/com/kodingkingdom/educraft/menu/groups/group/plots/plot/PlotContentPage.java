package com.kodingkingdom.educraft.menu.groups.group.plots.plot;

import java.util.function.Consumer;

import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.resources.Plot;
import com.kodingkingdom.educraft.resources.Plot.PlotItem;

public class PlotContentPage extends SelectFunctionSortedPage<PlotItem>{

	public PlotContentPage(Plot plot, Consumer<PlotItem> PlotAction) {
		super(
			()->{
				return plot.getPlotItems();}
			, plotItem->{
				return new SelectItem(
						()->{
							PlotAction.accept(plotItem);}
						,Icon.makeIcon(Textures.Plots)
							.withName(plotItem.getId())
							.withCaption(plotItem.getStudent().getName()).asIcon());}
			, Bible.pollInterval);}}
