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
/*     */ public abstract class BlockPurpurSlab
/*     */   extends BlockStepAbstract
/*     */ {
/*  19 */   public static final BlockStateEnum<Type> d = BlockStateEnum.of("variant", Type.class);
/*     */   
/*     */   public BlockPurpurSlab() {
/*  22 */     super(Material.STONE, MaterialMapColor.s);
/*  23 */     IBlockData iBlockData = this.blockStateList.getBlockData();
/*  24 */     if (!e()) {
/*  25 */       iBlockData = iBlockData.set(HALF, BlockStepAbstract.EnumSlabHalf.BOTTOM);
/*     */     }
/*  27 */     w(iBlockData.set(d, Type.DEFAULT));
/*  28 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  33 */     return Item.getItemOf(Blocks.PURPUR_SLAB);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  38 */     return new ItemStack(Blocks.PURPUR_SLAB);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  43 */     IBlockData iBlockData = getBlockData().set(d, Type.DEFAULT);
/*     */     
/*  45 */     if (!e()) {
/*  46 */       iBlockData = iBlockData.set(HALF, ((paramInt & 0x8) == 0) ? BlockStepAbstract.EnumSlabHalf.BOTTOM : BlockStepAbstract.EnumSlabHalf.TOP);
/*     */     }
/*     */     
/*  49 */     return iBlockData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  54 */     int i = 0;
/*     */     
/*  56 */     if (!e() && 
/*  57 */       paramIBlockData.get(HALF) == BlockStepAbstract.EnumSlabHalf.TOP) {
/*  58 */       i |= 0x8;
/*     */     }
/*     */ 
/*     */     
/*  62 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  67 */     if (e()) {
/*  68 */       return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { d });
/*     */     }
/*     */     
/*  71 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HALF, d });
/*     */   }
/*     */ 
/*     */   
/*     */   public String b(int paramInt) {
/*  76 */     return a();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState<?> g() {
/*  81 */     return d;
/*     */   }
/*     */ 
/*     */   
/*     */   public Comparable<?> a(ItemStack paramItemStack) {
/*  86 */     return Type.DEFAULT;
/*     */   }
/*     */   
/*     */   public static class Half
/*     */     extends BlockPurpurSlab {
/*     */     public boolean e() {
/*  92 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Default
/*     */     extends BlockPurpurSlab {
/*     */     public boolean e() {
/*  99 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Type
/*     */     implements INamable
/*     */   {
/* 106 */     DEFAULT;
/*     */ 
/*     */     
/*     */     public String getName() {
/* 110 */       return "default";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPurpurSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */