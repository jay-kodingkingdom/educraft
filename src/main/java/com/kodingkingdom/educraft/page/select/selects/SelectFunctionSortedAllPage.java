package com.kodingkingdom.educraft.page.select.selects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.page.select.SelectFunctionVariesItemsPage;
import com.kodingkingdom.educraft.page.select.SelectItem;

public class SelectFunctionSortedAllPage<T extends Comparable<? super T>> extends SelectFunctionVariesItemsPage<T[]>{

	public SelectFunctionSortedAllPage(
			Supplier<Collection<T>> Ts,
			Function<T,SelectItem> Function, Supplier<ItemStack> GroupIcon, long PollInterval) {
		super(
				new Supplier<List<T[]>>(){
					public List<T[]> get(){
						ArrayList<T> ts=new ArrayList<T>(Ts.get());
						Collections.sort(ts);
						ArrayList<T[]> tsList=new ArrayList<T[]>();
						tsList.add(ts.toArray((T[])new Object[0]));
						ts.forEach(
							(T t)->{
								tsList.add((T[])new Object[]{t});});
						return tsList;}},
				(T[] ts)->{return
					new SelectItem(()->{
						for(T t : ts){
							Function.apply(t).getAction().run();}}
						,(ts.length==1?Function.apply(ts[0]).getIcon():GroupIcon.get()));}
				, PollInterval);}}
