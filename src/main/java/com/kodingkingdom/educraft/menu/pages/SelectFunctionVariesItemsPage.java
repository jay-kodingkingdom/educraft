package com.kodingkingdom.educraft.menu.pages;

import java.util.List;
import java.util.function.Supplier;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.menu.Box;
import com.kodingkingdom.educraft.menu.BoxPage;
import com.kodingkingdom.educraft.menu.Menu;

public class SelectFunctionVariesItemsPage<T> extends BoxPage{

	long pollInterval;
	int pageNumber;
	Box<T> selectItemsBox;
	SelectFunctionItem<T> function;
	
	Supplier<List<T>> selectItemsGetter;
	
	int pollId =-1;
	List<T> selectItemsList;
		
	public SelectFunctionVariesItemsPage(Supplier<List<T>> SelectItemsGetter, SelectFunctionItem<T> Function, long PollInterval){
		pollInterval=PollInterval;
		pageNumber=0;
		selectItemsGetter=SelectItemsGetter;
		function=Function;}
	
	void updatePage(){
		updateSelectItems();
		updateMenuItems();
		pollId=EduCraftPlugin.getPlugin().getEduCraft().scheduleTask(() -> {updatePage();}, pollInterval);}
	
	void updateSelectItems(){
		selectItemsList=selectItemsGetter.get();
		int pageLength = menuItemsBox.getHeight()*menuItemsBox.getWidth();
		T[][] selectItems = (T[][])new Object[menuItemsBox.getWidth()][menuItemsBox.getHeight()]; 
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getHeight();heightY++){
				selectItems[widthX][heightY]=
						selectItemsList.get(
								pageNumber*pageLength+
								heightY*menuItemsBox.getWidth()+
								widthX);}}
		selectItemsBox=new Box<T>(selectItems);}
	
	void updateMenuItems(){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				menuItemsBox.getBoxItem(widthX, heightY).setIcon(
						function.iconFunction.apply(selectItemsBox.getBoxItem(widthX, heightY)));}}}
	
	protected void attachedAction(Connector connector){
		updatePage();}
	
	protected void removedAction(){
		if (pollId!=-1) {
			EduCraftPlugin.getPlugin().getEduCraft().cancelTask(pollId);
			pollId=-1;}
		
		function.iconFunction=(T t)->{return null;};
		updateMenuItems();}
	
	protected void clickItemAction(Menu.MenuItem item){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				if (menuItemsBox.getBoxItem(widthX, heightY).equals(item)){
					function.actionFunction.apply(selectItemsBox.getBoxItem(widthX, heightY));}}}}

	public void nextPage(){
		pageNumber++;}
	public void prevPage(){
		pageNumber--;}
	
	public Connector makePageConnector(BoxPage parentPage, int widthX1, int heightY1, int widthX2, int heightY2){
		if (widthX2-widthX1+1 != selectItemsBox.getWidth() ||
			heightY2-heightY1+1 != selectItemsBox.getHeight()) throw new IllegalArgumentException();
		else 
			return super.makePageConnector(parentPage, widthX1, heightY1, widthX2, heightY2);}}
