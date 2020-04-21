/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class MethodProfiler
/*     */ {
/*  15 */   public static final boolean ENABLED = Boolean.getBoolean("enableDebugMethodProfiler");
/*  16 */   private static final Logger b = LogManager.getLogger();
/*  17 */   private final List<String> c = Lists.newArrayList();
/*  18 */   private final List<Long> d = Lists.newArrayList();
/*     */   public boolean a;
/*  20 */   private String e = "";
/*  21 */   private final Map<String, Long> f = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {
/*  26 */     if (!ENABLED)
/*  27 */       return;  this.f.clear();
/*  28 */     this.e = "";
/*  29 */     this.c.clear();
/*     */   }
/*     */   
/*     */   public void a(String s) {
/*  33 */     if (!ENABLED)
/*  34 */       return;  if (this.a) {
/*  35 */       if (!this.e.isEmpty()) {
/*  36 */         this.e = String.valueOf(this.e) + ".";
/*     */       }
/*     */       
/*  39 */       this.e = String.valueOf(this.e) + s;
/*  40 */       this.c.add(this.e);
/*  41 */       this.d.add(Long.valueOf(System.nanoTime()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void b() {
/*  46 */     if (!ENABLED)
/*  47 */       return;  if (this.a) {
/*  48 */       long i = System.nanoTime();
/*  49 */       long j = ((Long)this.d.remove(this.d.size() - 1)).longValue();
/*     */       
/*  51 */       this.c.remove(this.c.size() - 1);
/*  52 */       long k = i - j;
/*     */       
/*  54 */       if (this.f.containsKey(this.e)) {
/*  55 */         this.f.put(this.e, Long.valueOf(((Long)this.f.get(this.e)).longValue() + k));
/*     */       } else {
/*  57 */         this.f.put(this.e, Long.valueOf(k));
/*     */       } 
/*     */       
/*  60 */       if (k > 100000000L) {
/*  61 */         b.warn("Something's taking too long! '{}' took aprox {} ms", this.e, Double.valueOf(k / 1000000.0D));
/*     */       }
/*     */       
/*  64 */       this.e = this.c.isEmpty() ? "" : this.c.get(this.c.size() - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<ProfilerInfo> b(String s) {
/*  69 */     if (!ENABLED || !this.a) {
/*  70 */       return Collections.emptyList();
/*     */     }
/*  72 */     long i = this.f.containsKey("root") ? ((Long)this.f.get("root")).longValue() : 0L;
/*  73 */     long j = this.f.containsKey(s) ? ((Long)this.f.get(s)).longValue() : -1L;
/*  74 */     ArrayList<ProfilerInfo> arraylist = Lists.newArrayList();
/*     */     
/*  76 */     if (!s.isEmpty()) {
/*  77 */       s = String.valueOf(s) + ".";
/*     */     }
/*     */     
/*  80 */     long k = 0L;
/*  81 */     Iterator<String> iterator = this.f.keySet().iterator();
/*     */     
/*  83 */     while (iterator.hasNext()) {
/*  84 */       String s1 = iterator.next();
/*     */       
/*  86 */       if (s1.length() > s.length() && s1.startsWith(s) && s1.indexOf(".", s.length() + 1) < 0) {
/*  87 */         k += ((Long)this.f.get(s1)).longValue();
/*     */       }
/*     */     } 
/*     */     
/*  91 */     float f = (float)k;
/*     */     
/*  93 */     if (k < j) {
/*  94 */       k = j;
/*     */     }
/*     */     
/*  97 */     if (i < k) {
/*  98 */       i = k;
/*     */     }
/*     */     
/* 101 */     Iterator<String> iterator1 = this.f.keySet().iterator();
/*     */ 
/*     */ 
/*     */     
/* 105 */     while (iterator1.hasNext()) {
/* 106 */       String s2 = iterator1.next();
/* 107 */       if (s2.length() > s.length() && s2.startsWith(s) && s2.indexOf(".", s.length() + 1) < 0) {
/* 108 */         long l = ((Long)this.f.get(s2)).longValue();
/* 109 */         double d0 = l * 100.0D / k;
/* 110 */         double d1 = l * 100.0D / i;
/* 111 */         String s3 = s2.substring(s.length());
/*     */         
/* 113 */         arraylist.add(new ProfilerInfo(s3, d0, d1));
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     iterator1 = this.f.keySet().iterator();
/*     */     
/* 119 */     while (iterator1.hasNext()) {
/* 120 */       String s2 = iterator1.next();
/* 121 */       this.f.put(s2, Long.valueOf(((Long)this.f.get(s2)).longValue() * 999L / 1000L));
/*     */     } 
/*     */     
/* 124 */     if ((float)k > f) {
/* 125 */       arraylist.add(new ProfilerInfo("unspecified", ((float)k - f) * 100.0D / k, ((float)k - f) * 100.0D / i));
/*     */     }
/*     */     
/* 128 */     Collections.sort(arraylist);
/* 129 */     arraylist.add(0, new ProfilerInfo(s, 100.0D, k * 100.0D / i));
/* 130 */     return arraylist;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(String s) {
/* 135 */     if (!ENABLED)
/* 136 */       return;  b();
/* 137 */     a(s);
/*     */   }
/*     */   
/*     */   public String c() {
/* 141 */     if (!ENABLED) return "[DISABLED]"; 
/* 142 */     return this.c.isEmpty() ? "[UNKNOWN]" : this.c.get(this.c.size() - 1);
/*     */   }
/*     */   
/*     */   public static final class ProfilerInfo
/*     */     implements Comparable<ProfilerInfo> {
/*     */     public double a;
/*     */     public double b;
/*     */     public String c;
/*     */     
/*     */     public ProfilerInfo(String s, double d0, double d1) {
/* 152 */       this.c = s;
/* 153 */       this.a = d0;
/* 154 */       this.b = d1;
/*     */     }
/*     */     
/*     */     public int a(ProfilerInfo methodprofiler_profilerinfo) {
/* 158 */       return (methodprofiler_profilerinfo.a < this.a) ? -1 : ((methodprofiler_profilerinfo.a > this.a) ? 1 : methodprofiler_profilerinfo.c.compareTo(this.c));
/*     */     }
/*     */     
/*     */     public int compareTo(ProfilerInfo object) {
/* 162 */       return a(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MethodProfiler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */