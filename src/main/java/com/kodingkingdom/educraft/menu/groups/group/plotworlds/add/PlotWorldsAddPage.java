
package com.kodingkingdom.educraft.menu.groups.group.plotworlds.add;

import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.menus.ControlsPage;
import com.kodingkingdom.educraft.menu.menus.VaryNamePage;
import com.kodingkingdom.educraft.page.CompositeBoxPage;
import com.kodingkingdom.educraft.page.Menu;
import com.kodingkingdom.educraft.resources.PlotWorld;
;

public class PlotWorldsAddPage extends CompositeBoxPage {
	Group group;
	String plotName = "";
	public PlotWorldsAddPage(Group Group){
		group=Group;}
	protected void compositeAttachedAction(Connector connector){
		VaryNamePage namePage = new VaryNamePage(()->{return plotName;}, getHeight());
		ControlsPage controlsPage = new ControlsPage(
				()->{
					PlotWorldsAddPage thisPage = PlotWorldsAddPage.this;
					thisPage.remove();}
				, null, null, null, null
				, ()->{
					plotName=(!plotName.equals("")?plotName.substring(0, plotName.length()-1):"");}
				, null
				, ()->{
					PlotWorldsAddPage thisPage = PlotWorldsAddPage.this;
					if (!plotName.equals("") &&
							!PlotWorld.getPlotWorlds().stream()
							.anyMatch(
									(PlotWorld plotWorld) -> {
										return plotName.equals(plotWorld.getName());})){
						
						Player player = Menu.getMenu(thisPage).getUser().getPlayer();
						group.addPlots(
								PlotWorld.createPlotWorld(plotName));
						player.sendMessage("PlotWorld "+plotName+" has been created!");
						thisPage.remove();}
					else {
						Player player = Menu.getMenu(thisPage).getUser().getPlayer();
						player.sendMessage("PlotWorld "+plotName+" already exists!");}});
		PlotWorldsAddContentPage contentPage = new PlotWorldsAddContentPage(
				letter->{
					String newGroupName = plotName + letter;
					if (newGroupName.length()<=getHeight()) plotName=newGroupName;}
				, getWidth()-1
				, getHeight()-1);
		this.compose(namePage.makePageConnector(this.getSubBox(0, 0, 0, getHeight()-1)));
		this.compose(controlsPage.makePageConnector(this.getSubBox(1, getHeight()-1, getWidth()-1, getHeight()-1)));
		this.compose(contentPage.makePageConnector(this.getSubBox(1, 0, getWidth()-1, getHeight()-2)));}}
