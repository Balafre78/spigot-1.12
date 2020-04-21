/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
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
/*    */ public class NavigationListener
/*    */   implements IWorldAccess
/*    */ {
/* 21 */   private final List<NavigationAbstract> a = Lists.newArrayList();
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData1, IBlockData paramIBlockData2, int paramInt) {
/* 25 */     if (!a(paramWorld, paramBlockPosition, paramIBlockData1, paramIBlockData2))
/*    */       return; 
/*    */     byte b;
/*    */     int i;
/* 29 */     for (b = 0, i = this.a.size(); b < i; b++) {
/* 30 */       NavigationAbstract navigationAbstract = this.a.get(b);
/* 31 */       if (navigationAbstract != null)
/*    */       {
/*    */ 
/*    */         
/* 35 */         if (!navigationAbstract.j()) {
/*    */ 
/*    */ 
/*    */           
/* 39 */           PathEntity pathEntity = navigationAbstract.l();
/* 40 */           if (pathEntity != null && !pathEntity.b() && pathEntity.d() != 0) {
/*    */ 
/*    */ 
/*    */             
/* 44 */             PathPoint pathPoint = navigationAbstract.c.c();
/* 45 */             double d = paramBlockPosition.distanceSquared((pathPoint.a + navigationAbstract.a.locX) / 2.0D, (pathPoint.b + navigationAbstract.a.locY) / 2.0D, (pathPoint.c + navigationAbstract.a.locZ) / 2.0D);
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 50 */             int j = (pathEntity.d() - pathEntity.e()) * (pathEntity.d() - pathEntity.e());
/*    */             
/* 52 */             if (d < j)
/* 53 */               navigationAbstract.k(); 
/*    */           } 
/*    */         }  } 
/*    */     } 
/*    */   }
/*    */   protected boolean a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData1, IBlockData paramIBlockData2) {
/* 59 */     AxisAlignedBB axisAlignedBB1 = paramIBlockData1.d(paramWorld, paramBlockPosition);
/* 60 */     AxisAlignedBB axisAlignedBB2 = paramIBlockData2.d(paramWorld, paramBlockPosition);
/* 61 */     return (axisAlignedBB1 != axisAlignedBB2 && (axisAlignedBB1 == null || !axisAlignedBB1.equals(axisAlignedBB2)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BlockPosition paramBlockPosition) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(@Nullable EntityHuman paramEntityHuman, SoundEffect paramSoundEffect, SoundCategory paramSoundCategory, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int... paramVarArgs) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(int paramInt, boolean paramBoolean1, boolean paramBoolean2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int... paramVarArgs) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(Entity paramEntity) {
/* 90 */     if (paramEntity instanceof EntityInsentient) {
/* 91 */       this.a.add(((EntityInsentient)paramEntity).getNavigation());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(Entity paramEntity) {
/* 97 */     if (paramEntity instanceof EntityInsentient)
/* 98 */       this.a.remove(((EntityInsentient)paramEntity).getNavigation()); 
/*    */   }
/*    */   
/*    */   public void a(SoundEffect paramSoundEffect, BlockPosition paramBlockPosition) {}
/*    */   
/*    */   public void a(int paramInt1, BlockPosition paramBlockPosition, int paramInt2) {}
/*    */   
/*    */   public void a(EntityHuman paramEntityHuman, int paramInt1, BlockPosition paramBlockPosition, int paramInt2) {}
/*    */   
/*    */   public void b(int paramInt1, BlockPosition paramBlockPosition, int paramInt2) {}
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NavigationListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */