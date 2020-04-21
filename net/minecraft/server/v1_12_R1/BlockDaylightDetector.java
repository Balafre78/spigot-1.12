/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockDaylightDetector extends BlockTileEntity {
/*   7 */   public static final BlockStateInteger POWER = BlockStateInteger.of("power", 0, 15);
/*   8 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
/*     */   private final boolean c;
/*     */   
/*     */   public BlockDaylightDetector(boolean flag) {
/*  12 */     super(Material.WOOD);
/*  13 */     this.c = flag;
/*  14 */     w(this.blockStateList.getBlockData().set(POWER, Integer.valueOf(0)));
/*  15 */     a(CreativeModeTab.d);
/*  16 */     c(0.2F);
/*  17 */     a(SoundEffectType.a);
/*  18 */     c("daylightDetector");
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  22 */     return b;
/*     */   }
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  26 */     return ((Integer)iblockdata.<Integer>get(POWER)).intValue();
/*     */   }
/*     */   
/*     */   public void c(World world, BlockPosition blockposition) {
/*  30 */     if (world.worldProvider.m()) {
/*  31 */       IBlockData iblockdata = world.getType(blockposition);
/*  32 */       int i = world.getBrightness(EnumSkyBlock.SKY, blockposition) - world.ah();
/*  33 */       float f = world.d(1.0F);
/*     */       
/*  35 */       if (this.c) {
/*  36 */         i = 15 - i;
/*     */       }
/*     */       
/*  39 */       if (i > 0 && !this.c) {
/*  40 */         float f1 = (f < 3.1415927F) ? 0.0F : 6.2831855F;
/*     */         
/*  42 */         f += (f1 - f) * 0.2F;
/*  43 */         i = Math.round(i * MathHelper.cos(f));
/*     */       } 
/*     */       
/*  46 */       i = MathHelper.clamp(i, 0, 15);
/*  47 */       if (((Integer)iblockdata.<Integer>get(POWER)).intValue() != i) {
/*  48 */         i = CraftEventFactory.callRedstoneChange(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), ((Integer)iblockdata.<Integer>get(POWER)).intValue(), i).getNewCurrent();
/*  49 */         world.setTypeAndData(blockposition, iblockdata.set(POWER, Integer.valueOf(i)), 3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  56 */     if (entityhuman.dk()) {
/*  57 */       if (world.isClientSide) {
/*  58 */         return true;
/*     */       }
/*  60 */       if (this.c) {
/*  61 */         world.setTypeAndData(blockposition, Blocks.DAYLIGHT_DETECTOR.getBlockData().set(POWER, iblockdata.<Integer>get(POWER)), 4);
/*  62 */         Blocks.DAYLIGHT_DETECTOR.c(world, blockposition);
/*     */       } else {
/*  64 */         world.setTypeAndData(blockposition, Blocks.DAYLIGHT_DETECTOR_INVERTED.getBlockData().set(POWER, iblockdata.<Integer>get(POWER)), 4);
/*  65 */         Blocks.DAYLIGHT_DETECTOR_INVERTED.c(world, blockposition);
/*     */       } 
/*     */       
/*  68 */       return true;
/*     */     } 
/*     */     
/*  71 */     return super.interact(world, blockposition, iblockdata, entityhuman, enumhand, enumdirection, f, f1, f2);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  76 */     return Item.getItemOf(Blocks.DAYLIGHT_DETECTOR);
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  80 */     return new ItemStack(Blocks.DAYLIGHT_DETECTOR);
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public EnumRenderType a(IBlockData iblockdata) {
/*  92 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/*  96 */     return true;
/*     */   }
/*     */   
/*     */   public TileEntity a(World world, int i) {
/* 100 */     return new TileEntityLightDetector();
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 104 */     return getBlockData().set(POWER, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 108 */     return ((Integer)iblockdata.<Integer>get(POWER)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 112 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { POWER });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 116 */     return (enumdirection == EnumDirection.DOWN) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */