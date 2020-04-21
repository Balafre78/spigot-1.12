/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Arrays;
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
/*    */ 
/*    */ 
/*    */ public class CommandTell
/*    */   extends CommandAbstract
/*    */ {
/*    */   public List<String> getAliases() {
/* 22 */     return Arrays.asList(new String[] { "w", "msg" });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommand() {
/* 27 */     return "tell";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 32 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 37 */     return "commands.message.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 42 */     if (paramArrayOfString.length < 2) {
/* 43 */       throw new ExceptionUsage("commands.message.usage", new Object[0]);
/*    */     }
/*    */     
/* 46 */     EntityPlayer entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/* 47 */     if (entityPlayer == paramICommandListener) {
/* 48 */       throw new ExceptionPlayerNotFound("commands.message.sameTarget");
/*    */     }
/*    */     
/* 51 */     IChatBaseComponent iChatBaseComponent = b(paramICommandListener, paramArrayOfString, 1, !(paramICommandListener instanceof EntityHuman));
/* 52 */     ChatMessage chatMessage1 = new ChatMessage("commands.message.display.incoming", new Object[] { paramICommandListener.getScoreboardDisplayName(), iChatBaseComponent.f() });
/* 53 */     ChatMessage chatMessage2 = new ChatMessage("commands.message.display.outgoing", new Object[] { entityPlayer.getScoreboardDisplayName(), iChatBaseComponent.f() });
/* 54 */     chatMessage1.getChatModifier().setColor(EnumChatFormat.GRAY).setItalic(Boolean.valueOf(true));
/* 55 */     chatMessage2.getChatModifier().setColor(EnumChatFormat.GRAY).setItalic(Boolean.valueOf(true));
/* 56 */     entityPlayer.sendMessage(chatMessage1);
/* 57 */     paramICommandListener.sendMessage(chatMessage2);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 62 */     return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 67 */     return (paramInt == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */