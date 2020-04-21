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
/*    */ public class BlockObsidian
/*    */   extends Block
/*    */ {
/*    */   public BlockObsidian() {
/* 15 */     super(Material.STONE);
/* 16 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 21 */     return Item.getItemOf(Blocks.OBSIDIAN);
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 26 */     return MaterialMapColor.F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockObsidian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */