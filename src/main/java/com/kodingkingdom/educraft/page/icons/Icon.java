package com.kodingkingdom.educraft.page.icons;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;


public class Icon {;
	ItemStack iconStack;

	private static HashMap<Material, Icon> materialIconMap=new HashMap<Material, Icon> ();
	private static HashMap<Material, HashMap<Short,Icon>> magicValueIconMap=new HashMap<Material, HashMap<Short,Icon>> ();
	
	public ItemStack asIcon(){
		return iconStack.clone();}
	public Icon withName(String name){
		ItemMeta iconInfo = iconStack.getItemMeta();
		iconInfo.setDisplayName(name);
		iconStack.setItemMeta(iconInfo);
		return this;}
	public Icon withCaption(String caption){
		ItemMeta iconInfo = iconStack.getItemMeta();
		List<String> captionList = Arrays.asList(caption.split("\n")); 
		iconInfo.setLore(captionList);
		iconStack.setItemMeta(iconInfo);
		return this;}
	private Icon withTexture(Texture texture){
		return withTexture(texture, UUID.randomUUID());}
	private Icon withTexture(Texture texture, UUID id){
		
		ItemMeta iconInfo = iconStack.getItemMeta();
		
		Class<?> headMetaClass=iconInfo.getClass();
		
		if( ! setFieldValue(headMetaClass,iconInfo,"profile",
				makeGameProfile(texture, id),
				"Unable to inject profile")){
			return null;}
		
		iconStack.setItemMeta(iconInfo);
		return this;}
	
	private Icon(){}
	private Icon(Material material, short magicValue){
		if (magicValue>0) {
			if (!magicValueIconMap.containsKey(material)) magicValueIconMap.put(material, new HashMap<Short,Icon>());
			magicValueIconMap.get(material).put(magicValue, this);}
		else if (magicValue<=0) {
			if (!magicValueIconMap.containsKey(material)) magicValueIconMap.put(material, new HashMap<Short,Icon>());
			magicValueIconMap.get(material).put((short) -magicValue, this);
			materialIconMap.put(material, this);}
		else {
			materialIconMap.put(material, this);}}

	public static Icon makeIcon(String name){
		return makeIcon(Material.ENCHANTED_BOOK).withName(name).withCaption(name);}
	
	private static Icon makeTexturedIcon(){
		return makeIcon(Material.SKULL_ITEM,(short)3);}
	public static Icon makeIcon(Texture texture){
		return makeTexturedIcon().withTexture(texture);}
	
	public static Icon makeIcon(Material material){
		Icon icon = new Icon(); 
		icon.iconStack=new ItemStack(material, 1);
		return icon;}
	public static Icon makeIcon(Material material, short magicValue){
		Icon icon = new Icon(); 
		icon.iconStack=new ItemStack(material, 1, magicValue);
		return icon;}
	
	public enum Texture{
		;
		String data;
		private Texture(String Data){
			data=Data;}}	
	private static GameProfile makeGameProfile(Texture texture,UUID id){
		GameProfile profile=new GameProfile(id,null);
		PropertyMap propertyMap=profile.getProperties();
		if(propertyMap==null){
			EduCraftPlugin.debug("No property map found in GameProfile, can't continue.");
			return null;}
		propertyMap.put("textures",new Property("textures",texture.data));
		propertyMap.put("Signature",new Property("Signature","1234"));
		return profile;}
	
	private static boolean setFieldValue(Class<?> objectClass, Object object, String fieldName, Object fieldValue, String message){
		try{
			Field field=objectClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			Field modifiersField=Field.class.getDeclaredField("modifiers");
			
			boolean accessible=modifiersField.isAccessible();
			if(!accessible){
				modifiersField.setAccessible(true);}
			try{
				field.set(object, fieldValue);
				return true;}
			finally{
				if(!accessible){
					modifiersField.setAccessible(false);}}}
		catch(IllegalArgumentException ex){
			EduCraftPlugin.debug(message+": unsupported version.");}
		catch(IllegalAccessException ex){
			EduCraftPlugin.debug(message+": security exception.");}
		catch(NoSuchFieldException ex){
			EduCraftPlugin.debug(message+": unsupported version, field "+fieldName+" not found.");}
		catch(SecurityException ex){
			EduCraftPlugin.debug(message+": security exception.");}
		catch(ClassCastException ex){
			EduCraftPlugin.debug(message+": unsupported version, unable to cast result.");}
		return false;}}
