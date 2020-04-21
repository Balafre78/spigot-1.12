/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class EULA
/*    */ {
/* 13 */   private static final Logger a = LogManager.getLogger();
/*    */   private final File b;
/*    */   private final boolean c;
/*    */   
/*    */   public EULA(File paramFile) {
/* 18 */     this.b = paramFile;
/* 19 */     this.c = a(paramFile);
/*    */   }
/*    */   
/*    */   private boolean a(File paramFile) {
/* 23 */     FileInputStream fileInputStream = null;
/* 24 */     boolean bool = false;
/*    */     try {
/* 26 */       Properties properties = new Properties();
/* 27 */       fileInputStream = new FileInputStream(paramFile);
/* 28 */       properties.load(fileInputStream);
/* 29 */       bool = Boolean.parseBoolean(properties.getProperty("eula", "false"));
/* 30 */     } catch (Exception exception) {
/* 31 */       a.warn("Failed to load {}", paramFile);
/* 32 */       b();
/*    */     } finally {
/* 34 */       IOUtils.closeQuietly(fileInputStream);
/*    */     } 
/* 36 */     return bool;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 40 */     return this.c;
/*    */   }
/*    */   
/*    */   public void b() {
/* 44 */     FileOutputStream fileOutputStream = null;
/*    */     try {
/* 46 */       Properties properties = new Properties();
/* 47 */       fileOutputStream = new FileOutputStream(this.b);
/* 48 */       properties.setProperty("eula", "false");
/* 49 */       properties.store(fileOutputStream, "By changing the setting below to TRUE you are indicating your agreement to our EULA (https://account.mojang.com/documents/minecraft_eula).");
/* 50 */     } catch (Exception exception) {
/* 51 */       a.warn("Failed to save {}", this.b, exception);
/*    */     } finally {
/* 53 */       IOUtils.closeQuietly(fileOutputStream);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EULA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */