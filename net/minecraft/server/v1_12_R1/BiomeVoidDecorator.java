/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeVoidDecorator
/*    */   extends BiomeDecorator
/*    */ {
/*    */   public void a(World paramWorld, Random paramRandom, BiomeBase paramBiomeBase, BlockPosition paramBlockPosition) {
/* 14 */     BlockPosition blockPosition1 = paramWorld.getSpawn();
/*    */     
/* 16 */     byte b = 16;
/*    */     
/* 18 */     double d = blockPosition1.n(paramBlockPosition.a(8, blockPosition1.getY(), 8));
/* 19 */     if (d > 1024.0D) {
/*    */       return;
/*    */     }
/*    */     
/* 23 */     BlockPosition blockPosition2 = new BlockPosition(blockPosition1.getX() - 16, blockPosition1.getY() - 1, blockPosition1.getZ() - 16);
/* 24 */     BlockPosition blockPosition3 = new BlockPosition(blockPosition1.getX() + 16, blockPosition1.getY() - 1, blockPosition1.getZ() + 16);
/* 25 */     BlockPosition.MutableBlockPosition mutableBlockPosition = new BlockPosition.MutableBlockPosition(blockPosition2);
/* 26 */     for (int i = paramBlockPosition.getZ(); i < paramBlockPosition.getZ() + 16; i++) {
/* 27 */       for (int j = paramBlockPosition.getX(); j < paramBlockPosition.getX() + 16; j++) {
/* 28 */         if (i >= blockPosition2.getZ() && i <= blockPosition3.getZ() && j >= blockPosition2.getX() && j <= blockPosition3.getX()) {
/*    */ 
/*    */           
/* 31 */           mutableBlockPosition.c(j, mutableBlockPosition.getY(), i);
/* 32 */           if (blockPosition1.getX() == j && blockPosition1.getZ() == i) {
/* 33 */             paramWorld.setTypeAndData(mutableBlockPosition, Blocks.COBBLESTONE.getBlockData(), 2);
/*    */           } else {
/* 35 */             paramWorld.setTypeAndData(mutableBlockPosition, Blocks.STONE.getBlockData(), 2);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BiomeVoidDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */