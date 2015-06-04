
package com.kodingkingdom.educraft.menu.groups.group.plots.add;

import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.VaryNamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.Menu;
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
					PlotsAddPage thisPage = PlotsAddPage.this;
					thisPage.remove();}
				, null, null, null, null
				, ()->{
					plotName=(!plotName.equals("")?plotName.substring(0, plotName.length()-1):"");}
				, null
				, ()->{
					PlotsAddPage thisPage = PlotsAddPage.this;
					if (!plotName.equals("") &&
							!Plot.getPlots().stream()
							.anyMatch(
									(Plot plot) -> {
										return plotName.equals(plot.getName());})){
						
						Player player = Menu.getMenu(thisPage).getUser().getPlayer();
						group.addPlots(
								Plot.createPlot(plotName));
						player.sendMessage("Plot "+plotName+" has been created!");
						thisPage.remove();}
					else {
						Player player = Menu.getMenu(thisPage).getUser().getPlayer();
						player.sendMessage("Plot "+plotName+" already exists!");}});
		PlotsAddContentPage contentPage = new PlotsAddContentPage(
				letter->{
					String newGroupName = plotName + letter;
					if (newGroupName.length()<=getHeight()) plotName=newGroupName;}
				, getWidth()-1
				, getHeight()-1);
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
