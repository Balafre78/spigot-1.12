/*    */ package net.minecraft.server.v1_12_R1;
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
/*    */ public class BlockCloth
/*    */   extends Block
/*    */ {
/* 16 */   public static final BlockStateEnum<EnumColor> COLOR = BlockStateEnum.of("color", EnumColor.class);
/*    */   
/*    */   public BlockCloth(Material paramMaterial) {
/* 19 */     super(paramMaterial);
/* 20 */     w(this.blockStateList.getBlockData().set(COLOR, EnumColor.WHITE));
/* 21 */     a(CreativeModeTab.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDropData(IBlockData paramIBlockData) {
/* 26 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
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
/* 38 */     return MaterialMapColor.a(paramIBlockData.<EnumColor>get(COLOR));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData fromLegacyData(int paramInt) {
/* 43 */     return getBlockData()
/* 44 */       .set(COLOR, EnumColor.fromColorIndex(paramInt));
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData paramIBlockData) {
/* 49 */     return ((EnumColor)paramIBlockData.<EnumColor>get(COLOR)).getColorIndex();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 54 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { COLOR });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCloth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */