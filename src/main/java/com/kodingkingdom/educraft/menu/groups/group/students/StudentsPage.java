package com.kodingkingdom.educraft.menu.groups.group.students;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.students.add.StudentAddPage;
import com.kodingkingdom.educraft.menu.groups.group.students.remove.StudentRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class StudentsPage extends CompositeBoxPage {
	Group group;
	public StudentsPage(Group Group){
		group = Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(group.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					StudentsPage thisPage = StudentsPage.this; 
					thisPage.remove();}
				, null
				, ()->{
					StudentsPage thisPage = StudentsPage.this; 
					StudentAddPage newPage = new StudentAddPage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					StudentsPage thisPage = StudentsPage.this; 
					StudentRemovePage newPage = new StudentRemovePage(group);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null);
		StudentsContentPage contentPage = new StudentsContentPage(
				group,
				student->{});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
