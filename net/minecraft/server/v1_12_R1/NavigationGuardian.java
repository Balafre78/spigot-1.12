/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NavigationGuardian
/*    */   extends NavigationAbstract
/*    */ {
/*    */   public NavigationGuardian(EntityInsentient paramEntityInsentient, World paramWorld) {
/* 13 */     super(paramEntityInsentient, paramWorld);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Pathfinder a() {
/* 18 */     return new Pathfinder(new PathfinderWater());
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b() {
/* 23 */     return q();
/*    */   }
/*    */ 
/*    */   
/*    */   protected Vec3D c() {
/* 28 */     return new Vec3D(this.a.locX, this.a.locY + this.a.length * 0.5D, this.a.locZ);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void n() {
/* 33 */     Vec3D vec3D = c();
/*    */ 
/*    */     
/* 36 */     float f = this.a.width * this.a.width;
/* 37 */     byte b = 6;
/* 38 */     if (vec3D.distanceSquared(this.c.a(this.a, this.c.e())) < f) {
/* 39 */       this.c.a();
/*    */     }
/*    */     
/* 42 */     for (int i = Math.min(this.c.e() + 6, this.c.d() - 1); i > this.c.e(); i--) {
/* 43 */       Vec3D vec3D1 = this.c.a(this.a, i);
/* 44 */       if (vec3D1.distanceSquared(vec3D) <= 36.0D)
/*    */       {
/*    */         
/* 47 */         if (a(vec3D, vec3D1, 0, 0, 0)) {
/* 48 */           this.c.c(i);
/*    */           break;
/*    */         } 
/*    */       }
/*    */     } 
/* 53 */     a(vec3D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Vec3D paramVec3D1, Vec3D paramVec3D2, int paramInt1, int paramInt2, int paramInt3) {
/* 58 */     MovingObjectPosition movingObjectPosition = this.b.rayTrace(paramVec3D1, new Vec3D(paramVec3D2.x, paramVec3D2.y + this.a.length * 0.5D, paramVec3D2.z), false, true, false);
/* 59 */     return (movingObjectPosition == null || movingObjectPosition.type == MovingObjectPosition.EnumMovingObjectType.MISS);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(BlockPosition paramBlockPosition) {
/* 64 */     return !this.b.getType(paramBlockPosition).b();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NavigationGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */