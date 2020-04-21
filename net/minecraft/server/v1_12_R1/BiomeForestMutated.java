/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BiomeForestMutated
/*    */   extends BiomeForest
/*    */ {
/*    */   public BiomeForestMutated(BiomeBase.a parama) {
/*  9 */     super(BiomeForest.Type.BIRCH, parama);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenTreeAbstract a(Random paramRandom) {
/* 14 */     if (paramRandom.nextBoolean()) {
/* 15 */       return BiomeForest.x;
/*    */     }
/* 17 */     return BiomeForest.y;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeForestMutated.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */