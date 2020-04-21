/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSlowSand
/*    */   extends Block
/*    */ {
/* 16 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
/*    */   
/*    */   public BlockSlowSand() {
/* 19 */     super(Material.SAND, MaterialMapColor.C);
/* 20 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 26 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Entity paramEntity) {
/* 31 */     paramEntity.motX *= 0.4D;
/* 32 */     paramEntity.motZ *= 0.4D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSlowSand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */