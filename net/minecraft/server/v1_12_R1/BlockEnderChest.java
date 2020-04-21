/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ public class BlockEnderChest
/*     */   extends BlockTileEntity
/*     */ {
/*  33 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  34 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/*     */   
/*     */   protected BlockEnderChest() {
/*  37 */     super(Material.STONE);
/*  38 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
/*  39 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  44 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/*  64 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  69 */     return Item.getItemOf(Blocks.OBSIDIAN);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random paramRandom) {
/*  74 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean n() {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  84 */     return getBlockData().set(FACING, paramEntityLiving.getDirection().opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/*  89 */     paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(FACING, paramEntityLiving.getDirection().opposite()), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  94 */     InventoryEnderChest inventoryEnderChest = paramEntityHuman.getEnderChest();
/*  95 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  96 */     if (inventoryEnderChest == null || !(tileEntity instanceof TileEntityEnderChest)) {
/*  97 */       return true;
/*     */     }
/*     */     
/* 100 */     if (paramWorld.getType(paramBlockPosition.up()).l()) {
/* 101 */       return true;
/*     */     }
/*     */     
/* 104 */     if (paramWorld.isClientSide) {
/* 105 */       return true;
/*     */     }
/*     */     
/* 108 */     inventoryEnderChest.a((TileEntityEnderChest)tileEntity);
/* 109 */     paramEntityHuman.openContainer(inventoryEnderChest);
/* 110 */     paramEntityHuman.b(StatisticList.V);
/*     */     
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/* 117 */     return new TileEntityEnderChest();
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
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 139 */     EnumDirection enumDirection = EnumDirection.fromType1(paramInt);
/* 140 */     if (enumDirection.k() == EnumDirection.EnumAxis.Y) {
/* 141 */       enumDirection = EnumDirection.NORTH;
/*     */     }
/* 143 */     return getBlockData()
/* 144 */       .set(FACING, enumDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 149 */     return ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 154 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 159 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 164 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 169 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */