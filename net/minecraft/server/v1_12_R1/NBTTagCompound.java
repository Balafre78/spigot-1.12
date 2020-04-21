/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NBTTagCompound
/*     */   extends NBTBase
/*     */ {
/*  26 */   private static final Logger b = LogManager.getLogger();
/*  27 */   private static final Pattern c = Pattern.compile("[A-Za-z0-9._+-]+");
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
/*  51 */   private final Map<String, NBTBase> map = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput paramDataOutput) throws IOException {
/*  58 */     for (String str : this.map.keySet()) {
/*  59 */       NBTBase nBTBase = this.map.get(str);
/*  60 */       a(str, nBTBase, paramDataOutput);
/*     */     } 
/*  62 */     paramDataOutput.writeByte(0);
/*     */   }
/*     */ 
/*     */   
/*     */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/*  67 */     paramNBTReadLimiter.a(384L);
/*     */     
/*  69 */     if (paramInt > 512) {
/*  70 */       throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */     }
/*  72 */     this.map.clear();
/*     */     byte b;
/*  74 */     while ((b = a(paramDataInput, paramNBTReadLimiter)) != 0) {
/*  75 */       String str = b(paramDataInput, paramNBTReadLimiter);
/*  76 */       paramNBTReadLimiter.a((224 + 16 * str.length()));
/*     */       
/*  78 */       NBTBase nBTBase = a(b, str, paramDataInput, paramInt + 1, paramNBTReadLimiter);
/*  79 */       if (this.map.put(str, nBTBase) != null) {
/*  80 */         paramNBTReadLimiter.a(288L);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<String> c() {
/*  86 */     return this.map.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  91 */     return 10;
/*     */   }
/*     */   
/*     */   public int d() {
/*  95 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public void set(String paramString, NBTBase paramNBTBase) {
/*  99 */     this.map.put(paramString, paramNBTBase);
/*     */   }
/*     */   
/*     */   public void setByte(String paramString, byte paramByte) {
/* 103 */     this.map.put(paramString, new NBTTagByte(paramByte));
/*     */   }
/*     */   
/*     */   public void setShort(String paramString, short paramShort) {
/* 107 */     this.map.put(paramString, new NBTTagShort(paramShort));
/*     */   }
/*     */   
/*     */   public void setInt(String paramString, int paramInt) {
/* 111 */     this.map.put(paramString, new NBTTagInt(paramInt));
/*     */   }
/*     */   
/*     */   public void setLong(String paramString, long paramLong) {
/* 115 */     this.map.put(paramString, new NBTTagLong(paramLong));
/*     */   }
/*     */   
/*     */   public void a(String paramString, UUID paramUUID) {
/* 119 */     setLong(paramString + "Most", paramUUID.getMostSignificantBits());
/* 120 */     setLong(paramString + "Least", paramUUID.getLeastSignificantBits());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID a(String paramString) {
/* 125 */     return new UUID(getLong(paramString + "Most"), getLong(paramString + "Least"));
/*     */   }
/*     */   
/*     */   public boolean b(String paramString) {
/* 129 */     return (hasKeyOfType(paramString + "Most", 99) && hasKeyOfType(paramString + "Least", 99));
/*     */   }
/*     */   
/*     */   public void setFloat(String paramString, float paramFloat) {
/* 133 */     this.map.put(paramString, new NBTTagFloat(paramFloat));
/*     */   }
/*     */   
/*     */   public void setDouble(String paramString, double paramDouble) {
/* 137 */     this.map.put(paramString, new NBTTagDouble(paramDouble));
/*     */   }
/*     */   
/*     */   public void setString(String paramString1, String paramString2) {
/* 141 */     this.map.put(paramString1, new NBTTagString(paramString2));
/*     */   }
/*     */   
/*     */   public void setByteArray(String paramString, byte[] paramArrayOfbyte) {
/* 145 */     this.map.put(paramString, new NBTTagByteArray(paramArrayOfbyte));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntArray(String paramString, int[] paramArrayOfint) {
/* 153 */     this.map.put(paramString, new NBTTagIntArray(paramArrayOfint));
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
/*     */   public void setBoolean(String paramString, boolean paramBoolean) {
/* 169 */     setByte(paramString, paramBoolean ? 1 : 0);
/*     */   }
/*     */   
/*     */   public NBTBase get(String paramString) {
/* 173 */     return this.map.get(paramString);
/*     */   }
/*     */   
/*     */   public byte d(String paramString) {
/* 177 */     NBTBase nBTBase = this.map.get(paramString);
/* 178 */     if (nBTBase == null) {
/* 179 */       return 0;
/*     */     }
/* 181 */     return nBTBase.getTypeId();
/*     */   }
/*     */   
/*     */   public boolean hasKey(String paramString) {
/* 185 */     return this.map.containsKey(paramString);
/*     */   }
/*     */   
/*     */   public boolean hasKeyOfType(String paramString, int paramInt) {
/* 189 */     byte b = d(paramString);
/* 190 */     if (b == paramInt) {
/* 191 */       return true;
/*     */     }
/* 193 */     if (paramInt == 99) {
/* 194 */       return (b == 1 || b == 2 || b == 3 || b == 4 || b == 5 || b == 6);
/*     */     }
/*     */     
/* 197 */     return false;
/*     */   }
/*     */   
/*     */   public byte getByte(String paramString) {
/*     */     try {
/* 202 */       if (hasKeyOfType(paramString, 99)) {
/* 203 */         return ((NBTNumber)this.map.get(paramString)).g();
/*     */       }
/* 205 */     } catch (ClassCastException classCastException) {}
/*     */     
/* 207 */     return 0;
/*     */   }
/*     */   
/*     */   public short getShort(String paramString) {
/*     */     try {
/* 212 */       if (hasKeyOfType(paramString, 99)) {
/* 213 */         return ((NBTNumber)this.map.get(paramString)).f();
/*     */       }
/* 215 */     } catch (ClassCastException classCastException) {}
/*     */     
/* 217 */     return 0;
/*     */   }
/*     */   
/*     */   public int getInt(String paramString) {
/*     */     try {
/* 222 */       if (hasKeyOfType(paramString, 99)) {
/* 223 */         return ((NBTNumber)this.map.get(paramString)).e();
/*     */       }
/* 225 */     } catch (ClassCastException classCastException) {}
/*     */     
/* 227 */     return 0;
/*     */   }
/*     */   
/*     */   public long getLong(String paramString) {
/*     */     try {
/* 232 */       if (hasKeyOfType(paramString, 99)) {
/* 233 */         return ((NBTNumber)this.map.get(paramString)).d();
/*     */       }
/* 235 */     } catch (ClassCastException classCastException) {}
/*     */     
/* 237 */     return 0L;
/*     */   }
/*     */   
/*     */   public float getFloat(String paramString) {
/*     */     try {
/* 242 */       if (hasKeyOfType(paramString, 99)) {
/* 243 */         return ((NBTNumber)this.map.get(paramString)).i();
/*     */       }
/* 245 */     } catch (ClassCastException classCastException) {}
/*     */     
/* 247 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public double getDouble(String paramString) {
/*     */     try {
/* 252 */       if (hasKeyOfType(paramString, 99)) {
/* 253 */         return ((NBTNumber)this.map.get(paramString)).asDouble();
/*     */       }
/* 255 */     } catch (ClassCastException classCastException) {}
/*     */     
/* 257 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public String getString(String paramString) {
/*     */     try {
/* 262 */       if (hasKeyOfType(paramString, 8)) {
/* 263 */         return ((NBTBase)this.map.get(paramString)).c_();
/*     */       }
/* 265 */     } catch (ClassCastException classCastException) {}
/*     */     
/* 267 */     return "";
/*     */   }
/*     */   
/*     */   public byte[] getByteArray(String paramString) {
/*     */     try {
/* 272 */       if (hasKeyOfType(paramString, 7)) {
/* 273 */         return ((NBTTagByteArray)this.map.get(paramString)).c();
/*     */       }
/* 275 */     } catch (ClassCastException classCastException) {
/* 276 */       throw new ReportedException(a(paramString, 7, classCastException));
/*     */     } 
/* 278 */     return new byte[0];
/*     */   }
/*     */   
/*     */   public int[] getIntArray(String paramString) {
/*     */     try {
/* 283 */       if (hasKeyOfType(paramString, 11)) {
/* 284 */         return ((NBTTagIntArray)this.map.get(paramString)).d();
/*     */       }
/* 286 */     } catch (ClassCastException classCastException) {
/* 287 */       throw new ReportedException(a(paramString, 11, classCastException));
/*     */     } 
/* 289 */     return new int[0];
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
/*     */   public NBTTagCompound getCompound(String paramString) {
/*     */     try {
/* 305 */       if (hasKeyOfType(paramString, 10)) {
/* 306 */         return (NBTTagCompound)this.map.get(paramString);
/*     */       }
/* 308 */     } catch (ClassCastException classCastException) {
/* 309 */       throw new ReportedException(a(paramString, 10, classCastException));
/*     */     } 
/* 311 */     return new NBTTagCompound();
/*     */   }
/*     */   
/*     */   public NBTTagList getList(String paramString, int paramInt) {
/*     */     try {
/* 316 */       if (d(paramString) == 9) {
/* 317 */         NBTTagList nBTTagList = (NBTTagList)this.map.get(paramString);
/* 318 */         if (nBTTagList.isEmpty() || nBTTagList.g() == paramInt) {
/* 319 */           return nBTTagList;
/*     */         }
/* 321 */         return new NBTTagList();
/*     */       } 
/* 323 */     } catch (ClassCastException classCastException) {
/* 324 */       throw new ReportedException(a(paramString, 9, classCastException));
/*     */     } 
/* 326 */     return new NBTTagList();
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String paramString) {
/* 330 */     return (getByte(paramString) != 0);
/*     */   }
/*     */   
/*     */   public void remove(String paramString) {
/* 334 */     this.map.remove(paramString);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     ArrayList<Comparable> arrayList;
/* 354 */     StringBuilder stringBuilder = new StringBuilder("{");
/*     */     
/* 356 */     Set<String> set = this.map.keySet();
/* 357 */     if (b.isDebugEnabled()) {
/* 358 */       ArrayList<Comparable> arrayList1 = Lists.newArrayList(this.map.keySet());
/* 359 */       Collections.sort(arrayList1);
/* 360 */       arrayList = arrayList1;
/*     */     } 
/* 362 */     for (String str : arrayList) {
/* 363 */       if (stringBuilder.length() != 1) {
/* 364 */         stringBuilder.append(',');
/*     */       }
/* 366 */       stringBuilder.append(s(str)).append(':').append(this.map.get(str));
/*     */     } 
/*     */     
/* 369 */     return stringBuilder.append('}').toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 374 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   private CrashReport a(String paramString, int paramInt, ClassCastException paramClassCastException) {
/* 378 */     CrashReport crashReport = CrashReport.a(paramClassCastException, "Reading NBT data");
/* 379 */     CrashReportSystemDetails crashReportSystemDetails = crashReport.a("Corrupt NBT tag", 1);
/*     */     
/* 381 */     crashReportSystemDetails.a("Tag type found", new CrashReportCallable<String>(this, paramString)
/*     */         {
/*     */           public String a() throws Exception {
/* 384 */             return NBTBase.a[((NBTBase)NBTTagCompound.b(this.b).get(this.a)).getTypeId()];
/*     */           }
/*     */         });
/* 387 */     crashReportSystemDetails.a("Tag type expected", new CrashReportCallable<String>(this, paramInt)
/*     */         {
/*     */           public String a() throws Exception {
/* 390 */             return NBTBase.a[this.a];
/*     */           }
/*     */         });
/* 393 */     crashReportSystemDetails.a("Tag name", paramString);
/*     */     
/* 395 */     return crashReport;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound g() {
/* 400 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 401 */     for (String str : this.map.keySet()) {
/* 402 */       nBTTagCompound.set(str, ((NBTBase)this.map.get(str)).clone());
/*     */     }
/* 404 */     return nBTTagCompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 409 */     return (super.equals(paramObject) && Objects.equals(this.map.entrySet(), ((NBTTagCompound)paramObject).map.entrySet()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 414 */     return super.hashCode() ^ this.map.hashCode();
/*     */   }
/*     */   
/*     */   private static void a(String paramString, NBTBase paramNBTBase, DataOutput paramDataOutput) throws IOException {
/* 418 */     paramDataOutput.writeByte(paramNBTBase.getTypeId());
/* 419 */     if (paramNBTBase.getTypeId() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 423 */     paramDataOutput.writeUTF(paramString);
/*     */     
/* 425 */     paramNBTBase.write(paramDataOutput);
/*     */   }
/*     */   
/*     */   private static byte a(DataInput paramDataInput, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 429 */     return paramDataInput.readByte();
/*     */   }
/*     */   
/*     */   private static String b(DataInput paramDataInput, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 433 */     return paramDataInput.readUTF();
/*     */   }
/*     */   
/*     */   static NBTBase a(byte paramByte, String paramString, DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 437 */     NBTBase nBTBase = NBTBase.createTag(paramByte);
/*     */     
/*     */     try {
/* 440 */       nBTBase.load(paramDataInput, paramInt, paramNBTReadLimiter);
/* 441 */     } catch (IOException iOException) {
/* 442 */       CrashReport crashReport = CrashReport.a(iOException, "Loading NBT data");
/* 443 */       CrashReportSystemDetails crashReportSystemDetails = crashReport.a("NBT Tag");
/* 444 */       crashReportSystemDetails.a("Tag name", paramString);
/* 445 */       crashReportSystemDetails.a("Tag type", Byte.valueOf(paramByte));
/* 446 */       throw new ReportedException(crashReport);
/*     */     } 
/*     */     
/* 449 */     return nBTBase;
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
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 466 */     for (String str : paramNBTTagCompound.map.keySet()) {
/* 467 */       NBTBase nBTBase = paramNBTTagCompound.map.get(str);
/*     */ 
/*     */       
/* 470 */       if (nBTBase.getTypeId() == 10) {
/* 471 */         if (hasKeyOfType(str, 10)) {
/* 472 */           NBTTagCompound nBTTagCompound = getCompound(str);
/* 473 */           nBTTagCompound.a((NBTTagCompound)nBTBase); continue;
/*     */         } 
/* 475 */         set(str, nBTBase.clone());
/*     */         continue;
/*     */       } 
/* 478 */       set(str, nBTBase.clone());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static String s(String paramString) {
/* 484 */     if (c.matcher(paramString).matches()) {
/* 485 */       return paramString;
/*     */     }
/*     */     
/* 488 */     return NBTTagString.a(paramString);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagCompound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */