
package com.kodingkingdom.educraft.menu.groups.group.powers.power.add;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.powers.Power;

public class PowerAddPage extends CompositeBoxPage {
	Group group;
	Power power;
	
	public PowerAddPage(Group Group, Power Power){
		group=Group;
		power=Power;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(power.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null, null
				,  ()->{
					PowerAddPage thisPage = PowerAddPage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		PowerAddContentPage contentPage = new PowerAddContentPage(
				group
				,power
				, ()->{
					return Icon.makeIcon(Textures.Users).withName("All Group Users").withCaption("All Group Users").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
