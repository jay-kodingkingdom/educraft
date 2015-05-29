package com.kodingkingdom.educraft.menu.groups;

import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class GroupsAddControlsPage extends SelectItemsPage{

	private static SelectItem[][] makeSelectItems(Runnable addGroupAction, int width){
		SelectItem[][] selectItems = new SelectItem[width][1];
		selectItems[0][0]=new SelectItem(addGroupAction, Icon.makeIcon("+").asIcon());
		return selectItems;}
	
	public GroupsAddControlsPage(Runnable addGroupAction, int width) {
		super(makeSelectItems(addGroupAction, width));}}
