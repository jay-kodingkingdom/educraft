package com.kodingkingdom.educraft.menu.groups.group.locks;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.powers.Lock;

public class LocksContentPage extends SelectFunctionSortedPage<Lock>{

	public LocksContentPage(Group Group, Consumer<Lock> LockAction) {
		super(
			()->{
				return Group.getLocks().stream()
						.collect(Collectors.toList());}
			,(Lock lock)->{
				return new SelectItem(
						()->{
							LockAction.accept(lock);}
						,Icon.makeIcon(Textures.Locks).withName(lock.getName()).withCaption(lock.getName()).asIcon());}
			, Bible.pollInterval);}}
