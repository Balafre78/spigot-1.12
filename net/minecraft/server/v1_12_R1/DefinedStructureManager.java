/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.io.IOUtils;
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
/*     */ public class DefinedStructureManager
/*     */ {
/*  27 */   private final Map<String, DefinedStructure> a = Maps.newHashMap();
/*     */   private final String b;
/*     */   private final DataConverterManager c;
/*     */   
/*     */   public DefinedStructureManager(String paramString, DataConverterManager paramDataConverterManager) {
/*  32 */     this.b = paramString;
/*  33 */     this.c = paramDataConverterManager;
/*     */   }
/*     */   
/*     */   public DefinedStructure a(@Nullable MinecraftServer paramMinecraftServer, MinecraftKey paramMinecraftKey) {
/*  37 */     DefinedStructure definedStructure = b(paramMinecraftServer, paramMinecraftKey);
/*  38 */     if (definedStructure == null) {
/*  39 */       definedStructure = new DefinedStructure();
/*  40 */       this.a.put(paramMinecraftKey.getKey(), definedStructure);
/*     */     } 
/*  42 */     return definedStructure;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public DefinedStructure b(@Nullable MinecraftServer paramMinecraftServer, MinecraftKey paramMinecraftKey) {
/*  47 */     String str = paramMinecraftKey.getKey();
/*  48 */     if (this.a.containsKey(str)) {
/*  49 */       return this.a.get(str);
/*     */     }
/*  51 */     if (paramMinecraftServer == null) {
/*  52 */       c(paramMinecraftKey);
/*     */     } else {
/*  54 */       a(paramMinecraftKey);
/*     */     } 
/*  56 */     if (this.a.containsKey(str)) {
/*  57 */       return this.a.get(str);
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(MinecraftKey paramMinecraftKey) {
/*  64 */     String str = paramMinecraftKey.getKey();
/*  65 */     File file = new File(this.b, str + ".nbt");
/*  66 */     if (!file.exists()) {
/*  67 */       return c(paramMinecraftKey);
/*     */     }
/*     */     
/*  70 */     FileInputStream fileInputStream = null;
/*     */     try {
/*  72 */       fileInputStream = new FileInputStream(file);
/*  73 */       a(str, fileInputStream);
/*  74 */     } catch (Throwable throwable) {
/*  75 */       return false;
/*     */     } finally {
/*  77 */       IOUtils.closeQuietly(fileInputStream);
/*     */     } 
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   private boolean c(MinecraftKey paramMinecraftKey) {
/*  83 */     String str1 = paramMinecraftKey.b();
/*  84 */     String str2 = paramMinecraftKey.getKey();
/*     */     
/*  86 */     InputStream inputStream = null;
/*     */     try {
/*  88 */       inputStream = MinecraftServer.class.getResourceAsStream("/assets/" + str1 + "/structures/" + str2 + ".nbt");
/*  89 */       a(str2, inputStream);
/*  90 */     } catch (Throwable throwable) {
/*  91 */       return false;
/*     */     } finally {
/*  93 */       IOUtils.closeQuietly(inputStream);
/*     */     } 
/*  95 */     return true;
/*     */   }
/*     */   
/*     */   private void a(String paramString, InputStream paramInputStream) throws IOException {
/*  99 */     NBTTagCompound nBTTagCompound = NBTCompressedStreamTools.a(paramInputStream);
/*     */ 
/*     */     
/* 102 */     if (!nBTTagCompound.hasKeyOfType("DataVersion", 99)) {
/* 103 */       nBTTagCompound.setInt("DataVersion", 500);
/*     */     }
/*     */     
/* 106 */     DefinedStructure definedStructure = new DefinedStructure();
/* 107 */     definedStructure.b(this.c.a(DataConverterTypes.STRUCTURE, nBTTagCompound));
/*     */     
/* 109 */     this.a.put(paramString, definedStructure);
/*     */   }
/*     */   
/*     */   public boolean c(@Nullable MinecraftServer paramMinecraftServer, MinecraftKey paramMinecraftKey) {
/* 113 */     String str = paramMinecraftKey.getKey();
/* 114 */     if (paramMinecraftServer == null || !this.a.containsKey(str)) {
/* 115 */       return false;
/*     */     }
/* 117 */     File file1 = new File(this.b);
/* 118 */     if (!file1.exists()) {
/* 119 */       if (!file1.mkdirs()) {
/* 120 */         return false;
/*     */       }
/* 122 */     } else if (!file1.isDirectory()) {
/* 123 */       return false;
/*     */     } 
/*     */     
/* 126 */     File file2 = new File(file1, str + ".nbt");
/* 127 */     DefinedStructure definedStructure = this.a.get(str);
/* 128 */     FileOutputStream fileOutputStream = null;
/*     */     try {
/* 130 */       NBTTagCompound nBTTagCompound = definedStructure.a(new NBTTagCompound());
/* 131 */       fileOutputStream = new FileOutputStream(file2);
/* 132 */       NBTCompressedStreamTools.a(nBTTagCompound, fileOutputStream);
/* 133 */     } catch (Throwable throwable) {
/* 134 */       return false;
/*     */     } finally {
/* 136 */       IOUtils.closeQuietly(fileOutputStream);
/*     */     } 
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   public void b(MinecraftKey paramMinecraftKey) {
/* 142 */     this.a.remove(paramMinecraftKey.getKey());
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DefinedStructureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */