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
/*    */ public class BlockGravel
/*    */   extends BlockFalling
/*    */ {
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 15 */     if (paramInt > 3) {
/* 16 */       paramInt = 3;
/*    */     }
/* 18 */     if (paramRandom.nextInt(10 - paramInt * 3) == 0) {
/* 19 */       return Items.FLINT;
/*    */     }
/* 21 */     return super.getDropType(paramIBlockData, paramRandom, paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 26 */     return MaterialMapColor.n;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockGravel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */