/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerLandingFly
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private PathEntity b;
/*    */   private Vec3D c;
/*    */   
/*    */   public DragonControllerLandingFly(EntityEnderDragon paramEntityEnderDragon) {
/* 18 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLandingFly> getControllerPhase() {
/* 23 */     return DragonControllerPhase.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 28 */     this.b = null;
/* 29 */     this.c = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 34 */     double d = (this.c == null) ? 0.0D : this.c.c(this.a.locX, this.a.locY, this.a.locZ);
/* 35 */     if (d < 100.0D || d > 22500.0D || this.a.positionChanged || this.a.B) {
/* 36 */       j();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 43 */     return this.c;
/*    */   }
/*    */   
/*    */   private void j() {
/* 47 */     if (this.b == null || this.b.b()) {
/* 48 */       int j, i = this.a.p();
/* 49 */       BlockPosition blockPosition = this.a.world.q(WorldGenEndTrophy.a);
/* 50 */       EntityHuman entityHuman = this.a.world.a(blockPosition, 128.0D, 128.0D);
/*    */ 
/*    */       
/* 53 */       if (entityHuman != null) {
/* 54 */         Vec3D vec3D = (new Vec3D(entityHuman.locX, 0.0D, entityHuman.locZ)).a();
/* 55 */         j = this.a.k(-vec3D.x * 40.0D, 105.0D, -vec3D.z * 40.0D);
/*    */       } else {
/* 57 */         j = this.a.k(40.0D, blockPosition.getY(), 0.0D);
/*    */       } 
/*    */       
/* 60 */       PathPoint pathPoint = new PathPoint(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
/*    */       
/* 62 */       this.b = this.a.a(i, j, pathPoint);
/*    */       
/* 64 */       if (this.b != null) {
/* 65 */         this.b.a();
/*    */       }
/*    */     } 
/*    */     
/* 69 */     k();
/*    */     
/* 71 */     if (this.b != null && this.b.b()) {
/* 72 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.d);
/*    */     }
/*    */   }
/*    */   
/*    */   private void k() {
/* 77 */     if (this.b != null && !this.b.b()) {
/* 78 */       double d3; Vec3D vec3D = this.b.f();
/*    */       
/* 80 */       this.b.a();
/* 81 */       double d1 = vec3D.x;
/* 82 */       double d2 = vec3D.z;
/*    */ 
/*    */       
/*    */       do {
/* 86 */         d3 = vec3D.y + (this.a.getRandom().nextFloat() * 20.0F);
/* 87 */       } while (d3 < vec3D.y);
/*    */       
/* 89 */       this.c = new Vec3D(d1, d3, d2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerLandingFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */