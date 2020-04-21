/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class DragonControllerHover
/*    */   extends AbstractDragonController
/*    */ {
/*    */   private Vec3D b;
/*    */   
/*    */   public DragonControllerHover(EntityEnderDragon paramEntityEnderDragon) {
/* 12 */     super(paramEntityEnderDragon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 17 */     if (this.b == null) {
/* 18 */       this.b = new Vec3D(this.a.locX, this.a.locY, this.a.locZ);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 29 */     this.b = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 34 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 40 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerHover> getControllerPhase() {
/* 45 */     return DragonControllerPhase.k;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerHover.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */