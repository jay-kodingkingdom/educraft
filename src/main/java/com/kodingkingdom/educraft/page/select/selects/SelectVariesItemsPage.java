package com.kodingkingdom.educraft.page.select.selects;

import java.util.List;
import java.util.function.Supplier;

import com.kodingkingdom.educraft.page.select.SelectFunctionVariesItemsPage;
import com.kodingkingdom.educraft.page.select.SelectItem;

public class SelectVariesItemsPage extends SelectFunctionVariesItemsPage<SelectItem>{

	public SelectVariesItemsPage(Supplier<List<SelectItem>> SelectItemsGetter, long PollInterval){
		super(SelectItemsGetter, (SelectItem selectItem)->{return selectItem;}, PollInterval);}}
