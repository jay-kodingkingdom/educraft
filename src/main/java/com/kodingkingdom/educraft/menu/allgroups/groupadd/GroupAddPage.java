
package com.kodingkingdom.educraft.menu.allgroups.groupadd;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.allgroups.groupadd.GroupAddContentPage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.VaryNamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class GroupAddPage extends CompositeBoxPage {
	String groupName="";
	protected void compositeAttachedAction(Connector connector){
		VaryNamePage namePage = new VaryNamePage(()->{return groupName;}, getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					groupName=groupName.substring(0, groupName.length());}
				, ()->{
					Group.create(groupName);
					GroupAddPage thisPage = GroupAddPage.this;
					thisPage.remove();}
				,  ()->{
					GroupAddPage thisPage = GroupAddPage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		GroupAddContentPage contentPage = new GroupAddContentPage(
				letter->{
					String newGroupName = letter + groupName;
					if (newGroupName.length()<=getHeight()) groupName=newGroupName;}
				, getWidth()-1, getHeight()-1);
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, menuItemsBox.getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, menuItemsBox.getHeight()-1, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, menuItemsBox.getWidth()-1, menuItemsBox.getHeight()-2)));}}
