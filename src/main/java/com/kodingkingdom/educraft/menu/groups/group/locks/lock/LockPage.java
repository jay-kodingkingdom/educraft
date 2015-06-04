package com.kodingkingdom.educraft.menu.groups.group.locks.lock;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.locks.lock.add.LockAddPage;
import com.kodingkingdom.educraft.menu.groups.group.locks.lock.remove.LockRemovePage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.powers.Lock;

public class LockPage extends CompositeBoxPage {
	Group group;
	Lock lock;
	public LockPage(Group Group, Lock Lock){
		group = Group;
		lock=Lock;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage("Lock", getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					LockPage thisPage = LockPage.this; 
					thisPage.remove();}
				, null
				, ()->{
					LockPage thisPage = LockPage.this; 
					LockAddPage newPage = new LockAddPage(group, lock);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, ()->{
					LockPage thisPage = LockPage.this; 
					LockRemovePage newPage = new LockRemovePage(lock);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));}
				, null, null, null, null);
		LockContentPage contentPage = new LockContentPage(
				lock,
				student->{});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
