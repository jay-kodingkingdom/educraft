
package com.kodingkingdom.educraft.menu.groups.group.plots.plot.add;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.NamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.icons.Icon;
import com.kodingkingdom.educraft.page.icons.Icon.Textures;
import com.kodingkingdom.educraft.resources.Plot;

public class PlotAddPage extends CompositeBoxPage {
	Group group;
	Plot plot;
	
	public PlotAddPage(Group Group, Plot Plot){
		group=Group;
		plot=Plot;}
	protected void compositeAttachedAction(Connector connector){
		NamePage namePage = new NamePage(plot.getName(), getHeight());
		ControlsPage controlsPage = new ControlsPage(
				null, null
				,  ()->{
					PlotAddPage thisPage = PlotAddPage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		PlotAddContentPage contentPage = new PlotAddContentPage(
				group
				,plot
				, ()->{
					return Icon.makeIcon(Textures.Users).withName("All Group Users").withCaption("All Group Users").asIcon();});
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
