/*     */ package com.kodingkingdom.educraft.page.icons.headhandler;
/*     */ 
///*     */ import com.dan.HeadItems.HeadItems;
/*     */ import com.kodingkingdom.educraft.page.icons.utils.GenericUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.ShapedRecipe;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class ItemHandler
/*     */ {
/*     */   private FileConfiguration config;
/*     */   private YamlConfiguration IDconfig;
/*     */   private HeadProcessor hc;
/*     */   Plugin plugin;
/*     */   
/*     */   public ItemHandler(Plugin plugin, FileConfiguration config)
/*     */   {
/*  33 */     this.config = config;
/*  34 */     this.plugin = plugin;
/*  35 */     LoadIDconfig();
/*  36 */     this.hc = new HeadProcessor();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addItem(String name)
/*     */   {
/*  43 */     UUID id = getUUID(name);
/*  44 */     ItemStack is = getItemStack(id, name);
/*  45 */     addRecipe(is, name);
/*  46 */     addItemHeadObject(name, id, is);
/*     */   }
/*     */   
/*     */ 
/*     */   private void addItemHeadObject(String name, UUID id, ItemStack is)
/*     */   {
/*  52 */     ItemHead im = new ItemHead(id, name);
/*  53 */     String itemTree = "Items." + name;
/*  54 */     im.setItem(is);
/*  55 */     im.setrestoresFood(this.config.getBoolean(itemTree + ".isFoodORPotion"));
/*  56 */     im.setRightClickToEat(this.config.getBoolean(itemTree + ".RightClickToUse"));
/*  57 */     im.setUseWithFoodAtMax(this.config.getBoolean(itemTree + ".UseWithFoodAtMax"));
/*  58 */     im.setFoodRestore(this.config.getInt(itemTree + ".FeedAmount"));
/*  59 */     im.setSound(this.config.getString(itemTree + ".Sound"));
/*  60 */     im.setPlacable(Boolean.valueOf(this.config.getBoolean(itemTree + ".Placable")));
/*  61 */     im.setDisplayName(this.config.getString(String.valueOf(itemTree) + ".DisplayName"));
/*     */     try
/*     */     {
/*  64 */       List<String> potions = this.config.getStringList(itemTree + ".Potions");
/*  65 */       if (potions.size() != 0) {
/*  66 */         im.setPotions(potions);
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {}
/*     */     
/*     */ 
/*  72 */     //HeadItems.ItemMap.put(im.getID(), im);
/*     */   }
/*     */   
/*     */   private void addRecipe(ItemStack is, String name)
/*     */   {
/*  77 */     name = "Items." + name;
/*     */     
/*     */ 
/*  80 */     if (!this.config.getBoolean(name + ".Recipe.Enabled")) {
/*  81 */       return;
/*     */     }
/*  83 */     ShapedRecipe craftitem = new ShapedRecipe(is);
/*     */     
/*  85 */     List<String> crafting = this.config.getStringList(name + ".Recipe.Pattern");
/*  86 */     for (String ingredient : crafting) {
/*  87 */       ingredient.replaceAll("%", " ");
/*     */     }
/*  89 */     craftitem.shape(new String[] { (String)crafting.get(0), (String)crafting.get(1), (String)crafting.get(2) });
/*  90 */     Set<String> ingredients = this.config.getConfigurationSection(name + ".Recipe.Ingredients").getKeys(false);
/*  91 */     for (String ingredient2 : ingredients) {
/*  92 */       char c = ingredient2.charAt(0);
/*  93 */       String mat = this.config.getString(name + 
/*  94 */         ".Recipe.Ingredients." + ingredient2);
/*  95 */       String[] spl = mat.split("#");
/*  96 */       if (spl.length < 2) {
/*  97 */         craftitem.setIngredient(c, GenericUtil.getMat(spl[0]));
/*     */       } else {
/*  99 */         craftitem.setIngredient(c, 
/* 100 */           new MaterialData(GenericUtil.getMat(spl[0]), 
/* 101 */           Byte.parseByte(spl[1])));
/*     */       }
/*     */     }
/* 104 */     org.bukkit.Bukkit.getServer().addRecipe(craftitem);
/*     */   }
/*     */   
/*     */   private ItemStack getItemStack(UUID id, String name) {
/* 108 */     String itemTree = "Items." + name;
/*     */     
/* 110 */     String displayName = ChatColor.RESET + GenericUtil.ColorUtil(this.config.getString(new StringBuilder(String.valueOf(itemTree)).append(".DisplayName").toString()));
/* 111 */     List<String> displayLore = this.config.getStringList(itemTree + ".DisplayLore");
/* 112 */     List<String> newLore = new ArrayList();
/* 113 */     for (String s : displayLore) {
/* 114 */       newLore.add(GenericUtil.ColorUtil(s));
/*     */     }
/*     */     
/* 117 */     String texture = this.config.getString(itemTree + ".Texture");
/* 118 */     return this.hc.CreateHead(id, displayName, newLore, texture);
/*     */   }
/*     */   
/*     */   protected UUID getUUID(String name)
/*     */   {
/* 123 */     UUID finalID = null;
/*     */     
/*     */ 
/* 126 */     finalID = getExistingUUID(name);
/*     */     
/* 128 */     if (finalID != null) {
/* 129 */       return finalID;
/*     */     }
/*     */     
/* 132 */     finalID = UUID.randomUUID();
/*     */     
/*     */     do
/*     */     {
/* 136 */       finalID = UUID.randomUUID();
/* 135 */     } while (
/*     */     
/*     */ 
/* 138 */       HeadItems.ItemMap.containsKey(finalID));
/*     */     
/*     */ 
/* 141 */     this.IDconfig.set("IDs." + name, finalID.toString());
/*     */     
/*     */ 
/* 144 */     return finalID;
/*     */   }
/*     */   
/*     */   private UUID getExistingUUID(String name) {
/* 148 */     String existing = this.IDconfig.getString("IDs." + name);
/* 149 */     if (existing == null) {
/* 150 */       return null;
/*     */     }
/*     */     try {
/* 153 */       return UUID.fromString(existing);
/*     */     }
/*     */     catch (Exception e) {}
/* 156 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ItemStack FindItem(String name)
/*     */   {
/* 164 */     for (Map.Entry<UUID, ItemHead> entry : HeadItems.ItemMap.entrySet()) {
/* 165 */       ItemHead im = (ItemHead)entry.getValue();
/* 166 */       if ((name.equalsIgnoreCase(im.getDisplayName())) || (name.equalsIgnoreCase(im.getUNAME()))) {
/* 167 */         return im.getItem();
/*     */       }
/*     */     }
/* 170 */     return null;
/*     */   }
/*     */   
/*     */   public static ItemHead FindItemHead(String name) {
/* 174 */     for (Map.Entry<UUID, ItemHead> entry : HeadItems.ItemMap.entrySet()) {
/* 175 */       ItemHead im = (ItemHead)entry.getValue();
/* 176 */       if ((name.equalsIgnoreCase(im.getDisplayName())) || (name.equalsIgnoreCase(im.getUNAME()))) {
/* 177 */         return im;
/*     */       }
/*     */     }
/* 180 */     return null;
/*     */   }
/*     */   
/*     */   public void cleanUUID()
/*     */   {
/* 185 */     Set<String> ids = this.IDconfig.getConfigurationSection("IDs").getKeys(false);
/* 186 */     for (String s : ids) {
/* 187 */       UUID id = UUID.fromString(this.IDconfig.getString("IDs." + s));
/* 188 */       if (!HeadItems.ItemMap.containsKey(id)) {
/* 189 */         this.IDconfig.set("IDs." + s, null);
/*     */       }
/*     */     }
/* 192 */     saveIDconfig();
/*     */   }
/*     */   
/*     */ 
/*     */   private void LoadIDconfig()
/*     */   {
/* 198 */     this.IDconfig = new YamlConfiguration();
/* 199 */     File file = new File(this.plugin.getDataFolder(), "uuids.dat");
/* 200 */     if (file.exists()) {
/*     */       try {
/* 202 */         this.IDconfig.load(file);
/*     */       }
/*     */       catch (IOException|InvalidConfigurationException e) {
/* 205 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void saveIDconfig()
/*     */   {
/* 213 */     File file = new File(this.plugin.getDataFolder(), "uuids.dat");
/*     */     try {
/* 215 */       this.IDconfig.save(file);
/*     */     } catch (IOException e) {
/* 217 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Jay\Downloads\HeadItems.jar!\com\dan\HeadItems\HeadHandler\ItemHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */