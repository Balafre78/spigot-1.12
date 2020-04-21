/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSetData
/*    */   extends LootItemFunction
/*    */ {
/* 18 */   private static final Logger a = LogManager.getLogger();
/*    */   
/*    */   private final LootValueBounds b;
/*    */   
/*    */   public LootItemFunctionSetData(LootItemCondition[] paramArrayOfLootItemCondition, LootValueBounds paramLootValueBounds) {
/* 23 */     super(paramArrayOfLootItemCondition);
/* 24 */     this.b = paramLootValueBounds;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 29 */     if (paramItemStack.f()) {
/* 30 */       a.warn("Couldn't set data of loot item {}", paramItemStack);
/*    */     } else {
/* 32 */       paramItemStack.setData(this.b.a(paramRandom));
/*    */     } 
/* 34 */     return paramItemStack;
/*    */   }
/*    */   
/*    */   public static class a extends LootItemFunction.a<LootItemFunctionSetData> {
/*    */     protected a() {
/* 39 */       super(new MinecraftKey("set_data"), LootItemFunctionSetData.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemFunctionSetData param1LootItemFunctionSetData, JsonSerializationContext param1JsonSerializationContext) {
/* 44 */       param1JsonObject.add("data", param1JsonSerializationContext.serialize(LootItemFunctionSetData.a(param1LootItemFunctionSetData)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetData a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/* 49 */       return new LootItemFunctionSetData(param1ArrayOfLootItemCondition, ChatDeserializer.<LootValueBounds>a(param1JsonObject, "data", param1JsonDeserializationContext, LootValueBounds.class));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctionSetData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */