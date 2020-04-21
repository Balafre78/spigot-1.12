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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFlowerPot
/*     */   extends BlockTileEntity
/*     */ {
/*  33 */   public static final BlockStateInteger LEGACY_DATA = BlockStateInteger.of("legacy_data", 0, 15);
/*  34 */   public static final BlockStateEnum<EnumFlowerPotContents> CONTENTS = BlockStateEnum.of("contents", EnumFlowerPotContents.class);
/*     */ 
/*     */   
/*  37 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
/*     */   
/*     */   public BlockFlowerPot() {
/*  40 */     super(Material.ORIENTABLE);
/*  41 */     w(this.blockStateList.getBlockData().<EnumFlowerPotContents, EnumFlowerPotContents>set(CONTENTS, EnumFlowerPotContents.EMPTY).set(LEGACY_DATA, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  46 */     return LocaleI18n.get("item.flowerPot.name");
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  51 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/*  61 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  71 */     ItemStack itemStack1 = paramEntityHuman.b(paramEnumHand);
/*  72 */     TileEntityFlowerPot tileEntityFlowerPot = c(paramWorld, paramBlockPosition);
/*  73 */     if (tileEntityFlowerPot == null) {
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     ItemStack itemStack2 = tileEntityFlowerPot.getContents();
/*  78 */     if (itemStack2.isEmpty()) {
/*  79 */       if (!a(itemStack1)) {
/*  80 */         return false;
/*     */       }
/*  82 */       tileEntityFlowerPot.setContents(itemStack1);
/*  83 */       paramEntityHuman.b(StatisticList.T);
/*     */       
/*  85 */       if (!paramEntityHuman.abilities.canInstantlyBuild) {
/*  86 */         itemStack1.subtract(1);
/*     */       }
/*     */     } else {
/*  89 */       if (itemStack1.isEmpty()) {
/*  90 */         paramEntityHuman.a(paramEnumHand, itemStack2);
/*  91 */       } else if (!paramEntityHuman.c(itemStack2)) {
/*  92 */         paramEntityHuman.drop(itemStack2, false);
/*     */       } 
/*  94 */       tileEntityFlowerPot.setContents(ItemStack.a);
/*     */     } 
/*  96 */     tileEntityFlowerPot.update();
/*  97 */     paramWorld.notify(paramBlockPosition, paramIBlockData, paramIBlockData, 3);
/*     */     
/*  99 */     return true;
/*     */   }
/*     */   
/*     */   private boolean a(ItemStack paramItemStack) {
/* 103 */     Block block = Block.asBlock(paramItemStack.getItem());
/* 104 */     if (block == Blocks.YELLOW_FLOWER || block == Blocks.RED_FLOWER || block == Blocks.CACTUS || block == Blocks.BROWN_MUSHROOM || block == Blocks.RED_MUSHROOM || block == Blocks.SAPLING || block == Blocks.DEADBUSH) {
/* 105 */       return true;
/*     */     }
/* 107 */     int i = paramItemStack.getData();
/* 108 */     if (block == Blocks.TALLGRASS && i == BlockLongGrass.EnumTallGrassType.FERN.a()) {
/* 109 */       return true;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 116 */     TileEntityFlowerPot tileEntityFlowerPot = c(paramWorld, paramBlockPosition);
/* 117 */     if (tileEntityFlowerPot != null) {
/* 118 */       ItemStack itemStack = tileEntityFlowerPot.getContents();
/* 119 */       if (!itemStack.isEmpty()) {
/* 120 */         return itemStack;
/*     */       }
/*     */     } 
/* 123 */     return new ItemStack(Items.FLOWER_POT);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World paramWorld, BlockPosition paramBlockPosition) {
/* 128 */     return (super.canPlace(paramWorld, paramBlockPosition) && paramWorld.getType(paramBlockPosition.down()).q());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 133 */     if (!paramWorld.getType(paramBlockPosition1.down()).q()) {
/* 134 */       b(paramWorld, paramBlockPosition1, paramIBlockData, 0);
/*     */       
/* 136 */       paramWorld.setAir(paramBlockPosition1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 144 */     TileEntityFlowerPot tileEntityFlowerPot = c(paramWorld, paramBlockPosition);
/* 145 */     if (tileEntityFlowerPot != null && tileEntityFlowerPot.getItem() != null) {
/* 146 */       a(paramWorld, paramBlockPosition, new ItemStack(tileEntityFlowerPot.getItem(), 1, tileEntityFlowerPot.getData()));
/*     */     }
/* 148 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman) {
/* 153 */     super.a(paramWorld, paramBlockPosition, paramIBlockData, paramEntityHuman);
/*     */     
/* 155 */     if (paramEntityHuman.abilities.canInstantlyBuild) {
/* 156 */       TileEntityFlowerPot tileEntityFlowerPot = c(paramWorld, paramBlockPosition);
/* 157 */       if (tileEntityFlowerPot != null)
/*     */       {
/* 159 */         tileEntityFlowerPot.setContents(ItemStack.a);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 166 */     return Items.FLOWER_POT;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private TileEntityFlowerPot c(World paramWorld, BlockPosition paramBlockPosition) {
/* 171 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 172 */     if (tileEntity instanceof TileEntityFlowerPot) {
/* 173 */       return (TileEntityFlowerPot)tileEntity;
/*     */     }
/*     */     
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/* 182 */     Block block = null;
/* 183 */     int i = 0;
/* 184 */     switch (paramInt)
/*     */     
/*     */     { 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 235 */         return new TileEntityFlowerPot(Item.getItemOf(block), i);case 1: block = Blocks.RED_FLOWER; i = BlockFlowers.EnumFlowerVarient.POPPY.b();case 2: block = Blocks.YELLOW_FLOWER;case 3: block = Blocks.SAPLING; i = BlockWood.EnumLogVariant.OAK.a();case 4: block = Blocks.SAPLING; i = BlockWood.EnumLogVariant.SPRUCE.a();case 5: block = Blocks.SAPLING; i = BlockWood.EnumLogVariant.BIRCH.a();case 6: block = Blocks.SAPLING; i = BlockWood.EnumLogVariant.JUNGLE.a();case 12: block = Blocks.SAPLING; i = BlockWood.EnumLogVariant.ACACIA.a();case 13: block = Blocks.SAPLING; i = BlockWood.EnumLogVariant.DARK_OAK.a();
/*     */       case 7: block = Blocks.RED_MUSHROOM;
/*     */       case 8: block = Blocks.BROWN_MUSHROOM;
/*     */       case 9: block = Blocks.CACTUS;
/*     */       case 10: block = Blocks.DEADBUSH;
/* 240 */       case 11: break; }  block = Blocks.TALLGRASS; i = BlockLongGrass.EnumTallGrassType.FERN.a(); } protected BlockStateList getStateList() { return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { CONTENTS, LEGACY_DATA }); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 245 */     return ((Integer)paramIBlockData.<Integer>get(LEGACY_DATA)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 250 */     EnumFlowerPotContents enumFlowerPotContents = EnumFlowerPotContents.EMPTY;
/*     */     
/* 252 */     TileEntity tileEntity = (paramIBlockAccess instanceof ChunkCache) ? ((ChunkCache)paramIBlockAccess).a(paramBlockPosition, Chunk.EnumTileEntityState.CHECK) : paramIBlockAccess.getTileEntity(paramBlockPosition);
/* 253 */     if (tileEntity instanceof TileEntityFlowerPot)
/* 254 */     { TileEntityFlowerPot tileEntityFlowerPot = (TileEntityFlowerPot)tileEntity;
/* 255 */       Item item = tileEntityFlowerPot.getItem();
/*     */       
/* 257 */       if (item instanceof ItemBlock)
/* 258 */       { int i = tileEntityFlowerPot.getData();
/* 259 */         Block block = Block.asBlock(item);
/* 260 */         if (block == Blocks.SAPLING)
/* 261 */         { switch (null.a[BlockWood.EnumLogVariant.a(i).ordinal()])
/*     */           { case 1:
/* 263 */               enumFlowerPotContents = EnumFlowerPotContents.OAK_SAPLING;
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
/* 340 */               return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 2: enumFlowerPotContents = EnumFlowerPotContents.SPRUCE_SAPLING; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 3: enumFlowerPotContents = EnumFlowerPotContents.BIRCH_SAPLING; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 4: enumFlowerPotContents = EnumFlowerPotContents.JUNGLE_SAPLING; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 5: enumFlowerPotContents = EnumFlowerPotContents.ACACIA_SAPLING; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 6: enumFlowerPotContents = EnumFlowerPotContents.DARK_OAK_SAPLING; return paramIBlockData.set(CONTENTS, enumFlowerPotContents); }  enumFlowerPotContents = EnumFlowerPotContents.EMPTY; } else if (block == Blocks.TALLGRASS) { switch (i) { case 0: enumFlowerPotContents = EnumFlowerPotContents.DEAD_BUSH; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 2: enumFlowerPotContents = EnumFlowerPotContents.FERN; return paramIBlockData.set(CONTENTS, enumFlowerPotContents); }  enumFlowerPotContents = EnumFlowerPotContents.EMPTY; } else if (block == Blocks.YELLOW_FLOWER) { enumFlowerPotContents = EnumFlowerPotContents.DANDELION; } else if (block == Blocks.RED_FLOWER) { switch (null.b[BlockFlowers.EnumFlowerVarient.a(BlockFlowers.EnumFlowerType.RED, i).ordinal()]) { case 1: enumFlowerPotContents = EnumFlowerPotContents.POPPY; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 2: enumFlowerPotContents = EnumFlowerPotContents.BLUE_ORCHID; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 3: enumFlowerPotContents = EnumFlowerPotContents.ALLIUM; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 4: enumFlowerPotContents = EnumFlowerPotContents.HOUSTONIA; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 5: enumFlowerPotContents = EnumFlowerPotContents.RED_TULIP; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 6: enumFlowerPotContents = EnumFlowerPotContents.ORANGE_TULIP; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 7: enumFlowerPotContents = EnumFlowerPotContents.WHITE_TULIP; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 8: enumFlowerPotContents = EnumFlowerPotContents.PINK_TULIP; return paramIBlockData.set(CONTENTS, enumFlowerPotContents);case 9: enumFlowerPotContents = EnumFlowerPotContents.OXEYE_DAISY; return paramIBlockData.set(CONTENTS, enumFlowerPotContents); }  enumFlowerPotContents = EnumFlowerPotContents.EMPTY; } else if (block == Blocks.RED_MUSHROOM) { enumFlowerPotContents = EnumFlowerPotContents.MUSHROOM_RED; } else if (block == Blocks.BROWN_MUSHROOM) { enumFlowerPotContents = EnumFlowerPotContents.MUSHROOM_BROWN; } else if (block == Blocks.DEADBUSH) { enumFlowerPotContents = EnumFlowerPotContents.DEAD_BUSH; } else if (block == Blocks.CACTUS) { enumFlowerPotContents = EnumFlowerPotContents.CACTUS; }  }  }  return paramIBlockData.set(CONTENTS, enumFlowerPotContents);
/*     */   }
/*     */   
/*     */   public enum EnumFlowerPotContents implements INamable {
/* 344 */     EMPTY("empty"),
/* 345 */     POPPY("rose"),
/* 346 */     BLUE_ORCHID("blue_orchid"),
/* 347 */     ALLIUM("allium"),
/* 348 */     HOUSTONIA("houstonia"),
/* 349 */     RED_TULIP("red_tulip"),
/* 350 */     ORANGE_TULIP("orange_tulip"),
/* 351 */     WHITE_TULIP("white_tulip"),
/* 352 */     PINK_TULIP("pink_tulip"),
/* 353 */     OXEYE_DAISY("oxeye_daisy"),
/* 354 */     DANDELION("dandelion"),
/* 355 */     OAK_SAPLING("oak_sapling"),
/* 356 */     SPRUCE_SAPLING("spruce_sapling"),
/* 357 */     BIRCH_SAPLING("birch_sapling"),
/* 358 */     JUNGLE_SAPLING("jungle_sapling"),
/* 359 */     ACACIA_SAPLING("acacia_sapling"),
/* 360 */     DARK_OAK_SAPLING("dark_oak_sapling"),
/* 361 */     MUSHROOM_RED("mushroom_red"),
/* 362 */     MUSHROOM_BROWN("mushroom_brown"),
/* 363 */     DEAD_BUSH("dead_bush"),
/* 364 */     FERN("fern"),
/* 365 */     CACTUS("cactus");
/*     */     
/*     */     private final String w;
/*     */ 
/*     */     
/*     */     EnumFlowerPotContents(String param1String1) {
/* 371 */       this.w = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 376 */       return this.w;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 381 */       return this.w;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 392 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */