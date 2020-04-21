/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeSavannaMutated
/*    */   extends BiomeSavanna
/*    */ {
/*    */   public BiomeSavannaMutated(BiomeBase.a parama) {
/* 13 */     super(parama);
/* 14 */     this.s.z = 2;
/* 15 */     this.s.B = 2;
/* 16 */     this.s.C = 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, ChunkSnapshot paramChunkSnapshot, int paramInt1, int paramInt2, double paramDouble) {
/* 21 */     this.q = Blocks.GRASS.getBlockData();
/* 22 */     this.r = Blocks.DIRT.getBlockData();
/* 23 */     if (paramDouble > 1.75D) {
/* 24 */       this.q = Blocks.STONE.getBlockData();
/* 25 */       this.r = Blocks.STONE.getBlockData();
/* 26 */     } else if (paramDouble > -0.5D) {
/* 27 */       this.q = Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.COARSE_DIRT);
/*    */     } 
/* 29 */     b(paramWorld, paramRandom, paramChunkSnapshot, paramInt1, paramInt2, paramDouble);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition) {
/* 34 */     this.s.a(paramWorld, paramRandom, this, paramBlockPosition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeSavannaMutated.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */