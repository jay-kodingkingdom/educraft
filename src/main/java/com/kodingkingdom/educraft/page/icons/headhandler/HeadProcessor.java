/*    */ package com.kodingkingdom.educraft.page.icons.headhandler;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.properties.Property;
/*    */ import com.mojang.authlib.properties.PropertyMap;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Skull;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.inventory.meta.SkullMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeadProcessor
/*    */ {
/*    */   public ItemStack CreateHead(UUID id, String displayName, List<String> displayLore, String texture)
/*    */   {
/* 24 */     GameProfile profile = createGameProfile(texture, id);
/* 25 */     ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 26 */     ItemMeta headMeta = head.getItemMeta();
/*    */     
/* 28 */     Class<?> headMetaClass = headMeta.getClass();
/*    */     
/* 30 */     if (!Reflection.set(headMetaClass, headMeta, "profile", 
/* 31 */       profile, 
/* 32 */       "Unable to inject profile")) {
/* 33 */       return null;
/*    */     }
/*    */     
/* 36 */     head.setItemMeta(headMeta);
/* 37 */     SkullMeta skullMeta = (SkullMeta)head.getItemMeta();
/* 38 */     skullMeta.setDisplayName(displayName);
/* 39 */     skullMeta.setLore(displayLore);
/* 40 */     head.setItemMeta(skullMeta);
/*    */     
/* 42 */     return head;
/*    */   }
/*    */   
/*    */   public GameProfile createGameProfile(String texture, UUID id)
/*    */   {
/* 47 */     GameProfile profile = new GameProfile(id, null);
/* 48 */     PropertyMap propertyMap = profile.getProperties();
/* 49 */     if (propertyMap == null)
/*    */     {
/* 51 */       Bukkit.getLogger().log(Level.INFO, "No property map found in GameProfile, can't continue.");
/* 52 */       return null;
/*    */     }
/* 54 */     propertyMap.put("textures", new Property("textures", texture));
/* 55 */     propertyMap.put("Signature", new Property("Signature", "1234"));
/* 56 */     return profile;
/*    */   }
/*    */   
/*    */   public static UUID getNamePropertyfromSkull(Skull head) {
/* 60 */     GameProfile profile = new GameProfile(UUID.randomUUID(), null);
/* 61 */     Class<?> headSkullClass = head.getClass();
/*    */     
/* 63 */     profile = (GameProfile)Reflection.get(headSkullClass, profile.getClass(), head, "profile", "Unable to inject profile");
/*    */     
/* 65 */     if (profile == null)
/* 66 */       return null;
/* 67 */     return profile.getId();
/*    */   }
/*    */   
/*    */   public static UUID getNameProperty(ItemStack head) {
/* 71 */     GameProfile profile = new GameProfile(UUID.randomUUID(), null);
/* 72 */     ItemMeta headMeta = head.getItemMeta();
/* 73 */     Class<?> headMetaClass = headMeta.getClass();
/* 74 */     profile = (GameProfile)Reflection.get(headMetaClass, profile.getClass(), headMeta, "profile", "Unable to inject profile");
/*    */     
/* 76 */     if (profile == null)
/* 77 */       return null;
/* 78 */     return profile.getId();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jay\Downloads\HeadItems.jar!\com\dan\HeadItems\HeadHandler\HeadProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */