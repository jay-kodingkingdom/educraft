package com.kodingkingdom.educraft.page.select;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.page.Box;
import com.kodingkingdom.educraft.page.BoxPage;
import com.kodingkingdom.educraft.page.Menu;

public class SelectFunctionVariesItemsPage<T> extends BoxPage{

	long pollInterval;
	int pageNumber;
	Box<T> selectItemsBox;
	Function<T,SelectItem> function;
	
	Supplier<List<T>> selectItemsGetter;
	
	int pollId =-1;
	List<T> selectItemsList;
		
	public SelectFunctionVariesItemsPage(Supplier<List<T>> SelectItemsGetter, Function<T,SelectItem> Function, long PollInterval){
		pollInterval=PollInterval;
		pageNumber=0;
		selectItemsGetter=SelectItemsGetter;
		function=Function;}
	
	T normalize(Supplier<T> selectItemGetter){
		try {
			return selectItemGetter.get();}
		catch (Exception e){
			return null;}}
	
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
				int X=widthX,Y=heightY;
				selectItems[widthX][heightY]=
						normalize(()->{
							return selectItemsList.get(
								pageNumber*pageLength+
								menuItemsBox.getSlotNumber(X,Y));});}}
		selectItemsBox=new Box<T>(selectItems);}
	
	void updateMenuItems(){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				menuItemsBox.getBoxItem(widthX, heightY).setIcon(
						SelectItem.normalize(function.apply(selectItemsBox.getBoxItem(widthX, heightY))).icon);}}}
	
	protected void attachedAction(Connector connector){
		super.attachedAction(connector);
		updatePage();}
	
	protected void removedAction(){
		if (pollId!=-1) {
			EduCraftPlugin.getPlugin().getEduCraft().cancelTask(pollId);
			pollId=-1;}
		
		function=(T t)->{return SelectItem.Null;};
		updateMenuItems();
		super.removedAction();}
	
	protected void clickItemAction(Menu.MenuItem item){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				if (menuItemsBox.getBoxItem(widthX, heightY).equals(item)){
					SelectItem.normalize(function.apply(selectItemsBox.getBoxItem(widthX, heightY))).action.run();}}}}

	public void nextPage(){
		pageNumber++;}
	public void prevPage(){
		pageNumber--;}}
