/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
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
/*     */ public class BlockHopper
/*     */   extends BlockTileEntity
/*     */ {
/*  33 */   public static final BlockStateDirection FACING = BlockStateDirection.of("facing", new Predicate<EnumDirection>()
/*     */       {
/*     */         public boolean a(@Nullable EnumDirection param1EnumDirection) {
/*  36 */           return (param1EnumDirection != EnumDirection.UP);
/*     */         }
/*     */       });
/*  39 */   public static final BlockStateBoolean ENABLED = BlockStateBoolean.of("enabled");
/*     */   
/*  41 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
/*     */   
/*  43 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
/*  44 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
/*  45 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  46 */   protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
/*     */   
/*     */   public BlockHopper() {
/*  49 */     super(Material.ORE, MaterialMapColor.n);
/*  50 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.DOWN).set(ENABLED, Boolean.valueOf(true)));
/*  51 */     a(CreativeModeTab.d);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  56 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/*  61 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, c);
/*  62 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, g);
/*  63 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, f);
/*  64 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, d);
/*  65 */     a(paramBlockPosition, paramAxisAlignedBB, paramList, e);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  70 */     EnumDirection enumDirection = paramEnumDirection.opposite();
/*  71 */     if (enumDirection == EnumDirection.UP) {
/*  72 */       enumDirection = EnumDirection.DOWN;
/*     */     }
/*  74 */     return getBlockData().<Comparable, EnumDirection>set(FACING, enumDirection).set(ENABLED, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  79 */     return new TileEntityHopper();
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/*  84 */     super.postPlace(paramWorld, paramBlockPosition, paramIBlockData, paramEntityLiving, paramItemStack);
/*     */     
/*  86 */     if (paramItemStack.hasName()) {
/*  87 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  88 */       if (tileEntity instanceof TileEntityHopper) {
/*  89 */         ((TileEntityHopper)tileEntity).setCustomName(paramItemStack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean k(IBlockData paramIBlockData) {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 101 */     e(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 106 */     if (paramWorld.isClientSide) {
/* 107 */       return true;
/*     */     }
/*     */     
/* 110 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 111 */     if (tileEntity instanceof TileEntityHopper) {
/* 112 */       paramEntityHuman.openContainer((TileEntityHopper)tileEntity);
/* 113 */       paramEntityHuman.b(StatisticList.P);
/*     */     } 
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 120 */     e(paramWorld, paramBlockPosition1, paramIBlockData);
/*     */   }
/*     */   
/*     */   private void e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 124 */     boolean bool = !paramWorld.isBlockIndirectlyPowered(paramBlockPosition);
/* 125 */     if (bool != ((Boolean)paramIBlockData.<Boolean>get(ENABLED)).booleanValue()) {
/* 126 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(ENABLED, Boolean.valueOf(bool)), 4);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 132 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 133 */     if (tileEntity instanceof TileEntityHopper) {
/* 134 */       InventoryUtils.dropInventory(paramWorld, paramBlockPosition, (TileEntityHopper)tileEntity);
/*     */       
/* 136 */       paramWorld.updateAdjacentComparators(paramBlockPosition, this);
/*     */     } 
/*     */     
/* 139 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 144 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumDirection b(int paramInt) {
/* 163 */     return EnumDirection.fromType1(paramInt & 0x7);
/*     */   }
/*     */   
/*     */   public static boolean f(int paramInt) {
/* 167 */     return ((paramInt & 0x8) != 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData paramIBlockData) {
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition) {
/* 177 */     return Container.a(paramWorld.getTileEntity(paramBlockPosition));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 187 */     return getBlockData()
/* 188 */       .<Comparable, EnumDirection>set(FACING, b(paramInt))
/* 189 */       .set(ENABLED, Boolean.valueOf(f(paramInt)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 194 */     int i = 0;
/*     */     
/* 196 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */     
/* 198 */     if (!((Boolean)paramIBlockData.<Boolean>get(ENABLED)).booleanValue()) {
/* 199 */       i |= 0x8;
/*     */     }
/*     */     
/* 202 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 207 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 212 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 217 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, ENABLED });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 222 */     if (paramEnumDirection == EnumDirection.UP) {
/* 223 */       return EnumBlockFaceShape.BOWL;
/*     */     }
/* 225 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */