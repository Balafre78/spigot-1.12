/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPotatoes
/*    */   extends BlockCrops
/*    */ {
/* 13 */   private static final AxisAlignedBB[] a = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D) };
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
/*    */   protected Item h() {
/* 26 */     return Items.POTATO;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item i() {
/* 31 */     return Items.POTATO;
/*    */   }
/*    */ 
/*    */   
/*    */   public void dropNaturally(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat, int paramInt) {
/* 36 */     super.dropNaturally(paramWorld, paramBlockPosition, paramIBlockData, paramFloat, paramInt);
/*    */     
/* 38 */     if (paramWorld.isClientSide) {
/*    */       return;
/*    */     }
/*    */     
/* 42 */     if (z(paramIBlockData) && paramWorld.random.nextInt(50) == 0) {
/* 43 */       a(paramWorld, paramBlockPosition, new ItemStack(Items.POISONOUS_POTATO));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 49 */     return a[((Integer)paramIBlockData.get(e())).intValue()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPotatoes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */