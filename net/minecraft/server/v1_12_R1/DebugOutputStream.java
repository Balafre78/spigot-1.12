/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class DebugOutputStream extends RedirectStream {
/*    */   public DebugOutputStream(String paramString, OutputStream paramOutputStream) {
/*  7 */     super(paramString, paramOutputStream);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(String paramString) {
/* 12 */     StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
/* 13 */     StackTraceElement stackTraceElement = arrayOfStackTraceElement[Math.min(3, arrayOfStackTraceElement.length)];
/* 14 */     a.info("[{}]@.({}:{}): {}", this.b, stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()), paramString);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DebugOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */