package com.kodingkingdom.educraft.menu.groups;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.TeacherMenu;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.BoxPage;
import com.kodingkingdom.educraft.page.Page;

public class GroupsPage extends BoxPage {
	protected void attachedAction(Connector connector){
		NamePage namePage = new NamePage("Groups", menuItemsBox.getHeight());
		GroupsAllControlsPage controlsPage = new GroupsAllControlsPage(
				()->{
					for (Page childPage : GroupsPage.this.getChildPages()){
						if (childPage instanceof GroupsAllControlsPage){
							GroupsPage.this.remove(childPage);
							
							GroupsPage.this.attach(childPage, connector);}
						else if (childPage instanceof GroupsAllPage){
							GroupsPage.this.remove(childPage);
							GroupsAddPage groupAddPage = new GroupsAddPage(null,menuItemsBox.getWidth()-1,menuItemsBox.getHeight()-1); 
							GroupsPage.this.attach(childPage, connector);}}}
				, menuItemsBox.getWidth()-1);
		GroupsAllPage groupsPage = new GroupsAllPage((Group group)->{}, ()->{return TeacherMenu.groupsIcon;}, 0);
		this.attach(namePage, namePage.makePageConnector(this, 0, 0, 0, menuItemsBox.getHeight()-1));
		this.attach(controlsPage, controlsPage.makePageConnector(this, 1, menuItemsBox.getHeight()-1, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-1));
		this.attach(groupsPage, groupsPage.makePageConnector(this, 1, 0, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-2));}}
