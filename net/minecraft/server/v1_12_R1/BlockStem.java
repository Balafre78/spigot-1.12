/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockStem
/*     */   extends BlockPlant
/*     */   implements IBlockFragilePlantElement {
/*  11 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 7);
/*  12 */   public static final BlockStateDirection FACING = BlockTorch.FACING;
/*     */   private final Block blockFruit;
/*  14 */   protected static final AxisAlignedBB[] d = new AxisAlignedBB[] { new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.5D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.625D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.75D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D) };
/*     */   
/*     */   protected BlockStem(Block block) {
/*  17 */     w(this.blockStateList.getBlockData().<Comparable, Integer>set(AGE, Integer.valueOf(0)).set(FACING, EnumDirection.UP));
/*  18 */     this.blockFruit = block;
/*  19 */     a(true);
/*  20 */     a((CreativeModeTab)null);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  24 */     return d[((Integer)iblockdata.get(AGE)).intValue()];
/*     */   }
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  28 */     int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */     
/*  30 */     iblockdata = iblockdata.set(FACING, EnumDirection.UP);
/*  31 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/*  33 */     while (iterator.hasNext()) {
/*  34 */       EnumDirection enumdirection = iterator.next();
/*     */       
/*  36 */       if (iblockaccess.getType(blockposition.shift(enumdirection)).getBlock() == this.blockFruit && i == 7) {
/*  37 */         iblockdata = iblockdata.set(FACING, enumdirection);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  42 */     return iblockdata;
/*     */   }
/*     */   
/*     */   protected boolean x(IBlockData iblockdata) {
/*  46 */     return (iblockdata.getBlock() == Blocks.FARMLAND);
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  50 */     super.b(world, blockposition, iblockdata, random);
/*  51 */     if (world.getLightLevel(blockposition.up()) >= 9) {
/*  52 */       float f = BlockCrops.a(this, world, blockposition);
/*     */       
/*  54 */       if (random.nextInt((int)(100.0F / ((this == Blocks.PUMPKIN_STEM) ? world.spigotConfig.pumpkinModifier : world.spigotConfig.melonModifier) * 25.0F / f) + 1) == 0) {
/*  55 */         int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */         
/*  57 */         if (i < 7) {
/*  58 */           iblockdata = iblockdata.set(AGE, Integer.valueOf(i + 1));
/*     */           
/*  60 */           CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(iblockdata));
/*     */         } else {
/*  62 */           Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */           
/*  64 */           while (iterator.hasNext()) {
/*  65 */             EnumDirection enumdirection = iterator.next();
/*     */             
/*  67 */             if (world.getType(blockposition.shift(enumdirection)).getBlock() == this.blockFruit) {
/*     */               return;
/*     */             }
/*     */           } 
/*     */           
/*  72 */           blockposition = blockposition.shift(EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random));
/*  73 */           Block block = world.getType(blockposition.down()).getBlock();
/*     */           
/*  75 */           if ((world.getType(blockposition).getBlock()).material == Material.AIR && (block == Blocks.FARMLAND || block == Blocks.DIRT || block == Blocks.GRASS))
/*     */           {
/*  77 */             CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this.blockFruit, 0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  86 */     int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue() + MathHelper.nextInt(world.random, 2, 5);
/*     */ 
/*     */     
/*  89 */     CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, Math.min(7, i));
/*     */   }
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/*  93 */     super.dropNaturally(world, blockposition, iblockdata, f, i);
/*  94 */     if (!world.isClientSide) {
/*  95 */       Item item = e();
/*     */       
/*  97 */       if (item != null) {
/*  98 */         int j = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */         
/* 100 */         for (int k = 0; k < 3; k++) {
/* 101 */           if (world.random.nextInt(15) <= j) {
/* 102 */             a(world, blockposition, new ItemStack(item));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected Item e() {
/* 112 */     return (this.blockFruit == Blocks.PUMPKIN) ? Items.PUMPKIN_SEEDS : ((this.blockFruit == Blocks.MELON_BLOCK) ? Items.MELON_SEEDS : null);
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 116 */     return Items.a;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 120 */     Item item = e();
/*     */     
/* 122 */     return (item == null) ? ItemStack.a : new ItemStack(item);
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 126 */     return (((Integer)iblockdata.<Integer>get(AGE)).intValue() != 7);
/*     */   }
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 134 */     g(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 138 */     return getBlockData().set(AGE, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 142 */     return ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 146 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AGE, FACING });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */