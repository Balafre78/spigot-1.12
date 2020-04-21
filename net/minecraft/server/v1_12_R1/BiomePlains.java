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
/*    */ public class BiomePlains
/*    */   extends BiomeBase
/*    */ {
/*    */   protected boolean x;
/*    */   
/*    */   protected BiomePlains(boolean paramBoolean, BiomeBase.a parama) {
/* 17 */     super(parama);
/* 18 */     this.x = paramBoolean;
/*    */     
/* 20 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityHorse.class, 5, 2, 6));
/* 21 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityHorseDonkey.class, 1, 1, 3));
/*    */     
/* 23 */     this.s.z = 0;
/* 24 */     this.s.A = 0.05F;
/* 25 */     this.s.B = 4;
/* 26 */     this.s.C = 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFlowers.EnumFlowerVarient a(Random paramRandom, BlockPosition paramBlockPosition) {
/* 31 */     double d = k.a(paramBlockPosition.getX() / 200.0D, paramBlockPosition.getZ() / 200.0D);
/* 32 */     if (d < -0.8D) {
/* 33 */       int i = paramRandom.nextInt(4);
/* 34 */       switch (i) {
/*    */         case 0:
/* 36 */           return BlockFlowers.EnumFlowerVarient.ORANGE_TULIP;
/*    */         case 1:
/* 38 */           return BlockFlowers.EnumFlowerVarient.RED_TULIP;
/*    */         case 2:
/* 40 */           return BlockFlowers.EnumFlowerVarient.PINK_TULIP;
/*    */       } 
/*    */       
/* 43 */       return BlockFlowers.EnumFlowerVarient.WHITE_TULIP;
/*    */     } 
/*    */     
/* 46 */     if (paramRandom.nextInt(3) > 0) {
/* 47 */       int i = paramRandom.nextInt(3);
/* 48 */       if (i == 0)
/* 49 */         return BlockFlowers.EnumFlowerVarient.POPPY; 
/* 50 */       if (i == 1) {
/* 51 */         return BlockFlowers.EnumFlowerVarient.HOUSTONIA;
/*    */       }
/* 53 */       return BlockFlowers.EnumFlowerVarient.OXEYE_DAISY;
/*    */     } 
/*    */     
/* 56 */     return BlockFlowers.EnumFlowerVarient.DANDELION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 62 */     double d = k.a((paramBlockPosition.getX() + 8) / 200.0D, (paramBlockPosition.getZ() + 8) / 200.0D);
/* 63 */     if (d < -0.8D) {
/* 64 */       this.s.B = 15;
/* 65 */       this.s.C = 5;
/*    */     } else {
/* 67 */       this.s.B = 4;
/* 68 */       this.s.C = 10;
/*    */       
/* 70 */       l.a(BlockTallPlant.EnumTallFlowerVariants.GRASS);
/* 71 */       for (byte b = 0; b < 7; b++) {
/* 72 */         int i = paramRandom.nextInt(16) + 8;
/* 73 */         int j = paramRandom.nextInt(16) + 8;
/* 74 */         int k = paramRandom.nextInt(paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)).getY() + 32);
/* 75 */         l.generate(paramWorld, paramRandom, paramBlockPosition.a(i, k, j));
/*    */       } 
/*    */     } 
/* 78 */     if (this.x) {
/* 79 */       l.a(BlockTallPlant.EnumTallFlowerVariants.SUNFLOWER);
/* 80 */       for (byte b = 0; b < 10; b++) {
/* 81 */         int i = paramRandom.nextInt(16) + 8;
/* 82 */         int j = paramRandom.nextInt(16) + 8;
/* 83 */         int k = paramRandom.nextInt(paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)).getY() + 32);
/* 84 */         l.generate(paramWorld, paramRandom, paramBlockPosition.a(i, k, j));
/*    */       } 
/*    */     } 
/* 87 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 92 */     if (paramRandom.nextInt(3) == 0) {
/* 93 */       return n;
/*    */     }
/* 95 */     return m;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomePlains.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */