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
/*    */ 
/*    */ 
/*    */ public class CommandKick
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 21 */     return "kick";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 26 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 31 */     return "commands.kick.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 36 */     if (paramArrayOfString.length <= 0 || paramArrayOfString[0].length() <= 1) {
/* 37 */       throw new ExceptionUsage("commands.kick.usage", new Object[0]);
/*    */     }
/*    */     
/* 40 */     EntityPlayer entityPlayer = paramMinecraftServer.getPlayerList().getPlayer(paramArrayOfString[0]);
/* 41 */     if (entityPlayer == null) {
/* 42 */       throw new ExceptionPlayerNotFound("commands.generic.player.notFound", new Object[] { paramArrayOfString[0] });
/*    */     }
/*    */     
/* 45 */     if (paramArrayOfString.length >= 2) {
/* 46 */       IChatBaseComponent iChatBaseComponent = a(paramICommandListener, paramArrayOfString, 1);
/* 47 */       entityPlayer.playerConnection.disconnect(iChatBaseComponent);
/* 48 */       a(paramICommandListener, this, "commands.kick.success.reason", new Object[] { entityPlayer.getName(), iChatBaseComponent.toPlainText() });
/*    */     } else {
/* 50 */       entityPlayer.playerConnection.disconnect(new ChatMessage("multiplayer.disconnect.kicked", new Object[0]));
/* 51 */       a(paramICommandListener, this, "commands.kick.success", new Object[] { entityPlayer.getName() });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 57 */     if (paramArrayOfString.length >= 1) {
/* 58 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/*    */     
/* 61 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandKick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */