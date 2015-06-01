package com.kodingkingdom.educraft.menu.allgroups.groupremove;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.TeacherMenu;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class GroupRemovePage extends CompositeBoxPage {
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Groups", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null , null
				,  ()->{
					GroupRemovePage thisPage = GroupRemovePage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		GroupRemoveContentPage contentPage = new GroupRemoveContentPage(
				group->{
					Group.delete(group);
					GroupRemovePage thisPage = GroupRemovePage.this;
					thisPage.remove();}
				, ()->{
					return TeacherMenu.groupsIcon;});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, menuItemsBox.getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, menuItemsBox.getHeight()-1, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-2)));}}
