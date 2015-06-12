package com.kodingkingdom.educraft.menu.groups.group.plotworlds;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.plotworlds.add.PlotWorldsAddPage;
import com.kodingkingdom.educraft.menu.groups.group.plotworlds.plot.PlotPage;
import com.kodingkingdom.educraft.menu.groups.group.plotworlds.remove.PlotWorldsRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class PlotWorldsPage extends CompositeBoxPage {
	Group group;
	
	public PlotWorldsPage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("PlotWorlds", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PlotWorldsPage thisPage = PlotWorldsPage.this; 
					thisPage.remove();}
				, null
				, ()->{
					PlotWorldsPage thisPage = PlotWorldsPage.this; 
					PlotWorldsAddPage newPage = new PlotWorldsAddPage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					PlotWorldsPage thisPage = PlotWorldsPage.this; 
					PlotWorldsRemovePage newPage = new PlotWorldsRemovePage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null);
		PlotWorldsContentPage contentPage = new PlotWorldsContentPage(
				group,
				plot->{
					PlotWorldsPage thisPage = PlotWorldsPage.this;
					PlotPage newPage = new PlotPage(group,plot);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
