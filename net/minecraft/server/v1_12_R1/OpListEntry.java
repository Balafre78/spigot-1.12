/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class OpListEntry
/*    */   extends JsonListEntry<GameProfile>
/*    */ {
/*    */   private final int a;
/*    */   private final boolean b;
/*    */   
/*    */   public OpListEntry(GameProfile paramGameProfile, int paramInt, boolean paramBoolean) {
/* 14 */     super(paramGameProfile);
/* 15 */     this.a = paramInt;
/* 16 */     this.b = paramBoolean;
/*    */   }
/*    */   
/*    */   public OpListEntry(JsonObject paramJsonObject) {
/* 20 */     super(b(paramJsonObject), paramJsonObject);
/* 21 */     this.a = paramJsonObject.has("level") ? paramJsonObject.get("level").getAsInt() : 0;
/* 22 */     this.b = (paramJsonObject.has("bypassesPlayerLimit") && paramJsonObject.get("bypassesPlayerLimit").getAsBoolean());
/*    */   }
/*    */   
/*    */   public int a() {
/* 26 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 30 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject paramJsonObject) {
/* 35 */     if (getKey() == null) {
/*    */       return;
/*    */     }
/* 38 */     paramJsonObject.addProperty("uuid", (getKey().getId() == null) ? "" : getKey().getId().toString());
/* 39 */     paramJsonObject.addProperty("name", getKey().getName());
/* 40 */     super.a(paramJsonObject);
/* 41 */     paramJsonObject.addProperty("level", Integer.valueOf(this.a));
/* 42 */     paramJsonObject.addProperty("bypassesPlayerLimit", Boolean.valueOf(this.b));
/*    */   }
/*    */   private static GameProfile b(JsonObject paramJsonObject) {
/*    */     UUID uUID;
/* 46 */     if (!paramJsonObject.has("uuid") || !paramJsonObject.has("name")) {
/* 47 */       return null;
/*    */     }
/* 49 */     String str = paramJsonObject.get("uuid").getAsString();
/*    */     
/*    */     try {
/* 52 */       uUID = UUID.fromString(str);
/* 53 */     } catch (Throwable throwable) {
/* 54 */       return null;
/*    */     } 
/* 56 */     return new GameProfile(uUID, paramJsonObject.get("name").getAsString());
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\OpListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */