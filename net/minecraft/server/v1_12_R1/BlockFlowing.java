/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockFromToEvent;
/*     */ 
/*     */ public class BlockFlowing extends BlockFluids {
/*     */   int a;
/*     */   
/*     */   protected BlockFlowing(Material material) {
/*  18 */     super(material);
/*     */   }
/*     */   
/*     */   private void f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  22 */     world.setTypeAndData(blockposition, b(this.material).getBlockData().set(LEVEL, iblockdata.<Integer>get(LEVEL)), 2);
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  26 */     int i = ((Integer)iblockdata.<Integer>get(LEVEL)).intValue();
/*  27 */     byte b0 = 1;
/*     */     
/*  29 */     if (this.material == Material.LAVA && !world.worldProvider.l()) {
/*  30 */       b0 = 2;
/*     */     }
/*     */     
/*  33 */     int j = a(world);
/*     */ 
/*     */     
/*  36 */     if (i > 0) {
/*  37 */       int l = -100;
/*     */       
/*  39 */       this.a = 0;
/*     */ 
/*     */ 
/*     */       
/*  43 */       for (Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator(); iterator.hasNext(); l = a(world, blockposition.shift(enumdirection), l)) {
/*  44 */         EnumDirection enumdirection = iterator.next();
/*     */       }
/*     */       
/*  47 */       int i1 = l + b0;
/*     */       
/*  49 */       if (i1 >= 8 || l < 0) {
/*  50 */         i1 = -1;
/*     */       }
/*     */       
/*  53 */       int k = x(world.getType(blockposition.up()));
/*  54 */       if (k >= 0) {
/*  55 */         if (k >= 8) {
/*  56 */           i1 = k;
/*     */         } else {
/*  58 */           i1 = k + 8;
/*     */         } 
/*     */       }
/*     */       
/*  62 */       if (this.a >= 2 && this.material == Material.WATER) {
/*  63 */         IBlockData iblockdata1 = world.getType(blockposition.down());
/*     */         
/*  65 */         if (iblockdata1.getMaterial().isBuildable()) {
/*  66 */           i1 = 0;
/*  67 */         } else if (iblockdata1.getMaterial() == this.material && ((Integer)iblockdata1.<Integer>get(LEVEL)).intValue() == 0) {
/*  68 */           i1 = 0;
/*     */         } 
/*     */       } 
/*     */       
/*  72 */       if (this.material == Material.LAVA && i < 8 && i1 < 8 && i1 > i && random.nextInt(4) != 0) {
/*  73 */         j *= 4;
/*     */       }
/*     */       
/*  76 */       if (i1 == i) {
/*  77 */         f(world, blockposition, iblockdata);
/*     */       } else {
/*  79 */         i = i1;
/*  80 */         if (i1 < 0) {
/*  81 */           world.setAir(blockposition);
/*     */         } else {
/*  83 */           iblockdata = iblockdata.set(LEVEL, Integer.valueOf(i1));
/*  84 */           world.setTypeAndData(blockposition, iblockdata, 2);
/*  85 */           world.a(blockposition, this, j);
/*  86 */           world.applyPhysics(blockposition, this, false);
/*     */         } 
/*     */       } 
/*     */     } else {
/*  90 */       f(world, blockposition, iblockdata);
/*     */     } 
/*     */     
/*  93 */     Block source = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  94 */     IBlockData iblockdata2 = world.getType(blockposition.down());
/*     */     
/*  96 */     if (h(world, blockposition.down(), iblockdata2)) {
/*     */       
/*  98 */       BlockFromToEvent event = new BlockFromToEvent(source, BlockFace.DOWN);
/*  99 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 101 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 106 */       if (this.material == Material.LAVA && world.getType(blockposition.down()).getMaterial() == Material.WATER) {
/*     */         
/* 108 */         if (CraftEventFactory.handleBlockFormEvent(world, blockposition.down(), Blocks.STONE.getBlockData(), null)) {
/* 109 */           fizz(world, blockposition.down());
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 115 */       if (i >= 8) {
/* 116 */         flow(world, blockposition.down(), iblockdata2, i);
/*     */       } else {
/* 118 */         flow(world, blockposition.down(), iblockdata2, i + 8);
/*     */       } 
/* 120 */     } else if (i >= 0 && (i == 0 || g(world, blockposition.down(), iblockdata2))) {
/* 121 */       Set<EnumDirection> set = c(world, blockposition);
/*     */       
/* 123 */       int k = i + b0;
/* 124 */       if (i >= 8) {
/* 125 */         k = 1;
/*     */       }
/*     */       
/* 128 */       if (k >= 8) {
/*     */         return;
/*     */       }
/*     */       
/* 132 */       Iterator<EnumDirection> iterator1 = set.iterator();
/*     */       
/* 134 */       while (iterator1.hasNext()) {
/* 135 */         EnumDirection enumdirection1 = iterator1.next();
/*     */ 
/*     */         
/* 138 */         BlockFromToEvent event = new BlockFromToEvent(source, CraftBlock.notchToBlockFace(enumdirection1));
/* 139 */         world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 141 */         if (!event.isCancelled()) {
/* 142 */           flow(world, blockposition.shift(enumdirection1), world.getType(blockposition.shift(enumdirection1)), k);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void flow(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
/* 151 */     if (world.isLoaded(blockposition) && h(world, blockposition, iblockdata)) {
/* 152 */       if (iblockdata.getMaterial() != Material.AIR) {
/* 153 */         if (this.material == Material.LAVA) {
/* 154 */           fizz(world, blockposition);
/*     */         } else {
/* 156 */           iblockdata.getBlock().b(world, blockposition, iblockdata, 0);
/*     */         } 
/*     */       }
/*     */       
/* 160 */       world.setTypeAndData(blockposition, getBlockData().set(LEVEL, Integer.valueOf(i)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int a(World world, BlockPosition blockposition, int i, EnumDirection enumdirection) {
/* 166 */     int j = 1000;
/* 167 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 169 */     while (iterator.hasNext()) {
/* 170 */       EnumDirection enumdirection1 = iterator.next();
/*     */       
/* 172 */       if (enumdirection1 != enumdirection) {
/* 173 */         BlockPosition blockposition1 = blockposition.shift(enumdirection1);
/* 174 */         IBlockData iblockdata = world.getType(blockposition1);
/*     */         
/* 176 */         if (!g(world, blockposition1, iblockdata) && (iblockdata.getMaterial() != this.material || ((Integer)iblockdata.<Integer>get(LEVEL)).intValue() > 0)) {
/* 177 */           if (!g(world, blockposition1.down(), iblockdata)) {
/* 178 */             return i;
/*     */           }
/*     */           
/* 181 */           if (i < b(world)) {
/* 182 */             int k = a(world, blockposition1, i + 1, enumdirection1.opposite());
/*     */             
/* 184 */             if (k < j) {
/* 185 */               j = k;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     return j;
/*     */   }
/*     */   
/*     */   private int b(World world) {
/* 196 */     return (this.material == Material.LAVA && !world.worldProvider.l()) ? 2 : 4;
/*     */   }
/*     */   
/*     */   private Set<EnumDirection> c(World world, BlockPosition blockposition) {
/* 200 */     int i = 1000;
/* 201 */     EnumSet<EnumDirection> enumset = EnumSet.noneOf(EnumDirection.class);
/* 202 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 204 */     while (iterator.hasNext()) {
/* 205 */       EnumDirection enumdirection = iterator.next();
/* 206 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 207 */       IBlockData iblockdata = world.getType(blockposition1);
/*     */       
/* 209 */       if (!g(world, blockposition1, iblockdata) && (iblockdata.getMaterial() != this.material || ((Integer)iblockdata.<Integer>get(LEVEL)).intValue() > 0)) {
/*     */         int j;
/*     */         
/* 212 */         if (g(world, blockposition1.down(), world.getType(blockposition1.down()))) {
/* 213 */           j = a(world, blockposition1, 1, enumdirection.opposite());
/*     */         } else {
/* 215 */           j = 0;
/*     */         } 
/*     */         
/* 218 */         if (j < i) {
/* 219 */           enumset.clear();
/*     */         }
/*     */         
/* 222 */         if (j <= i) {
/* 223 */           enumset.add(enumdirection);
/* 224 */           i = j;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     return enumset;
/*     */   }
/*     */   
/*     */   private boolean g(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 233 */     Block block = world.getType(blockposition).getBlock();
/*     */     
/* 235 */     return (!(block instanceof BlockDoor) && block != Blocks.STANDING_SIGN && block != Blocks.LADDER && block != Blocks.REEDS) ? ((block.material != Material.PORTAL && block.material != Material.J) ? block.material.isSolid() : true) : true;
/*     */   }
/*     */   
/*     */   protected int a(World world, BlockPosition blockposition, int i) {
/* 239 */     int j = x(world.getType(blockposition));
/*     */     
/* 241 */     if (j < 0) {
/* 242 */       return i;
/*     */     }
/* 244 */     if (j == 0) {
/* 245 */       this.a++;
/*     */     }
/*     */     
/* 248 */     if (j >= 8) {
/* 249 */       j = 0;
/*     */     }
/*     */     
/* 252 */     return (i >= 0 && j >= i) ? i : j;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean h(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 257 */     Material material = iblockdata.getMaterial();
/*     */     
/* 259 */     return (material != this.material && material != Material.LAVA && !g(world, blockposition, iblockdata));
/*     */   }
/*     */   
/*     */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 263 */     if (!e(world, blockposition, iblockdata))
/* 264 */       world.a(blockposition, this, a(world)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFlowing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */