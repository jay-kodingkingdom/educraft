package com.kodingkingdom.educraft.menu.groups.group.plots;

import com.kodingkingdom.educraft.menu.groups.add.GroupAddPage;
import com.kodingkingdom.educraft.menu.groups.group.GroupPage;
import com.kodingkingdom.educraft.menu.groups.remove.GroupRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class PlotsPage extends CompositeBoxPage {
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Plots", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PlotsPage thisPage = PlotsPage.this; 
					GroupAddPage newPage = new GroupAddPage();
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					PlotsPage thisPage = PlotsPage.this; 
					GroupRemovePage newPage = new GroupRemovePage();
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null, null, null);
		PlotsContentPage contentPage = new PlotsContentPage(
				group->{
					PlotsPage thisPage = PlotsPage.this;
					GroupPage newPage = new GroupPage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
