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
/*    */ public class NBTTagShort
/*    */   extends NBTNumber
/*    */ {
/*    */   private short data;
/*    */   
/*    */   public NBTTagShort() {}
/*    */   
/*    */   public NBTTagShort(short paramShort) {
/* 20 */     this.data = paramShort;
/*    */   }
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {
/* 25 */     paramDataOutput.writeShort(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 30 */     paramNBTReadLimiter.a(80L);
/* 31 */     this.data = paramDataInput.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 36 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return this.data + "s";
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagShort c() {
/* 46 */     return new NBTTagShort(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 51 */     return (super.equals(paramObject) && this.data == ((NBTTagShort)paramObject).data);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 56 */     return super.hashCode() ^ this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public long d() {
/* 61 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e() {
/* 66 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public short f() {
/* 71 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte g() {
/* 76 */     return (byte)(this.data & 0xFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public double asDouble() {
/* 81 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public float i() {
/* 86 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagShort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */