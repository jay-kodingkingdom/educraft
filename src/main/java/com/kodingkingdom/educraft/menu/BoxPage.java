package com.kodingkingdom.educraft.menu;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kodingkingdom.educraft.menu.Menu.MenuItem;

public class BoxPage extends Page{
	
	MenuItem[][] itemArray;

	public MenuItem getItem(int widthX, int heightY){
		return itemArray[widthX][heightY];}
	public MenuItem[][] getItems(int widthX1, int heightY1, int widthX2, int heightY2){
		MenuItem[][] items = new MenuItem[widthX2-widthX1+1][heightY2-heightY1+1]; 
		for (int widthX=(widthX1<widthX2?widthX1:widthX2);widthX<(widthX1>widthX2?widthX1:widthX2);widthX++){
			for (int heightY=(heightY1<heightY2?heightY1:heightY2);heightY<(heightY1>heightY2?heightY1:heightY2);heightY++){
				items[widthX-widthX1][heightY-heightY1]=itemArray[widthX][heightY];}}
		return items;}

	protected void attachedAction(Connector connector){
		if (!(connector.connectorData instanceof BoxConnectorData)) throw new RuntimeException();
		BoxConnectorData boxConnectorData = (BoxConnectorData)connector.connectorData;
		itemArray = boxConnectorData.items;}
	
	protected void removedAction(){
		itemArray=null;}
	
	public Connector makePageConnector(Page parentPage){
		throw new UnsupportedOperationException();}
	public Connector makePageConnector(BoxPage parentPage, int widthX1, int heightY1, int widthX2, int heightY2){
		if (parentPage==null) throw new IllegalStateException();
		MenuItem[][] items=parentPage.getItems(widthX1, heightY1, widthX2, heightY2);
		return makePageConnector(
				Stream.of(
						Stream.of(items).collect(Collectors.toList()).toArray(new MenuItem[0])).collect(Collectors.toList()))
				.with(new BoxConnectorData(items));}
	public class BoxConnectorData extends ConnectorData{
		MenuItem[][] items;
		private BoxConnectorData (MenuItem[][] Items){
			items=Items;}}}