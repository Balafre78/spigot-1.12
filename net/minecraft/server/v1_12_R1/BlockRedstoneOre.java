/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ 
/*     */ public class BlockRedstoneOre extends Block {
/*     */   private final boolean a;
/*     */   
/*     */   public BlockRedstoneOre(boolean flag) {
/*  15 */     super(Material.STONE);
/*  16 */     if (flag) {
/*  17 */       a(true);
/*     */     }
/*     */     
/*  20 */     this.a = flag;
/*     */   }
/*     */   
/*     */   public int a(World world) {
/*  24 */     return 30;
/*     */   }
/*     */   
/*     */   public void attack(World world, BlockPosition blockposition, EntityHuman entityhuman) {
/*  28 */     interact(world, blockposition, entityhuman);
/*  29 */     super.attack(world, blockposition, entityhuman);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stepOn(World world, BlockPosition blockposition, Entity entity) {
/*  36 */     if (entity instanceof EntityHuman) {
/*  37 */       PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*  38 */       if (!event.isCancelled()) {
/*  39 */         interact(world, blockposition, entity);
/*  40 */         super.stepOn(world, blockposition, entity);
/*     */       } 
/*     */     } else {
/*  43 */       EntityInteractEvent event = new EntityInteractEvent((Entity)entity.getBukkitEntity(), world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/*  44 */       world.getServer().getPluginManager().callEvent((Event)event);
/*  45 */       if (!event.isCancelled()) {
/*  46 */         interact(world, blockposition, entity);
/*  47 */         super.stepOn(world, blockposition, entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  55 */     interact(world, blockposition, entityhuman);
/*  56 */     return super.interact(world, blockposition, iblockdata, entityhuman, enumhand, enumdirection, f, f1, f2);
/*     */   }
/*     */   
/*     */   private void interact(World world, BlockPosition blockposition, Entity entity) {
/*  60 */     playEffect(world, blockposition);
/*  61 */     if (this == Blocks.REDSTONE_ORE) {
/*     */       
/*  63 */       if (CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, Blocks.LIT_REDSTONE_ORE, 0).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  67 */       world.setTypeUpdate(blockposition, Blocks.LIT_REDSTONE_ORE.getBlockData());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  73 */     if (this == Blocks.LIT_REDSTONE_ORE) {
/*     */       
/*  75 */       if (CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), Blocks.REDSTONE_ORE).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  79 */       world.setTypeUpdate(blockposition, Blocks.REDSTONE_ORE.getBlockData());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  85 */     return Items.REDSTONE;
/*     */   }
/*     */   
/*     */   public int getDropCount(int i, Random random) {
/*  89 */     return a(random) + random.nextInt(i + 1);
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/*  93 */     return 4 + random.nextInt(2);
/*     */   }
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/*  97 */     super.dropNaturally(world, blockposition, iblockdata, f, i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpDrop(World world, IBlockData data, int i) {
/* 110 */     if (getDropType(data, world.random, i) != Item.getItemOf(this)) {
/* 111 */       int j = 1 + world.random.nextInt(5);
/*     */       
/* 113 */       return j;
/*     */     } 
/* 115 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private void playEffect(World world, BlockPosition blockposition) {
/* 120 */     Random random = world.random;
/*     */ 
/*     */     
/* 123 */     for (int i = 0; i < 6; i++) {
/* 124 */       double d1 = (blockposition.getX() + random.nextFloat());
/* 125 */       double d2 = (blockposition.getY() + random.nextFloat());
/* 126 */       double d3 = (blockposition.getZ() + random.nextFloat());
/*     */       
/* 128 */       if (i == 0 && !world.getType(blockposition.up()).p()) {
/* 129 */         d2 = blockposition.getY() + 0.0625D + 1.0D;
/*     */       }
/*     */       
/* 132 */       if (i == 1 && !world.getType(blockposition.down()).p()) {
/* 133 */         d2 = blockposition.getY() - 0.0625D;
/*     */       }
/*     */       
/* 136 */       if (i == 2 && !world.getType(blockposition.south()).p()) {
/* 137 */         d3 = blockposition.getZ() + 0.0625D + 1.0D;
/*     */       }
/*     */       
/* 140 */       if (i == 3 && !world.getType(blockposition.north()).p()) {
/* 141 */         d3 = blockposition.getZ() - 0.0625D;
/*     */       }
/*     */       
/* 144 */       if (i == 4 && !world.getType(blockposition.east()).p()) {
/* 145 */         d1 = blockposition.getX() + 0.0625D + 1.0D;
/*     */       }
/*     */       
/* 148 */       if (i == 5 && !world.getType(blockposition.west()).p()) {
/* 149 */         d1 = blockposition.getX() - 0.0625D;
/*     */       }
/*     */       
/* 152 */       if (d1 < blockposition.getX() || d1 > (blockposition.getX() + 1) || d2 < 0.0D || d2 > (blockposition.getY() + 1) || d3 < blockposition.getZ() || d3 > (blockposition.getZ() + 1)) {
/* 153 */         world.addParticle(EnumParticle.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack u(IBlockData iblockdata) {
/* 160 */     return new ItemStack(Blocks.REDSTONE_ORE);
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 164 */     return new ItemStack(Item.getItemOf(Blocks.REDSTONE_ORE), 1, getDropData(iblockdata));
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRedstoneOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */