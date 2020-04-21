/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.common.io.Resources;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LootTableRegistry
/*     */ {
/*  27 */   private static final Logger a = LogManager.getLogger();
/*  28 */   private static final Gson b = (new GsonBuilder())
/*  29 */     .registerTypeAdapter(LootValueBounds.class, new LootValueBounds.a())
/*  30 */     .registerTypeAdapter(LootSelector.class, new LootSelector.a())
/*  31 */     .registerTypeAdapter(LootTable.class, new LootTable.a())
/*  32 */     .registerTypeHierarchyAdapter(LotoSelectorEntry.class, new LotoSelectorEntry.a())
/*  33 */     .registerTypeHierarchyAdapter(LootItemFunction.class, new LootItemFunctions.a())
/*  34 */     .registerTypeHierarchyAdapter(LootItemCondition.class, new LootItemConditions.a())
/*  35 */     .registerTypeHierarchyAdapter(LootTableInfo.EntityTarget.class, new LootTableInfo.EntityTarget.a())
/*  36 */     .create();
/*     */   
/*  38 */   private final LoadingCache<MinecraftKey, LootTable> c = CacheBuilder.newBuilder().build(new a());
/*     */   private final File d;
/*     */   
/*     */   public LootTableRegistry(@Nullable File paramFile) {
/*  42 */     this.d = paramFile;
/*  43 */     reload();
/*     */   }
/*     */   
/*     */   public LootTable a(MinecraftKey paramMinecraftKey) {
/*  47 */     return (LootTable)this.c.getUnchecked(paramMinecraftKey);
/*     */   }
/*     */   
/*     */   public void reload() {
/*  51 */     this.c.invalidateAll();
/*  52 */     for (MinecraftKey minecraftKey : LootTables.a())
/*  53 */       a(minecraftKey); 
/*     */   }
/*     */   
/*     */   class a
/*     */     extends CacheLoader<MinecraftKey, LootTable> {
/*     */     private a(LootTableRegistry this$0) {}
/*     */     
/*     */     public LootTable a(MinecraftKey param1MinecraftKey) throws Exception {
/*  61 */       if (param1MinecraftKey.getKey().contains(".")) {
/*  62 */         LootTableRegistry.b().debug("Invalid loot table name '{}' (can't contain periods)", param1MinecraftKey);
/*  63 */         return LootTable.a;
/*     */       } 
/*  65 */       LootTable lootTable = b(param1MinecraftKey);
/*     */       
/*  67 */       if (lootTable == null) {
/*  68 */         lootTable = c(param1MinecraftKey);
/*     */       }
/*     */       
/*  71 */       if (lootTable == null) {
/*  72 */         lootTable = LootTable.a;
/*  73 */         LootTableRegistry.b().warn("Couldn't find resource table {}", param1MinecraftKey);
/*     */       } 
/*     */       
/*  76 */       return lootTable;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private LootTable b(MinecraftKey param1MinecraftKey) {
/*  81 */       if (LootTableRegistry.a(this.a) == null) {
/*  82 */         return null;
/*     */       }
/*     */       
/*  85 */       File file = new File(new File(LootTableRegistry.a(this.a), param1MinecraftKey.b()), param1MinecraftKey.getKey() + ".json");
/*     */       
/*  87 */       if (file.exists()) {
/*  88 */         if (file.isFile()) {
/*     */           String str;
/*     */           try {
/*  91 */             str = Files.toString(file, StandardCharsets.UTF_8);
/*  92 */           } catch (IOException iOException) {
/*  93 */             LootTableRegistry.b().warn("Couldn't load loot table {} from {}", param1MinecraftKey, file, iOException);
/*  94 */             return LootTable.a;
/*     */           } 
/*     */           try {
/*  97 */             return ChatDeserializer.<LootTable>a(LootTableRegistry.c(), str, LootTable.class);
/*  98 */           } catch (JsonParseException|IllegalArgumentException jsonParseException) {
/*  99 */             LootTableRegistry.b().error("Couldn't load loot table {} from {}", param1MinecraftKey, file, jsonParseException);
/* 100 */             return LootTable.a;
/*     */           } 
/*     */         } 
/* 103 */         LootTableRegistry.b().warn("Expected to find loot table {} at {} but it was a folder.", param1MinecraftKey, file);
/* 104 */         return LootTable.a;
/*     */       } 
/*     */ 
/*     */       
/* 108 */       return null;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private LootTable c(MinecraftKey param1MinecraftKey) {
/* 113 */       URL uRL = LootTableRegistry.class.getResource("/assets/" + param1MinecraftKey.b() + "/loot_tables/" + param1MinecraftKey.getKey() + ".json");
/*     */       
/* 115 */       if (uRL != null) {
/*     */         String str;
/*     */         try {
/* 118 */           str = Resources.toString(uRL, StandardCharsets.UTF_8);
/* 119 */         } catch (IOException iOException) {
/* 120 */           LootTableRegistry.b().warn("Couldn't load loot table {} from {}", param1MinecraftKey, uRL, iOException);
/* 121 */           return LootTable.a;
/*     */         } 
/*     */         try {
/* 124 */           return ChatDeserializer.<LootTable>a(LootTableRegistry.c(), str, LootTable.class);
/* 125 */         } catch (JsonParseException jsonParseException) {
/* 126 */           LootTableRegistry.b().error("Couldn't load loot table {} from {}", param1MinecraftKey, uRL, jsonParseException);
/* 127 */           return LootTable.a;
/*     */         } 
/*     */       } 
/*     */       
/* 131 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootTableRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */