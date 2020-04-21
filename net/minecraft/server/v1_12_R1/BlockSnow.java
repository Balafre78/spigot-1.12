/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockSnow extends Block {
/*   8 */   public static final BlockStateInteger LAYERS = BlockStateInteger.of("layers", 1, 8);
/*   9 */   protected static final AxisAlignedBB[] b = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
/*     */   
/*     */   protected BlockSnow() {
/*  12 */     super(Material.PACKED_ICE);
/*  13 */     w(this.blockStateList.getBlockData().set(LAYERS, Integer.valueOf(1)));
/*  14 */     a(true);
/*  15 */     a(CreativeModeTab.c);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  19 */     return b[((Integer)iblockdata.get(LAYERS)).intValue()];
/*     */   }
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  23 */     return (((Integer)iblockaccess.getType(blockposition).get(LAYERS)).intValue() < 5);
/*     */   }
/*     */   
/*     */   public boolean k(IBlockData iblockdata) {
/*  27 */     return (((Integer)iblockdata.<Integer>get(LAYERS)).intValue() == 8);
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/*  31 */     return (enumdirection == EnumDirection.DOWN) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  36 */     int i = ((Integer)iblockdata.<Integer>get(LAYERS)).intValue() - 1;
/*     */     
/*  38 */     AxisAlignedBB axisalignedbb = iblockdata.e(iblockaccess, blockposition);
/*     */     
/*  40 */     return new AxisAlignedBB(axisalignedbb.a, axisalignedbb.b, axisalignedbb.c, axisalignedbb.d, (i * 0.125F), axisalignedbb.f);
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  48 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  52 */     IBlockData iblockdata = world.getType(blockposition.down());
/*  53 */     Block block = iblockdata.getBlock();
/*     */     
/*  55 */     if (block != Blocks.ICE && block != Blocks.PACKED_ICE && block != Blocks.BARRIER) {
/*  56 */       EnumBlockFaceShape enumblockfaceshape = iblockdata.d(world, blockposition.down(), EnumDirection.UP);
/*     */       
/*  58 */       return !(enumblockfaceshape != EnumBlockFaceShape.SOLID && iblockdata.getMaterial() != Material.LEAVES && (block != this || ((Integer)iblockdata.<Integer>get(LAYERS)).intValue() != 8));
/*     */     } 
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  65 */     e(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   private boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  69 */     if (!canPlace(world, blockposition)) {
/*  70 */       b(world, blockposition, iblockdata, 0);
/*  71 */       world.setAir(blockposition);
/*  72 */       return false;
/*     */     } 
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/*  79 */     a(world, blockposition, new ItemStack(Items.SNOWBALL, ((Integer)iblockdata.<Integer>get(LAYERS)).intValue() + 1, 0));
/*  80 */     world.setAir(blockposition);
/*  81 */     entityhuman.b(StatisticList.a(this));
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  85 */     return Items.SNOWBALL;
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/*  89 */     return 0;
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  93 */     if (world.getBrightness(EnumSkyBlock.BLOCK, blockposition) > 11) {
/*     */       
/*  95 */       if (CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), Blocks.AIR).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  99 */       b(world, blockposition, world.getType(blockposition), 0);
/* 100 */       world.setAir(blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 106 */     return getBlockData().set(LAYERS, Integer.valueOf((i & 0x7) + 1));
/*     */   }
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 110 */     return (((Integer)iblockaccess.getType(blockposition).<Integer>get(LAYERS)).intValue() == 1);
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 114 */     return ((Integer)iblockdata.<Integer>get(LAYERS)).intValue() - 1;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 118 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { LAYERS });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */