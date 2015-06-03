
package com.kodingkingdom.educraft.menu.groups.group.plots.add;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.VaryNamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.resources.Plot;
;

public class PlotsAddPage extends CompositeBoxPage {
	Group group;
	String plotName = "";
	public PlotsAddPage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		VaryNamePage namePage = new VaryNamePage(()->{return plotName;}, getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					plotName=(!plotName.equals("")?plotName.substring(0, plotName.length()-1):"");}
				, ()->{
					group.addPlots(
						Plot.createPlot(plotName));
					PlotsAddPage thisPage = PlotsAddPage.this;
					thisPage.remove();}
				,  ()->{
					PlotsAddPage thisPage = PlotsAddPage.this;
					thisPage.remove();}
				, null, null, null, null, null);
		PlotsAddContentPage contentPage = new PlotsAddContentPage(
				letter->{
					String newGroupName = plotName + letter;
					if (newGroupName.length()<=getHeight()) plotName=newGroupName;}
				, getWidth()-1
				, getHeight()-1);
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
