/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MobEffects
/*    */ {
/*    */   static {
/* 10 */     if (!DispenserRegistry.a()) {
/* 11 */       throw new RuntimeException("Accessed MobEffects before Bootstrap!");
/*    */     }
/*    */   }
/*    */   
/* 15 */   public static final MobEffectList FASTER_MOVEMENT = a("speed");
/* 16 */   public static final MobEffectList SLOWER_MOVEMENT = a("slowness");
/* 17 */   public static final MobEffectList FASTER_DIG = a("haste");
/* 18 */   public static final MobEffectList SLOWER_DIG = a("mining_fatigue");
/* 19 */   public static final MobEffectList INCREASE_DAMAGE = a("strength");
/* 20 */   public static final MobEffectList HEAL = a("instant_health");
/* 21 */   public static final MobEffectList HARM = a("instant_damage");
/* 22 */   public static final MobEffectList JUMP = a("jump_boost");
/* 23 */   public static final MobEffectList CONFUSION = a("nausea");
/* 24 */   public static final MobEffectList REGENERATION = a("regeneration");
/* 25 */   public static final MobEffectList RESISTANCE = a("resistance");
/* 26 */   public static final MobEffectList FIRE_RESISTANCE = a("fire_resistance");
/* 27 */   public static final MobEffectList WATER_BREATHING = a("water_breathing");
/* 28 */   public static final MobEffectList INVISIBILITY = a("invisibility");
/* 29 */   public static final MobEffectList BLINDNESS = a("blindness");
/* 30 */   public static final MobEffectList NIGHT_VISION = a("night_vision");
/* 31 */   public static final MobEffectList HUNGER = a("hunger");
/* 32 */   public static final MobEffectList WEAKNESS = a("weakness");
/* 33 */   public static final MobEffectList POISON = a("poison");
/* 34 */   public static final MobEffectList WITHER = a("wither");
/* 35 */   public static final MobEffectList HEALTH_BOOST = a("health_boost");
/* 36 */   public static final MobEffectList ABSORBTION = a("absorption");
/* 37 */   public static final MobEffectList SATURATION = a("saturation");
/* 38 */   public static final MobEffectList GLOWING = a("glowing");
/* 39 */   public static final MobEffectList LEVITATION = a("levitation");
/* 40 */   public static final MobEffectList z = a("luck");
/* 41 */   public static final MobEffectList A = a("unluck");
/*    */   
/*    */   @Nullable
/*    */   private static MobEffectList a(String paramString) {
/* 45 */     MobEffectList mobEffectList = MobEffectList.REGISTRY.get(new MinecraftKey(paramString));
/* 46 */     if (mobEffectList == null) {
/* 47 */       throw new IllegalStateException("Invalid MobEffect requested: " + paramString);
/*    */     }
/* 49 */     return mobEffectList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */