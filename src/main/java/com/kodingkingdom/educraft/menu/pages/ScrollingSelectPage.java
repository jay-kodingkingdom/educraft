package com.kodingkingdom.educraft.menu.pages;

import java.util.List;
import java.util.function.Supplier;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.menu.Box;
import com.kodingkingdom.educraft.menu.BoxPage;
import com.kodingkingdom.educraft.menu.Menu;

public class ScrollingSelectPage extends BoxPage{

	private long pollInterval;
	private int pageNumber;
	private Box<SelectItem> selectItemsBox;
	
	private Supplier<List<SelectItem>> selectItemsGetter;
	
	private int pollId =-1;
	
	public ScrollingSelectPage(Supplier<List<SelectItem>> SelectItemsGetter, long PollInterval){
		pollInterval=PollInterval;
		pageNumber=0;
		selectItemsGetter=SelectItemsGetter;}
	
	void updatePage(){
		updateSelectItems();
		updateMenuItems();
		pollId=EduCraftPlugin.getPlugin().getEduCraft().scheduleTask(() -> {updatePage();}, pollInterval);}
	
	void updateSelectItems(){
		List<SelectItem> selectItemsList=selectItemsGetter.get();
		int pageLength = menuItemsBox.getHeight()*menuItemsBox.getWidth();
		SelectItem[][] selectItems = new SelectItem[menuItemsBox.getWidth()][menuItemsBox.getHeight()]; 
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getHeight();heightY++){
				selectItems[widthX][heightY]=
						selectItemsList.get(
								pageNumber*pageLength+
								heightY*menuItemsBox.getWidth()+
								widthX);}}
		selectItemsBox=new Box<SelectItem>(selectItems);}
	
	void updateMenuItems(){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				menuItemsBox.getBoxItem(widthX, heightY).setIcon(
						selectItemsBox.getBoxItem(widthX, heightY).icon);}}}
	
	protected void attachedAction(Connector connector){
		updatePage();}
	
	protected void removedAction(){
		if (pollId!=-1) {
			EduCraftPlugin.getPlugin().getEduCraft().cancelTask(pollId);
			pollId=-1;}
		
		selectItemsBox = new Box<SelectItem>(new SelectItem[selectItemsBox.getWidth()][selectItemsBox.getHeight()]);
		updateMenuItems();}
	
	protected void clickItemAction(Menu.MenuItem item){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				if (menuItemsBox.getBoxItem(widthX, heightY).equals(item)){
					selectItemsBox.getBoxItem(widthX, heightY).action.run();}}}}
	
	public Connector makePageConnector(BoxPage parentPage, int widthX1, int heightY1, int widthX2, int heightY2){
		if (widthX2-widthX1+1 != selectItemsBox.getWidth() ||
			heightY2-heightY1+1 != selectItemsBox.getHeight()) throw new IllegalArgumentException();
		else 
			return super.makePageConnector(parentPage, widthX1, heightY1, widthX2, heightY2);}}
