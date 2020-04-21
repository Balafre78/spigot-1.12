/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MojangsonParser
/*     */ {
/*  17 */   private static final Pattern a = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", 2);
/*  18 */   private static final Pattern b = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", 2);
/*  19 */   private static final Pattern c = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", 2);
/*  20 */   private static final Pattern d = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", 2);
/*  21 */   private static final Pattern e = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", 2);
/*  22 */   private static final Pattern f = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", 2);
/*  23 */   private static final Pattern g = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");
/*     */   
/*     */   private final String h;
/*     */   private int i;
/*     */   
/*     */   public static NBTTagCompound parse(String paramString) throws MojangsonParseException {
/*  29 */     return (new MojangsonParser(paramString)).a();
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   NBTTagCompound a() throws MojangsonParseException {
/*  34 */     NBTTagCompound nBTTagCompound = f();
/*     */     
/*  36 */     l();
/*     */     
/*  38 */     if (g()) {
/*  39 */       this.i++;
/*  40 */       throw b("Trailing data found");
/*     */     } 
/*  42 */     return nBTTagCompound;
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   MojangsonParser(String paramString) {
/*  47 */     this.h = paramString;
/*     */   }
/*     */   
/*     */   protected String b() throws MojangsonParseException {
/*  51 */     l();
/*     */     
/*  53 */     if (!g()) {
/*  54 */       throw b("Expected key");
/*     */     }
/*     */     
/*  57 */     return (n() == '"') ? h() : i();
/*     */   }
/*     */   
/*     */   private MojangsonParseException b(String paramString) {
/*  61 */     return new MojangsonParseException(paramString, this.h, this.i);
/*     */   }
/*     */   
/*     */   protected NBTBase c() throws MojangsonParseException {
/*  65 */     l();
/*     */     
/*  67 */     if (n() == '"') {
/*  68 */       return new NBTTagString(h());
/*     */     }
/*     */     
/*  71 */     String str = i();
/*  72 */     if (str.isEmpty()) {
/*  73 */       throw b("Expected value");
/*     */     }
/*  75 */     return c(str);
/*     */   }
/*     */   
/*     */   private NBTBase c(String paramString) {
/*     */     try {
/*  80 */       if (c.matcher(paramString).matches()) {
/*  81 */         return new NBTTagFloat(Float.parseFloat(paramString.substring(0, paramString.length() - 1)));
/*     */       }
/*  83 */       if (d.matcher(paramString).matches()) {
/*  84 */         return new NBTTagByte(Byte.parseByte(paramString.substring(0, paramString.length() - 1)));
/*     */       }
/*  86 */       if (e.matcher(paramString).matches()) {
/*  87 */         return new NBTTagLong(Long.parseLong(paramString.substring(0, paramString.length() - 1)));
/*     */       }
/*  89 */       if (f.matcher(paramString).matches()) {
/*  90 */         return new NBTTagShort(Short.parseShort(paramString.substring(0, paramString.length() - 1)));
/*     */       }
/*  92 */       if (g.matcher(paramString).matches()) {
/*  93 */         return new NBTTagInt(Integer.parseInt(paramString));
/*     */       }
/*  95 */       if (b.matcher(paramString).matches()) {
/*  96 */         return new NBTTagDouble(Double.parseDouble(paramString.substring(0, paramString.length() - 1)));
/*     */       }
/*  98 */       if (a.matcher(paramString).matches()) {
/*  99 */         return new NBTTagDouble(Double.parseDouble(paramString));
/*     */       }
/* 101 */       if ("true".equalsIgnoreCase(paramString)) {
/* 102 */         return new NBTTagByte((byte)1);
/*     */       }
/* 104 */       if ("false".equalsIgnoreCase(paramString)) {
/* 105 */         return new NBTTagByte((byte)0);
/*     */       }
/* 107 */     } catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */     
/* 110 */     return new NBTTagString(paramString);
/*     */   }
/*     */   
/*     */   private String h() throws MojangsonParseException {
/* 114 */     int i = ++this.i;
/*     */     
/* 116 */     StringBuilder stringBuilder = null;
/* 117 */     boolean bool = false;
/* 118 */     while (g()) {
/* 119 */       char c = o();
/* 120 */       if (bool) {
/* 121 */         if (c != '\\' && c != '"') {
/* 122 */           throw b("Invalid escape of '" + c + "'");
/*     */         }
/* 124 */         bool = false;
/*     */       } else {
/* 126 */         if (c == '\\') {
/* 127 */           bool = true;
/* 128 */           if (stringBuilder == null)
/* 129 */             stringBuilder = new StringBuilder(this.h.substring(i, this.i - 1)); 
/*     */           continue;
/*     */         } 
/* 132 */         if (c == '"') {
/* 133 */           return (stringBuilder == null) ? this.h.substring(i, this.i - 1) : stringBuilder.toString();
/*     */         }
/*     */       } 
/* 136 */       if (stringBuilder != null) {
/* 137 */         stringBuilder.append(c);
/*     */       }
/*     */     } 
/* 140 */     throw b("Missing termination quote");
/*     */   }
/*     */   
/*     */   private String i() {
/* 144 */     int i = this.i;
/* 145 */     while (g() && a(n())) {
/* 146 */       this.i++;
/*     */     }
/* 148 */     return this.h.substring(i, this.i);
/*     */   }
/*     */   
/*     */   protected NBTBase d() throws MojangsonParseException {
/* 152 */     l();
/*     */     
/* 154 */     if (!g()) {
/* 155 */       throw b("Expected value");
/*     */     }
/*     */     
/* 158 */     char c = n();
/* 159 */     if (c == '{')
/* 160 */       return f(); 
/* 161 */     if (c == '[') {
/* 162 */       return e();
/*     */     }
/* 164 */     return c();
/*     */   }
/*     */   
/*     */   protected NBTBase e() throws MojangsonParseException {
/* 168 */     if (a(2) && 
/* 169 */       b(1) != '"' && b(2) == ';') {
/* 170 */       return k();
/*     */     }
/*     */     
/* 173 */     return j();
/*     */   }
/*     */   
/*     */   protected NBTTagCompound f() throws MojangsonParseException {
/* 177 */     b('{');
/*     */     
/* 179 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */     
/* 181 */     l();
/* 182 */     while (g() && n() != '}') {
/* 183 */       String str = b();
/* 184 */       if (str.isEmpty()) {
/* 185 */         throw b("Expected non-empty key");
/*     */       }
/*     */       
/* 188 */       b(':');
/*     */       
/* 190 */       nBTTagCompound.set(str, d());
/*     */       
/* 192 */       if (!m())
/*     */         break; 
/* 194 */       if (!g()) {
/* 195 */         throw b("Expected key");
/*     */       }
/*     */     } 
/* 198 */     b('}');
/*     */     
/* 200 */     return nBTTagCompound;
/*     */   }
/*     */   
/*     */   private NBTBase j() throws MojangsonParseException {
/* 204 */     b('[');
/*     */     
/* 206 */     l();
/*     */     
/* 208 */     if (!g()) {
/* 209 */       throw b("Expected value");
/*     */     }
/*     */     
/* 212 */     NBTTagList nBTTagList = new NBTTagList();
/*     */     
/* 214 */     byte b = -1;
/* 215 */     while (n() != ']') {
/* 216 */       NBTBase nBTBase = d();
/*     */       
/* 218 */       byte b1 = nBTBase.getTypeId();
/* 219 */       if (b < 0) {
/* 220 */         b = b1;
/* 221 */       } else if (b1 != b) {
/* 222 */         throw b("Unable to insert " + NBTBase.j(b1) + " into ListTag of type " + NBTBase.j(b));
/*     */       } 
/*     */       
/* 225 */       nBTTagList.add(nBTBase);
/*     */       
/* 227 */       if (!m())
/*     */         break; 
/* 229 */       if (!g()) {
/* 230 */         throw b("Expected value");
/*     */       }
/*     */     } 
/* 233 */     b(']');
/*     */     
/* 235 */     return nBTTagList;
/*     */   }
/*     */   
/*     */   private NBTBase k() throws MojangsonParseException {
/* 239 */     b('[');
/* 240 */     char c = o();
/* 241 */     o();
/*     */     
/* 243 */     l();
/*     */     
/* 245 */     if (!g()) {
/* 246 */       throw b("Expected value");
/*     */     }
/*     */     
/* 249 */     if (c == 'B')
/* 250 */       return new NBTTagByteArray(a((byte)7, (byte)1)); 
/* 251 */     if (c == 'L')
/* 252 */       return new NBTTagLongArray(a((byte)12, (byte)4)); 
/* 253 */     if (c == 'I') {
/* 254 */       return new NBTTagIntArray(a((byte)11, (byte)3));
/*     */     }
/* 256 */     throw b("Invalid array type '" + c + "' found");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private <T extends Number> List<T> a(byte paramByte1, byte paramByte2) throws MojangsonParseException {
/* 262 */     ArrayList<Byte> arrayList = Lists.newArrayList();
/*     */     
/* 264 */     while (n() != ']') {
/* 265 */       NBTBase nBTBase = d();
/*     */       
/* 267 */       byte b = nBTBase.getTypeId();
/* 268 */       if (b != paramByte2) {
/* 269 */         throw b("Unable to insert " + NBTBase.j(b) + " into " + NBTBase.j(paramByte1));
/*     */       }
/*     */       
/* 272 */       if (paramByte2 == 1) {
/* 273 */         arrayList.add(Byte.valueOf(((NBTNumber)nBTBase).g()));
/* 274 */       } else if (paramByte2 == 4) {
/* 275 */         arrayList.add(Long.valueOf(((NBTNumber)nBTBase).d()));
/*     */       } else {
/* 277 */         arrayList.add(Integer.valueOf(((NBTNumber)nBTBase).e()));
/*     */       } 
/*     */       
/* 280 */       if (!m())
/*     */         break; 
/* 282 */       if (!g()) {
/* 283 */         throw b("Expected value");
/*     */       }
/*     */     } 
/* 286 */     b(']');
/*     */     
/* 288 */     return (List)arrayList;
/*     */   }
/*     */   
/*     */   private void l() {
/* 292 */     while (g() && Character.isWhitespace(n())) {
/* 293 */       this.i++;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean m() {
/* 298 */     l();
/* 299 */     if (g() && n() == ',') {
/* 300 */       this.i++;
/* 301 */       l();
/* 302 */       return true;
/*     */     } 
/* 304 */     return false;
/*     */   }
/*     */   
/*     */   private void b(char paramChar) throws MojangsonParseException {
/* 308 */     l();
/*     */     
/* 310 */     boolean bool = g();
/* 311 */     if (bool && n() == paramChar) {
/* 312 */       this.i++;
/*     */       return;
/*     */     } 
/* 315 */     throw new MojangsonParseException("Expected '" + paramChar + "' but got '" + (bool ? Character.valueOf(n()) : "<EOF>") + "'", this.h, this.i + 1);
/*     */   }
/*     */   
/*     */   protected boolean a(char paramChar) {
/* 319 */     return ((paramChar >= '0' && paramChar <= '9') || (paramChar >= 'A' && paramChar <= 'Z') || (paramChar >= 'a' && paramChar <= 'z') || paramChar == '_' || paramChar == '-' || paramChar == '.' || paramChar == '+');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(int paramInt) {
/* 327 */     return (this.i + paramInt < this.h.length());
/*     */   }
/*     */   
/*     */   boolean g() {
/* 331 */     return a(0);
/*     */   }
/*     */   
/*     */   private char b(int paramInt) {
/* 335 */     return this.h.charAt(this.i + paramInt);
/*     */   }
/*     */   
/*     */   private char n() {
/* 339 */     return b(0);
/*     */   }
/*     */   
/*     */   private char o() {
/* 343 */     return this.h.charAt(this.i++);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MojangsonParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */