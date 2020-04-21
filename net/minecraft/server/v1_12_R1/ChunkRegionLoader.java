/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ 
/*     */ public class ChunkRegionLoader
/*     */   implements IChunkLoader, IAsyncChunkSaver
/*     */ {
/*  20 */   private static final Logger a = LogManager.getLogger();
/*  21 */   private final Map<ChunkCoordIntPair, NBTTagCompound> b = Maps.newConcurrentMap();
/*  22 */   private final Set<ChunkCoordIntPair> c = Collections.newSetFromMap(Maps.newConcurrentMap());
/*     */   private final File d;
/*     */   private final DataConverterManager e;
/*     */   private boolean f;
/*     */   
/*     */   public ChunkRegionLoader(File file, DataConverterManager dataconvertermanager) {
/*  28 */     this.d = file;
/*  29 */     this.e = dataconvertermanager;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Chunk a(World world, int i, int j) throws IOException {
/*  35 */     world.timings.syncChunkLoadDataTimer.startTiming();
/*  36 */     Object[] data = loadChunk(world, i, j);
/*  37 */     world.timings.syncChunkLoadDataTimer.stopTiming();
/*  38 */     if (data != null) {
/*  39 */       Chunk chunk = (Chunk)data[0];
/*  40 */       NBTTagCompound nbttagcompound = (NBTTagCompound)data[1];
/*  41 */       loadEntities(chunk, nbttagcompound.getCompound("Level"), world);
/*  42 */       return chunk;
/*     */     } 
/*     */     
/*  45 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] loadChunk(World world, int i, int j) throws IOException {
/*  50 */     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
/*  51 */     NBTTagCompound nbttagcompound = this.b.get(chunkcoordintpair);
/*     */     
/*  53 */     if (nbttagcompound == null) {
/*     */       
/*  55 */       nbttagcompound = RegionFileCache.d(this.d, i, j);
/*     */       
/*  57 */       if (nbttagcompound == null) {
/*  58 */         return null;
/*     */       }
/*     */       
/*  61 */       nbttagcompound = this.e.a(DataConverterTypes.CHUNK, nbttagcompound);
/*     */     } 
/*     */ 
/*     */     
/*  65 */     return a(world, i, j, nbttagcompound);
/*     */   }
/*     */   
/*     */   public boolean chunkExists(int i, int j) {
/*  69 */     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
/*  70 */     NBTTagCompound nbttagcompound = this.b.get(chunkcoordintpair);
/*     */     
/*  72 */     return (nbttagcompound != null) ? true : RegionFileCache.chunkExists(this.d, i, j);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected Object[] a(World world, int i, int j, NBTTagCompound nbttagcompound) {
/*  77 */     if (!nbttagcompound.hasKeyOfType("Level", 10)) {
/*  78 */       a.error("Chunk file at {},{} is missing level data, skipping", Integer.valueOf(i), Integer.valueOf(j));
/*  79 */       return null;
/*     */     } 
/*  81 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Level");
/*     */     
/*  83 */     if (!nbttagcompound1.hasKeyOfType("Sections", 9)) {
/*  84 */       a.error("Chunk file at {},{} is missing block data, skipping", Integer.valueOf(i), Integer.valueOf(j));
/*  85 */       return null;
/*     */     } 
/*  87 */     Chunk chunk = a(world, nbttagcompound1);
/*     */     
/*  89 */     if (!chunk.a(i, j)) {
/*  90 */       a.error("Chunk file at {},{} is in the wrong location; relocating. (Expected {}, {}, got {}, {})", Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(chunk.locX), Integer.valueOf(chunk.locZ));
/*  91 */       nbttagcompound1.setInt("xPos", i);
/*  92 */       nbttagcompound1.setInt("zPos", j);
/*     */ 
/*     */       
/*  95 */       NBTTagList tileEntities = nbttagcompound.getCompound("Level").getList("TileEntities", 10);
/*  96 */       if (tileEntities != null) {
/*  97 */         for (int te = 0; te < tileEntities.size(); te++) {
/*  98 */           NBTTagCompound tileEntity = tileEntities.get(te);
/*  99 */           int x = tileEntity.getInt("x") - chunk.locX * 16;
/* 100 */           int z = tileEntity.getInt("z") - chunk.locZ * 16;
/* 101 */           tileEntity.setInt("x", i * 16 + x);
/* 102 */           tileEntity.setInt("z", j * 16 + z);
/*     */         } 
/*     */       }
/*     */       
/* 106 */       chunk = a(world, nbttagcompound1);
/*     */     } 
/*     */ 
/*     */     
/* 110 */     Object[] data = new Object[2];
/* 111 */     data[0] = chunk;
/* 112 */     data[1] = nbttagcompound;
/* 113 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, Chunk chunk) throws IOException, ExceptionWorldConflict {
/* 120 */     world.checkSession();
/*     */     
/*     */     try {
/* 123 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 124 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */       
/* 126 */       nbttagcompound.set("Level", nbttagcompound1);
/* 127 */       nbttagcompound.setInt("DataVersion", 1139);
/* 128 */       a(chunk, world, nbttagcompound1);
/* 129 */       a(chunk.k(), nbttagcompound);
/* 130 */     } catch (Exception exception) {
/* 131 */       a.error("Failed to save chunk", exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) {
/* 137 */     if (!this.c.contains(chunkcoordintpair)) {
/* 138 */       this.b.put(chunkcoordintpair, nbttagcompound);
/*     */     }
/*     */     
/* 141 */     FileIOThread.a().a(this);
/*     */   }
/*     */   
/*     */   public boolean a() {
/*     */     boolean flag;
/* 146 */     Iterator<Map.Entry<ChunkCoordIntPair, NBTTagCompound>> iter = this.b.entrySet().iterator();
/* 147 */     if (!iter.hasNext()) {
/*     */       
/* 149 */       if (this.f) {
/* 150 */         a.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", this.d.getName());
/*     */       }
/*     */       
/* 153 */       return false;
/*     */     } 
/*     */     
/* 156 */     Map.Entry<ChunkCoordIntPair, NBTTagCompound> entry = iter.next();
/* 157 */     iter.remove();
/* 158 */     ChunkCoordIntPair chunkcoordintpair = entry.getKey();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 164 */       this.c.add(chunkcoordintpair);
/* 165 */       NBTTagCompound nbttagcompound = entry.getValue();
/*     */       
/* 167 */       if (nbttagcompound != null) {
/*     */         try {
/* 169 */           b(chunkcoordintpair, nbttagcompound);
/* 170 */         } catch (Exception exception) {
/* 171 */           a.error("Failed to save chunk", exception);
/*     */         } 
/*     */       }
/*     */       
/* 175 */       flag = true;
/*     */     } finally {
/* 177 */       this.c.remove(chunkcoordintpair);
/*     */     } 
/*     */     
/* 180 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) throws IOException {
/* 186 */     RegionFileCache.e(this.d, chunkcoordintpair.x, chunkcoordintpair.z, nbttagcompound);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(World world, Chunk chunk) throws IOException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() {
/*     */     try {
/* 201 */       this.f = true;
/*     */ 
/*     */       
/* 204 */       while (a());
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 210 */       this.f = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 216 */     dataconvertermanager.a(DataConverterTypes.CHUNK, new DataInspector() {
/*     */           public NBTTagCompound a(DataConverter dataconverter, NBTTagCompound nbttagcompound, int i) {
/* 218 */             if (nbttagcompound.hasKeyOfType("Level", 10)) {
/* 219 */               NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Level");
/*     */ 
/*     */ 
/*     */               
/* 223 */               if (nbttagcompound1.hasKeyOfType("Entities", 9)) {
/* 224 */                 NBTTagList nbttaglist = nbttagcompound1.getList("Entities", 10);
/*     */                 
/* 226 */                 for (int j = 0; j < nbttaglist.size(); j++) {
/* 227 */                   nbttaglist.a(j, dataconverter.a(DataConverterTypes.ENTITY, (NBTTagCompound)nbttaglist.i(j), i));
/*     */                 }
/*     */               } 
/*     */               
/* 231 */               if (nbttagcompound1.hasKeyOfType("TileEntities", 9)) {
/* 232 */                 NBTTagList nbttaglist = nbttagcompound1.getList("TileEntities", 10);
/*     */                 
/* 234 */                 for (int j = 0; j < nbttaglist.size(); j++) {
/* 235 */                   nbttaglist.a(j, dataconverter.a(DataConverterTypes.BLOCK_ENTITY, (NBTTagCompound)nbttaglist.i(j), i));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 240 */             return nbttagcompound;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void a(Chunk chunk, World world, NBTTagCompound nbttagcompound) {
/* 246 */     nbttagcompound.setInt("xPos", chunk.locX);
/* 247 */     nbttagcompound.setInt("zPos", chunk.locZ);
/* 248 */     nbttagcompound.setLong("LastUpdate", world.getTime());
/* 249 */     nbttagcompound.setIntArray("HeightMap", chunk.r());
/* 250 */     nbttagcompound.setBoolean("TerrainPopulated", chunk.isDone());
/* 251 */     nbttagcompound.setBoolean("LightPopulated", chunk.v());
/* 252 */     nbttagcompound.setLong("InhabitedTime", chunk.x());
/* 253 */     ChunkSection[] achunksection = chunk.getSections();
/* 254 */     NBTTagList nbttaglist = new NBTTagList();
/* 255 */     boolean flag = world.worldProvider.m();
/* 256 */     ChunkSection[] achunksection1 = achunksection;
/* 257 */     int i = achunksection.length;
/*     */ 
/*     */ 
/*     */     
/* 261 */     for (int j = 0; j < i; j++) {
/* 262 */       ChunkSection chunksection = achunksection1[j];
/*     */       
/* 264 */       if (chunksection != Chunk.a) {
/* 265 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 266 */         nbttagcompound1.setByte("Y", (byte)(chunksection.getYPosition() >> 4 & 0xFF));
/* 267 */         byte[] abyte = new byte[4096];
/* 268 */         NibbleArray nibblearray = new NibbleArray();
/* 269 */         NibbleArray nibblearray1 = chunksection.getBlocks().exportData(abyte, nibblearray);
/*     */         
/* 271 */         nbttagcompound1.setByteArray("Blocks", abyte);
/* 272 */         nbttagcompound1.setByteArray("Data", nibblearray.asBytes());
/* 273 */         if (nibblearray1 != null) {
/* 274 */           nbttagcompound1.setByteArray("Add", nibblearray1.asBytes());
/*     */         }
/*     */         
/* 277 */         nbttagcompound1.setByteArray("BlockLight", chunksection.getEmittedLightArray().asBytes());
/* 278 */         if (flag) {
/* 279 */           nbttagcompound1.setByteArray("SkyLight", chunksection.getSkyLightArray().asBytes());
/*     */         } else {
/* 281 */           nbttagcompound1.setByteArray("SkyLight", new byte[(chunksection.getEmittedLightArray().asBytes()).length]);
/*     */         } 
/*     */         
/* 284 */         nbttaglist.add(nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/* 288 */     nbttagcompound.set("Sections", nbttaglist);
/* 289 */     nbttagcompound.setByteArray("Biomes", chunk.getBiomeIndex());
/* 290 */     chunk.g(false);
/* 291 */     NBTTagList nbttaglist1 = new NBTTagList();
/*     */ 
/*     */ 
/*     */     
/* 295 */     for (i = 0; i < (chunk.getEntitySlices()).length; i++) {
/* 296 */       Iterator<Entity> iterator1 = chunk.getEntitySlices()[i].iterator();
/*     */       
/* 298 */       while (iterator1.hasNext()) {
/* 299 */         Entity entity = iterator1.next();
/*     */         
/* 301 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 302 */         if (entity.d(nbttagcompound1)) {
/* 303 */           chunk.g(true);
/* 304 */           nbttaglist1.add(nbttagcompound1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 309 */     nbttagcompound.set("Entities", nbttaglist1);
/* 310 */     NBTTagList nbttaglist2 = new NBTTagList();
/*     */     
/* 312 */     Iterator<TileEntity> iterator = chunk.getTileEntities().values().iterator();
/*     */     
/* 314 */     while (iterator.hasNext()) {
/* 315 */       TileEntity tileentity = iterator.next();
/*     */       
/* 317 */       NBTTagCompound nbttagcompound1 = tileentity.save(new NBTTagCompound());
/* 318 */       nbttaglist2.add(nbttagcompound1);
/*     */     } 
/*     */     
/* 321 */     nbttagcompound.set("TileEntities", nbttaglist2);
/* 322 */     List<NextTickListEntry> list = world.a(chunk, false);
/*     */     
/* 324 */     if (list != null) {
/* 325 */       long k = world.getTime();
/* 326 */       NBTTagList nbttaglist3 = new NBTTagList();
/* 327 */       Iterator<NextTickListEntry> iterator1 = list.iterator();
/*     */       
/* 329 */       while (iterator1.hasNext()) {
/* 330 */         NextTickListEntry nextticklistentry = iterator1.next();
/* 331 */         NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/* 332 */         MinecraftKey minecraftkey = Block.REGISTRY.b(nextticklistentry.a());
/*     */         
/* 334 */         nbttagcompound2.setString("i", (minecraftkey == null) ? "" : minecraftkey.toString());
/* 335 */         nbttagcompound2.setInt("x", nextticklistentry.a.getX());
/* 336 */         nbttagcompound2.setInt("y", nextticklistentry.a.getY());
/* 337 */         nbttagcompound2.setInt("z", nextticklistentry.a.getZ());
/* 338 */         nbttagcompound2.setInt("t", (int)(nextticklistentry.b - k));
/* 339 */         nbttagcompound2.setInt("p", nextticklistentry.c);
/* 340 */         nbttaglist3.add(nbttagcompound2);
/*     */       } 
/*     */       
/* 343 */       nbttagcompound.set("TileTicks", nbttaglist3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Chunk a(World world, NBTTagCompound nbttagcompound) {
/* 349 */     int i = nbttagcompound.getInt("xPos");
/* 350 */     int j = nbttagcompound.getInt("zPos");
/* 351 */     Chunk chunk = new Chunk(world, i, j);
/*     */     
/* 353 */     chunk.a(nbttagcompound.getIntArray("HeightMap"));
/* 354 */     chunk.d(nbttagcompound.getBoolean("TerrainPopulated"));
/* 355 */     chunk.e(nbttagcompound.getBoolean("LightPopulated"));
/* 356 */     chunk.c(nbttagcompound.getLong("InhabitedTime"));
/* 357 */     NBTTagList nbttaglist = nbttagcompound.getList("Sections", 10);
/*     */     
/* 359 */     ChunkSection[] achunksection = new ChunkSection[16];
/* 360 */     boolean flag1 = world.worldProvider.m();
/*     */     
/* 362 */     for (int k = 0; k < nbttaglist.size(); k++) {
/* 363 */       NBTTagCompound nbttagcompound1 = nbttaglist.get(k);
/* 364 */       byte b0 = nbttagcompound1.getByte("Y");
/* 365 */       ChunkSection chunksection = new ChunkSection(b0 << 4, flag1);
/* 366 */       byte[] abyte = nbttagcompound1.getByteArray("Blocks");
/* 367 */       NibbleArray nibblearray = new NibbleArray(nbttagcompound1.getByteArray("Data"));
/* 368 */       NibbleArray nibblearray1 = nbttagcompound1.hasKeyOfType("Add", 7) ? new NibbleArray(nbttagcompound1.getByteArray("Add")) : null;
/*     */       
/* 370 */       chunksection.getBlocks().a(abyte, nibblearray, nibblearray1);
/* 371 */       chunksection.a(new NibbleArray(nbttagcompound1.getByteArray("BlockLight")));
/* 372 */       if (flag1) {
/* 373 */         chunksection.b(new NibbleArray(nbttagcompound1.getByteArray("SkyLight")));
/*     */       }
/*     */       
/* 376 */       chunksection.recalcBlockCounts();
/* 377 */       achunksection[b0] = chunksection;
/*     */     } 
/*     */     
/* 380 */     chunk.a(achunksection);
/* 381 */     if (nbttagcompound.hasKeyOfType("Biomes", 7)) {
/* 382 */       chunk.a(nbttagcompound.getByteArray("Biomes"));
/*     */     }
/*     */ 
/*     */     
/* 386 */     return chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadEntities(Chunk chunk, NBTTagCompound nbttagcompound, World world) {
/* 391 */     world.timings.syncChunkLoadEntitiesTimer.startTiming();
/* 392 */     NBTTagList nbttaglist1 = nbttagcompound.getList("Entities", 10);
/*     */     
/* 394 */     for (int l = 0; l < nbttaglist1.size(); l++) {
/* 395 */       NBTTagCompound nbttagcompound2 = nbttaglist1.get(l);
/*     */       
/* 397 */       a(nbttagcompound2, world, chunk);
/* 398 */       chunk.g(true);
/*     */     } 
/* 400 */     world.timings.syncChunkLoadEntitiesTimer.stopTiming();
/* 401 */     world.timings.syncChunkLoadTileEntitiesTimer.startTiming();
/* 402 */     NBTTagList nbttaglist2 = nbttagcompound.getList("TileEntities", 10);
/*     */     
/* 404 */     for (int i1 = 0; i1 < nbttaglist2.size(); i1++) {
/* 405 */       NBTTagCompound nbttagcompound3 = nbttaglist2.get(i1);
/* 406 */       TileEntity tileentity = TileEntity.a(world, nbttagcompound3);
/*     */       
/* 408 */       if (tileentity != null) {
/* 409 */         chunk.a(tileentity);
/*     */       }
/*     */     } 
/* 412 */     world.timings.syncChunkLoadTileEntitiesTimer.stopTiming();
/* 413 */     world.timings.syncChunkLoadTileTicksTimer.startTiming();
/*     */     
/* 415 */     if (nbttagcompound.hasKeyOfType("TileTicks", 9)) {
/* 416 */       NBTTagList nbttaglist3 = nbttagcompound.getList("TileTicks", 10);
/*     */       
/* 418 */       for (int j1 = 0; j1 < nbttaglist3.size(); j1++) {
/* 419 */         Block block; NBTTagCompound nbttagcompound4 = nbttaglist3.get(j1);
/*     */ 
/*     */         
/* 422 */         if (nbttagcompound4.hasKeyOfType("i", 8)) {
/* 423 */           block = Block.getByName(nbttagcompound4.getString("i"));
/*     */         } else {
/* 425 */           block = Block.getById(nbttagcompound4.getInt("i"));
/*     */         } 
/*     */         
/* 428 */         world.b(new BlockPosition(nbttagcompound4.getInt("x"), nbttagcompound4.getInt("y"), nbttagcompound4.getInt("z")), block, nbttagcompound4.getInt("t"), nbttagcompound4.getInt("p"));
/*     */       } 
/*     */     } 
/* 431 */     world.timings.syncChunkLoadTileTicksTimer.stopTiming();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(NBTTagCompound nbttagcompound, World world, Chunk chunk) {
/* 438 */     Entity entity = a(nbttagcompound, world);
/*     */     
/* 440 */     if (entity == null) {
/* 441 */       return null;
/*     */     }
/* 443 */     chunk.a(entity);
/* 444 */     if (nbttagcompound.hasKeyOfType("Passengers", 9)) {
/* 445 */       NBTTagList nbttaglist = nbttagcompound.getList("Passengers", 10);
/*     */       
/* 447 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 448 */         Entity entity1 = a(nbttaglist.get(i), world, chunk);
/*     */         
/* 450 */         if (entity1 != null) {
/* 451 */           entity1.a(entity, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 456 */     return entity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(NBTTagCompound nbttagcompound, World world, double d0, double d1, double d2, boolean flag) {
/* 463 */     return spawnEntity(nbttagcompound, world, d0, d1, d2, flag, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity spawnEntity(NBTTagCompound nbttagcompound, World world, double d0, double d1, double d2, boolean flag, CreatureSpawnEvent.SpawnReason spawnReason) {
/* 468 */     Entity entity = a(nbttagcompound, world);
/*     */     
/* 470 */     if (entity == null) {
/* 471 */       return null;
/*     */     }
/* 473 */     entity.setPositionRotation(d0, d1, d2, entity.yaw, entity.pitch);
/* 474 */     if (flag && !world.addEntity(entity, spawnReason)) {
/* 475 */       return null;
/*     */     }
/* 477 */     if (nbttagcompound.hasKeyOfType("Passengers", 9)) {
/* 478 */       NBTTagList nbttaglist = nbttagcompound.getList("Passengers", 10);
/*     */       
/* 480 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 481 */         Entity entity1 = a(nbttaglist.get(i), world, d0, d1, d2, flag);
/*     */         
/* 483 */         if (entity1 != null) {
/* 484 */           entity1.a(entity, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 489 */     return entity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected static Entity a(NBTTagCompound nbttagcompound, World world) {
/*     */     try {
/* 497 */       return EntityTypes.a(nbttagcompound, world);
/* 498 */     } catch (RuntimeException runtimeException) {
/* 499 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(Entity entity, World world) {
/* 505 */     a(entity, world, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*     */   }
/*     */   
/*     */   public static void a(Entity entity, World world, CreatureSpawnEvent.SpawnReason reason) {
/* 509 */     if (world.addEntity(entity, reason) && entity.isVehicle()) {
/*     */       
/* 511 */       Iterator<Entity> iterator = entity.bF().iterator();
/*     */       
/* 513 */       while (iterator.hasNext()) {
/* 514 */         Entity entity1 = iterator.next();
/*     */         
/* 516 */         a(entity1, world);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(NBTTagCompound nbttagcompound, World world, boolean flag) {
/* 524 */     Entity entity = a(nbttagcompound, world);
/*     */     
/* 526 */     if (entity == null)
/* 527 */       return null; 
/* 528 */     if (flag && !world.addEntity(entity)) {
/* 529 */       return null;
/*     */     }
/* 531 */     if (nbttagcompound.hasKeyOfType("Passengers", 9)) {
/* 532 */       NBTTagList nbttaglist = nbttagcompound.getList("Passengers", 10);
/*     */       
/* 534 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 535 */         Entity entity1 = a(nbttaglist.get(i), world, flag);
/*     */         
/* 537 */         if (entity1 != null) {
/* 538 */           entity1.a(entity, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 543 */     return entity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkRegionLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */