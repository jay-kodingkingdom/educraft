package com.kodingkingdom.educraft.menu.groups.group.plots;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.plots.add.PlotsAddPage;
import com.kodingkingdom.educraft.menu.groups.group.plots.plot.PlotPage;
import com.kodingkingdom.educraft.menu.groups.group.plots.remove.PlotsRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class PlotsPage extends CompositeBoxPage {
	Group group;
	
	public PlotsPage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Plots", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PlotsPage thisPage = PlotsPage.this; 
					PlotsAddPage newPage = new PlotsAddPage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					PlotsPage thisPage = PlotsPage.this; 
					PlotsRemovePage newPage = new PlotsRemovePage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					PlotsPage thisPage = PlotsPage.this; 
					thisPage.remove();}
				, null, null, null, null, null);
		PlotsContentPage contentPage = new PlotsContentPage(
				group,
				plot->{
					PlotsPage thisPage = PlotsPage.this;
					PlotPage newPage = new PlotPage(group,plot);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
