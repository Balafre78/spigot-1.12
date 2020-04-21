/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootEntityPropertyOnFire
/*    */   implements LootEntityProperty
/*    */ {
/*    */   private final boolean a;
/*    */   
/*    */   public LootEntityPropertyOnFire(boolean paramBoolean) {
/* 17 */     this.a = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Random paramRandom, Entity paramEntity) {
/* 22 */     return (paramEntity.isBurning() == this.a);
/*    */   }
/*    */   
/*    */   public static class a extends LootEntityProperty.a<LootEntityPropertyOnFire> {
/*    */     protected a() {
/* 27 */       super(new MinecraftKey("on_fire"), LootEntityPropertyOnFire.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement a(LootEntityPropertyOnFire param1LootEntityPropertyOnFire, JsonSerializationContext param1JsonSerializationContext) {
/* 32 */       return (JsonElement)new JsonPrimitive(Boolean.valueOf(LootEntityPropertyOnFire.a(param1LootEntityPropertyOnFire)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootEntityPropertyOnFire b(JsonElement param1JsonElement, JsonDeserializationContext param1JsonDeserializationContext) {
/* 37 */       return new LootEntityPropertyOnFire(ChatDeserializer.c(param1JsonElement, "on_fire"));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootEntityPropertyOnFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */