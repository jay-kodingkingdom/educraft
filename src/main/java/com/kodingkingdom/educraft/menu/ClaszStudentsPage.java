package com.kodingkingdom.educraft.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.kodingkingdom.educraft.Clasz;
import com.kodingkingdom.educraft.TeacherUser;
import com.kodingkingdom.educraft.User;

public class ClaszStudentsPage extends MenuPage{

	HashMap<Integer, MenuItem> pageItems;
	ArrayList<UUID>	studentList;
	
	
	Clasz clasz;
	int pageSize;
	int pageCount;
	int pageNum;
	
	public ClaszStudentsPage(Clasz clasZ, int PageSize){
		clasz=clasZ;pageSize=PageSize;refresh();}

	@Override
	public ItemStack getPageItem() {
		ItemStack item=new ItemStack(Material.STAINED_GLASS,1,DyeColor.RED.getData());ItemMeta itemMeta = item.getItemMeta();itemMeta.setDisplayName("Class Students");item.setItemMeta(itemMeta);
		return item;}
	
	@Override
	public HashMap<Integer, MenuItem> getPageItems() {
		flush();
		return pageItems;}

	@Override
	public void refresh() {
		pageNum=0;
		pageCount=(clasz.students.size()+pageSize-1)/pageSize;}

	@Override
	public void nextPage() {
		if (pageNum<pageCount)pageNum++;}

	@Override
	public void prevPage() {
		if (pageNum>0)pageNum--;}
	
	public void flush(){
		studentList=new ArrayList<UUID>();
		for (User student : clasz.students.values()){
			studentList.add(student.getID());}
		pageItems=new HashMap<Integer,MenuItem>();
		for (int i=0;i<pageSize&&i<studentList.size()-pageNum*pageSize;i++){
			Player player = Bukkit.getPlayer(studentList.get(i+pageNum*pageSize));
			pageItems.put(i, 
					new ClaszItem(this, player.getUniqueId()));}}
	
	class ClaszItem extends MenuItem{
		ClaszStudentsPage page;
		UUID uuid;
		@Override
		public void onClick(TeacherUser whoTeacher) {
			clasz.students.remove(uuid);}
		@Override
		public ItemStack getItem() {
			Player player = Bukkit.getPlayer(uuid);
			ItemStack item = new ItemStack(Material.SKULL_ITEM);
			item.setDurability((short)3);
			SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
			itemMeta.setOwner(player.getName());
			itemMeta.setDisplayName(player.getPlayerListName()); ArrayList<String> lore = new ArrayList<String>();
			lore.add("Click to remove this student from class");
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			return item;}
		ClaszItem(ClaszStudentsPage Page,UUID uUID){
			uuid=uUID;page=Page;}}}
