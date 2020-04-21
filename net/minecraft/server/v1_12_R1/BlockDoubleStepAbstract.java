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
/*     */ public abstract class BlockDoubleStepAbstract
/*     */   extends BlockStepAbstract
/*     */ {
/*  22 */   public static final BlockStateBoolean SEAMLESS = BlockStateBoolean.of("seamless");
/*  23 */   public static final BlockStateEnum<EnumStoneSlabVariant> VARIANT = BlockStateEnum.of("variant", EnumStoneSlabVariant.class);
/*     */   
/*     */   public BlockDoubleStepAbstract() {
/*  26 */     super(Material.STONE);
/*  27 */     IBlockData iBlockData = this.blockStateList.getBlockData();
/*  28 */     if (e()) {
/*  29 */       iBlockData = iBlockData.set(SEAMLESS, Boolean.valueOf(false));
/*     */     } else {
/*  31 */       iBlockData = iBlockData.set(HALF, BlockStepAbstract.EnumSlabHalf.BOTTOM);
/*     */     } 
/*  33 */     w(iBlockData.set(VARIANT, EnumStoneSlabVariant.STONE));
/*  34 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  39 */     return Item.getItemOf(Blocks.STONE_SLAB);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  44 */     return new ItemStack(Blocks.STONE_SLAB, 1, ((EnumStoneSlabVariant)paramIBlockData.<EnumStoneSlabVariant>get(VARIANT)).a());
/*     */   }
/*     */ 
/*     */   
/*     */   public String b(int paramInt) {
/*  49 */     return a() + "." + EnumStoneSlabVariant.a(paramInt).d();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState<?> g() {
/*  54 */     return VARIANT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Comparable<?> a(ItemStack paramItemStack) {
/*  59 */     return EnumStoneSlabVariant.a(paramItemStack.getData() & 0x7);
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
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  74 */     IBlockData iBlockData = getBlockData().set(VARIANT, EnumStoneSlabVariant.a(paramInt & 0x7));
/*     */     
/*  76 */     if (e()) {
/*  77 */       iBlockData = iBlockData.set(SEAMLESS, Boolean.valueOf(((paramInt & 0x8) != 0)));
/*     */     } else {
/*  79 */       iBlockData = iBlockData.set(HALF, ((paramInt & 0x8) == 0) ? BlockStepAbstract.EnumSlabHalf.BOTTOM : BlockStepAbstract.EnumSlabHalf.TOP);
/*     */     } 
/*     */     
/*  82 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  87 */     int i = 0;
/*     */     
/*  89 */     i |= ((EnumStoneSlabVariant)paramIBlockData.<EnumStoneSlabVariant>get(VARIANT)).a();
/*     */     
/*  91 */     if (e()) {
/*  92 */       if (((Boolean)paramIBlockData.<Boolean>get(SEAMLESS)).booleanValue()) {
/*  93 */         i |= 0x8;
/*     */       }
/*     */     }
/*  96 */     else if (paramIBlockData.get(HALF) == BlockStepAbstract.EnumSlabHalf.TOP) {
/*  97 */       i |= 0x8;
/*     */     } 
/*     */ 
/*     */     
/* 101 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 106 */     if (e()) {
/* 107 */       return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { SEAMLESS, VARIANT });
/*     */     }
/*     */     
/* 110 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HALF, VARIANT });
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/* 115 */     return ((EnumStoneSlabVariant)paramIBlockData.<EnumStoneSlabVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 120 */     return ((EnumStoneSlabVariant)paramIBlockData.<EnumStoneSlabVariant>get(VARIANT)).c();
/*     */   }
/*     */   
/*     */   public enum EnumStoneSlabVariant implements INamable {
/* 124 */     STONE(0, MaterialMapColor.n, "stone"),
/* 125 */     SAND(1, MaterialMapColor.e, "sandstone", (MaterialMapColor)"sand"),
/* 126 */     WOOD(2, MaterialMapColor.p, "wood_old", (MaterialMapColor)"wood"),
/* 127 */     COBBLESTONE(3, MaterialMapColor.n, "cobblestone", (MaterialMapColor)"cobble"),
/* 128 */     BRICK(4, MaterialMapColor.E, "brick"),
/* 129 */     SMOOTHBRICK(5, MaterialMapColor.n, "stone_brick", (MaterialMapColor)"smoothStoneBrick"),
/* 130 */     NETHERBRICK(6, MaterialMapColor.L, "nether_brick", (MaterialMapColor)"netherBrick"),
/* 131 */     QUARTZ(7, MaterialMapColor.q, "quartz");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     private static final EnumStoneSlabVariant[] i = new EnumStoneSlabVariant[(values()).length];
/*     */     static {
/* 143 */       for (EnumStoneSlabVariant enumStoneSlabVariant : values())
/* 144 */         i[enumStoneSlabVariant.a()] = enumStoneSlabVariant; 
/*     */     }
/*     */     
/*     */     private final int j;
/*     */     private final MaterialMapColor k;
/*     */     private final String l;
/*     */     private final String m;
/*     */     
/*     */     EnumStoneSlabVariant(int param1Int1, MaterialMapColor param1MaterialMapColor, String param1String1, String param1String2) {
/* 153 */       this.j = param1Int1;
/* 154 */       this.k = param1MaterialMapColor;
/* 155 */       this.l = param1String1;
/* 156 */       this.m = param1String2;
/*     */     }
/*     */     
/*     */     public int a() {
/* 160 */       return this.j;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MaterialMapColor c() {
/* 168 */       return this.k;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 173 */       return this.l;
/*     */     }
/*     */     
/*     */     public static EnumStoneSlabVariant a(int param1Int) {
/* 177 */       if (param1Int < 0 || param1Int >= i.length) {
/* 178 */         param1Int = 0;
/*     */       }
/* 180 */       return i[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 185 */       return this.l;
/*     */     }
/*     */     
/*     */     public String d() {
/* 189 */       return this.m;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDoubleStepAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */