/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockCrops
/*     */   extends BlockPlant
/*     */   implements IBlockFragilePlantElement {
/*   9 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 7);
/*  10 */   private static final AxisAlignedBB[] a = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
/*     */   
/*     */   protected BlockCrops() {
/*  13 */     w(this.blockStateList.getBlockData().set(e(), Integer.valueOf(0)));
/*  14 */     a(true);
/*  15 */     a((CreativeModeTab)null);
/*  16 */     c(0.0F);
/*  17 */     a(SoundEffectType.c);
/*  18 */     p();
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  22 */     return a[((Integer)iblockdata.get(e())).intValue()];
/*     */   }
/*     */   
/*     */   protected boolean x(IBlockData iblockdata) {
/*  26 */     return (iblockdata.getBlock() == Blocks.FARMLAND);
/*     */   }
/*     */   
/*     */   protected BlockStateInteger e() {
/*  30 */     return AGE;
/*     */   }
/*     */   
/*     */   public int g() {
/*  34 */     return 7;
/*     */   }
/*     */   
/*     */   protected int y(IBlockData iblockdata) {
/*  38 */     return ((Integer)iblockdata.<Integer>get(e())).intValue();
/*     */   }
/*     */   
/*     */   public IBlockData setAge(int i) {
/*  42 */     return getBlockData().set(e(), Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public boolean z(IBlockData iblockdata) {
/*  46 */     return (((Integer)iblockdata.get(e())).intValue() >= g());
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  50 */     super.b(world, blockposition, iblockdata, random);
/*  51 */     if (world.getLightLevel(blockposition.up()) >= 9) {
/*  52 */       int i = y(iblockdata);
/*     */       
/*  54 */       if (i < g()) {
/*  55 */         float f = a(this, world, blockposition);
/*     */         
/*  57 */         if (random.nextInt((int)(100.0F / world.spigotConfig.wheatModifier * 25.0F / f) + 1) == 0) {
/*     */           
/*  59 */           IBlockData data = setAge(i + 1);
/*  60 */           CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(data));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  69 */     int i = y(iblockdata) + b(world);
/*  70 */     int j = g();
/*     */     
/*  72 */     if (i > j) {
/*  73 */       i = j;
/*     */     }
/*     */ 
/*     */     
/*  77 */     IBlockData data = setAge(i);
/*  78 */     CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(data));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int b(World world) {
/*  83 */     return MathHelper.nextInt(world.random, 2, 5);
/*     */   }
/*     */   
/*     */   protected static float a(Block block, World world, BlockPosition blockposition) {
/*  87 */     float f = 1.0F;
/*  88 */     BlockPosition blockposition1 = blockposition.down();
/*     */     
/*  90 */     for (int i = -1; i <= 1; i++) {
/*  91 */       for (int j = -1; j <= 1; j++) {
/*  92 */         float f1 = 0.0F;
/*  93 */         IBlockData iblockdata = world.getType(blockposition1.a(i, 0, j));
/*     */         
/*  95 */         if (iblockdata.getBlock() == Blocks.FARMLAND) {
/*  96 */           f1 = 1.0F;
/*  97 */           if (((Integer)iblockdata.<Integer>get(BlockSoil.MOISTURE)).intValue() > 0) {
/*  98 */             f1 = 3.0F;
/*     */           }
/*     */         } 
/*     */         
/* 102 */         if (i != 0 || j != 0) {
/* 103 */           f1 /= 4.0F;
/*     */         }
/*     */         
/* 106 */         f += f1;
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     BlockPosition blockposition2 = blockposition.north();
/* 111 */     BlockPosition blockposition3 = blockposition.south();
/* 112 */     BlockPosition blockposition4 = blockposition.west();
/* 113 */     BlockPosition blockposition5 = blockposition.east();
/* 114 */     boolean flag = !(block != world.getType(blockposition4).getBlock() && block != world.getType(blockposition5).getBlock());
/* 115 */     boolean flag1 = !(block != world.getType(blockposition2).getBlock() && block != world.getType(blockposition3).getBlock());
/*     */     
/* 117 */     if (flag && flag1) {
/* 118 */       f /= 2.0F;
/*     */     } else {
/* 120 */       boolean flag2 = !(block != world.getType(blockposition4.north()).getBlock() && block != world.getType(blockposition5.north()).getBlock() && block != world.getType(blockposition5.south()).getBlock() && block != world.getType(blockposition4.south()).getBlock());
/*     */       
/* 122 */       if (flag2) {
/* 123 */         f /= 2.0F;
/*     */       }
/*     */     } 
/*     */     
/* 127 */     return f;
/*     */   }
/*     */   
/*     */   public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 131 */     return ((world.j(blockposition) >= 8 || world.h(blockposition)) && x(world.getType(blockposition.down())));
/*     */   }
/*     */   
/*     */   protected Item h() {
/* 135 */     return Items.WHEAT_SEEDS;
/*     */   }
/*     */   
/*     */   protected Item i() {
/* 139 */     return Items.WHEAT;
/*     */   }
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/* 143 */     super.dropNaturally(world, blockposition, iblockdata, f, 0);
/* 144 */     if (!world.isClientSide) {
/* 145 */       int j = y(iblockdata);
/*     */       
/* 147 */       if (j >= g()) {
/* 148 */         int k = 3 + i;
/*     */         
/* 150 */         for (int l = 0; l < k; l++) {
/* 151 */           if (world.random.nextInt(2 * g()) <= j) {
/* 152 */             a(world, blockposition, new ItemStack(h()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 161 */     return z(iblockdata) ? i() : h();
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 165 */     return new ItemStack(h());
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 169 */     return !z(iblockdata);
/*     */   }
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 173 */     return true;
/*     */   }
/*     */   
/*     */   public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 177 */     g(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 181 */     return setAge(i);
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 185 */     return y(iblockdata);
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 189 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AGE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */