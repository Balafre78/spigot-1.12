/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPowered
/*    */   extends Block
/*    */ {
/*    */   public BlockPowered(Material paramMaterial, MaterialMapColor paramMaterialMapColor) {
/* 13 */     super(paramMaterial, paramMaterialMapColor);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPowerSource(IBlockData paramIBlockData) {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 23 */     return 15;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPowered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */