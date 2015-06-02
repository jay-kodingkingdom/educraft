package com.kodingkingdom.educraft.menu.groups.group.plots.add;

import java.util.ArrayDeque;
import java.util.Arrays;

import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class PowersAddContentPage extends SelectItemsPage{

	private static SelectItem[][] makeSelectItems(int width, int height, SelectItem[] selectItemsList){
		SelectItem[][] selectItems = new SelectItem[height][width];
		ArrayDeque<SelectItem> selectItemsQueue=new ArrayDeque<SelectItem>(Arrays.asList(selectItemsList));
		for (int heightY=0;heightY<height;heightY++){
			for (int widthX=0;widthX<width;widthX++){
				if (!selectItemsQueue.isEmpty()){
					selectItems[heightY][widthX]=selectItemsQueue.pop();}
				else {
					selectItems[heightY][widthX]=SelectItem.Null;}}}
		return selectItems;}
	
	public PowersAddContentPage(int width, int height, SelectItem... selectItemsList) {
		super(makeSelectItems(width, height, selectItemsList));}}