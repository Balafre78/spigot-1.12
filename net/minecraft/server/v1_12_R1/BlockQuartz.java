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
/*     */ 
/*     */ 
/*     */ public class BlockQuartz
/*     */   extends Block
/*     */ {
/*  20 */   public static final BlockStateEnum<EnumQuartzVariant> VARIANT = BlockStateEnum.of("variant", EnumQuartzVariant.class);
/*     */   
/*     */   public BlockQuartz() {
/*  23 */     super(Material.STONE);
/*  24 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumQuartzVariant.DEFAULT));
/*  25 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  30 */     if (paramInt == EnumQuartzVariant.LINES_Y.a()) {
/*  31 */       switch (null.a[paramEnumDirection.k().ordinal()]) {
/*     */         case 1:
/*  33 */           return getBlockData().set(VARIANT, EnumQuartzVariant.LINES_Z);
/*     */         case 2:
/*  35 */           return getBlockData().set(VARIANT, EnumQuartzVariant.LINES_X);
/*     */         case 3:
/*  37 */           return getBlockData().set(VARIANT, EnumQuartzVariant.LINES_Y);
/*     */       } 
/*     */     
/*     */     }
/*  41 */     if (paramInt == EnumQuartzVariant.CHISELED.a()) {
/*  42 */       return getBlockData().set(VARIANT, EnumQuartzVariant.CHISELED);
/*     */     }
/*     */     
/*  45 */     return getBlockData().set(VARIANT, EnumQuartzVariant.DEFAULT);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  50 */     EnumQuartzVariant enumQuartzVariant = paramIBlockData.<EnumQuartzVariant>get(VARIANT);
/*  51 */     if (enumQuartzVariant == EnumQuartzVariant.LINES_X || enumQuartzVariant == EnumQuartzVariant.LINES_Z) {
/*  52 */       return EnumQuartzVariant.LINES_Y.a();
/*     */     }
/*     */     
/*  55 */     return enumQuartzVariant.a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack u(IBlockData paramIBlockData) {
/*  60 */     EnumQuartzVariant enumQuartzVariant = paramIBlockData.<EnumQuartzVariant>get(VARIANT);
/*  61 */     if (enumQuartzVariant == EnumQuartzVariant.LINES_X || enumQuartzVariant == EnumQuartzVariant.LINES_Z) {
/*  62 */       return new ItemStack(Item.getItemOf(this), 1, EnumQuartzVariant.LINES_Y.a());
/*     */     }
/*  64 */     return super.u(paramIBlockData);
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
/*  76 */     return MaterialMapColor.q;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  81 */     return getBlockData()
/*  82 */       .set(VARIANT, EnumQuartzVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  87 */     return ((EnumQuartzVariant)paramIBlockData.<EnumQuartzVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  92 */     switch (null.c[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/*     */       case 2:
/*  95 */         switch (null.b[((EnumQuartzVariant)paramIBlockData.get((IBlockState)VARIANT)).ordinal()]) {
/*     */           case 1:
/*  97 */             return paramIBlockData.set(VARIANT, EnumQuartzVariant.LINES_Z);
/*     */           case 2:
/*  99 */             return paramIBlockData.set(VARIANT, EnumQuartzVariant.LINES_X);
/*     */         } 
/* 101 */         return paramIBlockData;
/*     */     } 
/*     */     
/* 104 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 110 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumQuartzVariant implements INamable {
/* 114 */     DEFAULT(0, "default", "default"),
/* 115 */     CHISELED(1, "chiseled", "chiseled"),
/* 116 */     LINES_Y(2, "lines_y", "lines"),
/* 117 */     LINES_X(3, "lines_x", "lines"),
/* 118 */     LINES_Z(4, "lines_z", "lines");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     private static final EnumQuartzVariant[] f = new EnumQuartzVariant[(values()).length]; private final int g;
/*     */     static {
/* 129 */       for (EnumQuartzVariant enumQuartzVariant : values())
/* 130 */         f[enumQuartzVariant.a()] = enumQuartzVariant; 
/*     */     }
/*     */     private final String h; private final String i;
/*     */     
/*     */     EnumQuartzVariant(int param1Int1, String param1String1, String param1String2) {
/* 135 */       this.g = param1Int1;
/* 136 */       this.h = param1String1;
/* 137 */       this.i = param1String2;
/*     */     }
/*     */     
/*     */     public int a() {
/* 141 */       return this.g;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 150 */       return this.i;
/*     */     }
/*     */     
/*     */     public static EnumQuartzVariant a(int param1Int) {
/* 154 */       if (param1Int < 0 || param1Int >= f.length) {
/* 155 */         param1Int = 0;
/*     */       }
/* 157 */       return f[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 162 */       return this.h;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockQuartz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */