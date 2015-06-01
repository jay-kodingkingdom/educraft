package com.kodingkingdom.educraft.menu.allgroups;

import com.kodingkingdom.educraft.menu.TeacherMenu;
import com.kodingkingdom.educraft.menu.allgroups.group.GroupPage;
import com.kodingkingdom.educraft.menu.allgroups.groupadd.GroupAddPage;
import com.kodingkingdom.educraft.menu.allgroups.groupremove.GroupRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class AllGroupsPage extends CompositeBoxPage {
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Groups", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					AllGroupsPage thisPage = AllGroupsPage.this; 
					GroupAddPage newPage = new GroupAddPage();
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					AllGroupsPage thisPage = AllGroupsPage.this; 
					GroupRemovePage newPage = new GroupRemovePage();
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null, null, null);
		AllGroupsContentPage contentPage = new AllGroupsContentPage(
				group->{
					AllGroupsPage thisPage = AllGroupsPage.this;
					GroupPage newPage = new GroupPage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					return TeacherMenu.groupsIcon;});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, menuItemsBox.getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, menuItemsBox.getHeight()-1, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-2)));}}
