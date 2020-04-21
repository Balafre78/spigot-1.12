/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class NBTTagByteArray extends NBTBase {
/*    */   private byte[] data;
/*    */   
/*    */   NBTTagByteArray() {}
/*    */   
/*    */   public NBTTagByteArray(byte[] abyte) {
/* 16 */     this.data = abyte;
/*    */   }
/*    */   
/*    */   public NBTTagByteArray(List<Byte> list) {
/* 20 */     this(a(list));
/*    */   }
/*    */   
/*    */   private static byte[] a(List<Byte> list) {
/* 24 */     byte[] abyte = new byte[list.size()];
/*    */     
/* 26 */     for (int i = 0; i < list.size(); i++) {
/* 27 */       Byte obyte = list.get(i);
/*    */       
/* 29 */       abyte[i] = (obyte == null) ? 0 : obyte.byteValue();
/*    */     } 
/*    */     
/* 32 */     return abyte;
/*    */   }
/*    */   
/*    */   void write(DataOutput dataoutput) throws IOException {
/* 36 */     dataoutput.writeInt(this.data.length);
/* 37 */     dataoutput.write(this.data);
/*    */   }
/*    */   
/*    */   void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/* 41 */     nbtreadlimiter.a(192L);
/* 42 */     int j = datainput.readInt();
/* 43 */     Preconditions.checkArgument((j < 16777216));
/*    */     
/* 45 */     nbtreadlimiter.a((8 * j));
/* 46 */     this.data = new byte[j];
/* 47 */     datainput.readFully(this.data);
/*    */   }
/*    */   
/*    */   public byte getTypeId() {
/* 51 */     return 7;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 55 */     StringBuilder stringbuilder = new StringBuilder("[B;");
/*    */     
/* 57 */     for (int i = 0; i < this.data.length; i++) {
/* 58 */       if (i != 0) {
/* 59 */         stringbuilder.append(',');
/*    */       }
/*    */       
/* 62 */       stringbuilder.append(this.data[i]).append('B');
/*    */     } 
/*    */     
/* 65 */     return stringbuilder.append(']').toString();
/*    */   }
/*    */   
/*    */   public NBTBase clone() {
/* 69 */     byte[] abyte = new byte[this.data.length];
/*    */     
/* 71 */     System.arraycopy(this.data, 0, abyte, 0, this.data.length);
/* 72 */     return new NBTTagByteArray(abyte);
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 76 */     return (super.equals(object) && Arrays.equals(this.data, ((NBTTagByteArray)object).data));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 80 */     return super.hashCode() ^ Arrays.hashCode(this.data);
/*    */   }
/*    */   
/*    */   public byte[] c() {
/* 84 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagByteArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */