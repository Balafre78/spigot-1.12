/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityComplexPart
/*    */   extends Entity
/*    */ {
/*    */   public final IComplex owner;
/*    */   public final String b;
/*    */   
/*    */   public EntityComplexPart(IComplex paramIComplex, String paramString, float paramFloat1, float paramFloat2) {
/* 12 */     super(paramIComplex.a());
/* 13 */     setSize(paramFloat1, paramFloat2);
/* 14 */     this.owner = paramIComplex;
/* 15 */     this.b = paramString;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void i() {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(NBTTagCompound paramNBTTagCompound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void b(NBTTagCompound paramNBTTagCompound) {}
/*    */ 
/*    */   
/*    */   public boolean isInteractable() {
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean damageEntity(DamageSource paramDamageSource, float paramFloat) {
/* 37 */     if (isInvulnerable(paramDamageSource)) {
/* 38 */       return false;
/*    */     }
/* 40 */     return this.owner.a(this, paramDamageSource, paramFloat);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean s(Entity paramEntity) {
/* 45 */     return (this == paramEntity || this.owner == paramEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityComplexPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */