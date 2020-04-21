/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockHay
/*    */   extends BlockRotatable
/*    */ {
/*    */   public BlockHay() {
/* 13 */     super(Material.GRASS, MaterialMapColor.u);
/* 14 */     w(this.blockStateList.getBlockData().set(AXIS, EnumDirection.EnumAxis.Y));
/* 15 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fallOn(World paramWorld, BlockPosition paramBlockPosition, Entity paramEntity, float paramFloat) {
/* 20 */     paramEntity.e(paramFloat, 0.2F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockHay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */