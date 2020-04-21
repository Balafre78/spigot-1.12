/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ public interface CriterionTrigger<T extends CriterionInstance> {
/*    */   MinecraftKey a();
/*    */   
/*    */   void a(AdvancementDataPlayer paramAdvancementDataPlayer, a<T> parama);
/*    */   
/*    */   void b(AdvancementDataPlayer paramAdvancementDataPlayer, a<T> parama);
/*    */   
/*    */   void a(AdvancementDataPlayer paramAdvancementDataPlayer);
/*    */   
/*    */   T a(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext);
/*    */   
/*    */   public static class a<T extends CriterionInstance> {
/*    */     private final T a;
/*    */     
/*    */     public a(T param1T, Advancement param1Advancement, String param1String) {
/* 21 */       this.a = param1T;
/* 22 */       this.b = param1Advancement;
/* 23 */       this.c = param1String;
/*    */     }
/*    */     private final Advancement b; private final String c;
/*    */     public T a() {
/* 27 */       return this.a;
/*    */     }
/*    */     
/*    */     public void a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/* 31 */       param1AdvancementDataPlayer.grantCriteria(this.b, this.c);
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean equals(Object param1Object) {
/* 36 */       if (this == param1Object) {
/* 37 */         return true;
/*    */       }
/* 39 */       if (param1Object == null || getClass() != param1Object.getClass()) {
/* 40 */         return false;
/*    */       }
/*    */       
/* 43 */       a a1 = (a)param1Object;
/*    */       
/* 45 */       if (!this.a.equals(a1.a)) {
/* 46 */         return false;
/*    */       }
/* 48 */       if (!this.b.equals(a1.b)) {
/* 49 */         return false;
/*    */       }
/* 51 */       return this.c.equals(a1.c);
/*    */     }
/*    */ 
/*    */     
/*    */     public int hashCode() {
/* 56 */       int i = this.a.hashCode();
/* 57 */       i = 31 * i + this.b.hashCode();
/* 58 */       i = 31 * i + this.c.hashCode();
/* 59 */       return i;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */