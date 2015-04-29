package com.kodingkingdom.educraft.assessment.questionandanswer;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.conversations.Conversation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.StudentUser;
import com.kodingkingdom.educraft.assessment.Result;
import com.kodingkingdom.educraft.assessment.WorkSheet;
import com.lenis0012.bukkit.npc.NPC;
import com.lenis0012.bukkit.npc.NPCFactory;
import com.lenis0012.bukkit.npc.NPCProfile;

public class QuestionAnswerWorkSheet extends WorkSheet implements Listener {

	String name;
	StudentUser student;
	NPC npc;
	ArrayList<String> questionAnswerScript;
	ArrayList<Integer> answerList;
	ArrayList<Integer> hintList;
	QuestionAnswerResult result;
	
	Conversation conversation;

	int speechNumber;
	@Override
	public StudentUser getStudent() {
		return student;}
	void setStudent(StudentUser Student){student=Student;}
	QuestionAnswerWorkSheet(String Name, StudentUser Student, ArrayList<String> QuestionAnswerScript,ArrayList<Integer> AnswerList,ArrayList<Integer> HintList){
		name=Name;student=Student;
		questionAnswerScript=new ArrayList<String> (QuestionAnswerScript);
		answerList=new ArrayList<Integer> (AnswerList);
		hintList=new ArrayList<Integer> (HintList);
		speechNumber=-1;
		result=new QuestionAnswerResult(this);result.total=hintList.size();}
	@Override
	public QuestionAnswerWorkSheet getClone() {
		QuestionAnswerWorkSheet clone = new QuestionAnswerWorkSheet(name,student,questionAnswerScript,answerList,hintList);
		return clone;}	
	@Override
	public void begin() {
		final NPCFactory factory = new NPCFactory(EduCraftPlugin.getPlugin());
		final Location location = student.getPlayer().getLocation().add(student.getPlayer().getLocation().getDirection());
		npc = factory.spawnHumanNPC(location, new NPCProfile("Teacher for "+student.getPlayer().getName()));
		npc.setGravity(false);
		npc.getBukkitEntity().teleport(location);
		npc.setGodmode(true);
		npc.setTarget(student.getPlayer());		
		
		EduCraftPlugin.getPlugin().getEduCraft().registerEvents(this);
		
		conversation=new Conversation(EduCraftPlugin.getPlugin(),student.getPlayer(),nextPrompt(null));
		conversation.begin();}
	@Override
	public void end() {
		conversation.abandon();
		npc.getBukkitEntity().remove();
		PlayerMoveEvent.getHandlerList().unregister(this);}
	
	
	QuestionAnswerPrompt nextPrompt(QuestionAnswerPrompt prevPrompt){
		if (speechNumber>=questionAnswerScript.size())return null;
		
		speechNumber++;
		
		if (answerList.contains(speechNumber))
			return new AnswerPrompt(this, speechNumber, answerList.lastIndexOf(speechNumber));
		else if (hintList.contains(speechNumber))
			return new HintPrompt(this, speechNumber, hintList.lastIndexOf(speechNumber));
		else
			return new QuestionPrompt(this,speechNumber);}
	
	
	@Override
	public Result getResult() {
		return result;}
	@EventHandler
	public void freezeStudent(PlayerMoveEvent e){ 
		if (e.getPlayer().equals(student.getPlayer())) e.setTo(e.getFrom());}}
