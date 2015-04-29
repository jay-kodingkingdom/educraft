package com.kodingkingdom.educraft.assessment.fillintheblanks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.Clasz;
import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.TeacherUser;
import com.kodingkingdom.educraft.assessment.Assessment;
import com.kodingkingdom.educraft.assessment.TestMaker;

public class FillBlankMaker extends TestMaker{

	Clasz clasz;
	
	Object obj = new Object();
	ReentrantLock lock = new ReentrantLock();

	FillBlankWorkSheet newWorkSheet=null;
	String name;

	public FillBlankMaker(Clasz clasZ){clasz=clasZ;name="Make Fill-in-the-Blank Worksheets";}
		
	
	private final Pattern validbracks=Pattern.compile("(\\{\\})*");
	FillBlankWorkSheet makeWorkSheet(String Name, String Text){
		String bracks = Text.replaceAll("[^\\{\\}]", "");
		if (!validbracks.matcher(bracks).matches())throw new IllegalArgumentException();
		
		final HashMap<Integer,Pattern> pageFormat=new HashMap<Integer,Pattern>();
		final ArrayList<String> pages = new ArrayList<String>();
		
		final int maxLetters = 192;
		StringBuilder pageBuilder = new StringBuilder();char ch;boolean inBracks=false;int textdist=0,sentdist=0,phrdist=0,commdist=0,worddist=0;
		for (int i=0; i < Text.length(); i++){
			ch=Text.charAt(i);textdist++;sentdist++;phrdist++;commdist++;worddist++;
			if (ch=='{'){pageBuilder.append("§k");inBracks=true;continue;}
			if (ch=='}'){pageBuilder.append("§r");
				int sectionLength=(textdist<=maxLetters?textdist:(sentdist<=maxLetters?sentdist:(phrdist<=maxLetters?phrdist:(commdist<=maxLetters?commdist:(worddist<=maxLetters?worddist:-1)))));if (sectionLength==-1) throw new IllegalArgumentException();
				if (textdist>maxLetters){
					String page = pageBuilder.toString();
					pages.add(page.substring(0, textdist-sectionLength));pageFormat.put(pages.size(),Pattern.compile(Pattern.quote(page.substring(0, textdist-sectionLength))));
					pageBuilder.setLength(0);pageBuilder.append(page.substring(textdist-sectionLength));}
				pageBuilder.append("\n§r\nBlanks:\n§r");
				pages.add(pageBuilder.toString());
				pageFormat.put(pages.size(),Pattern.compile(Pattern.quote(pageBuilder.toString())+"(.*)"));
				pageBuilder.setLength(0);inBracks=false;textdist=sentdist=phrdist=commdist=worddist=0;continue;}
			pageBuilder.append(ch);
			if (!inBracks) {
				if (textdist>=255){
					pages.add(pageBuilder.toString());
					pageBuilder.setLength(0);textdist=sentdist=phrdist=commdist=worddist=0;}
				if (ch=='.'||ch=='?'||ch=='!')sentdist=0;
				if (ch=='"'||ch==';')phrdist=0;
				if (ch==',')commdist=0;
				if (ch==' ')worddist=0;}}
		if (textdist>0){
			pages.add(pageBuilder.toString());
			pageFormat.put(pages.size(),Pattern.compile(Pattern.quote(pageBuilder.toString())));}
		
		final String name=Name;
		//final String text = Text.replaceAll("\\{", "§k").replaceAll("\\}", "§r");
		
		return new FillBlankWorkSheet(name, pages, pageFormat, null);}

	
	
	@Override
	public Callable<Assessment> makeTest(final TeacherUser teacher) {
		return new Callable<Assessment>(){
			@Override
			public Assessment call() throws Exception {
				synchronized(obj){
					EduCraftPlugin.getPlugin().getEduCraft().registerEvents(new Listener(){
						@EventHandler
						public void makeFillBlanks(PlayerEditBookEvent e){
							if (!e.getPlayer().equals(teacher.getPlayer())) return;
							if (!e.isSigning())return;
							
							String text = "";for (int i =1; i<=e.getNewBookMeta().getPageCount();i++){
								text = text+e.getNewBookMeta().getPage(i);}
							try {	
								synchronized(obj){
									newWorkSheet=makeWorkSheet(e.getNewBookMeta().getTitle(), text);
									obj.notifyAll();
									e.getPlayer().sendMessage("SUCCESS: Worksheet created");
									PlayerEditBookEvent.getHandlerList().unregister(this);}}
							catch(Exception ex){
								e.getPlayer().sendMessage("ERROR: Worksheet bad formatting");
								e.getPlayer().sendMessage("ERROR: Perhaps {} braces not closed?");}
							
							e.setSigning(false);}});
					teacher.giveItem(new ItemStack(Material.BOOK_AND_QUILL));
					teacher.getPlayer().sendMessage("Sign a book to make a Fill-in-the-Blanks assessment");
					while(newWorkSheet==null){obj.wait();}
					Assessment assessment = new FillBlankAssessment(FillBlankMaker.this, newWorkSheet.name, newWorkSheet);
					newWorkSheet=null;
					return assessment;}}};}
	@Override
	public String getName() {
		return name;}}
