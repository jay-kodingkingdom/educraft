package com.kodingkingdom.educraft.page.select.selects;

import com.kodingkingdom.educraft.page.select.SelectFunctionItemsPage;
import com.kodingkingdom.educraft.page.select.SelectItem;

public class SelectItemsPage extends SelectFunctionItemsPage<SelectItem>{
	public SelectItemsPage(SelectItem[][] SelectItems){
		super(SelectItems
				,(SelectItem selectItem)->{return selectItem;});}}
