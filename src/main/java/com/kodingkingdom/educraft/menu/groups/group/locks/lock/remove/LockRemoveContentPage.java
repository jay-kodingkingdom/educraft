package com.kodingkingdom.educraft.menu.groups.group.locks.lock.remove;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedAllPage;
import com.kodingkingdom.educraft.powers.Lock;

public class LockRemoveContentPage extends SelectFunctionSortedAllPage<Student>{

	public LockRemoveContentPage(Lock lock, Consumer<Student> StudentAction, Supplier<ItemStack> StudentIcon) {
		super(
			()->{
				return lock.getStudents().stream().collect(Collectors.toList());}
			,(Student student)->{
				return new SelectItem(
						()->{
							lock.take(student);}
						,Icon.makeIcon(student).asIcon());}
			, StudentIcon, Bible.pollInterval);}}
