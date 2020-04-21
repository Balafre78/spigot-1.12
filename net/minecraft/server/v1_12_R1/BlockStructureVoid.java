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
/*    */ 
/*    */ public class BlockStructureVoid
/*    */   extends Block
/*    */ {
/* 17 */   private static final AxisAlignedBB a = new AxisAlignedBB(0.3D, 0.3D, 0.3D, 0.7D, 0.7D, 0.7D);
/*    */   
/*    */   protected BlockStructureVoid() {
/* 20 */     super(Material.J);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 25 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 31 */     return k;
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 36 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData paramIBlockData) {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c(IBlockData paramIBlockData) {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void dropNaturally(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat, int paramInt) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumPistonReaction h(IBlockData paramIBlockData) {
/* 60 */     return EnumPistonReaction.DESTROY;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 65 */     return EnumBlockFaceShape.UNDEFINED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStructureVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */