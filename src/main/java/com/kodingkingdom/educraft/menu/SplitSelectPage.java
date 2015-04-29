package com.kodingkingdom.educraft.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kodingkingdom.educraft.TeacherUser;

public class SplitSelectPage extends MenuPage{

	ArrayList<MenuPage>	selectionList;
	
	int selectionCount;
	int selectionNum;
	int selectionSize;
	int selectionHeight;
	int selectionWidth;
	

	MenuPage currentSelection=null;
	
	int pageSize;
	int pageHeight;
	int pageWidth;			
	
	boolean canFlipPage;
	boolean canFlipSelection;
	
	HashMap<Integer, MenuItem> menuItems;
	
	int menuSize;
	int menuHeight;
	int menuWidth;

	int selectionNumberToSlot(int selectionNumber){
		return (menuWidth*(selectionNumber/selectionWidth)+(selectionNumber%selectionWidth));}
	int pageNumberToSlot(int pageNumber){
		return (pageWidth*(pageNumber/pageWidth)+(pageNumber%pageWidth)+selectionWidth);}
	int slotToSelectionNumber(int slotNumber){
		return (selectionWidth*(slotNumber/menuWidth)+(slotNumber%menuWidth));}
	int slotToPageNumber(int slotNumber){
		return (pageWidth*(slotNumber/menuWidth)+(slotNumber%menuWidth)-selectionWidth);}
	boolean isSlotSelection(int slotNumber){
		return (slotNumber%menuWidth<selectionWidth);}
	public SplitSelectPage(Collection<MenuPage> selections, int MenuWidth, int MenuHeight, int SelectionWidth, boolean CanFlipPage, boolean CanFlipSelection){
		selectionHeight=pageHeight=menuHeight=MenuHeight;
		menuWidth=MenuWidth;menuSize=menuWidth*menuHeight;
		selectionWidth=SelectionWidth;selectionSize=selectionHeight*selectionWidth;
		pageWidth=menuWidth-selectionWidth;pageSize=pageHeight*pageWidth;
		
		canFlipPage = CanFlipPage;canFlipSelection=CanFlipSelection;
		menuItems= new HashMap<Integer, MenuItem>(); 
		
		selectionList = new ArrayList<MenuPage>(selections);
		selectionNum=0;
		selectionSize-=(canFlipPage?2:0)+(CanFlipSelection?2:0);
		selectionCount=(selectionList.size()+selectionSize-1)/selectionSize;
		
		flush();}	
	public MenuPage getSelection(){
		return currentSelection;}		
	@Override
	public ItemStack getPageItem() {
		ItemStack item=new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("Selection Menu");
		item.setItemMeta(itemMeta);
		return item;}		
	@Override
	public HashMap<Integer, MenuItem> getPageItems() {
		flush();
		return menuItems;}
	@Override
	public void refresh() {
		currentSelection=null;}
	@Override
	public void nextPage() {
		if (selectionNum<selectionCount-1)selectionNum++;}
	@Override
	public void prevPage() {
		if (selectionNum>0)selectionNum--;}
	public void flush(){
		menuItems.clear();
		flushSelection();flushPage();}
	void flushPage(){
		if (currentSelection==null)return;
		for (Map.Entry<Integer, MenuItem> entry : currentSelection.getPageItems().entrySet()){
			menuItems.put(pageNumberToSlot(entry.getKey()),entry.getValue());}}
	void flushSelection(){
		for (int i=0;i<selectionSize&&i<selectionList.size()-selectionNum*selectionSize;i++){
			menuItems.put(selectionNumberToSlot(i), new SelectionItem(this, selectionList.get(i+selectionNum*selectionSize)));}
		if (canFlipPage){
			menuItems.put(selectionNumberToSlot(selectionSize), new GeneralItem(new Runnable(){public void run(){currentSelection.prevPage();flush();}},Material.STAINED_GLASS_PANE,DyeColor.GRAY.getData(), "Previous Page"));
			menuItems.put(selectionNumberToSlot(selectionSize+1),new GeneralItem(new Runnable(){public void run(){currentSelection.nextPage();flush();}},Material.STAINED_GLASS_PANE,DyeColor.WHITE.getData(), "Next Page"));}
		if (canFlipSelection){
			menuItems.put(selectionNumberToSlot(selectionWidth*selectionHeight-2),new GeneralItem(new Runnable(){public void run(){prevPage();flush();}},Material.STAINED_GLASS_PANE,DyeColor.GRAY.getData(), "Previous Selections"));
			menuItems.put(selectionNumberToSlot(selectionWidth*selectionHeight-1), new GeneralItem(new Runnable(){public void run(){nextPage();flush();}},Material.STAINED_GLASS_PANE,DyeColor.WHITE.getData(), "Next Selections"));}}
	
	class SelectionItem extends MenuItem{
		SplitSelectPage splitSelectPage;
		MenuPage page;
		@Override
		public ItemStack getItem() {return page.getPageItem();}
		@Override
		public void onClick(TeacherUser whoTeacher) {
			page.refresh();
			splitSelectPage.currentSelection=page;
			splitSelectPage.flush();}
		SelectionItem(SplitSelectPage SplitSelectPage, MenuPage Page){
			splitSelectPage=SplitSelectPage;page=Page;}}}