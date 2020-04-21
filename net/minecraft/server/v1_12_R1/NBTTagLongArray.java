/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTTagLongArray
/*    */   extends NBTBase
/*    */ {
/*    */   private long[] b;
/*    */   
/*    */   NBTTagLongArray() {}
/*    */   
/*    */   public NBTTagLongArray(long[] paramArrayOflong) {
/* 23 */     this.b = paramArrayOflong;
/*    */   }
/*    */   
/*    */   public NBTTagLongArray(List<Long> paramList) {
/* 27 */     this(a(paramList));
/*    */   }
/*    */   
/*    */   private static long[] a(List<Long> paramList) {
/* 31 */     long[] arrayOfLong = new long[paramList.size()];
/* 32 */     for (byte b = 0; b < paramList.size(); b++) {
/* 33 */       Long long_ = paramList.get(b);
/* 34 */       arrayOfLong[b] = (long_ == null) ? 0L : long_.longValue();
/*    */     } 
/*    */     
/* 37 */     return arrayOfLong;
/*    */   }
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {
/* 42 */     paramDataOutput.writeInt(this.b.length);
/* 43 */     for (long l : this.b) {
/* 44 */       paramDataOutput.writeLong(l);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 50 */     paramNBTReadLimiter.a(192L);
/*    */     
/* 52 */     int i = paramDataInput.readInt();
/* 53 */     paramNBTReadLimiter.a((64 * i));
/* 54 */     this.b = new long[i];
/* 55 */     for (byte b = 0; b < i; b++) {
/* 56 */       this.b[b] = paramDataInput.readLong();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 62 */     return 12;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     StringBuilder stringBuilder = new StringBuilder("[L;");
/* 68 */     for (byte b = 0; b < this.b.length; b++) {
/* 69 */       if (b != 0) {
/* 70 */         stringBuilder.append(',');
/*    */       }
/* 72 */       stringBuilder.append(this.b[b]).append('L');
/*    */     } 
/* 74 */     return stringBuilder.append(']').toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagLongArray c() {
/* 79 */     long[] arrayOfLong = new long[this.b.length];
/* 80 */     System.arraycopy(this.b, 0, arrayOfLong, 0, this.b.length);
/* 81 */     return new NBTTagLongArray(arrayOfLong);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 86 */     return (super.equals(paramObject) && Arrays.equals(this.b, ((NBTTagLongArray)paramObject).b));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 91 */     return super.hashCode() ^ Arrays.hashCode(this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagLongArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */