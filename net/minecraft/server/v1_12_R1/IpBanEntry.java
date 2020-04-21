/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class IpBanEntry
/*    */   extends ExpirableListEntry<String> {
/*    */   public IpBanEntry(String paramString) {
/*  9 */     this(paramString, null, null, null, null);
/*    */   }
/*    */   
/*    */   public IpBanEntry(String paramString1, Date paramDate1, String paramString2, Date paramDate2, String paramString3) {
/* 13 */     super(paramString1, paramDate1, paramString2, paramDate2, paramString3);
/*    */   }
/*    */   
/*    */   public IpBanEntry(JsonObject paramJsonObject) {
/* 17 */     super(b(paramJsonObject), paramJsonObject);
/*    */   }
/*    */   
/*    */   private static String b(JsonObject paramJsonObject) {
/* 21 */     return paramJsonObject.has("ip") ? paramJsonObject.get("ip").getAsString() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject paramJsonObject) {
/* 26 */     if (getKey() == null) {
/*    */       return;
/*    */     }
/* 29 */     paramJsonObject.addProperty("ip", getKey());
/* 30 */     super.a(paramJsonObject);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IpBanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */