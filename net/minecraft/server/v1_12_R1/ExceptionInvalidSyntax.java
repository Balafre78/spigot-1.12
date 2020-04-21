/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ExceptionInvalidSyntax extends CommandException {
/*    */   public ExceptionInvalidSyntax() {
/*  5 */     this("commands.generic.snytax", new Object[0]);
/*    */   }
/*    */   
/*    */   public ExceptionInvalidSyntax(String paramString, Object... paramVarArgs) {
/*  9 */     super(paramString, paramVarArgs);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 14 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ExceptionInvalidSyntax.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */