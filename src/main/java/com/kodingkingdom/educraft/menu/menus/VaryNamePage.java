package com.kodingkingdom.educraft.menu.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.menu.TeacherMenu;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectVariesItemsPage;

public class VaryNamePage extends SelectVariesItemsPage{
	
	private static List<SelectItem> copyIterator(Iterator<SelectItem> iter) {
	    List<SelectItem> copy = new ArrayList<SelectItem>();
	    while (iter.hasNext())
	        copy.add(iter.next());
	    return copy;}
	
	public VaryNamePage(Supplier<String> nameGetter, int height) {
		super(
			()->{
				return copyIterator(((LinkedList<SelectItem>)nameGetter.get().chars().mapToObj(ch -> (char)ch)
						.map((Character ch)->{return new SelectItem(()->{}, Icon.makeIcon(""+ch).asIcon());})
						.collect(Collectors.toCollection(LinkedList::new)))
						.descendingIterator());}
			, TeacherMenu.pollInterval);}}
