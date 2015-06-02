
package com.kodingkingdom.educraft.menu.groups.group.powers.add.playerteleport;

import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.Menu;
import com.kodingkingdom.educraft.powers.powers.PlayerTeleportPower;

public class PlayerTeleportPowerAddPage extends CompositeBoxPage {
	Group group;
	
	public PlayerTeleportPowerAddPage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("PlayerTeleportPower", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null, null
				,  ()->{
					PlayerTeleportPowerAddPage thisPage = PlayerTeleportPowerAddPage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		PlayerTeleportPowerAddContentPage contentPage = new PlayerTeleportPowerAddContentPage(
				group
				, student->{
					group.
					addPowers(
							new PlayerTeleportPower(
									student.
									getPlayer()));
					Player player = Menu.getMenu(this).getUser().getPlayer();
					if (player!=null) {
						player.sendMessage("PlayerTeleportPower has been created on player "+student.getName());}});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
