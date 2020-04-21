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
/*     */ public class BlockFurnace
/*     */   extends BlockTileEntity
/*     */ {
/*  27 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*     */   
/*     */   private final boolean b;
/*     */   private static boolean c;
/*     */   
/*     */   protected BlockFurnace(boolean paramBoolean) {
/*  33 */     super(Material.STONE);
/*  34 */     w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
/*  35 */     this.b = paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  40 */     return Item.getItemOf(Blocks.FURNACE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  45 */     e(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */   
/*     */   private void e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  49 */     if (paramWorld.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/*  53 */     IBlockData iBlockData1 = paramWorld.getType(paramBlockPosition.north());
/*  54 */     IBlockData iBlockData2 = paramWorld.getType(paramBlockPosition.south());
/*  55 */     IBlockData iBlockData3 = paramWorld.getType(paramBlockPosition.west());
/*  56 */     IBlockData iBlockData4 = paramWorld.getType(paramBlockPosition.east());
/*     */     
/*  58 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*     */     
/*  60 */     if (enumDirection == EnumDirection.NORTH && iBlockData1.b() && !iBlockData2.b()) {
/*  61 */       enumDirection = EnumDirection.SOUTH;
/*  62 */     } else if (enumDirection == EnumDirection.SOUTH && iBlockData2.b() && !iBlockData1.b()) {
/*  63 */       enumDirection = EnumDirection.NORTH;
/*  64 */     } else if (enumDirection == EnumDirection.WEST && iBlockData3.b() && !iBlockData4.b()) {
/*  65 */       enumDirection = EnumDirection.EAST;
/*  66 */     } else if (enumDirection == EnumDirection.EAST && iBlockData4.b() && !iBlockData3.b()) {
/*  67 */       enumDirection = EnumDirection.WEST;
/*     */     } 
/*     */     
/*  70 */     paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(FACING, enumDirection), 2);
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
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 112 */     if (paramWorld.isClientSide) {
/* 113 */       return true;
/*     */     }
/*     */     
/* 116 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 117 */     if (tileEntity instanceof TileEntityFurnace) {
/* 118 */       paramEntityHuman.openContainer((TileEntityFurnace)tileEntity);
/* 119 */       paramEntityHuman.b(StatisticList.Y);
/*     */     } 
/*     */     
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   public static void a(boolean paramBoolean, World paramWorld, BlockPosition paramBlockPosition) {
/* 126 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/* 127 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*     */     
/* 129 */     c = true;
/*     */     
/* 131 */     if (paramBoolean) {
/* 132 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.LIT_FURNACE.getBlockData().set(FACING, iBlockData.get(FACING)), 3);
/* 133 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.LIT_FURNACE.getBlockData().set(FACING, iBlockData.get(FACING)), 3);
/*     */     } else {
/* 135 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.FURNACE.getBlockData().set(FACING, iBlockData.get(FACING)), 3);
/* 136 */       paramWorld.setTypeAndData(paramBlockPosition, Blocks.FURNACE.getBlockData().set(FACING, iBlockData.get(FACING)), 3);
/*     */     } 
/* 138 */     c = false;
/*     */     
/* 140 */     if (tileEntity != null) {
/* 141 */       tileEntity.A();
/* 142 */       paramWorld.setTileEntity(paramBlockPosition, tileEntity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/* 148 */     return new TileEntityFurnace();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/* 153 */     return getBlockData().set(FACING, paramEntityLiving.getDirection().opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/* 158 */     paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(FACING, paramEntityLiving.getDirection().opposite()), 2);
/*     */     
/* 160 */     if (paramItemStack.hasName()) {
/* 161 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 162 */       if (tileEntity instanceof TileEntityFurnace) {
/* 163 */         ((TileEntityFurnace)tileEntity).setCustomName(paramItemStack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 170 */     if (!c) {
/* 171 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 172 */       if (tileEntity instanceof TileEntityFurnace) {
/* 173 */         InventoryUtils.dropInventory(paramWorld, paramBlockPosition, (TileEntityFurnace)tileEntity);
/*     */         
/* 175 */         paramWorld.updateAdjacentComparators(paramBlockPosition, this);
/*     */       } 
/*     */     } 
/* 178 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData paramIBlockData) {
/* 183 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition) {
/* 188 */     return Container.a(paramWorld.getTileEntity(paramBlockPosition));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 193 */     return new ItemStack(Blocks.FURNACE);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 198 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 203 */     EnumDirection enumDirection = EnumDirection.fromType1(paramInt);
/* 204 */     if (enumDirection.k() == EnumDirection.EnumAxis.Y) {
/* 205 */       enumDirection = EnumDirection.NORTH;
/*     */     }
/* 207 */     return getBlockData()
/* 208 */       .set(FACING, enumDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 213 */     return ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 218 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 223 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 228 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */