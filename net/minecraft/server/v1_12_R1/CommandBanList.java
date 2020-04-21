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
/*    */ public class CommandBanList
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 18 */     return "banlist";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 23 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener) {
/* 28 */     return ((paramMinecraftServer.getPlayerList().getIPBans().isEnabled() || paramMinecraftServer.getPlayerList().getProfileBans().isEnabled()) && super.canUse(paramMinecraftServer, paramICommandListener));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 33 */     return "commands.banlist.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 38 */     if (paramArrayOfString.length >= 1 && "ips".equalsIgnoreCase(paramArrayOfString[0])) {
/* 39 */       paramICommandListener.sendMessage(new ChatMessage("commands.banlist.ips", new Object[] { Integer.valueOf((paramMinecraftServer.getPlayerList().getIPBans().getEntries()).length) }));
/* 40 */       paramICommandListener.sendMessage(new ChatComponentText(a((Object[])paramMinecraftServer.getPlayerList().getIPBans().getEntries())));
/*    */     } else {
/* 42 */       paramICommandListener.sendMessage(new ChatMessage("commands.banlist.players", new Object[] { Integer.valueOf((paramMinecraftServer.getPlayerList().getProfileBans().getEntries()).length) }));
/* 43 */       paramICommandListener.sendMessage(new ChatComponentText(a((Object[])paramMinecraftServer.getPlayerList().getProfileBans().getEntries())));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 49 */     if (paramArrayOfString.length == 1) {
/* 50 */       return a(paramArrayOfString, new String[] { "players", "ips" });
/*    */     }
/*    */     
/* 53 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandBanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */