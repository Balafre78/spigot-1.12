/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ExceptionEntityNotFound extends CommandException {
/*    */   public ExceptionEntityNotFound(String paramString) {
/*  5 */     this("commands.generic.entity.notFound", new Object[] { paramString });
/*    */   }
/*    */   
/*    */   public ExceptionEntityNotFound(String paramString, Object... paramVarArgs) {
/*  9 */     super(paramString, paramVarArgs);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 14 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ExceptionEntityNotFound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */