/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandStop
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 11 */     return "stop";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 16 */     return "commands.stop.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 21 */     if (paramMinecraftServer.worldServer != null) {
/* 22 */       a(paramICommandListener, this, "commands.stop.start", new Object[0]);
/*    */     }
/* 24 */     paramMinecraftServer.safeShutdown();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandStop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */