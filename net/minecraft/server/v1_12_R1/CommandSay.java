/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Collections;
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
/*    */ public class CommandSay
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "say";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 29 */     return "commands.say.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 34 */     if (paramArrayOfString.length <= 0 || paramArrayOfString[0].length() <= 0) {
/* 35 */       throw new ExceptionUsage("commands.say.usage", new Object[0]);
/*    */     }
/*    */     
/* 38 */     IChatBaseComponent iChatBaseComponent = b(paramICommandListener, paramArrayOfString, 0, true);
/* 39 */     paramMinecraftServer.getPlayerList().sendMessage(new ChatMessage("chat.type.announcement", new Object[] { paramICommandListener.getScoreboardDisplayName(), iChatBaseComponent }));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 44 */     if (paramArrayOfString.length >= 1) {
/* 45 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/*    */     
/* 48 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */