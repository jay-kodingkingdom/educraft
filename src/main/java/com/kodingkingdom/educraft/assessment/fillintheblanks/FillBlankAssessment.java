package com.kodingkingdom.educraft.assessment.fillintheblanks;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.kodingkingdom.educraft.Clasz;
import com.kodingkingdom.educraft.StudentUser;
import com.kodingkingdom.educraft.assessment.Assessment;
import com.kodingkingdom.educraft.assessment.Result;
import com.kodingkingdom.educraft.assessment.WorkSheet;

public class FillBlankAssessment extends Assessment {

	String name;
	FillBlankMaker maker;
	FillBlankWorkSheet workSheet;
	HashMap<StudentUser, FillBlankWorkSheet> workSheets;
	HashMap<StudentUser, FillBlankResult> workSheetResults;
	
	FillBlankAssessment(FillBlankMaker Maker, String Name, FillBlankWorkSheet WorkSheet){name=Name;
		maker=Maker;workSheet=WorkSheet;workSheets=new HashMap<StudentUser, FillBlankWorkSheet>();workSheetResults=new HashMap<StudentUser, FillBlankResult>();}
	
	@Override
	public void Administer() {
		for (StudentUser student : maker.clasz.students.values()){
			FillBlankWorkSheet clone = workSheet.getClone();clone.setStudent(student);
			workSheets.put(student, clone);}
		for (FillBlankWorkSheet workSheet : workSheets.values()){
			workSheet.begin();}}
	@Override
	public void Mark() {
		for (WorkSheet workSheet : workSheets.values()){
			workSheet.end();
			workSheet.getResult();
			workSheetResults.put(workSheet.getStudent(), (FillBlankResult)workSheet.getResult());}}
	@Override
	public Clasz getClasz() {
		return maker.clasz;}
	@Override
	public String getName() {
		return name;}
	@Override
	public FillBlankAssessment getClone() {
		FillBlankAssessment clone = new FillBlankAssessment(maker,name,workSheet.getClone());
		return clone;}
	@Override
	public ItemStack getResultItem() {
		StringBuilder sb = new StringBuilder();ArrayList<String> pages = new ArrayList<String>(); 
		for (Result result : workSheetResults.values()){
			String report = result.getDetail();
			if (sb.length()+report.length()>256) {
				pages.add(sb.toString());sb.setLength(0);}
			sb.append(report);sb.append("\n");}
		if (sb.length()>0) {
			pages.add(sb.toString());}
		ItemStack item= new ItemStack(Material.WRITTEN_BOOK);
		BookMeta book=(BookMeta)item.getItemMeta();
		book.setTitle("Results");
		book.setPages(pages);
		item.setItemMeta(book);
		return item;}}
