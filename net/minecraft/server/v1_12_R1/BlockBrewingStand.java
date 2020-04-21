/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class BlockBrewingStand
/*     */   extends BlockTileEntity
/*     */ {
/*  34 */   public static final BlockStateBoolean[] HAS_BOTTLE = new BlockStateBoolean[] {
/*  35 */       BlockStateBoolean.of("has_bottle_0"), 
/*  36 */       BlockStateBoolean.of("has_bottle_1"), 
/*  37 */       BlockStateBoolean.of("has_bottle_2")
/*     */     };
/*  39 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/*  40 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 0.875D, 0.5625D);
/*     */   
/*     */   public BlockBrewingStand() {
/*  43 */     super(Material.ORE);
/*  44 */     w(this.blockStateList.getBlockData().<Comparable, Boolean>set(HAS_BOTTLE[0], Boolean.valueOf(false)).<Comparable, Boolean>set(HAS_BOTTLE[1], Boolean.valueOf(false)).set(HAS_BOTTLE[2], Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  49 */     return LocaleI18n.get("item.brewingStand.name");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/*  59 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  64 */     return new TileEntityBrewingStand();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  74 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, c);
/*  75 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, b);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  80 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  85 */     if (paramWorld.isClientSide) {
/*  86 */       return true;
/*     */     }
/*     */     
/*  89 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  90 */     if (tileEntity instanceof TileEntityBrewingStand) {
/*  91 */       paramEntityHuman.openContainer((TileEntityBrewingStand)tileEntity);
/*  92 */       paramEntityHuman.b(StatisticList.M);
/*     */     } 
/*     */     
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/* 100 */     if (paramItemStack.hasName()) {
/* 101 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 102 */       if (tileEntity instanceof TileEntityBrewingStand) {
/* 103 */         ((TileEntityBrewingStand)tileEntity).setCustomName(paramItemStack.getName());
/*     */       }
/*     */     } 
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
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 119 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 120 */     if (tileEntity instanceof TileEntityBrewingStand) {
/* 121 */       InventoryUtils.dropInventory(paramWorld, paramBlockPosition, (TileEntityBrewingStand)tileEntity);
/*     */     }
/* 123 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 128 */     return Items.BREWING_STAND;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 133 */     return new ItemStack(Items.BREWING_STAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData paramIBlockData) {
/* 138 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition) {
/* 143 */     return Container.a(paramWorld.getTileEntity(paramBlockPosition));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 153 */     IBlockData iBlockData = getBlockData();
/*     */     
/* 155 */     for (byte b = 0; b < 3; b++) {
/* 156 */       iBlockData = iBlockData.set(HAS_BOTTLE[b], Boolean.valueOf(((paramInt & 1 << b) > 0)));
/*     */     }
/*     */     
/* 159 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 164 */     int i = 0;
/*     */     
/* 166 */     for (byte b = 0; b < 3; b++) {
/* 167 */       if (((Boolean)paramIBlockData.<Boolean>get(HAS_BOTTLE[b])).booleanValue()) {
/* 168 */         i |= 1 << b;
/*     */       }
/*     */     } 
/*     */     
/* 172 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 177 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HAS_BOTTLE[0], HAS_BOTTLE[1], HAS_BOTTLE[2] });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 182 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */