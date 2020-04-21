/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockActionData
/*    */ {
/*    */   private final BlockPosition a;
/*    */   private final Block b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   public BlockActionData(BlockPosition paramBlockPosition, Block paramBlock, int paramInt1, int paramInt2) {
/* 13 */     this.a = paramBlockPosition;
/* 14 */     this.c = paramInt1;
/* 15 */     this.d = paramInt2;
/* 16 */     this.b = paramBlock;
/*    */   }
/*    */   
/*    */   public BlockPosition a() {
/* 20 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 24 */     return this.c;
/*    */   }
/*    */   
/*    */   public int c() {
/* 28 */     return this.d;
/*    */   }
/*    */   
/*    */   public Block d() {
/* 32 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 37 */     if (paramObject instanceof BlockActionData) {
/* 38 */       BlockActionData blockActionData = (BlockActionData)paramObject;
/* 39 */       return (this.a.equals(blockActionData.a) && this.c == blockActionData.c && this.d == blockActionData.d && this.b == blockActionData.b);
/*    */     } 
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "TE(" + this.a + ")," + this.c + "," + this.d + "," + this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockActionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */