package com.kodingkingdom.educraft.menu.groups.group.worlds;

import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.menu.groups.add.GroupAddPage;
import com.kodingkingdom.educraft.menu.groups.group.GroupPage;
import com.kodingkingdom.educraft.menu.groups.remove.GroupRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class WorldsPage extends CompositeBoxPage {
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Groups", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					WorldsPage thisPage = WorldsPage.this; 
					GroupAddPage newPage = new GroupAddPage();
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					WorldsPage thisPage = WorldsPage.this; 
					GroupRemovePage newPage = new GroupRemovePage();
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null, null, null);
		WorldsContentPage contentPage = new WorldsContentPage(
				group->{
					WorldsPage thisPage = WorldsPage.this;
					GroupPage newPage = new GroupPage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					return null;});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
