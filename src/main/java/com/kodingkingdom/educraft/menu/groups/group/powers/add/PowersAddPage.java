
package com.kodingkingdom.educraft.menu.groups.group.powers.add;

import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.powers.add.playerteleport.PlayerTeleportPowerAddPage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.Menu;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.powers.powers.LocationTeleportPower;;

public class PowersAddPage extends CompositeBoxPage {
	Group group;
	
	public PowersAddPage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Powers", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PowersAddPage thisPage = PowersAddPage.this;
					thisPage.remove();}
				, null, null, null, null, null, null, null);
		PowersAddContentPage contentPage = new PowersAddContentPage(
				getWidth()-1
				, getHeight()-1
				, new SelectItem(
						()->{
							PowersAddPage thisPage = PowersAddPage.this;
							Player player = Menu.getMenu(thisPage).getUser().getPlayer();
							if (player!=null) {
								group.addPowers(new LocationTeleportPower(player.getLocation()));
								player.sendMessage("LocationTeleportPower has been created on your current location");}}
						, Icon.makeIcon(Textures.Portal).withName("Create LocationTeleportPower on your current location")
						.withCaption("Create LocationTeleportPower on your current location").asIcon())
				, new SelectItem(
						()->{
							PowersAddPage thisPage = PowersAddPage.this;
							PlayerTeleportPowerAddPage newPage = new PlayerTeleportPowerAddPage(group);
							thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
						, Icon.makeIcon(Textures.Teleport).withName("Create PlayerTeleportPower on a player")
						.withCaption("Create PlayerTeleportPower on a player").asIcon()));
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
