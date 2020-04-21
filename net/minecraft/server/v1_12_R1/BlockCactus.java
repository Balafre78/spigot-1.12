/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockCactus
/*     */   extends Block
/*     */ {
/*  10 */   public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 15);
/*  11 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
/*  12 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
/*     */   
/*     */   protected BlockCactus() {
/*  15 */     super(Material.CACTUS);
/*  16 */     w(this.blockStateList.getBlockData().set(AGE, Integer.valueOf(0)));
/*  17 */     a(true);
/*  18 */     a(CreativeModeTab.c);
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  22 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/*  24 */     if (world.isEmpty(blockposition1)) {
/*     */       int i;
/*     */       
/*  27 */       for (i = 1; world.getType(blockposition.down(i)).getBlock() == this; i++);
/*     */ 
/*     */ 
/*     */       
/*  31 */       if (i < 3) {
/*  32 */         int j = ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */         
/*  34 */         if (j >= (byte)(int)range(3.0F, 100.0F / world.spigotConfig.cactusModifier * 15.0F + 0.5F, 15.0F)) {
/*     */           
/*  36 */           IBlockData iblockdata1 = iblockdata.set(AGE, Integer.valueOf(0));
/*     */           
/*  38 */           CraftEventFactory.handleBlockGrowEvent(world, blockposition1.getX(), blockposition1.getY(), blockposition1.getZ(), this, 0);
/*  39 */           world.setTypeAndData(blockposition, iblockdata1, 4);
/*  40 */           iblockdata1.doPhysics(world, blockposition1, this, blockposition);
/*     */         } else {
/*  42 */           world.setTypeAndData(blockposition, iblockdata.set(AGE, Integer.valueOf(j + 1)), 4);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  50 */     return b;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  62 */     return super.canPlace(world, blockposition) ? b(world, blockposition) : false;
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  66 */     if (!b(world, blockposition)) {
/*  67 */       world.setAir(blockposition, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean b(World world, BlockPosition blockposition) {
/*     */     Material material;
/*  73 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  78 */       if (!iterator.hasNext()) {
/*  79 */         Block block = world.getType(blockposition.down()).getBlock();
/*     */         
/*  81 */         return !(block != Blocks.CACTUS && (block != Blocks.SAND || world.getType(blockposition.up()).getMaterial().isLiquid()));
/*     */       } 
/*     */       
/*  84 */       EnumDirection enumdirection = iterator.next();
/*     */       
/*  86 */       material = world.getType(blockposition.shift(enumdirection)).getMaterial();
/*  87 */     } while (!material.isBuildable() && material != Material.LAVA);
/*     */     
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/*  93 */     CraftEventFactory.blockDamage = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  94 */     entity.damageEntity(DamageSource.CACTUS, 1.0F);
/*  95 */     CraftEventFactory.blockDamage = null;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/*  99 */     return getBlockData().set(AGE, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 103 */     return ((Integer)iblockdata.<Integer>get(AGE)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 107 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { AGE });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 111 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCactus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */