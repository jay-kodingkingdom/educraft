package com.kodingkingdom.educraft.menu.groups.group.plotworlds.plot.remove;

import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.resources.PlotWorld;

public class PlotRemovePage extends CompositeBoxPage {
	PlotWorld plotWorld;
	
	public PlotRemovePage(PlotWorld PlotWorld){
		plotWorld=PlotWorld;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(plotWorld.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PlotRemovePage thisPage = PlotRemovePage.this;
					thisPage.remove();}
				, null , null, null, null, null, null, null);
		PlotRemoveContentPage contentPage = new PlotRemoveContentPage(
				plotWorld
				, ()->{
					return Icon.makeIcon(Textures.All).withName("All Plots").withCaption("All Plots").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
