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
/*    */ 
/*    */ public class LootItemFunctionSetDamage
/*    */   extends LootItemFunction
/*    */ {
/* 19 */   private static final Logger a = LogManager.getLogger();
/*    */   
/*    */   private final LootValueBounds b;
/*    */   
/*    */   public LootItemFunctionSetDamage(LootItemCondition[] paramArrayOfLootItemCondition, LootValueBounds paramLootValueBounds) {
/* 24 */     super(paramArrayOfLootItemCondition);
/* 25 */     this.b = paramLootValueBounds;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 30 */     if (paramItemStack.f()) {
/* 31 */       float f = 1.0F - this.b.b(paramRandom);
/* 32 */       paramItemStack.setData(MathHelper.d(f * paramItemStack.k()));
/*    */     } else {
/* 34 */       a.warn("Couldn't set damage of loot item {}", paramItemStack);
/*    */     } 
/* 36 */     return paramItemStack;
/*    */   }
/*    */   
/*    */   public static class a extends LootItemFunction.a<LootItemFunctionSetDamage> {
/*    */     protected a() {
/* 41 */       super(new MinecraftKey("set_damage"), LootItemFunctionSetDamage.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemFunctionSetDamage param1LootItemFunctionSetDamage, JsonSerializationContext param1JsonSerializationContext) {
/* 46 */       param1JsonObject.add("damage", param1JsonSerializationContext.serialize(LootItemFunctionSetDamage.a(param1LootItemFunctionSetDamage)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetDamage a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/* 51 */       return new LootItemFunctionSetDamage(param1ArrayOfLootItemCondition, ChatDeserializer.<LootValueBounds>a(param1JsonObject, "damage", param1JsonDeserializationContext, LootValueBounds.class));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctionSetDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */