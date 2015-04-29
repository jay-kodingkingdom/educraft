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

public class MakeTestPage extends MenuPage{

	HashMap<Integer, MenuItem> pageItems;
	Clasz clasz; 
	
	int pageSize;
	int pageCount;
	int pageNum;
	
	public MakeTestPage(Clasz clasZ,int PageSize){
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
		itemMeta.setDisplayName("Make new Assessments");
		item.setItemMeta(itemMeta);
		return item;}
	@Override
	public void flush() {
		ArrayList<TestMaker> testMakers=new ArrayList<TestMaker> (clasz.testMakers);
		pageCount=(clasz.testMakers.size()+pageSize-1)/pageSize;
		pageItems=new HashMap<Integer,MenuItem>();
		for (int i=0;i<pageSize&&i<testMakers.size()-pageNum*pageSize;i++){
			pageItems.put(i, new MakeAssessmentItem(testMakers.get(i), clasz));}}
	@Override
	public void refresh() {pageNum=0;}
	@Override
	public void nextPage() {
		if (pageNum<pageCount)pageNum++;}
	@Override
	public void prevPage() {
		if (pageNum>0)pageNum--;}
	public class MakeAssessmentItem extends MenuItem{
		TestMaker testMaker;
		Clasz clasz;
		MakeAssessmentItem(TestMaker testMakeR, Clasz clasZ){testMaker=testMakeR;clasz=clasZ;}
		@Override
		public void onClick(final TeacherUser whoTeacher) {
			EduCraftPlugin.getPlugin().getEduCraft().runAsync
			(new BukkitRunnable(){public void run() {try {
					clasz.assessments.add(testMaker.makeTest(whoTeacher).call());}catch (Exception e) {}}});
			whoTeacher.getPlayer().sendMessage("Making "+testMaker.getName()+"...");}
		public ItemStack getItem() {
			ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
			ItemMeta itemMeta=item.getItemMeta();
			itemMeta.setDisplayName(testMaker.getName()); ArrayList<String> lore = new ArrayList<String>();
			lore.add("Click to make new assessments");
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			return item;}}}
