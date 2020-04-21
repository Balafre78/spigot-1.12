/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockFromToEvent;
/*     */ 
/*     */ public class BlockDragonEgg extends Block {
/*   9 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
/*     */   
/*     */   public BlockDragonEgg() {
/*  12 */     super(Material.DRAGON_EGG, MaterialMapColor.F);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  16 */     return a;
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  20 */     world.a(blockposition, this, a(world));
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  24 */     world.a(blockposition, this, a(world));
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  28 */     b(world, blockposition);
/*     */   }
/*     */   
/*     */   private void b(World world, BlockPosition blockposition) {
/*  32 */     if (BlockFalling.x(world.getType(blockposition.down())) && blockposition.getY() >= 0)
/*     */     {
/*     */       
/*  35 */       if (!BlockFalling.instaFall && world.areChunksLoadedBetween(blockposition.a(-32, -32, -32), blockposition.a(32, 32, 32))) {
/*  36 */         world.addEntity(new EntityFallingBlock(world, (blockposition.getX() + 0.5F), blockposition.getY(), (blockposition.getZ() + 0.5F), getBlockData()));
/*     */       } else {
/*  38 */         world.setAir(blockposition);
/*     */         
/*     */         BlockPosition blockposition1;
/*     */         
/*  42 */         for (blockposition1 = blockposition; BlockFalling.x(world.getType(blockposition1)) && blockposition1.getY() > 0; blockposition1 = blockposition1.down());
/*     */ 
/*     */ 
/*     */         
/*  46 */         if (blockposition1.getY() > 0) {
/*  47 */           world.setTypeAndData(blockposition1, getBlockData(), 2);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  55 */     c(world, blockposition);
/*  56 */     return true;
/*     */   }
/*     */   
/*     */   public void attack(World world, BlockPosition blockposition, EntityHuman entityhuman) {
/*  60 */     c(world, blockposition);
/*     */   }
/*     */   
/*     */   private void c(World world, BlockPosition blockposition) {
/*  64 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  66 */     if (iblockdata.getBlock() == this) {
/*  67 */       for (int i = 0; i < 1000; i++) {
/*  68 */         BlockPosition blockposition1 = blockposition.a(world.random.nextInt(16) - world.random.nextInt(16), world.random.nextInt(8) - world.random.nextInt(8), world.random.nextInt(16) - world.random.nextInt(16));
/*     */         
/*  70 */         if ((world.getType(blockposition1).getBlock()).material == Material.AIR) {
/*     */           
/*  72 */           Block from = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  73 */           Block to = world.getWorld().getBlockAt(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/*  74 */           BlockFromToEvent event = new BlockFromToEvent(from, to);
/*  75 */           Bukkit.getPluginManager().callEvent((Event)event);
/*     */           
/*  77 */           if (event.isCancelled()) {
/*     */             return;
/*     */           }
/*     */           
/*  81 */           blockposition1 = new BlockPosition(event.getToBlock().getX(), event.getToBlock().getY(), event.getToBlock().getZ());
/*     */           
/*  83 */           if (world.isClientSide) {
/*  84 */             for (int j = 0; j < 128; j++) {
/*  85 */               double d0 = world.random.nextDouble();
/*  86 */               float f = (world.random.nextFloat() - 0.5F) * 0.2F;
/*  87 */               float f1 = (world.random.nextFloat() - 0.5F) * 0.2F;
/*  88 */               float f2 = (world.random.nextFloat() - 0.5F) * 0.2F;
/*  89 */               double d1 = blockposition1.getX() + (blockposition.getX() - blockposition1.getX()) * d0 + world.random.nextDouble() - 0.5D + 0.5D;
/*  90 */               double d2 = blockposition1.getY() + (blockposition.getY() - blockposition1.getY()) * d0 + world.random.nextDouble() - 0.5D;
/*  91 */               double d3 = blockposition1.getZ() + (blockposition.getZ() - blockposition1.getZ()) * d0 + world.random.nextDouble() - 0.5D + 0.5D;
/*     */               
/*  93 */               world.addParticle(EnumParticle.PORTAL, d1, d2, d3, f, f1, f2, new int[0]);
/*     */             } 
/*     */           } else {
/*  96 */             world.setTypeAndData(blockposition1, iblockdata, 2);
/*  97 */             world.setAir(blockposition);
/*     */           } 
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(World world) {
/* 108 */     return 5;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 120 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDragonEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */