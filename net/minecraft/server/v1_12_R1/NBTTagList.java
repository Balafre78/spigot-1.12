/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class NBTTagList
/*     */   extends NBTBase
/*     */ {
/*  15 */   private static final Logger b = LogManager.getLogger();
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
/*  28 */   private List<NBTBase> list = Lists.newArrayList();
/*  29 */   private byte type = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput paramDataOutput) throws IOException {
/*  36 */     if (this.list.isEmpty()) {
/*  37 */       this.type = 0;
/*     */     } else {
/*  39 */       this.type = ((NBTBase)this.list.get(0)).getTypeId();
/*     */     } 
/*     */     
/*  42 */     paramDataOutput.writeByte(this.type);
/*  43 */     paramDataOutput.writeInt(this.list.size());
/*  44 */     for (byte b = 0; b < this.list.size(); b++) {
/*  45 */       ((NBTBase)this.list.get(b)).write(paramDataOutput);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/*  51 */     paramNBTReadLimiter.a(296L);
/*     */     
/*  53 */     if (paramInt > 512) {
/*  54 */       throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */     }
/*  56 */     this.type = paramDataInput.readByte();
/*  57 */     int i = paramDataInput.readInt();
/*  58 */     if (this.type == 0 && i > 0) {
/*  59 */       throw new RuntimeException("Missing type on ListTag");
/*     */     }
/*  61 */     paramNBTReadLimiter.a(32L * i);
/*     */     
/*  63 */     this.list = Lists.newArrayListWithCapacity(i);
/*  64 */     for (byte b = 0; b < i; b++) {
/*  65 */       NBTBase nBTBase = NBTBase.createTag(this.type);
/*  66 */       nBTBase.load(paramDataInput, paramInt + 1, paramNBTReadLimiter);
/*  67 */       this.list.add(nBTBase);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  73 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  78 */     StringBuilder stringBuilder = new StringBuilder("[");
/*  79 */     for (byte b = 0; b < this.list.size(); b++) {
/*  80 */       if (b != 0) {
/*  81 */         stringBuilder.append(',');
/*     */       }
/*  83 */       stringBuilder.append(this.list.get(b));
/*     */     } 
/*  85 */     return stringBuilder.append(']').toString();
/*     */   }
/*     */   
/*     */   public void add(NBTBase paramNBTBase) {
/*  89 */     if (paramNBTBase.getTypeId() == 0) {
/*  90 */       b.warn("Invalid TagEnd added to ListTag");
/*     */       return;
/*     */     } 
/*  93 */     if (this.type == 0) {
/*  94 */       this.type = paramNBTBase.getTypeId();
/*  95 */     } else if (this.type != paramNBTBase.getTypeId()) {
/*  96 */       b.warn("Adding mismatching tag types to tag list");
/*     */       return;
/*     */     } 
/*  99 */     this.list.add(paramNBTBase);
/*     */   }
/*     */   
/*     */   public void a(int paramInt, NBTBase paramNBTBase) {
/* 103 */     if (paramNBTBase.getTypeId() == 0) {
/* 104 */       b.warn("Invalid TagEnd added to ListTag");
/*     */       return;
/*     */     } 
/* 107 */     if (paramInt < 0 || paramInt >= this.list.size()) {
/* 108 */       b.warn("index out of bounds to set tag in tag list");
/*     */       return;
/*     */     } 
/* 111 */     if (this.type == 0) {
/* 112 */       this.type = paramNBTBase.getTypeId();
/* 113 */     } else if (this.type != paramNBTBase.getTypeId()) {
/* 114 */       b.warn("Adding mismatching tag types to tag list");
/*     */       return;
/*     */     } 
/* 117 */     this.list.set(paramInt, paramNBTBase);
/*     */   }
/*     */   
/*     */   public NBTBase remove(int paramInt) {
/* 121 */     return this.list.remove(paramInt);
/*     */   }
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
/*     */   public boolean isEmpty() {
/* 137 */     return this.list.isEmpty();
/*     */   }
/*     */   
/*     */   public NBTTagCompound get(int paramInt) {
/* 141 */     if (paramInt >= 0 && paramInt < this.list.size()) {
/* 142 */       NBTBase nBTBase = this.list.get(paramInt);
/* 143 */       if (nBTBase.getTypeId() == 10) {
/* 144 */         return (NBTTagCompound)nBTBase;
/*     */       }
/*     */     } 
/* 147 */     return new NBTTagCompound();
/*     */   }
/*     */   
/*     */   public int c(int paramInt) {
/* 151 */     if (paramInt >= 0 && paramInt < this.list.size()) {
/* 152 */       NBTBase nBTBase = this.list.get(paramInt);
/* 153 */       if (nBTBase.getTypeId() == 3) {
/* 154 */         return ((NBTTagInt)nBTBase).e();
/*     */       }
/*     */     } 
/* 157 */     return 0;
/*     */   }
/*     */   
/*     */   public int[] d(int paramInt) {
/* 161 */     if (paramInt >= 0 && paramInt < this.list.size()) {
/* 162 */       NBTBase nBTBase = this.list.get(paramInt);
/* 163 */       if (nBTBase.getTypeId() == 11) {
/* 164 */         return ((NBTTagIntArray)nBTBase).d();
/*     */       }
/*     */     } 
/* 167 */     return new int[0];
/*     */   }
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
/*     */   public double f(int paramInt) {
/* 181 */     if (paramInt >= 0 && paramInt < this.list.size()) {
/* 182 */       NBTBase nBTBase = this.list.get(paramInt);
/* 183 */       if (nBTBase.getTypeId() == 6) {
/* 184 */         return ((NBTTagDouble)nBTBase).asDouble();
/*     */       }
/*     */     } 
/* 187 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public float g(int paramInt) {
/* 191 */     if (paramInt >= 0 && paramInt < this.list.size()) {
/* 192 */       NBTBase nBTBase = this.list.get(paramInt);
/* 193 */       if (nBTBase.getTypeId() == 5) {
/* 194 */         return ((NBTTagFloat)nBTBase).i();
/*     */       }
/*     */     } 
/* 197 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public String getString(int paramInt) {
/* 201 */     if (paramInt < 0 || paramInt >= this.list.size()) {
/* 202 */       return "";
/*     */     }
/* 204 */     NBTBase nBTBase = this.list.get(paramInt);
/* 205 */     if (nBTBase.getTypeId() == 8) {
/* 206 */       return nBTBase.c_();
/*     */     }
/* 208 */     return nBTBase.toString();
/*     */   }
/*     */   
/*     */   public NBTBase i(int paramInt) {
/* 212 */     if (paramInt < 0 || paramInt >= this.list.size()) {
/* 213 */       return new NBTTagEnd();
/*     */     }
/* 215 */     return this.list.get(paramInt);
/*     */   }
/*     */   
/*     */   public int size() {
/* 219 */     return this.list.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagList d() {
/* 224 */     NBTTagList nBTTagList = new NBTTagList();
/* 225 */     nBTTagList.type = this.type;
/* 226 */     for (NBTBase nBTBase1 : this.list) {
/* 227 */       NBTBase nBTBase2 = nBTBase1.clone();
/* 228 */       nBTTagList.list.add(nBTBase2);
/*     */     } 
/* 230 */     return nBTTagList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 235 */     if (!super.equals(paramObject)) {
/* 236 */       return false;
/*     */     }
/*     */     
/* 239 */     NBTTagList nBTTagList = (NBTTagList)paramObject;
/* 240 */     return (this.type == nBTTagList.type && Objects.equals(this.list, nBTTagList.list));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 245 */     return super.hashCode() ^ this.list.hashCode();
/*     */   }
/*     */   
/*     */   public int g() {
/* 249 */     return this.type;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */