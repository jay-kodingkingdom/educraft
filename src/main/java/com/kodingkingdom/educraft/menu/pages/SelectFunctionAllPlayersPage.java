package com.kodingkingdom.educraft.menu.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.menu.Menu;

public class SelectFunctionAllPlayersPage extends SelectFunctionVariesItemsPage<Player>{

	public SelectFunctionAllPlayersPage(
			Group Group,
			SelectFunctionItem<Player> Function, long PollInterval) {
		super(new Supplier<List<Player>>(){
				public List<Player> get(){
					ArrayList<Player> playersList=new ArrayList<Player>();
					Bukkit.getOnlinePlayers().forEach(new Consumer<Player>(){
						public void accept(Player player){
							playersList.add(player);}});
					return playersList;}},
			Function, PollInterval);}

	protected void clickItemAction(Menu.MenuItem item){
		for (int widthX=0;widthX<menuItemsBox.getWidth();widthX++){
			for (int heightY=0;heightY<menuItemsBox.getWidth();heightY++){
				if (menuItemsBox.getBoxItem(widthX, heightY).equals(item)){
					if (selectItemsBox.getBoxItem(widthX, heightY)!=null)
						function.actionFunction.apply(selectItemsBox.getBoxItem(widthX, heightY));
					else
						for (Player player : selectItemsList){
							if (selectItemsBox.getBoxItem(widthX, heightY)!=null)
								function.actionFunction.apply(selectItemsBox.getBoxItem(widthX, heightY));}}}}}}
