/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockEndGateway
/*    */   extends BlockTileEntity
/*    */ {
/*    */   protected BlockEndGateway(Material paramMaterial) {
/* 22 */     super(paramMaterial);
/* 23 */     a(1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity a(World paramWorld, int paramInt) {
/* 28 */     return new TileEntityEndGateway();
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
/*    */   @Nullable
/*    */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 41 */     return k;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData paramIBlockData) {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c(IBlockData paramIBlockData) {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random paramRandom) {
/* 56 */     return 0;
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
/*    */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 89 */     return ItemStack.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 94 */     return MaterialMapColor.F;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 99 */     return EnumBlockFaceShape.UNDEFINED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockEndGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */