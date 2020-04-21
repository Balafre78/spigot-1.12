/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class LocaleLanguage
/*     */ {
/*  16 */   private static final Pattern a = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
/*  17 */   private static final Splitter b = Splitter.on('=').limit(2);
/*     */   
/*  19 */   private static final LocaleLanguage c = new LocaleLanguage();
/*     */   
/*  21 */   private final Map<String, String> d = Maps.newHashMap();
/*     */   private long e;
/*     */   
/*     */   public LocaleLanguage() {
/*     */     try {
/*  26 */       InputStream inputStream = LocaleLanguage.class.getResourceAsStream("/assets/minecraft/lang/en_us.lang");
/*  27 */       for (String str1 : IOUtils.readLines(inputStream, StandardCharsets.UTF_8)) {
/*     */         
/*  29 */         if (str1.isEmpty() || str1.charAt(0) == '#') {
/*     */           continue;
/*     */         }
/*     */         
/*  33 */         String[] arrayOfString = (String[])Iterables.toArray(b.split(str1), String.class);
/*     */ 
/*     */         
/*  36 */         if (arrayOfString == null || arrayOfString.length != 2) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/*  41 */         String str2 = arrayOfString[0];
/*  42 */         String str3 = a.matcher(arrayOfString[1]).replaceAll("%$1s");
/*     */         
/*  44 */         this.d.put(str2, str3);
/*     */       } 
/*  46 */       this.e = System.currentTimeMillis();
/*  47 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static LocaleLanguage a() {
/*  54 */     return c;
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
/*     */   public synchronized String a(String paramString) {
/*  92 */     return c(paramString);
/*     */   }
/*     */   
/*     */   public synchronized String a(String paramString, Object... paramVarArgs) {
/*  96 */     String str = c(paramString);
/*     */     try {
/*  98 */       return String.format(str, paramVarArgs);
/*  99 */     } catch (IllegalFormatException illegalFormatException) {
/* 100 */       return "Format error: " + str;
/*     */     } 
/*     */   }
/*     */   
/*     */   private String c(String paramString) {
/* 105 */     String str = this.d.get(paramString);
/* 106 */     return (str == null) ? paramString : str;
/*     */   }
/*     */   
/*     */   public synchronized boolean b(String paramString) {
/* 110 */     return this.d.containsKey(paramString);
/*     */   }
/*     */   
/*     */   public long c() {
/* 114 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LocaleLanguage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */