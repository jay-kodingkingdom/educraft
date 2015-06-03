package com.kodingkingdom.educraft.menu.groups.group.plots;

import java.util.function.Consumer;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.resources.Plot;

public class PlotsContentPage extends SelectFunctionSortedPage<Plot>{

	public PlotsContentPage(Group Group, Consumer<Plot> PlotAction) {
		super(
			()->{
				return Group.getPlots();}
			,plot->{
				return new SelectItem(
						()->{
							PlotAction.accept(plot);}
						,Icon.makeIcon(Textures.Plots).withCaption(plot.getName()).withName(plot.getName()).asIcon());}
			, Bible.pollInterval);}}
