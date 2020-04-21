/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class BlockLog2
/*     */   extends BlockLogAbstract
/*     */ {
/*  18 */   public static final BlockStateEnum<BlockWood.EnumLogVariant> VARIANT = BlockStateEnum.a("variant", BlockWood.EnumLogVariant.class, new Predicate<BlockWood.EnumLogVariant>()
/*     */       {
/*     */         public boolean a(@Nullable BlockWood.EnumLogVariant param1EnumLogVariant) {
/*  21 */           return (param1EnumLogVariant.a() >= 4);
/*     */         }
/*     */       });
/*     */   
/*     */   public BlockLog2() {
/*  26 */     w(this.blockStateList.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(VARIANT, BlockWood.EnumLogVariant.ACACIA).set(AXIS, BlockLogAbstract.EnumLogRotation.Y));
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  31 */     BlockWood.EnumLogVariant enumLogVariant = paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT);
/*  32 */     switch (null.b[((BlockLogAbstract.EnumLogRotation)paramIBlockData.get((IBlockState)AXIS)).ordinal()]) {
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/*  37 */         switch (null.a[enumLogVariant.ordinal()])
/*     */         
/*     */         { default:
/*  40 */             return MaterialMapColor.n;
/*     */           case 2:
/*  42 */             break; }  return BlockWood.EnumLogVariant.DARK_OAK.c();
/*     */       case 4:
/*     */         break;
/*  45 */     }  return enumLogVariant.c();
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
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  58 */     IBlockData iBlockData = getBlockData().set(VARIANT, BlockWood.EnumLogVariant.a((paramInt & 0x3) + 4));
/*     */     
/*  60 */     switch (paramInt & 0xC)
/*     */     { case 0:
/*  62 */         iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.Y);
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
/*  75 */         return iBlockData;case 4: iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.X); return iBlockData;case 8: iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.Z); return iBlockData; }  iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.NONE); return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  80 */     int i = 0;
/*     */     
/*  82 */     i |= ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a() - 4;
/*     */     
/*  84 */     switch (null.b[((BlockLogAbstract.EnumLogRotation)paramIBlockData.get((IBlockState)AXIS)).ordinal()]) {
/*     */       case 1:
/*  86 */         i |= 0x4;
/*     */         break;
/*     */       case 2:
/*  89 */         i |= 0x8;
/*     */         break;
/*     */       case 3:
/*  92 */         i |= 0xC;
/*     */         break;
/*     */     } 
/*     */     
/*  96 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 101 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT, AXIS });
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack u(IBlockData paramIBlockData) {
/* 106 */     return new ItemStack(Item.getItemOf(this), 1, ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a() - 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/* 111 */     return ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a() - 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLog2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */