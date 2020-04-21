/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandPublish
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 12 */     return "publish";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 17 */     return "commands.publish.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 22 */     String str = paramMinecraftServer.a(EnumGamemode.SURVIVAL, false);
/* 23 */     if (str != null) {
/* 24 */       a(paramICommandListener, this, "commands.publish.started", new Object[] { str });
/*    */     } else {
/* 26 */       a(paramICommandListener, this, "commands.publish.failed", new Object[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandPublish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */