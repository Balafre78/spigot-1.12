/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class CommandDebug
/*     */   extends CommandAbstract {
/*  18 */   private static final Logger a = LogManager.getLogger();
/*     */   
/*     */   private long b;
/*     */   
/*     */   private int c;
/*     */   
/*     */   public String getCommand() {
/*  25 */     return "debug";
/*     */   }
/*     */   
/*     */   public int a() {
/*  29 */     return 3;
/*     */   }
/*     */   
/*     */   public String getUsage(ICommandListener icommandlistener) {
/*  33 */     return "commands.debug.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring) throws CommandException {
/*  38 */     if (!MethodProfiler.ENABLED) {
/*  39 */       icommandlistener.sendMessage(new ChatComponentText("Vanilla debug profiling is disabled."));
/*  40 */       icommandlistener.sendMessage(new ChatComponentText("To enable, restart the server with `-DenableDebugMethodProfiler=true' before `-jar'."));
/*  41 */       icommandlistener.sendMessage(new ChatComponentText("Use `/timings' for plugin timings."));
/*     */       
/*     */       return;
/*     */     } 
/*  45 */     if (astring.length < 1) {
/*  46 */       throw new ExceptionUsage("commands.debug.usage", new Object[0]);
/*     */     }
/*  48 */     if ("start".equals(astring[0])) {
/*  49 */       if (astring.length != 1) {
/*  50 */         throw new ExceptionUsage("commands.debug.usage", new Object[0]);
/*     */       }
/*     */       
/*  53 */       a(icommandlistener, this, "commands.debug.start", new Object[0]);
/*  54 */       minecraftserver.ar();
/*  55 */       this.b = MinecraftServer.aw();
/*  56 */       this.c = minecraftserver.aq();
/*     */     } else {
/*  58 */       if (!"stop".equals(astring[0])) {
/*  59 */         throw new ExceptionUsage("commands.debug.usage", new Object[0]);
/*     */       }
/*     */       
/*  62 */       if (astring.length != 1) {
/*  63 */         throw new ExceptionUsage("commands.debug.usage", new Object[0]);
/*     */       }
/*     */       
/*  66 */       if (!minecraftserver.methodProfiler.a) {
/*  67 */         throw new CommandException("commands.debug.notStarted", new Object[0]);
/*     */       }
/*     */       
/*  70 */       long i = MinecraftServer.aw();
/*  71 */       int j = minecraftserver.aq();
/*  72 */       long k = i - this.b;
/*  73 */       int l = j - this.c;
/*     */       
/*  75 */       a(k, l, minecraftserver);
/*  76 */       minecraftserver.methodProfiler.a = false;
/*  77 */       a(icommandlistener, this, "commands.debug.stop", new Object[] { String.format("%.2f", new Object[] { Float.valueOf((float)k / 1000.0F) }), Integer.valueOf(l) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(long i, int j, MinecraftServer minecraftserver) {
/*  84 */     File file = new File(minecraftserver.d("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
/*     */     
/*  86 */     file.getParentFile().mkdirs();
/*  87 */     OutputStreamWriter outputstreamwriter = null;
/*     */     
/*     */     try {
/*  90 */       outputstreamwriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
/*  91 */       outputstreamwriter.write(b(i, j, minecraftserver));
/*  92 */     } catch (Throwable throwable) {
/*  93 */       a.error("Could not save profiler results to {}", file, throwable);
/*     */     } finally {
/*  95 */       IOUtils.closeQuietly(outputstreamwriter);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String b(long i, int j, MinecraftServer minecraftserver) {
/* 101 */     StringBuilder stringbuilder = new StringBuilder();
/*     */     
/* 103 */     stringbuilder.append("---- Minecraft Profiler Results ----\n");
/* 104 */     stringbuilder.append("// ");
/* 105 */     stringbuilder.append(d());
/* 106 */     stringbuilder.append("\n\n");
/* 107 */     stringbuilder.append("Time span: ").append(i).append(" ms\n");
/* 108 */     stringbuilder.append("Tick span: ").append(j).append(" ticks\n");
/* 109 */     stringbuilder.append("// This is approximately ").append(String.format("%.2f", new Object[] { Float.valueOf(j / (float)i / 1000.0F) })).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
/* 110 */     stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
/* 111 */     a(0, "root", stringbuilder, minecraftserver);
/* 112 */     stringbuilder.append("--- END PROFILE DUMP ---\n\n");
/* 113 */     return stringbuilder.toString();
/*     */   }
/*     */   
/*     */   private void a(int i, String s, StringBuilder stringbuilder, MinecraftServer minecraftserver) {
/* 117 */     List<MethodProfiler.ProfilerInfo> list = minecraftserver.methodProfiler.b(s);
/*     */     
/* 119 */     if (list != null && list.size() >= 3) {
/* 120 */       for (int j = 1; j < list.size(); j++) {
/* 121 */         MethodProfiler.ProfilerInfo methodprofiler_profilerinfo = list.get(j);
/*     */         
/* 123 */         stringbuilder.append(String.format("[%02d] ", new Object[] { Integer.valueOf(i) }));
/*     */         
/* 125 */         for (int k = 0; k < i; k++) {
/* 126 */           stringbuilder.append("|   ");
/*     */         }
/*     */         
/* 129 */         stringbuilder.append(methodprofiler_profilerinfo.c).append(" - ").append(String.format("%.2f", new Object[] { Double.valueOf(methodprofiler_profilerinfo.a) })).append("%/").append(String.format("%.2f", new Object[] { Double.valueOf(methodprofiler_profilerinfo.b) })).append("%\n");
/* 130 */         if (!"unspecified".equals(methodprofiler_profilerinfo.c)) {
/*     */           try {
/* 132 */             a(i + 1, String.valueOf(s) + "." + methodprofiler_profilerinfo.c, stringbuilder, minecraftserver);
/* 133 */           } catch (Exception exception) {
/* 134 */             stringbuilder.append("[[ EXCEPTION ").append(exception).append(" ]]");
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static String d() {
/* 143 */     String[] astring = { "Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server." };
/*     */     
/*     */     try {
/* 146 */       return astring[(int)(System.nanoTime() % astring.length)];
/* 147 */     } catch (Throwable throwable) {
/* 148 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring, @Nullable BlockPosition blockposition) {
/* 153 */     return (astring.length == 1) ? a(astring, new String[] { "start", "stop" }) : Collections.<String>emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand o) {
/* 159 */     return a(o);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */