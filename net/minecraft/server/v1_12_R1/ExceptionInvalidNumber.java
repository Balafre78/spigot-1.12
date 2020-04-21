/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ExceptionInvalidNumber extends CommandException {
/*    */   public ExceptionInvalidNumber() {
/*  5 */     this("commands.generic.num.invalid", new Object[0]);
/*    */   }
/*    */   
/*    */   public ExceptionInvalidNumber(String paramString, Object... paramVarArgs) {
/*  9 */     super(paramString, paramVarArgs);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 14 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ExceptionInvalidNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */