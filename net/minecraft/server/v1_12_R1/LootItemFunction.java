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
/*    */ public abstract class LootItemFunction
/*    */ {
/*    */   private final LootItemCondition[] a;
/*    */   
/*    */   protected LootItemFunction(LootItemCondition[] paramArrayOfLootItemCondition) {
/* 17 */     this.a = paramArrayOfLootItemCondition;
/*    */   }
/*    */   
/*    */   public abstract ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo);
/*    */   
/*    */   public LootItemCondition[] a() {
/* 23 */     return this.a;
/*    */   }
/*    */   
/*    */   public static abstract class a<T extends LootItemFunction> {
/*    */     private final MinecraftKey a;
/*    */     private final Class<T> b;
/*    */     
/*    */     protected a(MinecraftKey param1MinecraftKey, Class<T> param1Class) {
/* 31 */       this.a = param1MinecraftKey;
/* 32 */       this.b = param1Class;
/*    */     }
/*    */     
/*    */     public MinecraftKey a() {
/* 36 */       return this.a;
/*    */     }
/*    */     
/*    */     public Class<T> b() {
/* 40 */       return this.b;
/*    */     }
/*    */     
/*    */     public abstract void a(JsonObject param1JsonObject, T param1T, JsonSerializationContext param1JsonSerializationContext);
/*    */     
/*    */     public abstract T b(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */