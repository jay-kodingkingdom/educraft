package com.kodingkingdom.educraft.menu.pages;

import java.util.function.Function;

import org.bukkit.inventory.ItemStack;

public class SelectFunctionItem<T>{
	Function<T,Runnable> actionFunction;
	Function<T,ItemStack> iconFunction;
	public SelectFunctionItem(Function<T,Runnable> ActionFunction,Function<T,ItemStack> IconFunction){
		actionFunction=ActionFunction;
		iconFunction=IconFunction;}}