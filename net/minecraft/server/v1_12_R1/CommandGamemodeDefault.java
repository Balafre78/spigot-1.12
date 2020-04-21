/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandGamemodeDefault
/*    */   extends CommandGamemode
/*    */ {
/*    */   public String getCommand() {
/* 14 */     return "defaultgamemode";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 19 */     return "commands.defaultgamemode.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 24 */     if (paramArrayOfString.length <= 0) {
/* 25 */       throw new ExceptionUsage("commands.defaultgamemode.usage", new Object[0]);
/*    */     }
/*    */     
/* 28 */     EnumGamemode enumGamemode = c(paramICommandListener, paramArrayOfString[0]);
/* 29 */     a(enumGamemode, paramMinecraftServer);
/*    */     
/* 31 */     a(paramICommandListener, this, "commands.defaultgamemode.success", new Object[] { new ChatMessage("gameMode." + enumGamemode.b(), new Object[0]) });
/*    */   }
/*    */   
/*    */   protected void a(EnumGamemode paramEnumGamemode, MinecraftServer paramMinecraftServer) {
/* 35 */     paramMinecraftServer.setGamemode(paramEnumGamemode);
/*    */     
/* 37 */     if (paramMinecraftServer.getForceGamemode())
/* 38 */       for (EntityPlayer entityPlayer : paramMinecraftServer.getPlayerList().v())
/* 39 */         entityPlayer.a(paramEnumGamemode);  
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandGamemodeDefault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */