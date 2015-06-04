package com.kodingkingdom.educraft.menu.groups.group.powers.power.remove;

import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.powers.Power;

public class PowerRemovePage extends CompositeBoxPage {
	Power power;
	
	public PowerRemovePage(Power Power){
		power=Power;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(power.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PowerRemovePage thisPage = PowerRemovePage.this;
					thisPage.remove();}
				, null , null, null, null, null, null, null);
		PowerRemoveContentPage contentPage = new PowerRemoveContentPage(
				power
				, ()->{
					return Icon.makeIcon(Textures.Powers).withName("All Powered Users").withCaption("All Locked Users").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
