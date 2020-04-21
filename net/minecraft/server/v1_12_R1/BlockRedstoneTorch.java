/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.WeakHashMap;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class BlockRedstoneTorch extends BlockTorch {
/*  13 */   private static final Map<World, List<RedstoneUpdateInfo>> g = new WeakHashMap<>();
/*     */   private final boolean isOn;
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition, boolean flag) {
/*  17 */     if (!g.containsKey(world)) {
/*  18 */       g.put(world, Lists.newArrayList());
/*     */     }
/*     */     
/*  21 */     List<RedstoneUpdateInfo> list = g.get(world);
/*     */     
/*  23 */     if (flag) {
/*  24 */       list.add(new RedstoneUpdateInfo(blockposition, world.getTime()));
/*     */     }
/*     */     
/*  27 */     int i = 0;
/*     */     
/*  29 */     for (int j = 0; j < list.size(); j++) {
/*  30 */       RedstoneUpdateInfo blockredstonetorch_redstoneupdateinfo = list.get(j);
/*     */ 
/*     */       
/*  33 */       i++;
/*  34 */       if (blockredstonetorch_redstoneupdateinfo.a.equals(blockposition) && i >= 8) {
/*  35 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  40 */     return false;
/*     */   }
/*     */   
/*     */   protected BlockRedstoneTorch(boolean flag) {
/*  44 */     this.isOn = flag;
/*  45 */     a(true);
/*  46 */     a((CreativeModeTab)null);
/*     */   }
/*     */   
/*     */   public int a(World world) {
/*  50 */     return 2;
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  54 */     if (this.isOn) {
/*  55 */       EnumDirection[] aenumdirection = EnumDirection.values();
/*  56 */       int i = aenumdirection.length;
/*     */       
/*  58 */       for (int j = 0; j < i; j++) {
/*  59 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/*  61 */         world.applyPhysics(blockposition.shift(enumdirection), this, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  68 */     if (this.isOn) {
/*  69 */       EnumDirection[] aenumdirection = EnumDirection.values();
/*  70 */       int i = aenumdirection.length;
/*     */       
/*  72 */       for (int j = 0; j < i; j++) {
/*  73 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/*  75 */         world.applyPhysics(blockposition.shift(enumdirection), this, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  82 */     return (this.isOn && iblockdata.get(FACING) != enumdirection) ? 15 : 0;
/*     */   }
/*     */   
/*     */   private boolean g(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  86 */     EnumDirection enumdirection = ((EnumDirection)iblockdata.<EnumDirection>get(FACING)).opposite();
/*     */     
/*  88 */     return world.isBlockFacePowered(blockposition.shift(enumdirection), enumdirection);
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  94 */     boolean flag = g(world, blockposition, iblockdata);
/*  95 */     List list = g.get(world);
/*     */     
/*  97 */     while (list != null && !list.isEmpty() && world.getTime() - ((RedstoneUpdateInfo)list.get(0)).b > 60L) {
/*  98 */       list.remove(0);
/*     */     }
/*     */ 
/*     */     
/* 102 */     PluginManager manager = world.getServer().getPluginManager();
/* 103 */     Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 104 */     int oldCurrent = this.isOn ? 15 : 0;
/*     */     
/* 106 */     BlockRedstoneEvent event = new BlockRedstoneEvent(block, oldCurrent, oldCurrent);
/*     */ 
/*     */     
/* 109 */     if (this.isOn) {
/* 110 */       if (flag) {
/*     */         
/* 112 */         if (oldCurrent != 0) {
/* 113 */           event.setNewCurrent(0);
/* 114 */           manager.callEvent((Event)event);
/* 115 */           if (event.getNewCurrent() != 0) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */         
/* 120 */         world.setTypeAndData(blockposition, Blocks.UNLIT_REDSTONE_TORCH.getBlockData().set(FACING, iblockdata.<EnumDirection>get(FACING)), 3);
/* 121 */         if (a(world, blockposition, true)) {
/* 122 */           world.a(null, blockposition, SoundEffects.gm, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
/*     */           
/* 124 */           for (int i = 0; i < 5; i++) {
/* 125 */             double d0 = blockposition.getX() + random.nextDouble() * 0.6D + 0.2D;
/* 126 */             double d1 = blockposition.getY() + random.nextDouble() * 0.6D + 0.2D;
/* 127 */             double d2 = blockposition.getZ() + random.nextDouble() * 0.6D + 0.2D;
/*     */             
/* 129 */             world.addParticle(EnumParticle.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           } 
/*     */           
/* 132 */           world.a(blockposition, world.getType(blockposition).getBlock(), 160);
/*     */         } 
/*     */       } 
/* 135 */     } else if (!flag && !a(world, blockposition, false)) {
/*     */       
/* 137 */       if (oldCurrent != 15) {
/* 138 */         event.setNewCurrent(15);
/* 139 */         manager.callEvent((Event)event);
/* 140 */         if (event.getNewCurrent() != 15) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 145 */       world.setTypeAndData(blockposition, Blocks.REDSTONE_TORCH.getBlockData().set(FACING, iblockdata.<EnumDirection>get(FACING)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 151 */     if (!e(world, blockposition, iblockdata) && 
/* 152 */       this.isOn == g(world, blockposition, iblockdata)) {
/* 153 */       world.a(blockposition, this, a(world));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 160 */     return (enumdirection == EnumDirection.DOWN) ? iblockdata.a(iblockaccess, blockposition, enumdirection) : 0;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 164 */     return Item.getItemOf(Blocks.REDSTONE_TORCH);
/*     */   }
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 172 */     return new ItemStack(Blocks.REDSTONE_TORCH);
/*     */   }
/*     */   
/*     */   public boolean d(Block block) {
/* 176 */     return !(block != Blocks.UNLIT_REDSTONE_TORCH && block != Blocks.REDSTONE_TORCH);
/*     */   }
/*     */   
/*     */   static class RedstoneUpdateInfo
/*     */   {
/*     */     BlockPosition a;
/*     */     long b;
/*     */     
/*     */     public RedstoneUpdateInfo(BlockPosition blockposition, long i) {
/* 185 */       this.a = blockposition;
/* 186 */       this.b = i;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRedstoneTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */