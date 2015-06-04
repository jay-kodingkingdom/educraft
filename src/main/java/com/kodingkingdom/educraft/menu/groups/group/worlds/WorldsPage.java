package com.kodingkingdom.educraft.menu.groups.group.worlds;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class WorldsPage extends CompositeBoxPage {
	Group group;
	public WorldsPage(Group Group){
		group = Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(group.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					WorldsPage thisPage = WorldsPage.this; 
					thisPage.remove();}
				, null, null, null, null, null, null, null);
		WorldsContentPage contentPage = new WorldsContentPage(
				group
				,task->{});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
