package com.kodingkingdom.educraft.menu.groups.group.plots.remove;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;

public class PlotsRemovePage extends CompositeBoxPage {
	Group group;
	
	public PlotsRemovePage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Plots", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null , null
				,  ()->{
					PlotsRemovePage thisPage = PlotsRemovePage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		PlotsRemoveContentPage contentPage = new PlotsRemoveContentPage(
				group
				, ()->{
					return Icon.makeIcon(Textures.Plots).withName("All Plots").withCaption("All Plots").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
