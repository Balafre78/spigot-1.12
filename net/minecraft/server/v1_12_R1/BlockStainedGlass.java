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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStainedGlass
/*    */   extends BlockHalfTransparent
/*    */ {
/* 20 */   public static final BlockStateEnum<EnumColor> COLOR = BlockStateEnum.of("color", EnumColor.class);
/*    */   
/*    */   public BlockStainedGlass(Material paramMaterial) {
/* 23 */     super(paramMaterial, false);
/* 24 */     w(this.blockStateList.getBlockData().set(COLOR, EnumColor.WHITE));
/* 25 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDropData(IBlockData paramIBlockData) {
/* 30 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
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
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 42 */     return MaterialMapColor.a(paramIBlockData.<EnumColor>get(COLOR));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(Random paramRandom) {
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean n() {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c(IBlockData paramIBlockData) {
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData fromLegacyData(int paramInt) {
/* 67 */     return getBlockData()
/* 68 */       .set(COLOR, EnumColor.fromColorIndex(paramInt));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 73 */     if (!paramWorld.isClientSide) {
/* 74 */       BlockBeacon.c(paramWorld, paramBlockPosition);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 80 */     if (!paramWorld.isClientSide) {
/* 81 */       BlockBeacon.c(paramWorld, paramBlockPosition);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData paramIBlockData) {
/* 87 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 92 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { COLOR });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStainedGlass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */