package com.kodingkingdom.educraft.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kodingkingdom.educraft.Clasz;
import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.TeacherUser;
import com.kodingkingdom.educraft.assessment.AdministerPage;
import com.kodingkingdom.educraft.assessment.CopyTestPage;
import com.kodingkingdom.educraft.assessment.MakeTestPage;
import com.kodingkingdom.educraft.assessment.GetResultPage;
import com.kodingkingdom.educraft.assessment.RemoveAssessmentPage;


public class TeacherMenu extends MenuPage implements Listener{
	Inventory menuInventory;
		
	SplitSelectPage selection;	
	Clasz clasz;
	
	TeacherUser teacher;
	
	int menuSize;
	int menuHeight;
	int menuWidth;
	int selectionSize;
	int selectionHeight;
	int selectionWidth;
	int pageSize;
	int pageHeight;
	int pageWidth;	
	
	private void setMenuParams(){
		menuSize=9*6;menuWidth=9;menuHeight=(menuSize+menuWidth-1)/menuWidth;
		selectionWidth = menuWidth/2;pageWidth = menuWidth-selectionWidth;pageHeight=selectionHeight=menuHeight;pageSize=pageWidth*pageHeight;selectionSize=selectionWidth*selectionHeight;}
	public TeacherMenu(Clasz clasZ,TeacherUser Teacher){		
		clasz=clasZ;teacher=Teacher;
		menuInventory=Bukkit.createInventory(null, 54, "Teacher Menu");

		setMenuParams();
		
		ArrayList<MenuPage> pages = new ArrayList<MenuPage>();
		pages.add(new MakeTestPage(clasz, pageSize));
		pages.add(new AdministerPage(clasz, pageSize));
		pages.add(new GetResultPage(clasz, pageSize));
		pages.add(new CopyTestPage(clasz, pageSize));
		pages.add(new RemoveAssessmentPage(clasz, pageSize));		
		WholeSelectPage assesses= new WholeSelectPage(pages, "Assessments",pageSize);		
		pages.clear();
		pages.add(new ClaszStudentsPage(clasz,pageSize));
		pages.add(new AllPlayersPage(clasz,pageSize));
		pages.add(assesses);		
		selection=new SplitSelectPage(pages, menuWidth, menuHeight, selectionWidth, true, false);
				
		teacher.giveItem(getPageItem());		
		refresh();
		
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(this);}
	@EventHandler
	public void openMenu(PlayerInteractEvent e){
		if (!e.getAction().equals(Action.RIGHT_CLICK_AIR)&&!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
		if (e.getItem()==null) return;		
		if (!e.getItem().equals(getPageItem()))return;
		refresh();
		flush();
		e.getPlayer().openInventory(menuInventory);}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void clickMenu(InventoryClickEvent e){
		if (!e.getInventory().getName().equals(menuInventory.getName()))return;
		e.setCancelled(true);
		if (e.getCursor()==null)return;

		int slot = e.getRawSlot();
		Map<Integer, MenuItem> pageItems = getPageItems();int selNum =selection.slotToSelectionNumber(slot); 
		TeacherUser whoTeacher=clasz.teachers.get(e.getWhoClicked().getUniqueId());

		if (pageItems.containsKey(selNum)) {
			EduCraftPlugin.debug("debug: clicking "+selNum);
			pageItems.get(selNum).onClick(whoTeacher);}
		
		flush();}
	@Override
	public HashMap<Integer, MenuItem> getPageItems() {
		return selection.getPageItems();}
	@Override
	public ItemStack getPageItem() {
		ItemStack item=new ItemStack(Material.BOOK);
		ItemMeta itemMeta=item.getItemMeta();
		itemMeta.setDisplayName("Teacher Menu");
		item.setItemMeta(itemMeta);
		return item;}
	@Override
	public void flush() {
		menuInventory.clear();
		for (Map.Entry<Integer,MenuItem> entry : selection.getPageItems().entrySet()){
			if (entry.getValue()!=null) menuInventory.setItem(entry.getKey(),entry.getValue().getItem());}	}
	@Override
	public void refresh() {
		selection.refresh();}
	@Override
	public void nextPage() {
		selection.nextPage();}
	@Override
	public void prevPage() {
		selection.prevPage();}
	


}
