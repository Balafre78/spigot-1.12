/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerFly
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private boolean b;
/*    */   private PathEntity c;
/*    */   private Vec3D d;
/*    */   
/*    */   public DragonControllerFly(EntityEnderDragon paramEntityEnderDragon) {
/* 17 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 22 */     if (this.b || this.c == null) {
/* 23 */       this.b = false;
/* 24 */       j();
/*    */     } else {
/* 26 */       BlockPosition blockPosition = this.a.world.q(WorldGenEndTrophy.a);
/* 27 */       double d = this.a.d(blockPosition);
/* 28 */       if (d > 100.0D) {
/* 29 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.a);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 36 */     this.b = true;
/* 37 */     this.c = null;
/* 38 */     this.d = null;
/*    */   }
/*    */   
/*    */   private void j() {
/* 42 */     int i = this.a.p();
/* 43 */     Vec3D vec3D = this.a.a(1.0F);
/* 44 */     int j = this.a.k(-vec3D.x * 40.0D, 105.0D, -vec3D.z * 40.0D);
/*    */     
/* 46 */     if (this.a.df() == null || this.a.df().c() <= 0) {
/*    */       
/* 48 */       j -= 12;
/* 49 */       j &= 0x7;
/* 50 */       j += 12;
/*    */     } else {
/*    */       
/* 53 */       j %= 12;
/* 54 */       if (j < 0) {
/* 55 */         j += 12;
/*    */       }
/*    */     } 
/*    */     
/* 59 */     this.c = this.a.a(i, j, (PathPoint)null);
/*    */     
/* 61 */     if (this.c != null) {
/* 62 */       this.c.a();
/* 63 */       k();
/*    */     } 
/*    */   }
/*    */   private void k() {
/*    */     double d;
/* 68 */     Vec3D vec3D = this.c.f();
/*    */     
/* 70 */     this.c.a();
/*    */ 
/*    */     
/*    */     do {
/* 74 */       d = vec3D.y + (this.a.getRandom().nextFloat() * 20.0F);
/* 75 */     } while (d < vec3D.y);
/*    */     
/* 77 */     this.d = new Vec3D(vec3D.x, d, vec3D.z);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 83 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerFly> getControllerPhase() {
/* 88 */     return DragonControllerPhase.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */