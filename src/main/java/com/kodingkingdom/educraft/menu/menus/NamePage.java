package com.kodingkingdom.educraft.menu.menus;

import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class NamePage extends SelectItemsPage{

	private static SelectItem[][] makeSelectItems(String name, int height){
		SelectItem[][] selectItems = new SelectItem[height][1];
		int heightY=0;
		for (;heightY<height&&heightY<name.length();heightY++){
			selectItems[heightY][0]=new SelectItem(()->{}, Icon.makeIcon(""+name.charAt(heightY)).asIcon());}
		for (;heightY<height;heightY++){
			selectItems[heightY][0]=new SelectItem(()->{}, null);}
		return selectItems;}
	
	public NamePage(String name, int height) {
		super(makeSelectItems(name, height));}}
