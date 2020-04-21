/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import java.io.File;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.command.CraftFunctionCommandSender;
/*     */ 
/*     */ public class CustomFunctionData implements ITickable {
/*  18 */   private static final Logger a = LogManager.getLogger();
/*     */   private final File b;
/*     */   private final MinecraftServer c;
/*  21 */   private final Map<MinecraftKey, CustomFunction> d = Maps.newHashMap();
/*  22 */   private String e = "-";
/*     */   private CustomFunction f;
/*  24 */   private final ArrayDeque<a> g = new ArrayDeque<>();
/*     */   
/*     */   private boolean h = false;
/*  27 */   private final ICommandListener i = new CustomFunctionListener();
/*     */   
/*     */   public class CustomFunctionListener
/*     */     implements ICommandListener {
/*  31 */     protected CommandSender sender = (CommandSender)new CraftFunctionCommandSender(this);
/*     */ 
/*     */     
/*     */     public String getName() {
/*  35 */       return CustomFunctionData.this.e;
/*     */     }
/*     */     
/*     */     public boolean a(int i, String s) {
/*  39 */       return (i <= 2);
/*     */     }
/*     */     
/*     */     public World getWorld() {
/*  43 */       return CustomFunctionData.this.c.worlds.get(0);
/*     */     }
/*     */     
/*     */     public MinecraftServer C_() {
/*  47 */       return CustomFunctionData.this.c;
/*     */     }
/*     */   }
/*     */   
/*     */   public CustomFunctionData(@Nullable File file, MinecraftServer minecraftserver) {
/*  52 */     this.b = file;
/*  53 */     this.c = minecraftserver;
/*  54 */     f();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CustomFunction a(MinecraftKey minecraftkey) {
/*  59 */     return this.d.get(minecraftkey);
/*     */   }
/*     */   
/*     */   public ICommandHandler a() {
/*  63 */     return this.c.getCommandHandler();
/*     */   }
/*     */   
/*     */   public int c() {
/*  67 */     return ((WorldServer)this.c.worlds.get(0)).getGameRules().c("maxCommandChainLength");
/*     */   }
/*     */   
/*     */   public Map<MinecraftKey, CustomFunction> d() {
/*  71 */     return this.d;
/*     */   }
/*     */   
/*     */   public void e() {
/*  75 */     String s = ((WorldServer)this.c.worlds.get(0)).getGameRules().get("gameLoopFunction");
/*     */     
/*  77 */     if (!s.equals(this.e)) {
/*  78 */       this.e = s;
/*  79 */       this.f = a(new MinecraftKey(s));
/*     */     } 
/*     */     
/*  82 */     if (this.f != null) {
/*  83 */       a(this.f, this.i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(CustomFunction customfunction, ICommandListener icommandlistener) {
/*  89 */     int j, i = c();
/*     */     
/*  91 */     if (this.h) {
/*  92 */       if (this.g.size() < i) {
/*  93 */         this.g.addFirst(new a(this, icommandlistener, new CustomFunction.d(customfunction)));
/*     */       }
/*     */       
/*  96 */       return 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 101 */     try { this.h = true;
/* 102 */       int k = 0;
/* 103 */       CustomFunction.c[] acustomfunction_c = customfunction.a();
/*     */       
/* 105 */       for (j = acustomfunction_c.length - 1; j >= 0; j--) {
/* 106 */         this.g.push(new a(this, icommandlistener, acustomfunction_c[j]));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */       
/*     */        }
/*     */     
/*     */     finally
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 121 */       this.g.clear();
/* 122 */       this.h = false; }  this.g.clear(); this.h = false;
/*     */ 
/*     */     
/* 125 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public void f() {
/* 130 */     this.d.clear();
/* 131 */     this.f = null;
/* 132 */     this.e = "-";
/* 133 */     h();
/*     */   }
/*     */   
/*     */   private void h() {
/* 137 */     if (this.b != null) {
/* 138 */       this.b.mkdirs();
/* 139 */       Iterator<File> iterator = FileUtils.listFiles(this.b, new String[] { "mcfunction" }, true).iterator();
/*     */       
/* 141 */       while (iterator.hasNext()) {
/* 142 */         File file = iterator.next();
/* 143 */         String s = FilenameUtils.removeExtension(this.b.toURI().relativize(file.toURI()).toString());
/* 144 */         String[] astring = s.split("/", 2);
/*     */         
/* 146 */         if (astring.length == 2) {
/* 147 */           MinecraftKey minecraftkey = new MinecraftKey(astring[0], astring[1]);
/*     */           
/*     */           try {
/* 150 */             this.d.put(minecraftkey, CustomFunction.a(this, Files.readLines(file, StandardCharsets.UTF_8)));
/* 151 */           } catch (Throwable throwable) {
/* 152 */             a.error("Couldn't read custom function " + minecraftkey + " from " + file, throwable);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 157 */       if (!this.d.isEmpty()) {
/* 158 */         a.info("Loaded " + this.d.size() + " custom command functions");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static class a
/*     */   {
/*     */     private final CustomFunctionData a;
/*     */     private final ICommandListener b;
/*     */     private final CustomFunction.c c;
/*     */     
/*     */     public a(CustomFunctionData customfunctiondata, ICommandListener icommandlistener, CustomFunction.c customfunction_c) {
/* 171 */       this.a = customfunctiondata;
/* 172 */       this.b = icommandlistener;
/* 173 */       this.c = customfunction_c;
/*     */     }
/*     */     
/*     */     public void a(ArrayDeque<a> arraydeque, int i) {
/* 177 */       this.c.a(this.a, this.b, arraydeque, i);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 181 */       return this.c.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CustomFunctionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */