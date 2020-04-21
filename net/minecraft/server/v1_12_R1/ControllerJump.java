/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class ControllerJump
/*    */ {
/*    */   private final EntityInsentient b;
/*    */   protected boolean a;
/*    */   
/*    */   public ControllerJump(EntityInsentient paramEntityInsentient) {
/* 10 */     this.b = paramEntityInsentient;
/*    */   }
/*    */   
/*    */   public void a() {
/* 14 */     this.a = true;
/*    */   }
/*    */   
/*    */   public void b() {
/* 18 */     this.b.l(this.a);
/* 19 */     this.a = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ControllerJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */