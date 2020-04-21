/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.Date;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class GameProfileBanEntry
/*    */   extends ExpirableListEntry<GameProfile> {
/*    */   public GameProfileBanEntry(GameProfile gameprofile) {
/* 11 */     this(gameprofile, null, null, null, null);
/*    */   }
/*    */   
/*    */   public GameProfileBanEntry(GameProfile gameprofile, Date date, String s, Date date1, String s1) {
/* 15 */     super(gameprofile, date, s, date1, s1);
/*    */   }
/*    */   
/*    */   public GameProfileBanEntry(JsonObject jsonobject) {
/* 19 */     super(b(jsonobject), jsonobject);
/*    */   }
/*    */   
/*    */   protected void a(JsonObject jsonobject) {
/* 23 */     if (getKey() != null) {
/* 24 */       jsonobject.addProperty("uuid", (getKey().getId() == null) ? "" : getKey().getId().toString());
/* 25 */       jsonobject.addProperty("name", getKey().getName());
/* 26 */       super.a(jsonobject);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static GameProfile b(JsonObject jsonobject) {
/* 33 */     UUID uuid = null;
/* 34 */     String name = null;
/* 35 */     if (jsonobject.has("uuid")) {
/* 36 */       String s = jsonobject.get("uuid").getAsString();
/*    */       
/*    */       try {
/* 39 */         uuid = UUID.fromString(s);
/* 40 */       } catch (Throwable throwable) {}
/*    */     } 
/*    */ 
/*    */     
/* 44 */     if (jsonobject.has("name"))
/*    */     {
/* 46 */       name = jsonobject.get("name").getAsString();
/*    */     }
/* 48 */     if (uuid != null || name != null)
/*    */     {
/* 50 */       return new GameProfile(uuid, name);
/*    */     }
/* 52 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GameProfileBanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */