package com.kodingkingdom.educraft.assessment.questionandanswer;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
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

public class QuestionAnswerMaker extends TestMaker{

	Clasz clasz;
	
	Object obj = new Object();
	ReentrantLock lock = new ReentrantLock();

	QuestionAnswerWorkSheet newWorkSheet=null;
	String name;

	public QuestionAnswerMaker(Clasz clasZ){clasz=clasZ;name="Make Interactive Q&A Worksheets";}
		

	private final Pattern validbracks=Pattern.compile("(\\{\\}|\\[\\])*");
	private final Pattern speechPattern=Pattern.compile("\\[([^\\{\\}\\[\\]]*)(\\])|\\{([^\\{\\}\\[\\]]*)(\\})|([^\\{\\}\\[\\]]*)()");
	QuestionAnswerWorkSheet makeWorkSheet(String Name, String Text){
		String bracks = Text.replaceAll("[^\\{\\}\\[\\]]", "");
		if (!validbracks.matcher(bracks).matches())throw new IllegalArgumentException();
		
		ArrayList<String> questionAnswerScript=new ArrayList<String> ();ArrayList<Integer> answerList=new ArrayList<Integer> ();ArrayList<Integer> hintList=new ArrayList<Integer> ();
		Matcher speechFinder=speechPattern.matcher(Text);
		
		while (speechFinder.find()){
			if ("]".equals(speechFinder.group(2)))hintList.add(questionAnswerScript.size());
			else if ("}".equals(speechFinder.group(2)))answerList.add(questionAnswerScript.size());
			questionAnswerScript.add(speechFinder.group(1));}
		
		return new QuestionAnswerWorkSheet(Name, null, questionAnswerScript, answerList, hintList);}
	
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
								ex.printStackTrace();
								e.getPlayer().sendMessage("ERROR: Worksheet bad formatting!");
								e.getPlayer().sendMessage("ERROR: Perhaps {} [] braces not closed?");}
							
							e.setSigning(false);}});
					teacher.giveItem(new ItemStack(Material.BOOK_AND_QUILL));
					teacher.getPlayer().sendMessage("Sign a book to make an interactive Q&A assessment");
					while(newWorkSheet==null){obj.wait();}
					Assessment assessment = new QuestionAnswerAssessment(QuestionAnswerMaker.this, newWorkSheet.name, newWorkSheet);
					newWorkSheet=null;
					return assessment;}}};}
	@Override
	public String getName() {
		return name;}}
