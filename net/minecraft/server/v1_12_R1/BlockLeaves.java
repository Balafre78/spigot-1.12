/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.LeavesDecayEvent;
/*     */ 
/*     */ public abstract class BlockLeaves
/*     */   extends Block {
/*   9 */   public static final BlockStateBoolean DECAYABLE = BlockStateBoolean.of("decayable");
/*  10 */   public static final BlockStateBoolean CHECK_DECAY = BlockStateBoolean.of("check_decay");
/*     */   protected boolean c;
/*     */   int[] d;
/*     */   
/*     */   public BlockLeaves() {
/*  15 */     super(Material.LEAVES);
/*  16 */     a(true);
/*  17 */     a(CreativeModeTab.c);
/*  18 */     c(0.2F);
/*  19 */     e(1);
/*  20 */     a(SoundEffectType.c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  26 */     int i = blockposition.getX();
/*  27 */     int j = blockposition.getY();
/*  28 */     int k = blockposition.getZ();
/*     */     
/*  30 */     if (world.areChunksLoadedBetween(new BlockPosition(i - 2, j - 2, k - 2), new BlockPosition(i + 2, j + 2, k + 2))) {
/*  31 */       for (int l = -1; l <= 1; l++) {
/*  32 */         for (int i1 = -1; i1 <= 1; i1++) {
/*  33 */           for (int j1 = -1; j1 <= 1; j1++) {
/*  34 */             BlockPosition blockposition1 = blockposition.a(l, i1, j1);
/*  35 */             IBlockData iblockdata1 = world.getType(blockposition1);
/*     */             
/*  37 */             if (iblockdata1.getMaterial() == Material.LEAVES && !((Boolean)iblockdata1.<Boolean>get(CHECK_DECAY)).booleanValue()) {
/*  38 */               world.setTypeAndData(blockposition1, iblockdata1.set(CHECK_DECAY, Boolean.valueOf(true)), 4);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  48 */     if (!world.isClientSide && (
/*  49 */       (Boolean)iblockdata.<Boolean>get(CHECK_DECAY)).booleanValue() && ((Boolean)iblockdata.<Boolean>get(DECAYABLE)).booleanValue()) {
/*     */ 
/*     */       
/*  52 */       int i = blockposition.getX();
/*  53 */       int j = blockposition.getY();
/*  54 */       int k = blockposition.getZ();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  59 */       if (this.d == null) {
/*  60 */         this.d = new int[32768];
/*     */       }
/*     */       
/*  63 */       if (world.areChunksLoadedBetween(new BlockPosition(i - 5, j - 5, k - 5), new BlockPosition(i + 5, j + 5, k + 5))) {
/*  64 */         BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */ 
/*     */         
/*     */         int l;
/*     */ 
/*     */         
/*  70 */         for (l = -4; l <= 4; l++) {
/*  71 */           for (int i1 = -4; i1 <= 4; i1++) {
/*  72 */             for (int j1 = -4; j1 <= 4; j1++) {
/*  73 */               IBlockData iblockdata1 = world.getType(blockposition_mutableblockposition.c(i + l, j + i1, k + j1));
/*  74 */               Block block = iblockdata1.getBlock();
/*     */               
/*  76 */               if (block != Blocks.LOG && block != Blocks.LOG2) {
/*  77 */                 if (iblockdata1.getMaterial() == Material.LEAVES) {
/*  78 */                   this.d[(l + 16) * 1024 + (i1 + 16) * 32 + j1 + 16] = -2;
/*     */                 } else {
/*  80 */                   this.d[(l + 16) * 1024 + (i1 + 16) * 32 + j1 + 16] = -1;
/*     */                 } 
/*     */               } else {
/*  83 */                 this.d[(l + 16) * 1024 + (i1 + 16) * 32 + j1 + 16] = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*  89 */         for (l = 1; l <= 4; l++) {
/*  90 */           for (int i1 = -4; i1 <= 4; i1++) {
/*  91 */             for (int j1 = -4; j1 <= 4; j1++) {
/*  92 */               for (int k1 = -4; k1 <= 4; k1++) {
/*  93 */                 if (this.d[(i1 + 16) * 1024 + (j1 + 16) * 32 + k1 + 16] == l - 1) {
/*  94 */                   if (this.d[(i1 + 16 - 1) * 1024 + (j1 + 16) * 32 + k1 + 16] == -2) {
/*  95 */                     this.d[(i1 + 16 - 1) * 1024 + (j1 + 16) * 32 + k1 + 16] = l;
/*     */                   }
/*     */                   
/*  98 */                   if (this.d[(i1 + 16 + 1) * 1024 + (j1 + 16) * 32 + k1 + 16] == -2) {
/*  99 */                     this.d[(i1 + 16 + 1) * 1024 + (j1 + 16) * 32 + k1 + 16] = l;
/*     */                   }
/*     */                   
/* 102 */                   if (this.d[(i1 + 16) * 1024 + (j1 + 16 - 1) * 32 + k1 + 16] == -2) {
/* 103 */                     this.d[(i1 + 16) * 1024 + (j1 + 16 - 1) * 32 + k1 + 16] = l;
/*     */                   }
/*     */                   
/* 106 */                   if (this.d[(i1 + 16) * 1024 + (j1 + 16 + 1) * 32 + k1 + 16] == -2) {
/* 107 */                     this.d[(i1 + 16) * 1024 + (j1 + 16 + 1) * 32 + k1 + 16] = l;
/*     */                   }
/*     */                   
/* 110 */                   if (this.d[(i1 + 16) * 1024 + (j1 + 16) * 32 + k1 + 16 - 1] == -2) {
/* 111 */                     this.d[(i1 + 16) * 1024 + (j1 + 16) * 32 + k1 + 16 - 1] = l;
/*     */                   }
/*     */                   
/* 114 */                   if (this.d[(i1 + 16) * 1024 + (j1 + 16) * 32 + k1 + 16 + 1] == -2) {
/* 115 */                     this.d[(i1 + 16) * 1024 + (j1 + 16) * 32 + k1 + 16 + 1] = l;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 124 */       int l1 = this.d[16912];
/*     */       
/* 126 */       if (l1 >= 0) {
/* 127 */         world.setTypeAndData(blockposition, iblockdata.set(CHECK_DECAY, Boolean.valueOf(false)), 4);
/*     */       } else {
/* 129 */         b(world, blockposition);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(World world, BlockPosition blockposition) {
/* 138 */     LeavesDecayEvent event = new LeavesDecayEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 139 */     world.getServer().getPluginManager().callEvent((Event)event);
/*     */     
/* 141 */     if (event.isCancelled() || world.getType(blockposition).getBlock() != this) {
/*     */       return;
/*     */     }
/*     */     
/* 145 */     b(world, blockposition, world.getType(blockposition), 0);
/* 146 */     world.setAir(blockposition);
/*     */   }
/*     */   
/*     */   public int a(Random random) {
/* 150 */     return (random.nextInt(20) == 0) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 154 */     return Item.getItemOf(Blocks.SAPLING);
/*     */   }
/*     */   
/*     */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/* 158 */     if (!world.isClientSide) {
/* 159 */       int j = x(iblockdata);
/*     */       
/* 161 */       if (i > 0) {
/* 162 */         j -= 2 << i;
/* 163 */         if (j < 10) {
/* 164 */           j = 10;
/*     */         }
/*     */       } 
/*     */       
/* 168 */       if (world.random.nextInt(j) == 0) {
/* 169 */         Item item = getDropType(iblockdata, world.random, i);
/*     */         
/* 171 */         a(world, blockposition, new ItemStack(item, 1, getDropData(iblockdata)));
/*     */       } 
/*     */       
/* 174 */       j = 200;
/* 175 */       if (i > 0) {
/* 176 */         j -= 10 << i;
/* 177 */         if (j < 40) {
/* 178 */           j = 40;
/*     */         }
/*     */       } 
/*     */       
/* 182 */       a(world, blockposition, iblockdata, j);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {}
/*     */   
/*     */   protected int x(IBlockData iblockdata) {
/* 190 */     return 20;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/* 194 */     return !this.c;
/*     */   }
/*     */   
/*     */   public boolean t(IBlockData iblockdata) {
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   public abstract BlockWood.EnumLogVariant b(int paramInt);
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */