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
/*     */ public class BlockWood
/*     */   extends Block
/*     */ {
/*  16 */   public static final BlockStateEnum<EnumLogVariant> VARIANT = BlockStateEnum.of("variant", EnumLogVariant.class);
/*     */   
/*     */   public BlockWood() {
/*  19 */     super(Material.WOOD);
/*  20 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumLogVariant.OAK));
/*  21 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  26 */     return ((EnumLogVariant)paramIBlockData.<EnumLogVariant>get(VARIANT)).a();
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
/*  38 */     return getBlockData()
/*  39 */       .set(VARIANT, EnumLogVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  44 */     return ((EnumLogVariant)paramIBlockData.<EnumLogVariant>get(VARIANT)).c();
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  49 */     return ((EnumLogVariant)paramIBlockData.<EnumLogVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  54 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumLogVariant implements INamable {
/*  58 */     OAK(0, "oak", MaterialMapColor.p),
/*  59 */     SPRUCE(1, "spruce", MaterialMapColor.K),
/*  60 */     BIRCH(2, "birch", MaterialMapColor.e),
/*  61 */     JUNGLE(3, "jungle", MaterialMapColor.m),
/*  62 */     ACACIA(4, "acacia", MaterialMapColor.r),
/*  63 */     DARK_OAK(5, "dark_oak", "big_oak", (String)MaterialMapColor.C);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     private static final EnumLogVariant[] g = new EnumLogVariant[(values()).length];
/*     */     static {
/*  75 */       for (EnumLogVariant enumLogVariant : values())
/*  76 */         g[enumLogVariant.a()] = enumLogVariant; 
/*     */     }
/*     */     
/*     */     private final int h;
/*     */     private final String i;
/*     */     private final String j;
/*     */     private final MaterialMapColor k;
/*     */     
/*     */     EnumLogVariant(int param1Int1, String param1String1, String param1String2, MaterialMapColor param1MaterialMapColor) {
/*  85 */       this.h = param1Int1;
/*  86 */       this.i = param1String1;
/*  87 */       this.j = param1String2;
/*  88 */       this.k = param1MaterialMapColor;
/*     */     }
/*     */     
/*     */     public int a() {
/*  92 */       return this.h;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MaterialMapColor c() {
/* 100 */       return this.k;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 105 */       return this.i;
/*     */     }
/*     */     
/*     */     public static EnumLogVariant a(int param1Int) {
/* 109 */       if (param1Int < 0 || param1Int >= g.length) {
/* 110 */         param1Int = 0;
/*     */       }
/* 112 */       return g[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 117 */       return this.i;
/*     */     }
/*     */     
/*     */     public String d() {
/* 121 */       return this.j;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */