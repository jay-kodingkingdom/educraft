package com.kodingkingdom.educraft.menu;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.group.User;
import com.kodingkingdom.educraft.menu.groups.GroupsPage;
import com.kodingkingdom.educraft.page.Menu;
import com.kodingkingdom.educraft.page.icons.Icon;

public class Bible {
	Menu bible;
	public ItemStack getIcon(){
		return menuIcon.clone();}
	final ItemStack menuIcon;
	public final static long pollInterval=8;
	
	public Bible(User user){
		menuIcon = Icon.makeIcon(Material.ENCHANTED_BOOK)
				.withName("Bible")
				.withCaption(UUID.randomUUID().toString())
				.asIcon();
		EduCraftPlugin.debug("menu icon is "+menuIcon);
		EduCraftPlugin.debug("menu icon clone is "+menuIcon.clone());
		bible=Menu.createMenu(9, 6, "Bible", menuIcon, user);
		EduCraftPlugin.debug("bible is "+bible);
		
		GroupsPage allGroupsPage = new GroupsPage(); 
		bible.attach(allGroupsPage.makePageConnector(bible.getSubBox(0, 0, 8, 5)));}}
