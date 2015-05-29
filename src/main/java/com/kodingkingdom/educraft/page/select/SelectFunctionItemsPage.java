package com.kodingkingdom.educraft.page.select;

import java.util.function.Function;

import com.kodingkingdom.educraft.page.Box;
import com.kodingkingdom.educraft.page.BoxPage;
import com.kodingkingdom.educraft.page.Menu;

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
	
	public Connector makePageConnector(BoxPage parentPage, int widthX1, int heightY1, int widthX2, int heightY2){
		if (widthX2-widthX1+1 != selectItemsBox.getWidth() ||
			heightY2-heightY1+1 != selectItemsBox.getHeight()) throw new IllegalArgumentException();
		else 
			return super.makePageConnector(parentPage, widthX1, heightY1, widthX2, heightY2);}}
