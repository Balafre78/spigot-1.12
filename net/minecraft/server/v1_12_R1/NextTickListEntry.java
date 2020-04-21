/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class NextTickListEntry
/*    */   implements Comparable<NextTickListEntry>
/*    */ {
/*    */   private static long d;
/*    */   private final Block e;
/*    */   public final BlockPosition a;
/*    */   public long b;
/*    */   public int c;
/* 12 */   private final long f = d++;
/*    */   
/*    */   public NextTickListEntry(BlockPosition paramBlockPosition, Block paramBlock) {
/* 15 */     this.a = paramBlockPosition.h();
/* 16 */     this.e = paramBlock;
/*    */   }
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 20 */     if (paramObject instanceof NextTickListEntry) {
/* 21 */       NextTickListEntry nextTickListEntry = (NextTickListEntry)paramObject;
/* 22 */       return (this.a.equals(nextTickListEntry.a) && Block.a(this.e, nextTickListEntry.e));
/*    */     } 
/* 24 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 28 */     return this.a.hashCode();
/*    */   }
/*    */   
/*    */   public NextTickListEntry a(long paramLong) {
/* 32 */     this.b = paramLong;
/* 33 */     return this;
/*    */   }
/*    */   
/*    */   public void a(int paramInt) {
/* 37 */     this.c = paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(NextTickListEntry paramNextTickListEntry) {
/* 42 */     if (this.b < paramNextTickListEntry.b) {
/* 43 */       return -1;
/*    */     }
/* 45 */     if (this.b > paramNextTickListEntry.b) {
/* 46 */       return 1;
/*    */     }
/* 48 */     if (this.c != paramNextTickListEntry.c) {
/* 49 */       return this.c - paramNextTickListEntry.c;
/*    */     }
/* 51 */     if (this.f < paramNextTickListEntry.f) {
/* 52 */       return -1;
/*    */     }
/* 54 */     if (this.f > paramNextTickListEntry.f) {
/* 55 */       return 1;
/*    */     }
/* 57 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 61 */     return Block.getId(this.e) + ": " + this.a + ", " + this.b + ", " + this.c + ", " + this.f;
/*    */   }
/*    */   
/*    */   public Block a() {
/* 65 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NextTickListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */