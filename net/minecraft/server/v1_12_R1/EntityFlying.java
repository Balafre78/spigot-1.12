/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityFlying
/*    */   extends EntityInsentient
/*    */ {
/*    */   public EntityFlying(World paramWorld) {
/* 10 */     super(paramWorld);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void e(float paramFloat1, float paramFloat2) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(double paramDouble, boolean paramBoolean, IBlockData paramIBlockData, BlockPosition paramBlockPosition) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 25 */     if (isInWater()) {
/* 26 */       b(paramFloat1, paramFloat2, paramFloat3, 0.02F);
/* 27 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*    */       
/* 29 */       this.motX *= 0.800000011920929D;
/* 30 */       this.motY *= 0.800000011920929D;
/* 31 */       this.motZ *= 0.800000011920929D;
/* 32 */     } else if (au()) {
/* 33 */       b(paramFloat1, paramFloat2, paramFloat3, 0.02F);
/* 34 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 35 */       this.motX *= 0.5D;
/* 36 */       this.motY *= 0.5D;
/* 37 */       this.motZ *= 0.5D;
/*    */     } else {
/* 39 */       float f1 = 0.91F;
/* 40 */       if (this.onGround) {
/* 41 */         f1 = (this.world.getType(new BlockPosition(MathHelper.floor(this.locX), MathHelper.floor((getBoundingBox()).b) - 1, MathHelper.floor(this.locZ))).getBlock()).frictionFactor * 0.91F;
/*    */       }
/*    */       
/* 44 */       float f2 = 0.16277136F / f1 * f1 * f1;
/* 45 */       b(paramFloat1, paramFloat2, paramFloat3, this.onGround ? (0.1F * f2) : 0.02F);
/*    */       
/* 47 */       f1 = 0.91F;
/* 48 */       if (this.onGround) {
/* 49 */         f1 = (this.world.getType(new BlockPosition(MathHelper.floor(this.locX), MathHelper.floor((getBoundingBox()).b) - 1, MathHelper.floor(this.locZ))).getBlock()).frictionFactor * 0.91F;
/*    */       }
/*    */       
/* 52 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*    */       
/* 54 */       this.motX *= f1;
/* 55 */       this.motY *= f1;
/* 56 */       this.motZ *= f1;
/*    */     } 
/* 58 */     this.aF = this.aG;
/* 59 */     double d1 = this.locX - this.lastX;
/* 60 */     double d2 = this.locZ - this.lastZ;
/* 61 */     float f = MathHelper.sqrt(d1 * d1 + d2 * d2) * 4.0F;
/* 62 */     if (f > 1.0F) {
/* 63 */       f = 1.0F;
/*    */     }
/* 65 */     this.aG += (f - this.aG) * 0.4F;
/* 66 */     this.aH += this.aG;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean m_() {
/* 71 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */