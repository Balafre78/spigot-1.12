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
/*     */ public class BlockLog1
/*     */   extends BlockLogAbstract
/*     */ {
/*  18 */   public static final BlockStateEnum<BlockWood.EnumLogVariant> VARIANT = BlockStateEnum.a("variant", BlockWood.EnumLogVariant.class, new Predicate<BlockWood.EnumLogVariant>()
/*     */       {
/*     */         public boolean a(@Nullable BlockWood.EnumLogVariant param1EnumLogVariant) {
/*  21 */           return (param1EnumLogVariant.a() < 4);
/*     */         }
/*     */       });
/*     */   
/*     */   public BlockLog1() {
/*  26 */     w(this.blockStateList.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(VARIANT, BlockWood.EnumLogVariant.OAK).set(AXIS, BlockLogAbstract.EnumLogRotation.Y));
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
/*  40 */             return BlockWood.EnumLogVariant.SPRUCE.c();
/*     */           case 2:
/*  42 */             return BlockWood.EnumLogVariant.DARK_OAK.c();
/*     */           case 3:
/*  44 */             return MaterialMapColor.q;
/*     */           case 4:
/*  46 */             break; }  return BlockWood.EnumLogVariant.SPRUCE.c();
/*     */       case 4:
/*     */         break;
/*  49 */     }  return enumLogVariant.c();
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
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  64 */     IBlockData iBlockData = getBlockData().set(VARIANT, BlockWood.EnumLogVariant.a((paramInt & 0x3) % 4));
/*     */     
/*  66 */     switch (paramInt & 0xC)
/*     */     { case 0:
/*  68 */         iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.Y);
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
/*  81 */         return iBlockData;case 4: iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.X); return iBlockData;case 8: iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.Z); return iBlockData; }  iBlockData = iBlockData.set(AXIS, BlockLogAbstract.EnumLogRotation.NONE); return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  86 */     int i = 0;
/*     */     
/*  88 */     i |= ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a();
/*     */     
/*  90 */     switch (null.b[((BlockLogAbstract.EnumLogRotation)paramIBlockData.get((IBlockState)AXIS)).ordinal()]) {
/*     */       case 1:
/*  92 */         i |= 0x4;
/*     */         break;
/*     */       case 2:
/*  95 */         i |= 0x8;
/*     */         break;
/*     */       case 3:
/*  98 */         i |= 0xC;
/*     */         break;
/*     */     } 
/*     */     
/* 102 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 107 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT, AXIS });
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack u(IBlockData paramIBlockData) {
/* 112 */     return new ItemStack(Item.getItemOf(this), 1, ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/* 117 */     return ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLog1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */