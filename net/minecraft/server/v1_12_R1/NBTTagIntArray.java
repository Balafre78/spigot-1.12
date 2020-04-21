/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class NBTTagIntArray extends NBTBase {
/*     */   private int[] data;
/*     */   
/*     */   NBTTagIntArray() {}
/*     */   
/*     */   public NBTTagIntArray(int[] aint) {
/*  16 */     this.data = aint;
/*     */   }
/*     */   
/*     */   public NBTTagIntArray(List<Integer> list) {
/*  20 */     this(a(list));
/*     */   }
/*     */   
/*     */   private static int[] a(List<Integer> list) {
/*  24 */     int[] aint = new int[list.size()];
/*     */     
/*  26 */     for (int i = 0; i < list.size(); i++) {
/*  27 */       Integer integer = list.get(i);
/*     */       
/*  29 */       aint[i] = (integer == null) ? 0 : integer.intValue();
/*     */     } 
/*     */     
/*  32 */     return aint;
/*     */   }
/*     */   
/*     */   void write(DataOutput dataoutput) throws IOException {
/*  36 */     dataoutput.writeInt(this.data.length);
/*  37 */     int[] aint = this.data;
/*  38 */     int i = aint.length;
/*     */     
/*  40 */     for (int j = 0; j < i; j++) {
/*  41 */       int k = aint[j];
/*     */       
/*  43 */       dataoutput.writeInt(k);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/*  49 */     nbtreadlimiter.a(192L);
/*  50 */     int j = datainput.readInt();
/*  51 */     Preconditions.checkArgument((j < 16777216));
/*     */     
/*  53 */     nbtreadlimiter.a((32 * j));
/*  54 */     this.data = new int[j];
/*     */     
/*  56 */     for (int k = 0; k < j; k++) {
/*  57 */       this.data[k] = datainput.readInt();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  63 */     return 11;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  67 */     StringBuilder stringbuilder = new StringBuilder("[I;");
/*     */     
/*  69 */     for (int i = 0; i < this.data.length; i++) {
/*  70 */       if (i != 0) {
/*  71 */         stringbuilder.append(',');
/*     */       }
/*     */       
/*  74 */       stringbuilder.append(this.data[i]);
/*     */     } 
/*     */     
/*  77 */     return stringbuilder.append(']').toString();
/*     */   }
/*     */   
/*     */   public NBTTagIntArray c() {
/*  81 */     int[] aint = new int[this.data.length];
/*     */     
/*  83 */     System.arraycopy(this.data, 0, aint, 0, this.data.length);
/*  84 */     return new NBTTagIntArray(aint);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  88 */     return (super.equals(object) && Arrays.equals(this.data, ((NBTTagIntArray)object).data));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  92 */     return super.hashCode() ^ Arrays.hashCode(this.data);
/*     */   }
/*     */   
/*     */   public int[] d() {
/*  96 */     return this.data;
/*     */   }
/*     */   
/*     */   public NBTBase clone() {
/* 100 */     return c();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagIntArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */