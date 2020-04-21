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
/*     */ public class BlockPistonMoving
/*     */   extends BlockTileEntity
/*     */ {
/*  33 */   public static final BlockStateDirection FACING = BlockPistonExtension.FACING;
/*  34 */   public static final BlockStateEnum<BlockPistonExtension.EnumPistonType> TYPE = BlockPistonExtension.TYPE;
/*     */   
/*     */   public BlockPistonMoving() {
/*  37 */     super(Material.PISTON);
/*  38 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(TYPE, BlockPistonExtension.EnumPistonType.DEFAULT));
/*  39 */     c(-1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  45 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TileEntity a(IBlockData paramIBlockData, EnumDirection paramEnumDirection, boolean paramBoolean1, boolean paramBoolean2) {
/*  50 */     return new TileEntityPiston(paramIBlockData, paramEnumDirection, paramBoolean1, paramBoolean2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  55 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  56 */     if (tileEntity instanceof TileEntityPiston) {
/*  57 */       ((TileEntityPiston)tileEntity).j();
/*     */     } else {
/*  59 */       super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void postBreak(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  76 */     BlockPosition blockPosition = paramBlockPosition.shift(((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).opposite());
/*  77 */     IBlockData iBlockData = paramWorld.getType(blockPosition);
/*  78 */     if (iBlockData.getBlock() instanceof BlockPiston && ((Boolean)iBlockData.<Boolean>get(BlockPiston.EXTENDED)).booleanValue()) {
/*  79 */       paramWorld.setAir(blockPosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  96 */     if (!paramWorld.isClientSide && paramWorld.getTileEntity(paramBlockPosition) == null) {
/*     */       
/*  98 */       paramWorld.setAir(paramBlockPosition);
/*  99 */       return true;
/*     */     } 
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 106 */     return Items.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropNaturally(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, float paramFloat, int paramInt) {
/* 111 */     if (paramWorld.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/* 115 */     TileEntityPiston tileEntityPiston = c(paramWorld, paramBlockPosition);
/* 116 */     if (tileEntityPiston == null) {
/*     */       return;
/*     */     }
/*     */     
/* 120 */     IBlockData iBlockData = tileEntityPiston.a();
/* 121 */     iBlockData.getBlock().b(paramWorld, paramBlockPosition, iBlockData, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MovingObjectPosition a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, Vec3D paramVec3D1, Vec3D paramVec3D2) {
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 133 */     if (!paramWorld.isClientSide) {
/* 134 */       paramWorld.getTileEntity(paramBlockPosition1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 141 */     TileEntityPiston tileEntityPiston = c(paramIBlockAccess, paramBlockPosition);
/* 142 */     if (tileEntityPiston == null) {
/* 143 */       return null;
/*     */     }
/*     */     
/* 146 */     return tileEntityPiston.a(paramIBlockAccess, paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, AxisAlignedBB paramAxisAlignedBB, List<AxisAlignedBB> paramList, @Nullable Entity paramEntity, boolean paramBoolean) {
/* 151 */     TileEntityPiston tileEntityPiston = c(paramWorld, paramBlockPosition);
/* 152 */     if (tileEntityPiston != null) {
/* 153 */       tileEntityPiston.a(paramWorld, paramBlockPosition, paramAxisAlignedBB, paramList, paramEntity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 159 */     TileEntityPiston tileEntityPiston = c(paramIBlockAccess, paramBlockPosition);
/* 160 */     if (tileEntityPiston != null) {
/* 161 */       return tileEntityPiston.a(paramIBlockAccess, paramBlockPosition);
/*     */     }
/* 163 */     return j;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private TileEntityPiston c(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 168 */     TileEntity tileEntity = paramIBlockAccess.getTileEntity(paramBlockPosition);
/* 169 */     if (tileEntity instanceof TileEntityPiston) {
/* 170 */       return (TileEntityPiston)tileEntity;
/*     */     }
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 177 */     return ItemStack.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 182 */     return getBlockData()
/* 183 */       .<Comparable, EnumDirection>set(FACING, BlockPistonExtension.b(paramInt))
/* 184 */       .set(TYPE, ((paramInt & 0x8) > 0) ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 189 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 194 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 199 */     int i = 0;
/*     */     
/* 201 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */     
/* 203 */     if (paramIBlockData.get(TYPE) == BlockPistonExtension.EnumPistonType.STICKY) {
/* 204 */       i |= 0x8;
/*     */     }
/*     */     
/* 207 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 212 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, TYPE });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 217 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPistonMoving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */