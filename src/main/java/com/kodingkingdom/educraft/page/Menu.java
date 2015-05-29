package com.kodingkingdom.educraft.page;

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

public class Menu extends BoxPage implements Listener{
	
	String menuName;
	int menuWidth,menuHeight;
		
	ItemStack menuIcon;
	Inventory menuMenu;	
	HashMap<Integer,MenuItem> itemMap;
	HashMap<MenuItem,Integer> slotMap;
	
	private Menu(){}
	
	static HashSet<Menu> menus=new HashSet<Menu>();
	
	public static Menu createMenu(int MenuWidth, int MenuHeight, String MenuName, ItemStack MenuIcon){
		Menu menu = new Menu();
		
		menu.menuName=MenuName;
		menu.menuWidth=MenuWidth;
		menu.menuHeight=MenuHeight;
		menu.menuIcon=MenuIcon;
		menu.menuMenu = Bukkit.createInventory(null, MenuWidth * MenuHeight, MenuName);
		EduCraftPlugin.debug("size of inv is "+menu.menuMenu.getSize());
		
		menu.itemMap = new HashMap<Integer,MenuItem>();
		menu.slotMap = new HashMap<MenuItem,Integer>();
		menu.menuItemsBox = new Box<MenuItem>(new MenuItem[MenuWidth][MenuHeight]);
		
		for (int widthX=0;widthX<MenuWidth;widthX++){
			for (int heightY=0;heightY<MenuHeight;heightY++){
				int slotNumber = menu.menuItemsBox.getSlotNumber(widthX, heightY);
				EduCraftPlugin.debug("slot Number is "+slotNumber);
				ItemStack itemIcon = menu.menuMenu.getItem(slotNumber);
				MenuItem item = menu.new MenuItem(itemIcon);
				menu.itemMap.put(slotNumber, item);
				menu.slotMap.put(item, slotNumber);
				
				menu.menuItemsBox.setBoxItem(widthX,heightY,item);

				menu.itemPageMap.put(item, menu);}}
		
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(menu);
		
		menus.add(menu);
		return menu;}
	
	public static void deleteMenu(Menu menu){
		menu.menuName=null;
		menu.menuWidth=menu.menuHeight=-1;
		menu.menuIcon=null;
		menu.menuMenu=null;	
		menu.itemMap=null;
		menu.slotMap=null;
		menus.remove(menu);}
	
	@EventHandler
	public void openMenu(PlayerInteractEvent e){
		if (e.getItem()!=null && e.getItem().equals(menuIcon)){
			e.getPlayer().openInventory(menuMenu);
			openPage();}}
	@EventHandler(priority=EventPriority.MONITOR)
	public void clickMenu(InventoryClickEvent e){
		if (menuMenu.equals(e.getClickedInventory())){
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
