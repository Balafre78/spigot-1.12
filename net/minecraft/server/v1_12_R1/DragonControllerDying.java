/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerDying
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private Vec3D b;
/*    */   private int c;
/*    */   
/*    */   public DragonControllerDying(EntityEnderDragon paramEntityEnderDragon) {
/* 16 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b() {
/* 21 */     if (this.c++ % 10 == 0) {
/* 22 */       float f1 = (this.a.getRandom().nextFloat() - 0.5F) * 8.0F;
/* 23 */       float f2 = (this.a.getRandom().nextFloat() - 0.5F) * 4.0F;
/* 24 */       float f3 = (this.a.getRandom().nextFloat() - 0.5F) * 8.0F;
/* 25 */       this.a.world.addParticle(EnumParticle.EXPLOSION_HUGE, this.a.locX + f1, this.a.locY + 2.0D + f2, this.a.locZ + f3, 0.0D, 0.0D, 0.0D, new int[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 31 */     this.c++;
/*    */     
/* 33 */     if (this.b == null) {
/* 34 */       BlockPosition blockPosition = this.a.world.getHighestBlockYAt(WorldGenEndTrophy.a);
/* 35 */       this.b = new Vec3D(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
/*    */     } 
/*    */     
/* 38 */     double d = this.b.c(this.a.locX, this.a.locY, this.a.locZ);
/* 39 */     if (d < 100.0D || d > 22500.0D || this.a.positionChanged || this.a.B) {
/* 40 */       this.a.setHealth(0.0F);
/*    */     } else {
/* 42 */       this.a.setHealth(1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 48 */     this.b = null;
/* 49 */     this.c = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 54 */     return 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 60 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerDying> getControllerPhase() {
/* 65 */     return DragonControllerPhase.j;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerDying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */