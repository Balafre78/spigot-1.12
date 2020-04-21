/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandMe
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "me";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 29 */     return "commands.me.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 34 */     if (paramArrayOfString.length <= 0) {
/* 35 */       throw new ExceptionUsage("commands.me.usage", new Object[0]);
/*    */     }
/*    */     
/* 38 */     IChatBaseComponent iChatBaseComponent = b(paramICommandListener, paramArrayOfString, 0, !(paramICommandListener instanceof EntityHuman));
/* 39 */     paramMinecraftServer.getPlayerList().sendMessage(new ChatMessage("chat.type.emote", new Object[] { paramICommandListener.getScoreboardDisplayName(), iChatBaseComponent }));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 44 */     return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandMe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */