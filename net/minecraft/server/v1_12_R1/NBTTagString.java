/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTTagString
/*    */   extends NBTBase
/*    */ {
/*    */   private String data;
/*    */   
/*    */   public NBTTagString() {
/* 18 */     this("");
/*    */   }
/*    */   
/*    */   public NBTTagString(String paramString) {
/* 22 */     Objects.requireNonNull(paramString, "Null string not allowed");
/*    */     
/* 24 */     this.data = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {
/* 29 */     paramDataOutput.writeUTF(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 34 */     paramNBTReadLimiter.a(288L);
/*    */ 
/*    */     
/* 37 */     this.data = paramDataInput.readUTF();
/* 38 */     paramNBTReadLimiter.a((16 * this.data.length()));
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 43 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return a(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagString c() {
/* 53 */     return new NBTTagString(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 58 */     return this.data.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 63 */     if (!super.equals(paramObject)) {
/* 64 */       return false;
/*    */     }
/*    */     
/* 67 */     NBTTagString nBTTagString = (NBTTagString)paramObject;
/* 68 */     return ((this.data == null && nBTTagString.data == null) || Objects.equals(this.data, nBTTagString.data));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 73 */     return super.hashCode() ^ this.data.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String c_() {
/* 78 */     return this.data;
/*    */   }
/*    */   
/*    */   public static String a(String paramString) {
/* 82 */     StringBuilder stringBuilder = new StringBuilder("\"");
/* 83 */     for (byte b = 0; b < paramString.length(); b++) {
/* 84 */       char c = paramString.charAt(b);
/* 85 */       if (c == '\\' || c == '"') {
/* 86 */         stringBuilder.append('\\');
/*    */       }
/* 88 */       stringBuilder.append(c);
/*    */     } 
/* 90 */     return stringBuilder.append('"').toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */