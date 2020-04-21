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
/*    */ public class BlockLightStone
/*    */   extends Block
/*    */ {
/*    */   public BlockLightStone(Material paramMaterial) {
/* 17 */     super(paramMaterial);
/* 18 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDropCount(int paramInt, Random paramRandom) {
/* 23 */     return MathHelper.clamp(a(paramRandom) + paramRandom.nextInt(paramInt + 1), 1, 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random paramRandom) {
/* 28 */     return 2 + paramRandom.nextInt(3);
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 33 */     return Items.GLOWSTONE_DUST;
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 38 */     return MaterialMapColor.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLightStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */