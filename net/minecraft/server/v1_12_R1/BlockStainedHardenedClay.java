/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStainedHardenedClay
/*    */   extends BlockCloth
/*    */ {
/* 10 */   private static final MaterialMapColor[] b = new MaterialMapColor[] { MaterialMapColor.M, MaterialMapColor.N, MaterialMapColor.O, MaterialMapColor.P, MaterialMapColor.Q, MaterialMapColor.R, MaterialMapColor.S, MaterialMapColor.T, MaterialMapColor.U, MaterialMapColor.V, MaterialMapColor.W, MaterialMapColor.X, MaterialMapColor.Y, MaterialMapColor.Z, MaterialMapColor.aa, MaterialMapColor.ab };
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
/*    */   public BlockStainedHardenedClay() {
/* 30 */     super(Material.STONE);
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 35 */     return b[((EnumColor)paramIBlockData.get((IBlockState)COLOR)).getColorIndex()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStainedHardenedClay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */