package com.kodingkingdom.educraft.menu.menus;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectVariesItemsPage;

public class VaryNamePage extends SelectVariesItemsPage{
	
	public VaryNamePage(Supplier<String> nameGetter, int height) {
		super(
			()->{
				return nameGetter.get().chars().mapToObj(ch -> (char)ch)
						.map((Character ch)->{return new SelectItem(()->{}, Icon.makeIcon(""+ch).asIcon());})
						.collect(Collectors.toList());}
			, Bible.pollInterval);}}
