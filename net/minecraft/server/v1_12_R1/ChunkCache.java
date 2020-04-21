/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class ChunkCache
/*     */   implements IBlockAccess
/*     */ {
/*     */   protected int a;
/*     */   protected int b;
/*     */   protected Chunk[][] c;
/*     */   protected boolean d;
/*     */   protected World e;
/*     */   
/*     */   public ChunkCache(World paramWorld, BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2, int paramInt) {
/*  24 */     this.e = paramWorld;
/*     */     
/*  26 */     this.a = paramBlockPosition1.getX() - paramInt >> 4;
/*  27 */     this.b = paramBlockPosition1.getZ() - paramInt >> 4;
/*  28 */     int i = paramBlockPosition2.getX() + paramInt >> 4;
/*  29 */     int j = paramBlockPosition2.getZ() + paramInt >> 4;
/*     */     
/*  31 */     this.c = new Chunk[i - this.a + 1][j - this.b + 1];
/*     */     
/*  33 */     this.d = true; int k;
/*  34 */     for (k = this.a; k <= i; k++) {
/*  35 */       for (int m = this.b; m <= j; m++) {
/*  36 */         this.c[k - this.a][m - this.b] = paramWorld.getChunkAt(k, m);
/*     */       }
/*     */     } 
/*     */     
/*  40 */     for (k = paramBlockPosition1.getX() >> 4; k <= paramBlockPosition2.getX() >> 4; k++) {
/*  41 */       for (int m = paramBlockPosition1.getZ() >> 4; m <= paramBlockPosition2.getZ() >> 4; m++) {
/*  42 */         Chunk chunk = this.c[k - this.a][m - this.b];
/*  43 */         if (chunk != null && 
/*  44 */           !chunk.c(paramBlockPosition1.getY(), paramBlockPosition2.getY())) {
/*  45 */           this.d = false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity getTileEntity(BlockPosition paramBlockPosition) {
/*  60 */     return a(paramBlockPosition, Chunk.EnumTileEntityState.IMMEDIATE);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public TileEntity a(BlockPosition paramBlockPosition, Chunk.EnumTileEntityState paramEnumTileEntityState) {
/*  65 */     int i = (paramBlockPosition.getX() >> 4) - this.a;
/*  66 */     int j = (paramBlockPosition.getZ() >> 4) - this.b;
/*     */     
/*  68 */     return this.c[i][j].a(paramBlockPosition, paramEnumTileEntityState);
/*     */   }
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
/*     */ 
/*     */   
/*     */   public IBlockData getType(BlockPosition paramBlockPosition) {
/* 139 */     if (paramBlockPosition.getY() >= 0 && 
/* 140 */       paramBlockPosition.getY() < 256) {
/* 141 */       int i = (paramBlockPosition.getX() >> 4) - this.a;
/* 142 */       int j = (paramBlockPosition.getZ() >> 4) - this.b;
/*     */       
/* 144 */       if (i >= 0 && i < this.c.length && j >= 0 && j < (this.c[i]).length) {
/* 145 */         Chunk chunk = this.c[i][j];
/* 146 */         if (chunk != null) {
/* 147 */           return chunk.getBlockData(paramBlockPosition);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 153 */     return Blocks.AIR.getBlockData();
/*     */   }
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
/*     */   public boolean isEmpty(BlockPosition paramBlockPosition) {
/* 199 */     return (getType(paramBlockPosition).getMaterial() == Material.AIR);
/*     */   }
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
/*     */   public int getBlockPower(BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 220 */     return getType(paramBlockPosition).b(this, paramBlockPosition, paramEnumDirection);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */