package com.kodingkingdom.educraft.menu.menus;

import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class ControlsPage extends SelectItemsPage{

	private static Icon[] controlsIcons = new Icon[]
			{Icon.makeIcon("+"),Icon.makeIcon("2"),Icon.makeIcon("3"),Icon.makeIcon("4"),Icon.makeIcon("5"),Icon.makeIcon("6"),Icon.makeIcon("7"),Icon.makeIcon("8")};
	
	private static SelectItem[][] makeSelectItems(Runnable[] controlsActions){
		SelectItem[][] selectItems = new SelectItem[1][controlsActions.length];
		for (int widthX=0;widthX<controlsActions.length;widthX++){
			if (controlsActions[widthX]==null) selectItems[0][widthX]=SelectItem.Null;
			else selectItems[0][widthX]=
					new SelectItem(
						controlsActions[widthX]
						,controlsIcons[widthX].asIcon());}
		return selectItems;}
	
	public ControlsPage(Runnable... controlsActions) {
		super(makeSelectItems(controlsActions));}}
