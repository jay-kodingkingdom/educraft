package com.kodingkingdom.educraft.assessment.questionandanswer;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.kodingkingdom.educraft.assessment.Result;
import com.kodingkingdom.educraft.assessment.WorkSheet;

public class QuestionAnswerResult extends Result {
	
	int total;
	ArrayList<ArrayList<String>> attempts;
	QuestionAnswerWorkSheet workSheet;
	
	QuestionAnswerResult(QuestionAnswerWorkSheet WorkSheet){total=0;workSheet=WorkSheet;attempts=new ArrayList<ArrayList<String>>(); for (int i=0;i<workSheet.questionAnswerScript.size();i++){attempts.add(new ArrayList<String>());}}
	
	public WorkSheet getWorksheet() {
		return workSheet;}

	@Override
	public int getMarks() {
		int marks=0;
		for (int i=0;i<workSheet.answerList.size();i++){
			if (attempts.get(i).isEmpty())continue;
			if (workSheet.questionAnswerScript.get(workSheet.answerList.get(i)).equals(
					attempts.get(i).get(attempts.get(i).size()-1)))marks++;}
		return marks;}

	@Override
	public String getReport() {
		return workSheet.student.getPlayer().getName()+": "+getMarks()+"/"+total;}

	@Override
	public String getDetail() {
		String result=ChatColor.BOLD+workSheet.student.getPlayer().getName()+": "+getMarks()+"/"+total;
		for (int i=0;i<attempts.size();i++){
			ArrayList<String> questionAttempts=attempts.get(i);
			String correctAnswer = workSheet.questionAnswerScript.get(
									workSheet.answerList.get(i));
			String firstAttempt = (questionAttempts.isEmpty()?ChatColor.RED+"Not Answered":questionAttempts.get(0));
			result = result+ChatColor.RESET+ChatColor.BLACK+"; Q"+ i++ +" "+ChatColor.RED +(correctAnswer.equals(firstAttempt)?"correct":"wrong")+"  ,answer:"+correctAnswer+ChatColor.ITALIC+",  attempt:"+firstAttempt+ChatColor.BLACK+ChatColor.BOLD+", No. of attempts:"+questionAttempts.size();}
		return result;}}
