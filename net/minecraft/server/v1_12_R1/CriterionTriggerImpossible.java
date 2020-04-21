/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerImpossible
/*    */   implements CriterionTrigger<CriterionTriggerImpossible.a>
/*    */ {
/* 10 */   private static final MinecraftKey a = new MinecraftKey("impossible");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 14 */     return a;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<a> parama) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<a> parama) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {}
/*    */ 
/*    */   
/*    */   public a b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/* 31 */     return new a();
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     public a() {
/* 36 */       super(CriterionTriggerImpossible.b());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerImpossible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */