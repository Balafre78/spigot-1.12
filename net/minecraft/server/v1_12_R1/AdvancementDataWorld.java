/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ 
/*     */ public class AdvancementDataWorld
/*     */ {
/*  38 */   private static final Logger a = LogManager.getLogger();
/*  39 */   public static final Gson DESERIALIZER = (new GsonBuilder()).registerTypeHierarchyAdapter(Advancement.SerializedAdvancement.class, new JsonDeserializer() {
/*     */         public Advancement.SerializedAdvancement a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/*  41 */           JsonObject jsonobject = ChatDeserializer.m(jsonelement, "advancement");
/*     */           
/*  43 */           return Advancement.SerializedAdvancement.a(jsonobject, jsondeserializationcontext);
/*     */         }
/*     */         
/*     */         public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/*  47 */           return a(jsonelement, type, jsondeserializationcontext);
/*     */         }
/*  49 */       }).registerTypeAdapter(AdvancementRewards.class, new AdvancementRewards.a()).registerTypeHierarchyAdapter(IChatBaseComponent.class, new IChatBaseComponent.ChatSerializer()).registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifier.ChatModifierSerializer()).registerTypeAdapterFactory(new ChatTypeAdapterFactory()).create();
/*  50 */   public static final Advancements REGISTRY = new Advancements();
/*     */   public final File folder;
/*     */   private boolean e;
/*     */   
/*     */   public AdvancementDataWorld(@Nullable File file) {
/*  55 */     this.folder = file;
/*  56 */     reload();
/*     */   }
/*     */   
/*     */   public void reload() {
/*  60 */     this.e = false;
/*  61 */     REGISTRY.a();
/*  62 */     Map<MinecraftKey, Advancement.SerializedAdvancement> map = d();
/*     */     
/*  64 */     a(map);
/*  65 */     REGISTRY.a(map);
/*  66 */     Iterator<Advancement> iterator = REGISTRY.b().iterator();
/*     */     
/*  68 */     while (iterator.hasNext()) {
/*  69 */       Advancement advancement = iterator.next();
/*     */       
/*  71 */       if (advancement.c() != null) {
/*  72 */         AdvancementTree.a(advancement);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  79 */     return this.e;
/*     */   }
/*     */   
/*     */   private Map<MinecraftKey, Advancement.SerializedAdvancement> d() {
/*  83 */     if (this.folder == null) {
/*  84 */       return Maps.newHashMap();
/*     */     }
/*  86 */     HashMap<MinecraftKey, Advancement.SerializedAdvancement> hashmap = Maps.newHashMap();
/*     */     
/*  88 */     this.folder.mkdirs();
/*  89 */     Iterator<File> iterator = FileUtils.listFiles(this.folder, new String[] { "json" }, true).iterator();
/*     */     
/*  91 */     while (iterator.hasNext()) {
/*  92 */       File file = iterator.next();
/*  93 */       String s = FilenameUtils.removeExtension(this.folder.toURI().relativize(file.toURI()).toString());
/*  94 */       String[] astring = s.split("/", 2);
/*     */       
/*  96 */       if (astring.length == 2) {
/*  97 */         MinecraftKey minecraftkey = new MinecraftKey(astring[0], astring[1]);
/*     */         
/*     */         try {
/* 100 */           Advancement.SerializedAdvancement advancement_serializedadvancement = ChatDeserializer.<Advancement.SerializedAdvancement>a(DESERIALIZER, FileUtils.readFileToString(file, StandardCharsets.UTF_8), Advancement.SerializedAdvancement.class);
/*     */           
/* 102 */           if (advancement_serializedadvancement == null) {
/* 103 */             a.error("Couldn't load custom advancement " + minecraftkey + " from " + file + " as it's empty or null"); continue;
/*     */           } 
/* 105 */           hashmap.put(minecraftkey, advancement_serializedadvancement);
/*     */         }
/* 107 */         catch (IllegalArgumentException|JsonParseException jsonparseexception) {
/* 108 */           a.error("Parsing error loading custom advancement " + minecraftkey, jsonparseexception);
/* 109 */           this.e = true;
/* 110 */         } catch (IOException ioexception) {
/* 111 */           a.error("Couldn't read custom advancement " + minecraftkey + " from " + file, ioexception);
/* 112 */           this.e = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     return hashmap;
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Map<MinecraftKey, Advancement.SerializedAdvancement> map) {
/* 122 */     FileSystem filesystem = null;
/*     */     
/*     */     try {
/* 125 */       URL url = AdvancementDataWorld.class.getResource("/assets/.mcassetsroot");
/*     */       
/* 127 */       if (url == null) {
/* 128 */         a.error("Couldn't find .mcassetsroot");
/* 129 */         this.e = true;
/*     */       } else {
/* 131 */         Path java_nio_file_path; URI uri = url.toURI();
/*     */ 
/*     */         
/* 134 */         if ("file".equals(uri.getScheme())) {
/* 135 */           java_nio_file_path = Paths.get(CraftingManager.class.getResource("/assets/minecraft/advancements").toURI());
/*     */         } else {
/* 137 */           if (!"jar".equals(uri.getScheme())) {
/* 138 */             a.error("Unsupported scheme " + uri + " trying to list all built-in advancements (NYI?)");
/* 139 */             this.e = true;
/*     */             
/*     */             return;
/*     */           } 
/* 143 */           filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
/* 144 */           java_nio_file_path = filesystem.getPath("/assets/minecraft/advancements", new String[0]);
/*     */         } 
/*     */         
/* 147 */         Iterator<Path> iterator = Files.walk(java_nio_file_path, new java.nio.file.FileVisitOption[0]).iterator();
/*     */         
/* 149 */         while (iterator.hasNext()) {
/* 150 */           Path java_nio_file_path1 = iterator.next();
/*     */           
/* 152 */           if ("json".equals(FilenameUtils.getExtension(java_nio_file_path1.toString()))) {
/* 153 */             Path java_nio_file_path2 = java_nio_file_path.relativize(java_nio_file_path1);
/* 154 */             String s = FilenameUtils.removeExtension(java_nio_file_path2.toString()).replaceAll("\\\\", "/");
/* 155 */             MinecraftKey minecraftkey = new MinecraftKey("minecraft", s);
/*     */             
/* 157 */             if (SpigotConfig.disabledAdvancements.contains("*") || SpigotConfig.disabledAdvancements.contains(minecraftkey.toString())) {
/*     */               continue;
/*     */             }
/*     */ 
/*     */             
/* 162 */             if (!map.containsKey(minecraftkey)) {
/* 163 */               BufferedReader bufferedreader = null;
/*     */               
/*     */               try {
/* 166 */                 bufferedreader = Files.newBufferedReader(java_nio_file_path1);
/* 167 */                 Advancement.SerializedAdvancement advancement_serializedadvancement = ChatDeserializer.<Advancement.SerializedAdvancement>a(DESERIALIZER, bufferedreader, Advancement.SerializedAdvancement.class);
/*     */                 
/* 169 */                 map.put(minecraftkey, advancement_serializedadvancement);
/* 170 */               } catch (JsonParseException jsonparseexception) {
/* 171 */                 a.error("Parsing error loading built-in advancement " + minecraftkey, (Throwable)jsonparseexception);
/* 172 */                 this.e = true; continue;
/* 173 */               } catch (IOException ioexception) {
/* 174 */                 a.error("Couldn't read advancement " + minecraftkey + " from " + java_nio_file_path1, ioexception);
/* 175 */                 this.e = true; continue;
/*     */               } finally {
/* 177 */                 IOUtils.closeQuietly(bufferedreader);
/*     */               }
/*     */             
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 184 */     } catch (IOException|java.net.URISyntaxException urisyntaxexception) {
/* 185 */       a.error("Couldn't get a list of all built-in advancement files", urisyntaxexception);
/* 186 */       this.e = true;
/*     */     } finally {
/* 188 */       IOUtils.closeQuietly(filesystem);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Advancement a(MinecraftKey minecraftkey) {
/* 194 */     return REGISTRY.a(minecraftkey);
/*     */   }
/*     */   
/*     */   public Iterable<Advancement> c() {
/* 198 */     return REGISTRY.c();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AdvancementDataWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */