package com.kodingkingdom.educraft.menu.menus;

import java.util.stream.Stream;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.TeacherMenu;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;
import com.kodingkingdom.educraft.page.select.selects.SelectVariesItemsPage;

public class VaryNamePage extends SelectVariesItemsPage{

	private static SelectItem[][] makeSelectItems(String name, int height){
		SelectItem[][] selectItems = new SelectItem[1][height];
		for (int heightY=0;heightY<heightY;heightY++){
			selectItems[0][heightY]=new SelectItem(()->{}, Icon.makeIcon(""+name.charAt(heightY)).asIcon());}
		return selectItems;}
	
	public VaryNamePage(Group group, int height) {
		super(()->{
			return Stream.of(group.getName().toCharArray()).collect((char[] chs)->{return new SelectItem();};}
			, TeacherMenu.pollInterval);}}
