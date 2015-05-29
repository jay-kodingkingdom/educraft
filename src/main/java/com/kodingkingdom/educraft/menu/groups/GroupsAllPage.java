package com.kodingkingdom.educraft.menu.groups;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;

public class GroupsAllPage extends SelectFunctionSortedAllPage<Group>{

	public GroupsAllPage(Consumer<Group> GroupAction, Supplier<ItemStack> GroupIcon, long PollInterval) {
		super(()->{return Group.getGroups();}
			,(Group group)->{
				return new SelectItem(()->{GroupAction.accept(group);},Icon.makeIcon(group.getName()).asIcon());}
			, GroupIcon, PollInterval);}}
