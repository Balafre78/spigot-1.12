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
/*    */ public class BiomeBigHills
/*    */   extends BiomeBase
/*    */ {
/* 20 */   private final WorldGenerator x = new WorldGenMinable(Blocks.MONSTER_EGG.getBlockData().set(BlockMonsterEggs.VARIANT, BlockMonsterEggs.EnumMonsterEggVarient.STONE), 9); private final Type z;
/* 21 */   private final WorldGenTaiga2 y = new WorldGenTaiga2(false);
/*    */   
/*    */   public enum Type {
/* 24 */     NORMAL, EXTRA_TREES, MUTATED;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected BiomeBigHills(Type paramType, BiomeBase.a parama) {
/* 30 */     super(parama);
/* 31 */     if (paramType == Type.EXTRA_TREES) {
/* 32 */       this.s.z = 3;
/*    */     }
/* 34 */     this.u.add(new BiomeBase.BiomeMeta((Class)EntityLlama.class, 5, 4, 6));
/*    */     
/* 36 */     this.z = paramType;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 41 */     if (paramRandom.nextInt(3) > 0) {
/* 42 */       return this.y;
/*    */     }
/* 44 */     return super.a(paramRandom);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 49 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*    */ 
/*    */     
/* 52 */     int i = 3 + paramRandom.nextInt(6); int j;
/* 53 */     for (j = 0; j < i; j++) {
/* 54 */       int k = paramRandom.nextInt(16);
/* 55 */       int m = paramRandom.nextInt(28) + 4;
/* 56 */       int n = paramRandom.nextInt(16);
/*    */       
/* 58 */       BlockPosition blockPosition = paramBlockPosition.a(k, m, n);
/* 59 */       if (paramWorld.getType(blockPosition).getBlock() == Blocks.STONE) {
/* 60 */         paramWorld.setTypeAndData(blockPosition, Blocks.EMERALD_ORE.getBlockData(), 2);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 65 */     for (i = 0; i < 7; i++) {
/* 66 */       j = paramRandom.nextInt(16);
/* 67 */       int k = paramRandom.nextInt(64);
/* 68 */       int m = paramRandom.nextInt(16);
/* 69 */       this.x.generate(paramWorld, paramRandom, paramBlockPosition.a(j, k, m));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, ChunkSnapshot paramChunkSnapshot, int paramInt1, int paramInt2, double paramDouble) {
/* 75 */     this.q = Blocks.GRASS.getBlockData();
/* 76 */     this.r = Blocks.DIRT.getBlockData();
/* 77 */     if ((paramDouble < -1.0D || paramDouble > 2.0D) && this.z == Type.MUTATED) {
/* 78 */       this.q = Blocks.GRAVEL.getBlockData();
/* 79 */       this.r = Blocks.GRAVEL.getBlockData();
/* 80 */     } else if (paramDouble > 1.0D && this.z != Type.EXTRA_TREES) {
/* 81 */       this.q = Blocks.STONE.getBlockData();
/* 82 */       this.r = Blocks.STONE.getBlockData();
/*    */     } 
/* 84 */     b(paramWorld, paramRandom, paramChunkSnapshot, paramInt1, paramInt2, paramDouble);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeBigHills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */