/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class NBTReadLimiter {
/*  4 */   public static final NBTReadLimiter a = new NBTReadLimiter(0L)
/*    */     {
/*    */       public void a(long param1Long) {}
/*    */     };
/*    */ 
/*    */   
/*    */   private final long b;
/*    */   
/*    */   private long c;
/*    */   
/*    */   public NBTReadLimiter(long paramLong) {
/* 15 */     this.b = paramLong;
/*    */   }
/*    */   
/*    */   public void a(long paramLong) {
/* 19 */     this.c += paramLong / 8L;
/* 20 */     if (this.c > this.b)
/* 21 */       throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.c + "bytes where max allowed: " + this.b); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTReadLimiter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */