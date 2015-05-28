package com.kodingkingdom.educraft.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.menu.Menu.MenuItem;

public class Page {
	Page parentPage;
	HashSet<Page> childPages;
	
	HashMap<Menu.MenuItem,Page> itemPageMap;
	
	public Page(){
		parentPage = null;
		childPages = new HashSet<Page>();
		itemPageMap = new HashMap<Menu.MenuItem,Page>();}
	
	public final void attach(Page childPage, Connector connector){
		if (!connector.getPage().equals(childPage)) throw new IllegalArgumentException();
		childPage.parentPage=this;
		childPages.add(childPage);
		for (Menu.MenuItem childItem : connector.connectingItems){
			childPage.itemPageMap.put(childItem, childPage);
			itemPageMap.replace(childItem, childPage);}
		childPage.attachedAction(connector);}

	public final void remove(Page childPage){
		childPage.parentPage=null;
		childPages.remove(childPage);
		for (Menu.MenuItem childItem : childPage.itemPageMap.keySet()){
			childPage.itemPageMap.remove(childItem);
			itemPageMap.replace(childItem, this);}
		childPage.removedAction();}

	public final void openPage(){
		for (Page childPage : childPages){
			childPage.openPage();}
		openPageAction();}
	
	public final void closePage(){
		for (Page childPage : childPages){
			childPage.closePage();}
		closePageAction();}
	
	public final void clickItem(Menu.MenuItem item){
		EduCraftPlugin.debug("recieved click in page "+this);
		clickItemAction(item);
		if (itemPageMap.get(item)!=this){
			EduCraftPlugin.debug("propagating click to child "+itemPageMap.get(item));
			itemPageMap.get(item).clickItem(item);}}

	public Connector makePageConnector(Page parentPage){
		if (parentPage==null) throw new IllegalStateException();
		HashSet<MenuItem> emptyItems = new HashSet<MenuItem>();
		for (MenuItem item : parentPage.itemPageMap.keySet()){
			if (parentPage.itemPageMap.get(item).equals(parentPage))
				emptyItems.add(item);}
		return makePageConnector(emptyItems);}
	
	protected void attachedAction(Connector connector){}
	
	protected void removedAction(){}
	
	protected void openPageAction(){}
	
	protected void closePageAction(){}
	
	protected void clickItemAction(Menu.MenuItem item){}
	
	protected final Connector makePageConnector(Collection<Menu.MenuItem> connectingItems){
		return new Connector(connectingItems);}
	
	public class ConnectorData{}
	public final class Connector{
		ConnectorData connectorData;
		Collection<Menu.MenuItem> connectingItems;
		private Connector(Collection<Menu.MenuItem> ConnectingItems){
			connectingItems=ConnectingItems;}
		public Connector with(ConnectorData ConnectorData){
			connectorData=ConnectorData;
			return this;}
		public Page getPage(){
			return Page.this;}}}