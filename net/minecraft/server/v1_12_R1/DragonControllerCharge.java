/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class DragonControllerCharge
/*    */   extends AbstractDragonController
/*    */ {
/* 11 */   private static final Logger b = LogManager.getLogger();
/*    */   
/*    */   private Vec3D c;
/*    */   
/*    */   private int d;
/*    */   
/*    */   public DragonControllerCharge(EntityEnderDragon paramEntityEnderDragon) {
/* 18 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 23 */     if (this.c == null) {
/* 24 */       b.warn("Aborting charge player as no target was set.");
/* 25 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.a);
/*    */       
/*    */       return;
/*    */     } 
/* 29 */     if (this.d > 0 && 
/* 30 */       this.d++ >= 10) {
/* 31 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.a);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 36 */     double d = this.c.c(this.a.locX, this.a.locY, this.a.locZ);
/* 37 */     if (d < 100.0D || d > 22500.0D || this.a.positionChanged || this.a.B) {
/* 38 */       this.d++;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 44 */     this.c = null;
/* 45 */     this.d = 0;
/*    */   }
/*    */   
/*    */   public void a(Vec3D paramVec3D) {
/* 49 */     this.c = paramVec3D;
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
/* 60 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerCharge> getControllerPhase() {
/* 65 */     return DragonControllerPhase.i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerCharge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */