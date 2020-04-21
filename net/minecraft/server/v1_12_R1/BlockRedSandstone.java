/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockRedSandstone
/*     */   extends Block
/*     */ {
/*  13 */   public static final BlockStateEnum<EnumRedSandstoneVariant> TYPE = BlockStateEnum.of("type", EnumRedSandstoneVariant.class);
/*     */   
/*     */   public BlockRedSandstone() {
/*  16 */     super(Material.STONE, BlockSand.EnumSandVariant.RED_SAND.d());
/*  17 */     w(this.blockStateList.getBlockData().set(TYPE, EnumRedSandstoneVariant.DEFAULT));
/*  18 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  23 */     return ((EnumRedSandstoneVariant)paramIBlockData.<EnumRedSandstoneVariant>get(TYPE)).a();
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
/*  35 */     return getBlockData()
/*  36 */       .set(TYPE, EnumRedSandstoneVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  41 */     return ((EnumRedSandstoneVariant)paramIBlockData.<EnumRedSandstoneVariant>get(TYPE)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  46 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { TYPE });
/*     */   }
/*     */   
/*     */   public enum EnumRedSandstoneVariant implements INamable {
/*  50 */     DEFAULT(0, "red_sandstone", "default"),
/*  51 */     CHISELED(1, "chiseled_red_sandstone", "chiseled"),
/*  52 */     SMOOTH(2, "smooth_red_sandstone", "smooth");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     private static final EnumRedSandstoneVariant[] d = new EnumRedSandstoneVariant[(values()).length]; private final int e;
/*     */     static {
/*  63 */       for (EnumRedSandstoneVariant enumRedSandstoneVariant : values())
/*  64 */         d[enumRedSandstoneVariant.a()] = enumRedSandstoneVariant; 
/*     */     }
/*     */     private final String f; private final String g;
/*     */     
/*     */     EnumRedSandstoneVariant(int param1Int1, String param1String1, String param1String2) {
/*  69 */       this.e = param1Int1;
/*  70 */       this.f = param1String1;
/*  71 */       this.g = param1String2;
/*     */     }
/*     */     
/*     */     public int a() {
/*  75 */       return this.e;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  84 */       return this.f;
/*     */     }
/*     */     
/*     */     public static EnumRedSandstoneVariant a(int param1Int) {
/*  88 */       if (param1Int < 0 || param1Int >= d.length) {
/*  89 */         param1Int = 0;
/*     */       }
/*  91 */       return d[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/*  96 */       return this.f;
/*     */     }
/*     */     
/*     */     public String c() {
/* 100 */       return this.g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRedSandstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */