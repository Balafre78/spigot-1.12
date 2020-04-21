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
/*     */ public abstract class BlockLogAbstract
/*     */   extends BlockRotatable
/*     */ {
/*  16 */   public static final BlockStateEnum<EnumLogRotation> AXIS = BlockStateEnum.of("axis", EnumLogRotation.class);
/*     */   
/*     */   public BlockLogAbstract() {
/*  19 */     super(Material.WOOD);
/*  20 */     a(CreativeModeTab.b);
/*  21 */     c(2.0F);
/*  22 */     a(SoundEffectType.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  27 */     byte b1 = 4;
/*  28 */     byte b2 = 5;
/*     */     
/*  30 */     if (!paramWorld.areChunksLoadedBetween(paramBlockPosition.a(-5, -5, -5), paramBlockPosition.a(5, 5, 5))) {
/*     */       return;
/*     */     }
/*     */     
/*  34 */     for (BlockPosition blockPosition : BlockPosition.a(paramBlockPosition.a(-4, -4, -4), paramBlockPosition.a(4, 4, 4))) {
/*  35 */       IBlockData iBlockData = paramWorld.getType(blockPosition);
/*  36 */       if (iBlockData.getMaterial() == Material.LEAVES && 
/*  37 */         !((Boolean)iBlockData.<Boolean>get(BlockLeaves.CHECK_DECAY)).booleanValue()) {
/*  38 */         paramWorld.setTypeAndData(blockPosition, iBlockData.set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(true)), 4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  49 */     return fromLegacyData(paramInt).set(AXIS, EnumLogRotation.a(paramEnumDirection.k()));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  54 */     switch (null.b[paramEnumBlockRotation.ordinal()]) {
/*     */       case 1:
/*     */       case 2:
/*  57 */         switch (null.a[((EnumLogRotation)paramIBlockData.get((IBlockState)AXIS)).ordinal()]) {
/*     */           case 1:
/*  59 */             return paramIBlockData.set(AXIS, EnumLogRotation.Z);
/*     */           case 2:
/*  61 */             return paramIBlockData.set(AXIS, EnumLogRotation.X);
/*     */         } 
/*  63 */         return paramIBlockData;
/*     */     } 
/*     */     
/*  66 */     return paramIBlockData;
/*     */   }
/*     */   
/*     */   public enum EnumLogRotation
/*     */     implements INamable {
/*  71 */     X("x"),
/*  72 */     Y("y"),
/*  73 */     Z("z"),
/*  74 */     NONE("none");
/*     */     
/*     */     private final String e;
/*     */ 
/*     */     
/*     */     EnumLogRotation(String param1String1) {
/*  80 */       this.e = param1String1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  85 */       return this.e;
/*     */     }
/*     */     
/*     */     public static EnumLogRotation a(EnumDirection.EnumAxis param1EnumAxis) {
/*  89 */       switch (BlockLogAbstract.null.c[param1EnumAxis.ordinal()]) {
/*     */         case 1:
/*  91 */           return X;
/*     */         case 2:
/*  93 */           return Y;
/*     */         case 3:
/*  95 */           return Z;
/*     */       } 
/*     */       
/*  98 */       return NONE;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 103 */       return this.e;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLogAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */