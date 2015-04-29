package com.kodingkingdom.educraft.menu;

import org.bukkit.inventory.ItemStack;

import com.kodingkingdom.educraft.TeacherUser;

public abstract class MenuItem {
	public abstract void onClick(TeacherUser whoTeacher);
	public abstract ItemStack getItem();}
