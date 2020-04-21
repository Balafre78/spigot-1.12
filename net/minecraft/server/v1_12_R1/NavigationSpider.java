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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NavigationSpider
/*    */   extends Navigation
/*    */ {
/*    */   private BlockPosition i;
/*    */   
/*    */   public NavigationSpider(EntityInsentient paramEntityInsentient, World paramWorld) {
/* 22 */     super(paramEntityInsentient, paramWorld);
/*    */   }
/*    */ 
/*    */   
/*    */   public PathEntity b(BlockPosition paramBlockPosition) {
/* 27 */     this.i = paramBlockPosition;
/* 28 */     return super.b(paramBlockPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public PathEntity a(Entity paramEntity) {
/* 33 */     this.i = new BlockPosition(paramEntity);
/* 34 */     return super.a(paramEntity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Entity paramEntity, double paramDouble) {
/* 39 */     PathEntity pathEntity = a(paramEntity);
/* 40 */     if (pathEntity != null) {
/* 41 */       return a(pathEntity, paramDouble);
/*    */     }
/* 43 */     this.i = new BlockPosition(paramEntity);
/* 44 */     this.d = paramDouble;
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void d() {
/* 51 */     if (o()) {
/* 52 */       if (this.i != null) {
/* 53 */         double d = (this.a.width * this.a.width);
/*    */         
/* 55 */         if (this.a.d(this.i) < d || (this.a.locY > this.i.getY() && this.a.d(new BlockPosition(this.i.getX(), MathHelper.floor(this.a.locY), this.i.getZ())) < d)) {
/* 56 */           this.i = null;
/*    */         } else {
/* 58 */           this.a.getControllerMove().a(this.i.getX(), this.i.getY(), this.i.getZ(), this.d);
/*    */         } 
/*    */       } 
/*    */       return;
/*    */     } 
/* 63 */     super.d();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NavigationSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */