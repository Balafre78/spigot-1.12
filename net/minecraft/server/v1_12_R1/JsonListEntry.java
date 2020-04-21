/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ public class JsonListEntry<T> {
/*    */   private final T a;
/*    */   
/*    */   public JsonListEntry(T paramT) {
/*  9 */     this.a = paramT;
/*    */   }
/*    */   
/*    */   protected JsonListEntry(T paramT, JsonObject paramJsonObject) {
/* 13 */     this.a = paramT;
/*    */   }
/*    */   
/*    */   public T getKey() {
/* 17 */     return this.a;
/*    */   }
/*    */   
/*    */   boolean hasExpired() {
/* 21 */     return false;
/*    */   }
/*    */   
/*    */   protected void a(JsonObject paramJsonObject) {}
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\JsonListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */