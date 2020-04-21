/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
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
/*    */ public class BiomeIcePlains
/*    */   extends BiomeBase
/*    */ {
/*    */   private final boolean x;
/* 20 */   private final WorldGenPackedIce2 y = new WorldGenPackedIce2();
/* 21 */   private final WorldGenPackedIce1 z = new WorldGenPackedIce1(4);
/*    */   
/*    */   public BiomeIcePlains(boolean paramBoolean, BiomeBase.a parama) {
/* 24 */     super(parama);
/* 25 */     this.x = paramBoolean;
/*    */     
/* 27 */     if (paramBoolean) {
/* 28 */       this.q = Blocks.SNOW.getBlockData();
/*    */     }
/* 30 */     this.u.clear();
/* 31 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityRabbit.class, 10, 2, 3));
/* 32 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityPolarBear.class, 1, 1, 2));
/*    */ 
/*    */     
/* 35 */     Iterator<BiomeBase.BiomeMeta> iterator = this.t.iterator();
/* 36 */     while (iterator.hasNext()) {
/* 37 */       BiomeBase.BiomeMeta biomeMeta = iterator.next();
/* 38 */       if (biomeMeta.b == EntitySkeleton.class) {
/* 39 */         iterator.remove();
/*    */       }
/*    */     } 
/*    */     
/* 43 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntitySkeleton.class, 20, 4, 4));
/* 44 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntitySkeletonStray.class, 80, 4, 4));
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 49 */     return 0.07F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 54 */     if (this.x) {
/* 55 */       byte b; for (b = 0; b < 3; b++) {
/* 56 */         int i = paramRandom.nextInt(16) + 8;
/* 57 */         int j = paramRandom.nextInt(16) + 8;
/* 58 */         this.y.generate(paramWorld, paramRandom, paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)));
/*    */       } 
/* 60 */       for (b = 0; b < 2; b++) {
/* 61 */         int i = paramRandom.nextInt(16) + 8;
/* 62 */         int j = paramRandom.nextInt(16) + 8;
/* 63 */         this.z.generate(paramWorld, paramRandom, paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)));
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 72 */     return new WorldGenTaiga2(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeIcePlains.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */