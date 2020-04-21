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
/*    */ 
/*    */ 
/*    */ public class NBTTagDouble
/*    */   extends NBTNumber
/*    */ {
/*    */   private double data;
/*    */   
/*    */   NBTTagDouble() {}
/*    */   
/*    */   public NBTTagDouble(double paramDouble) {
/* 22 */     this.data = paramDouble;
/*    */   }
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {
/* 27 */     paramDataOutput.writeDouble(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 32 */     paramNBTReadLimiter.a(128L);
/* 33 */     this.data = paramDataInput.readDouble();
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 38 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     return this.data + "d";
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagDouble c() {
/* 48 */     return new NBTTagDouble(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 53 */     return (super.equals(paramObject) && this.data == ((NBTTagDouble)paramObject).data);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 58 */     long l = Double.doubleToLongBits(this.data);
/* 59 */     return super.hashCode() ^ (int)(l ^ l >>> 32L);
/*    */   }
/*    */ 
/*    */   
/*    */   public long d() {
/* 64 */     return (long)Math.floor(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public int e() {
/* 69 */     return MathHelper.floor(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public short f() {
/* 74 */     return (short)(MathHelper.floor(this.data) & 0xFFFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte g() {
/* 79 */     return (byte)(MathHelper.floor(this.data) & 0xFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public double asDouble() {
/* 84 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public float i() {
/* 89 */     return (float)this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */