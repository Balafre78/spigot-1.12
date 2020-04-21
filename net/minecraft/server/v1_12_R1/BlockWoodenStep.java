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
/*     */ public abstract class BlockWoodenStep
/*     */   extends BlockStepAbstract
/*     */ {
/*  20 */   public static final BlockStateEnum<BlockWood.EnumLogVariant> VARIANT = BlockStateEnum.of("variant", BlockWood.EnumLogVariant.class);
/*     */   
/*     */   public BlockWoodenStep() {
/*  23 */     super(Material.WOOD);
/*  24 */     IBlockData iBlockData = this.blockStateList.getBlockData();
/*  25 */     if (!e()) {
/*  26 */       iBlockData = iBlockData.set(HALF, BlockStepAbstract.EnumSlabHalf.BOTTOM);
/*     */     }
/*  28 */     w(iBlockData.set(VARIANT, BlockWood.EnumLogVariant.OAK));
/*  29 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  34 */     return ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).c();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  39 */     return Item.getItemOf(Blocks.WOODEN_SLAB);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  44 */     return new ItemStack(Blocks.WOODEN_SLAB, 1, ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a());
/*     */   }
/*     */ 
/*     */   
/*     */   public String b(int paramInt) {
/*  49 */     return a() + "." + BlockWood.EnumLogVariant.a(paramInt).d();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState<?> g() {
/*  54 */     return VARIANT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Comparable<?> a(ItemStack paramItemStack) {
/*  59 */     return BlockWood.EnumLogVariant.a(paramItemStack.getData() & 0x7);
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
/*  71 */     IBlockData iBlockData = getBlockData().set(VARIANT, BlockWood.EnumLogVariant.a(paramInt & 0x7));
/*     */     
/*  73 */     if (!e()) {
/*  74 */       iBlockData = iBlockData.set(HALF, ((paramInt & 0x8) == 0) ? BlockStepAbstract.EnumSlabHalf.BOTTOM : BlockStepAbstract.EnumSlabHalf.TOP);
/*     */     }
/*     */     
/*  77 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  82 */     int i = 0;
/*     */     
/*  84 */     i |= ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a();
/*     */     
/*  86 */     if (!e() && paramIBlockData.get(HALF) == BlockStepAbstract.EnumSlabHalf.TOP) {
/*  87 */       i |= 0x8;
/*     */     }
/*     */     
/*  90 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  95 */     if (e()) {
/*  96 */       return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT });
/*     */     }
/*     */     
/*  99 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HALF, VARIANT });
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/* 104 */     return ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockWoodenStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */