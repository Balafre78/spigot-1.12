/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NBTBase
/*     */ {
/*  31 */   public static final String[] a = new String[] { "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]", "LONG[]" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void write(DataOutput paramDataOutput) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String toString();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract byte getTypeId();
/*     */ 
/*     */ 
/*     */   
/*     */   protected static NBTBase createTag(byte paramByte) {
/*  53 */     switch (paramByte) {
/*     */       case 0:
/*  55 */         return new NBTTagEnd();
/*     */       case 1:
/*  57 */         return new NBTTagByte();
/*     */       case 2:
/*  59 */         return new NBTTagShort();
/*     */       case 3:
/*  61 */         return new NBTTagInt();
/*     */       case 4:
/*  63 */         return new NBTTagLong();
/*     */       case 5:
/*  65 */         return new NBTTagFloat();
/*     */       case 6:
/*  67 */         return new NBTTagDouble();
/*     */       case 7:
/*  69 */         return new NBTTagByteArray();
/*     */       case 11:
/*  71 */         return new NBTTagIntArray();
/*     */       case 12:
/*  73 */         return new NBTTagLongArray();
/*     */       case 8:
/*  75 */         return new NBTTagString();
/*     */       case 9:
/*  77 */         return new NBTTagList();
/*     */       case 10:
/*  79 */         return new NBTTagCompound();
/*     */     } 
/*  81 */     return null;
/*     */   }
/*     */   
/*     */   public static String j(int paramInt) {
/*  85 */     switch (paramInt) {
/*     */       case 0:
/*  87 */         return "TAG_End";
/*     */       case 1:
/*  89 */         return "TAG_Byte";
/*     */       case 2:
/*  91 */         return "TAG_Short";
/*     */       case 3:
/*  93 */         return "TAG_Int";
/*     */       case 4:
/*  95 */         return "TAG_Long";
/*     */       case 5:
/*  97 */         return "TAG_Float";
/*     */       case 6:
/*  99 */         return "TAG_Double";
/*     */       case 7:
/* 101 */         return "TAG_Byte_Array";
/*     */       case 11:
/* 103 */         return "TAG_Int_Array";
/*     */       case 12:
/* 105 */         return "TAG_Long_Array";
/*     */       case 8:
/* 107 */         return "TAG_String";
/*     */       case 9:
/* 109 */         return "TAG_List";
/*     */       case 10:
/* 111 */         return "TAG_Compound";
/*     */       case 99:
/* 113 */         return "Any Numeric Tag";
/*     */     } 
/* 115 */     return "UNKNOWN";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract NBTBase clone();
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 129 */     return (paramObject instanceof NBTBase && getTypeId() == ((NBTBase)paramObject).getTypeId());
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 134 */     return getTypeId();
/*     */   }
/*     */   
/*     */   protected String c_() {
/* 138 */     return toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */