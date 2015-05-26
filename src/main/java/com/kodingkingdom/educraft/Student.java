package com.kodingkingdom.educraft;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Student {
	UUID id;
	public UUID getId(){
		return id;}
	public String getName(){
		return null;}
	public Player getPlayer(){
		return Bukkit.getPlayer(id);}
	public static Student getStudent(UUID id){
		return null;}}
