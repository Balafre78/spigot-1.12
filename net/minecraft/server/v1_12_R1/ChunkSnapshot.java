/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChunkSnapshot
/*    */ {
/* 11 */   private static final IBlockData a = Blocks.AIR.getBlockData();
/*    */   
/* 13 */   private final char[] b = new char[65536];
/*    */   
/*    */   public IBlockData a(int paramInt1, int paramInt2, int paramInt3) {
/* 16 */     IBlockData iBlockData = Block.REGISTRY_ID.fromId(this.b[b(paramInt1, paramInt2, paramInt3)]);
/* 17 */     return (iBlockData == null) ? a : iBlockData;
/*    */   }
/*    */   
/*    */   public void a(int paramInt1, int paramInt2, int paramInt3, IBlockData paramIBlockData) {
/* 21 */     this.b[b(paramInt1, paramInt2, paramInt3)] = (char)Block.REGISTRY_ID.getId((T)paramIBlockData);
/*    */   }
/*    */   
/*    */   private static int b(int paramInt1, int paramInt2, int paramInt3) {
/* 25 */     return paramInt1 << 12 | paramInt3 << 8 | paramInt2;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt1, int paramInt2) {
/* 30 */     int i = (paramInt1 << 12 | paramInt2 << 8) + 256 - 1;
/* 31 */     for (char c = 'ÿ'; c >= '\000'; c--) {
/* 32 */       IBlockData iBlockData = Block.REGISTRY_ID.fromId(this.b[i + c]);
/*    */       
/* 34 */       if (iBlockData != null && iBlockData != a) {
/* 35 */         return c;
/*    */       }
/*    */     } 
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkSnapshot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */