package com.kodingkingdom.educraft.menu.groups.group.students.remove;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;

public class StudentRemovePage extends CompositeBoxPage {
	Group group;
	
	public StudentRemovePage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Groups", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					StudentRemovePage thisPage = StudentRemovePage.this;
					thisPage.remove();}
				, null , null, null, null, null, null, null);
		StudentRemoveContentPage contentPage = new StudentRemoveContentPage(
				group
				,()->{
					return Icon.makeIcon(Textures.Users).withName("All Users").withCaption("All Users").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
