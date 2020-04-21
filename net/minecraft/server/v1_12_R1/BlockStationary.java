/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockStationary
/*     */   extends BlockFluids
/*     */ {
/*     */   protected BlockStationary(Material material) {
/*  10 */     super(material);
/*  11 */     a(false);
/*  12 */     if (material == Material.LAVA) {
/*  13 */       a(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  19 */     if (!e(world, blockposition, iblockdata)) {
/*  20 */       f(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  26 */     BlockFlowing blockflowing = a(this.material);
/*     */     
/*  28 */     world.setTypeAndData(blockposition, blockflowing.getBlockData().set(LEVEL, iblockdata.<Integer>get(LEVEL)), 2);
/*  29 */     world.a(blockposition, blockflowing, a(world));
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  33 */     if (this.material == Material.LAVA && 
/*  34 */       world.getGameRules().getBoolean("doFireTick")) {
/*  35 */       int i = random.nextInt(3);
/*     */       
/*  37 */       if (i > 0) {
/*  38 */         BlockPosition blockposition1 = blockposition;
/*     */         
/*  40 */         for (int j = 0; j < i; j++) {
/*  41 */           blockposition1 = blockposition1.a(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
/*  42 */           if (blockposition1.getY() >= 0 && blockposition1.getY() < 256 && !world.isLoaded(blockposition1)) {
/*     */             return;
/*     */           }
/*     */           
/*  46 */           Block block = world.getType(blockposition1).getBlock();
/*     */           
/*  48 */           if (block.material == Material.AIR) {
/*  49 */             if (c(world, blockposition1))
/*     */             {
/*  51 */               if (world.getType(blockposition1) == Blocks.FIRE || 
/*  52 */                 !CraftEventFactory.callBlockIgniteEvent(world, blockposition1.getX(), blockposition1.getY(), blockposition1.getZ(), blockposition.getX(), blockposition.getY(), blockposition.getZ()).isCancelled()) {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*  57 */                 world.setTypeUpdate(blockposition1, Blocks.FIRE.getBlockData());
/*     */                 return;
/*     */               }  } 
/*  60 */           } else if (block.material.isSolid()) {
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } else {
/*  65 */         for (int k = 0; k < 3; k++) {
/*  66 */           BlockPosition blockposition2 = blockposition.a(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
/*     */           
/*  68 */           if (blockposition2.getY() >= 0 && blockposition2.getY() < 256 && !world.isLoaded(blockposition2)) {
/*     */             return;
/*     */           }
/*     */           
/*  72 */           if (world.isEmpty(blockposition2.up()) && d(world, blockposition2)) {
/*     */             
/*  74 */             BlockPosition up = blockposition2.up();
/*  75 */             if (world.getType(up) == Blocks.FIRE || 
/*  76 */               !CraftEventFactory.callBlockIgniteEvent(world, up.getX(), up.getY(), up.getZ(), blockposition.getX(), blockposition.getY(), blockposition.getZ()).isCancelled())
/*     */             {
/*     */ 
/*     */ 
/*     */               
/*  81 */               world.setTypeUpdate(blockposition2.up(), Blocks.FIRE.getBlockData());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c(World world, BlockPosition blockposition) {
/*  91 */     EnumDirection[] aenumdirection = EnumDirection.values();
/*  92 */     int i = aenumdirection.length;
/*     */     
/*  94 */     for (int j = 0; j < i; j++) {
/*  95 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/*  97 */       if (d(world, blockposition.shift(enumdirection))) {
/*  98 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   private boolean d(World world, BlockPosition blockposition) {
/* 106 */     return (blockposition.getY() >= 0 && blockposition.getY() < 256 && !world.isLoaded(blockposition)) ? false : world.getType(blockposition).getMaterial().isBurnable();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStationary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */