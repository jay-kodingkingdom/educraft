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

public class SelectFunctionSortedPage<T extends Comparable<? super T>> extends SelectFunctionVariesItemsPage<T>{

	public SelectFunctionSortedPage(
			Supplier<Collection<T>> Ts,
			Function<T,SelectItem> Function, Supplier<ItemStack> GroupIcon, long PollInterval) {
		super(
				new Supplier<List<T>>(){
					public List<T> get(){
						ArrayList<T> ts=new ArrayList<T>(Ts.get());
						Collections.sort(ts);
						return ts;}},
				t->{
					return
						new SelectItem(()->{
							if (t!=null) Function.apply(t).getAction().run();}
							,(t!=null?Function.apply(t).getIcon():null));}
				, PollInterval);}}
