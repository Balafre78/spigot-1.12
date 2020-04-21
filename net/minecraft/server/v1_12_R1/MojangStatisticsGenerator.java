/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MojangStatisticsGenerator
/*     */ {
/*  23 */   private final Map<String, Object> a = Maps.newHashMap();
/*  24 */   private final Map<String, Object> b = Maps.newHashMap();
/*     */   
/*  26 */   private final String c = UUID.randomUUID().toString();
/*     */   private final URL d;
/*     */   private final IMojangStatistics e;
/*  29 */   private final Timer f = new Timer("Snooper Timer", true);
/*  30 */   private final Object g = new Object();
/*     */   private final long h;
/*     */   private boolean i;
/*     */   private int j;
/*     */   
/*     */   public MojangStatisticsGenerator(String paramString, IMojangStatistics paramIMojangStatistics, long paramLong) {
/*     */     try {
/*  37 */       this.d = new URL("http://snoop.minecraft.net/" + paramString + "?version=" + '\002');
/*  38 */     } catch (MalformedURLException malformedURLException) {
/*  39 */       throw new IllegalArgumentException();
/*     */     } 
/*     */     
/*  42 */     this.e = paramIMojangStatistics;
/*  43 */     this.h = paramLong;
/*     */   }
/*     */   
/*     */   public void a() {
/*  47 */     if (this.i) {
/*     */       return;
/*     */     }
/*  50 */     this.i = true;
/*     */     
/*  52 */     h();
/*     */     
/*  54 */     this.f.schedule(new TimerTask(this) {
/*     */           public void run() {
/*     */             HashMap<String, Integer> hashMap;
/*  57 */             if (!MojangStatisticsGenerator.a(this.a).getSnooperEnabled()) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  62 */             synchronized (MojangStatisticsGenerator.b(this.a)) {
/*  63 */               hashMap = Maps.newHashMap(MojangStatisticsGenerator.c(this.a));
/*  64 */               if (MojangStatisticsGenerator.d(this.a) == 0) {
/*  65 */                 hashMap.putAll(MojangStatisticsGenerator.e(this.a));
/*     */               }
/*  67 */               hashMap.put("snooper_count", Integer.valueOf(MojangStatisticsGenerator.f(this.a)));
/*  68 */               hashMap.put("snooper_token", MojangStatisticsGenerator.g(this.a));
/*     */             } 
/*     */             
/*  71 */             MinecraftServer minecraftServer = (MojangStatisticsGenerator.a(this.a) instanceof MinecraftServer) ? (MinecraftServer)MojangStatisticsGenerator.a(this.a) : null;
/*  72 */             HttpUtilities.a(MojangStatisticsGenerator.h(this.a), (Map)hashMap, true, (minecraftServer == null) ? null : minecraftServer.av());
/*     */           }
/*     */         }0L, 900000L);
/*     */   }
/*     */   
/*     */   private void h() {
/*  78 */     i();
/*     */     
/*  80 */     a("snooper_token", this.c);
/*  81 */     b("snooper_token", this.c);
/*  82 */     b("os_name", System.getProperty("os.name"));
/*  83 */     b("os_version", System.getProperty("os.version"));
/*  84 */     b("os_architecture", System.getProperty("os.arch"));
/*  85 */     b("java_version", System.getProperty("java.version"));
/*  86 */     a("version", "1.12");
/*     */     
/*  88 */     this.e.b(this);
/*     */   }
/*     */   
/*     */   private void i() {
/*  92 */     RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
/*  93 */     List<String> list = runtimeMXBean.getInputArguments();
/*  94 */     byte b = 0;
/*     */     
/*  96 */     for (String str : list) {
/*  97 */       if (str.startsWith("-X")) {
/*  98 */         a("jvm_arg[" + b++ + "]", str);
/*     */       }
/*     */     } 
/*     */     
/* 102 */     a("jvm_args", Integer.valueOf(b));
/*     */   }
/*     */   
/*     */   public void b() {
/* 106 */     b("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
/* 107 */     b("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
/* 108 */     b("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
/* 109 */     b("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
/*     */     
/* 111 */     this.e.a(this);
/*     */   }
/*     */   
/*     */   public void a(String paramString, Object paramObject) {
/* 115 */     synchronized (this.g) {
/* 116 */       this.b.put(paramString, paramObject);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void b(String paramString, Object paramObject) {
/* 121 */     synchronized (this.g) {
/* 122 */       this.a.put(paramString, paramObject);
/*     */     } 
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
/*     */   public boolean d() {
/* 145 */     return this.i;
/*     */   }
/*     */   
/*     */   public void e() {
/* 149 */     this.f.cancel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long g() {
/* 157 */     return this.h;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MojangStatisticsGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */