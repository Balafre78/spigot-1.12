/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionRandomChance
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final float a;
/*    */   
/*    */   public LootItemConditionRandomChance(float paramFloat) {
/* 16 */     this.a = paramFloat;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 21 */     return (paramRandom.nextFloat() < this.a);
/*    */   }
/*    */   
/*    */   public static class a extends LootItemCondition.a<LootItemConditionRandomChance> {
/*    */     protected a() {
/* 26 */       super(new MinecraftKey("random_chance"), LootItemConditionRandomChance.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemConditionRandomChance param1LootItemConditionRandomChance, JsonSerializationContext param1JsonSerializationContext) {
/* 31 */       param1JsonObject.addProperty("chance", Float.valueOf(LootItemConditionRandomChance.a(param1LootItemConditionRandomChance)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionRandomChance a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext) {
/* 36 */       return new LootItemConditionRandomChance(ChatDeserializer.l(param1JsonObject, "chance"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemConditionRandomChance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */