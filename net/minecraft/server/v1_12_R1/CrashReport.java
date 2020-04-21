/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftCrashReport;
/*     */ 
/*     */ public class CrashReport {
/*  23 */   private static final Logger a = LogManager.getLogger();
/*     */   private final String b;
/*     */   private final Throwable c;
/*  26 */   private final CrashReportSystemDetails d = new CrashReportSystemDetails(this, "System Details");
/*  27 */   private final List<CrashReportSystemDetails> e = Lists.newArrayList();
/*     */   private File f;
/*     */   private boolean g = true;
/*  30 */   private StackTraceElement[] h = new StackTraceElement[0];
/*     */   
/*     */   public CrashReport(String s, Throwable throwable) {
/*  33 */     this.b = s;
/*  34 */     this.c = throwable;
/*  35 */     h();
/*     */   }
/*     */   
/*     */   private void h() {
/*  39 */     this.d.a("Minecraft Version", new CrashReportCallable<String>() {
/*     */           public String a() {
/*  41 */             return "1.12";
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/*  45 */             return a();
/*     */           }
/*     */         });
/*  48 */     this.d.a("Operating System", new CrashReportCallable<String>() {
/*     */           public String a() {
/*  50 */             return String.valueOf(System.getProperty("os.name")) + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/*  54 */             return a();
/*     */           }
/*     */         });
/*  57 */     this.d.a("Java Version", new CrashReportCallable<String>() {
/*     */           public String a() {
/*  59 */             return String.valueOf(System.getProperty("java.version")) + ", " + System.getProperty("java.vendor");
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/*  63 */             return a();
/*     */           }
/*     */         });
/*  66 */     this.d.a("Java VM Version", new CrashReportCallable<String>() {
/*     */           public String a() {
/*  68 */             return String.valueOf(System.getProperty("java.vm.name")) + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/*  72 */             return a();
/*     */           }
/*     */         });
/*  75 */     this.d.a("Memory", new CrashReportCallable<String>() {
/*     */           public String a() {
/*  77 */             Runtime runtime = Runtime.getRuntime();
/*  78 */             long i = runtime.maxMemory();
/*  79 */             long j = runtime.totalMemory();
/*  80 */             long k = runtime.freeMemory();
/*  81 */             long l = i / 1024L / 1024L;
/*  82 */             long i1 = j / 1024L / 1024L;
/*  83 */             long j1 = k / 1024L / 1024L;
/*     */             
/*  85 */             return String.valueOf(k) + " bytes (" + j1 + " MB) / " + j + " bytes (" + i1 + " MB) up to " + i + " bytes (" + l + " MB)";
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/*  89 */             return a();
/*     */           }
/*     */         });
/*  92 */     this.d.a("JVM Flags", new CrashReportCallable<String>() {
/*     */           public String a() {
/*  94 */             RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
/*  95 */             List<String> list = runtimemxbean.getInputArguments();
/*  96 */             int i = 0;
/*  97 */             StringBuilder stringbuilder = new StringBuilder();
/*  98 */             Iterator<String> iterator = list.iterator();
/*     */             
/* 100 */             while (iterator.hasNext()) {
/* 101 */               String s = iterator.next();
/*     */               
/* 103 */               if (s.startsWith("-X")) {
/* 104 */                 if (i++ > 0) {
/* 105 */                   stringbuilder.append(" ");
/*     */                 }
/*     */                 
/* 108 */                 stringbuilder.append(s);
/*     */               } 
/*     */             } 
/*     */             
/* 112 */             return String.format("%d total; %s", new Object[] { Integer.valueOf(i), stringbuilder.toString() });
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/* 116 */             return a();
/*     */           }
/*     */         });
/* 119 */     this.d.a("IntCache", new CrashReportCallable<String>() {
/*     */           public String a() throws Exception {
/* 121 */             return IntCache.b();
/*     */           }
/*     */           
/*     */           public Object call() throws Exception {
/* 125 */             return a();
/*     */           }
/*     */         });
/* 128 */     this.d.a("CraftBukkit Information", (CrashReportCallable<String>)new CraftCrashReport());
/*     */   }
/*     */   
/*     */   public String a() {
/* 132 */     return this.b;
/*     */   }
/*     */   
/*     */   public Throwable b() {
/* 136 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(StringBuilder stringbuilder) {
/* 140 */     if ((this.h == null || this.h.length <= 0) && !this.e.isEmpty()) {
/* 141 */       this.h = (StackTraceElement[])ArrayUtils.subarray((Object[])((CrashReportSystemDetails)this.e.get(0)).a(), 0, 1);
/*     */     }
/*     */     
/* 144 */     if (this.h != null && this.h.length > 0) {
/* 145 */       stringbuilder.append("-- Head --\n");
/* 146 */       stringbuilder.append("Thread: ").append(Thread.currentThread().getName()).append("\n");
/* 147 */       stringbuilder.append("Stacktrace:\n");
/* 148 */       StackTraceElement[] astacktraceelement = this.h;
/* 149 */       int i = astacktraceelement.length;
/*     */       
/* 151 */       for (int j = 0; j < i; j++) {
/* 152 */         StackTraceElement stacktraceelement = astacktraceelement[j];
/*     */         
/* 154 */         stringbuilder.append("\t").append("at ").append(stacktraceelement);
/* 155 */         stringbuilder.append("\n");
/*     */       } 
/*     */       
/* 158 */       stringbuilder.append("\n");
/*     */     } 
/*     */     
/* 161 */     Iterator<CrashReportSystemDetails> iterator = this.e.iterator();
/*     */     
/* 163 */     while (iterator.hasNext()) {
/* 164 */       CrashReportSystemDetails crashreportsystemdetails = iterator.next();
/*     */       
/* 166 */       crashreportsystemdetails.a(stringbuilder);
/* 167 */       stringbuilder.append("\n\n");
/*     */     } 
/*     */     
/* 170 */     this.d.a(stringbuilder);
/*     */   }
/*     */   
/*     */   public String d() {
/* 174 */     StringWriter stringwriter = null;
/* 175 */     PrintWriter printwriter = null;
/* 176 */     Object object = this.c;
/*     */     
/* 178 */     if (((Throwable)object).getMessage() == null) {
/* 179 */       if (object instanceof NullPointerException) {
/* 180 */         object = new NullPointerException(this.b);
/* 181 */       } else if (object instanceof StackOverflowError) {
/* 182 */         object = new StackOverflowError(this.b);
/* 183 */       } else if (object instanceof OutOfMemoryError) {
/* 184 */         object = new OutOfMemoryError(this.b);
/*     */       } 
/*     */       
/* 187 */       ((Throwable)object).setStackTrace(this.c.getStackTrace());
/*     */     } 
/*     */     
/* 190 */     String s = ((Throwable)object).toString();
/*     */     
/*     */     try {
/* 193 */       stringwriter = new StringWriter();
/* 194 */       printwriter = new PrintWriter(stringwriter);
/* 195 */       ((Throwable)object).printStackTrace(printwriter);
/* 196 */       s = stringwriter.toString();
/*     */     } finally {
/* 198 */       IOUtils.closeQuietly(stringwriter);
/* 199 */       IOUtils.closeQuietly(printwriter);
/*     */     } 
/*     */     
/* 202 */     return s;
/*     */   }
/*     */   
/*     */   public String e() {
/* 206 */     StringBuilder stringbuilder = new StringBuilder();
/*     */     
/* 208 */     stringbuilder.append("---- Minecraft Crash Report ----\n");
/* 209 */     stringbuilder.append("// ");
/* 210 */     stringbuilder.append(i());
/* 211 */     stringbuilder.append("\n\n");
/* 212 */     stringbuilder.append("Time: ");
/* 213 */     stringbuilder.append((new SimpleDateFormat()).format(new Date()));
/* 214 */     stringbuilder.append("\n");
/* 215 */     stringbuilder.append("Description: ");
/* 216 */     stringbuilder.append(this.b);
/* 217 */     stringbuilder.append("\n\n");
/* 218 */     stringbuilder.append(d());
/* 219 */     stringbuilder.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");
/*     */     
/* 221 */     for (int i = 0; i < 87; i++) {
/* 222 */       stringbuilder.append("-");
/*     */     }
/*     */     
/* 225 */     stringbuilder.append("\n\n");
/* 226 */     a(stringbuilder);
/* 227 */     return stringbuilder.toString();
/*     */   }
/*     */   public boolean a(File file) {
/*     */     boolean flag;
/* 231 */     if (this.f != null) {
/* 232 */       return false;
/*     */     }
/* 234 */     if (file.getParentFile() != null) {
/* 235 */       file.getParentFile().mkdirs();
/*     */     }
/*     */     
/* 238 */     OutputStreamWriter outputstreamwriter = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 243 */       outputstreamwriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
/* 244 */       outputstreamwriter.write(e());
/* 245 */       this.f = file;
/* 246 */       boolean flag1 = true;
/*     */       
/* 248 */       return flag1;
/* 249 */     } catch (Throwable throwable) {
/* 250 */       a.error("Could not save crash report to {}", file, throwable);
/* 251 */       flag = false;
/*     */     } finally {
/* 253 */       IOUtils.closeQuietly(outputstreamwriter);
/*     */     } 
/*     */     
/* 256 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public CrashReportSystemDetails g() {
/* 261 */     return this.d;
/*     */   }
/*     */   
/*     */   public CrashReportSystemDetails a(String s) {
/* 265 */     return a(s, 1);
/*     */   }
/*     */   
/*     */   public CrashReportSystemDetails a(String s, int i) {
/* 269 */     CrashReportSystemDetails crashreportsystemdetails = new CrashReportSystemDetails(this, s);
/*     */     
/* 271 */     if (this.g) {
/* 272 */       int j = crashreportsystemdetails.a(i);
/* 273 */       StackTraceElement[] astacktraceelement = this.c.getStackTrace();
/* 274 */       StackTraceElement stacktraceelement = null;
/* 275 */       StackTraceElement stacktraceelement1 = null;
/* 276 */       int k = astacktraceelement.length - j;
/*     */       
/* 278 */       if (k < 0) {
/* 279 */         System.out.println("Negative index in crash report handler (" + astacktraceelement.length + "/" + j + ")");
/*     */       }
/*     */       
/* 282 */       if (astacktraceelement != null && k >= 0 && k < astacktraceelement.length) {
/* 283 */         stacktraceelement = astacktraceelement[k];
/* 284 */         if (astacktraceelement.length + 1 - j < astacktraceelement.length) {
/* 285 */           stacktraceelement1 = astacktraceelement[astacktraceelement.length + 1 - j];
/*     */         }
/*     */       } 
/*     */       
/* 289 */       this.g = crashreportsystemdetails.a(stacktraceelement, stacktraceelement1);
/* 290 */       if (j > 0 && !this.e.isEmpty()) {
/* 291 */         CrashReportSystemDetails crashreportsystemdetails1 = this.e.get(this.e.size() - 1);
/*     */         
/* 293 */         crashreportsystemdetails1.b(j);
/* 294 */       } else if (astacktraceelement != null && astacktraceelement.length >= j && k >= 0 && k < astacktraceelement.length) {
/* 295 */         this.h = new StackTraceElement[k];
/* 296 */         System.arraycopy(astacktraceelement, 0, this.h, 0, this.h.length);
/*     */       } else {
/* 298 */         this.g = false;
/*     */       } 
/*     */     } 
/*     */     
/* 302 */     this.e.add(crashreportsystemdetails);
/* 303 */     return crashreportsystemdetails;
/*     */   }
/*     */   
/*     */   private static String i() {
/* 307 */     String[] astring = { "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!", "But it works on my machine." };
/*     */     
/*     */     try {
/* 310 */       return astring[(int)(System.nanoTime() % astring.length)];
/* 311 */     } catch (Throwable throwable) {
/* 312 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static CrashReport a(Throwable throwable, String s) {
/*     */     CrashReport crashreport;
/* 319 */     if (throwable instanceof ReportedException) {
/* 320 */       crashreport = ((ReportedException)throwable).a();
/*     */     } else {
/* 322 */       crashreport = new CrashReport(s, throwable);
/*     */     } 
/*     */     
/* 325 */     return crashreport;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CrashReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */