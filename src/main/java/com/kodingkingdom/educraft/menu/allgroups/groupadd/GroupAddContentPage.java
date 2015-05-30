package com.kodingkingdom.educraft.menu.allgroups.groupadd;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.function.Consumer;

import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class GroupAddContentPage extends SelectItemsPage{

	private static SelectItem[][] makeSelectItems(Consumer<String> groupAddContentAction, int width, int height){
		SelectItem[][] selectItems = new SelectItem[width][height];
		ArrayDeque<String> alphabetQueue=new ArrayDeque<String>(Arrays.asList(
				new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","."}));
		for (int widthX=0;widthX<width;widthX++){
			for (int heightY=0;heightY<width;heightY++){
				String letter = alphabetQueue.pop();
				selectItems[widthX][heightY]=new SelectItem(
						()->{
							groupAddContentAction.accept(letter);}
						, Icon.makeIcon(letter).asIcon());}}
		return selectItems;}
	
	public GroupAddContentPage(Consumer<String> groupAddContentAction, int width, int height) {
		super(makeSelectItems(groupAddContentAction, width, height));}}