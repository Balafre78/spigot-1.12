/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandList
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 14 */     return "list";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 19 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 24 */     return "commands.players.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 29 */     int i = paramMinecraftServer.H();
/* 30 */     paramICommandListener.sendMessage(new ChatMessage("commands.players.list", new Object[] { Integer.valueOf(i), Integer.valueOf(paramMinecraftServer.I()) }));
/* 31 */     paramICommandListener.sendMessage(new ChatComponentText(paramMinecraftServer.getPlayerList().b((paramArrayOfString.length > 0 && "uuids".equalsIgnoreCase(paramArrayOfString[0])))));
/* 32 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, i);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */