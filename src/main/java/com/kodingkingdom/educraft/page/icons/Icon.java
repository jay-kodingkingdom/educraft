package com.kodingkingdom.educraft.page.icons;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.kodingkingdom.educraft.EduCraftPlugin;
import com.kodingkingdom.educraft.group.User;
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
	private Icon withUser(User user){
		SkullMeta iconInfo = (SkullMeta)iconStack.getItemMeta();

		EduCraftPlugin.debug("user is "+user);
		EduCraftPlugin.debug("user id is "+user.getId());
		EduCraftPlugin.debug("username is "+user.getName());
		
		iconInfo.setOwner(user.getName());
		
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
	
	public static final Icon Null = makeIcon((ItemStack)null);
	
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
		//EduCraftPlugin.debug("icon out of "+name);
		if (name.equals("")) return Null;
		try {
			return makeIcon(Texture.valueOf(getTextureName(name))).withName(name).withCaption(name);} 
		catch(Exception e){
			return makeIcon(Material.ENCHANTED_BOOK).withName(name).withCaption(name);}}
	
	private static String getTextureName(String name){
		
		if (Stream.of(Texture.values())
				.map(texture->texture.name())
				.collect(Collectors.toCollection(HashSet::new))
				.contains(name)) return name;
		
		if (name.equals("")) return "";
				
		String textureName = name.substring(0, 1).toUpperCase();

		if (textureName.equals(".")) return "Dot";
		if (textureName.equals("/")) return "Slash";
		if (textureName.equals("?")) return "Question";
		if (textureName.equals("!")) return "Exclamation";
		
		return textureName;}
	private static Icon makeTexturedIcon(){
		return makeIcon(Material.SKULL_ITEM,(short)3);}
	public static Icon makeIcon(Texture texture){
		return makeTexturedIcon().withTexture(texture);}

	private static HashMap<UUID,Icon> userIconMap=new HashMap<UUID,Icon> ();
	private static Icon makeIcon(ItemStack IconStack){
		Icon icon = new Icon(); 
		icon.iconStack=IconStack;
		return icon;}
	private static Icon makeIcon(Icon Icon){
		return makeIcon(Icon.iconStack.clone());}
	public static Icon makeIcon(User user){
		if (!userIconMap.containsKey(user.getId())) userIconMap.put(user.getId(),makeTexturedIcon().withUser(user).withCaption(user.getName()).withName(user.getName()));
		return makeIcon(userIconMap.get(user.getId()));}
			
	public static Icon makeIcon(Material material){
		return makeIcon(new ItemStack(material, 1));}
	public static Icon makeIcon(Material material, short magicValue){
		return makeIcon(new ItemStack(material, 1, magicValue));}
	
	public enum Texture{
		ArrowUp("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDQ4Yjc2OGM2MjM0MzJkZmIyNTlmYjNjMzk3OGU5OGRlYzExMWY3OWRiZDZjZDg4ZjIxMTU1Mzc0YjcwYjNjIn19fQ==")
		,ArrowDown("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmRhZGQ3NTVkMDg1MzczNTJiZjdhOTNlM2JiN2RkNGQ3MzMxMjFkMzlmMmZiNjcwNzNjZDQ3MWY1NjExOTRkZCJ9fX0=")
		,ArrowRight("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWI2ZjFhMjViNmJjMTk5OTQ2NDcyYWVkYjM3MDUyMjU4NGZmNmY0ZTgzMjIxZTU5NDZiZDJlNDFiNWNhMTNiIn19fQ==")
		,ArrowLeft("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ViZjkwNzQ5NGE5MzVlOTU1YmZjYWRhYjgxYmVhZmI5MGZiOWJlNDljNzAyNmJhOTdkNzk4ZDVmMWEyMyJ9fX0=")
		,Question("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE2M2RhZmFjMWQ5MWE4YzkxZGI1NzZjYWFjNzg0MzM2NzkxYTZlMThkOGY3ZjYyNzc4ZmM0N2JmMTQ2YjYifX19")
		,Exclamation("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmE1M2JkZDE1NDU1MzFjOWViYjljNmY4OTViYzU3NjAxMmY2MTgyMGU2ZjQ4OTg4NTk4OGE3ZTg3MDlhM2Y0OCJ9fX0=")
		,Dot("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMzYWEyNDkxNmM4ODY5NmVlNzFkYjdhYzhjZDMwNmFkNzMwOTZiNWI2ZmZkODY4ZTFjMzg0YjFkNjJjZmIzYyJ9fX0=")
		,Slash("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5NWQ3YzFiYmYzYWZhMjg1ZDhkOTY3NTdiYjU1NzIyNTlhM2FlODU0ZjUzODlkYzUzMjA3Njk5ZDk0ZmQ4In19fQ==")
		
		/*,A("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjc0OGYyMTM1ODhkYmY0NDE1Y2UyNGZlNjZkZTM1MjY4MTZiZjM1ZGY4ZTM5OGY5OGVmZWMyZmIwODk1NmEzIn19fQ==")
		,B("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBkY2RjYTJhNDVlM2FiZjg4ZDYzYzQxNmZhZWNlYTlhYzI4NTQ4NGJlYmVkZWNiZWJiODA3NzkyMjg0MTdmIn19fQ==")
		,C("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzIzNDEwMzgyMWU3Y2M0NWNmZjlmNGNkYWJmMTViYjY3ODhiODI2NTVhYjAzNmYyYTBhMWY5ZDQ3MDEzYmUyZiJ9fX0=")
		,D("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzcyNWMzMmMzMzZmNzVmNzllNjYwYTZmZWViMzc1ZWNkNjFmYzhiY2M1ZTdhN2RlY2Y0ODU1Yzk5NTg5MCJ9fX0=")
		,E("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWQ4MTE1YjFhZGJiMzljZGJjYmNkMDM0ZTk1NjBkZTdkYWI0MDI1YmI0YmM0M2M0OWI0M2QxNWRkOGE3NyJ9fX0=")
		,F("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2JiNzVhM2VhMzNmYjY2MzZmMmJmYWZlODk3NjM4YmIyZGY5MTJmMjZmNWEyNGI2OTliNTBiMzFiNTQ3YSJ9fX0=")
		,G("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M0MjNhYjhkZjk3YmYxYjlhOWMxZDBlNDcxNjkwY2Y2NzdlNGZjYTEwYjM3NmM1ZTZjMDc2OTJiNTVkMTQifX19")
		,H("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWU5YWUzZTdlN2NiZDczOTUyNTE4OWE3YWMzYzkxZDI2MWZjMjlkMmVmZWRiNzAyMjY0ZDc0OWFlZDNkOTJhIn19fQ==")
		,I("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM3ODg5Yjk4ZjM1MWMyZGIxMmJmMWZmYTU3ZDA4Y2I2NDYwYzY0OWM5MDIxMzViMTMzZTI2OTYxYiJ9fX0=")
		,J("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJmYTJmNjVlNTQwZjg3NmNjOTk5NDY2ZDlmNTk2ZWExZjk5MzRmNjI1ODNjY2Y0Njc5YWJmNmIxOGQ0NDM4OSJ9fX0=")
		,K("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZkY2UyMzNhZWFiZjI5MTVmM2E2ODc1ZDI2ODIyOGZmOTA4MjM2YTU2M2UxZmRkMTRkNTliOTMxZDQ5OWIifX19")
		,L("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFlYWE5ZWY0N2NhZjZlNDRlNTlhYWU5OWJlZGQ4OTQ5ZTE0MjM2MjMwODAxZjM5ZWQyYTJlMjk3ODMifX19")
		,M("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWFhNTRkNzQyNDkyOTg5ZjczOWRlYjcyMjJhMjNiY2ZjZjg1ZTZjNzgyZmUyY2UwY2RlZWUwZjNmMmIwZWIifX19")
		,N("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzdkOGYyN2Y1N2ZmMzYyNmExMWQ3YmM1NDU2NjgzZjJjODkyNDg2YzJkOTZlOGU4NTZkNjJjYjc5YWE0YTA1YiJ9fX0=")
		,O("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjg5OWY0MzY4ZjYyMzZiOTlkNWY0NzkxODZlYTZhMzlkMTA4M2ZmMjA3ZWFjYTcxYWYxMDc0NWM0ZSJ9fX0=")
		,P("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWYyYWRjZTA5NTI0MTY4ZTg0YWM2YzhiN2YyOWM5ZTg3NDU3MTJjNzMxNThiOGQ1YTFjNmIxMmRmODdjY2UwIn19fQ==")
		,Q("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDNjMWFjNmRjMGViZmJmNGUzYWQzZThlODgzNTU2Mzk3ZDZjY2E3N2UyZDVhOWE0MzdiYmM2MjY1ODA4NiJ9fX0=")
		,R("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRlY2E2ZjkxMTUwZDE4YzliNjhhMTRkNjNkMzgyZTljNDIxMzJlZWU0M2Q2YTc4NmExMTVmMjU1Y2ZkNyJ9fX0=")
		,S("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjc0ZDI3M2RkN2IzODQxNzE1MTgyNWYxZmIyNDUwYzk0NjhhYzY0YzE0NmYxYmFkMjQ0MTZlZGIxOWI1In19fQ==")
		,T("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWY2Njc4MjA0NWZhYmQ0OTViOTdhODg2YTI2NmZkYTlmNjZhNTJiZTc5YWNlZGFiZGVhODcxMDM1OGUyMjg1In19fQ==")
		,U("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzVmNWU0YmI4OTFlNWI2ZDYwMTM1ZGQ2NzIxY2M2MzFjNDc2NGFjOGE5MzQyMDdmMzFkOWVlYjJiYzQ5YTdhIn19fQ==")
		,V("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQ0YjQ1MzExOGI3YWU5ZGRjYzc5YTgyODQxZDgxZWQzYWI3ZmE4N2ZmMjhlZmY0MWY0ODY3NWZkNGQifX19")
		,W("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzkzZTc1NWNiYjZmNmMyZGEzYmYxNjlhMmU3NWVmZjM0MTUxNjM3ZDIzOTFhNDFlODYxNzM1ZWI5ZWEwNjc1In19fQ==")
		,X("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODliYTNiODM0NzU1ZGUyNGRhNmE4M2NlMWZiN2Y5NzFmN2FiYjIwNTU4NWVmODIzYjJmYzQ3YWJkNTAyOCJ9fX0=")
		,Y("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg0Yjk2ZjFjMTEzNjY1ZTM5YTNjMjhlYTE4NWIzNmRkNWE3NWM3ZDY1MGYyYzk0NDE3MWRhZTk5ZGZiIn19fQ==")
		,Z("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGNkYjgwMTc1MTY5NjEzMjgwYThhMDZhMWM3NTFmYjcxYjY2ODllZGM4Y2UzZDhiOWQ5NmJkNDUwY2M4MSJ9fX0=")
		,Empty("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNhZjQ0NzMzNzIzNjQ0MjY0NDRlNDk2NzZmNmUzMzcyNDM4YWZhM2E2ZWIwNGEzOGFmOGU0MTI5NjVjMWI4In19fQ==")
*/
		,A("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTY3ZDgxM2FlN2ZmZTViZTk1MWE0ZjQxZjJhYTYxOWE1ZTM4OTRlODVlYTVkNDk4NmY4NDk0OWM2M2Q3NjcyZSJ9fX0=")
		,B("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBjMWI1ODRmMTM5ODdiNDY2MTM5Mjg1YjJmM2YyOGRmNjc4NzEyM2QwYjMyMjgzZDg3OTRlMzM3NGUyMyJ9fX0=")
		,C("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJlOTgzZWM0NzgwMjRlYzZmZDA0NmZjZGZhNDg0MjY3NjkzOTU1MWI0NzM1MDQ0N2M3N2MxM2FmMThlNmYifX19")
		,D("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5M2RjMGQ0YzVlODBmZjlhOGEwNWQyZmNmZTI2OTUzOWNiMzkyNzE5MGJhYzE5ZGEyZmNlNjFkNzEifX19")
		,E("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGJiMjczN2VjYmY5MTBlZmUzYjI2N2RiN2Q0YjMyN2YzNjBhYmM3MzJjNzdiZDBlNGVmZjFkNTEwY2RlZiJ9fX0=")
		,F("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjE4M2JhYjUwYTMyMjQwMjQ4ODZmMjUyNTFkMjRiNmRiOTNkNzNjMjQzMjU1OWZmNDllNDU5YjRjZDZhIn19fQ==")
		,G("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWNhM2YzMjRiZWVlZmI2YTBlMmM1YjNjNDZhYmM5MWNhOTFjMTRlYmE0MTlmYTQ3NjhhYzMwMjNkYmI0YjIifX19")
		,H("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFmMzQ2MmE0NzM1NDlmMTQ2OWY4OTdmODRhOGQ0MTE5YmM3MWQ0YTVkODUyZTg1YzI2YjU4OGE1YzBjNzJmIn19fQ==")
		,I("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDYxNzhhZDUxZmQ1MmIxOWQwYTM4ODg3MTBiZDkyMDY4ZTkzMzI1MmFhYzZiMTNjNzZlN2U2ZWE1ZDMyMjYifX19")
		,J("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E3OWRiOTkyMzg2N2U2OWMxZGJmMTcxNTFlNmY0YWQ5MmNlNjgxYmNlZGQzOTc3ZWViYmM0NGMyMDZmNDkifX19")
		,K("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ2MWIzOGM4ZTQ1NzgyYWRhNTlkMTYxMzJhNDIyMmMxOTM3NzhlN2Q3MGM0NTQyYzk1MzYzNzZmMzdiZTQyIn19fQ==")
		,L("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5ZjUwYjQzMmQ4NjhhZTM1OGUxNmY2MmVjMjZmMzU0MzdhZWI5NDkyYmNlMTM1NmM5YWE2YmIxOWEzODYifX19")
		,M("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDljNDVhMjRhYWFiZjQ5ZTIxN2MxNTQ4MzIwNDg0OGE3MzU4MmFiYTdmYWUxMGVlMmM1N2JkYjc2NDgyZiJ9fX0=")
		,N("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzViOGIzZDhjNzdkZmI4ZmJkMjQ5NWM4NDJlYWM5NGZmZmE2ZjU5M2JmMTVhMjU3NGQ4NTRkZmYzOTI4In19fQ==")
		,O("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDExZGUxY2FkYjJhZGU2MTE0OWU1ZGVkMWJkODg1ZWRmMGRmNjI1OTI1NWIzM2I1ODdhOTZmOTgzYjJhMSJ9fX0=")
		,P("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBhNzk4OWI1ZDZlNjIxYTEyMWVlZGFlNmY0NzZkMzUxOTNjOTdjMWE3Y2I4ZWNkNDM2MjJhNDg1ZGMyZTkxMiJ9fX0=")
		,Q("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM2MDlmMWZhZjgxZWQ0OWM1ODk0YWMxNGM5NGJhNTI5ODlmZGE0ZTFkMmE1MmZkOTQ1YTU1ZWQ3MTllZDQifX19")
		,R("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVjZWQ5OTMxYWNlMjNhZmMzNTEzNzEzNzliZjA1YzYzNWFkMTg2OTQzYmMxMzY0NzRlNGU1MTU2YzRjMzcifX19")
		,S("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0MWM2MDU3MmM1MzNlOTNjYTQyMTIyODkyOWU1NGQ2Yzg1NjUyOTQ1OTI0OWMyNWMzMmJhMzNhMWIxNTE3In19fQ==")
		,T("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU2MmU4YzFkNjZiMjFlNDU5YmU5YTI0ZTVjMDI3YTM0ZDI2OWJkY2U0ZmJlZTJmNzY3OGQyZDNlZTQ3MTgifX19")
		,U("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjA3ZmJjMzM5ZmYyNDFhYzNkNjYxOWJjYjY4MjUzZGZjM2M5ODc4MmJhZjNmMWY0ZWZkYjk1NGY5YzI2In19fQ==")
		,V("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2M5YTEzODYzOGZlZGI1MzRkNzk5Mjg4NzZiYWJhMjYxYzdhNjRiYTc5YzQyNGRjYmFmY2M5YmFjNzAxMGI4In19fQ==")
		,W("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY5YWQxYTg4ZWQyYjA3NGUxMzAzYTEyOWY5NGU0YjcxMGNmM2U1YjRkOTk1MTYzNTY3ZjY4NzE5YzNkOTc5MiJ9fX0=")
		,X("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE2Nzg3YmEzMjU2NGU3YzJmM2EwY2U2NDQ5OGVjYmIyM2I4OTg0NWU1YTY2YjVjZWM3NzM2ZjcyOWVkMzcifX19")
		,Y("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUyZmIzODhlMzMyMTJhMjQ3OGI1ZTE1YTk2ZjI3YWNhNmM2MmFjNzE5ZTFlNWY4N2ExY2YwZGU3YjE1ZTkxOCJ9fX0=")
		,Z("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA1ODJiOWI1ZDk3OTc0YjExNDYxZDYzZWNlZDg1ZjQzOGEzZWVmNWRjMzI3OWY5YzQ3ZTFlMzhlYTU0YWU4ZCJ9fX0=")
		,One("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiYzJiY2ZiMmJkMzc1OWU2YjFlODZmYzdhNzk1ODVlMTEyN2RkMzU3ZmMyMDI4OTNmOWRlMjQxYmM5ZTUzMCJ9fX0=")
		,Two("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkOWVlZWU4ODM0Njg4ODFkODM4NDhhNDZiZjMwMTI0ODVjMjNmNzU3NTNiOGZiZTg0ODczNDE0MTk4NDcifX19")
		,Three("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ0ZWFlMTM5MzM4NjBhNmRmNWU4ZTk1NTY5M2I5NWE4YzNiMTVjMzZiOGI1ODc1MzJhYzA5OTZiYzM3ZTUifX19")
		,Four("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJlNzhmYjIyNDI0MjMyZGMyN2I4MWZiY2I0N2ZkMjRjMWFjZjc2MDk4NzUzZjJkOWMyODU5ODI4N2RiNSJ9fX0=")
		,Five("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ1N2UzYmM4OGE2NTczMGUzMWExNGUzZjQxZTAzOGE1ZWNmMDg5MWE2YzI0MzY0M2I4ZTU0NzZhZTIifX19")
		,Six("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM0YjM2ZGU3ZDY3OWI4YmJjNzI1NDk5YWRhZWYyNGRjNTE4ZjVhZTIzZTcxNjk4MWUxZGNjNmIyNzIwYWIifX19")
		,Seven("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRiNmViMjVkMWZhYWJlMzBjZjQ0NGRjNjMzYjU4MzI0NzVlMzgwOTZiN2UyNDAyYTNlYzQ3NmRkN2I5In19fQ==")
		,Eight("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkxOTQ5NzNhM2YxN2JkYTk5NzhlZDYyNzMzODM5OTcyMjI3NzRiNDU0Mzg2YzgzMTljMDRmMWY0Zjc0YzJiNSJ9fX0=")
		,Nine("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3Y2FmNzU5MWIzOGUxMjVhODAxN2Q1OGNmYzY0MzNiZmFmODRjZDQ5OWQ3OTRmNDFkMTBiZmYyZTViODQwIn19fQ==")
		,Zero("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGViZTdlNTIxNTE2OWE2OTlhY2M2Y2VmYTdiNzNmZGIxMDhkYjg3YmI2ZGFlMjg0OWZiZTI0NzE0YjI3In19fQ==")
		,Locks("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGFkOTQzZDA2MzM0N2Y5NWFiOWU5ZmE3NTc5MmRhODRlYzY2NWViZDIyYjA1MGJkYmE1MTlmZjdkYTYxZGIifX19")
		,Powers("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM4Y2E2NjRmMDZkNDE0NjUwYWFjNWNkNDgyYzNiMGM3OWE2NjFiNWYxMWRjODUyMTQyNWJhNmU1NjllNSJ9fX0=")
		,Worlds("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzM0NjU2MTZjNzMxZDVhMGZjNTdjMjNmMzAxZjc2NWZlYmEyZDNkN2VmMTYxN2I1YjgyOGVmNzdlMCJ9fX0=")
		,Plots("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE0MDg4Mjk1NDQ3Njk3MjQ2YWEyMmFhMTM3NmI2MmJlMzY3ODNkZjhjOTExNDA0NzU0OWE0YTFhNTFjNyJ9fX0=")
		,Tasks("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY2OTJmOTljYzZkNzgyNDIzMDQxMTA1NTM1ODk0ODQyOThiMmU0YTAyMzNiNzY3NTNmODg4ZTIwN2VmNSJ9fX0=")
		,All("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjFjNjNkOWI5ZmQ4NzQyZWFlYjA0YzY5MjE3MmNiOWRhNDM3ODE2OThhNTc1Y2RhYmUxYzA0ZGYxMmMzZiJ9fX0=")
		,Users("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIyZDhlNzUxYzhmMmZkNGM4OTQyYzQ0YmRiMmY1Y2E0ZDhhZThlNTc1ZWQzZWIzNGMxOGE4NmU5M2IifX19")
		,Teleport("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=")
		,Portal("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBiZmMyNTc3ZjZlMjZjNmM2ZjczNjVjMmM0MDc2YmNjZWU2NTMxMjQ5ODkzODJjZTkzYmNhNGZjOWUzOWIifX19")
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
