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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockRepeater
/*     */   extends BlockDiodeAbstract
/*     */ {
/*  23 */   public static final BlockStateBoolean LOCKED = BlockStateBoolean.of("locked");
/*  24 */   public static final BlockStateInteger DELAY = BlockStateInteger.of("delay", 1, 4);
/*     */   
/*     */   protected BlockRepeater(boolean paramBoolean) {
/*  27 */     super(paramBoolean);
/*  28 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).<Comparable, Integer>set(DELAY, Integer.valueOf(1)).set(LOCKED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  33 */     return LocaleI18n.get("item.diode.name");
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  38 */     return paramIBlockData.set(LOCKED, Boolean.valueOf(b(paramIBlockAccess, paramBlockPosition, paramIBlockData)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/*  43 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockMirror paramEnumBlockMirror) {
/*  48 */     return paramIBlockData.a(paramEnumBlockMirror.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  53 */     if (!paramEntityHuman.abilities.mayBuild) {
/*  54 */       return false;
/*     */     }
/*     */     
/*  57 */     paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.a(DELAY), 3);
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int x(IBlockData paramIBlockData) {
/*  63 */     return ((Integer)paramIBlockData.<Integer>get(DELAY)).intValue() * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockData y(IBlockData paramIBlockData) {
/*  68 */     Integer integer = paramIBlockData.<Integer>get(DELAY);
/*  69 */     Boolean bool = paramIBlockData.<Boolean>get(LOCKED);
/*  70 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*  71 */     return Blocks.POWERED_REPEATER.getBlockData().<Comparable, EnumDirection>set(FACING, enumDirection).<Comparable, Integer>set(DELAY, integer).set(LOCKED, bool);
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockData z(IBlockData paramIBlockData) {
/*  76 */     Integer integer = paramIBlockData.<Integer>get(DELAY);
/*  77 */     Boolean bool = paramIBlockData.<Boolean>get(LOCKED);
/*  78 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*  79 */     return Blocks.UNPOWERED_REPEATER.getBlockData().<Comparable, EnumDirection>set(FACING, enumDirection).<Comparable, Integer>set(DELAY, integer).set(LOCKED, bool);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  84 */     return Items.REPEATER;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  89 */     return new ItemStack(Items.REPEATER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  94 */     return (c(paramIBlockAccess, paramBlockPosition, paramIBlockData) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean B(IBlockData paramIBlockData) {
/*  99 */     return isDiode(paramIBlockData);
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
/*     */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 127 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/* 128 */     h(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 133 */     return getBlockData()
/* 134 */       .<Comparable, EnumDirection>set(FACING, EnumDirection.fromType2(paramInt))
/* 135 */       .<Comparable, Boolean>set(LOCKED, Boolean.valueOf(false))
/* 136 */       .set(DELAY, Integer.valueOf(1 + (paramInt >> 2)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 141 */     int i = 0;
/*     */     
/* 143 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).get2DRotationValue();
/* 144 */     i |= ((Integer)paramIBlockData.<Integer>get(DELAY)).intValue() - 1 << 2;
/*     */     
/* 146 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 151 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, DELAY, LOCKED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRepeater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */