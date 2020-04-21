/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ public class ChunkProviderFlat
/*     */   implements ChunkGenerator
/*     */ {
/*     */   private final World a;
/*     */   private final Random b;
/*  35 */   private final IBlockData[] c = new IBlockData[256];
/*     */   private final WorldGenFlatInfo d;
/*  37 */   private final Map<String, StructureGenerator> e = new HashMap<>();
/*     */   private final boolean f;
/*     */   private final boolean g;
/*     */   private WorldGenLakes h;
/*     */   private WorldGenLakes i;
/*     */   
/*     */   public ChunkProviderFlat(World paramWorld, long paramLong, boolean paramBoolean, String paramString) {
/*  44 */     this.a = paramWorld;
/*  45 */     this.b = new Random(paramLong);
/*  46 */     this.d = WorldGenFlatInfo.a(paramString);
/*     */     
/*  48 */     if (paramBoolean) {
/*  49 */       Map<String, Map<String, String>> map = this.d.b();
/*     */       
/*  51 */       if (map.containsKey("village")) {
/*  52 */         Map<String, String> map1 = map.get("village");
/*  53 */         if (!map1.containsKey("size")) {
/*  54 */           map1.put("size", "1");
/*     */         }
/*  56 */         this.e.put("Village", new WorldGenVillage(map1));
/*     */       } 
/*     */       
/*  59 */       if (map.containsKey("biome_1")) {
/*  60 */         this.e.put("Temple", new WorldGenLargeFeature(map.get("biome_1")));
/*     */       }
/*  62 */       if (map.containsKey("mineshaft")) {
/*  63 */         this.e.put("Mineshaft", new WorldGenMineshaft(map.get("mineshaft")));
/*     */       }
/*  65 */       if (map.containsKey("stronghold")) {
/*  66 */         this.e.put("Stronghold", new WorldGenStronghold(map.get("stronghold")));
/*     */       }
/*  68 */       if (map.containsKey("oceanmonument")) {
/*  69 */         this.e.put("Monument", new WorldGenMonument(map.get("oceanmonument")));
/*     */       }
/*     */     } 
/*     */     
/*  73 */     if (this.d.b().containsKey("lake")) {
/*  74 */       this.h = new WorldGenLakes(Blocks.WATER);
/*     */     }
/*  76 */     if (this.d.b().containsKey("lava_lake")) {
/*  77 */       this.i = new WorldGenLakes(Blocks.LAVA);
/*     */     }
/*  79 */     this.g = this.d.b().containsKey("dungeon");
/*     */     
/*  81 */     int i = 0;
/*  82 */     int j = 0;
/*  83 */     boolean bool = true;
/*  84 */     for (WorldGenFlatLayerInfo worldGenFlatLayerInfo : this.d.c()) {
/*  85 */       for (int k = worldGenFlatLayerInfo.d(); k < worldGenFlatLayerInfo.d() + worldGenFlatLayerInfo.b(); k++) {
/*  86 */         IBlockData iBlockData = worldGenFlatLayerInfo.c();
/*  87 */         if (iBlockData.getBlock() != Blocks.AIR) {
/*     */ 
/*     */           
/*  90 */           bool = false;
/*  91 */           this.c[k] = iBlockData;
/*     */         } 
/*  93 */       }  if (worldGenFlatLayerInfo.c().getBlock() == Blocks.AIR) {
/*  94 */         j += worldGenFlatLayerInfo.b(); continue;
/*     */       } 
/*  96 */       i += worldGenFlatLayerInfo.b() + j;
/*  97 */       j = 0;
/*     */     } 
/*     */     
/* 100 */     paramWorld.b(i);
/*     */     
/* 102 */     this.f = (bool && this.d.a() != BiomeBase.a(Biomes.P)) ? false : this.d.b().containsKey("decoration");
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getOrCreateChunk(int paramInt1, int paramInt2) {
/* 107 */     ChunkSnapshot chunkSnapshot = new ChunkSnapshot();
/*     */     
/* 109 */     for (byte b1 = 0; b1 < this.c.length; b1++) {
/* 110 */       IBlockData iBlockData = this.c[b1];
/* 111 */       if (iBlockData != null)
/*     */       {
/*     */ 
/*     */         
/* 115 */         for (byte b = 0; b < 16; b++) {
/* 116 */           for (byte b3 = 0; b3 < 16; b3++) {
/* 117 */             chunkSnapshot.a(b, b1, b3, iBlockData);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 122 */     for (WorldGenBase worldGenBase : this.e.values()) {
/* 123 */       worldGenBase.a(this.a, paramInt1, paramInt2, chunkSnapshot);
/*     */     }
/*     */     
/* 126 */     Chunk chunk = new Chunk(this.a, chunkSnapshot, paramInt1, paramInt2);
/* 127 */     BiomeBase[] arrayOfBiomeBase = this.a.getWorldChunkManager().getBiomeBlock(null, paramInt1 * 16, paramInt2 * 16, 16, 16);
/* 128 */     byte[] arrayOfByte = chunk.getBiomeIndex();
/*     */     
/* 130 */     for (byte b2 = 0; b2 < arrayOfByte.length; b2++) {
/* 131 */       arrayOfByte[b2] = (byte)BiomeBase.a(arrayOfBiomeBase[b2]);
/*     */     }
/*     */     
/* 134 */     chunk.initLighting();
/*     */     
/* 136 */     return chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(int paramInt1, int paramInt2) {
/* 141 */     int i = paramInt1 * 16;
/* 142 */     int j = paramInt2 * 16;
/* 143 */     BlockPosition blockPosition = new BlockPosition(i, 0, j);
/* 144 */     BiomeBase biomeBase = this.a.getBiome(new BlockPosition(i + 16, 0, j + 16));
/* 145 */     boolean bool = false;
/*     */     
/* 147 */     this.b.setSeed(this.a.getSeed());
/* 148 */     long l1 = this.b.nextLong() / 2L * 2L + 1L;
/* 149 */     long l2 = this.b.nextLong() / 2L * 2L + 1L;
/* 150 */     this.b.setSeed(paramInt1 * l1 + paramInt2 * l2 ^ this.a.getSeed());
/*     */     
/* 152 */     ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(paramInt1, paramInt2);
/*     */     
/* 154 */     for (StructureGenerator structureGenerator : this.e.values()) {
/* 155 */       boolean bool1 = structureGenerator.a(this.a, this.b, chunkCoordIntPair);
/* 156 */       if (structureGenerator instanceof WorldGenVillage) {
/* 157 */         bool |= bool1;
/*     */       }
/*     */     } 
/*     */     
/* 161 */     if (this.h != null && !bool && this.b.nextInt(4) == 0) {
/* 162 */       this.h.generate(this.a, this.b, blockPosition.a(this.b.nextInt(16) + 8, this.b.nextInt(256), this.b.nextInt(16) + 8));
/*     */     }
/*     */     
/* 165 */     if (this.i != null && !bool && this.b.nextInt(8) == 0) {
/* 166 */       BlockPosition blockPosition1 = blockPosition.a(this.b.nextInt(16) + 8, this.b.nextInt(this.b.nextInt(248) + 8), this.b.nextInt(16) + 8);
/* 167 */       if (blockPosition1.getY() < this.a.getSeaLevel() || this.b.nextInt(10) == 0) {
/* 168 */         this.i.generate(this.a, this.b, blockPosition1);
/*     */       }
/*     */     } 
/*     */     
/* 172 */     if (this.g) {
/* 173 */       for (byte b = 0; b < 8; b++) {
/* 174 */         (new WorldGenDungeons()).generate(this.a, this.b, blockPosition.a(this.b.nextInt(16) + 8, this.b.nextInt(256), this.b.nextInt(16) + 8));
/*     */       }
/*     */     }
/*     */     
/* 178 */     if (this.f) {
/* 179 */       biomeBase.a(this.a, this.b, blockPosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Chunk paramChunk, int paramInt1, int paramInt2) {
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType paramEnumCreatureType, BlockPosition paramBlockPosition) {
/* 190 */     BiomeBase biomeBase = this.a.getBiome(paramBlockPosition);
/* 191 */     return biomeBase.getMobs(paramEnumCreatureType);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition findNearestMapFeature(World paramWorld, String paramString, BlockPosition paramBlockPosition, boolean paramBoolean) {
/* 197 */     StructureGenerator structureGenerator = this.e.get(paramString);
/* 198 */     if (structureGenerator != null) {
/* 199 */       return structureGenerator.getNearestGeneratedFeature(paramWorld, paramBlockPosition, paramBoolean);
/*     */     }
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World paramWorld, String paramString, BlockPosition paramBlockPosition) {
/* 206 */     StructureGenerator structureGenerator = this.e.get(paramString);
/* 207 */     if (structureGenerator != null) {
/* 208 */       return structureGenerator.b(paramBlockPosition);
/*     */     }
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(Chunk paramChunk, int paramInt1, int paramInt2) {
/* 215 */     for (StructureGenerator structureGenerator : this.e.values())
/* 216 */       structureGenerator.a(this.a, paramInt1, paramInt2, (ChunkSnapshot)null); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkProviderFlat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */