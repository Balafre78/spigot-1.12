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
/*     */ public class BlockSandStone
/*     */   extends Block
/*     */ {
/*  16 */   public static final BlockStateEnum<EnumSandstoneVariant> TYPE = BlockStateEnum.of("type", EnumSandstoneVariant.class);
/*     */   
/*     */   public BlockSandStone() {
/*  19 */     super(Material.STONE);
/*  20 */     w(this.blockStateList.getBlockData().set(TYPE, EnumSandstoneVariant.DEFAULT));
/*  21 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  26 */     return ((EnumSandstoneVariant)paramIBlockData.<EnumSandstoneVariant>get(TYPE)).a();
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
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  38 */     return MaterialMapColor.e;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  43 */     return getBlockData()
/*  44 */       .set(TYPE, EnumSandstoneVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  49 */     return ((EnumSandstoneVariant)paramIBlockData.<EnumSandstoneVariant>get(TYPE)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  54 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { TYPE });
/*     */   }
/*     */   
/*     */   public enum EnumSandstoneVariant implements INamable {
/*  58 */     DEFAULT(0, "sandstone", "default"),
/*  59 */     CHISELED(1, "chiseled_sandstone", "chiseled"),
/*  60 */     SMOOTH(2, "smooth_sandstone", "smooth");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     private static final EnumSandstoneVariant[] d = new EnumSandstoneVariant[(values()).length]; private final int e;
/*     */     static {
/*  71 */       for (EnumSandstoneVariant enumSandstoneVariant : values())
/*  72 */         d[enumSandstoneVariant.a()] = enumSandstoneVariant; 
/*     */     }
/*     */     private final String f; private final String g;
/*     */     
/*     */     EnumSandstoneVariant(int param1Int1, String param1String1, String param1String2) {
/*  77 */       this.e = param1Int1;
/*  78 */       this.f = param1String1;
/*  79 */       this.g = param1String2;
/*     */     }
/*     */     
/*     */     public int a() {
/*  83 */       return this.e;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  92 */       return this.f;
/*     */     }
/*     */     
/*     */     public static EnumSandstoneVariant a(int param1Int) {
/*  96 */       if (param1Int < 0 || param1Int >= d.length) {
/*  97 */         param1Int = 0;
/*     */       }
/*  99 */       return d[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 104 */       return this.f;
/*     */     }
/*     */     
/*     */     public String c() {
/* 108 */       return this.g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSandStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */