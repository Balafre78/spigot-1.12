/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSmoothBrick
/*     */   extends Block
/*     */ {
/*  13 */   public static final BlockStateEnum<EnumStonebrickType> VARIANT = BlockStateEnum.of("variant", EnumStonebrickType.class);
/*     */   
/*  15 */   public static final int b = EnumStonebrickType.DEFAULT.a();
/*  16 */   public static final int c = EnumStonebrickType.MOSSY.a();
/*  17 */   public static final int d = EnumStonebrickType.CRACKED.a();
/*  18 */   public static final int e = EnumStonebrickType.CHISELED.a();
/*     */   
/*     */   public BlockSmoothBrick() {
/*  21 */     super(Material.STONE);
/*  22 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumStonebrickType.DEFAULT));
/*  23 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  28 */     return ((EnumStonebrickType)paramIBlockData.<EnumStonebrickType>get(VARIANT)).a();
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
/*  40 */     return getBlockData()
/*  41 */       .set(VARIANT, EnumStonebrickType.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  46 */     return ((EnumStonebrickType)paramIBlockData.<EnumStonebrickType>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  51 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumStonebrickType implements INamable {
/*  55 */     DEFAULT(0, "stonebrick", "default"),
/*  56 */     MOSSY(1, "mossy_stonebrick", "mossy"),
/*  57 */     CRACKED(2, "cracked_stonebrick", "cracked"),
/*  58 */     CHISELED(3, "chiseled_stonebrick", "chiseled");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     private static final EnumStonebrickType[] e = new EnumStonebrickType[(values()).length]; private final int f;
/*     */     static {
/*  69 */       for (EnumStonebrickType enumStonebrickType : values())
/*  70 */         e[enumStonebrickType.a()] = enumStonebrickType; 
/*     */     }
/*     */     private final String g; private final String h;
/*     */     
/*     */     EnumStonebrickType(int param1Int1, String param1String1, String param1String2) {
/*  75 */       this.f = param1Int1;
/*  76 */       this.g = param1String1;
/*  77 */       this.h = param1String2;
/*     */     }
/*     */     
/*     */     public int a() {
/*  81 */       return this.f;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  90 */       return this.g;
/*     */     }
/*     */     
/*     */     public static EnumStonebrickType a(int param1Int) {
/*  94 */       if (param1Int < 0 || param1Int >= e.length) {
/*  95 */         param1Int = 0;
/*     */       }
/*  97 */       return e[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 102 */       return this.g;
/*     */     }
/*     */     
/*     */     public String c() {
/* 106 */       return this.h;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSmoothBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */