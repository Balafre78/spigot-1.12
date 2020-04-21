/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockReed extends Block {
/*   9 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 15);
/*  10 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);
/*     */   
/*     */   protected BlockReed() {
/*  13 */     super(Material.PLANT);
/*  14 */     w(this.blockStateList.getBlockData().set(AGE, Integer.valueOf(0)));
/*  15 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  19 */     return b;
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  23 */     if ((world.getType(blockposition.down()).getBlock() == Blocks.REEDS || e(world, blockposition, iblockdata)) && 
/*  24 */       world.isEmpty(blockposition.up())) {
/*     */       int i;
/*     */       
/*  27 */       for (i = 1; world.getType(blockposition.down(i)).getBlock() == this; i++);
/*     */ 
/*     */ 
/*     */       
/*  31 */       if (i < 3) {
/*  32 */         int j = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */         
/*  34 */         if (j >= (byte)(int)range(3.0F, 100.0F / world.spigotConfig.caneModifier * 15.0F + 0.5F, 15.0F)) {
/*     */ 
/*     */           
/*  37 */           BlockPosition upPos = blockposition.up();
/*  38 */           CraftEventFactory.handleBlockGrowEvent(world, upPos.getX(), upPos.getY(), upPos.getZ(), this, 0);
/*  39 */           world.setTypeAndData(blockposition, iblockdata.set(AGE, Integer.valueOf(0)), 4);
/*     */         } else {
/*     */           
/*  42 */           world.setTypeAndData(blockposition, iblockdata.set(AGE, Integer.valueOf(j + 1)), 4);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*     */     IBlockData iblockdata;
/*  51 */     Block block = world.getType(blockposition.down()).getBlock();
/*     */     
/*  53 */     if (block == this)
/*  54 */       return true; 
/*  55 */     if (block != Blocks.GRASS && block != Blocks.DIRT && block != Blocks.SAND) {
/*  56 */       return false;
/*     */     }
/*  58 */     BlockPosition blockposition1 = blockposition.down();
/*  59 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  64 */       if (!iterator.hasNext()) {
/*  65 */         return false;
/*     */       }
/*     */       
/*  68 */       EnumDirection enumdirection = iterator.next();
/*     */       
/*  70 */       iblockdata = world.getType(blockposition1.shift(enumdirection));
/*  71 */     } while (iblockdata.getMaterial() != Material.WATER && iblockdata.getBlock() != Blocks.FROSTED_ICE);
/*     */     
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  78 */     e(world, blockposition, iblockdata);
/*     */   }
/*     */   
/*     */   protected final boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  82 */     if (b(world, blockposition)) {
/*  83 */       return true;
/*     */     }
/*  85 */     b(world, blockposition, iblockdata, 0);
/*  86 */     world.setAir(blockposition);
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(World world, BlockPosition blockposition) {
/*  92 */     return canPlace(world, blockposition);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  97 */     return k;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 101 */     return Items.REEDS;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 113 */     return new ItemStack(Items.REEDS);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 117 */     return getBlockData().set(AGE, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 121 */     return ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 125 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AGE });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 129 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockReed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */