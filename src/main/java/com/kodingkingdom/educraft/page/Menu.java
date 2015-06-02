package com.kodingkingdom.educraft.page;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.group.User;

public class Menu extends CompositeBoxPage implements Listener{
	
	String menuName;
	int menuWidth,menuHeight;
		
	ItemStack menuIcon;
	Inventory menuMenu;	
	HashMap<Integer,MenuItem> itemMap;
	HashMap<MenuItem,Integer> slotMap;
	
	User menuUser;
	
	private Menu(){}
	
	static HashSet<Menu> menus=new HashSet<Menu>();
	
	public static Menu createMenu(int MenuWidth, int MenuHeight, String MenuName, ItemStack MenuIcon, User MenuUser){
		Menu menu = new Menu();
		
		menu.menuName=MenuName;
		menu.menuWidth=MenuWidth;
		menu.menuHeight=MenuHeight;
		menu.menuIcon=MenuIcon;
		menu.menuUser=MenuUser;
		menu.menuMenu = Bukkit.createInventory(null, MenuWidth * MenuHeight, MenuName);
		EduCraftPlugin.debug("size of inv is "+menu.menuMenu.getSize());
		
		menu.itemMap = new HashMap<Integer,MenuItem>();
		menu.slotMap = new HashMap<MenuItem,Integer>();
		menu.menuItemsBox = menu.new Box<MenuItem>(new MenuItem[MenuHeight][MenuWidth]);
		
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
		if (e.getPlayer().isOp() &&
				e.getPlayer().getUniqueId().equals(menuUser.getId()) &&
				e.getItem()!=null && e.getItem().equals(menuIcon)){
			e.getPlayer().openInventory(menuMenu);
			openPage();}}
	@EventHandler(priority=EventPriority.MONITOR)
	public void clickMenu(InventoryDragEvent e){
		if (menuMenu.equals(e.getInventory())){
			e.setCancelled(true);}}
	@EventHandler(priority=EventPriority.MONITOR)
	public void clickMenu(InventoryClickEvent e){
		if (menuMenu.equals(e.getClickedInventory())){
			e.setCancelled(true);
			clickItem(normalize(itemMap.get(e.getRawSlot())));}}
	//@EventHandler(priority=EventPriority.MONITOR)
	public void closeMenu(InventoryCloseEvent e){
		if (e.getInventory().equals(menuMenu)){
			closePage();}}

	public MenuItem normalize(MenuItem menuItem){
		if (menuItem==null) return Null;
		else return menuItem;}
	
	public final MenuItem Null = new MenuItem(null);
	
	public User getUser(){
		return menuUser;}
	
	public static final Menu getMenu(Page page){
		return page.itemPageMap.keySet().iterator().next().getMenu();}
	
	public class MenuItem {
		private HashMap<Page,ItemStack> ownerIconMap;
		
		private Page owner;
		
		Menu getMenu(){
			return Menu.this;}
		
		Page getOwner(){
			return owner;}
		
		void setOwner(Page Owner){
			if (!ownerIconMap.containsKey(Owner)){
				ownerIconMap.put(Owner,getIcon());}
			owner=Owner;
			update();}
		
		MenuItem(ItemStack ItemIcon){
			owner = Menu.this;
			ownerIconMap=new HashMap<Page,ItemStack>();
			ownerIconMap.put(owner,ItemIcon);}
		
		public ItemStack getIcon(){
			return ownerIconMap.get(getOwner());}
		
		void setIcon(ItemStack ItemIcon){
			ownerIconMap.replace(getOwner(),ItemIcon);
			update();}

		void setIcon(Page Owner, ItemStack ItemIcon){
			ownerIconMap.replace(Owner,ItemIcon);
			if (Owner==getOwner()) update();}
		
		private void update(){
			Menu thisMenu=getMenu();
			thisMenu.menuMenu.setItem(thisMenu.slotMap.get(this), getIcon());
			for (Player player : thisMenu.menuMenu.getViewers().stream()
									.filter(entity -> entity instanceof Player)
									.map(entity -> (Player)entity)
									.collect(Collectors.toList())){
				player.updateInventory();}
			//EduCraftPlugin.debug("icon is "+getIcon());
			}}}