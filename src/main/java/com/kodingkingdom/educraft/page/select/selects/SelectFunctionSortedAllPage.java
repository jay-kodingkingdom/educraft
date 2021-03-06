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
						T[] allArray = (T[])new Comparable[tsList.size()+2];
						tsList.add(ts.toArray(allArray));
						ts.forEach(
							(T t)->{
								tsList.add((T[])new Comparable[]{t});});
						return tsList;}},
				(T[] ts)->{
					return
						new SelectItem(()->{
							if (ts!=null)
								for(T t : ts){
									if (t!=null) Function.apply(t).getAction().run();}}
							,(ts!=null?(ts.length==1?Function.apply(ts[0]).getIcon():GroupIcon.get()):null));}
				, PollInterval);}}
