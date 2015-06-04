package com.kodingkingdom.educraft.menu.groups.group.locks;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.groups.group.locks.lock.LockPage;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;

public class LocksPage extends CompositeBoxPage {
	Group group;
	public LocksPage(Group Group){
		group = Group;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(group.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					LocksPage thisPage = LocksPage.this; 
					thisPage.remove();}
				, null, null, null, null, null, null, null);
		LocksContentPage contentPage = new LocksContentPage(
				group
				,lock->{
					LocksPage thisPage = LocksPage.this;
					LockPage newPage = new LockPage(group,lock);
					thisPage.attach(newPage.makePageConnector(thisPage.getSubBox(0,0,getWidth()-1, getHeight()-1)));});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
