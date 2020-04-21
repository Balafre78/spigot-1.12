/*      */ package net.minecraft.server.v1_12_R1;
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import gnu.trove.map.hash.TObjectIntHashMap;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*      */ import org.bukkit.entity.HumanEntity;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.world.ChunkLoadEvent;
/*      */ import org.bukkit.generator.BlockPopulator;
/*      */ 
/*      */ public class Chunk {
/*   22 */   private static final Logger e = LogManager.getLogger();
/*   23 */   public static final ChunkSection a = null;
/*      */   private final ChunkSection[] sections;
/*      */   private final byte[] g;
/*      */   private final int[] h;
/*      */   private final boolean[] i;
/*      */   private boolean j;
/*      */   public final World world;
/*      */   public final int[] heightMap;
/*      */   public final int locX;
/*      */   public final int locZ;
/*      */   private boolean m;
/*      */   public final Map<BlockPosition, TileEntity> tileEntities;
/*      */   public final List<Entity>[] entitySlices;
/*      */   private boolean done;
/*      */   private boolean lit;
/*      */   private boolean r;
/*      */   private boolean s;
/*      */   private boolean t;
/*      */   private long lastSaved;
/*      */   private int v;
/*      */   private long w;
/*      */   private int x;
/*      */   private final ConcurrentLinkedQueue<BlockPosition> y;
/*      */   public boolean d;
/*   47 */   protected TObjectIntHashMap<Class> entityCount = new TObjectIntHashMap();
/*      */ 
/*      */   
/*   50 */   private int neighbors = 4096; public long chunkKey; public org.bukkit.Chunk bukkitChunk;
/*      */   public boolean mustSave;
/*      */   
/*      */   public boolean areNeighborsLoaded(int radius) {
/*   54 */     switch (radius) {
/*      */       case 2:
/*   56 */         return (this.neighbors == 33554431);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*   63 */         return ((this.neighbors & 0x739C0) == 473536);
/*      */     } 
/*   65 */     throw new UnsupportedOperationException(String.valueOf(radius));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNeighborLoaded(int x, int z) {
/*   70 */     this.neighbors |= 1 << x * 5 + 12 + z;
/*      */   }
/*      */   
/*      */   public void setNeighborUnloaded(int x, int z) {
/*   74 */     this.neighbors &= 1 << x * 5 + 12 + z ^ 0xFFFFFFFF;
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk(World world, int i, int j) {
/*   79 */     this.sections = new ChunkSection[16];
/*   80 */     this.g = new byte[256];
/*   81 */     this.h = new int[256];
/*   82 */     this.i = new boolean[256];
/*   83 */     this.tileEntities = Maps.newHashMap();
/*   84 */     this.x = 4096;
/*   85 */     this.y = Queues.newConcurrentLinkedQueue();
/*   86 */     this.entitySlices = (List<Entity>[])new List[16];
/*   87 */     this.world = world;
/*   88 */     this.locX = i;
/*   89 */     this.locZ = j;
/*   90 */     this.heightMap = new int[256];
/*      */     
/*   92 */     for (int k = 0; k < this.entitySlices.length; k++) {
/*   93 */       this.entitySlices[k] = (List<Entity>)new UnsafeList();
/*      */     }
/*      */     
/*   96 */     Arrays.fill(this.h, -999);
/*   97 */     Arrays.fill(this.g, (byte)-1);
/*      */     
/*   99 */     this.bukkitChunk = (org.bukkit.Chunk)new CraftChunk(this);
/*  100 */     this.chunkKey = ChunkCoordIntPair.a(this.locX, this.locZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk(World world, ChunkSnapshot chunksnapshot, int i, int j) {
/*  108 */     this(world, i, j);
/*      */     
/*  110 */     boolean flag1 = world.worldProvider.m();
/*      */     
/*  112 */     for (int k = 0; k < 16; k++) {
/*  113 */       for (int l = 0; l < 16; l++) {
/*  114 */         for (int i1 = 0; i1 < 256; i1++) {
/*  115 */           IBlockData iblockdata = chunksnapshot.a(k, i1, l);
/*      */           
/*  117 */           if (iblockdata.getMaterial() != Material.AIR) {
/*  118 */             int j1 = i1 >> 4;
/*      */             
/*  120 */             if (this.sections[j1] == a) {
/*  121 */               this.sections[j1] = new ChunkSection(j1 << 4, flag1);
/*      */             }
/*      */             
/*  124 */             this.sections[j1].setType(k, i1 & 0xF, l, iblockdata);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(int i, int j) {
/*  133 */     return (i == this.locX && j == this.locZ);
/*      */   }
/*      */   
/*      */   public int e(BlockPosition blockposition) {
/*  137 */     return b(blockposition.getX() & 0xF, blockposition.getZ() & 0xF);
/*      */   }
/*      */   
/*      */   public int b(int i, int j) {
/*  141 */     return this.heightMap[j << 4 | i];
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   private ChunkSection y() {
/*  146 */     for (int i = this.sections.length - 1; i >= 0; i--) {
/*  147 */       if (this.sections[i] != a) {
/*  148 */         return this.sections[i];
/*      */       }
/*      */     } 
/*      */     
/*  152 */     return null;
/*      */   }
/*      */   
/*      */   public int g() {
/*  156 */     ChunkSection chunksection = y();
/*      */     
/*  158 */     return (chunksection == null) ? 0 : chunksection.getYPosition();
/*      */   }
/*      */   
/*      */   public ChunkSection[] getSections() {
/*  162 */     return this.sections;
/*      */   }
/*      */   
/*      */   public void initLighting() {
/*  166 */     int i = g();
/*      */     
/*  168 */     this.v = Integer.MAX_VALUE;
/*      */     
/*  170 */     for (int j = 0; j < 16; j++) {
/*  171 */       int k = 0;
/*      */       
/*  173 */       while (k < 16) {
/*  174 */         this.h[j + (k << 4)] = -999;
/*  175 */         int l = i + 16;
/*      */ 
/*      */         
/*  178 */         while (l > 0) {
/*  179 */           if (d(j, l - 1, k) == 0) {
/*  180 */             l--;
/*      */             
/*      */             continue;
/*      */           } 
/*  184 */           this.heightMap[k << 4 | j] = l;
/*  185 */           if (l < this.v) {
/*  186 */             this.v = l;
/*      */           }
/*      */         } 
/*      */         
/*  190 */         if (this.world.worldProvider.m()) {
/*  191 */           l = 15;
/*  192 */           int i1 = i + 16 - 1;
/*      */           
/*      */           do {
/*  195 */             int j1 = d(j, i1, k);
/*      */             
/*  197 */             if (j1 == 0 && l != 15) {
/*  198 */               j1 = 1;
/*      */             }
/*      */             
/*  201 */             l -= j1;
/*  202 */             if (l <= 0)
/*  203 */               continue;  ChunkSection chunksection = this.sections[i1 >> 4];
/*      */             
/*  205 */             if (chunksection == a)
/*  206 */               continue;  chunksection.a(j, i1 & 0xF, k, l);
/*  207 */             this.world.m(new BlockPosition((this.locX << 4) + j, i1, (this.locZ << 4) + k));
/*      */ 
/*      */ 
/*      */             
/*  211 */             --i1;
/*  212 */           } while (i1 > 0 && l > 0);
/*      */         } 
/*      */         
/*  215 */         k++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  221 */     this.s = true;
/*      */   }
/*      */   
/*      */   private void d(int i, int j) {
/*  225 */     this.i[i + j * 16] = true;
/*  226 */     this.m = true;
/*      */   }
/*      */   
/*      */   private void h(boolean flag) {
/*  230 */     this.world.methodProfiler.a("recheckGaps");
/*  231 */     if (this.world.areChunksLoaded(new BlockPosition(this.locX * 16 + 8, 0, this.locZ * 16 + 8), 16)) {
/*  232 */       for (int i = 0; i < 16; i++) {
/*  233 */         for (int j = 0; j < 16; j++) {
/*  234 */           if (this.i[i + j * 16]) {
/*  235 */             this.i[i + j * 16] = false;
/*  236 */             int k = b(i, j);
/*  237 */             int l = this.locX * 16 + i;
/*  238 */             int i1 = this.locZ * 16 + j;
/*  239 */             int j1 = Integer.MAX_VALUE;
/*      */ 
/*      */             
/*      */             Iterator<EnumDirection> iterator;
/*      */             
/*  244 */             for (iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator(); iterator.hasNext(); j1 = Math.min(j1, this.world.d(l + enumdirection.getAdjacentX(), i1 + enumdirection.getAdjacentZ()))) {
/*  245 */               EnumDirection enumdirection = iterator.next();
/*      */             }
/*      */             
/*  248 */             b(l, i1, j1);
/*  249 */             iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*      */             
/*  251 */             while (iterator.hasNext()) {
/*  252 */               EnumDirection enumdirection = iterator.next();
/*  253 */               b(l + enumdirection.getAdjacentX(), i1 + enumdirection.getAdjacentZ(), k);
/*      */             } 
/*      */             
/*  256 */             if (flag) {
/*  257 */               this.world.methodProfiler.b();
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  264 */       this.m = false;
/*      */     } 
/*      */     
/*  267 */     this.world.methodProfiler.b();
/*      */   }
/*      */   
/*      */   private void b(int i, int j, int k) {
/*  271 */     int l = this.world.getHighestBlockYAt(new BlockPosition(i, 0, j)).getY();
/*      */     
/*  273 */     if (l > k) {
/*  274 */       a(i, j, k, l + 1);
/*  275 */     } else if (l < k) {
/*  276 */       a(i, j, l, k + 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(int i, int j, int k, int l) {
/*  282 */     if (l > k && this.world.areChunksLoaded(new BlockPosition(i, 0, j), 16)) {
/*  283 */       for (int i1 = k; i1 < l; i1++) {
/*  284 */         this.world.c(EnumSkyBlock.SKY, new BlockPosition(i, i1, j));
/*      */       }
/*      */       
/*  287 */       this.s = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void c(int i, int j, int k) {
/*  293 */     int l = this.heightMap[k << 4 | i] & 0xFF;
/*  294 */     int i1 = l;
/*      */     
/*  296 */     if (j > l) {
/*  297 */       i1 = j;
/*      */     }
/*      */     
/*  300 */     while (i1 > 0 && d(i, i1 - 1, k) == 0) {
/*  301 */       i1--;
/*      */     }
/*      */     
/*  304 */     if (i1 != l) {
/*  305 */       this.world.a(i + this.locX * 16, k + this.locZ * 16, i1, l);
/*  306 */       this.heightMap[k << 4 | i] = i1;
/*  307 */       int j1 = this.locX * 16 + i;
/*  308 */       int k1 = this.locZ * 16 + k;
/*      */ 
/*      */ 
/*      */       
/*  312 */       if (this.world.worldProvider.m()) {
/*      */ 
/*      */         
/*  315 */         if (i1 < l) {
/*  316 */           for (int n = i1; n < l; n++) {
/*  317 */             ChunkSection chunksection = this.sections[n >> 4];
/*  318 */             if (chunksection != a) {
/*  319 */               chunksection.a(i, n & 0xF, k, 15);
/*  320 */               this.world.m(new BlockPosition((this.locX << 4) + i, n, (this.locZ << 4) + k));
/*      */             } 
/*      */           } 
/*      */         } else {
/*  324 */           for (int n = l; n < i1; n++) {
/*  325 */             ChunkSection chunksection = this.sections[n >> 4];
/*  326 */             if (chunksection != a) {
/*  327 */               chunksection.a(i, n & 0xF, k, 0);
/*  328 */               this.world.m(new BlockPosition((this.locX << 4) + i, n, (this.locZ << 4) + k));
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  333 */         int m = 15;
/*      */         
/*  335 */         while (i1 > 0 && m > 0) {
/*  336 */           i1--;
/*  337 */           int n = d(i, i1, k);
/*  338 */           if (n == 0) {
/*  339 */             n = 1;
/*      */           }
/*      */           
/*  342 */           m -= n;
/*  343 */           if (m < 0) {
/*  344 */             m = 0;
/*      */           }
/*      */           
/*  347 */           ChunkSection chunksection1 = this.sections[i1 >> 4];
/*      */           
/*  349 */           if (chunksection1 != a) {
/*  350 */             chunksection1.a(i, i1 & 0xF, k, m);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  355 */       int l1 = this.heightMap[k << 4 | i];
/*  356 */       int i2 = l;
/*  357 */       int j2 = l1;
/*      */       
/*  359 */       if (l1 < l) {
/*  360 */         i2 = l1;
/*  361 */         j2 = l;
/*      */       } 
/*      */       
/*  364 */       if (l1 < this.v) {
/*  365 */         this.v = l1;
/*      */       }
/*      */       
/*  368 */       if (this.world.worldProvider.m()) {
/*  369 */         Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*      */         
/*  371 */         while (iterator.hasNext()) {
/*  372 */           EnumDirection enumdirection = iterator.next();
/*      */           
/*  374 */           a(j1 + enumdirection.getAdjacentX(), k1 + enumdirection.getAdjacentZ(), i2, j2);
/*      */         } 
/*      */         
/*  377 */         a(j1, k1, i2, j2);
/*      */       } 
/*      */       
/*  380 */       this.s = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int b(BlockPosition blockposition) {
/*  385 */     return getBlockData(blockposition).c();
/*      */   }
/*      */   
/*      */   private int d(int i, int j, int k) {
/*  389 */     return a(i, j, k).c();
/*      */   }
/*      */   
/*      */   public IBlockData getBlockData(BlockPosition blockposition) {
/*  393 */     return a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*      */   }
/*      */   
/*      */   public IBlockData a(final int i, final int j, final int k) {
/*  397 */     if (this.world.N() == WorldType.DEBUG_ALL_BLOCK_STATES) {
/*  398 */       IBlockData iblockdata = null;
/*      */       
/*  400 */       if (j == 60) {
/*  401 */         iblockdata = Blocks.BARRIER.getBlockData();
/*      */       }
/*      */       
/*  404 */       if (j == 70) {
/*  405 */         iblockdata = ChunkProviderDebug.c(i, k);
/*      */       }
/*      */       
/*  408 */       return (iblockdata == null) ? Blocks.AIR.getBlockData() : iblockdata;
/*      */     } 
/*      */     try {
/*  411 */       if (j >= 0 && j >> 4 < this.sections.length) {
/*  412 */         ChunkSection chunksection = this.sections[j >> 4];
/*      */         
/*  414 */         if (chunksection != a) {
/*  415 */           return chunksection.getType(i & 0xF, j & 0xF, k & 0xF);
/*      */         }
/*      */       } 
/*      */       
/*  419 */       return Blocks.AIR.getBlockData();
/*  420 */     } catch (Throwable throwable) {
/*  421 */       CrashReport crashreport = CrashReport.a(throwable, "Getting block state");
/*  422 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Block being got");
/*      */       
/*  424 */       crashreportsystemdetails.a("Location", new CrashReportCallable<String>() {
/*      */             public String a() throws Exception {
/*  426 */               return CrashReportSystemDetails.a(i, j, k);
/*      */             }
/*      */             
/*      */             public Object call() throws Exception {
/*  430 */               return a();
/*      */             }
/*      */           });
/*  433 */       throw new ReportedException(crashreport);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public IBlockData a(BlockPosition blockposition, IBlockData iblockdata) {
/*  440 */     int i = blockposition.getX() & 0xF;
/*  441 */     int j = blockposition.getY();
/*  442 */     int k = blockposition.getZ() & 0xF;
/*  443 */     int l = k << 4 | i;
/*      */     
/*  445 */     if (j >= this.h[l] - 1) {
/*  446 */       this.h[l] = -999;
/*      */     }
/*      */     
/*  449 */     int i1 = this.heightMap[l];
/*  450 */     IBlockData iblockdata1 = getBlockData(blockposition);
/*      */     
/*  452 */     if (iblockdata1 == iblockdata) {
/*  453 */       return null;
/*      */     }
/*  455 */     Block block = iblockdata.getBlock();
/*  456 */     Block block1 = iblockdata1.getBlock();
/*  457 */     ChunkSection chunksection = this.sections[j >> 4];
/*  458 */     boolean flag = false;
/*      */     
/*  460 */     if (chunksection == a) {
/*  461 */       if (block == Blocks.AIR) {
/*  462 */         return null;
/*      */       }
/*      */       
/*  465 */       chunksection = new ChunkSection(j >> 4 << 4, this.world.worldProvider.m());
/*  466 */       this.sections[j >> 4] = chunksection;
/*  467 */       flag = (j >= i1);
/*      */     } 
/*      */     
/*  470 */     chunksection.setType(i, j & 0xF, k, iblockdata);
/*  471 */     if (block1 != block) {
/*  472 */       if (!this.world.isClientSide) {
/*  473 */         block1.remove(this.world, blockposition, iblockdata1);
/*  474 */       } else if (block1 instanceof ITileEntity) {
/*  475 */         this.world.s(blockposition);
/*      */       } 
/*      */     }
/*      */     
/*  479 */     if (chunksection.getType(i, j & 0xF, k).getBlock() != block) {
/*  480 */       return null;
/*      */     }
/*  482 */     if (flag) {
/*  483 */       initLighting();
/*      */     } else {
/*  485 */       int j1 = iblockdata.c();
/*  486 */       int k1 = iblockdata1.c();
/*      */       
/*  488 */       if (j1 > 0) {
/*  489 */         if (j >= i1) {
/*  490 */           c(i, j + 1, k);
/*      */         }
/*  492 */       } else if (j == i1 - 1) {
/*  493 */         c(i, j, k);
/*      */       } 
/*      */       
/*  496 */       if (j1 != k1 && (j1 < k1 || getBrightness(EnumSkyBlock.SKY, blockposition) > 0 || getBrightness(EnumSkyBlock.BLOCK, blockposition) > 0)) {
/*  497 */         d(i, k);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  503 */     if (block1 instanceof ITileEntity) {
/*  504 */       TileEntity tileentity = a(blockposition, EnumTileEntityState.CHECK);
/*  505 */       if (tileentity != null) {
/*  506 */         tileentity.invalidateBlockCache();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  511 */     if (!this.world.isClientSide && block1 != block && (!this.world.captureBlockStates || block instanceof BlockTileEntity)) {
/*  512 */       block.onPlace(this.world, blockposition, iblockdata);
/*      */     }
/*      */     
/*  515 */     if (block instanceof ITileEntity) {
/*  516 */       TileEntity tileentity = a(blockposition, EnumTileEntityState.CHECK);
/*  517 */       if (tileentity == null) {
/*  518 */         tileentity = ((ITileEntity)block).a(this.world, block.toLegacyData(iblockdata));
/*  519 */         this.world.setTileEntity(blockposition, tileentity);
/*      */       } 
/*      */       
/*  522 */       if (tileentity != null) {
/*  523 */         tileentity.invalidateBlockCache();
/*      */       }
/*      */     } 
/*      */     
/*  527 */     this.s = true;
/*  528 */     return iblockdata1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBrightness(EnumSkyBlock enumskyblock, BlockPosition blockposition) {
/*  534 */     int i = blockposition.getX() & 0xF;
/*  535 */     int j = blockposition.getY();
/*  536 */     int k = blockposition.getZ() & 0xF;
/*  537 */     ChunkSection chunksection = this.sections[j >> 4];
/*      */     
/*  539 */     return (chunksection == a) ? (c(blockposition) ? enumskyblock.c : 0) : ((enumskyblock == EnumSkyBlock.SKY) ? (!this.world.worldProvider.m() ? 0 : chunksection.b(i, j & 0xF, k)) : ((enumskyblock == EnumSkyBlock.BLOCK) ? chunksection.c(i, j & 0xF, k) : enumskyblock.c));
/*      */   }
/*      */   
/*      */   public void a(EnumSkyBlock enumskyblock, BlockPosition blockposition, int i) {
/*  543 */     int j = blockposition.getX() & 0xF;
/*  544 */     int k = blockposition.getY();
/*  545 */     int l = blockposition.getZ() & 0xF;
/*  546 */     ChunkSection chunksection = this.sections[k >> 4];
/*      */     
/*  548 */     if (chunksection == a) {
/*  549 */       chunksection = new ChunkSection(k >> 4 << 4, this.world.worldProvider.m());
/*  550 */       this.sections[k >> 4] = chunksection;
/*  551 */       initLighting();
/*      */     } 
/*      */     
/*  554 */     this.s = true;
/*  555 */     if (enumskyblock == EnumSkyBlock.SKY) {
/*  556 */       if (this.world.worldProvider.m()) {
/*  557 */         chunksection.a(j, k & 0xF, l, i);
/*      */       }
/*  559 */     } else if (enumskyblock == EnumSkyBlock.BLOCK) {
/*  560 */       chunksection.b(j, k & 0xF, l, i);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int a(BlockPosition blockposition, int i) {
/*  566 */     int j = blockposition.getX() & 0xF;
/*  567 */     int k = blockposition.getY();
/*  568 */     int l = blockposition.getZ() & 0xF;
/*  569 */     ChunkSection chunksection = this.sections[k >> 4];
/*      */     
/*  571 */     if (chunksection == a) {
/*  572 */       return (this.world.worldProvider.m() && i < EnumSkyBlock.SKY.c) ? (EnumSkyBlock.SKY.c - i) : 0;
/*      */     }
/*  574 */     int i1 = !this.world.worldProvider.m() ? 0 : chunksection.b(j, k & 0xF, l);
/*      */     
/*  576 */     i1 -= i;
/*  577 */     int j1 = chunksection.c(j, k & 0xF, l);
/*      */     
/*  579 */     if (j1 > i1) {
/*  580 */       i1 = j1;
/*      */     }
/*      */     
/*  583 */     return i1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(Entity entity) {
/*  588 */     this.t = true;
/*  589 */     int i = MathHelper.floor(entity.locX / 16.0D);
/*  590 */     int j = MathHelper.floor(entity.locZ / 16.0D);
/*      */     
/*  592 */     if (i != this.locX || j != this.locZ) {
/*  593 */       e.warn("Wrong location! ({}, {}) should be ({}, {}), {}", Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(this.locX), Integer.valueOf(this.locZ), entity);
/*  594 */       entity.die();
/*      */     } 
/*      */     
/*  597 */     int k = MathHelper.floor(entity.locY / 16.0D);
/*      */     
/*  599 */     if (k < 0) {
/*  600 */       k = 0;
/*      */     }
/*      */     
/*  603 */     if (k >= this.entitySlices.length) {
/*  604 */       k = this.entitySlices.length - 1;
/*      */     }
/*      */     
/*  607 */     entity.aa = true;
/*  608 */     entity.ab = this.locX;
/*  609 */     entity.ac = k;
/*  610 */     entity.ad = this.locZ;
/*  611 */     this.entitySlices[k].add(entity);
/*      */ 
/*      */     
/*  614 */     if (entity instanceof EntityInsentient) {
/*  615 */       EntityInsentient entityinsentient = (EntityInsentient)entity;
/*  616 */       if (entityinsentient.isTypeNotPersistent() && entityinsentient.isPersistent())
/*      */         return; 
/*      */     }  EnumCreatureType[] arrayOfEnumCreatureType; int m;
/*      */     byte b;
/*  620 */     for (m = (arrayOfEnumCreatureType = EnumCreatureType.values()).length, b = 0; b < m; ) { EnumCreatureType creatureType = arrayOfEnumCreatureType[b];
/*      */       
/*  622 */       if (creatureType.a().isAssignableFrom(entity.getClass()))
/*      */       {
/*  624 */         this.entityCount.adjustOrPutValue(creatureType.a(), 1, 1);
/*      */       }
/*      */       b++; }
/*      */   
/*      */   }
/*      */   
/*      */   public void b(Entity entity) {
/*  631 */     a(entity, entity.ac);
/*      */   }
/*      */   
/*      */   public void a(Entity entity, int i) {
/*  635 */     if (i < 0) {
/*  636 */       i = 0;
/*      */     }
/*      */     
/*  639 */     if (i >= this.entitySlices.length) {
/*  640 */       i = this.entitySlices.length - 1;
/*      */     }
/*      */     
/*  643 */     this.entitySlices[i].remove(entity);
/*      */ 
/*      */     
/*  646 */     if (entity instanceof EntityInsentient) {
/*  647 */       EntityInsentient entityinsentient = (EntityInsentient)entity;
/*  648 */       if (entityinsentient.isTypeNotPersistent() && entityinsentient.isPersistent())
/*      */         return; 
/*      */     }  EnumCreatureType[] arrayOfEnumCreatureType; int j;
/*      */     byte b;
/*  652 */     for (j = (arrayOfEnumCreatureType = EnumCreatureType.values()).length, b = 0; b < j; ) { EnumCreatureType creatureType = arrayOfEnumCreatureType[b];
/*      */       
/*  654 */       if (creatureType.a().isAssignableFrom(entity.getClass()))
/*      */       {
/*  656 */         this.entityCount.adjustValue(creatureType.a(), -1);
/*      */       }
/*      */       b++; }
/*      */   
/*      */   }
/*      */   
/*      */   public boolean c(BlockPosition blockposition) {
/*  663 */     int i = blockposition.getX() & 0xF;
/*  664 */     int j = blockposition.getY();
/*  665 */     int k = blockposition.getZ() & 0xF;
/*      */     
/*  667 */     return (j >= this.heightMap[k << 4 | i]);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   private TileEntity g(BlockPosition blockposition) {
/*  672 */     IBlockData iblockdata = getBlockData(blockposition);
/*  673 */     Block block = iblockdata.getBlock();
/*      */     
/*  675 */     return !block.isTileEntity() ? null : ((ITileEntity)block).a(this.world, iblockdata.getBlock().toLegacyData(iblockdata));
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public TileEntity a(BlockPosition blockposition, EnumTileEntityState chunk_enumtileentitystate) {
/*  681 */     TileEntity tileentity = null;
/*  682 */     if (this.world.captureBlockStates) {
/*  683 */       tileentity = this.world.capturedTileEntities.get(blockposition);
/*      */     }
/*  685 */     if (tileentity == null) {
/*  686 */       tileentity = this.tileEntities.get(blockposition);
/*      */     }
/*      */ 
/*      */     
/*  690 */     if (tileentity == null) {
/*  691 */       if (chunk_enumtileentitystate == EnumTileEntityState.IMMEDIATE) {
/*  692 */         tileentity = g(blockposition);
/*  693 */         this.world.setTileEntity(blockposition, tileentity);
/*  694 */       } else if (chunk_enumtileentitystate == EnumTileEntityState.QUEUED) {
/*  695 */         this.y.add(blockposition);
/*      */       } 
/*  697 */     } else if (tileentity.y()) {
/*  698 */       this.tileEntities.remove(blockposition);
/*  699 */       return null;
/*      */     } 
/*      */     
/*  702 */     return tileentity;
/*      */   }
/*      */   
/*      */   public void a(TileEntity tileentity) {
/*  706 */     a(tileentity.getPosition(), tileentity);
/*  707 */     if (this.j) {
/*  708 */       this.world.a(tileentity);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(BlockPosition blockposition, TileEntity tileentity) {
/*  714 */     tileentity.a(this.world);
/*  715 */     tileentity.setPosition(blockposition);
/*  716 */     if (getBlockData(blockposition).getBlock() instanceof ITileEntity) {
/*  717 */       if (this.tileEntities.containsKey(blockposition)) {
/*  718 */         ((TileEntity)this.tileEntities.get(blockposition)).z();
/*      */       }
/*      */       
/*  721 */       tileentity.A();
/*  722 */       this.tileEntities.put(blockposition, tileentity);
/*      */     } else {
/*      */       
/*  725 */       System.out.println("Attempted to place a tile entity (" + tileentity + ") at " + tileentity.position.getX() + "," + tileentity.position.getY() + "," + tileentity.position.getZ() + 
/*  726 */           " (" + CraftMagicNumbers.getMaterial(getBlockData(blockposition).getBlock()) + ") where there was no entity tile!");
/*  727 */       System.out.println("Chunk coordinates: " + (this.locX * 16) + "," + (this.locZ * 16));
/*  728 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void d(BlockPosition blockposition) {
/*  734 */     if (this.j) {
/*  735 */       TileEntity tileentity = this.tileEntities.remove(blockposition);
/*      */       
/*  737 */       if (tileentity != null) {
/*  738 */         tileentity.z();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void addEntities() {
/*  745 */     this.j = true;
/*  746 */     this.world.b(this.tileEntities.values());
/*  747 */     List<Entity>[] arrayOfList = this.entitySlices;
/*  748 */     int i = arrayOfList.length;
/*      */     
/*  750 */     for (int j = 0; j < i; j++) {
/*  751 */       List<Entity> entityslice = arrayOfList[j];
/*      */       
/*  753 */       this.world.a(entityslice);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeEntities() {
/*  759 */     this.j = false;
/*  760 */     Iterator<TileEntity> iterator = this.tileEntities.values().iterator();
/*      */     
/*  762 */     while (iterator.hasNext()) {
/*  763 */       TileEntity tileentity = iterator.next();
/*      */       
/*  765 */       if (tileentity instanceof IInventory)
/*      */       {
/*  767 */         for (HumanEntity h : Lists.newArrayList(((IInventory)tileentity).getViewers())) {
/*      */           
/*  769 */           if (h instanceof CraftHumanEntity)
/*      */           {
/*  771 */             ((CraftHumanEntity)h).getHandle().closeInventory();
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  777 */       this.world.b(tileentity);
/*      */     } 
/*      */     
/*  780 */     List<Entity>[] arrayOfList = this.entitySlices;
/*  781 */     int i = arrayOfList.length;
/*      */     
/*  783 */     for (int j = 0; j < i; j++) {
/*      */       
/*  785 */       List<Entity> newList = Lists.newArrayList(arrayOfList[j]);
/*  786 */       Iterator<Entity> iter = newList.iterator();
/*  787 */       while (iter.hasNext()) {
/*  788 */         Entity entity = iter.next();
/*      */         
/*  790 */         if (entity instanceof IInventory)
/*      */         {
/*  792 */           for (HumanEntity h : Lists.newArrayList(((IInventory)entity).getViewers())) {
/*      */             
/*  794 */             if (h instanceof CraftHumanEntity)
/*      */             {
/*  796 */               ((CraftHumanEntity)h).getHandle().closeInventory();
/*      */             }
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  804 */         if (entity instanceof EntityPlayer) {
/*  805 */           iter.remove();
/*      */         }
/*      */       } 
/*      */       
/*  809 */       this.world.c(newList);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void markDirty() {
/*  816 */     this.s = true;
/*      */   }
/*      */   
/*      */   public void a(@Nullable Entity entity, AxisAlignedBB axisalignedbb, List<Entity> list, Predicate<? super Entity> predicate) {
/*  820 */     int i = MathHelper.floor((axisalignedbb.b - 2.0D) / 16.0D);
/*  821 */     int j = MathHelper.floor((axisalignedbb.e + 2.0D) / 16.0D);
/*      */     
/*  823 */     i = MathHelper.clamp(i, 0, this.entitySlices.length - 1);
/*  824 */     j = MathHelper.clamp(j, 0, this.entitySlices.length - 1);
/*      */     
/*  826 */     for (int k = i; k <= j; k++) {
/*  827 */       if (!this.entitySlices[k].isEmpty()) {
/*  828 */         Iterator<Entity> iterator = this.entitySlices[k].iterator();
/*      */         
/*  830 */         while (iterator.hasNext()) {
/*  831 */           Entity entity1 = iterator.next();
/*      */           
/*  833 */           if (entity1.getBoundingBox().c(axisalignedbb) && entity1 != entity) {
/*  834 */             if (predicate == null || predicate.apply(entity1)) {
/*  835 */               list.add(entity1);
/*      */             }
/*      */             
/*  838 */             Entity[] aentity = entity1.bb();
/*      */             
/*  840 */             if (aentity != null) {
/*  841 */               Entity[] aentity1 = aentity;
/*  842 */               int l = aentity.length;
/*      */               
/*  844 */               for (int i1 = 0; i1 < l; i1++) {
/*  845 */                 Entity entity2 = aentity1[i1];
/*      */                 
/*  847 */                 if (entity2 != entity && entity2.getBoundingBox().c(axisalignedbb) && (predicate == null || predicate.apply(entity2))) {
/*  848 */                   list.add(entity2);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends Entity> void a(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, List<T> list, Predicate<? super T> predicate) {
/*  860 */     int i = MathHelper.floor((axisalignedbb.b - 2.0D) / 16.0D);
/*  861 */     int j = MathHelper.floor((axisalignedbb.e + 2.0D) / 16.0D);
/*      */     
/*  863 */     i = MathHelper.clamp(i, 0, this.entitySlices.length - 1);
/*  864 */     j = MathHelper.clamp(j, 0, this.entitySlices.length - 1);
/*      */     
/*  866 */     for (int k = i; k <= j; k++) {
/*  867 */       Iterator<Entity> iterator = this.entitySlices[k].iterator();
/*      */       
/*  869 */       while (iterator.hasNext()) {
/*  870 */         Entity entity = iterator.next();
/*      */         
/*  872 */         if (oclass.isInstance(entity) && entity.getBoundingBox().c(axisalignedbb) && (predicate == null || predicate.apply(entity))) {
/*  873 */           list.add((T)entity);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(boolean flag) {
/*  881 */     if (flag) {
/*  882 */       if ((this.t && this.world.getTime() != this.lastSaved) || this.s) {
/*  883 */         return true;
/*      */       }
/*  885 */     } else if (this.t && this.world.getTime() >= this.lastSaved + ((MinecraftServer.getServer()).autosavePeriod * 4)) {
/*  886 */       return true;
/*      */     } 
/*      */     
/*  889 */     return this.s;
/*      */   }
/*      */   
/*      */   public Random a(long i) {
/*  893 */     return new Random(this.world.getSeed() + (this.locX * this.locX * 4987142) + (this.locX * 5947611) + (this.locZ * this.locZ) * 4392871L + (this.locZ * 389711) ^ i);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  897 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadNearby(IChunkProvider ichunkprovider, ChunkGenerator chunkgenerator, boolean newChunk) {
/*  902 */     this.world.timings.syncChunkLoadPostTimer.startTiming();
/*  903 */     CraftServer craftServer = this.world.getServer();
/*  904 */     if (craftServer != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  910 */       craftServer.getPluginManager().callEvent((Event)new ChunkLoadEvent(this.bukkitChunk, newChunk));
/*      */     }
/*      */ 
/*      */     
/*  914 */     for (int x = -2; x < 3; x++) {
/*  915 */       for (int z = -2; z < 3; z++) {
/*  916 */         if (x != 0 || z != 0) {
/*      */ 
/*      */ 
/*      */           
/*  920 */           Chunk neighbor = getWorld().getChunkIfLoaded(this.locX + x, this.locZ + z);
/*  921 */           if (neighbor != null) {
/*  922 */             neighbor.setNeighborLoaded(-x, -z);
/*  923 */             setNeighborLoaded(x, z);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  929 */     Chunk chunk = ichunkprovider.getLoadedChunkAt(this.locX, this.locZ - 1);
/*  930 */     Chunk chunk1 = ichunkprovider.getLoadedChunkAt(this.locX + 1, this.locZ);
/*  931 */     Chunk chunk2 = ichunkprovider.getLoadedChunkAt(this.locX, this.locZ + 1);
/*  932 */     Chunk chunk3 = ichunkprovider.getLoadedChunkAt(this.locX - 1, this.locZ);
/*      */     
/*  934 */     if (chunk1 != null && chunk2 != null && ichunkprovider.getLoadedChunkAt(this.locX + 1, this.locZ + 1) != null) {
/*  935 */       a(chunkgenerator);
/*      */     }
/*      */     
/*  938 */     if (chunk3 != null && chunk2 != null && ichunkprovider.getLoadedChunkAt(this.locX - 1, this.locZ + 1) != null) {
/*  939 */       chunk3.a(chunkgenerator);
/*      */     }
/*      */     
/*  942 */     if (chunk != null && chunk1 != null && ichunkprovider.getLoadedChunkAt(this.locX + 1, this.locZ - 1) != null) {
/*  943 */       chunk.a(chunkgenerator);
/*      */     }
/*      */     
/*  946 */     if (chunk != null && chunk3 != null) {
/*  947 */       Chunk chunk4 = ichunkprovider.getLoadedChunkAt(this.locX - 1, this.locZ - 1);
/*      */       
/*  949 */       if (chunk4 != null) {
/*  950 */         chunk4.a(chunkgenerator);
/*      */       }
/*      */     } 
/*  953 */     this.world.timings.syncChunkLoadPostTimer.stopTiming();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(ChunkGenerator chunkgenerator) {
/*  958 */     if (isDone()) {
/*  959 */       if (chunkgenerator.a(this, this.locX, this.locZ)) {
/*  960 */         markDirty();
/*      */       }
/*      */     } else {
/*  963 */       o();
/*  964 */       chunkgenerator.recreateStructures(this.locX, this.locZ);
/*      */ 
/*      */       
/*  967 */       BlockSand.instaFall = true;
/*  968 */       Random random = new Random();
/*  969 */       random.setSeed(this.world.getSeed());
/*  970 */       long xRand = random.nextLong() / 2L * 2L + 1L;
/*  971 */       long zRand = random.nextLong() / 2L * 2L + 1L;
/*  972 */       random.setSeed(this.locX * xRand + this.locZ * zRand ^ this.world.getSeed());
/*      */       
/*  974 */       CraftWorld craftWorld = this.world.getWorld();
/*  975 */       if (craftWorld != null) {
/*  976 */         this.world.populating = true;
/*      */         try {
/*  978 */           for (BlockPopulator populator : craftWorld.getPopulators()) {
/*  979 */             populator.populate((World)craftWorld, random, this.bukkitChunk);
/*      */           }
/*      */         } finally {
/*  982 */           this.world.populating = false;
/*      */         } 
/*      */       } 
/*  985 */       BlockSand.instaFall = false;
/*  986 */       this.world.getServer().getPluginManager().callEvent((Event)new ChunkPopulateEvent(this.bukkitChunk));
/*      */       
/*  988 */       markDirty();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPosition f(BlockPosition blockposition) {
/*  994 */     int i = blockposition.getX() & 0xF;
/*  995 */     int j = blockposition.getZ() & 0xF;
/*  996 */     int k = i | j << 4;
/*  997 */     BlockPosition blockposition1 = new BlockPosition(blockposition.getX(), this.h[k], blockposition.getZ());
/*      */     
/*  999 */     if (blockposition1.getY() == -999) {
/* 1000 */       int l = g() + 15;
/*      */       
/* 1002 */       blockposition1 = new BlockPosition(blockposition.getX(), l, blockposition.getZ());
/* 1003 */       int i1 = -1;
/*      */       
/* 1005 */       while (blockposition1.getY() > 0 && i1 == -1) {
/* 1006 */         IBlockData iblockdata = getBlockData(blockposition1);
/* 1007 */         Material material = iblockdata.getMaterial();
/*      */         
/* 1009 */         if (!material.isSolid() && !material.isLiquid()) {
/* 1010 */           blockposition1 = blockposition1.down(); continue;
/*      */         } 
/* 1012 */         i1 = blockposition1.getY() + 1;
/*      */       } 
/*      */ 
/*      */       
/* 1016 */       this.h[k] = i1;
/*      */     } 
/*      */     
/* 1019 */     return new BlockPosition(blockposition.getX(), this.h[k], blockposition.getZ());
/*      */   }
/*      */   
/*      */   public void b(boolean flag) {
/* 1023 */     if (this.m && this.world.worldProvider.m() && !flag) {
/* 1024 */       h(this.world.isClientSide);
/*      */     }
/*      */     
/* 1027 */     this.r = true;
/* 1028 */     if (!this.lit && this.done && this.world.spigotConfig.randomLightUpdates) {
/* 1029 */       o();
/*      */     }
/*      */     
/* 1032 */     while (!this.y.isEmpty()) {
/* 1033 */       BlockPosition blockposition = this.y.poll();
/*      */       
/* 1035 */       if (a(blockposition, EnumTileEntityState.CHECK) == null && getBlockData(blockposition).getBlock().isTileEntity()) {
/* 1036 */         TileEntity tileentity = g(blockposition);
/*      */         
/* 1038 */         this.world.setTileEntity(blockposition, tileentity);
/* 1039 */         this.world.b(blockposition, blockposition);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReady() {
/* 1053 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean j() {
/* 1058 */     return this.r;
/*      */   }
/*      */   
/*      */   public ChunkCoordIntPair k() {
/* 1062 */     return new ChunkCoordIntPair(this.locX, this.locZ);
/*      */   }
/*      */   
/*      */   public boolean c(int i, int j) {
/* 1066 */     if (i < 0) {
/* 1067 */       i = 0;
/*      */     }
/*      */     
/* 1070 */     if (j >= 256) {
/* 1071 */       j = 255;
/*      */     }
/*      */     
/* 1074 */     for (int k = i; k <= j; k += 16) {
/* 1075 */       ChunkSection chunksection = this.sections[k >> 4];
/*      */       
/* 1077 */       if (chunksection != a && !chunksection.a()) {
/* 1078 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1082 */     return true;
/*      */   }
/*      */   
/*      */   public void a(ChunkSection[] achunksection) {
/* 1086 */     if (this.sections.length != achunksection.length) {
/* 1087 */       e.warn("Could not set level chunk sections, array length is {} instead of {}", Integer.valueOf(achunksection.length), Integer.valueOf(this.sections.length));
/*      */     } else {
/* 1089 */       System.arraycopy(achunksection, 0, this.sections, 0, this.sections.length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public BiomeBase getBiome(BlockPosition blockposition, WorldChunkManager worldchunkmanager) {
/* 1094 */     int i = blockposition.getX() & 0xF;
/* 1095 */     int j = blockposition.getZ() & 0xF;
/* 1096 */     int k = this.g[j << 4 | i] & 0xFF;
/*      */ 
/*      */     
/* 1099 */     if (k == 255) {
/* 1100 */       BiomeBase biomeBase = worldchunkmanager.getBiome(blockposition, Biomes.c);
/* 1101 */       k = BiomeBase.a(biomeBase);
/* 1102 */       this.g[j << 4 | i] = (byte)(k & 0xFF);
/*      */     } 
/*      */     
/* 1105 */     BiomeBase biomebase = BiomeBase.getBiome(k);
/* 1106 */     return (biomebase == null) ? Biomes.c : biomebase;
/*      */   }
/*      */   
/*      */   public byte[] getBiomeIndex() {
/* 1110 */     return this.g;
/*      */   }
/*      */   
/*      */   public void a(byte[] abyte) {
/* 1114 */     if (this.g.length != abyte.length) {
/* 1115 */       e.warn("Could not set level chunk biomes, array length is {} instead of {}", Integer.valueOf(abyte.length), Integer.valueOf(this.g.length));
/*      */     } else {
/* 1117 */       System.arraycopy(abyte, 0, this.g, 0, this.g.length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void m() {
/* 1122 */     this.x = 0;
/*      */   }
/*      */   
/*      */   public void n() {
/* 1126 */     if (this.x < 4096) {
/* 1127 */       BlockPosition blockposition = new BlockPosition(this.locX << 4, 0, this.locZ << 4);
/*      */       
/* 1129 */       for (int i = 0; i < 8; i++) {
/* 1130 */         if (this.x >= 4096) {
/*      */           return;
/*      */         }
/*      */         
/* 1134 */         int j = this.x % 16;
/* 1135 */         int k = this.x / 16 % 16;
/* 1136 */         int l = this.x / 256;
/*      */         
/* 1138 */         this.x++;
/*      */         
/* 1140 */         for (int i1 = 0; i1 < 16; i1++) {
/* 1141 */           BlockPosition blockposition1 = blockposition.a(k, (j << 4) + i1, l);
/* 1142 */           boolean flag = !(i1 != 0 && i1 != 15 && k != 0 && k != 15 && l != 0 && l != 15);
/*      */           
/* 1144 */           if ((this.sections[j] == a && flag) || (this.sections[j] != a && this.sections[j].getType(k, i1, l).getMaterial() == Material.AIR)) {
/* 1145 */             EnumDirection[] aenumdirection = EnumDirection.values();
/* 1146 */             int j1 = aenumdirection.length;
/*      */             
/* 1148 */             for (int k1 = 0; k1 < j1; k1++) {
/* 1149 */               EnumDirection enumdirection = aenumdirection[k1];
/* 1150 */               BlockPosition blockposition2 = blockposition1.shift(enumdirection);
/*      */               
/* 1152 */               if (this.world.getType(blockposition2).d() > 0) {
/* 1153 */                 this.world.w(blockposition2);
/*      */               }
/*      */             } 
/*      */             
/* 1157 */             this.world.w(blockposition1);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void o() {
/* 1166 */     this.done = true;
/* 1167 */     this.lit = true;
/* 1168 */     BlockPosition blockposition = new BlockPosition(this.locX << 4, 0, this.locZ << 4);
/*      */     
/* 1170 */     if (this.world.worldProvider.m()) {
/* 1171 */       if (this.world.areChunksLoadedBetween(blockposition.a(-1, 0, -1), blockposition.a(16, this.world.getSeaLevel(), 16))) {
/*      */         int i;
/* 1173 */         label31: for (i = 0; i < 16; i++) {
/* 1174 */           for (int j = 0; j < 16; j++) {
/* 1175 */             if (!e(i, j)) {
/* 1176 */               this.lit = false;
/*      */               
/*      */               break label31;
/*      */             } 
/*      */           } 
/*      */         } 
/* 1182 */         if (this.lit) {
/* 1183 */           Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*      */           
/* 1185 */           while (iterator.hasNext()) {
/* 1186 */             EnumDirection enumdirection = iterator.next();
/* 1187 */             int k = (enumdirection.c() == EnumDirection.EnumAxisDirection.POSITIVE) ? 16 : 1;
/*      */             
/* 1189 */             this.world.getChunkAtWorldCoords(blockposition.shift(enumdirection, k)).a(enumdirection.opposite());
/*      */           } 
/*      */           
/* 1192 */           z();
/*      */         } 
/*      */       } else {
/* 1195 */         this.lit = false;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void z() {
/* 1202 */     for (int i = 0; i < this.i.length; i++) {
/* 1203 */       this.i[i] = true;
/*      */     }
/*      */     
/* 1206 */     h(false);
/*      */   }
/*      */   
/*      */   private void a(EnumDirection enumdirection) {
/* 1210 */     if (this.done)
/*      */     {
/*      */       
/* 1213 */       if (enumdirection == EnumDirection.EAST) {
/* 1214 */         for (int i = 0; i < 16; i++) {
/* 1215 */           e(15, i);
/*      */         }
/* 1217 */       } else if (enumdirection == EnumDirection.WEST) {
/* 1218 */         for (int i = 0; i < 16; i++) {
/* 1219 */           e(0, i);
/*      */         }
/* 1221 */       } else if (enumdirection == EnumDirection.SOUTH) {
/* 1222 */         for (int i = 0; i < 16; i++) {
/* 1223 */           e(i, 15);
/*      */         }
/* 1225 */       } else if (enumdirection == EnumDirection.NORTH) {
/* 1226 */         for (int i = 0; i < 16; i++) {
/* 1227 */           e(i, 0);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean e(int i, int j) {
/* 1235 */     int k = g();
/* 1236 */     boolean flag = false;
/* 1237 */     boolean flag1 = false;
/* 1238 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition((this.locX << 4) + i, 0, (this.locZ << 4) + j);
/*      */     
/*      */     int l;
/*      */     
/* 1242 */     for (l = k + 16 - 1; l > this.world.getSeaLevel() || (l > 0 && !flag1); l--) {
/* 1243 */       blockposition_mutableblockposition.c(blockposition_mutableblockposition.getX(), l, blockposition_mutableblockposition.getZ());
/* 1244 */       int i1 = b(blockposition_mutableblockposition);
/*      */       
/* 1246 */       if (i1 == 255 && blockposition_mutableblockposition.getY() < this.world.getSeaLevel()) {
/* 1247 */         flag1 = true;
/*      */       }
/*      */       
/* 1250 */       if (!flag && i1 > 0) {
/* 1251 */         flag = true;
/* 1252 */       } else if (flag && i1 == 0 && !this.world.w(blockposition_mutableblockposition)) {
/* 1253 */         return false;
/*      */       } 
/*      */     } 
/*      */     
/* 1257 */     for (l = blockposition_mutableblockposition.getY(); l > 0; l--) {
/* 1258 */       blockposition_mutableblockposition.c(blockposition_mutableblockposition.getX(), l, blockposition_mutableblockposition.getZ());
/* 1259 */       if (getBlockData(blockposition_mutableblockposition).d() > 0) {
/* 1260 */         this.world.w(blockposition_mutableblockposition);
/*      */       }
/*      */     } 
/*      */     
/* 1264 */     return true;
/*      */   }
/*      */   
/*      */   public boolean p() {
/* 1268 */     return this.j;
/*      */   }
/*      */   
/*      */   public World getWorld() {
/* 1272 */     return this.world;
/*      */   }
/*      */   
/*      */   public int[] r() {
/* 1276 */     return this.heightMap;
/*      */   }
/*      */   
/*      */   public void a(int[] aint) {
/* 1280 */     if (this.heightMap.length != aint.length) {
/* 1281 */       e.warn("Could not set level chunk heightmap, array length is {} instead of {}", Integer.valueOf(aint.length), Integer.valueOf(this.heightMap.length));
/*      */     } else {
/* 1283 */       System.arraycopy(aint, 0, this.heightMap, 0, this.heightMap.length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Map<BlockPosition, TileEntity> getTileEntities() {
/* 1288 */     return this.tileEntities;
/*      */   }
/*      */   
/*      */   public List<Entity>[] getEntitySlices() {
/* 1292 */     return this.entitySlices;
/*      */   }
/*      */   
/*      */   public boolean isDone() {
/* 1296 */     return this.done;
/*      */   }
/*      */   
/*      */   public void d(boolean flag) {
/* 1300 */     this.done = flag;
/*      */   }
/*      */   
/*      */   public boolean v() {
/* 1304 */     return this.lit;
/*      */   }
/*      */   
/*      */   public void e(boolean flag) {
/* 1308 */     this.lit = flag;
/*      */   }
/*      */   
/*      */   public void f(boolean flag) {
/* 1312 */     this.s = flag;
/*      */   }
/*      */   
/*      */   public void g(boolean flag) {
/* 1316 */     this.t = flag;
/*      */   }
/*      */   
/*      */   public void setLastSaved(long i) {
/* 1320 */     this.lastSaved = i;
/*      */   }
/*      */   
/*      */   public int w() {
/* 1324 */     return this.v;
/*      */   }
/*      */   
/*      */   public long x() {
/* 1328 */     return this.w;
/*      */   }
/*      */   
/*      */   public void c(long i) {
/* 1332 */     this.w = i;
/*      */   }
/*      */   
/*      */   public enum EnumTileEntityState
/*      */   {
/* 1337 */     IMMEDIATE, QUEUED, CHECK;
/*      */   }
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Chunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */