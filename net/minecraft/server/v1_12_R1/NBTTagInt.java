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
/*    */ public class NBTTagInt
/*    */   extends NBTNumber
/*    */ {
/*    */   private int data;
/*    */   
/*    */   NBTTagInt() {}
/*    */   
/*    */   public NBTTagInt(int paramInt) {
/* 20 */     this.data = paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {
/* 25 */     paramDataOutput.writeInt(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 30 */     paramNBTReadLimiter.a(96L);
/* 31 */     this.data = paramDataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 36 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return String.valueOf(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagInt c() {
/* 46 */     return new NBTTagInt(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 51 */     return (super.equals(paramObject) && this.data == ((NBTTagInt)paramObject).data);
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
/* 71 */     return (short)(this.data & 0xFFFF);
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


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */