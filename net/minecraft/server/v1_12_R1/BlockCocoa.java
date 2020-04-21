/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockCocoa
/*     */   extends BlockFacingHorizontal
/*     */   implements IBlockFragilePlantElement {
/*   9 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 2);
/*  10 */   protected static final AxisAlignedBB[] b = new AxisAlignedBB[] { new AxisAlignedBB(0.6875D, 0.4375D, 0.375D, 0.9375D, 0.75D, 0.625D), new AxisAlignedBB(0.5625D, 0.3125D, 0.3125D, 0.9375D, 0.75D, 0.6875D), new AxisAlignedBB(0.4375D, 0.1875D, 0.25D, 0.9375D, 0.75D, 0.75D) };
/*  11 */   protected static final AxisAlignedBB[] c = new AxisAlignedBB[] { new AxisAlignedBB(0.0625D, 0.4375D, 0.375D, 0.3125D, 0.75D, 0.625D), new AxisAlignedBB(0.0625D, 0.3125D, 0.3125D, 0.4375D, 0.75D, 0.6875D), new AxisAlignedBB(0.0625D, 0.1875D, 0.25D, 0.5625D, 0.75D, 0.75D) };
/*  12 */   protected static final AxisAlignedBB[] d = new AxisAlignedBB[] { new AxisAlignedBB(0.375D, 0.4375D, 0.0625D, 0.625D, 0.75D, 0.3125D), new AxisAlignedBB(0.3125D, 0.3125D, 0.0625D, 0.6875D, 0.75D, 0.4375D), new AxisAlignedBB(0.25D, 0.1875D, 0.0625D, 0.75D, 0.75D, 0.5625D) };
/*  13 */   protected static final AxisAlignedBB[] e = new AxisAlignedBB[] { new AxisAlignedBB(0.375D, 0.4375D, 0.6875D, 0.625D, 0.75D, 0.9375D), new AxisAlignedBB(0.3125D, 0.3125D, 0.5625D, 0.6875D, 0.75D, 0.9375D), new AxisAlignedBB(0.25D, 0.1875D, 0.4375D, 0.75D, 0.75D, 0.9375D) };
/*     */   
/*     */   public BlockCocoa() {
/*  16 */     super(Material.PLANT);
/*  17 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(AGE, Integer.valueOf(0)));
/*  18 */     a(true);
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  22 */     if (!e(world, blockposition, iblockdata)) {
/*  23 */       f(world, blockposition, iblockdata);
/*  24 */     } else if (world.random.nextInt(Math.max(1, (int)(100.0F / world.spigotConfig.cocoaModifier) * 5)) == 0) {
/*  25 */       int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */       
/*  27 */       if (i < 2) {
/*     */         
/*  29 */         IBlockData data = iblockdata.set(AGE, Integer.valueOf(i + 1));
/*  30 */         CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(data));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  38 */     blockposition = blockposition.shift(iblockdata.<EnumDirection>get(FACING));
/*  39 */     IBlockData iblockdata1 = world.getType(blockposition);
/*     */     
/*  41 */     return (iblockdata1.getBlock() == Blocks.LOG && iblockdata1.get(BlockLog1.VARIANT) == BlockWood.EnumLogVariant.JUNGLE);
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  49 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  53 */     int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */     
/*  55 */     switch ((EnumDirection)iblockdata.get(FACING)) {
/*     */       case SOUTH:
/*  57 */         return e[i];
/*     */ 
/*     */       
/*     */       default:
/*  61 */         return d[i];
/*     */       
/*     */       case WEST:
/*  64 */         return c[i];
/*     */       case EAST:
/*     */         break;
/*  67 */     }  return b[i];
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/*  72 */     return iblockdata.set(FACING, enumblockrotation.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/*  76 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(FACING)));
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/*  80 */     EnumDirection enumdirection = EnumDirection.fromAngle(entityliving.yaw);
/*     */     
/*  82 */     world.setTypeAndData(blockposition, iblockdata.set(FACING, enumdirection), 2);
/*     */   }
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/*  86 */     if (!enumdirection.k().c()) {
/*  87 */       enumdirection = EnumDirection.NORTH;
/*     */     }
/*     */     
/*  90 */     return getBlockData().<Comparable, EnumDirection>set(FACING, enumdirection.opposite()).set(AGE, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  94 */     if (!e(world, blockposition, iblockdata)) {
/*  95 */       f(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 101 */     world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
/* 102 */     b(world, blockposition, iblockdata, 0);
/*     */   }
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/* 106 */     int j = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/* 107 */     byte b0 = 1;
/*     */     
/* 109 */     if (j >= 2) {
/* 110 */       b0 = 3;
/*     */     }
/*     */     
/* 113 */     for (int k = 0; k < b0; k++) {
/* 114 */       a(world, blockposition, new ItemStack(Items.DYE, 1, EnumColor.BROWN.getInvColorIndex()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 120 */     return new ItemStack(Items.DYE, 1, EnumColor.BROWN.getInvColorIndex());
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 124 */     return (((Integer)iblockdata.get(AGE)).intValue() < 2);
/*     */   }
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 133 */     IBlockData data = iblockdata.set(AGE, Integer.valueOf(((Integer)iblockdata.<Integer>get(AGE)).intValue() + 1));
/* 134 */     CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(data));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 139 */     return getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.fromType2(i)).set(AGE, Integer.valueOf((i & 0xF) >> 2));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 143 */     byte b0 = 0;
/* 144 */     int i = b0 | ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).get2DRotationValue();
/*     */     
/* 146 */     i |= ((Integer)iblockdata.<Integer>get(AGE)).intValue() << 2;
/* 147 */     return i;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 151 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, AGE });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 155 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCocoa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */