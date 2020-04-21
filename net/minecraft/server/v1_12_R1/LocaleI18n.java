/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class LocaleI18n
/*    */ {
/*  7 */   private static final LocaleLanguage a = LocaleLanguage.a();
/*  8 */   private static final LocaleLanguage b = new LocaleLanguage();
/*    */   
/*    */   @Deprecated
/*    */   public static String get(String paramString) {
/* 12 */     return a.a(paramString);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public static String a(String paramString, Object... paramVarArgs) {
/* 17 */     return a.a(paramString, paramVarArgs);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public static String b(String paramString) {
/* 22 */     return b.a(paramString);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public static boolean c(String paramString) {
/* 32 */     return a.b(paramString);
/*    */   }
/*    */   
/*    */   public static long a() {
/* 36 */     return a.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LocaleI18n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */