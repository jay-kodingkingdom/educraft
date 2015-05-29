package com.kodingkingdom.educraft.menu.menus;

import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class NamePage extends SelectItemsPage{

	private static SelectItem[][] makeSelectItems(String name, int height){
		SelectItem[][] selectItems = new SelectItem[1][height];
		for (int heightY=0;heightY<heightY;heightY++){
			selectItems[0][heightY]=new SelectItem(()->{}, Icon.makeIcon(""+name.charAt(heightY)).asIcon());}
		return selectItems;}
	
	public NamePage(String name, int height) {
		super(makeSelectItems(name, height));}}
