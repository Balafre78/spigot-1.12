/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class ChunkCoordIntPair
/*    */ {
/*    */   public final int x;
/*    */   public final int z;
/*    */   
/*    */   public ChunkCoordIntPair(int paramInt1, int paramInt2) {
/* 10 */     this.x = paramInt1;
/* 11 */     this.z = paramInt2;
/*    */   }
/*    */   
/*    */   public ChunkCoordIntPair(BlockPosition paramBlockPosition) {
/* 15 */     this.x = paramBlockPosition.getX() >> 4;
/* 16 */     this.z = paramBlockPosition.getZ() >> 4;
/*    */   }
/*    */   
/*    */   public static long a(int paramInt1, int paramInt2) {
/* 20 */     return paramInt1 & 0xFFFFFFFFL | (paramInt2 & 0xFFFFFFFFL) << 32L;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 29 */     int i = 1664525 * this.x + 1013904223;
/* 30 */     int j = 1664525 * (this.z ^ 0xDEADBEEF) + 1013904223;
/* 31 */     return i ^ j;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 36 */     if (this == paramObject) {
/* 37 */       return true;
/*    */     }
/*    */     
/* 40 */     if (paramObject instanceof ChunkCoordIntPair) {
/* 41 */       ChunkCoordIntPair chunkCoordIntPair = (ChunkCoordIntPair)paramObject;
/*    */       
/* 43 */       return (this.x == chunkCoordIntPair.x && this.z == chunkCoordIntPair.z);
/*    */     } 
/*    */     
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public double a(Entity paramEntity) {
/* 50 */     double d1 = (this.x * 16 + 8);
/* 51 */     double d2 = (this.z * 16 + 8);
/*    */     
/* 53 */     double d3 = d1 - paramEntity.locX;
/* 54 */     double d4 = d2 - paramEntity.locZ;
/*    */     
/* 56 */     return d3 * d3 + d4 * d4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int c() {
/* 68 */     return this.x << 4;
/*    */   }
/*    */   
/*    */   public int d() {
/* 72 */     return this.z << 4;
/*    */   }
/*    */   
/*    */   public int e() {
/* 76 */     return (this.x << 4) + 15;
/*    */   }
/*    */   
/*    */   public int f() {
/* 80 */     return (this.z << 4) + 15;
/*    */   }
/*    */   
/*    */   public BlockPosition a(int paramInt1, int paramInt2, int paramInt3) {
/* 84 */     return new BlockPosition((this.x << 4) + paramInt1, paramInt2, (this.z << 4) + paramInt3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     return "[" + this.x + ", " + this.z + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkCoordIntPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */