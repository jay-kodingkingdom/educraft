package com.kodingkingdom.educraft.page.select;

import org.bukkit.inventory.ItemStack;

public class SelectItem{
	Runnable action;
	ItemStack icon;
	public SelectItem(Runnable Action,ItemStack Icon){
		action=Action;
		icon=Icon.clone();}

	public Runnable getAction(){
		return ()->action.run();}
	public ItemStack getIcon(){
		return icon.clone();}
	public static SelectItem normalize(SelectItem selectItem){
		if (selectItem==null) return Null;
		else return selectItem;}
	public static final SelectItem Null=new SelectItem(()->{},null);}