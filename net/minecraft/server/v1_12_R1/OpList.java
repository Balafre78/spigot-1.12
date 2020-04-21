/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.File;
/*    */ 
/*    */ public class OpList
/*    */   extends JsonList<GameProfile, OpListEntry>
/*    */ {
/*    */   public OpList(File paramFile) {
/* 11 */     super(paramFile);
/*    */   }
/*    */ 
/*    */   
/*    */   protected JsonListEntry<GameProfile> a(JsonObject paramJsonObject) {
/* 16 */     return new OpListEntry(paramJsonObject);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getEntries() {
/* 21 */     String[] arrayOfString = new String[e().size()];
/* 22 */     byte b = 0;
/* 23 */     for (OpListEntry opListEntry : e().values()) {
/* 24 */       arrayOfString[b++] = opListEntry.getKey().getName();
/*    */     }
/* 26 */     return arrayOfString;
/*    */   }
/*    */   
/*    */   public int a(GameProfile paramGameProfile) {
/* 30 */     OpListEntry opListEntry = get(paramGameProfile);
/*    */     
/* 32 */     if (opListEntry != null) {
/* 33 */       return opListEntry.a();
/*    */     }
/*    */     
/* 36 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean b(GameProfile paramGameProfile) {
/* 40 */     OpListEntry opListEntry = get(paramGameProfile);
/*    */     
/* 42 */     if (opListEntry != null) {
/* 43 */       return opListEntry.b();
/*    */     }
/*    */     
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String c(GameProfile paramGameProfile) {
/* 51 */     return paramGameProfile.getId().toString();
/*    */   }
/*    */   
/*    */   public GameProfile a(String paramString) {
/* 55 */     for (OpListEntry opListEntry : e().values()) {
/* 56 */       if (paramString.equalsIgnoreCase(opListEntry.getKey().getName())) {
/* 57 */         return opListEntry.getKey();
/*    */       }
/*    */     } 
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\OpList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */