/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.File;
/*    */ 
/*    */ public class GameProfileBanList
/*    */   extends JsonList<GameProfile, GameProfileBanEntry> {
/*    */   public GameProfileBanList(File paramFile) {
/* 10 */     super(paramFile);
/*    */   }
/*    */ 
/*    */   
/*    */   protected JsonListEntry<GameProfile> a(JsonObject paramJsonObject) {
/* 15 */     return new GameProfileBanEntry(paramJsonObject);
/*    */   }
/*    */   
/*    */   public boolean isBanned(GameProfile paramGameProfile) {
/* 19 */     return d(paramGameProfile);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getEntries() {
/* 24 */     String[] arrayOfString = new String[e().size()];
/* 25 */     byte b = 0;
/* 26 */     for (GameProfileBanEntry gameProfileBanEntry : e().values()) {
/* 27 */       arrayOfString[b++] = gameProfileBanEntry.getKey().getName();
/*    */     }
/* 29 */     return arrayOfString;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String b(GameProfile paramGameProfile) {
/* 34 */     return paramGameProfile.getId().toString();
/*    */   }
/*    */   
/*    */   public GameProfile a(String paramString) {
/* 38 */     for (GameProfileBanEntry gameProfileBanEntry : e().values()) {
/* 39 */       if (paramString.equalsIgnoreCase(gameProfileBanEntry.getKey().getName())) {
/* 40 */         return gameProfileBanEntry.getKey();
/*    */       }
/*    */     } 
/* 43 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GameProfileBanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */