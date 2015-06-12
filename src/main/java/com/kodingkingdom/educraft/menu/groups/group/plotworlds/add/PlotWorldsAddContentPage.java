package com.kodingkingdom.educraft.menu.groups.group.plotworlds.add;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.function.Consumer;

import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectItemsPage;

public class PlotWorldsAddContentPage extends SelectItemsPage{

	private static SelectItem[][] makeSelectItems(Consumer<String> groupAddContentAction, int width, int height){
		SelectItem[][] selectItems = new SelectItem[height][width];
		ArrayDeque<String> alphabetQueue=new ArrayDeque<String>(Arrays.asList(
				new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","."}));
		for (int heightY=0;heightY<height;heightY++){
			for (int widthX=0;widthX<width;widthX++){
				if (!alphabetQueue.isEmpty()){
					String letter = alphabetQueue.pop();
					selectItems[heightY][widthX]=new SelectItem(
							()->{
								groupAddContentAction.accept(letter);}
							, Icon.makeIcon(letter).asIcon());}
				else {
					selectItems[heightY][widthX]=SelectItem.Null;}}}
		return selectItems;}
	
	public PlotWorldsAddContentPage(Consumer<String> groupAddContentAction, int width, int height) {
		super(makeSelectItems(groupAddContentAction, width, height));}}