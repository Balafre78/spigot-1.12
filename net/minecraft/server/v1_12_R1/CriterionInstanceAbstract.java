/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class CriterionInstanceAbstract
/*    */   implements CriterionInstance
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public CriterionInstanceAbstract(MinecraftKey paramMinecraftKey) {
/* 10 */     this.a = paramMinecraftKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 15 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "AbstractCriterionInstance{criterion=" + this.a + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionInstanceAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */