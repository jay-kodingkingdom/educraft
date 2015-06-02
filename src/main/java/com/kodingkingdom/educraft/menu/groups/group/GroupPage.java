
package com.kodingkingdom.educraft.menu.groups.group;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.locks.LocksPage;
import com.kodingkingdom.educraft.menu.groups.group.plots.PlotsPage;
import com.kodingkingdom.educraft.menu.groups.group.powers.PowersPage;
import com.kodingkingdom.educraft.menu.groups.group.students.StudentsPage;
import com.kodingkingdom.educraft.menu.groups.group.tasks.TasksPage;
import com.kodingkingdom.educraft.menu.groups.group.worlds.WorldsPage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.select.SelectItem;

public class GroupPage extends CompositeBoxPage {
	
	Group group;
	
	public GroupPage(Group Group){
		group=Group;}
	
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(group.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null
				, ()->{
					//Group.create(groupName);
					GroupPage thisPage = GroupPage.this;
					thisPage.remove();}
				,  ()->{
					GroupPage thisPage = GroupPage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		GroupContentPage contentPage = new GroupContentPage(getWidth()-1, getHeight()-1
											,new SelectItem(
													()->{
														GroupPage thisPage = GroupPage.this; 
														StudentsPage newPage = new StudentsPage(group);
														thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
													,Icon.makeIcon("Users").asIcon())
											,new SelectItem(
													()->{
														GroupPage thisPage = GroupPage.this; 
														PowersPage newPage = new PowersPage(group);
														thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
													,Icon.makeIcon("Powers").asIcon())
											,new SelectItem(
													()->{
														GroupPage thisPage = GroupPage.this; 
														LocksPage newPage = new LocksPage(group);
														thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
													,Icon.makeIcon("Locks").asIcon())
											,new SelectItem(
													()->{
														GroupPage thisPage = GroupPage.this; 
														TasksPage newPage = new TasksPage(group);
														thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
													,Icon.makeIcon("Tasks").asIcon())
											,new SelectItem(
													()->{
														GroupPage thisPage = GroupPage.this; 
														WorldsPage newPage = new WorldsPage();
														thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
													,Icon.makeIcon("Worlds").asIcon())
											,new SelectItem(
													()->{
														GroupPage thisPage = GroupPage.this; 
														PlotsPage newPage = new PlotsPage();
														thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
													,Icon.makeIcon("Plots").asIcon()));
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}