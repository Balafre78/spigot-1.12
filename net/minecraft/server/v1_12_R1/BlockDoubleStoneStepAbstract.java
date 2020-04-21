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
/*     */ public abstract class BlockDoubleStoneStepAbstract
/*     */   extends BlockStepAbstract
/*     */ {
/*  23 */   public static final BlockStateBoolean SEAMLESS = BlockStateBoolean.of("seamless");
/*  24 */   public static final BlockStateEnum<EnumStoneSlab2Variant> VARIANT = BlockStateEnum.of("variant", EnumStoneSlab2Variant.class);
/*     */   
/*     */   public BlockDoubleStoneStepAbstract() {
/*  27 */     super(Material.STONE);
/*  28 */     IBlockData iBlockData = this.blockStateList.getBlockData();
/*  29 */     if (e()) {
/*  30 */       iBlockData = iBlockData.set(SEAMLESS, Boolean.valueOf(false));
/*     */     } else {
/*  32 */       iBlockData = iBlockData.set(HALF, BlockStepAbstract.EnumSlabHalf.BOTTOM);
/*     */     } 
/*  34 */     w(iBlockData.set(VARIANT, EnumStoneSlab2Variant.RED_SANDSTONE));
/*  35 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  40 */     return LocaleI18n.get(a() + ".red_sandstone.name");
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  45 */     return Item.getItemOf(Blocks.STONE_SLAB2);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  50 */     return new ItemStack(Blocks.STONE_SLAB2, 1, ((EnumStoneSlab2Variant)paramIBlockData.<EnumStoneSlab2Variant>get(VARIANT)).a());
/*     */   }
/*     */ 
/*     */   
/*     */   public String b(int paramInt) {
/*  55 */     return a() + "." + EnumStoneSlab2Variant.a(paramInt).d();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState<?> g() {
/*  60 */     return VARIANT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Comparable<?> a(ItemStack paramItemStack) {
/*  65 */     return EnumStoneSlab2Variant.a(paramItemStack.getData() & 0x7);
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
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  77 */     IBlockData iBlockData = getBlockData().set(VARIANT, EnumStoneSlab2Variant.a(paramInt & 0x7));
/*     */     
/*  79 */     if (e()) {
/*  80 */       iBlockData = iBlockData.set(SEAMLESS, Boolean.valueOf(((paramInt & 0x8) != 0)));
/*     */     } else {
/*  82 */       iBlockData = iBlockData.set(HALF, ((paramInt & 0x8) == 0) ? BlockStepAbstract.EnumSlabHalf.BOTTOM : BlockStepAbstract.EnumSlabHalf.TOP);
/*     */     } 
/*     */     
/*  85 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  90 */     int i = 0;
/*     */     
/*  92 */     i |= ((EnumStoneSlab2Variant)paramIBlockData.<EnumStoneSlab2Variant>get(VARIANT)).a();
/*     */     
/*  94 */     if (e()) {
/*  95 */       if (((Boolean)paramIBlockData.<Boolean>get(SEAMLESS)).booleanValue()) {
/*  96 */         i |= 0x8;
/*     */       }
/*     */     }
/*  99 */     else if (paramIBlockData.get(HALF) == BlockStepAbstract.EnumSlabHalf.TOP) {
/* 100 */       i |= 0x8;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 109 */     if (e()) {
/* 110 */       return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { SEAMLESS, VARIANT });
/*     */     }
/*     */     
/* 113 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HALF, VARIANT });
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 118 */     return ((EnumStoneSlab2Variant)paramIBlockData.<EnumStoneSlab2Variant>get(VARIANT)).c();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/* 123 */     return ((EnumStoneSlab2Variant)paramIBlockData.<EnumStoneSlab2Variant>get(VARIANT)).a();
/*     */   }
/*     */   
/*     */   public enum EnumStoneSlab2Variant implements INamable {
/* 127 */     RED_SANDSTONE(0, "red_sandstone", BlockSand.EnumSandVariant.RED_SAND.d());
/*     */ 
/*     */     
/*     */     private final MaterialMapColor e;
/*     */     
/*     */     private final String d;
/*     */     
/*     */     private final int c;
/*     */     
/* 136 */     private static final EnumStoneSlab2Variant[] b = new EnumStoneSlab2Variant[(values()).length];
/*     */     static {
/* 138 */       for (EnumStoneSlab2Variant enumStoneSlab2Variant : values()) {
/* 139 */         b[enumStoneSlab2Variant.a()] = enumStoneSlab2Variant;
/*     */       }
/*     */     }
/*     */     
/*     */     EnumStoneSlab2Variant(int param1Int1, String param1String1, MaterialMapColor param1MaterialMapColor) {
/* 144 */       this.c = param1Int1;
/* 145 */       this.d = param1String1;
/* 146 */       this.e = param1MaterialMapColor;
/*     */     }
/*     */     
/*     */     public int a() {
/* 150 */       return this.c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MaterialMapColor c() {
/* 158 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 163 */       return this.d;
/*     */     }
/*     */     
/*     */     public static EnumStoneSlab2Variant a(int param1Int) {
/* 167 */       if (param1Int < 0 || param1Int >= b.length) {
/* 168 */         param1Int = 0;
/*     */       }
/* 170 */       return b[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 175 */       return this.d;
/*     */     }
/*     */     
/*     */     public String d() {
/* 179 */       return this.d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDoubleStoneStepAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */