package com.kodingkingdom.educraft.menu.groups.group.locks.lock.remove;

import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Texture;
import com.kodingkingdom.educraft.powers.Lock;

public class LockRemovePage extends CompositeBoxPage {
	Lock lock;
	
	public LockRemovePage(Lock Lock){
		lock=Lock;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(lock.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null , null
				,  ()->{
					LockRemovePage thisPage = LockRemovePage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		LockRemoveContentPage contentPage = new LockRemoveContentPage(
				lock,
				student->{
					lock.take(student);}
				, ()->{
					return Icon.makeIcon(Texture.Users).withName("All Locked Users").withCaption("All Locked Users").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
