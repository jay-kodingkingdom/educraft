package com.kodingkingdom.educraft.menu.groups.group.tasks;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Bible;
import com.kodingkingdom.educraft.page.select.SelectItem;
import com.kodingkingdom.educraft.page.select.selects.SelectFunctionSortedPage;
import com.kodingkingdom.educraft.powers.Task;

public class TasksContentPage extends SelectFunctionSortedPage<Task>{

	public TasksContentPage(Group Group, Consumer<Task> TaskAction) {
		super(
			()->{
				return new ArrayList<Task>();}
			,(Task task)->{
				return new SelectItem(
						()->{TaskAction.accept(task);}
						,null);}
			, Bible.pollInterval);}}
