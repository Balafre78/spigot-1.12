/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ public class ChunkSection
/*     */ {
/*     */   private final int yPos;
/*     */   private int nonEmptyBlockCount;
/*     */   private int tickingBlockCount;
/*     */   private final DataPaletteBlock blockIds;
/*     */   private NibbleArray emittedLight;
/*     */   private NibbleArray skyLight;
/*     */   
/*     */   public ChunkSection(int i, boolean flag) {
/*  13 */     this.yPos = i;
/*  14 */     this.blockIds = new DataPaletteBlock();
/*  15 */     this.emittedLight = new NibbleArray();
/*  16 */     if (flag) {
/*  17 */       this.skyLight = new NibbleArray();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkSection(int y, boolean flag, char[] blockIds) {
/*  24 */     this.yPos = y;
/*  25 */     this.blockIds = new DataPaletteBlock();
/*  26 */     for (int i = 0; i < blockIds.length; i++) {
/*  27 */       int xx = i & 0xF;
/*  28 */       int yy = i >> 8 & 0xF;
/*  29 */       int zz = i >> 4 & 0xF;
/*  30 */       this.blockIds.setBlock(xx, yy, zz, Block.REGISTRY_ID.fromId(blockIds[i]));
/*     */     } 
/*  32 */     this.emittedLight = new NibbleArray();
/*  33 */     if (flag) {
/*  34 */       this.skyLight = new NibbleArray();
/*     */     }
/*  36 */     recalcBlockCounts();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getType(int i, int j, int k) {
/*  41 */     return this.blockIds.a(i, j, k);
/*     */   }
/*     */   
/*     */   public void setType(int i, int j, int k, IBlockData iblockdata) {
/*  45 */     IBlockData iblockdata1 = getType(i, j, k);
/*  46 */     Block block = iblockdata1.getBlock();
/*  47 */     Block block1 = iblockdata.getBlock();
/*     */     
/*  49 */     if (block != Blocks.AIR) {
/*  50 */       this.nonEmptyBlockCount--;
/*  51 */       if (block.isTicking()) {
/*  52 */         this.tickingBlockCount--;
/*     */       }
/*     */     } 
/*     */     
/*  56 */     if (block1 != Blocks.AIR) {
/*  57 */       this.nonEmptyBlockCount++;
/*  58 */       if (block1.isTicking()) {
/*  59 */         this.tickingBlockCount++;
/*     */       }
/*     */     } 
/*     */     
/*  63 */     this.blockIds.setBlock(i, j, k, iblockdata);
/*     */   }
/*     */   
/*     */   public boolean a() {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   public boolean shouldTick() {
/*  71 */     return (this.tickingBlockCount > 0);
/*     */   }
/*     */   
/*     */   public int getYPosition() {
/*  75 */     return this.yPos;
/*     */   }
/*     */   
/*     */   public void a(int i, int j, int k, int l) {
/*  79 */     this.skyLight.a(i, j, k, l);
/*     */   }
/*     */   
/*     */   public int b(int i, int j, int k) {
/*  83 */     return this.skyLight.a(i, j, k);
/*     */   }
/*     */   
/*     */   public void b(int i, int j, int k, int l) {
/*  87 */     this.emittedLight.a(i, j, k, l);
/*     */   }
/*     */   
/*     */   public int c(int i, int j, int k) {
/*  91 */     return this.emittedLight.a(i, j, k);
/*     */   }
/*     */   
/*     */   public void recalcBlockCounts() {
/*  95 */     this.nonEmptyBlockCount = 0;
/*  96 */     this.tickingBlockCount = 0;
/*     */     
/*  98 */     for (int i = 0; i < 16; i++) {
/*  99 */       for (int j = 0; j < 16; j++) {
/* 100 */         for (int k = 0; k < 16; k++) {
/* 101 */           Block block = getType(i, j, k).getBlock();
/*     */           
/* 103 */           if (block != Blocks.AIR) {
/* 104 */             this.nonEmptyBlockCount++;
/* 105 */             if (block.isTicking()) {
/* 106 */               this.tickingBlockCount++;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DataPaletteBlock getBlocks() {
/* 116 */     return this.blockIds;
/*     */   }
/*     */   
/*     */   public NibbleArray getEmittedLightArray() {
/* 120 */     return this.emittedLight;
/*     */   }
/*     */   
/*     */   public NibbleArray getSkyLightArray() {
/* 124 */     return this.skyLight;
/*     */   }
/*     */   
/*     */   public void a(NibbleArray nibblearray) {
/* 128 */     this.emittedLight = nibblearray;
/*     */   }
/*     */   
/*     */   public void b(NibbleArray nibblearray) {
/* 132 */     this.skyLight = nibblearray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */