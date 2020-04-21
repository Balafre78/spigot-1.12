/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandReload
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 12 */     return "reload";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 17 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 22 */     return "commands.reload.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 27 */     if (paramArrayOfString.length > 0) {
/* 28 */       throw new ExceptionUsage("commands.reload.usage", new Object[0]);
/*    */     }
/*    */     
/* 31 */     paramMinecraftServer.reload();
/* 32 */     a(paramICommandListener, this, "commands.reload.success", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandReload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */