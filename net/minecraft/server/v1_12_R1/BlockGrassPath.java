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
/*    */ 
/*    */ public class BlockGrassPath
/*    */   extends Block
/*    */ {
/* 17 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
/*    */   
/*    */   protected BlockGrassPath() {
/* 20 */     super(Material.EARTH);
/* 21 */     e(255);
/*    */   }
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
/*    */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 42 */     super.onPlace(paramWorld, paramBlockPosition, paramIBlockData);
/*    */     
/* 44 */     b(paramWorld, paramBlockPosition);
/*    */   }
/*    */   
/*    */   private void b(World paramWorld, BlockPosition paramBlockPosition) {
/* 48 */     if (paramWorld.getType(paramBlockPosition.up()).getMaterial().isBuildable()) {
/* 49 */       paramWorld.setTypeUpdate(paramBlockPosition, Blocks.DIRT.getBlockData());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 55 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData paramIBlockData) {
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c(IBlockData paramIBlockData) {
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 70 */     return Blocks.DIRT.getDropType(Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.DIRT), paramRandom, paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 75 */     return new ItemStack(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 80 */     super.a(paramIBlockData, paramWorld, paramBlockPosition1, paramBlock, paramBlockPosition2);
/*    */     
/* 82 */     b(paramWorld, paramBlockPosition1);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 87 */     if (paramEnumDirection == EnumDirection.DOWN) {
/* 88 */       return EnumBlockFaceShape.SOLID;
/*    */     }
/* 90 */     return EnumBlockFaceShape.UNDEFINED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockGrassPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */