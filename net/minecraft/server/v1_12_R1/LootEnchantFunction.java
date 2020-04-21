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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootEnchantFunction
/*    */   extends LootItemFunction
/*    */ {
/*    */   private final LootValueBounds a;
/*    */   private final int b;
/*    */   
/*    */   public LootEnchantFunction(LootItemCondition[] paramArrayOfLootItemCondition, LootValueBounds paramLootValueBounds, int paramInt) {
/* 23 */     super(paramArrayOfLootItemCondition);
/* 24 */     this.a = paramLootValueBounds;
/* 25 */     this.b = paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 30 */     Entity entity = paramLootTableInfo.c();
/*    */     
/* 32 */     if (entity instanceof EntityLiving) {
/* 33 */       int i = EnchantmentManager.g((EntityLiving)entity);
/* 34 */       if (i == 0) {
/* 35 */         return paramItemStack;
/*    */       }
/* 37 */       float f = i * this.a.b(paramRandom);
/* 38 */       paramItemStack.add(Math.round(f));
/*    */       
/* 40 */       if (this.b != 0 && paramItemStack.getCount() > this.b) {
/* 41 */         paramItemStack.setCount(this.b);
/*    */       }
/*    */     } 
/*    */     
/* 45 */     return paramItemStack;
/*    */   }
/*    */   
/*    */   public static class a extends LootItemFunction.a<LootEnchantFunction> {
/*    */     protected a() {
/* 50 */       super(new MinecraftKey("looting_enchant"), LootEnchantFunction.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootEnchantFunction param1LootEnchantFunction, JsonSerializationContext param1JsonSerializationContext) {
/* 55 */       param1JsonObject.add("count", param1JsonSerializationContext.serialize(LootEnchantFunction.a(param1LootEnchantFunction)));
/* 56 */       if (LootEnchantFunction.b(param1LootEnchantFunction) > 0) {
/* 57 */         param1JsonObject.add("limit", param1JsonSerializationContext.serialize(Integer.valueOf(LootEnchantFunction.b(param1LootEnchantFunction))));
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public LootEnchantFunction a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/* 63 */       int i = ChatDeserializer.a(param1JsonObject, "limit", 0);
/* 64 */       return new LootEnchantFunction(param1ArrayOfLootItemCondition, ChatDeserializer.<LootValueBounds>a(param1JsonObject, "count", param1JsonDeserializationContext, LootValueBounds.class), i);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootEnchantFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */