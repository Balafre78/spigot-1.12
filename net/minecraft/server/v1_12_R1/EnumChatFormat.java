/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public enum EnumChatFormat
/*     */ {
/*  14 */   BLACK("BLACK", '0', 0),
/*  15 */   DARK_BLUE("DARK_BLUE", '1', 1),
/*  16 */   DARK_GREEN("DARK_GREEN", '2', 2),
/*  17 */   DARK_AQUA("DARK_AQUA", '3', 3),
/*  18 */   DARK_RED("DARK_RED", '4', 4),
/*  19 */   DARK_PURPLE("DARK_PURPLE", '5', 5),
/*  20 */   GOLD("GOLD", '6', 6),
/*  21 */   GRAY("GRAY", '7', 7),
/*  22 */   DARK_GRAY("DARK_GRAY", '8', 8),
/*  23 */   BLUE("BLUE", '9', 9),
/*  24 */   GREEN("GREEN", 'a', 10),
/*  25 */   AQUA("AQUA", 'b', 11),
/*  26 */   RED("RED", 'c', 12),
/*  27 */   LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13),
/*  28 */   YELLOW("YELLOW", 'e', 14),
/*  29 */   WHITE("WHITE", 'f', 15),
/*  30 */   OBFUSCATED("OBFUSCATED", 'k', true),
/*  31 */   BOLD("BOLD", 'l', true),
/*  32 */   STRIKETHROUGH("STRIKETHROUGH", 'm', true),
/*  33 */   UNDERLINE("UNDERLINE", 'n', true),
/*  34 */   ITALIC("ITALIC", 'o', true),
/*  35 */   RESET("RESET", 'r', -1); private static final Map<String, EnumChatFormat> w; private static final Pattern x; private final String y;
/*     */   
/*     */   static {
/*  38 */     w = Maps.newHashMap();
/*  39 */     x = Pattern.compile("(?i)§[0-9A-FK-OR]");
/*     */ 
/*     */     
/*  42 */     for (EnumChatFormat enumChatFormat : values())
/*  43 */       w.put(c(enumChatFormat.y), enumChatFormat); 
/*     */   }
/*     */   public final char character; private final boolean A; private final String B; private final int C;
/*     */   
/*     */   private static String c(String paramString) {
/*  48 */     return paramString.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
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
/*     */   EnumChatFormat(String paramString1, char paramChar, boolean paramBoolean, int paramInt1) {
/*  66 */     this.y = paramString1;
/*  67 */     this.character = paramChar;
/*  68 */     this.A = paramBoolean;
/*  69 */     this.C = paramInt1;
/*     */     
/*  71 */     this.B = "§" + paramChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/*  79 */     return this.C;
/*     */   }
/*     */   
/*     */   public boolean isFormat() {
/*  83 */     return this.A;
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  87 */     return (!this.A && this != RESET);
/*     */   }
/*     */   
/*     */   public String e() {
/*  91 */     return name().toLowerCase(Locale.ROOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  96 */     return this.B;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static String a(@Nullable String paramString) {
/* 101 */     return (paramString == null) ? null : x.matcher(paramString).replaceAll("");
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static EnumChatFormat b(@Nullable String paramString) {
/* 106 */     if (paramString == null) {
/* 107 */       return null;
/*     */     }
/* 109 */     return w.get(c(paramString));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static EnumChatFormat a(int paramInt) {
/* 114 */     if (paramInt < 0) {
/* 115 */       return RESET;
/*     */     }
/* 117 */     for (EnumChatFormat enumChatFormat : values()) {
/* 118 */       if (enumChatFormat.b() == paramInt) {
/* 119 */         return enumChatFormat;
/*     */       }
/*     */     } 
/* 122 */     return null;
/*     */   }
/*     */   
/*     */   public static Collection<String> a(boolean paramBoolean1, boolean paramBoolean2) {
/* 126 */     ArrayList<String> arrayList = Lists.newArrayList();
/*     */     
/* 128 */     for (EnumChatFormat enumChatFormat : values()) {
/* 129 */       if (!enumChatFormat.d() || paramBoolean1)
/*     */       {
/*     */         
/* 132 */         if (!enumChatFormat.isFormat() || paramBoolean2)
/*     */         {
/*     */           
/* 135 */           arrayList.add(enumChatFormat.e()); } 
/*     */       }
/*     */     } 
/* 138 */     return arrayList;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumChatFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */