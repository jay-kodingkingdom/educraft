package com.kodingkingdom.educraft.menu.groups.group.plots.plot;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.plots.plot.add.PlotAddPage;
import com.kodingkingdom.educraft.menu.groups.group.plots.plot.remove.PlotRemovePage;
import com.kodingkingdom.educraft.menu.groups.group.powers.power.PowerPage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.resources.Plot;

public class PlotPage extends CompositeBoxPage {
	Group group;
	Plot plot;
	public PlotPage(Group Group, Plot Plot){
		group = Group;
		plot=Plot;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(plot.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PlotPage thisPage = PlotPage.this; 
					thisPage.remove();}
				, null
				, ()->{
					PlotPage thisPage = PlotPage.this; 
					PlotAddPage newPage = new PlotAddPage(group, plot);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					PlotPage thisPage = PlotPage.this; 
					PlotRemovePage newPage = new PlotRemovePage (plot);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null);
		PlotContentPage contentPage = new PlotContentPage(
				plot,
				plotItem->{
					PlotPage thisPage = PlotPage.this; 
					PowerPage newPage = new PowerPage(group, plotItem.getTeleporter());
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
