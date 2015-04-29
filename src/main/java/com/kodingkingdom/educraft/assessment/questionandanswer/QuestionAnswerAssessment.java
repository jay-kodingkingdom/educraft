package com.kodingkingdom.educraft.assessment.questionandanswer;

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

public class QuestionAnswerAssessment extends Assessment {

	String name;
	QuestionAnswerMaker maker;
	QuestionAnswerWorkSheet workSheet;
	HashMap<StudentUser, QuestionAnswerWorkSheet> workSheets;
	HashMap<StudentUser, QuestionAnswerResult> workSheetResults;
	
	QuestionAnswerAssessment(QuestionAnswerMaker Maker, String Name, QuestionAnswerWorkSheet WorkSheet){name=Name;
		maker=Maker;workSheet=WorkSheet;workSheets=new HashMap<StudentUser, QuestionAnswerWorkSheet>();workSheetResults=new HashMap<StudentUser, QuestionAnswerResult>();}
	
	@Override
	public void Administer() {
		for (StudentUser student : maker.clasz.students.values()){
			QuestionAnswerWorkSheet clone = workSheet.getClone();clone.setStudent(student);
			workSheets.put(student, clone);}
		for (QuestionAnswerWorkSheet workSheet : workSheets.values()){
			workSheet.begin();}}
	@Override
	public void Mark() {
		for (WorkSheet workSheet : workSheets.values()){
			workSheet.end();
			workSheet.getResult();
			workSheetResults.put(workSheet.getStudent(), (QuestionAnswerResult)workSheet.getResult());}}
	@Override
	public Clasz getClasz() {
		return maker.clasz;}
	@Override
	public String getName() {
		return name;}
	@Override
	public QuestionAnswerAssessment getClone() {
		QuestionAnswerAssessment clone = new QuestionAnswerAssessment(maker,name,workSheet.getClone());
		return clone;}
	@Override
	public ItemStack getResultItem() {
		StringBuilder sb = new StringBuilder();ArrayList<String> pages = new ArrayList<String>(); 
		for (Result result : workSheetResults.values()){
			String report = result.getDetail();
			if (sb.length()+report.length()>256) {
				pages.add(sb.toString());sb.setLength(0);}
			sb.append(report);sb.append("\n\n");}
		if (sb.length()>0) {
			pages.add(sb.toString());}
		ItemStack item= new ItemStack(Material.WRITTEN_BOOK);
		BookMeta book=(BookMeta)item.getItemMeta();
		book.setTitle(name+" Results");
		book.setPages(pages);
		item.setItemMeta(book);
		return item;}}
