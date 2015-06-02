package com.kodingkingdom.educraft.menu.groups.group.powers;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.powers.add.PowersAddPage;
import com.kodingkingdom.educraft.menu.groups.group.powers.power.PowerPage;
import com.kodingkingdom.educraft.menu.groups.group.powers.remove.PowersRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class PowersPage extends CompositeBoxPage {
	Group group;
	public PowersPage(Group Group){
		group = Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(group.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null
				, ()->{
					PowersPage thisPage = PowersPage.this; 
					thisPage.remove();}
				, ()->{
					PowersPage thisPage = PowersPage.this; 
					PowersAddPage newPage = new PowersAddPage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					PowersPage thisPage = PowersPage.this; 
					PowersRemovePage newPage = new PowersRemovePage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}, null, null, null, null);
		PowersContentPage contentPage = new PowersContentPage(
				group
				,power->{
					PowersPage thisPage = PowersPage.this;
					PowerPage newPage = new PowerPage(group,power);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
