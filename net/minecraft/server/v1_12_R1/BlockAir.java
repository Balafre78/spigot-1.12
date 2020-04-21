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
/*    */ public class BlockAir
/*    */   extends Block
/*    */ {
/*    */   protected BlockAir() {
/* 16 */     super(Material.AIR);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 21 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 27 */     return k;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData paramIBlockData) {
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData paramIBlockData, boolean paramBoolean) {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void dropNaturally(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat, int paramInt) {}
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c(IBlockData paramIBlockData) {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 56 */     return EnumBlockFaceShape.UNDEFINED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockAir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */