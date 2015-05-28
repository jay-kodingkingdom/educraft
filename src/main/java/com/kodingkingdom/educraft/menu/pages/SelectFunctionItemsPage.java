package com.kodingkingdom.educraft.menu.pages;

import com.kodingkingdom.educraft.menu.Box;
import com.kodingkingdom.educraft.menu.BoxPage;
import com.kodingkingdom.educraft.menu.Menu;

public class SelectFunctionItemsPage<T> extends BoxPage{

	private Box<T> selectItemsBox;
	private SelectFunctionItem<T> function;
	
	public SelectFunctionItemsPage(T[][] SelectItems, SelectFunctionItem<T> Function){ 
		selectItemsBox=new Box<T>(SelectItems);
		function=Function;}
	
	protected void attachedAction(Connector connector){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				menuItemsBox.getBoxItem(widthX, heightY).setIcon(
						function.iconFunction.apply(selectItemsBox.getBoxItem(widthX, heightY)));}}}
	
	protected void removedAction(){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				menuItemsBox.getBoxItem(widthX, heightY).setIcon(
						null);}}}
	
	protected void clickItemAction(Menu.MenuItem item){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				if (menuItemsBox.getBoxItem(widthX, heightY).equals(item)){
					function.actionFunction.apply(selectItemsBox.getBoxItem(widthX, heightY));}}}}
	
	public Connector makePageConnector(BoxPage parentPage, int widthX1, int heightY1, int widthX2, int heightY2){
		if (widthX2-widthX1+1 != selectItemsBox.getWidth() ||
			heightY2-heightY1+1 != selectItemsBox.getHeight()) throw new IllegalArgumentException();
		else 
			return super.makePageConnector(parentPage, widthX1, heightY1, widthX2, heightY2);}}
