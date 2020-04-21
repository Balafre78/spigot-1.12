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
/*    */ public class NBTTagFloat
/*    */   extends NBTNumber
/*    */ {
/*    */   private float data;
/*    */   
/*    */   NBTTagFloat() {}
/*    */   
/*    */   public NBTTagFloat(float paramFloat) {
/* 22 */     this.data = paramFloat;
/*    */   }
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {
/* 27 */     paramDataOutput.writeFloat(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 32 */     paramNBTReadLimiter.a(96L);
/* 33 */     this.data = paramDataInput.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 38 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     return this.data + "f";
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagFloat c() {
/* 48 */     return new NBTTagFloat(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 53 */     return (super.equals(paramObject) && this.data == ((NBTTagFloat)paramObject).data);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 58 */     return super.hashCode() ^ Float.floatToIntBits(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public long d() {
/* 63 */     return (long)this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e() {
/* 68 */     return MathHelper.d(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public short f() {
/* 73 */     return (short)(MathHelper.d(this.data) & 0xFFFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte g() {
/* 78 */     return (byte)(MathHelper.d(this.data) & 0xFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public double asDouble() {
/* 83 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public float i() {
/* 88 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */