/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ 
/*     */ public class JsonList<K, V extends JsonListEntry<K>> {
/*  35 */   protected static final Logger a = LogManager.getLogger();
/*     */   protected final Gson b;
/*     */   private final File c;
/*  38 */   private final Map<String, V> d = Maps.newHashMap();
/*     */   
/*  40 */   private static final ParameterizedType f = new ParameterizedType() {
/*     */       public Type[] getActualTypeArguments() {
/*  42 */         return new Type[] { JsonListEntry.class };
/*     */       }
/*     */       
/*     */       public Type getRawType() {
/*  46 */         return List.class;
/*     */       }
/*     */       
/*     */       public Type getOwnerType() {
/*  50 */         return null;
/*     */       }
/*     */     };
/*     */   private boolean e = true;
/*     */   public JsonList(File file) {
/*  55 */     this.c = file;
/*  56 */     GsonBuilder gsonbuilder = (new GsonBuilder()).setPrettyPrinting();
/*     */     
/*  58 */     gsonbuilder.registerTypeHierarchyAdapter(JsonListEntry.class, new JsonListEntrySerializer(null));
/*  59 */     this.b = gsonbuilder.create();
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/*  63 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/*  67 */     this.e = flag;
/*     */   }
/*     */   
/*     */   public File c() {
/*  71 */     return this.c;
/*     */   }
/*     */   
/*     */   public void add(V v0) {
/*  75 */     this.d.put(a(v0.getKey()), v0);
/*     */     
/*     */     try {
/*  78 */       save();
/*  79 */     } catch (IOException ioexception) {
/*  80 */       a.warn("Could not save the list after adding a user.", ioexception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public V get(K k0) {
/*  86 */     h();
/*  87 */     return this.d.get(a(k0));
/*     */   }
/*     */   
/*     */   public void remove(K k0) {
/*  91 */     this.d.remove(a(k0));
/*     */     
/*     */     try {
/*  94 */       save();
/*  95 */     } catch (IOException ioexception) {
/*  96 */       a.warn("Could not save the list after removing a user.", ioexception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getEntries() {
/* 102 */     return (String[])this.d.keySet().toArray((Object[])new String[this.d.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<V> getValues() {
/* 107 */     return this.d.values();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 112 */     return (this.d.size() < 1);
/*     */   }
/*     */   
/*     */   protected String a(K k0) {
/* 116 */     return k0.toString();
/*     */   }
/*     */   
/*     */   protected boolean d(K k0) {
/* 120 */     return this.d.containsKey(a(k0));
/*     */   }
/*     */   
/*     */   private void h() {
/* 124 */     ArrayList<JsonListEntry> arraylist = Lists.newArrayList();
/* 125 */     Iterator<JsonListEntry> iterator = this.d.values().iterator();
/*     */     
/* 127 */     while (iterator.hasNext()) {
/* 128 */       JsonListEntry jsonlistentry = iterator.next();
/*     */       
/* 130 */       if (jsonlistentry.hasExpired()) {
/* 131 */         arraylist.add(jsonlistentry.getKey());
/*     */       }
/*     */     } 
/*     */     
/* 135 */     iterator = arraylist.iterator();
/*     */     
/* 137 */     while (iterator.hasNext()) {
/* 138 */       Object object = iterator.next();
/*     */       
/* 140 */       this.d.remove(object);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected JsonListEntry<K> a(JsonObject jsonobject) {
/* 146 */     return new JsonListEntry<>(null, jsonobject);
/*     */   }
/*     */   
/*     */   protected Map<String, V> e() {
/* 150 */     return this.d;
/*     */   }
/*     */   
/*     */   public void save() throws IOException {
/* 154 */     Collection<V> collection = this.d.values();
/* 155 */     String s = this.b.toJson(collection);
/* 156 */     BufferedWriter bufferedwriter = null;
/*     */     
/*     */     try {
/* 159 */       bufferedwriter = Files.newWriter(this.c, StandardCharsets.UTF_8);
/* 160 */       bufferedwriter.write(s);
/*     */     } finally {
/* 162 */       IOUtils.closeQuietly(bufferedwriter);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void load() throws FileNotFoundException {
/* 168 */     if (this.c.exists()) {
/* 169 */       Collection collection = null;
/* 170 */       BufferedReader bufferedreader = null;
/*     */       
/*     */       try {
/* 173 */         bufferedreader = Files.newReader(this.c, StandardCharsets.UTF_8);
/* 174 */         collection = ChatDeserializer.<Collection>a(this.b, bufferedreader, f);
/*     */       }
/* 176 */       catch (JsonParseException ex) {
/*     */         
/* 178 */         Bukkit.getLogger().log(Level.WARNING, "Unable to read file " + this.c + ", backing it up to {0}.backup and creating new copy.", (Throwable)ex);
/* 179 */         File backup = new File(this.c + ".backup");
/* 180 */         this.c.renameTo(backup);
/* 181 */         this.c.delete();
/*     */       } finally {
/*     */         
/* 184 */         IOUtils.closeQuietly(bufferedreader);
/*     */       } 
/*     */       
/* 187 */       if (collection != null) {
/* 188 */         this.d.clear();
/* 189 */         Iterator<JsonListEntry> iterator = collection.iterator();
/*     */         
/* 191 */         while (iterator.hasNext()) {
/* 192 */           JsonListEntry<K> jsonlistentry = iterator.next();
/*     */           
/* 194 */           if (jsonlistentry.getKey() != null) {
/* 195 */             this.d.put(a(jsonlistentry.getKey()), (V)jsonlistentry);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   class JsonListEntrySerializer
/*     */     implements JsonDeserializer<JsonListEntry<K>>, JsonSerializer<JsonListEntry<K>>
/*     */   {
/*     */     private JsonListEntrySerializer() {}
/*     */     
/*     */     public JsonElement a(JsonListEntry<K> jsonlistentry, Type type, JsonSerializationContext jsonserializationcontext) {
/* 208 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 210 */       jsonlistentry.a(jsonobject);
/* 211 */       return (JsonElement)jsonobject;
/*     */     }
/*     */     
/*     */     public JsonListEntry<K> a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 215 */       if (jsonelement.isJsonObject()) {
/* 216 */         JsonObject jsonobject = jsonelement.getAsJsonObject();
/*     */         
/* 218 */         return JsonList.this.a(jsonobject);
/*     */       } 
/* 220 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(JsonListEntry<K> object, Type type, JsonSerializationContext jsonserializationcontext) {
/* 225 */       return a(object, type, jsonserializationcontext);
/*     */     }
/*     */     
/*     */     public JsonListEntry<K> deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 229 */       return a(jsonelement, type, jsondeserializationcontext);
/*     */     }
/*     */     
/*     */     JsonListEntrySerializer(Object object) {
/* 233 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\JsonList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */