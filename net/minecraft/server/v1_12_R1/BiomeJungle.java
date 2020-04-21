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
/*    */ public class BiomeJungle
/*    */   extends BiomeBase
/*    */ {
/*    */   private final boolean x;
/* 28 */   private static final IBlockData y = Blocks.LOG.getBlockData().set(BlockLog1.VARIANT, BlockWood.EnumLogVariant.JUNGLE);
/* 29 */   private static final IBlockData z = Blocks.LEAVES.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(BlockLeaves1.VARIANT, BlockWood.EnumLogVariant.JUNGLE).set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
/* 30 */   private static final IBlockData A = Blocks.LEAVES.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(BlockLeaves1.VARIANT, BlockWood.EnumLogVariant.OAK).set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
/*    */   
/*    */   public BiomeJungle(boolean paramBoolean, BiomeBase.a parama) {
/* 33 */     super(parama);
/* 34 */     this.x = paramBoolean;
/* 35 */     if (paramBoolean) {
/* 36 */       this.s.z = 2;
/*    */     } else {
/* 38 */       this.s.z = 50;
/*    */     } 
/* 40 */     this.s.C = 25;
/* 41 */     this.s.B = 4;
/*    */     
/* 43 */     if (!paramBoolean) {
/* 44 */       this.t.add(new BiomeBase.BiomeMeta((Class)EntityOcelot.class, 2, 1, 1));
/*    */     }
/*    */     
/* 47 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityParrot.class, 40, 1, 2));
/*    */ 
/*    */     
/* 50 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityChicken.class, 10, 4, 4));
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 55 */     if (paramRandom.nextInt(10) == 0) {
/* 56 */       return n;
/*    */     }
/* 58 */     if (paramRandom.nextInt(2) == 0) {
/* 59 */       return new WorldGenGroundBush(y, A);
/*    */     }
/* 61 */     if (!this.x && paramRandom.nextInt(3) == 0) {
/* 62 */       return new WorldGenJungleTree(false, 10, 20, y, z);
/*    */     }
/* 64 */     return new WorldGenTrees(false, 4 + paramRandom.nextInt(7), y, z, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenerator b(Random paramRandom) {
/* 69 */     if (paramRandom.nextInt(4) == 0) {
/* 70 */       return new WorldGenGrass(BlockLongGrass.EnumTallGrassType.FERN);
/*    */     }
/* 72 */     return new WorldGenGrass(BlockLongGrass.EnumTallGrassType.GRASS);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 77 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*    */ 
/*    */     
/* 80 */     int i = paramRandom.nextInt(16) + 8;
/* 81 */     int j = paramRandom.nextInt(16) + 8;
/* 82 */     int k = paramRandom.nextInt(paramWorld.getHighestBlockYAt(paramBlockPosition.a(i, 0, j)).getY() * 2);
/* 83 */     (new WorldGenMelon()).generate(paramWorld, paramRandom, paramBlockPosition.a(i, k, j));
/*    */ 
/*    */     
/* 86 */     WorldGenVines worldGenVines = new WorldGenVines();
/*    */     
/* 88 */     for (j = 0; j < 50; j++) {
/* 89 */       k = paramRandom.nextInt(16) + 8;
/* 90 */       char c = '';
/* 91 */       int m = paramRandom.nextInt(16) + 8;
/*    */       
/* 93 */       worldGenVines.generate(paramWorld, paramRandom, paramBlockPosition.a(k, 128, m));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeJungle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */