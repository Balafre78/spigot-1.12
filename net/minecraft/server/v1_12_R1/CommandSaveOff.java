/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSaveOff
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 12 */     return "save-off";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 17 */     return "commands.save-off.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 22 */     boolean bool = false;
/* 23 */     for (byte b = 0; b < paramMinecraftServer.worldServer.length; b++) {
/* 24 */       if (paramMinecraftServer.worldServer[b] != null) {
/* 25 */         WorldServer worldServer = paramMinecraftServer.worldServer[b];
/* 26 */         if (!worldServer.savingDisabled) {
/* 27 */           worldServer.savingDisabled = true;
/* 28 */           bool = true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 33 */     if (bool) {
/* 34 */       a(paramICommandListener, this, "commands.save.disabled", new Object[0]);
/*    */     } else {
/* 36 */       throw new CommandException("commands.save-off.alreadyOff", new Object[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSaveOff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */