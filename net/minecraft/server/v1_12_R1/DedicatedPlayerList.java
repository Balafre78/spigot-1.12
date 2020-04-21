/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.io.IOException;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class DedicatedPlayerList
/*     */   extends PlayerList
/*     */ {
/*  11 */   private static final Logger f = LogManager.getLogger();
/*     */   
/*     */   public DedicatedPlayerList(DedicatedServer paramDedicatedServer) {
/*  14 */     super(paramDedicatedServer);
/*     */     
/*  16 */     a(paramDedicatedServer.a("view-distance", 10));
/*  17 */     this.maxPlayers = paramDedicatedServer.a("max-players", 20);
/*  18 */     setHasWhitelist(paramDedicatedServer.a("white-list", false));
/*     */     
/*  20 */     if (!paramDedicatedServer.R()) {
/*  21 */       getProfileBans().a(true);
/*  22 */       getIPBans().a(true);
/*     */     } 
/*     */     
/*  25 */     A();
/*  26 */     y();
/*  27 */     z();
/*  28 */     x();
/*  29 */     B();
/*  30 */     D();
/*  31 */     C();
/*  32 */     if (!getWhitelist().c().exists()) {
/*  33 */       E();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasWhitelist(boolean paramBoolean) {
/*  39 */     super.setHasWhitelist(paramBoolean);
/*  40 */     getServer().a("white-list", Boolean.valueOf(paramBoolean));
/*  41 */     getServer().a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addOp(GameProfile paramGameProfile) {
/*  46 */     super.addOp(paramGameProfile);
/*  47 */     C();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeOp(GameProfile paramGameProfile) {
/*  52 */     super.removeOp(paramGameProfile);
/*  53 */     C();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeWhitelist(GameProfile paramGameProfile) {
/*  58 */     super.removeWhitelist(paramGameProfile);
/*  59 */     E();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addWhitelist(GameProfile paramGameProfile) {
/*  64 */     super.addWhitelist(paramGameProfile);
/*  65 */     E();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reloadWhitelist() {
/*  70 */     D();
/*     */   }
/*     */   
/*     */   private void x() {
/*     */     try {
/*  75 */       getIPBans().save();
/*  76 */     } catch (IOException iOException) {
/*  77 */       f.warn("Failed to save ip banlist: ", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void y() {
/*     */     try {
/*  83 */       getProfileBans().save();
/*  84 */     } catch (IOException iOException) {
/*  85 */       f.warn("Failed to save user banlist: ", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void z() {
/*     */     try {
/*  91 */       getIPBans().load();
/*  92 */     } catch (IOException iOException) {
/*  93 */       f.warn("Failed to load ip banlist: ", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void A() {
/*     */     try {
/*  99 */       getProfileBans().load();
/* 100 */     } catch (IOException iOException) {
/* 101 */       f.warn("Failed to load user banlist: ", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void B() {
/*     */     try {
/* 107 */       getOPs().load();
/* 108 */     } catch (Exception exception) {
/* 109 */       f.warn("Failed to load operators list: ", exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void C() {
/*     */     try {
/* 115 */       getOPs().save();
/* 116 */     } catch (Exception exception) {
/* 117 */       f.warn("Failed to save operators list: ", exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void D() {
/*     */     try {
/* 123 */       getWhitelist().load();
/* 124 */     } catch (Exception exception) {
/* 125 */       f.warn("Failed to load white-list: ", exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void E() {
/*     */     try {
/* 131 */       getWhitelist().save();
/* 132 */     } catch (Exception exception) {
/* 133 */       f.warn("Failed to save white-list: ", exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWhitelisted(GameProfile paramGameProfile) {
/* 139 */     return (!getHasWhitelist() || isOp(paramGameProfile) || getWhitelist().isWhitelisted(paramGameProfile));
/*     */   }
/*     */ 
/*     */   
/*     */   public DedicatedServer getServer() {
/* 144 */     return (DedicatedServer)super.getServer();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f(GameProfile paramGameProfile) {
/* 149 */     return getOPs().b(paramGameProfile);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DedicatedPlayerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */