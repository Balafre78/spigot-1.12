/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
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
/*     */ public class ChunkProviderDebug
/*     */   implements ChunkGenerator
/*     */ {
/*  22 */   private static final List<IBlockData> c = Lists.newArrayList();
/*     */   
/*     */   private static final int d;
/*     */   private static final int e;
/*  26 */   protected static final IBlockData a = Blocks.AIR.getBlockData();
/*  27 */   protected static final IBlockData b = Blocks.BARRIER.getBlockData();
/*     */   
/*     */   private final World f;
/*     */   
/*     */   static {
/*  32 */     for (Block block : Block.REGISTRY) {
/*  33 */       c.addAll((Collection<? extends IBlockData>)block.s().a());
/*     */     }
/*     */     
/*  36 */     d = MathHelper.f(MathHelper.c(c.size()));
/*  37 */     e = MathHelper.f(c.size() / d);
/*     */   }
/*     */   
/*     */   public ChunkProviderDebug(World paramWorld) {
/*  41 */     this.f = paramWorld;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getOrCreateChunk(int paramInt1, int paramInt2) {
/*  46 */     ChunkSnapshot chunkSnapshot = new ChunkSnapshot();
/*     */     
/*  48 */     for (byte b1 = 0; b1 < 16; b1++) {
/*  49 */       for (byte b = 0; b < 16; b++) {
/*  50 */         int i = paramInt1 * 16 + b1;
/*  51 */         int j = paramInt2 * 16 + b;
/*     */         
/*  53 */         chunkSnapshot.a(b1, 60, b, b);
/*     */         
/*  55 */         IBlockData iBlockData = c(i, j);
/*  56 */         if (iBlockData != null) {
/*  57 */           chunkSnapshot.a(b1, 70, b, iBlockData);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  62 */     Chunk chunk = new Chunk(this.f, chunkSnapshot, paramInt1, paramInt2);
/*  63 */     chunk.initLighting();
/*     */     
/*  65 */     BiomeBase[] arrayOfBiomeBase = this.f.getWorldChunkManager().getBiomeBlock(null, paramInt1 * 16, paramInt2 * 16, 16, 16);
/*  66 */     byte[] arrayOfByte = chunk.getBiomeIndex();
/*     */     
/*  68 */     for (byte b2 = 0; b2 < arrayOfByte.length; b2++) {
/*  69 */       arrayOfByte[b2] = (byte)BiomeBase.a(arrayOfBiomeBase[b2]);
/*     */     }
/*     */     
/*  72 */     chunk.initLighting();
/*     */     
/*  74 */     return chunk;
/*     */   }
/*     */   
/*     */   public static IBlockData c(int paramInt1, int paramInt2) {
/*  78 */     IBlockData iBlockData = a;
/*     */     
/*  80 */     if (paramInt1 > 0 && paramInt2 > 0 && paramInt1 % 2 != 0 && paramInt2 % 2 != 0) {
/*  81 */       paramInt1 /= 2;
/*  82 */       paramInt2 /= 2;
/*     */       
/*  84 */       if (paramInt1 <= d && paramInt2 <= e) {
/*  85 */         int i = MathHelper.a(paramInt1 * d + paramInt2);
/*  86 */         if (i < c.size()) {
/*  87 */           iBlockData = c.get(i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  92 */     return iBlockData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void recreateStructures(int paramInt1, int paramInt2) {}
/*     */ 
/*     */   
/*     */   public boolean a(Chunk paramChunk, int paramInt1, int paramInt2) {
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType paramEnumCreatureType, BlockPosition paramBlockPosition) {
/* 106 */     BiomeBase biomeBase = this.f.getBiome(paramBlockPosition);
/* 107 */     return biomeBase.getMobs(paramEnumCreatureType);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition findNearestMapFeature(World paramWorld, String paramString, BlockPosition paramBlockPosition, boolean paramBoolean) {
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World paramWorld, String paramString, BlockPosition paramBlockPosition) {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public void recreateStructures(Chunk paramChunk, int paramInt1, int paramInt2) {}
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkProviderDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */