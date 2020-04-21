/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTTagLong
/*    */   extends NBTNumber
/*    */ {
/*    */   private long data;
/*    */   
/*    */   NBTTagLong() {}
/*    */   
/*    */   public NBTTagLong(long paramLong) {
/* 20 */     this.data = paramLong;
/*    */   }
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {
/* 25 */     paramDataOutput.writeLong(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 30 */     paramNBTReadLimiter.a(128L);
/* 31 */     this.data = paramDataInput.readLong();
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 36 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return this.data + "L";
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagLong c() {
/* 46 */     return new NBTTagLong(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 51 */     return (super.equals(paramObject) && this.data == ((NBTTagLong)paramObject).data);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 56 */     return super.hashCode() ^ (int)(this.data ^ this.data >>> 32L);
/*    */   }
/*    */ 
/*    */   
/*    */   public long d() {
/* 61 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e() {
/* 66 */     return (int)(this.data & 0xFFFFFFFFFFFFFFFFL);
/*    */   }
/*    */ 
/*    */   
/*    */   public short f() {
/* 71 */     return (short)(int)(this.data & 0xFFFFL);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte g() {
/* 76 */     return (byte)(int)(this.data & 0xFFL);
/*    */   }
/*    */ 
/*    */   
/*    */   public double asDouble() {
/* 81 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public float i() {
/* 86 */     return (float)this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagLong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */