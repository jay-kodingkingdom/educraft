package com.kodingkingdom.educraft.menu.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.kodingkingdom.educraft.group.Group;
import com.kodingkingdom.educraft.group.users.Student;
import com.kodingkingdom.educraft.menu.Menu;

public class SelectFunctionGroupPlayersPage extends SelectFunctionVariesItemsPage<Player>{

	public SelectFunctionGroupPlayersPage(
			Group Group,
			SelectFunctionItem<Player> Function, long PollInterval) {
		super(new Supplier<List<Player>>(){
				public List<Player> get(){
					ArrayList<Student> studentsList=new ArrayList<Student>(Group.getStudents());
					Collections.sort(studentsList,new Comparator<Student>(){
						public int compare(Student a, Student b){
							return a.getName().compareTo(b.getName());}});
					ArrayList<Player> playersList=new ArrayList<Player>();
					playersList.add(null);
					for (Student student : Group.getStudents()){
						playersList.add(student.getPlayer());}
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
