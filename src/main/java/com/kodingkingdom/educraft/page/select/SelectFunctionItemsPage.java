package com.kodingkingdom.educraft.page.select;

import java.util.function.Function;

import com.kodingkingdom.educraft.page.BoxPage;
import com.kodingkingdom.educraft.page.Menu;
import com.kodingkingdom.educraft.page.Menu.MenuItem;

public class SelectFunctionItemsPage<T> extends BoxPage{

	private Box<T> selectItemsBox;
	private Function<T,SelectItem> function;
	
	public SelectFunctionItemsPage(T[][] SelectItems, Function<T,SelectItem> Function){ 
		selectItemsBox=new Box<T>(SelectItems);
		function=Function;}
	
	protected void attachedAction(Connector connector){
		super.attachedAction(connector);
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				menuItemsBox.getBoxItem(widthX, heightY).setIcon(
						SelectItem.normalize(function.apply(selectItemsBox.getBoxItem(widthX, heightY))).icon);}}}
	
	protected void removedAction(){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				menuItemsBox.getBoxItem(widthX, heightY).setIcon(
						null);}}
		super.removedAction();}
	
	protected void clickItemAction(Menu.MenuItem item){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				if (menuItemsBox.getBoxItem(widthX, heightY).equals(item)){
					SelectItem.normalize(function.apply(selectItemsBox.getBoxItem(widthX, heightY))).action.run();}}}}
	
	public Connector makePageConnector(Box<MenuItem> menuItemsBox){
		if (menuItemsBox.getWidth() != selectItemsBox.getWidth() ||
			menuItemsBox.getHeight() != selectItemsBox.getHeight()) throw new IllegalArgumentException();
		else 
			return super.makePageConnector(menuItemsBox);}}
