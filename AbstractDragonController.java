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
/*    */ 
/*    */ public abstract class AbstractDragonController
/*    */   implements IDragonController
/*    */ {
/*    */   protected final EntityEnderDragon a;
/*    */   
/*    */   public AbstractDragonController(EntityEnderDragon paramEntityEnderDragon) {
/* 18 */     this.a = paramEntityEnderDragon;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(EntityEnderCrystal paramEntityEnderCrystal, BlockPosition paramBlockPosition, DamageSource paramDamageSource, @Nullable EntityHuman paramEntityHuman) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void d() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void e() {}
/*    */ 
/*    */   
/*    */   public float f() {
/* 48 */     return 0.6F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 54 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(EntityComplexPart paramEntityComplexPart, DamageSource paramDamageSource, float paramFloat) {
/* 59 */     return paramFloat;
/*    */   }
/*    */ 
/*    */   
/*    */   public float h() {
/* 64 */     float f1 = MathHelper.sqrt(this.a.motX * this.a.motX + this.a.motZ * this.a.motZ) + 1.0F;
/* 65 */     float f2 = Math.min(f1, 40.0F);
/*    */     
/* 67 */     return 0.7F / f2 / f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AbstractDragonController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */