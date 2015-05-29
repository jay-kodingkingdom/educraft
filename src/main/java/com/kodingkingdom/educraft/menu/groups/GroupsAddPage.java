package com.kodingkingdom.educraft.menu.groups;

import java.util.ArrayDeque;
import java.util.Arrays;

import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class GroupsAddPage extends SelectItemsPage{

	private static SelectItem[][] makeSelectItems(int width, int height){
		SelectItem[][] selectItems = new SelectItem[width][height];
		ArrayDeque<String> alphabetQueue=new ArrayDeque<String>(Arrays.asList(
				new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","."}));
		for (int widthX=0;widthX<width;widthX++){
			for (int heightY=0;heightY<width;heightY++){
				String ch = alphabetQueue.pop();
				selectItems[widthX][heightY]=new SelectItem(
						()->{
							for (Page childPage : getParentPage()){}
						}, Icon.makeIcon(ch).asIcon());}}
		
		selectItems[0][0]=new SelectItem(addGroupAction, Icon.makeIcon("+").asIcon());
		return selectItems;}
	
	public GroupsAddPage(int width, int height) {
		super(makeSelectItems(width, height));}}