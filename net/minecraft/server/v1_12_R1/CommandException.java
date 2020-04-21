/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class CommandException extends Exception {
/*    */   private final Object[] a;
/*    */   
/*    */   public CommandException(String paramString, Object... paramVarArgs) {
/*  7 */     super(paramString);
/*    */     
/*  9 */     this.a = paramVarArgs;
/*    */   }
/*    */   
/*    */   public Object[] getArgs() {
/* 13 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 18 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */