package com.kodingkingdom.educraft.menu.groups.group.plotworlds.remove;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;

public class PlotWorldsRemovePage extends CompositeBoxPage {
	Group group;
	
	public PlotWorldsRemovePage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Plots", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PlotWorldsRemovePage thisPage = PlotWorldsRemovePage.this;
					thisPage.remove();}
				, null , null, null, null, null, null, null);
		PlotWorldsRemoveContentPage contentPage = new PlotWorldsRemoveContentPage(
				group
				, ()->{
					return Icon.makeIcon(Textures.PlotWorlds).withName("All Plots").withCaption("All Plots").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
