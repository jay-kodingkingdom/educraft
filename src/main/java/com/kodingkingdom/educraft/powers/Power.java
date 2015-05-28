package com.kodingkingdom.educraft.powers;

import java.util.HashMap;

import com.kodingkingdom.educraft.group.User;

public class Power {

	protected HashMap<User,PowerItem> userPowerMap;

	public final void give(User... powerUsers){
		for (User powerUser : powerUsers){
			PowerItem powerItem = new PowerItem(powerUser);
			userPowerMap.put(powerUser, powerItem);
			doAction(powerItem);}}
	public final void take(User... powerUsers){
		for (User powerUser : powerUsers){
			PowerItem powerItem = new PowerItem(powerUser);
			userPowerMap.remove(powerUser);
			undoAction(powerItem);}}
	
	protected void doAction(PowerItem lockItem){}
	protected void undoAction(PowerItem lockItem){}
	
	public class PowerItem{
		User user;
		
		PowerItem(User User){
			user=User;}
		
		public User getUser(){
			return user;}
		public Power getPower(){
			return Power.this;}}}
