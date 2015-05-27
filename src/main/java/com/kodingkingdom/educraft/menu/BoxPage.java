package com.kodingkingdom.educraft.menu;

import com.kodingkingdom.educraft.menu.Menu.MenuItem;

public class BoxPage extends Page{
	
	protected Box<MenuItem> menuItemsBox;

	public MenuItem getItem(int widthX, int heightY){
		return menuItemsBox.getBoxItem(widthX, heightY);}
	public MenuItem[][] getItems(int widthX1, int heightY1, int widthX2, int heightY2){
		MenuItem[][] items = new MenuItem[widthX2-widthX1+1][heightY2-heightY1+1]; 
		for (int widthX=(widthX1<widthX2?widthX1:widthX2);widthX<(widthX1>widthX2?widthX1:widthX2);widthX++){
			for (int heightY=(heightY1<heightY2?heightY1:heightY2);heightY<(heightY1>heightY2?heightY1:heightY2);heightY++){
				items[widthX-widthX1][heightY-heightY1]=menuItemsBox.getBoxItem(widthX, heightY);}}
		return items;}

	protected void attachedAction(Connector connector){
		if (!(connector.connectorData instanceof BoxConnectorData)) throw new RuntimeException();
		BoxConnectorData boxConnectorData = (BoxConnectorData)connector.connectorData;
		menuItemsBox = boxConnectorData.menuItemsBox;}
	
	protected void removedAction(){
		menuItemsBox=null;}
	
	public final Connector makePageConnector(Page parentPage){
		throw new UnsupportedOperationException();}
	public Connector makePageConnector(BoxPage parentPage, int widthX1, int heightY1, int widthX2, int heightY2){
		if (parentPage==null) throw new IllegalStateException();
		MenuItem[][] items=parentPage.getItems(widthX1, heightY1, widthX2, heightY2);
		return makePageConnector(
				new Box<MenuItem>(items)
				.asCollection())
					.with(new BoxConnectorData(items));}
	public class BoxConnectorData extends ConnectorData{
		Box<MenuItem> menuItemsBox;
		protected BoxConnectorData (MenuItem[][] Items){
			menuItemsBox=new Box<MenuItem>(Items);}}}