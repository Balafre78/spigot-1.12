/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.lang.reflect.Type;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChatDeserializer
/*     */ {
/*     */   public static boolean a(JsonObject paramJsonObject, String paramString) {
/*  26 */     if (!f(paramJsonObject, paramString)) {
/*  27 */       return false;
/*     */     }
/*  29 */     return paramJsonObject.getAsJsonPrimitive(paramString).isString();
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
/*     */   public static boolean b(JsonElement paramJsonElement) {
/*  47 */     if (!paramJsonElement.isJsonPrimitive()) {
/*  48 */       return false;
/*     */     }
/*  50 */     return paramJsonElement.getAsJsonPrimitive().isNumber();
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
/*     */   public static boolean d(JsonObject paramJsonObject, String paramString) {
/*  68 */     if (!g(paramJsonObject, paramString)) {
/*  69 */       return false;
/*     */     }
/*  71 */     return paramJsonObject.get(paramString).isJsonArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean f(JsonObject paramJsonObject, String paramString) {
/*  82 */     if (!g(paramJsonObject, paramString)) {
/*  83 */       return false;
/*     */     }
/*  85 */     return paramJsonObject.get(paramString).isJsonPrimitive();
/*     */   }
/*     */   
/*     */   public static boolean g(JsonObject paramJsonObject, String paramString) {
/*  89 */     if (paramJsonObject == null) {
/*  90 */       return false;
/*     */     }
/*  92 */     return (paramJsonObject.get(paramString) != null);
/*     */   }
/*     */   
/*     */   public static String a(JsonElement paramJsonElement, String paramString) {
/*  96 */     if (paramJsonElement.isJsonPrimitive()) {
/*  97 */       return paramJsonElement.getAsString();
/*     */     }
/*  99 */     throw new JsonSyntaxException("Expected " + paramString + " to be a string, was " + d(paramJsonElement));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String h(JsonObject paramJsonObject, String paramString) {
/* 104 */     if (paramJsonObject.has(paramString)) {
/* 105 */       return a(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 107 */     throw new JsonSyntaxException("Missing " + paramString + ", expected to find a string");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String a(JsonObject paramJsonObject, String paramString1, String paramString2) {
/* 112 */     if (paramJsonObject.has(paramString1)) {
/* 113 */       return a(paramJsonObject.get(paramString1), paramString1);
/*     */     }
/* 115 */     return paramString2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item b(JsonElement paramJsonElement, String paramString) {
/* 120 */     if (paramJsonElement.isJsonPrimitive()) {
/* 121 */       String str = paramJsonElement.getAsString();
/* 122 */       Item item = Item.b(str);
/* 123 */       if (item == null) {
/* 124 */         throw new JsonSyntaxException("Expected " + paramString + " to be an item, was unknown string '" + str + "'");
/*     */       }
/* 126 */       return item;
/*     */     } 
/*     */     
/* 129 */     throw new JsonSyntaxException("Expected " + paramString + " to be an item, was " + d(paramJsonElement));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item i(JsonObject paramJsonObject, String paramString) {
/* 134 */     if (paramJsonObject.has(paramString)) {
/* 135 */       return b(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 137 */     throw new JsonSyntaxException("Missing " + paramString + ", expected to find an item");
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
/*     */   public static boolean c(JsonElement paramJsonElement, String paramString) {
/* 150 */     if (paramJsonElement.isJsonPrimitive()) {
/* 151 */       return paramJsonElement.getAsBoolean();
/*     */     }
/* 153 */     throw new JsonSyntaxException("Expected " + paramString + " to be a Boolean, was " + d(paramJsonElement));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean j(JsonObject paramJsonObject, String paramString) {
/* 158 */     if (paramJsonObject.has(paramString)) {
/* 159 */       return c(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 161 */     throw new JsonSyntaxException("Missing " + paramString + ", expected to find a Boolean");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(JsonObject paramJsonObject, String paramString, boolean paramBoolean) {
/* 166 */     if (paramJsonObject.has(paramString)) {
/* 167 */       return c(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 169 */     return paramBoolean;
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
/*     */   public static float e(JsonElement paramJsonElement, String paramString) {
/* 198 */     if (paramJsonElement.isJsonPrimitive() && paramJsonElement.getAsJsonPrimitive().isNumber()) {
/* 199 */       return paramJsonElement.getAsFloat();
/*     */     }
/* 201 */     throw new JsonSyntaxException("Expected " + paramString + " to be a Float, was " + d(paramJsonElement));
/*     */   }
/*     */ 
/*     */   
/*     */   public static float l(JsonObject paramJsonObject, String paramString) {
/* 206 */     if (paramJsonObject.has(paramString)) {
/* 207 */       return e(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 209 */     throw new JsonSyntaxException("Missing " + paramString + ", expected to find a Float");
/*     */   }
/*     */ 
/*     */   
/*     */   public static float a(JsonObject paramJsonObject, String paramString, float paramFloat) {
/* 214 */     if (paramJsonObject.has(paramString)) {
/* 215 */       return e(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 217 */     return paramFloat;
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
/*     */   public static int g(JsonElement paramJsonElement, String paramString) {
/* 246 */     if (paramJsonElement.isJsonPrimitive() && paramJsonElement.getAsJsonPrimitive().isNumber()) {
/* 247 */       return paramJsonElement.getAsInt();
/*     */     }
/* 249 */     throw new JsonSyntaxException("Expected " + paramString + " to be a Int, was " + d(paramJsonElement));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int n(JsonObject paramJsonObject, String paramString) {
/* 254 */     if (paramJsonObject.has(paramString)) {
/* 255 */       return g(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 257 */     throw new JsonSyntaxException("Missing " + paramString + ", expected to find a Int");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int a(JsonObject paramJsonObject, String paramString, int paramInt) {
/* 262 */     if (paramJsonObject.has(paramString)) {
/* 263 */       return g(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 265 */     return paramInt;
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
/*     */   public static JsonObject m(JsonElement paramJsonElement, String paramString) {
/* 390 */     if (paramJsonElement.isJsonObject()) {
/* 391 */       return paramJsonElement.getAsJsonObject();
/*     */     }
/* 393 */     throw new JsonSyntaxException("Expected " + paramString + " to be a JsonObject, was " + d(paramJsonElement));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonObject t(JsonObject paramJsonObject, String paramString) {
/* 398 */     if (paramJsonObject.has(paramString)) {
/* 399 */       return m(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 401 */     throw new JsonSyntaxException("Missing " + paramString + ", expected to find a JsonObject");
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonObject a(JsonObject paramJsonObject1, String paramString, JsonObject paramJsonObject2) {
/* 406 */     if (paramJsonObject1.has(paramString)) {
/* 407 */       return m(paramJsonObject1.get(paramString), paramString);
/*     */     }
/* 409 */     return paramJsonObject2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonArray n(JsonElement paramJsonElement, String paramString) {
/* 414 */     if (paramJsonElement.isJsonArray()) {
/* 415 */       return paramJsonElement.getAsJsonArray();
/*     */     }
/* 417 */     throw new JsonSyntaxException("Expected " + paramString + " to be a JsonArray, was " + d(paramJsonElement));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonArray u(JsonObject paramJsonObject, String paramString) {
/* 422 */     if (paramJsonObject.has(paramString)) {
/* 423 */       return n(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 425 */     throw new JsonSyntaxException("Missing " + paramString + ", expected to find a JsonArray");
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonArray a(JsonObject paramJsonObject, String paramString, @Nullable JsonArray paramJsonArray) {
/* 430 */     if (paramJsonObject.has(paramString)) {
/* 431 */       return n(paramJsonObject.get(paramString), paramString);
/*     */     }
/* 433 */     return paramJsonArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T a(@Nullable JsonElement paramJsonElement, String paramString, JsonDeserializationContext paramJsonDeserializationContext, Class<? extends T> paramClass) {
/* 438 */     if (paramJsonElement != null) {
/* 439 */       return (T)paramJsonDeserializationContext.deserialize(paramJsonElement, paramClass);
/*     */     }
/* 441 */     throw new JsonSyntaxException("Missing " + paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T a(JsonObject paramJsonObject, String paramString, JsonDeserializationContext paramJsonDeserializationContext, Class<? extends T> paramClass) {
/* 446 */     if (paramJsonObject.has(paramString)) {
/* 447 */       return a(paramJsonObject.get(paramString), paramString, paramJsonDeserializationContext, paramClass);
/*     */     }
/* 449 */     throw new JsonSyntaxException("Missing " + paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T a(JsonObject paramJsonObject, String paramString, T paramT, JsonDeserializationContext paramJsonDeserializationContext, Class<? extends T> paramClass) {
/* 454 */     if (paramJsonObject.has(paramString)) {
/* 455 */       return a(paramJsonObject.get(paramString), paramString, paramJsonDeserializationContext, paramClass);
/*     */     }
/* 457 */     return paramT;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String d(JsonElement paramJsonElement) {
/* 462 */     String str = StringUtils.abbreviateMiddle(String.valueOf(paramJsonElement), "...", 10);
/* 463 */     if (paramJsonElement == null) {
/* 464 */       return "null (missing)";
/*     */     }
/* 466 */     if (paramJsonElement.isJsonNull()) {
/* 467 */       return "null (json)";
/*     */     }
/* 469 */     if (paramJsonElement.isJsonArray()) {
/* 470 */       return "an array (" + str + ")";
/*     */     }
/* 472 */     if (paramJsonElement.isJsonObject()) {
/* 473 */       return "an object (" + str + ")";
/*     */     }
/* 475 */     if (paramJsonElement.isJsonPrimitive()) {
/* 476 */       JsonPrimitive jsonPrimitive = paramJsonElement.getAsJsonPrimitive();
/* 477 */       if (jsonPrimitive.isNumber()) {
/* 478 */         return "a number (" + str + ")";
/*     */       }
/* 480 */       if (jsonPrimitive.isBoolean()) {
/* 481 */         return "a boolean (" + str + ")";
/*     */       }
/*     */     } 
/* 484 */     return str;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, Reader paramReader, Class<T> paramClass, boolean paramBoolean) {
/*     */     try {
/* 490 */       JsonReader jsonReader = new JsonReader(paramReader);
/* 491 */       jsonReader.setLenient(paramBoolean);
/* 492 */       return (T)paramGson.getAdapter(paramClass).read(jsonReader);
/* 493 */     } catch (IOException iOException) {
/* 494 */       throw new JsonParseException(iOException);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, Reader paramReader, Type paramType, boolean paramBoolean) {
/*     */     try {
/* 502 */       JsonReader jsonReader = new JsonReader(paramReader);
/* 503 */       jsonReader.setLenient(paramBoolean);
/* 504 */       return (T)paramGson.getAdapter(TypeToken.get(paramType)).read(jsonReader);
/* 505 */     } catch (IOException iOException) {
/* 506 */       throw new JsonParseException(iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, String paramString, Type paramType, boolean paramBoolean) {
/* 512 */     return a(paramGson, new StringReader(paramString), paramType, paramBoolean);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, String paramString, Class<T> paramClass, boolean paramBoolean) {
/* 517 */     return a(paramGson, new StringReader(paramString), paramClass, paramBoolean);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, Reader paramReader, Type paramType) {
/* 522 */     return a(paramGson, paramReader, paramType, false);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, String paramString, Type paramType) {
/* 527 */     return a(paramGson, paramString, paramType, false);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, Reader paramReader, Class<T> paramClass) {
/* 532 */     return a(paramGson, paramReader, paramClass, false);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson paramGson, String paramString, Class<T> paramClass) {
/* 537 */     return a(paramGson, paramString, paramClass, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */