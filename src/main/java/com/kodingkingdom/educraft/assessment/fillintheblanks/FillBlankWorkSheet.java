package com.kodingkingdom.educraft.assessment.fillintheblanks;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.StudentUser;
import com.kodingkingdom.educraft.assessment.Result;
import com.kodingkingdom.educraft.assessment.WorkSheet;

public class FillBlankWorkSheet extends WorkSheet implements Listener {

	String name;
	ArrayList<String> pages;
	HashMap<Integer, Pattern> pageFormat;
	StudentUser student;
	ItemStack item;
	

	@Override
	public StudentUser getStudent() {
		return student;}
	void setStudent(StudentUser Student){student=Student;}
	FillBlankWorkSheet(String Name, ArrayList<String> Pages, HashMap<Integer,Pattern> PageFormat, StudentUser Student){
		name=Name;pages=new ArrayList<String>(Pages);pageFormat=new HashMap<Integer,Pattern>(PageFormat);student=Student;
		item = new ItemStack(Material.BOOK_AND_QUILL);BookMeta book = (BookMeta)item.getItemMeta();book.setPages(pages);item.setItemMeta(book);}
	@Override
	public FillBlankWorkSheet getClone() {
		FillBlankWorkSheet clone = new FillBlankWorkSheet(name,pages,pageFormat,student);
		return clone;}
	
	@Override
	public void begin() {
		student.giveItem(item);
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(this);}
	@Override
	public void end() {PlayerEditBookEvent.getHandlerList().unregister(this);}

	private final Pattern replyFormat=Pattern.compile("§k([^§]*)§r");
	@Override
	public Result getResult() {
		FillBlankResult result = new FillBlankResult(this);
		String text="";for(int pageNum=0;pageNum<pages.size();pageNum++){text=text+pages.get(pageNum);}result.marks=0;result.total = 0;Matcher reply= replyFormat.matcher(text);
			for (Map.Entry<Integer,Pattern> entry : pageFormat.entrySet()){
				if (entry.getValue().pattern().endsWith("(.*)")){
					reply.find();result.total++;		
					Matcher answer = entry.getValue().matcher(pages.get(entry.getKey()-1));
					answer.lookingAt();result.records.add(new 						AbstractMap.SimpleEntry<Boolean,Entry<String,String>>(
							reply.group(1).equals(answer.group(1)							),new  AbstractMap.SimpleEntry<String,String>
							(reply.group(1),answer.group(1))));
					if (reply.group(1).equals(answer.group(1))) result.marks++;}}
		return result;}
	@EventHandler
	public void editBook(PlayerEditBookEvent e){ 
		if (!e.getPlayer().equals(student.getPlayer())) return;
		if (!e.getPreviousBookMeta().equals(item.getItemMeta())) return;
		for (Map.Entry<Integer,Pattern> entry : pageFormat.entrySet()){
			if (entry.getKey()>e.getNewBookMeta().getPageCount()){
				e.getPlayer().sendMessage("ERROR: You are only allowed to fill in blanks");
				e.setCancelled(true);return;}
			if (!entry.getValue().matcher(e.getNewBookMeta().getPage(entry.getKey())).matches()){
				e.getPlayer().sendMessage("ERROR: You are only allowed to fill in blanks");
				e.setCancelled(true);return;}}
		if (e.isSigning()){
			BookMeta book = e.getNewBookMeta(); 
			book.setTitle(name);
			book.setDisplayName("Worksheet "+name);
			e.setNewBookMeta(book);
			pages = new ArrayList<String>(book.getPages());
			end();}}}
