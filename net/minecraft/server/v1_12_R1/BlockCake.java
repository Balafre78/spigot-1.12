/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ 
/*     */ public class BlockCake extends Block {
/*   7 */   public static final BlockStateInteger BITES = BlockStateInteger.of("bites", 0, 6);
/*   8 */   protected static final AxisAlignedBB[] b = new AxisAlignedBB[] { new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.1875D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.3125D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.4375D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.5625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.6875D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.8125D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D) };
/*     */   
/*     */   protected BlockCake() {
/*  11 */     super(Material.CAKE);
/*  12 */     w(this.blockStateList.getBlockData().set(BITES, Integer.valueOf(0)));
/*  13 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  17 */     return b[((Integer)iblockdata.get(BITES)).intValue()];
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  21 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  25 */     return false;
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  29 */     if (!world.isClientSide) {
/*  30 */       return b(world, blockposition, iblockdata, entityhuman);
/*     */     }
/*  32 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  34 */     return !(!b(world, blockposition, iblockdata, entityhuman) && !itemstack.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean b(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/*  39 */     if (!entityhuman.n(false)) {
/*  40 */       return false;
/*     */     }
/*  42 */     entityhuman.b(StatisticList.H);
/*     */ 
/*     */     
/*  45 */     int oldFoodLevel = (entityhuman.getFoodData()).foodLevel;
/*     */     
/*  47 */     FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, 2 + oldFoodLevel);
/*     */     
/*  49 */     if (!event.isCancelled()) {
/*  50 */       entityhuman.getFoodData().eat(event.getFoodLevel() - oldFoodLevel, 0.1F);
/*     */     }
/*     */     
/*  53 */     ((EntityPlayer)entityhuman).getBukkitEntity().sendHealthUpdate();
/*     */     
/*  55 */     int i = ((Integer)iblockdata.<Integer>get(BITES)).intValue();
/*     */     
/*  57 */     if (i < 6) {
/*  58 */       world.setTypeAndData(blockposition, iblockdata.set(BITES, Integer.valueOf(i + 1)), 3);
/*     */     } else {
/*  60 */       world.setAir(blockposition);
/*     */     } 
/*     */     
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  68 */     return super.canPlace(world, blockposition) ? b(world, blockposition) : false;
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  72 */     if (!b(world, blockposition)) {
/*  73 */       world.setAir(blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean b(World world, BlockPosition blockposition) {
/*  79 */     return world.getType(blockposition.down()).getMaterial().isBuildable();
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/*  83 */     return 0;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  87 */     return Items.a;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  91 */     return new ItemStack(Items.CAKE);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/*  95 */     return getBlockData().set(BITES, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/*  99 */     return ((Integer)iblockdata.<Integer>get(BITES)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 103 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { BITES });
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 107 */     return (7 - ((Integer)iblockdata.<Integer>get(BITES)).intValue()) * 2;
/*     */   }
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 115 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */