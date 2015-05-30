/*    */ package com.kodingkingdom.educraft.page.icons.utils;
/*    */ 
/*    */ import java.text.NumberFormat;
/*    */ import java.text.ParsePosition;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ public class GenericUtil
/*    */ {
/*    */   public static boolean isNumeric(String str)
/*    */   {
/* 14 */     NumberFormat formatter = NumberFormat.getInstance();
/* 15 */     ParsePosition pos = new ParsePosition(0);
/* 16 */     formatter.parse(str, pos);
/* 17 */     return str.length() == pos.getIndex();
/*    */   }
/*    */   
/*    */   public static String ColorUtil(String s) {
/* 21 */     return ChatColor.translateAlternateColorCodes('&', s);
/*    */   }
/*    */   
/*    */   public static Material getMat(String input) {
/* 25 */     Material finalMat = null;
/*    */     try
/*    */     {
/* 28 */       finalMat = Material.valueOf(input);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */       try {
/* 33 */         int id = Integer.parseInt(input);
/* 34 */         finalMat = Material.getMaterial(id);
/*    */       }
/*    */       catch (Exception x) {
/* 37 */         return null;
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 42 */     return finalMat;
/*    */   }
/*    */   
/*    */   public static int convertPotionToNumber(String potion) {
/* 46 */     int num = -1;
/* 47 */     if (potion.equalsIgnoreCase("SPEED"))
/* 48 */       return 1;
/* 49 */     if ((potion.equalsIgnoreCase("SLOW")) || (potion.equalsIgnoreCase("SLOWNESS")))
/* 50 */       return 2;
/* 51 */     if (potion.equalsIgnoreCase("HASTE"))
/* 52 */       return 3;
/* 53 */     if ((potion.equalsIgnoreCase("MINING_FATIGUE")) || (potion.equalsIgnoreCase("MININGFATIGUE")))
/* 54 */       return 4;
/* 55 */     if ((potion.equalsIgnoreCase("STRENGTH")) || (potion.equalsIgnoreCase("STR")))
/* 56 */       return 5;
/* 57 */     if ((potion.equalsIgnoreCase("INSTANTHEALTH")) || (potion.equalsIgnoreCase("INSTANT_HEALTH")) || (potion.equalsIgnoreCase("HEALTH")))
/* 58 */       return 6;
/* 59 */     if ((potion.equalsIgnoreCase("INSTANTDAMAGE")) || (potion.equalsIgnoreCase("INSTANT_DAMAGE")) || (potion.equalsIgnoreCase("DAMAGE")))
/* 60 */       return 7;
/* 61 */     if (potion.equalsIgnoreCase("NAUSEA"))
/* 62 */       return 9;
/* 63 */     if ((potion.equalsIgnoreCase("REGENERATION")) || (potion.equalsIgnoreCase("REGEN")))
/* 64 */       return 10;
/* 65 */     if (potion.equalsIgnoreCase("RESISTANCE"))
/* 66 */       return 11;
/* 67 */     if ((potion.equalsIgnoreCase("FIRE_RESISTANCE")) || (potion.equalsIgnoreCase("FIRERESISTANCE")) || (potion.equalsIgnoreCase("FIRE")))
/* 68 */       return 12;
/* 69 */     if ((potion.equalsIgnoreCase("WATER_BREATHING")) || (potion.equalsIgnoreCase("WATERBREATHING")) || (potion.equalsIgnoreCase("WATER")))
/* 70 */       return 13;
/* 71 */     if (potion.equalsIgnoreCase("INVISIBILITY"))
/* 72 */       return 14;
/* 73 */     if ((potion.equalsIgnoreCase("BLINDNESS")) || (potion.equalsIgnoreCase("BLIND")))
/* 74 */       return 15;
/* 75 */     if ((potion.equalsIgnoreCase("NIGHT_VISION")) || (potion.equalsIgnoreCase("NIGHTVISION")))
/* 76 */       return 16;
/* 77 */     if (potion.equalsIgnoreCase("HUNGER"))
/* 78 */       return 17;
/* 79 */     if (potion.equalsIgnoreCase("WEAKNESS"))
/* 80 */       return 18;
/* 81 */     if (potion.equalsIgnoreCase("POISON"))
/* 82 */       return 19;
/* 83 */     if (potion.equalsIgnoreCase("WITHER"))
/* 84 */       return 20;
/* 85 */     if ((potion.equalsIgnoreCase("HEALTH_BOOST")) || (potion.equalsIgnoreCase("HEALTHBOOST")))
/* 86 */       return 21;
/* 87 */     if (potion.equalsIgnoreCase("ABSORPTION"))
/* 88 */       return 22;
/* 89 */     if (potion.equalsIgnoreCase("SATURATION"))
/* 90 */       return 23;
/* 91 */     return num;
/*    */   }
/*    */   
/*    */   public static boolean PlayerhasFreeSpace(Player p) {
/* 95 */     int am = p.getInventory().firstEmpty();
/* 96 */     if (am != -1) {
/* 97 */       return true;
/*    */     }
/* 99 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jay\Downloads\HeadItems.jar!\com\dan\HeadItems\Utils\GenericUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */