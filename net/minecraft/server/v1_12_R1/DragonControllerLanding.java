/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerLanding
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private Vec3D b;
/*    */   
/*    */   public DragonControllerLanding(EntityEnderDragon paramEntityEnderDragon) {
/* 15 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b() {
/* 20 */     Vec3D vec3D = this.a.a(1.0F).a();
/* 21 */     vec3D.b(-0.7853982F);
/*    */     
/* 23 */     double d1 = this.a.bw.locX;
/* 24 */     double d2 = this.a.bw.locY + (this.a.bw.length / 2.0F);
/* 25 */     double d3 = this.a.bw.locZ;
/* 26 */     for (byte b = 0; b < 8; b++) {
/* 27 */       double d4 = d1 + this.a.getRandom().nextGaussian() / 2.0D;
/* 28 */       double d5 = d2 + this.a.getRandom().nextGaussian() / 2.0D;
/* 29 */       double d6 = d3 + this.a.getRandom().nextGaussian() / 2.0D;
/* 30 */       this.a.world.addParticle(EnumParticle.DRAGON_BREATH, d4, d5, d6, -vec3D.x * 0.07999999821186066D + this.a.motX, -vec3D.y * 0.30000001192092896D + this.a.motY, -vec3D.z * 0.07999999821186066D + this.a.motZ, new int[0]);
/* 31 */       vec3D.b(0.19634955F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 37 */     if (this.b == null) {
/* 38 */       this.b = new Vec3D(this.a.world.q(WorldGenEndTrophy.a));
/*    */     }
/*    */     
/* 41 */     if (this.b.c(this.a.locX, this.a.locY, this.a.locZ) < 1.0D) {
/* 42 */       ((DragonControllerLandedFlame)this.a.getDragonControllerManager().<DragonControllerLandedFlame>b(DragonControllerPhase.f)).j();
/* 43 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.g);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 49 */     return 1.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float h() {
/* 54 */     float f1 = MathHelper.sqrt(this.a.motX * this.a.motX + this.a.motZ * this.a.motZ) + 1.0F;
/* 55 */     float f2 = Math.min(f1, 40.0F);
/*    */     
/* 57 */     return f2 / f1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 62 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 68 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLanding> getControllerPhase() {
/* 73 */     return DragonControllerPhase.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerLanding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */