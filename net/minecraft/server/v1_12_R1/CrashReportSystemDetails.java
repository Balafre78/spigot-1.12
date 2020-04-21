/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrashReportSystemDetails
/*     */ {
/*     */   private final CrashReport a;
/*     */   private final String b;
/*  13 */   private final List<CrashReportDetail> c = Lists.newArrayList();
/*  14 */   private StackTraceElement[] d = new StackTraceElement[0];
/*     */   
/*     */   public CrashReportSystemDetails(CrashReport paramCrashReport, String paramString) {
/*  17 */     this.a = paramCrashReport;
/*  18 */     this.b = paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(BlockPosition paramBlockPosition) {
/*  26 */     return a(paramBlockPosition.getX(), paramBlockPosition.getY(), paramBlockPosition.getZ());
/*     */   }
/*     */   
/*     */   public static String a(int paramInt1, int paramInt2, int paramInt3) {
/*  30 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/*     */     try {
/*  33 */       stringBuilder.append(String.format("World: (%d,%d,%d)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) }));
/*  34 */     } catch (Throwable throwable) {
/*  35 */       stringBuilder.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  38 */     stringBuilder.append(", ");
/*     */     
/*     */     try {
/*  41 */       int i = paramInt1 >> 4;
/*  42 */       int j = paramInt3 >> 4;
/*  43 */       int k = paramInt1 & 0xF;
/*  44 */       int m = paramInt2 >> 4;
/*  45 */       int n = paramInt3 & 0xF;
/*  46 */       int i1 = i << 4;
/*  47 */       int i2 = j << 4;
/*  48 */       int i3 = (i + 1 << 4) - 1;
/*  49 */       int i4 = (j + 1 << 4) - 1;
/*  50 */       stringBuilder.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(i1), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4) }));
/*  51 */     } catch (Throwable throwable) {
/*  52 */       stringBuilder.append("(Error finding chunk loc)");
/*     */     } 
/*     */     
/*  55 */     stringBuilder.append(", ");
/*     */     
/*     */     try {
/*  58 */       int i = paramInt1 >> 9;
/*  59 */       int j = paramInt3 >> 9;
/*  60 */       int k = i << 5;
/*  61 */       int m = j << 5;
/*  62 */       int n = (i + 1 << 5) - 1;
/*  63 */       int i1 = (j + 1 << 5) - 1;
/*  64 */       int i2 = i << 9;
/*  65 */       int i3 = j << 9;
/*  66 */       int i4 = (i + 1 << 9) - 1;
/*  67 */       int i5 = (j + 1 << 9) - 1;
/*  68 */       stringBuilder.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(i1), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5) }));
/*  69 */     } catch (Throwable throwable) {
/*  70 */       stringBuilder.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  73 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public void a(String paramString, CrashReportCallable<String> paramCrashReportCallable) {
/*     */     try {
/*  78 */       a(paramString, paramCrashReportCallable.call());
/*  79 */     } catch (Throwable throwable) {
/*  80 */       a(paramString, throwable);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(String paramString, Object paramObject) {
/*  85 */     this.c.add(new CrashReportDetail(paramString, paramObject));
/*     */   }
/*     */   
/*     */   public void a(String paramString, Throwable paramThrowable) {
/*  89 */     a(paramString, paramThrowable);
/*     */   }
/*     */   
/*     */   public int a(int paramInt) {
/*  93 */     StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
/*     */ 
/*     */     
/*  96 */     if (arrayOfStackTraceElement.length <= 0) {
/*  97 */       return 0;
/*     */     }
/*     */     
/* 100 */     this.d = new StackTraceElement[arrayOfStackTraceElement.length - 3 - paramInt];
/* 101 */     System.arraycopy(arrayOfStackTraceElement, 3 + paramInt, this.d, 0, this.d.length);
/* 102 */     return this.d.length;
/*     */   }
/*     */   
/*     */   public boolean a(StackTraceElement paramStackTraceElement1, StackTraceElement paramStackTraceElement2) {
/* 106 */     if (this.d.length == 0 || paramStackTraceElement1 == null) {
/* 107 */       return false;
/*     */     }
/*     */     
/* 110 */     StackTraceElement stackTraceElement = this.d[0];
/*     */ 
/*     */     
/* 113 */     if (stackTraceElement.isNativeMethod() != paramStackTraceElement1.isNativeMethod() || 
/* 114 */       !stackTraceElement.getClassName().equals(paramStackTraceElement1.getClassName()) || 
/* 115 */       !stackTraceElement.getFileName().equals(paramStackTraceElement1.getFileName()) || 
/* 116 */       !stackTraceElement.getMethodName().equals(paramStackTraceElement1.getMethodName()))
/*     */     {
/* 118 */       return false;
/*     */     }
/*     */     
/* 121 */     if (((paramStackTraceElement2 != null) ? true : false) != ((this.d.length > 1) ? true : false)) {
/* 122 */       return false;
/*     */     }
/* 124 */     if (paramStackTraceElement2 != null && !this.d[1].equals(paramStackTraceElement2)) {
/* 125 */       return false;
/*     */     }
/*     */     
/* 128 */     this.d[0] = paramStackTraceElement1;
/*     */     
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   public void b(int paramInt) {
/* 134 */     StackTraceElement[] arrayOfStackTraceElement = new StackTraceElement[this.d.length - paramInt];
/* 135 */     System.arraycopy(this.d, 0, arrayOfStackTraceElement, 0, arrayOfStackTraceElement.length);
/* 136 */     this.d = arrayOfStackTraceElement;
/*     */   }
/*     */   
/*     */   public void a(StringBuilder paramStringBuilder) {
/* 140 */     paramStringBuilder.append("-- ").append(this.b).append(" --\n");
/* 141 */     paramStringBuilder.append("Details:");
/*     */     
/* 143 */     for (CrashReportDetail crashReportDetail : this.c) {
/* 144 */       paramStringBuilder.append("\n\t");
/* 145 */       paramStringBuilder.append(crashReportDetail.a());
/* 146 */       paramStringBuilder.append(": ");
/* 147 */       paramStringBuilder.append(crashReportDetail.b());
/*     */     } 
/*     */     
/* 150 */     if (this.d != null && this.d.length > 0) {
/* 151 */       paramStringBuilder.append("\nStacktrace:");
/*     */       
/* 153 */       for (StackTraceElement stackTraceElement : this.d) {
/* 154 */         paramStringBuilder.append("\n\tat ");
/* 155 */         paramStringBuilder.append(stackTraceElement);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public StackTraceElement[] a() {
/* 161 */     return this.d;
/*     */   }
/*     */   
/*     */   public static void a(CrashReportSystemDetails paramCrashReportSystemDetails, BlockPosition paramBlockPosition, Block paramBlock, int paramInt) {
/* 165 */     int i = Block.getId(paramBlock);
/* 166 */     paramCrashReportSystemDetails.a("Block type", new CrashReportCallable<String>(i, paramBlock)
/*     */         {
/*     */           public String a() throws Exception {
/*     */             try {
/* 170 */               return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(this.a), this.b.a(), this.b.getClass().getCanonicalName() });
/* 171 */             } catch (Throwable throwable) {
/* 172 */               return "ID #" + this.a;
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 177 */     paramCrashReportSystemDetails.a("Block data value", new CrashReportCallable<String>(paramInt)
/*     */         {
/*     */           public String a() throws Exception {
/* 180 */             if (this.a < 0) {
/* 181 */               return "Unknown? (Got " + this.a + ")";
/*     */             }
/* 183 */             String str = String.format("%4s", new Object[] { Integer.toBinaryString(this.a) }).replace(" ", "0");
/*     */ 
/*     */             
/* 186 */             return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(this.a), str });
/*     */           }
/*     */         });
/*     */     
/* 190 */     paramCrashReportSystemDetails.a("Block location", new CrashReportCallable<String>(paramBlockPosition)
/*     */         {
/*     */           public String a() throws Exception {
/* 193 */             return CrashReportSystemDetails.a(this.a);
/*     */           }
/*     */         });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(CrashReportSystemDetails paramCrashReportSystemDetails, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 222 */     paramCrashReportSystemDetails.a("Block", new CrashReportCallable<String>(paramIBlockData)
/*     */         {
/*     */           public String a() throws Exception {
/* 225 */             return this.a.toString();
/*     */           }
/*     */         });
/*     */     
/* 229 */     paramCrashReportSystemDetails.a("Block location", new CrashReportCallable<String>(paramBlockPosition)
/*     */         {
/*     */           public String a() throws Exception {
/* 232 */             return CrashReportSystemDetails.a(this.a);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   static class CrashReportDetail {
/*     */     private final String a;
/*     */     private final String b;
/*     */     
/*     */     public CrashReportDetail(String param1String, Object param1Object) {
/* 242 */       this.a = param1String;
/*     */       
/* 244 */       if (param1Object == null) {
/* 245 */         this.b = "~~NULL~~";
/* 246 */       } else if (param1Object instanceof Throwable) {
/* 247 */         Throwable throwable = (Throwable)param1Object;
/* 248 */         this.b = "~~ERROR~~ " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
/*     */       } else {
/* 250 */         this.b = param1Object.toString();
/*     */       } 
/*     */     }
/*     */     
/*     */     public String a() {
/* 255 */       return this.a;
/*     */     }
/*     */     
/*     */     public String b() {
/* 259 */       return this.b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CrashReportSystemDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */