package com.kodingkingdom.educraft.page;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.page.Menu.MenuItem;

public class Page {
	Page parentPage;
	HashSet<Page> childPages;
		
	HashMap<Menu.MenuItem,Page> itemPageMap;
	
	public Page(){
		parentPage = null;
		childPages = new HashSet<Page>();
		itemPageMap = new HashMap<Menu.MenuItem,Page>();}
	
	public final void attach(Connector Connector){
		Page childPage = Connector.getPage();
		childPage.parentPage=this;
		childPages.add(childPage);
		for (Menu.MenuItem childItem : Connector.connectingItems){
			childPage.itemPageMap.put(childItem, childPage);
			itemPageMap.replace(childItem, childPage);}
		childPage.attachedAction(Connector);}
	
	public final void remove(){
		if (parentPage!=null) {
			parentPage.removeAction();}
		for (Menu.MenuItem childItem : itemPageMap.keySet().toArray(new Menu.MenuItem [0])){
			if (parentPage!=null) parentPage.itemPageMap.replace(childItem, parentPage);
			itemPageMap.remove(childItem);}
		if (parentPage!=null) {
			parentPage.childPages.remove(this);
			parentPage=null;}
		removedAction();}

	public final void openPage(){
		for (Page childPage : childPages){
			childPage.openPage();}
		attachedAction(null);}
	
	public final void closePage(){
		for (Page childPage : childPages){
			childPage.closePage();}
		removedAction();}
	
	public final void clickItem(Menu.MenuItem item){
		EduCraftPlugin.debug("recieved click in page "+this);
		if (itemPageMap.get(item)==this){
			clickItemAction(item);}
		else{
			EduCraftPlugin.debug("propagating click to child "+itemPageMap.get(item));
			clickItemAction(item);
			itemPageMap.get(item).clickItem(item);}}

	public Connector makePageConnector(Page parentPage){
		if (parentPage==null) throw new IllegalStateException();
		HashSet<MenuItem> emptyItems = new HashSet<MenuItem>();
		for (MenuItem item : parentPage.itemPageMap.keySet()){
			if (parentPage.itemPageMap.get(item).equals(parentPage))
				emptyItems.add(item);}
		return makePageConnector(emptyItems);}
		
	protected void attachedAction(Connector connector){}
	
	protected void removeAction(){}
		
	protected void removedAction(){}
	
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