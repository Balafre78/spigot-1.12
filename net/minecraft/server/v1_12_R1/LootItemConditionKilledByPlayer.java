/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionKilledByPlayer
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final boolean a;
/*    */   
/*    */   public LootItemConditionKilledByPlayer(boolean paramBoolean) {
/* 16 */     this.a = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 21 */     boolean bool = (paramLootTableInfo.b() != null) ? true : false;
/* 22 */     return (bool == (!this.a ? true : false));
/*    */   }
/*    */   
/*    */   public static class a extends LootItemCondition.a<LootItemConditionKilledByPlayer> {
/*    */     protected a() {
/* 27 */       super(new MinecraftKey("killed_by_player"), LootItemConditionKilledByPlayer.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemConditionKilledByPlayer param1LootItemConditionKilledByPlayer, JsonSerializationContext param1JsonSerializationContext) {
/* 32 */       param1JsonObject.addProperty("inverse", Boolean.valueOf(LootItemConditionKilledByPlayer.a(param1LootItemConditionKilledByPlayer)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionKilledByPlayer a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext) {
/* 37 */       return new LootItemConditionKilledByPlayer(ChatDeserializer.a(param1JsonObject, "inverse", false));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemConditionKilledByPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */