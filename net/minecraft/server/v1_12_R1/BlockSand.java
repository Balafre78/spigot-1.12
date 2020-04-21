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
/*     */ public class BlockSand
/*     */   extends BlockFalling
/*     */ {
/*  15 */   public static final BlockStateEnum<EnumSandVariant> VARIANT = BlockStateEnum.of("variant", EnumSandVariant.class);
/*     */   
/*     */   public BlockSand() {
/*  18 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumSandVariant.SAND));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  23 */     return ((EnumSandVariant)paramIBlockData.<EnumSandVariant>get(VARIANT)).b();
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
/*  35 */     return ((EnumSandVariant)paramIBlockData.<EnumSandVariant>get(VARIANT)).d();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  40 */     return getBlockData()
/*  41 */       .set(VARIANT, EnumSandVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  46 */     return ((EnumSandVariant)paramIBlockData.<EnumSandVariant>get(VARIANT)).b();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  51 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumSandVariant
/*     */     implements INamable
/*     */   {
/*  61 */     SAND(0, "sand", "default", (String)MaterialMapColor.e, -2370656),
/*  62 */     RED_SAND(1, "red_sand", "red", (String)MaterialMapColor.r, -5679071);
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
/*  73 */     private static final EnumSandVariant[] c = new EnumSandVariant[(values()).length]; private final int d; private final String e;
/*     */     static {
/*  75 */       for (EnumSandVariant enumSandVariant : values())
/*  76 */         c[enumSandVariant.b()] = enumSandVariant; 
/*     */     }
/*     */     private final MaterialMapColor f; private final String g; private final int h;
/*     */     
/*     */     EnumSandVariant(int param1Int1, String param1String1, String param1String2, MaterialMapColor param1MaterialMapColor, int param1Int2) {
/*  81 */       this.d = param1Int1;
/*  82 */       this.e = param1String1;
/*  83 */       this.f = param1MaterialMapColor;
/*  84 */       this.g = param1String2;
/*  85 */       this.h = param1Int2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int b() {
/*  93 */       return this.d;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 102 */       return this.e;
/*     */     }
/*     */     
/*     */     public MaterialMapColor d() {
/* 106 */       return this.f;
/*     */     }
/*     */     
/*     */     public static EnumSandVariant a(int param1Int) {
/* 110 */       if (param1Int < 0 || param1Int >= c.length) {
/* 111 */         param1Int = 0;
/*     */       }
/* 113 */       return c[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 118 */       return this.e;
/*     */     }
/*     */     
/*     */     public String e() {
/* 122 */       return this.g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */