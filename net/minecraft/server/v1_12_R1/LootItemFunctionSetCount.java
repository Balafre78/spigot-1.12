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
/*    */ 
/*    */ public class LootItemFunctionSetCount
/*    */   extends LootItemFunction
/*    */ {
/*    */   private final LootValueBounds a;
/*    */   
/*    */   public LootItemFunctionSetCount(LootItemCondition[] paramArrayOfLootItemCondition, LootValueBounds paramLootValueBounds) {
/* 19 */     super(paramArrayOfLootItemCondition);
/* 20 */     this.a = paramLootValueBounds;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 25 */     paramItemStack.setCount(this.a.a(paramRandom));
/* 26 */     return paramItemStack;
/*    */   }
/*    */   
/*    */   public static class a extends LootItemFunction.a<LootItemFunctionSetCount> {
/*    */     protected a() {
/* 31 */       super(new MinecraftKey("set_count"), LootItemFunctionSetCount.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemFunctionSetCount param1LootItemFunctionSetCount, JsonSerializationContext param1JsonSerializationContext) {
/* 36 */       param1JsonObject.add("count", param1JsonSerializationContext.serialize(LootItemFunctionSetCount.a(param1LootItemFunctionSetCount)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetCount a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/* 41 */       return new LootItemFunctionSetCount(param1ArrayOfLootItemCondition, ChatDeserializer.<LootValueBounds>a(param1JsonObject, "count", param1JsonDeserializationContext, LootValueBounds.class));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctionSetCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */