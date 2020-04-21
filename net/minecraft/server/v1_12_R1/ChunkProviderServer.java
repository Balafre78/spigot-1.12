/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.chunkio.ChunkIOExecutor;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.world.ChunkUnloadEvent;
/*     */ import org.spigotmc.SlackActivityAccountant;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkProviderServer
/*     */   implements IChunkProvider
/*     */ {
/*  25 */   private static final Logger a = LogManager.getLogger();
/*  26 */   public final Set<Long> unloadQueue = Sets.newHashSet();
/*     */   public final ChunkGenerator chunkGenerator;
/*     */   private final IChunkLoader chunkLoader;
/*  29 */   public final Long2ObjectMap<Chunk> chunks = (Long2ObjectMap<Chunk>)new Long2ObjectOpenHashMap(8192);
/*     */   public final WorldServer world;
/*     */   
/*     */   public ChunkProviderServer(WorldServer worldserver, IChunkLoader ichunkloader, ChunkGenerator chunkgenerator) {
/*  33 */     this.world = worldserver;
/*  34 */     this.chunkLoader = ichunkloader;
/*  35 */     this.chunkGenerator = chunkgenerator;
/*     */   }
/*     */   private static final double UNLOAD_QUEUE_RESIZE_FACTOR = 0.96D;
/*     */   public Collection<Chunk> a() {
/*  39 */     return (Collection<Chunk>)this.chunks.values();
/*     */   }
/*     */   
/*     */   public void unload(Chunk chunk) {
/*  43 */     if (this.world.worldProvider.c(chunk.locX, chunk.locZ)) {
/*  44 */       this.unloadQueue.add(Long.valueOf(ChunkCoordIntPair.a(chunk.locX, chunk.locZ)));
/*  45 */       chunk.d = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b() {
/*  51 */     ObjectIterator objectiterator = this.chunks.values().iterator();
/*     */     
/*  53 */     while (objectiterator.hasNext()) {
/*  54 */       Chunk chunk = (Chunk)objectiterator.next();
/*     */       
/*  56 */       unload(chunk);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Chunk getLoadedChunkAt(int i, int j) {
/*  63 */     long k = ChunkCoordIntPair.a(i, j);
/*  64 */     Chunk chunk = (Chunk)this.chunks.get(k);
/*     */     
/*  66 */     if (chunk != null) {
/*  67 */       chunk.d = false;
/*     */     }
/*     */     
/*  70 */     return chunk;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Chunk getOrLoadChunkAt(int i, int j) {
/*  75 */     Chunk chunk = getLoadedChunkAt(i, j);
/*     */     
/*  77 */     if (chunk == null) {
/*     */       
/*  79 */       ChunkRegionLoader loader = null;
/*     */       
/*  81 */       if (this.chunkLoader instanceof ChunkRegionLoader) {
/*  82 */         loader = (ChunkRegionLoader)this.chunkLoader;
/*     */       }
/*  84 */       if (loader != null && loader.chunkExists(i, j)) {
/*  85 */         chunk = ChunkIOExecutor.syncChunkLoad(this.world, loader, this, i, j);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     return chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Chunk originalGetOrLoadChunkAt(int i, int j) {
/*  95 */     Chunk chunk = getLoadedChunkAt(i, j);
/*     */     
/*  97 */     if (chunk == null) {
/*  98 */       chunk = loadChunk(i, j);
/*  99 */       if (chunk != null) {
/* 100 */         this.chunks.put(ChunkCoordIntPair.a(i, j), chunk);
/* 101 */         chunk.addEntities();
/* 102 */         chunk.loadNearby(this, this.chunkGenerator, false);
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     return chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfLoaded(int x, int z) {
/* 111 */     return (Chunk)this.chunks.get(ChunkCoordIntPair.a(x, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkAt(int i, int j) {
/* 116 */     return getChunkAt(i, j, null);
/*     */   }
/*     */   
/*     */   public Chunk getChunkAt(int i, int j, Runnable runnable) {
/* 120 */     return getChunkAt(i, j, runnable, true);
/*     */   }
/*     */   
/*     */   public Chunk getChunkAt(int i, int j, Runnable runnable, boolean generate) {
/* 124 */     Chunk chunk = getChunkIfLoaded(i, j);
/* 125 */     ChunkRegionLoader loader = null;
/*     */     
/* 127 */     if (this.chunkLoader instanceof ChunkRegionLoader) {
/* 128 */       loader = (ChunkRegionLoader)this.chunkLoader;
/*     */     }
/*     */ 
/*     */     
/* 132 */     if (chunk == null && loader != null && loader.chunkExists(i, j)) {
/* 133 */       if (runnable != null) {
/* 134 */         ChunkIOExecutor.queueChunkLoad(this.world, loader, this, i, j, runnable);
/* 135 */         return null;
/*     */       } 
/* 137 */       chunk = ChunkIOExecutor.syncChunkLoad(this.world, loader, this, i, j);
/*     */     }
/* 139 */     else if (chunk == null && generate) {
/* 140 */       chunk = originalGetChunkAt(i, j);
/*     */     } 
/*     */ 
/*     */     
/* 144 */     if (runnable != null) {
/* 145 */       runnable.run();
/*     */     }
/*     */     
/* 148 */     return chunk;
/*     */   }
/*     */   
/*     */   public Chunk originalGetChunkAt(int i, int j) {
/* 152 */     Chunk chunk = originalGetOrLoadChunkAt(i, j);
/*     */ 
/*     */     
/* 155 */     if (chunk == null) {
/* 156 */       this.world.timings.syncChunkLoadTimer.startTiming();
/* 157 */       long k = ChunkCoordIntPair.a(i, j);
/*     */       
/*     */       try {
/* 160 */         chunk = this.chunkGenerator.getOrCreateChunk(i, j);
/* 161 */       } catch (Throwable throwable) {
/* 162 */         CrashReport crashreport = CrashReport.a(throwable, "Exception generating new chunk");
/* 163 */         CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Chunk to be generated");
/*     */         
/* 165 */         crashreportsystemdetails.a("Location", String.format("%d,%d", new Object[] { Integer.valueOf(i), Integer.valueOf(j) }));
/* 166 */         crashreportsystemdetails.a("Position hash", Long.valueOf(k));
/* 167 */         crashreportsystemdetails.a("Generator", this.chunkGenerator);
/* 168 */         throw new ReportedException(crashreport);
/*     */       } 
/*     */       
/* 171 */       this.chunks.put(k, chunk);
/* 172 */       chunk.addEntities();
/* 173 */       chunk.loadNearby(this, this.chunkGenerator, true);
/* 174 */       this.world.timings.syncChunkLoadTimer.stopTiming();
/*     */     } 
/*     */     
/* 177 */     return chunk;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Chunk loadChunk(int i, int j) {
/*     */     try {
/* 183 */       Chunk chunk = this.chunkLoader.a(this.world, i, j);
/*     */       
/* 185 */       if (chunk != null) {
/* 186 */         chunk.setLastSaved(this.world.getTime());
/* 187 */         this.chunkGenerator.recreateStructures(chunk, i, j);
/*     */       } 
/*     */       
/* 190 */       return chunk;
/* 191 */     } catch (Exception exception) {
/* 192 */       a.error("Couldn't load chunk", exception);
/* 193 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveChunkNOP(Chunk chunk) {
/*     */     try {
/* 199 */       this.chunkLoader.b(this.world, chunk);
/* 200 */     } catch (Exception exception) {
/* 201 */       a.error("Couldn't save entities", exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveChunk(Chunk chunk) {
/*     */     try {
/* 208 */       chunk.setLastSaved(this.world.getTime());
/* 209 */       this.chunkLoader.a(this.world, chunk);
/* 210 */     } catch (IOException ioexception) {
/* 211 */       a.error("Couldn't save chunk", ioexception);
/* 212 */     } catch (ExceptionWorldConflict exceptionworldconflict) {
/* 213 */       a.error("Couldn't save chunk; already in use by another instance of Minecraft?", exceptionworldconflict);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(boolean flag) {
/* 219 */     int i = 0;
/*     */ 
/*     */     
/* 222 */     ObjectIterator<Chunk> objectIterator = this.chunks.values().iterator();
/* 223 */     while (objectIterator.hasNext()) {
/* 224 */       Chunk chunk = objectIterator.next();
/*     */ 
/*     */       
/* 227 */       if (flag) {
/* 228 */         saveChunkNOP(chunk);
/*     */       }
/*     */       
/* 231 */       if (chunk.a(flag)) {
/* 232 */         saveChunk(chunk);
/* 233 */         chunk.f(false);
/* 234 */         i++;
/* 235 */         if (i == 24);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 241 */     return true;
/*     */   }
/*     */   
/*     */   public void c() {
/* 245 */     this.chunkLoader.c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unloadChunks() {
/* 251 */     if (!this.world.savingDisabled) {
/* 252 */       if (!this.unloadQueue.isEmpty()) {
/*     */         
/* 254 */         SlackActivityAccountant activityAccountant = (this.world.getMinecraftServer()).slackActivityAccountant;
/* 255 */         activityAccountant.startActivity(0.5D);
/* 256 */         int targetSize = (int)(this.unloadQueue.size() * 0.96D);
/*     */ 
/*     */         
/* 259 */         Iterator<Long> iterator = this.unloadQueue.iterator();
/*     */         
/* 261 */         while (iterator.hasNext()) {
/* 262 */           Long olong = iterator.next();
/* 263 */           iterator.remove();
/* 264 */           Chunk chunk = (Chunk)this.chunks.get(olong);
/*     */           
/* 266 */           if (chunk != null && chunk.d) {
/*     */             
/* 268 */             if (!unloadChunk(chunk, true)) {
/*     */               continue;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 274 */             if (this.unloadQueue.size() <= targetSize && activityAccountant.activityTimeIsExhausted()) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 281 */         activityAccountant.endActivity();
/*     */       } 
/*     */       
/* 284 */       this.chunkLoader.b();
/*     */     } 
/*     */     
/* 287 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean unloadChunk(Chunk chunk, boolean save) {
/* 292 */     ChunkUnloadEvent event = new ChunkUnloadEvent(chunk.bukkitChunk, save);
/* 293 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/* 294 */     if (event.isCancelled()) {
/* 295 */       return false;
/*     */     }
/* 297 */     save = event.isSaveChunk();
/*     */ 
/*     */     
/* 300 */     for (int x = -2; x < 3; x++) {
/* 301 */       for (int z = -2; z < 3; z++) {
/* 302 */         if (x != 0 || z != 0) {
/*     */ 
/*     */ 
/*     */           
/* 306 */           Chunk neighbor = getChunkIfLoaded(chunk.locX + x, chunk.locZ + z);
/* 307 */           if (neighbor != null) {
/* 308 */             neighbor.setNeighborUnloaded(-x, -z);
/* 309 */             chunk.setNeighborUnloaded(x, z);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 314 */     chunk.removeEntities();
/* 315 */     if (save) {
/* 316 */       saveChunk(chunk);
/* 317 */       saveChunkNOP(chunk);
/*     */     } 
/* 319 */     this.chunks.remove(chunk.chunkKey);
/* 320 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean e() {
/* 325 */     return !this.world.savingDisabled;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 329 */     return "ServerChunkCache: " + this.chunks.size() + " Drop: " + this.unloadQueue.size();
/*     */   }
/*     */   
/*     */   public List<BiomeBase.BiomeMeta> a(EnumCreatureType enumcreaturetype, BlockPosition blockposition) {
/* 333 */     return this.chunkGenerator.getMobsFor(enumcreaturetype, blockposition);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition a(World world, String s, BlockPosition blockposition, boolean flag) {
/* 338 */     return this.chunkGenerator.findNearestMapFeature(world, s, blockposition, flag);
/*     */   }
/*     */   
/*     */   public boolean a(World world, String s, BlockPosition blockposition) {
/* 342 */     return this.chunkGenerator.a(world, s, blockposition);
/*     */   }
/*     */   
/*     */   public int g() {
/* 346 */     return this.chunks.size();
/*     */   }
/*     */   
/*     */   public boolean isLoaded(int i, int j) {
/* 350 */     return this.chunks.containsKey(ChunkCoordIntPair.a(i, j));
/*     */   }
/*     */   
/*     */   public boolean e(int i, int j) {
/* 354 */     return !(!this.chunks.containsKey(ChunkCoordIntPair.a(i, j)) && !this.chunkLoader.chunkExists(i, j));
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkProviderServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */