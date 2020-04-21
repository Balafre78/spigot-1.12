/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionRandomChanceWithLooting
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final float a;
/*    */   private final float b;
/*    */   
/*    */   public LootItemConditionRandomChanceWithLooting(float paramFloat1, float paramFloat2) {
/* 19 */     this.a = paramFloat1;
/* 20 */     this.b = paramFloat2;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 25 */     int i = 0;
/* 26 */     if (paramLootTableInfo.c() instanceof EntityLiving) {
/* 27 */       i = EnchantmentManager.g((EntityLiving)paramLootTableInfo.c());
/*    */     }
/* 29 */     return (paramRandom.nextFloat() < this.a + i * this.b);
/*    */   }
/*    */   
/*    */   public static class a extends LootItemCondition.a<LootItemConditionRandomChanceWithLooting> {
/*    */     protected a() {
/* 34 */       super(new MinecraftKey("random_chance_with_looting"), LootItemConditionRandomChanceWithLooting.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemConditionRandomChanceWithLooting param1LootItemConditionRandomChanceWithLooting, JsonSerializationContext param1JsonSerializationContext) {
/* 39 */       param1JsonObject.addProperty("chance", Float.valueOf(LootItemConditionRandomChanceWithLooting.a(param1LootItemConditionRandomChanceWithLooting)));
/* 40 */       param1JsonObject.addProperty("looting_multiplier", Float.valueOf(LootItemConditionRandomChanceWithLooting.b(param1LootItemConditionRandomChanceWithLooting)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionRandomChanceWithLooting a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext) {
/* 45 */       return new LootItemConditionRandomChanceWithLooting(ChatDeserializer.l(param1JsonObject, "chance"), ChatDeserializer.l(param1JsonObject, "looting_multiplier"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemConditionRandomChanceWithLooting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */