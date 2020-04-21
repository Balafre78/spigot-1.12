/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ExceptionUsage extends ExceptionInvalidSyntax {
/*    */   public ExceptionUsage(String paramString, Object... paramVarArgs) {
/*  5 */     super(paramString, paramVarArgs);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 10 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ExceptionUsage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */