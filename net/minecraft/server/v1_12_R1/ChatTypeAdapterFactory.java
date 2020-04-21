/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import com.google.gson.stream.JsonReader;
/*    */ import com.google.gson.stream.JsonToken;
/*    */ import com.google.gson.stream.JsonWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatTypeAdapterFactory
/*    */   implements TypeAdapterFactory
/*    */ {
/*    */   @Nullable
/*    */   public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken) {
/* 24 */     Class<Object> clazz = paramTypeToken.getRawType();
/* 25 */     if (!clazz.isEnum()) {
/* 26 */       return null;
/*    */     }
/*    */     
/* 29 */     HashMap<String, Object> hashMap = Maps.newHashMap();
/* 30 */     for (Object object : clazz.getEnumConstants()) {
/* 31 */       hashMap.put(a(object), object);
/*    */     }
/*    */     
/* 34 */     return new TypeAdapter<T>(this, hashMap)
/*    */       {
/*    */         public void write(JsonWriter param1JsonWriter, T param1T) throws IOException {
/* 37 */           if (param1T == null) {
/* 38 */             param1JsonWriter.nullValue();
/*    */           } else {
/* 40 */             param1JsonWriter.value(ChatTypeAdapterFactory.a(this.b, param1T));
/*    */           } 
/*    */         }
/*    */ 
/*    */         
/*    */         @Nullable
/*    */         public T read(JsonReader param1JsonReader) throws IOException {
/* 47 */           if (param1JsonReader.peek() == JsonToken.NULL) {
/* 48 */             param1JsonReader.nextNull();
/* 49 */             return null;
/*    */           } 
/* 51 */           return (T)this.a.get(param1JsonReader.nextString());
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   private String a(Object paramObject) {
/* 58 */     if (paramObject instanceof Enum) {
/* 59 */       return ((Enum)paramObject).name().toLowerCase(Locale.ROOT);
/*    */     }
/* 61 */     return paramObject.toString().toLowerCase(Locale.ROOT);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatTypeAdapterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */