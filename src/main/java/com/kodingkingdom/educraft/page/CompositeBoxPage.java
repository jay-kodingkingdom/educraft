package com.kodingkingdom.educraft.page;

import java.util.HashMap;

import com.kodingkingdom.educraft.EduCraftPlugin;

public class CompositeBoxPage extends BoxPage{
	HashMap<Menu.MenuItem,Page> compositeItemPageMap;
	
	public final void compose(Connector connector){
		Page childPage = connector.getPage();
		for (Menu.MenuItem childItem : connector.connectingItems){
			compositeItemPageMap.replace(childItem, childPage);}
		childPage.attachedAction(connector);}
	
	protected final void clickItemAction(Menu.MenuItem item){
		if (itemPageMap.get(item)==this &&
				compositeItemPageMap.get(item)!=this){
			EduCraftPlugin.debug("propagating click to composite "+compositeItemPageMap.get(item));
			compositeItemPageMap.get(item).clickItem(item);}}

	protected final void attachedAction(Connector connector){
		for (Menu.MenuItem item : connector.connectingItems){
			compositeItemPageMap.put(item, this);}
		compositeAttachedAction(connector);}
	protected void compositeAttachedAction(Connector connector){}}