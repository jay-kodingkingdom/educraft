
package com.kodingkingdom.educraft.menu.groups.group.locks.lock.add;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.powers.Lock;

public class LockAddPage extends CompositeBoxPage {
	Group group;
	Lock lock;
	
	public LockAddPage(Group Group, Lock Lock){
		group=Group;
		lock=Lock;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(lock.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					LockAddPage thisPage = LockAddPage.this;
					thisPage.remove();}
				, null, null, null, null, null, null, null);
		LockAddContentPage contentPage = new LockAddContentPage(
				group
				,lock
				, (Student student)->{
					lock.give(student);}
				, ()->{
					return Icon.makeIcon(Textures.Users).withName("All Group Users").withCaption("All Group Users").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
