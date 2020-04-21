/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSlime
/*    */   extends BlockHalfTransparent
/*    */ {
/*    */   public BlockSlime() {
/* 14 */     super(Material.CLAY, false, MaterialMapColor.d);
/* 15 */     a(CreativeModeTab.c);
/* 16 */     this.frictionFactor = 0.8F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fallOn(World paramWorld, BlockPosition paramBlockPosition, Entity paramEntity, float paramFloat) {
/* 26 */     if (paramEntity.isSneaking()) {
/* 27 */       super.fallOn(paramWorld, paramBlockPosition, paramEntity, paramFloat);
/*    */     } else {
/* 29 */       paramEntity.e(paramFloat, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Entity paramEntity) {
/* 35 */     if (paramEntity.isSneaking()) {
/* 36 */       super.a(paramWorld, paramEntity);
/*    */     }
/* 38 */     else if (paramEntity.motY < 0.0D) {
/* 39 */       paramEntity.motY = -paramEntity.motY;
/*    */ 
/*    */       
/* 42 */       if (!(paramEntity instanceof EntityLiving)) {
/* 43 */         paramEntity.motY *= 0.8D;
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void stepOn(World paramWorld, BlockPosition paramBlockPosition, Entity paramEntity) {
/* 51 */     if (Math.abs(paramEntity.motY) < 0.1D && !paramEntity.isSneaking()) {
/* 52 */       double d = 0.4D + Math.abs(paramEntity.motY) * 0.2D;
/* 53 */       paramEntity.motX *= d;
/* 54 */       paramEntity.motZ *= d;
/*    */     } 
/* 56 */     super.stepOn(paramWorld, paramBlockPosition, paramEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */