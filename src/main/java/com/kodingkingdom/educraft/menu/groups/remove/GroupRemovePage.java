package com.kodingkingdom.educraft.menu.groups.remove;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Texture;

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
					return Icon.makeIcon(Texture.All).withName("All Groups").withCaption("All Groups").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
