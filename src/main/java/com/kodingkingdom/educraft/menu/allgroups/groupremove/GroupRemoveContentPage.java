package com.kodingkingdom.educraft.menu.allgroups.groupremove;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.TeacherMenu;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;

public class GroupRemoveContentPage extends SelectFunctionSortedAllPage<Group>{

	public GroupRemoveContentPage(Consumer<Group> GroupAction, Supplier<ItemStack> GroupIcon) {
		super(
			()->{
				return Group.getGroups();}
			,(Group group)->{
				return new SelectItem(
						()->{
							GroupAction.accept(group);}
						,Icon.makeIcon(group.getName()).asIcon());}
			, GroupIcon, TeacherMenu.pollInterval);}}
