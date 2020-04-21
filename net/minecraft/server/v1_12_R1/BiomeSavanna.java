/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeSavanna
/*    */   extends BiomeBase
/*    */ {
/* 15 */   private static final WorldGenAcaciaTree x = new WorldGenAcaciaTree(false);
/*    */   
/*    */   protected BiomeSavanna(BiomeBase.a parama) {
/* 18 */     super(parama);
/* 19 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityHorse.class, 1, 2, 6));
/* 20 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityHorseDonkey.class, 1, 1, 1));
/*    */     
/* 22 */     if (j() > 1.1F)
/*    */     {
/* 24 */       this.u.add(new BiomeBase.BiomeMeta((Class)EntityLlama.class, 8, 4, 4));
/*    */     }
/*    */     
/* 27 */     this.s.z = 1;
/* 28 */     this.s.B = 4;
/* 29 */     this.s.C = 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 34 */     if (paramRandom.nextInt(5) > 0) {
/* 35 */       return x;
/*    */     }
/* 37 */     return m;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 42 */     l.a(BlockTallPlant.EnumTallFlowerVariants.GRASS);
/* 43 */     for (byte b = 0; b < 7; b++) {
/* 44 */       int i = paramRandom.nextInt(16) + 8;
/* 45 */       int j = paramRandom.nextInt(16) + 8;
/* 46 */       int k = paramRandom.nextInt(paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)).getY() + 32);
/* 47 */       l.generate(paramWorld, paramRandom, paramBlockPosition.a(i, k, j));
/*    */     } 
/* 49 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<? extends BiomeBase> g() {
/* 54 */     return (Class)BiomeSavanna.class;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeSavanna.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */