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
import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.StudentUser;
import com.kodingkingdom.educraft.TeacherUser;
import com.kodingkingdom.educraft.User;

public class AllPlayersPage extends MenuPage{

	HashMap<Integer, MenuItem> pageItems;
	ArrayList<UUID> playerList;
	
	
	Clasz clasz;
	int pageSize;
	int pageCount;
	int pageNum;
	
	public AllPlayersPage(Clasz clasZ, int PageSize){
		clasz=clasZ;pageSize=PageSize;refresh();}

	@Override
	public ItemStack getPageItem() {
		ItemStack item=new ItemStack(Material.STAINED_GLASS,1,DyeColor.BLACK.getData());ItemMeta itemMeta = item.getItemMeta();itemMeta.setDisplayName("All Players");item.setItemMeta(itemMeta);
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
		playerList=new ArrayList<UUID>();
		for (Player player : Bukkit.getOnlinePlayers()){
			playerList.add(player.getUniqueId());}
		pageItems=new HashMap<Integer,MenuItem>();
		for (int i=0;i<pageSize&&i<playerList.size()-pageNum*pageSize;i++){
			Player player = Bukkit.getPlayer(playerList.get(i+pageNum*pageSize));
			pageItems.put(i, 
					new PlayerItem(this, player.getUniqueId()));}}
	
	class PlayerItem extends MenuItem{
		AllPlayersPage page;
		UUID uuid;
		@Override
		public void onClick(TeacherUser whoTeacher) {
			for (User student : clasz.students.values()){
				if (student.getPlayer().getUniqueId().equals(uuid))return;}
				clasz.students.put(uuid,new StudentUser(uuid));}
		@Override
		public ItemStack getItem() {
			EduCraftPlugin.debug(this.toString());
			Player player = Bukkit.getPlayer(uuid);
			ItemStack item = new ItemStack(Material.SKULL_ITEM);
			item.setDurability((short)3);
			SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
			itemMeta.setOwner(player.getName());
			itemMeta.setDisplayName(player.getPlayerListName()); ArrayList<String> lore = new ArrayList<String>();
			lore.add("Click to add this student to class");
			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			return item;}
		PlayerItem(AllPlayersPage Page,UUID uUID){
			uuid=uUID;page=Page;}}}