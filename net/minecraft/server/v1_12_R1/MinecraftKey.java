/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinecraftKey
/*     */   implements Comparable<MinecraftKey>
/*     */ {
/*     */   protected final String a;
/*     */   protected final String b;
/*     */   
/*     */   protected MinecraftKey(int paramInt, String... paramVarArgs) {
/*  25 */     this.a = StringUtils.isEmpty(paramVarArgs[0]) ? "minecraft" : paramVarArgs[0].toLowerCase(Locale.ROOT);
/*  26 */     this.b = paramVarArgs[1].toLowerCase(Locale.ROOT);
/*     */     
/*  28 */     Validate.notNull(this.b);
/*     */   }
/*     */   
/*     */   public MinecraftKey(String paramString) {
/*  32 */     this(0, a(paramString));
/*     */   }
/*     */   
/*     */   public MinecraftKey(String paramString1, String paramString2) {
/*  36 */     this(0, new String[] { paramString1, paramString2 });
/*     */   }
/*     */   
/*     */   protected static String[] a(String paramString) {
/*  40 */     String[] arrayOfString = { "minecraft", paramString };
/*  41 */     int i = paramString.indexOf(':');
/*  42 */     if (i >= 0) {
/*  43 */       arrayOfString[1] = paramString.substring(i + 1, paramString.length());
/*  44 */       if (i > 1) {
/*  45 */         arrayOfString[0] = paramString.substring(0, i);
/*     */       }
/*     */     } 
/*     */     
/*  49 */     return arrayOfString;
/*     */   }
/*     */   
/*     */   public String getKey() {
/*  53 */     return this.b;
/*     */   }
/*     */   
/*     */   public String b() {
/*  57 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  62 */     return this.a + ':' + this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*  67 */     if (this == paramObject) {
/*  68 */       return true;
/*     */     }
/*     */     
/*  71 */     if (paramObject instanceof MinecraftKey) {
/*  72 */       MinecraftKey minecraftKey = (MinecraftKey)paramObject;
/*     */       
/*  74 */       return (this.a.equals(minecraftKey.a) && this.b.equals(minecraftKey.b));
/*     */     } 
/*     */     
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  82 */     return 31 * this.a.hashCode() + this.b.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(MinecraftKey paramMinecraftKey) {
/*  87 */     int i = this.a.compareTo(paramMinecraftKey.a);
/*  88 */     if (i == 0) {
/*  89 */       i = this.b.compareTo(paramMinecraftKey.b);
/*     */     }
/*  91 */     return i;
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements JsonDeserializer<MinecraftKey>, JsonSerializer<MinecraftKey> {
/*     */     public MinecraftKey a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/*  97 */       return new MinecraftKey(ChatDeserializer.a(param1JsonElement, "location"));
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a(MinecraftKey param1MinecraftKey, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 102 */       return (JsonElement)new JsonPrimitive(param1MinecraftKey.toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MinecraftKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */