/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public interface LootItemCondition
/*    */ {
/*    */   boolean a(Random paramRandom, LootTableInfo paramLootTableInfo);
/*    */   
/*    */   public static abstract class a<T extends LootItemCondition>
/*    */   {
/*    */     private final MinecraftKey a;
/*    */     private final Class<T> b;
/*    */     
/*    */     protected a(MinecraftKey param1MinecraftKey, Class<T> param1Class) {
/* 19 */       this.a = param1MinecraftKey;
/* 20 */       this.b = param1Class;
/*    */     }
/*    */     
/*    */     public MinecraftKey a() {
/* 24 */       return this.a;
/*    */     }
/*    */     
/*    */     public Class<T> b() {
/* 28 */       return this.b;
/*    */     }
/*    */     
/*    */     public abstract void a(JsonObject param1JsonObject, T param1T, JsonSerializationContext param1JsonSerializationContext);
/*    */     
/*    */     public abstract T b(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */