package com.kodingkingdom.educraft.menu.groups.group.powers.power;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.powers.power.add.PowerAddPage;
import com.kodingkingdom.educraft.menu.groups.group.powers.power.remove.PowerRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.powers.Power;

public class PowerPage extends CompositeBoxPage {
	Group group;
	Power power;
	public PowerPage(Group Group, Power Power){
		group = Group;
		power=Power;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Power", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PowerPage thisPage = PowerPage.this; 
					thisPage.remove();}
				, null
				, ()->{
					PowerPage thisPage = PowerPage.this; 
					PowerAddPage newPage = new PowerAddPage(group, power);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					PowerPage thisPage = PowerPage.this; 
					PowerRemovePage newPage = new PowerRemovePage (power);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null);
		PowerContentPage contentPage = new PowerContentPage(
				power,
				power->{});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
