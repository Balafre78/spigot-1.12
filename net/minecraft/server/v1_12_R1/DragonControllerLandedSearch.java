/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerLandedSearch
/*    */   extends AbstractDragonControllerLanded
/*    */ {
/*    */   private int b;
/*    */   
/*    */   public DragonControllerLandedSearch(EntityEnderDragon paramEntityEnderDragon) {
/* 17 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 22 */     this.b++;
/* 23 */     EntityHuman entityHuman = this.a.world.a(this.a, 20.0D, 10.0D);
/*    */     
/* 25 */     if (entityHuman != null) {
/* 26 */       if (this.b > 25) {
/* 27 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.h);
/*    */       } else {
/* 29 */         Vec3D vec3D1 = (new Vec3D(entityHuman.locX - this.a.locX, 0.0D, entityHuman.locZ - this.a.locZ)).a();
/* 30 */         Vec3D vec3D2 = (new Vec3D(MathHelper.sin(this.a.yaw * 0.017453292F), 0.0D, -MathHelper.cos(this.a.yaw * 0.017453292F))).a();
/* 31 */         float f1 = (float)vec3D2.b(vec3D1);
/* 32 */         float f2 = (float)(Math.acos(f1) * 57.2957763671875D) + 0.5F;
/*    */         
/* 34 */         if (f2 < 0.0F || f2 > 10.0F) {
/* 35 */           double d1 = entityHuman.locX - this.a.bw.locX;
/* 36 */           double d2 = entityHuman.locZ - this.a.bw.locZ;
/* 37 */           double d3 = MathHelper.a(MathHelper.g(180.0D - MathHelper.c(d1, d2) * 57.2957763671875D - this.a.yaw), -100.0D, 100.0D);
/*    */           
/* 39 */           this.a.bh *= 0.8F;
/*    */           
/* 41 */           float f3 = MathHelper.sqrt(d1 * d1 + d2 * d2) + 1.0F;
/* 42 */           float f4 = f3;
/* 43 */           if (f3 > 40.0F) {
/* 44 */             f3 = 40.0F;
/*    */           }
/* 46 */           this.a.bh = (float)(this.a.bh + d3 * (0.7F / f3 / f4));
/* 47 */           this.a.yaw += this.a.bh;
/*    */         } 
/*    */       } 
/* 50 */     } else if (this.b >= 100) {
/* 51 */       entityHuman = this.a.world.a(this.a, 150.0D, 150.0D);
/* 52 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.e);
/* 53 */       if (entityHuman != null) {
/* 54 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.i);
/* 55 */         ((DragonControllerCharge)this.a.getDragonControllerManager().<DragonControllerCharge>b(DragonControllerPhase.i)).a(new Vec3D(entityHuman.locX, entityHuman.locY, entityHuman.locZ));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 62 */     this.b = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLandedSearch> getControllerPhase() {
/* 67 */     return DragonControllerPhase.g;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerLandedSearch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */