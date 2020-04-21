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
/*     */ public class BlockHugeMushroom
/*     */   extends Block
/*     */ {
/*  20 */   public static final BlockStateEnum<EnumHugeMushroomVariant> VARIANT = BlockStateEnum.of("variant", EnumHugeMushroomVariant.class);
/*     */   
/*     */   private final Block b;
/*     */   
/*     */   public BlockHugeMushroom(Material paramMaterial, MaterialMapColor paramMaterialMapColor, Block paramBlock) {
/*  25 */     super(paramMaterial, paramMaterialMapColor);
/*  26 */     w(this.blockStateList.getBlockData().set(VARIANT, EnumHugeMushroomVariant.ALL_OUTSIDE));
/*  27 */     this.b = paramBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random paramRandom) {
/*  32 */     return Math.max(0, paramRandom.nextInt(10) - 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  37 */     switch (null.a[((EnumHugeMushroomVariant)paramIBlockData.get((IBlockState)VARIANT)).ordinal()]) {
/*     */       case 1:
/*  39 */         return MaterialMapColor.f;
/*     */       case 2:
/*  41 */         return MaterialMapColor.e;
/*     */       case 3:
/*  43 */         return MaterialMapColor.e;
/*     */     } 
/*  45 */     return super.c(paramIBlockData, paramIBlockAccess, paramBlockPosition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  51 */     return Item.getItemOf(this.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  56 */     return new ItemStack(this.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  61 */     return getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  66 */     return getBlockData()
/*  67 */       .set(VARIANT, EnumHugeMushroomVariant.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  72 */     return ((EnumHugeMushroomVariant)paramIBlockData.<EnumHugeMushroomVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  77 */     switch (null.b[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/*  79 */         switch (null.a[((EnumHugeMushroomVariant)paramIBlockData.get((IBlockState)VARIANT)).ordinal()]) {
/*     */           case 4:
/*  81 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_EAST);
/*     */           case 5:
/*  83 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH);
/*     */           case 6:
/*  85 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_WEST);
/*     */           case 7:
/*  87 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.EAST);
/*     */           case 8:
/*  89 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.WEST);
/*     */           case 9:
/*  91 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_EAST);
/*     */           case 10:
/*  93 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH);
/*     */           case 11:
/*  95 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_WEST);
/*     */           case 3:
/*     */             break;
/*     */           default:
/*  99 */             return paramIBlockData;
/*     */         } 
/*     */       case 2:
/* 102 */         switch (null.a[((EnumHugeMushroomVariant)paramIBlockData.get((IBlockState)VARIANT)).ordinal()]) {
/*     */           case 4:
/* 104 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_WEST);
/*     */           case 5:
/* 106 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.WEST);
/*     */           case 6:
/* 108 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_WEST);
/*     */           case 7:
/* 110 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH);
/*     */           case 8:
/* 112 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH);
/*     */           case 9:
/* 114 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_EAST);
/*     */           case 10:
/* 116 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.EAST);
/*     */           case 11:
/* 118 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_EAST);
/*     */           case 3:
/*     */             break;
/*     */           default:
/* 122 */             return paramIBlockData;
/*     */         } 
/*     */       case 3:
/* 125 */         switch (null.a[((EnumHugeMushroomVariant)paramIBlockData.get((IBlockState)VARIANT)).ordinal()]) {
/*     */           case 4:
/* 127 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_EAST);
/*     */           case 5:
/* 129 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.EAST);
/*     */           case 6:
/* 131 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_EAST);
/*     */           case 7:
/* 133 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH);
/*     */           case 8:
/* 135 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH);
/*     */           case 9:
/* 137 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_WEST);
/*     */           case 10:
/* 139 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.WEST);
/*     */           case 11:
/* 141 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_WEST);
/*     */           case 3:
/*     */             break;
/*     */         } 
/* 145 */         return paramIBlockData;
/*     */     } 
/*     */     
/* 148 */     return paramIBlockData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/* 154 */     EnumHugeMushroomVariant enumHugeMushroomVariant = paramIBlockData.<EnumHugeMushroomVariant>get(VARIANT);
/* 155 */     switch (null.c[paramEnumBlockMirror.ordinal()]) {
/*     */       case 1:
/* 157 */         switch (null.a[enumHugeMushroomVariant.ordinal()]) {
/*     */           case 4:
/* 159 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_WEST);
/*     */           case 5:
/* 161 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH);
/*     */           case 6:
/* 163 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_EAST);
/*     */           case 9:
/* 165 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_WEST);
/*     */           case 10:
/* 167 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH);
/*     */           case 11:
/* 169 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_EAST);
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 173 */         switch (null.a[enumHugeMushroomVariant.ordinal()]) {
/*     */           case 4:
/* 175 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_EAST);
/*     */           case 6:
/* 177 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.NORTH_WEST);
/*     */           case 7:
/* 179 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.EAST);
/*     */           case 8:
/* 181 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.WEST);
/*     */           case 9:
/* 183 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_EAST);
/*     */           case 11:
/* 185 */             return paramIBlockData.set(VARIANT, EnumHugeMushroomVariant.SOUTH_WEST);
/*     */         } 
/*     */         
/*     */         break;
/*     */     } 
/*     */     
/* 191 */     return super.a(paramIBlockData, paramEnumBlockMirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 196 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumHugeMushroomVariant implements INamable {
/* 200 */     NORTH_WEST(1, "north_west"),
/* 201 */     NORTH(2, "north"),
/* 202 */     NORTH_EAST(3, "north_east"),
/* 203 */     WEST(4, "west"),
/* 204 */     CENTER(5, "center"),
/* 205 */     EAST(6, "east"),
/* 206 */     SOUTH_WEST(7, "south_west"),
/* 207 */     SOUTH(8, "south"),
/* 208 */     SOUTH_EAST(9, "south_east"),
/* 209 */     STEM(10, "stem"),
/* 210 */     ALL_INSIDE(0, "all_inside"),
/* 211 */     ALL_OUTSIDE(14, "all_outside"),
/* 212 */     ALL_STEM(15, "all_stem");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     private static final EnumHugeMushroomVariant[] n = new EnumHugeMushroomVariant[16]; private final int o;
/*     */     static {
/* 221 */       for (EnumHugeMushroomVariant enumHugeMushroomVariant : values())
/* 222 */         n[enumHugeMushroomVariant.a()] = enumHugeMushroomVariant; 
/*     */     }
/*     */     private final String p;
/*     */     
/*     */     EnumHugeMushroomVariant(int param1Int1, String param1String1) {
/* 227 */       this.o = param1Int1;
/* 228 */       this.p = param1String1;
/*     */     }
/*     */     
/*     */     public int a() {
/* 232 */       return this.o;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 241 */       return this.p;
/*     */     }
/*     */     
/*     */     public static EnumHugeMushroomVariant a(int param1Int) {
/* 245 */       if (param1Int < 0 || param1Int >= n.length) {
/* 246 */         param1Int = 0;
/*     */       }
/* 248 */       EnumHugeMushroomVariant enumHugeMushroomVariant = n[param1Int];
/* 249 */       return (enumHugeMushroomVariant == null) ? n[0] : enumHugeMushroomVariant;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 254 */       return this.p;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockHugeMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */