/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggers
/*    */ {
/* 36 */   private static final Map<MinecraftKey, CriterionTrigger<?>> C = Maps.newHashMap();
/*    */   
/* 38 */   public static final CriterionTriggerImpossible a = a(new CriterionTriggerImpossible());
/* 39 */   public static final CriterionTriggerKilled b = a(new CriterionTriggerKilled(new MinecraftKey("player_killed_entity")));
/* 40 */   public static final CriterionTriggerKilled c = a(new CriterionTriggerKilled(new MinecraftKey("entity_killed_player")));
/* 41 */   public static final CriterionTriggerEnterBlock d = a(new CriterionTriggerEnterBlock());
/* 42 */   public static final CriterionTriggerInventoryChanged e = a(new CriterionTriggerInventoryChanged());
/* 43 */   public static final CriterionTriggerRecipeUnlocked f = a(new CriterionTriggerRecipeUnlocked());
/* 44 */   public static final CriterionTriggerPlayerHurtEntity g = a(new CriterionTriggerPlayerHurtEntity());
/* 45 */   public static final CriterionTriggerEntityHurtPlayer h = a(new CriterionTriggerEntityHurtPlayer());
/* 46 */   public static final CriterionTriggerEnchantedItem i = a(new CriterionTriggerEnchantedItem());
/* 47 */   public static final CriterionTriggerBrewedPotion j = a(new CriterionTriggerBrewedPotion());
/* 48 */   public static final CriterionTriggerConstructBeacon k = a(new CriterionTriggerConstructBeacon());
/* 49 */   public static final CriterionTriggerUsedEnderEye l = a(new CriterionTriggerUsedEnderEye());
/* 50 */   public static final CriterionTriggerSummonedEntity m = a(new CriterionTriggerSummonedEntity());
/* 51 */   public static final CriterionTriggerBredAnimals n = a(new CriterionTriggerBredAnimals());
/* 52 */   public static final CriterionTriggerLocation o = a(new CriterionTriggerLocation(new MinecraftKey("location")));
/* 53 */   public static final CriterionTriggerLocation p = a(new CriterionTriggerLocation(new MinecraftKey("slept_in_bed")));
/* 54 */   public static final CriterionTriggerCuredZombieVillager q = a(new CriterionTriggerCuredZombieVillager());
/* 55 */   public static final CriterionTriggerVillagerTrade r = a(new CriterionTriggerVillagerTrade());
/* 56 */   public static final CriterionTriggerItemDurabilityChanged s = a(new CriterionTriggerItemDurabilityChanged());
/* 57 */   public static final CriterionTriggerLevitation t = a(new CriterionTriggerLevitation());
/* 58 */   public static final CriterionTriggerChangedDimension u = a(new CriterionTriggerChangedDimension());
/* 59 */   public static final CriterionTriggerTick v = a(new CriterionTriggerTick());
/* 60 */   public static final CriterionTriggerTamedAnimal w = a(new CriterionTriggerTamedAnimal());
/* 61 */   public static final CriterionTriggerPlacedBlock x = a(new CriterionTriggerPlacedBlock());
/* 62 */   public static final CriterionTriggerConsumeItem y = a(new CriterionTriggerConsumeItem());
/* 63 */   public static final CriterionTriggerEffectsChanged z = a(new CriterionTriggerEffectsChanged());
/* 64 */   public static final CriterionTriggerUsedTotem A = a(new CriterionTriggerUsedTotem());
/* 65 */   public static final CriterionTriggerNetherTravel B = a(new CriterionTriggerNetherTravel());
/*    */   
/*    */   private static <T extends CriterionTrigger> T a(T paramT) {
/* 68 */     if (C.containsKey(paramT.a())) {
/* 69 */       throw new IllegalArgumentException("Duplicate criterion id " + paramT.a());
/*    */     }
/* 71 */     C.put(paramT.a(), (CriterionTrigger<?>)paramT);
/* 72 */     return paramT;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static <T extends CriterionInstance> CriterionTrigger<T> a(MinecraftKey paramMinecraftKey) {
/* 78 */     return (CriterionTrigger<T>)C.get(paramMinecraftKey);
/*    */   }
/*    */   
/*    */   public static Iterable<? extends CriterionTrigger<?>> a() {
/* 82 */     return C.values();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */