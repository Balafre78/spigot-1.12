/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIBodyControl
/*    */ {
/*    */   private final EntityLiving a;
/*    */   private int b;
/*    */   private float c;
/*    */   
/*    */   public EntityAIBodyControl(EntityLiving paramEntityLiving) {
/* 14 */     this.a = paramEntityLiving;
/*    */   }
/*    */   
/*    */   public void a() {
/* 18 */     double d1 = this.a.locX - this.a.lastX;
/* 19 */     double d2 = this.a.locZ - this.a.lastZ;
/*    */     
/* 21 */     if (d1 * d1 + d2 * d2 > 2.500000277905201E-7D) {
/*    */       
/* 23 */       this.a.aN = this.a.yaw;
/* 24 */       this.a.aP = a(this.a.aN, this.a.aP, 75.0F);
/* 25 */       this.c = this.a.aP;
/* 26 */       this.b = 0;
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     if (this.a.bF().isEmpty() || !(this.a.bF().get(0) instanceof EntityInsentient)) {
/*    */       
/* 32 */       float f = 75.0F;
/* 33 */       if (Math.abs(this.a.aP - this.c) > 15.0F) {
/* 34 */         this.b = 0;
/* 35 */         this.c = this.a.aP;
/*    */       } else {
/* 37 */         this.b++;
/* 38 */         byte b = 10;
/* 39 */         if (this.b > 10) {
/* 40 */           f = Math.max(1.0F - (this.b - 10) / 10.0F, 0.0F) * 75.0F;
/*    */         }
/*    */       } 
/* 43 */       this.a.aN = a(this.a.aP, this.a.aN, f);
/*    */     } 
/*    */   }
/*    */   
/*    */   private float a(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 48 */     float f = MathHelper.g(paramFloat1 - paramFloat2);
/* 49 */     if (f < -paramFloat3) {
/* 50 */       f = -paramFloat3;
/*    */     }
/* 52 */     if (f >= paramFloat3) {
/* 53 */       f = paramFloat3;
/*    */     }
/* 55 */     return paramFloat1 - f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityAIBodyControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */