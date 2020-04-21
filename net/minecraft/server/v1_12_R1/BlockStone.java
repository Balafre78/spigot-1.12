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
/*     */ public class BlockStone
/*     */   extends Block
/*     */ {
/*  20 */   public static final BlockStateEnum<EnumStoneVariant> VARIANT = BlockStateEnum.of("variant", EnumStoneVariant.class);
/*     */   
/*     */   public BlockStone() {
/*  23 */     super(Material.STONE);
/*  24 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumStoneVariant.STONE));
/*  25 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  30 */     return LocaleI18n.get(a() + "." + EnumStoneVariant.STONE.d() + ".name");
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  35 */     return ((EnumStoneVariant)paramIBlockData.<EnumStoneVariant>get(VARIANT)).c();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  40 */     if (paramIBlockData.get(VARIANT) == EnumStoneVariant.STONE) {
/*  41 */       return Item.getItemOf(Blocks.COBBLESTONE);
/*     */     }
/*  43 */     return Item.getItemOf(Blocks.STONE);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  48 */     return ((EnumStoneVariant)paramIBlockData.<EnumStoneVariant>get(VARIANT)).a();
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
/*  60 */     return getBlockData()
/*  61 */       .set(VARIANT, EnumStoneVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  66 */     return ((EnumStoneVariant)paramIBlockData.<EnumStoneVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  71 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumStoneVariant implements INamable {
/*  75 */     STONE(0, MaterialMapColor.n, "stone", true),
/*  76 */     GRANITE(1, MaterialMapColor.m, "granite", true),
/*  77 */     GRANITE_SMOOTH(2, MaterialMapColor.m, "smooth_granite", (MaterialMapColor)"graniteSmooth", false),
/*  78 */     DIORITE(3, MaterialMapColor.q, "diorite", true),
/*  79 */     DIORITE_SMOOTH(4, MaterialMapColor.q, "smooth_diorite", (MaterialMapColor)"dioriteSmooth", false),
/*  80 */     ANDESITE(5, MaterialMapColor.n, "andesite", true),
/*  81 */     ANDESITE_SMOOTH(6, MaterialMapColor.n, "smooth_andesite", (MaterialMapColor)"andesiteSmooth", false);
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
/*  92 */     private static final EnumStoneVariant[] h = new EnumStoneVariant[(values()).length];
/*     */     static {
/*  94 */       for (EnumStoneVariant enumStoneVariant : values())
/*  95 */         h[enumStoneVariant.a()] = enumStoneVariant; 
/*     */     }
/*     */     private final int i;
/*     */     private final String j;
/*     */     private final String k;
/*     */     private final MaterialMapColor l;
/*     */     private final boolean m;
/*     */     
/*     */     EnumStoneVariant(int param1Int1, MaterialMapColor param1MaterialMapColor, String param1String1, String param1String2, boolean param1Boolean) {
/* 104 */       this.i = param1Int1;
/* 105 */       this.j = param1String1;
/* 106 */       this.k = param1String2;
/* 107 */       this.l = param1MaterialMapColor;
/* 108 */       this.m = param1Boolean;
/*     */     }
/*     */     
/*     */     public int a() {
/* 112 */       return this.i;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MaterialMapColor c() {
/* 120 */       return this.l;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 125 */       return this.j;
/*     */     }
/*     */     
/*     */     public static EnumStoneVariant a(int param1Int) {
/* 129 */       if (param1Int < 0 || param1Int >= h.length) {
/* 130 */         param1Int = 0;
/*     */       }
/* 132 */       return h[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 137 */       return this.j;
/*     */     }
/*     */     
/*     */     public String d() {
/* 141 */       return this.k;
/*     */     }
/*     */     
/*     */     public boolean e() {
/* 145 */       return this.m;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */