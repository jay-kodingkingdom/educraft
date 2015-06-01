package com.kodingkingdom.educraft.page;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.page.Menu.MenuItem;
import com.kodingkingdom.educraft.page.Page.Connector;

public class CompositeBoxPage extends BoxPage{
	HashMap<Menu.MenuItem,Page> compositeItemPageMap;
	
	public CompositeBoxPage(){
		compositeItemPageMap = new HashMap<Menu.MenuItem,Page>();}
	
	public final void compose(Connector Connector){
		Page childPage = Connector.getPage();
		childPage.parentPage=this;
		for (Menu.MenuItem childItem : Connector.connectingItems){
			childPage.itemPageMap.put(childItem, childPage);}
		childPage.attachedAction(Connector);}
	
	protected final void boxAttachedAction(Connector Connector){
		for (Menu.MenuItem childItem : Connector.connectingItems){
			compositeItemPageMap.put(childItem, this);}
		compositeAttachedAction(Connector);}
	
	protected final void clickItemAction(Menu.MenuItem item){
		if (itemPageMap.get(item)==this){
			if (compositeItemPageMap.get(item)!=this){
				EduCraftPlugin.debug("propagating click to composite "+itemPageMap.get(item));
				compositeItemPageMap.get(item).clickItem(item);}}}
	
	protected final void boxRemovedAction(){
		for (Page compositePage : compositeItemPageMap.values().stream()
									.collect(Collectors.toCollection(HashSet::new))){
			if (compositePage !=null) compositePage.remove();}
		compositeItemPageMap.clear();
		compositeRemovedAction();}

	protected void compositeAttachedAction(Connector connector){}
	
	protected void compositeRemovedAction(){}}