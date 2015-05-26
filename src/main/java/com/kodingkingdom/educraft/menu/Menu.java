package com.kodingkingdom.educraft.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.EduCraftPlugin;

public class Menu extends Page implements Listener{
	
	String menuName;
	int menuWidth,menuHeight;
		
	ItemStack menuIcon;
	Inventory menuMenu;	
	HashMap<Integer,MenuItem> itemMap;
	HashMap<MenuItem,Integer> slotMap;
	
	private int getSlotNumber(int widthX, int heightY){
		return (widthX - 1) +
				(heightY - 1) * menuWidth;}

	public MenuItem getItem(int widthX, int heightY){
		return itemMap.get(getSlotNumber(widthX, heightY));}
	public Collection<MenuItem> getItems(int widthX1, int heightY1, int widthX2, int heightY2){
		HashSet<MenuItem> items = new HashSet<MenuItem>(); 
		for (int widthX=(widthX1<widthX2?widthX1:widthX2);widthX<(widthX1>widthX2?widthX1:widthX2);widthX++){
			for (int heightY=(heightY1<heightY2?heightY1:heightY2);heightY<(heightY1>heightY2?heightY1:heightY2);heightY++){
				items.add(itemMap.get(getSlotNumber(widthX, heightY)));}}
		return items;}
	
	public Menu(int MenuWidth, int MenuHeight, String MenuName, ItemStack MenuIcon){
		menuName=MenuName;
		menuWidth=MenuWidth;
		menuHeight=MenuHeight;
		menuIcon=MenuIcon;
		menuMenu = Bukkit.createInventory(null, menuWidth * menuHeight, menuName);
		itemMap = new HashMap<Integer,MenuItem>();
		slotMap = new HashMap<MenuItem,Integer>();
		for (int widthX=0;widthX<menuWidth;widthX++){
			for (int heightY=0;heightY<menuHeight;heightY++){
				int slotNumber = getSlotNumber(widthX, heightY);
				ItemStack itemIcon = menuMenu.getItem(slotNumber);
				MenuItem item = new MenuItem(itemIcon);
				itemMap.put(slotNumber, item);
				slotMap.put(item, slotNumber);}}
		
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(this);}
	
	@EventHandler
	public void openMenu(PlayerInteractEvent e){
		if (e.getItem()!=null && e.getItem().equals(menuIcon)){
			e.getPlayer().openInventory(menuMenu);
			openPage();}}
	@EventHandler(priority=EventPriority.MONITOR)
	public void clickMenu(InventoryClickEvent e){
		if (e.getInventory().equals(menuMenu)){
			e.setCancelled(true);
			if (e.getCursor()==null)return;
			clickItem(itemMap.get(e.getRawSlot()));}}
	@EventHandler(priority=EventPriority.MONITOR)
	public void closeMenu(InventoryCloseEvent e){
		if (e.getInventory().equals(menuMenu)){
			closePage();}}
	
	public class MenuItem {
		private ItemStack itemIcon;
		
		MenuItem(ItemStack ItemIcon){
			itemIcon=ItemIcon;}
		
		public ItemStack getIcon(){
			return itemIcon;}
		
		public void setIcon(ItemStack ItemIcon){
			itemIcon = ItemIcon;
			Menu.this.menuMenu.setItem(Menu.this.slotMap.get(this), itemIcon);}}}
