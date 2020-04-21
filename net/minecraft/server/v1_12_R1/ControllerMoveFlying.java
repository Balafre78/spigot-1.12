/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControllerMoveFlying
/*    */   extends ControllerMove
/*    */ {
/*    */   public ControllerMoveFlying(EntityInsentient paramEntityInsentient) {
/* 11 */     super(paramEntityInsentient);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a() {
/* 16 */     if (this.h == ControllerMove.Operation.MOVE_TO) {
/* 17 */       float f2; this.h = ControllerMove.Operation.WAIT;
/*    */       
/* 19 */       this.a.setNoGravity(true);
/*    */       
/* 21 */       double d1 = this.b - this.a.locX;
/* 22 */       double d2 = this.c - this.a.locY;
/* 23 */       double d3 = this.d - this.a.locZ;
/* 24 */       double d4 = d1 * d1 + d2 * d2 + d3 * d3;
/* 25 */       if (d4 < 2.500000277905201E-7D) {
/* 26 */         this.a.o(0.0F);
/* 27 */         this.a.n(0.0F);
/*    */         return;
/*    */       } 
/* 30 */       float f1 = (float)(MathHelper.c(d3, d1) * 57.2957763671875D) - 90.0F;
/* 31 */       this.a.yaw = a(this.a.yaw, f1, 10.0F);
/*    */       
/* 33 */       if (this.a.onGround) {
/* 34 */         f2 = (float)(this.e * this.a.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue());
/*    */       } else {
/* 36 */         f2 = (float)(this.e * this.a.getAttributeInstance(GenericAttributes.e).getValue());
/*    */       } 
/* 38 */       this.a.k(f2);
/*    */       
/* 40 */       double d5 = MathHelper.sqrt(d1 * d1 + d3 * d3);
/* 41 */       float f3 = (float)-(MathHelper.c(d2, d5) * 57.2957763671875D);
/* 42 */       this.a.pitch = a(this.a.pitch, f3, 10.0F);
/* 43 */       this.a.o((d2 > 0.0D) ? f2 : -f2);
/*    */     } else {
/* 45 */       this.a.setNoGravity(false);
/*    */       
/* 47 */       this.a.o(0.0F);
/* 48 */       this.a.n(0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ControllerMoveFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */