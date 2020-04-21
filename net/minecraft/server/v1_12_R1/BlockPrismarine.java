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
/*     */ public class BlockPrismarine
/*     */   extends Block
/*     */ {
/*  17 */   public static final BlockStateEnum<EnumPrismarineVariant> VARIANT = BlockStateEnum.of("variant", EnumPrismarineVariant.class);
/*  18 */   public static final int b = EnumPrismarineVariant.ROUGH.a();
/*  19 */   public static final int c = EnumPrismarineVariant.BRICKS.a();
/*  20 */   public static final int d = EnumPrismarineVariant.DARK.a();
/*     */   
/*     */   public BlockPrismarine() {
/*  23 */     super(Material.STONE);
/*  24 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumPrismarineVariant.ROUGH));
/*  25 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  30 */     return LocaleI18n.get(a() + "." + EnumPrismarineVariant.ROUGH.c() + ".name");
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  35 */     if (paramIBlockData.get(VARIANT) == EnumPrismarineVariant.ROUGH) {
/*  36 */       return MaterialMapColor.z;
/*     */     }
/*  38 */     return MaterialMapColor.H;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  43 */     return ((EnumPrismarineVariant)paramIBlockData.<EnumPrismarineVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  48 */     return ((EnumPrismarineVariant)paramIBlockData.<EnumPrismarineVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  53 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  58 */     return getBlockData().set(VARIANT, EnumPrismarineVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumPrismarineVariant
/*     */     implements INamable
/*     */   {
/*  69 */     ROUGH(0, "prismarine", "rough"),
/*  70 */     BRICKS(1, "prismarine_bricks", "bricks"),
/*  71 */     DARK(2, "dark_prismarine", "dark");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     private static final EnumPrismarineVariant[] d = new EnumPrismarineVariant[(values()).length]; private final int e;
/*     */     static {
/*  81 */       for (EnumPrismarineVariant enumPrismarineVariant : values())
/*  82 */         d[enumPrismarineVariant.a()] = enumPrismarineVariant; 
/*     */     }
/*     */     private final String f; private final String g;
/*     */     
/*     */     EnumPrismarineVariant(int param1Int1, String param1String1, String param1String2) {
/*  87 */       this.e = param1Int1;
/*  88 */       this.f = param1String1;
/*  89 */       this.g = param1String2;
/*     */     }
/*     */     
/*     */     public int a() {
/*  93 */       return this.e;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 102 */       return this.f;
/*     */     }
/*     */     
/*     */     public static EnumPrismarineVariant a(int param1Int) {
/* 106 */       if (param1Int < 0 || param1Int >= d.length) {
/* 107 */         param1Int = 0;
/*     */       }
/* 109 */       return d[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 114 */       return this.f;
/*     */     }
/*     */     
/*     */     public String c() {
/* 118 */       return this.g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPrismarine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */