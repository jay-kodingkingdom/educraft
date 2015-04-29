package com.kodingkingdom.educraft.assessment.fillintheblanks;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

import com.kodingkingdom.educraft.assessment.Result;
import com.kodingkingdom.educraft.assessment.WorkSheet;

public class FillBlankResult extends Result {
	
	int total;
	int marks;
	ArrayList<Map.Entry<Boolean, Map.Entry<String, String>>> records;
	FillBlankWorkSheet workSheet;
	
	FillBlankResult(FillBlankWorkSheet WorkSheet){total=marks=0;records=new ArrayList<Map.Entry<Boolean, Map.Entry<String, String>>>();workSheet=WorkSheet;}
	
	public WorkSheet getWorksheet() {
		return workSheet;}

	@Override
	public int getMarks() {
		return marks;}

	@Override
	public String getReport() {
		return workSheet.student.getPlayer().getName()+": "+marks+"/"+total;}

	@Override
	public String getDetail() {
		String result=ChatColor.BOLD+workSheet.student.getPlayer().getName()+": "+marks+"/"+total;int i=1;
		for (Entry<Boolean, Entry<String, String>> entry : records){
			result = result+ChatColor.BLACK+"; Q"+ i++ +ChatColor.RED +(entry.getKey()?"correct":"wrong")+"  ,answer:"+entry.getValue().getKey()+ChatColor.ITALIC+",  attempt:"+entry.getValue().getValue();}
		return result;}}
