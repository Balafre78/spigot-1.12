/*     */ package net.minecraft.server.v1_12_R1;
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
/*     */ public class OldChunkLoader
/*     */ {
/*     */   public static OldChunk a(NBTTagCompound paramNBTTagCompound) {
/*  17 */     int i = paramNBTTagCompound.getInt("xPos");
/*  18 */     int j = paramNBTTagCompound.getInt("zPos");
/*     */     
/*  20 */     OldChunk oldChunk = new OldChunk(i, j);
/*  21 */     oldChunk.g = paramNBTTagCompound.getByteArray("Blocks");
/*  22 */     oldChunk.f = new OldNibbleArray(paramNBTTagCompound.getByteArray("Data"), 7);
/*  23 */     oldChunk.e = new OldNibbleArray(paramNBTTagCompound.getByteArray("SkyLight"), 7);
/*  24 */     oldChunk.d = new OldNibbleArray(paramNBTTagCompound.getByteArray("BlockLight"), 7);
/*  25 */     oldChunk.c = paramNBTTagCompound.getByteArray("HeightMap");
/*  26 */     oldChunk.b = paramNBTTagCompound.getBoolean("TerrainPopulated");
/*  27 */     oldChunk.h = paramNBTTagCompound.getList("Entities", 10);
/*  28 */     oldChunk.i = paramNBTTagCompound.getList("TileEntities", 10);
/*  29 */     oldChunk.j = paramNBTTagCompound.getList("TileTicks", 10);
/*     */ 
/*     */     
/*     */     try {
/*  33 */       oldChunk.a = paramNBTTagCompound.getLong("LastUpdate");
/*  34 */     } catch (ClassCastException classCastException) {
/*  35 */       oldChunk.a = paramNBTTagCompound.getInt("LastUpdate");
/*     */     } 
/*     */     
/*  38 */     return oldChunk;
/*     */   }
/*     */   
/*     */   public static void a(OldChunk paramOldChunk, NBTTagCompound paramNBTTagCompound, WorldChunkManager paramWorldChunkManager) {
/*  42 */     paramNBTTagCompound.setInt("xPos", paramOldChunk.k);
/*  43 */     paramNBTTagCompound.setInt("zPos", paramOldChunk.l);
/*  44 */     paramNBTTagCompound.setLong("LastUpdate", paramOldChunk.a);
/*  45 */     int[] arrayOfInt = new int[paramOldChunk.c.length];
/*  46 */     for (byte b1 = 0; b1 < paramOldChunk.c.length; b1++) {
/*  47 */       arrayOfInt[b1] = paramOldChunk.c[b1];
/*     */     }
/*  49 */     paramNBTTagCompound.setIntArray("HeightMap", arrayOfInt);
/*  50 */     paramNBTTagCompound.setBoolean("TerrainPopulated", paramOldChunk.b);
/*     */     
/*  52 */     NBTTagList nBTTagList = new NBTTagList();
/*  53 */     for (byte b2 = 0; b2 < 8; b2++) {
/*     */       
/*  55 */       boolean bool = true;
/*  56 */       for (byte b = 0; b < 16 && bool; b++) {
/*  57 */         for (byte b4 = 0; b4 < 16 && bool; b4++) {
/*  58 */           for (byte b5 = 0; b5 < 16; b5++) {
/*  59 */             int i = b << 11 | b5 << 7 | b4 + (b2 << 4);
/*  60 */             byte b6 = paramOldChunk.g[i];
/*  61 */             if (b6 != 0) {
/*  62 */               bool = false;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*  69 */       if (!bool) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  74 */         byte[] arrayOfByte1 = new byte[4096];
/*  75 */         NibbleArray nibbleArray1 = new NibbleArray();
/*  76 */         NibbleArray nibbleArray2 = new NibbleArray();
/*  77 */         NibbleArray nibbleArray3 = new NibbleArray();
/*     */         
/*  79 */         for (byte b4 = 0; b4 < 16; b4++) {
/*  80 */           for (byte b5 = 0; b5 < 16; b5++) {
/*  81 */             for (byte b6 = 0; b6 < 16; b6++) {
/*  82 */               int i = b4 << 11 | b6 << 7 | b5 + (b2 << 4);
/*  83 */               byte b7 = paramOldChunk.g[i];
/*     */               
/*  85 */               arrayOfByte1[b5 << 8 | b6 << 4 | b4] = (byte)(b7 & 0xFF);
/*  86 */               nibbleArray1.a(b4, b5, b6, paramOldChunk.f.a(b4, b5 + (b2 << 4), b6));
/*  87 */               nibbleArray2.a(b4, b5, b6, paramOldChunk.e.a(b4, b5 + (b2 << 4), b6));
/*  88 */               nibbleArray3.a(b4, b5, b6, paramOldChunk.d.a(b4, b5 + (b2 << 4), b6));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*  93 */         NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */         
/*  95 */         nBTTagCompound.setByte("Y", (byte)(b2 & 0xFF));
/*  96 */         nBTTagCompound.setByteArray("Blocks", arrayOfByte1);
/*  97 */         nBTTagCompound.setByteArray("Data", nibbleArray1.asBytes());
/*  98 */         nBTTagCompound.setByteArray("SkyLight", nibbleArray2.asBytes());
/*  99 */         nBTTagCompound.setByteArray("BlockLight", nibbleArray3.asBytes());
/*     */         
/* 101 */         nBTTagList.add(nBTTagCompound);
/*     */       } 
/* 103 */     }  paramNBTTagCompound.set("Sections", nBTTagList);
/*     */ 
/*     */     
/* 106 */     byte[] arrayOfByte = new byte[256];
/* 107 */     BlockPosition.MutableBlockPosition mutableBlockPosition = new BlockPosition.MutableBlockPosition();
/* 108 */     for (byte b3 = 0; b3 < 16; b3++) {
/* 109 */       for (byte b = 0; b < 16; b++) {
/* 110 */         mutableBlockPosition.c(paramOldChunk.k << 4 | b3, 0, paramOldChunk.l << 4 | b);
/* 111 */         arrayOfByte[b << 4 | b3] = (byte)(BiomeBase.a(paramWorldChunkManager.getBiome(mutableBlockPosition, Biomes.b)) & 0xFF);
/*     */       } 
/*     */     } 
/* 114 */     paramNBTTagCompound.setByteArray("Biomes", arrayOfByte);
/* 115 */     paramNBTTagCompound.set("Entities", paramOldChunk.h);
/* 116 */     paramNBTTagCompound.set("TileEntities", paramOldChunk.i);
/* 117 */     if (paramOldChunk.j != null) {
/* 118 */       paramNBTTagCompound.set("TileTicks", paramOldChunk.j);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class OldChunk
/*     */   {
/*     */     public long a;
/*     */     
/*     */     public boolean b;
/*     */     public byte[] c;
/*     */     public OldNibbleArray d;
/*     */     public OldNibbleArray e;
/*     */     public OldNibbleArray f;
/*     */     public byte[] g;
/*     */     public NBTTagList h;
/*     */     public NBTTagList i;
/*     */     public NBTTagList j;
/*     */     public final int k;
/*     */     public final int l;
/*     */     
/*     */     public OldChunk(int param1Int1, int param1Int2) {
/* 140 */       this.k = param1Int1;
/* 141 */       this.l = param1Int2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\OldChunkLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */