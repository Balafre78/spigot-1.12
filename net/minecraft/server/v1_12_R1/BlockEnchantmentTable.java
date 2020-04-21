/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEnchantmentTable
/*     */   extends BlockTileEntity
/*     */ {
/*  24 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
/*     */   
/*     */   protected BlockEnchantmentTable() {
/*  27 */     super(Material.STONE, MaterialMapColor.E);
/*  28 */     e(0);
/*  29 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  34 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  39 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/*  75 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  80 */     return new TileEntityEnchantTable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  85 */     if (paramWorld.isClientSide) {
/*  86 */       return true;
/*     */     }
/*     */     
/*  89 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  90 */     if (tileEntity instanceof TileEntityEnchantTable) {
/*  91 */       paramEntityHuman.openTileEntity((TileEntityEnchantTable)tileEntity);
/*     */     }
/*     */     
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/*  99 */     super.postPlace(paramWorld, paramBlockPosition, paramIBlockData, paramEntityLiving, paramItemStack);
/* 100 */     if (paramItemStack.hasName()) {
/* 101 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 102 */       if (tileEntity instanceof TileEntityEnchantTable) {
/* 103 */         ((TileEntityEnchantTable)tileEntity).setCustomName(paramItemStack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 110 */     if (paramEnumDirection == EnumDirection.DOWN) {
/* 111 */       return EnumBlockFaceShape.SOLID;
/*     */     }
/* 113 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockEnchantmentTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */