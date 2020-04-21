/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class BiomeTheEnd
/*    */   extends BiomeBase
/*    */ {
/*    */   public BiomeTheEnd(BiomeBase.a parama) {
/*  8 */     super(parama);
/*  9 */     this.t.clear();
/* 10 */     this.u.clear();
/* 11 */     this.v.clear();
/* 12 */     this.w.clear();
/*    */     
/* 14 */     this.t.add(new BiomeBase.BiomeMeta((Class)EntityEnderman.class, 10, 4, 4));
/* 15 */     this.q = Blocks.DIRT.getBlockData();
/* 16 */     this.r = Blocks.DIRT.getBlockData();
/*    */     
/* 18 */     this.s = new BiomeTheEndDecorator();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeTheEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */