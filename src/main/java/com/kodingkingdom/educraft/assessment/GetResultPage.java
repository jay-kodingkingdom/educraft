package com.kodingkingdom.educraft.assessment;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kodingkingdom.educraft.Clasz;
import com.kodingkingdom.educraft.TeacherUser;
import com.kodingkingdom.educraft.menu.MenuItem;
import com.kodingkingdom.educraft.menu.MenuPage;

public class GetResultPage extends MenuPage{

	HashMap<Integer, MenuItem> pageItems;
	Clasz clasz; 
	
	int pageSize;
	int pageCount;
	int pageNum;
	
	public GetResultPage(Clasz clasZ,int PageSize){
		clasz=clasZ;pageSize=PageSize;refresh();}	
	@Override
	public HashMap<Integer, MenuItem> getPageItems() {
		flush();
		return pageItems;}
	@Override
	public ItemStack getPageItem() {
		ItemStack item=new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("Mark Assessment");
		item.setItemMeta(itemMeta);
		return item;}
	@Override
	public void flush() {
		ArrayList<Assessment> assessments=new ArrayList<Assessment> (clasz.assessments);
		pageItems=new HashMap<Integer,MenuItem>();
		for (int i=0;i<pageSize&&i<assessments.size()-pageNum*pageSize;i++){
			pageItems.put(i, new ResultItem(assessments.get(i)));}}
	@Override
	public void refresh() {
		pageNum=0;pageCount=(clasz.assessments.size()+pageSize-1)/pageSize;}
	@Override
	public void nextPage() {
		if (pageNum<pageCount)pageNum++;}
	@Override
	public void prevPage() {
		if (pageNum>0)pageNum--;}
	public class ResultItem extends MenuItem{
		Assessment assessment;
		ResultItem(Assessment assessmenT){assessment=assessmenT;}
		@Override
		public void onClick(TeacherUser whoTeacher) {
			assessment.Mark();
			whoTeacher.giveItem(assessment.getResultItem());
			whoTeacher.getPlayer().sendMessage("Got results for "+assessment.getName());}
		public ItemStack getItem() {
			ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
			ItemMeta itemMeta=item.getItemMeta();
			itemMeta.setDisplayName(assessment.getName()); ArrayList<String> lore = new ArrayList<String>();
			lore.add("Click to mark assessment");
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			return item;}}}
