/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Enchantments
/*    */ {
/* 10 */   public static final Enchantment PROTECTION_ENVIRONMENTAL = a("protection");
/* 11 */   public static final Enchantment PROTECTION_FIRE = a("fire_protection");
/* 12 */   public static final Enchantment PROTECTION_FALL = a("feather_falling");
/* 13 */   public static final Enchantment PROTECTION_EXPLOSIONS = a("blast_protection");
/* 14 */   public static final Enchantment PROTECTION_PROJECTILE = a("projectile_protection");
/* 15 */   public static final Enchantment OXYGEN = a("respiration");
/* 16 */   public static final Enchantment WATER_WORKER = a("aqua_affinity");
/* 17 */   public static final Enchantment THORNS = a("thorns");
/* 18 */   public static final Enchantment DEPTH_STRIDER = a("depth_strider");
/* 19 */   public static final Enchantment j = a("frost_walker");
/* 20 */   public static final Enchantment k = a("binding_curse");
/*    */   
/* 22 */   public static final Enchantment DAMAGE_ALL = a("sharpness");
/* 23 */   public static final Enchantment DAMAGE_UNDEAD = a("smite");
/* 24 */   public static final Enchantment DAMAGE_ARTHROPODS = a("bane_of_arthropods");
/* 25 */   public static final Enchantment KNOCKBACK = a("knockback");
/* 26 */   public static final Enchantment FIRE_ASPECT = a("fire_aspect");
/* 27 */   public static final Enchantment LOOT_BONUS_MOBS = a("looting");
/* 28 */   public static final Enchantment r = a("sweeping");
/*    */   
/* 30 */   public static final Enchantment DIG_SPEED = a("efficiency");
/* 31 */   public static final Enchantment SILK_TOUCH = a("silk_touch");
/* 32 */   public static final Enchantment DURABILITY = a("unbreaking");
/* 33 */   public static final Enchantment LOOT_BONUS_BLOCKS = a("fortune");
/*    */   
/* 35 */   public static final Enchantment ARROW_DAMAGE = a("power");
/* 36 */   public static final Enchantment ARROW_KNOCKBACK = a("punch");
/* 37 */   public static final Enchantment ARROW_FIRE = a("flame");
/* 38 */   public static final Enchantment ARROW_INFINITE = a("infinity");
/*    */   
/* 40 */   public static final Enchantment LUCK = a("luck_of_the_sea");
/* 41 */   public static final Enchantment LURE = a("lure");
/*    */   
/* 43 */   public static final Enchantment C = a("mending");
/* 44 */   public static final Enchantment D = a("vanishing_curse");
/*    */   
/*    */   static {
/* 47 */     if (!DispenserRegistry.a()) {
/* 48 */       throw new RuntimeException("Accessed Enchantments before Bootstrap!");
/*    */     }
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private static Enchantment a(String paramString) {
/* 54 */     Enchantment enchantment = Enchantment.enchantments.get(new MinecraftKey(paramString));
/* 55 */     if (enchantment == null) {
/* 56 */       throw new IllegalStateException("Invalid Enchantment requested: " + paramString);
/*    */     }
/* 58 */     return enchantment;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Enchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */