
package com.kodingkingdom.educraft.menu.groups.add;

import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.add.GroupAddContentPage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.VaryNamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.Menu;

public class GroupAddPage extends CompositeBoxPage {
	String groupName="";
	protected void compositeAttachedAction(Connector connector){
		VaryNamePage namePage = new VaryNamePage(()->{return groupName;}, getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					GroupAddPage thisPage = GroupAddPage.this;
					thisPage.remove();}
				, null, null, null, null
				,  ()->{
					groupName=(!groupName.equals("")?groupName.substring(0, groupName.length()-1):"");}, null
				, ()->{
					GroupAddPage thisPage = GroupAddPage.this;
					if (!groupName.equals("") &&
							!Group.getGroups().stream()
							.anyMatch(
									(Group group) -> {
										return groupName.equals(group.getName());})){
						
						Group.create(groupName);
						Player player = Menu.getMenu(thisPage).getUser().getPlayer();
						player.sendMessage("Group "+groupName+" has been created!");
						thisPage.remove();}
					else {
						Player player = Menu.getMenu(thisPage).getUser().getPlayer();
						player.sendMessage("Group "+groupName+" already exists!");}});
		GroupAddContentPage contentPage = new GroupAddContentPage(
				letter->{
					String newGroupName = groupName + letter;
					if (newGroupName.length()<=getHeight()) groupName=newGroupName;}
				, getWidth()-1, getHeight()-1);
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
