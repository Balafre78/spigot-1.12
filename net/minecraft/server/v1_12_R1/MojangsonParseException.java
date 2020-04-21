/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class MojangsonParseException
/*    */   extends Exception
/*    */ {
/*    */   public MojangsonParseException(String paramString1, String paramString2, int paramInt) {
/*  7 */     super(paramString1 + " at: " + a(paramString2, paramInt));
/*    */   }
/*    */   
/*    */   private static String a(String paramString, int paramInt) {
/* 11 */     StringBuilder stringBuilder = new StringBuilder();
/* 12 */     int i = Math.min(paramString.length(), paramInt);
/*    */     
/* 14 */     if (i > 35) {
/* 15 */       stringBuilder.append("...");
/*    */     }
/*    */     
/* 18 */     stringBuilder.append(paramString.substring(Math.max(0, i - 35), i));
/* 19 */     stringBuilder.append("<--[HERE]");
/*    */     
/* 21 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MojangsonParseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */