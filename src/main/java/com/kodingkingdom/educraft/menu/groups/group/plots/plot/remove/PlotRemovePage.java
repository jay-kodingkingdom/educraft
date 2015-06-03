package com.kodingkingdom.educraft.menu.groups.group.plots.plot.remove;

import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.resources.Plot;

public class PlotRemovePage extends CompositeBoxPage {
	Plot plot;
	
	public PlotRemovePage(Plot Plot){
		plot=Plot;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(plot.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null , null
				,  ()->{
					PlotRemovePage thisPage = PlotRemovePage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		PlotRemoveContentPage contentPage = new PlotRemoveContentPage(
				plot
				, ()->{
					return Icon.makeIcon(Textures.All).withName("All Plots").withCaption("All Plots").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
