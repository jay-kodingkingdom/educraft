package com.kodingkingdom.educraft.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Page {
	Page parentPage;
	HashSet<Page> childPages;
	
	HashMap<Menu.MenuItem,Page> itemPageMap;
	
	public Page(){
		parentPage = null;
		childPages = new HashSet<Page>();
		itemPageMap = new HashMap<Menu.MenuItem,Page>();}
	
	public void attach(Page childPage, Collection<Menu.MenuItem> childItems){
		childPage.parentPage=this;
		childPages.add(childPage);
		for (Menu.MenuItem childItem : childItems){
			childPage.itemPageMap.put(childItem, childPage);
			itemPageMap.replace(childItem, childPage);}}

	public void remove(Page childPage){
		childPage.parentPage=null;
		childPages.remove(childPage);
		for (Menu.MenuItem childItem : childPage.itemPageMap.keySet()){
			childPage.itemPageMap.remove(childItem);
			itemPageMap.replace(childItem, this);}}
	
	public final void closePage(){
		for (Page childPage : childPages){
			childPage.closePage();;}
		closePageAction();}
	
	public final void clickItem(Menu.MenuItem item){
		clickItemAction(item);
		if (itemPageMap.get(item)!=this){
			itemPageMap.get(item).clickItem(item);}}

	protected void closePageAction(){}
	
	protected void clickItemAction(Menu.MenuItem item){}}