/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBarrier
/*    */   extends Block
/*    */ {
/*    */   protected BlockBarrier() {
/* 10 */     super(Material.BANNER);
/* 11 */     j();
/* 12 */     b(6000001.0F);
/* 13 */     p();
/* 14 */     this.n = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 19 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData paramIBlockData) {
/* 24 */     return false;
/*    */   }
/*    */   
/*    */   public void dropNaturally(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat, int paramInt) {}
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockBarrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */