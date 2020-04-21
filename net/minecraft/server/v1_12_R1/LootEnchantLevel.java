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
/*    */ public class LootEnchantLevel
/*    */   extends LootItemFunction
/*    */ {
/*    */   private final LootValueBounds a;
/*    */   private final boolean b;
/*    */   
/*    */   public LootEnchantLevel(LootItemCondition[] paramArrayOfLootItemCondition, LootValueBounds paramLootValueBounds, boolean paramBoolean) {
/* 21 */     super(paramArrayOfLootItemCondition);
/* 22 */     this.a = paramLootValueBounds;
/* 23 */     this.b = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 28 */     return EnchantmentManager.a(paramRandom, paramItemStack, this.a.a(paramRandom), this.b);
/*    */   }
/*    */   
/*    */   public static class a extends LootItemFunction.a<LootEnchantLevel> {
/*    */     public a() {
/* 33 */       super(new MinecraftKey("enchant_with_levels"), LootEnchantLevel.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootEnchantLevel param1LootEnchantLevel, JsonSerializationContext param1JsonSerializationContext) {
/* 38 */       param1JsonObject.add("levels", param1JsonSerializationContext.serialize(LootEnchantLevel.a(param1LootEnchantLevel)));
/* 39 */       param1JsonObject.addProperty("treasure", Boolean.valueOf(LootEnchantLevel.b(param1LootEnchantLevel)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootEnchantLevel a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/* 44 */       LootValueBounds lootValueBounds = ChatDeserializer.<LootValueBounds>a(param1JsonObject, "levels", param1JsonDeserializationContext, LootValueBounds.class);
/* 45 */       boolean bool = ChatDeserializer.a(param1JsonObject, "treasure", false);
/* 46 */       return new LootEnchantLevel(param1ArrayOfLootItemCondition, lootValueBounds, bool);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootEnchantLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */