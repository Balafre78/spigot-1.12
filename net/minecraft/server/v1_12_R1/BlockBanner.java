/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class BlockBanner
/*     */   extends BlockTileEntity
/*     */ {
/*  26 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  27 */   public static final BlockStateInteger ROTATION = BlockStateInteger.of("rotation", 0, 15);
/*  28 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/*     */   
/*     */   protected BlockBanner() {
/*  31 */     super(Material.WOOD);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  36 */     return LocaleI18n.get("item.banner.white.name");
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  42 */     return k;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean d() {
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  67 */     return new TileEntityBanner();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  72 */     return Items.BANNER;
/*     */   }
/*     */   
/*     */   private ItemStack c(World paramWorld, BlockPosition paramBlockPosition) {
/*  76 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  77 */     if (tileEntity instanceof TileEntityBanner) {
/*  78 */       return ((TileEntityBanner)tileEntity).l();
/*     */     }
/*     */     
/*  81 */     return ItemStack.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  86 */     ItemStack itemStack = c(paramWorld, paramBlockPosition);
/*  87 */     return itemStack.isEmpty() ? new ItemStack(Items.BANNER) : itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropNaturally(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat, int paramInt) {
/*  92 */     ItemStack itemStack = c(paramWorld, paramBlockPosition);
/*  93 */     if (itemStack.isEmpty()) {
/*  94 */       super.dropNaturally(paramWorld, paramBlockPosition, paramIBlockData, paramFloat, paramInt);
/*     */     } else {
/*  96 */       a(paramWorld, paramBlockPosition, itemStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/* 102 */     return (!b(paramWorld, paramBlockPosition) && super.canPlace(paramWorld, paramBlockPosition));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, @Nullable TileEntity paramTileEntity, ItemStack paramItemStack) {
/* 107 */     if (paramTileEntity instanceof TileEntityBanner) {
/* 108 */       TileEntityBanner tileEntityBanner = (TileEntityBanner)paramTileEntity;
/*     */       
/* 110 */       ItemStack itemStack = tileEntityBanner.l();
/*     */       
/* 112 */       a(paramWorld, paramBlockPosition, itemStack);
/*     */     } else {
/* 114 */       super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, (TileEntity)null, paramItemStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 120 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   public static class BlockWallBanner extends BlockBanner {
/* 124 */     protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 0.78125D, 1.0D);
/* 125 */     protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.78125D, 0.125D);
/* 126 */     protected static final AxisAlignedBB f = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 0.78125D, 1.0D);
/* 127 */     protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 0.78125D, 1.0D);
/*     */     
/*     */     public BlockWallBanner() {
/* 130 */       w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData a(IBlockData param1IBlockData, EnumBlockRotation param1EnumBlockRotation) {
/* 135 */       return param1IBlockData.set(FACING, param1EnumBlockRotation.a(param1IBlockData.<EnumDirection>get(FACING)));
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData a(IBlockData param1IBlockData, EnumBlockMirror param1EnumBlockMirror) {
/* 140 */       return param1IBlockData.a(param1EnumBlockMirror.a(param1IBlockData.<EnumDirection>get(FACING)));
/*     */     }
/*     */ 
/*     */     
/*     */     public AxisAlignedBB b(IBlockData param1IBlockData, IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition) {
/* 145 */       switch (BlockBanner.null.a[((EnumDirection)param1IBlockData.get(FACING)).ordinal()])
/*     */       
/*     */       { default:
/* 148 */           return d;
/*     */         case 2:
/* 150 */           return e;
/*     */         case 3:
/* 152 */           return f;
/*     */         case 4:
/* 154 */           break; }  return g;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(IBlockData param1IBlockData, World param1World, BlockPosition param1BlockPosition1, Block param1Block, BlockPosition param1BlockPosition2) {
/* 160 */       EnumDirection enumDirection = param1IBlockData.<EnumDirection>get(FACING);
/*     */       
/* 162 */       if (!param1World.getType(param1BlockPosition1.shift(enumDirection.opposite())).getMaterial().isBuildable()) {
/* 163 */         b(param1World, param1BlockPosition1, param1IBlockData, 0);
/* 164 */         param1World.setAir(param1BlockPosition1);
/*     */       } 
/*     */       
/* 167 */       super.a(param1IBlockData, param1World, param1BlockPosition1, param1Block, param1BlockPosition2);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData fromLegacyData(int param1Int) {
/* 172 */       EnumDirection enumDirection = EnumDirection.fromType1(param1Int);
/* 173 */       if (enumDirection.k() == EnumDirection.EnumAxis.Y) {
/* 174 */         enumDirection = EnumDirection.NORTH;
/*     */       }
/* 176 */       return getBlockData().set(FACING, enumDirection);
/*     */     }
/*     */ 
/*     */     
/*     */     public int toLegacyData(IBlockData param1IBlockData) {
/* 181 */       return ((EnumDirection)param1IBlockData.<EnumDirection>get(FACING)).a();
/*     */     }
/*     */ 
/*     */     
/*     */     protected BlockStateList getStateList() {
/* 186 */       return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BlockStandingBanner extends BlockBanner {
/*     */     public BlockStandingBanner() {
/* 192 */       w(this.blockStateList.getBlockData().set(ROTATION, Integer.valueOf(0)));
/*     */     }
/*     */ 
/*     */     
/*     */     public AxisAlignedBB b(IBlockData param1IBlockData, IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition) {
/* 197 */       return c;
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData a(IBlockData param1IBlockData, EnumBlockRotation param1EnumBlockRotation) {
/* 202 */       return param1IBlockData.set(ROTATION, Integer.valueOf(param1EnumBlockRotation.a(((Integer)param1IBlockData.<Integer>get(ROTATION)).intValue(), 16)));
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData a(IBlockData param1IBlockData, EnumBlockMirror param1EnumBlockMirror) {
/* 207 */       return param1IBlockData.set(ROTATION, Integer.valueOf(param1EnumBlockMirror.a(((Integer)param1IBlockData.<Integer>get(ROTATION)).intValue(), 16)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(IBlockData param1IBlockData, World param1World, BlockPosition param1BlockPosition1, Block param1Block, BlockPosition param1BlockPosition2) {
/* 212 */       if (!param1World.getType(param1BlockPosition1.down()).getMaterial().isBuildable()) {
/* 213 */         b(param1World, param1BlockPosition1, param1IBlockData, 0);
/* 214 */         param1World.setAir(param1BlockPosition1);
/*     */       } 
/*     */       
/* 217 */       super.a(param1IBlockData, param1World, param1BlockPosition1, param1Block, param1BlockPosition2);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockData fromLegacyData(int param1Int) {
/* 222 */       return getBlockData().set(ROTATION, Integer.valueOf(param1Int));
/*     */     }
/*     */ 
/*     */     
/*     */     public int toLegacyData(IBlockData param1IBlockData) {
/* 227 */       return ((Integer)param1IBlockData.<Integer>get(ROTATION)).intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     protected BlockStateList getStateList() {
/* 232 */       return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { ROTATION });
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */