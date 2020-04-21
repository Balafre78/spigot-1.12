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
/*    */ public class LootItemFunctionSmelt
/*    */   extends LootItemFunction
/*    */ {
/* 17 */   private static final Logger a = LogManager.getLogger();
/*    */   
/*    */   public LootItemFunctionSmelt(LootItemCondition[] paramArrayOfLootItemCondition) {
/* 20 */     super(paramArrayOfLootItemCondition);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 25 */     if (paramItemStack.isEmpty()) {
/* 26 */       return paramItemStack;
/*    */     }
/* 28 */     ItemStack itemStack1 = RecipesFurnace.getInstance().getResult(paramItemStack);
/*    */     
/* 30 */     if (itemStack1.isEmpty()) {
/* 31 */       a.warn("Couldn't smelt {} because there is no smelting recipe", paramItemStack);
/* 32 */       return paramItemStack;
/*    */     } 
/* 34 */     ItemStack itemStack2 = itemStack1.cloneItemStack();
/* 35 */     itemStack2.setCount(paramItemStack.getCount());
/* 36 */     return itemStack2;
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunction.a<LootItemFunctionSmelt> {
/*    */     protected a() {
/* 42 */       super(new MinecraftKey("furnace_smelt"), LootItemFunctionSmelt.class);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemFunctionSmelt param1LootItemFunctionSmelt, JsonSerializationContext param1JsonSerializationContext) {}
/*    */ 
/*    */     
/*    */     public LootItemFunctionSmelt a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/* 51 */       return new LootItemFunctionSmelt(param1ArrayOfLootItemCondition);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctionSmelt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */