/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandIdleTimeout
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 12 */     return "setidletimeout";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 17 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 22 */     return "commands.setidletimeout.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 27 */     if (paramArrayOfString.length != 1) {
/* 28 */       throw new ExceptionUsage("commands.setidletimeout.usage", new Object[0]);
/*    */     }
/*    */     
/* 31 */     int i = a(paramArrayOfString[0], 0);
/* 32 */     paramMinecraftServer.setIdleTimeout(i);
/* 33 */     a(paramICommandListener, this, "commands.setidletimeout.success", new Object[] { Integer.valueOf(i) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandIdleTimeout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */