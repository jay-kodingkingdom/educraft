package com.kodingkingdom.educraft.assessment;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.kodingkingdom.educraft.Clasz;
import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.TeacherUser;
import com.kodingkingdom.educraft.menu.MenuItem;
import com.kodingkingdom.educraft.menu.MenuPage;

public class CopyTestPage extends MenuPage{

	HashMap<Integer, MenuItem> pageItems;
	Clasz clasz; 
	
	int pageSize;
	int pageCount;
	int pageNum;
	
	public CopyTestPage(Clasz clasZ,int PageSize){
		clasz=clasZ;pageSize=PageSize;
		refresh();}
	@Override
	public HashMap<Integer, MenuItem> getPageItems() {
		flush();
		return pageItems;}
	@Override
	public ItemStack getPageItem() {
		ItemStack item=new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("Duplicate existing Assessments");
		item.setItemMeta(itemMeta);
		return item;}
	@Override
	public void flush() {
		ArrayList<Assessment> assessments=new ArrayList<Assessment> (clasz.assessments);
		pageCount=(clasz.testMakers.size()+pageSize-1)/pageSize;
		pageItems=new HashMap<Integer,MenuItem>();
		for (int i=0;i<pageSize&&i<assessments.size()-pageNum*pageSize;i++){
			pageItems.put(i, new CopyAssessmentItem(assessments.get(i), clasz));}}
	@Override
	public void refresh() {pageNum=0;}
	@Override
	public void nextPage() {
		if (pageNum<pageCount)pageNum++;}
	@Override
	public void prevPage() {
		if (pageNum>0)pageNum--;}
	public class CopyAssessmentItem extends MenuItem{
		Assessment assessment;
		Clasz clasz;
		CopyAssessmentItem(Assessment Assessment, Clasz clasZ){assessment=Assessment;clasz=clasZ;}
		@Override
		public void onClick(final TeacherUser whoTeacher) {
			EduCraftPlugin.getPlugin().getEduCraft().runAsync
			(new BukkitRunnable(){public void run() {try {
					clasz.assessments.add(new CopyTestMaker(assessment).makeTest(whoTeacher).call());}catch (Exception e) {}}});
			whoTeacher.getPlayer().sendMessage("Duplicated "+assessment.getName()+"...");}
		public ItemStack getItem() {
			ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
			ItemMeta itemMeta=item.getItemMeta();
			itemMeta.setDisplayName(assessment.getName()); ArrayList<String> lore = new ArrayList<String>();
			lore.add("Click to duplicate this assessments");
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			return item;}}}
