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
/*     */ public class BlockDirt
/*     */   extends Block
/*     */ {
/*  18 */   public static final BlockStateEnum<EnumDirtVariant> VARIANT = BlockStateEnum.of("variant", EnumDirtVariant.class);
/*  19 */   public static final BlockStateBoolean SNOWY = BlockStateBoolean.of("snowy");
/*     */   
/*     */   protected BlockDirt() {
/*  22 */     super(Material.EARTH);
/*  23 */     w(this.blockStateList.getBlockData().<EnumDirtVariant, EnumDirtVariant>set(VARIANT, EnumDirtVariant.DIRT).set(SNOWY, Boolean.valueOf(false)));
/*  24 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  29 */     return ((EnumDirtVariant)paramIBlockData.<EnumDirtVariant>get(VARIANT)).d();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  34 */     if (paramIBlockData.get(VARIANT) == EnumDirtVariant.PODZOL) {
/*  35 */       Block block = paramIBlockAccess.getType(paramBlockPosition.up()).getBlock();
/*  36 */       paramIBlockData = paramIBlockData.set(SNOWY, Boolean.valueOf((block == Blocks.SNOW || block == Blocks.SNOW_LAYER)));
/*     */     } 
/*  38 */     return paramIBlockData;
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
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  50 */     return new ItemStack(this, 1, ((EnumDirtVariant)paramIBlockData.<EnumDirtVariant>get(VARIANT)).a());
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  55 */     return getBlockData()
/*  56 */       .set(VARIANT, EnumDirtVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  61 */     return ((EnumDirtVariant)paramIBlockData.<EnumDirtVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  66 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT, SNOWY });
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  71 */     EnumDirtVariant enumDirtVariant = paramIBlockData.<EnumDirtVariant>get(VARIANT);
/*  72 */     if (enumDirtVariant == EnumDirtVariant.PODZOL) {
/*  73 */       enumDirtVariant = EnumDirtVariant.DIRT;
/*     */     }
/*  75 */     return enumDirtVariant.a();
/*     */   }
/*     */   
/*     */   public enum EnumDirtVariant implements INamable {
/*  79 */     DIRT(0, "dirt", "default", (String)MaterialMapColor.m),
/*  80 */     COARSE_DIRT(1, "coarse_dirt", "coarse", (String)MaterialMapColor.m),
/*  81 */     PODZOL(2, "podzol", MaterialMapColor.K);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     private static final EnumDirtVariant[] d = new EnumDirtVariant[(values()).length];
/*     */     static {
/*  93 */       for (EnumDirtVariant enumDirtVariant : values())
/*  94 */         d[enumDirtVariant.a()] = enumDirtVariant; 
/*     */     }
/*     */     
/*     */     private final int e;
/*     */     private final String f;
/*     */     private final String g;
/*     */     private final MaterialMapColor h;
/*     */     
/*     */     EnumDirtVariant(int param1Int1, String param1String1, String param1String2, MaterialMapColor param1MaterialMapColor) {
/* 103 */       this.e = param1Int1;
/* 104 */       this.f = param1String1;
/* 105 */       this.g = param1String2;
/* 106 */       this.h = param1MaterialMapColor;
/*     */     }
/*     */     
/*     */     public int a() {
/* 110 */       return this.e;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String c() {
/* 118 */       return this.g;
/*     */     }
/*     */     
/*     */     public MaterialMapColor d() {
/* 122 */       return this.h;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 127 */       return this.f;
/*     */     }
/*     */     
/*     */     public static EnumDirtVariant a(int param1Int) {
/* 131 */       if (param1Int < 0 || param1Int >= d.length) {
/* 132 */         param1Int = 0;
/*     */       }
/* 134 */       return d[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 139 */       return this.f;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDirt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */