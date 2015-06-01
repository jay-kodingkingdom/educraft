package com.kodingkingdom.educraft.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.menu.allgroups.AllGroupsPage;
import com.kodingkingdom.educraft.page.Menu;
import com.kodingkingdom.educraft.page.icons.Icon;

public class TeacherMenu {
	public Menu teacherMenu;
	public ItemStack menuIcon;
	public static long pollInterval=8;
	public static ItemStack groupsIcon=
			Icon.makeIcon(Material.ENCHANTED_BOOK)
			.withName("All of this")
			.asIcon();
	
	public TeacherMenu(){
		menuIcon = Icon.makeIcon(Material.ENCHANTED_BOOK)
				.withName("Teacher Menu")
				.asIcon();
		teacherMenu=Menu.createMenu(9, 6, "TeacherMenu", menuIcon);
		
		AllGroupsPage allGroupsPage = new AllGroupsPage(); 
		teacherMenu.attach(allGroupsPage.makePageConnector(teacherMenu.getSubBox(0, 0, 8, 5)));}}
