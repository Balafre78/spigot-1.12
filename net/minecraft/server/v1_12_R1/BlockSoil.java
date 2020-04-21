/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class BlockSoil extends Block {
/*  14 */   public static final BlockStateInteger MOISTURE = BlockStateInteger.of("moisture", 0, 7);
/*  15 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
/*     */   
/*     */   protected BlockSoil() {
/*  18 */     super(Material.EARTH);
/*  19 */     w(this.blockStateList.getBlockData().set(MOISTURE, Integer.valueOf(0)));
/*  20 */     a(true);
/*  21 */     e(255);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  25 */     return b;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  29 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  33 */     return false;
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  37 */     int i = ((Integer)iblockdata.<Integer>get(MOISTURE)).intValue();
/*     */     
/*  39 */     if (!d(world, blockposition) && !world.isRainingAt(blockposition.up())) {
/*  40 */       if (i > 0) {
/*  41 */         world.setTypeAndData(blockposition, iblockdata.set(MOISTURE, Integer.valueOf(i - 1)), 2);
/*  42 */       } else if (!c(world, blockposition)) {
/*     */         
/*  44 */         Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  45 */         if (CraftEventFactory.callBlockFadeEvent(block, Blocks.DIRT).isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/*  49 */         b(world, blockposition);
/*     */       } 
/*  51 */     } else if (i < 7) {
/*  52 */       world.setTypeAndData(blockposition, iblockdata.set(MOISTURE, Integer.valueOf(7)), 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
/*  58 */     super.fallOn(world, blockposition, entity, f);
/*  59 */     if (!world.isClientSide && world.random.nextFloat() < f - 0.5F && entity instanceof EntityLiving && (entity instanceof EntityHuman || world.getGameRules().getBoolean("mobGriefing")) && entity.width * entity.width * entity.length > 0.512F) {
/*     */       EntityInteractEvent entityInteractEvent;
/*     */       
/*  62 */       if (entity instanceof EntityHuman) {
/*  63 */         PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*     */       } else {
/*  65 */         entityInteractEvent = new EntityInteractEvent((Entity)entity.getBukkitEntity(), world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/*  66 */         world.getServer().getPluginManager().callEvent((Event)entityInteractEvent);
/*     */       } 
/*     */       
/*  69 */       if (entityInteractEvent.isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  73 */       if (CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, Blocks.DIRT, 0).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  77 */       b(world, blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(World world, BlockPosition blockposition) {
/*  84 */     IBlockData iblockdata = Blocks.DIRT.getBlockData();
/*     */     
/*  86 */     world.setTypeUpdate(blockposition, iblockdata);
/*  87 */     AxisAlignedBB axisalignedbb = iblockdata.d(world, blockposition).a(blockposition);
/*  88 */     List<Entity> list = world.getEntities(null, axisalignedbb);
/*  89 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/*  91 */     while (iterator.hasNext()) {
/*  92 */       Entity entity = iterator.next();
/*     */       
/*  94 */       entity.setPosition(entity.locX, axisalignedbb.e, entity.locZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean c(World world, BlockPosition blockposition) {
/* 100 */     Block block = world.getType(blockposition.up()).getBlock();
/*     */     
/* 102 */     return !(!(block instanceof BlockCrops) && !(block instanceof BlockStem));
/*     */   }
/*     */   private boolean d(World world, BlockPosition blockposition) {
/*     */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition;
/* 106 */     Iterator<BlockPosition.MutableBlockPosition> iterator = BlockPosition.b(blockposition.a(-4, 0, -4), blockposition.a(4, 1, 4)).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 111 */       if (!iterator.hasNext()) {
/* 112 */         return false;
/*     */       }
/*     */       
/* 115 */       blockposition_mutableblockposition = iterator.next();
/* 116 */     } while (world.getType(blockposition_mutableblockposition).getMaterial() != Material.WATER);
/*     */     
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 122 */     super.a(iblockdata, world, blockposition, block, blockposition1);
/* 123 */     if (world.getType(blockposition.up()).getMaterial().isBuildable()) {
/* 124 */       b(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 130 */     super.onPlace(world, blockposition, iblockdata);
/* 131 */     if (world.getType(blockposition.up()).getMaterial().isBuildable()) {
/* 132 */       b(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 138 */     return Blocks.DIRT.getDropType(Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.DIRT), random, i);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 142 */     return getBlockData().set(MOISTURE, Integer.valueOf(i & 0x7));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 146 */     return ((Integer)iblockdata.<Integer>get(MOISTURE)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 150 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { MOISTURE });
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 154 */     return (enumdirection == EnumDirection.DOWN) ? EnumBlockFaceShape.SOLID : EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSoil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */