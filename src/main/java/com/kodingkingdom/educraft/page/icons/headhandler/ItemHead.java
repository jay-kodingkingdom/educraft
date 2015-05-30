/*     */ package com.kodingkingdom.educraft.page.icons.headhandler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ public class ItemHead
/*     */ {
/*     */   private String DisplayName;
/*     */   private ItemStack item;
/*     */   private String Lore;
/*  14 */   private boolean restoresFood = false;
/*  15 */   private int foodRestore = 0;
/*     */   private String sound;
/*     */   private boolean RightClickToEat;
/*  18 */   private List Potions = new ArrayList();
/*     */   private boolean UseWithFoodAtMax;
/*     */   private UUID id;
/*     */   private String UNAME;
/*     */   private boolean isPlaceable;
/*     */   
/*     */   public ItemHead(UUID id, String UNAME) {
/*  25 */     this.id = id;
/*  26 */     this.UNAME = UNAME;
/*     */   }
/*     */   
/*     */   public UUID getID() {
/*  30 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getUNAME() {
/*  34 */     return this.UNAME;
/*     */   }
/*     */   
/*     */   public void setPotions(List potions) {
/*  38 */     this.Potions = potions;
/*     */   }
/*     */   
/*     */   public boolean hasPotions() {
/*  42 */     if (this.Potions.size() != 0) {
/*  43 */       return true;
/*     */     }
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   public void setDisplayName(String displayname) {
/*  49 */     this.DisplayName = displayname;
/*     */   }
/*     */   
/*     */   public String getDisplayName() {
/*  53 */     return this.DisplayName;
/*     */   }
/*     */   
/*     */   public void setLore(String lore) {
/*  57 */     this.Lore = lore;
/*     */   }
/*     */   
/*     */   public String getLore() {
/*  61 */     return this.Lore;
/*     */   }
/*     */   
/*     */   public void setItem(ItemStack item) {
/*  65 */     this.item = item;
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/*  69 */     return this.item;
/*     */   }
/*     */   
/*     */   public void setrestoresFood(boolean rf) {
/*  73 */     this.restoresFood = rf;
/*     */   }
/*     */   
/*     */   public boolean getRestoresFood() {
/*  77 */     return this.restoresFood;
/*     */   }
/*     */   
/*     */   public void setRightClickToEat(boolean rcf) {
/*  81 */     this.RightClickToEat = rcf;
/*     */   }
/*     */   
/*     */   public boolean getRightClickToEat() {
/*  85 */     return this.RightClickToEat;
/*     */   }
/*     */   
/*     */   public void setUseWithFoodAtMax(boolean max) {
/*  89 */     this.UseWithFoodAtMax = max;
/*     */   }
/*     */   
/*     */   public boolean getUseWithFoodAtMax() {
/*  93 */     return this.UseWithFoodAtMax;
/*     */   }
/*     */   
/*     */   public void setFoodRestore(int fr)
/*     */   {
/*  98 */     this.foodRestore = fr;
/*     */   }
/*     */   
/*     */   public int getFoodRestore() {
/* 102 */     return this.foodRestore;
/*     */   }
/*     */   
/*     */   public void setSound(String s) {
/* 106 */     s = s.trim();
/* 107 */     if ((!s.equals("")) && (!s.equalsIgnoreCase("NONE"))) {
/* 108 */       this.sound = s;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getSound() {
/* 113 */     return this.sound;
/*     */   }
/*     */   
/*     */   public void setPlacable(Boolean place) {
/* 117 */     this.isPlaceable = place.booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isPlaceable() {
/* 121 */     return this.isPlaceable;
/*     */   }
/*     */   
/*     */   public List<String> getPotions() {
/* 125 */     return this.Potions;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jay\Downloads\HeadItems.jar!\com\dan\HeadItems\HeadHandler\ItemHead.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */