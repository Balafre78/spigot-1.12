/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.io.File;
/*    */ import java.net.SocketAddress;
/*    */ 
/*    */ public class IpBanList
/*    */   extends JsonList<String, IpBanEntry> {
/*    */   public IpBanList(File paramFile) {
/* 10 */     super(paramFile);
/*    */   }
/*    */ 
/*    */   
/*    */   protected JsonListEntry<String> a(JsonObject paramJsonObject) {
/* 15 */     return new IpBanEntry(paramJsonObject);
/*    */   }
/*    */   
/*    */   public boolean isBanned(SocketAddress paramSocketAddress) {
/* 19 */     String str = c(paramSocketAddress);
/* 20 */     return d(str);
/*    */   }
/*    */   
/*    */   public IpBanEntry get(SocketAddress paramSocketAddress) {
/* 24 */     String str = c(paramSocketAddress);
/* 25 */     return get(str);
/*    */   }
/*    */   
/*    */   private String c(SocketAddress paramSocketAddress) {
/* 29 */     String str = paramSocketAddress.toString();
/* 30 */     if (str.contains("/")) {
/* 31 */       str = str.substring(str.indexOf('/') + 1);
/*    */     }
/* 33 */     if (str.contains(":")) {
/* 34 */       str = str.substring(0, str.indexOf(':'));
/*    */     }
/* 36 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IpBanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */