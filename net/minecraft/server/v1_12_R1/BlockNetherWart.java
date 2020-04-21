/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockNetherWart extends BlockPlant {
/*  7 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 3);
/*  8 */   private static final AxisAlignedBB[] c = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D) };
/*    */   
/*    */   protected BlockNetherWart() {
/* 11 */     super(Material.PLANT, MaterialMapColor.E);
/* 12 */     w(this.blockStateList.getBlockData().set(AGE, Integer.valueOf(0)));
/* 13 */     a(true);
/* 14 */     a((CreativeModeTab)null);
/*    */   }
/*    */   
/*    */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 18 */     return c[((Integer)iblockdata.get(AGE)).intValue()];
/*    */   }
/*    */   
/*    */   protected boolean x(IBlockData iblockdata) {
/* 22 */     return (iblockdata.getBlock() == Blocks.SOUL_SAND);
/*    */   }
/*    */   
/*    */   public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 26 */     return x(world.getType(blockposition.down()));
/*    */   }
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 30 */     int i = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*    */     
/* 32 */     if (i < 3 && random.nextInt(Math.max(1, (int)(100.0F / world.spigotConfig.wartModifier) * 10)) == 0) {
/* 33 */       iblockdata = iblockdata.set(AGE, Integer.valueOf(i + 1));
/*    */       
/* 35 */       CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(iblockdata));
/*    */     } 
/*    */     
/* 38 */     super.b(world, blockposition, iblockdata, random);
/*    */   }
/*    */   
/*    */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/* 42 */     if (!world.isClientSide) {
/* 43 */       int j = 1;
/*    */       
/* 45 */       if (((Integer)iblockdata.get(AGE)).intValue() >= 3) {
/* 46 */         j = 2 + world.random.nextInt(3);
/* 47 */         if (i > 0) {
/* 48 */           j += world.random.nextInt(i + 1);
/*    */         }
/*    */       } 
/*    */       
/* 52 */       for (int k = 0; k < j; k++) {
/* 53 */         a(world, blockposition, new ItemStack(Items.NETHER_WART));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 60 */     return Items.a;
/*    */   }
/*    */   
/*    */   public int a(Random random) {
/* 64 */     return 0;
/*    */   }
/*    */   
/*    */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 68 */     return new ItemStack(Items.NETHER_WART);
/*    */   }
/*    */   
/*    */   public IBlockData fromLegacyData(int i) {
/* 72 */     return getBlockData().set(AGE, Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   public int toLegacyData(IBlockData iblockdata) {
/* 76 */     return ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*    */   }
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 80 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AGE });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockNetherWart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */