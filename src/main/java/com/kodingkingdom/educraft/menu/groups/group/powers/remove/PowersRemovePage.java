package com.kodingkingdom.educraft.menu.groups.group.powers.remove;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;

public class PowersRemovePage extends CompositeBoxPage {
	Group group;
	
	public PowersRemovePage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Powers", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null , null
				,  ()->{
					PowersRemovePage thisPage = PowersRemovePage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		PowersRemoveContentPage contentPage = new PowersRemoveContentPage(
				group
				, ()->{
					return Icon.makeIcon(Textures.Powers).withName("Removable Powers").withCaption("Removable Powers").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
