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
/*    */ public class BiomeSwamp
/*    */   extends BiomeBase
/*    */ {
/* 17 */   protected static final IBlockData x = Blocks.WATERLILY.getBlockData();
/*    */   
/*    */   protected BiomeSwamp(BiomeBase.a parama) {
/* 20 */     super(parama);
/* 21 */     this.s.z = 2;
/* 22 */     this.s.B = 1;
/* 23 */     this.s.D = 1;
/* 24 */     this.s.E = 8;
/* 25 */     this.s.F = 10;
/* 26 */     this.s.J = 1;
/* 27 */     this.s.y = 4;
/* 28 */     this.s.I = 0;
/* 29 */     this.s.H = 0;
/* 30 */     this.s.C = 5;
/*    */     
/* 32 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntitySlime.class, 1, 1, 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 37 */     return o;
/*    */   }
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
/*    */   public BlockFlowers.EnumFlowerVarient a(Random paramRandom, BlockPosition paramBlockPosition) {
/* 56 */     return BlockFlowers.EnumFlowerVarient.BLUE_ORCHID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, ChunkSnapshot paramChunkSnapshot, int paramInt1, int paramInt2, double paramDouble) {
/* 61 */     double d = k.a(paramInt1 * 0.25D, paramInt2 * 0.25D);
/* 62 */     if (d > 0.0D) {
/* 63 */       int i = paramInt1 & 0xF;
/* 64 */       int j = paramInt2 & 0xF;
/* 65 */       for (char c = 'ÿ'; c >= '\000'; c--) {
/* 66 */         if (paramChunkSnapshot.a(j, c, i).getMaterial() != Material.AIR) {
/* 67 */           if (c == '>' && paramChunkSnapshot.a(j, c, i).getBlock() != Blocks.WATER) {
/* 68 */             paramChunkSnapshot.a(j, c, i, h);
/* 69 */             if (d < 0.12D) {
/* 70 */               paramChunkSnapshot.a(j, c + 1, i, x);
/*    */             }
/*    */           } 
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 78 */     b(paramWorld, paramRandom, paramChunkSnapshot, paramInt1, paramInt2, paramDouble);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 83 */     super.a(paramWorld, paramRandom, paramBlockPosition);
/*    */     
/* 85 */     if (paramRandom.nextInt(64) == 0)
/* 86 */       (new WorldGenFossils()).generate(paramWorld, paramRandom, paramBlockPosition); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeSwamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */