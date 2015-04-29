package com.kodingkingdom.educraft.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.TeacherUser;

public class WholeSelectPage extends MenuPage{

	String description;

	ItemStack pageStack;
	HashMap<Integer, MenuItem> pageItems;
	ArrayList<MenuPage>	selectionList;

	ArrayList<Map.Entry<Integer, MenuItem>> currentSelectionItems;
	
	MenuPage currentSelection=null;
		
	int pageSize;
	int pageCount;
	int pageNum;
	
	public WholeSelectPage(Collection<MenuPage> selections, String Description, int PageSize){
		description=Description;
		currentSelectionItems = new ArrayList<Map.Entry<Integer, MenuItem>>();
		selectionList=new ArrayList<MenuPage>(selections);pageSize=PageSize;
		refresh();}

	public MenuPage getSelection(){
		return currentSelection;}		
	@Override
	public ItemStack getPageItem() {
		ItemStack item=new ItemStack(Material.WRITTEN_BOOK);ItemMeta itemMeta = item.getItemMeta();itemMeta.setDisplayName(description);item.setItemMeta(itemMeta);
		return item;}		
	@Override
	public HashMap<Integer, MenuItem> getPageItems() {
		flush();
		return pageItems;}
	@Override
	public void refresh() {
		currentSelection=null;pageNum=0;pageCount=(selectionList.size()+pageSize-1)/pageSize;}
	@Override
	public void nextPage() {
		if (pageNum<pageCount-1){
			pageNum++;}}
	@Override
	public void prevPage() {
		if (pageNum>0){
			pageNum--;}}
	public void flush(){
		pageItems=new HashMap<Integer,MenuItem>();
		if (currentSelection==null){
			for (int i=0;i<pageSize&&i<selectionList.size()-pageNum*pageSize;i++){
				pageItems.put(i, new AssignmentItem(this, selectionList.get(i+pageNum*pageSize)));}}
		else {
			currentSelectionItems=new ArrayList<Map.Entry<Integer, MenuItem>> (currentSelection.getPageItems().entrySet());
			for (int i=0;i<pageSize&&i<currentSelectionItems.size()-pageNum*pageSize;i++){
				pageItems.put(currentSelectionItems.get(i+pageNum*pageSize).getKey(),
						currentSelectionItems.get(i+pageNum*pageSize).getValue());}}}	
	class AssignmentItem extends MenuItem{
		WholeSelectPage wholeSelectPage;
		MenuPage page;
		@Override
		public void onClick(TeacherUser whoTeacher) {
			EduCraftPlugin.debug("assignitem action called");
			page.refresh();
			wholeSelectPage.currentSelection=page;}
		@Override
		public ItemStack getItem() {
			return page.getPageItem();}
		AssignmentItem(WholeSelectPage WholeSelectPage, MenuPage Page){
			wholeSelectPage=WholeSelectPage;page=Page;}}
	











	public void sendDebugMessage() {
		EduCraftPlugin.debug("\n\n\ndebug: sneding wholeselectpage debug info");
		EduCraftPlugin.debug("debug: sellist size:"+selectionList.size());
		EduCraftPlugin.debug("debug: is selection null?"+(currentSelection==null));
	if (currentSelection!=null){	EduCraftPlugin.debug("debug: printing selection items");
		for (Map.Entry<Integer, MenuItem> entry: currentSelection.getPageItems().entrySet()){
			EduCraftPlugin.debug("debug: element id is"+entry.getKey()+"type is"+entry.getValue());}}
		
		EduCraftPlugin.debug("debug: pringing pageitem contents");
		for (Map.Entry<Integer, MenuItem> entry: pageItems.entrySet()){
			EduCraftPlugin.debug("debug: element id is"+entry.getKey()+"type is"+entry.getValue());}}
	}