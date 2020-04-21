/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
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
/*    */ public class CommandBanIp
/*    */   extends CommandAbstract
/*    */ {
/* 22 */   public static final Pattern a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
/*    */ 
/*    */   
/*    */   public String getCommand() {
/* 26 */     return "ban-ip";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 31 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener) {
/* 36 */     return (paramMinecraftServer.getPlayerList().getIPBans().isEnabled() && super.canUse(paramMinecraftServer, paramICommandListener));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 41 */     return "commands.banip.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 46 */     if (paramArrayOfString.length < 1 || paramArrayOfString[0].length() <= 1) {
/* 47 */       throw new ExceptionUsage("commands.banip.usage", new Object[0]);
/*    */     }
/*    */     
/* 50 */     IChatBaseComponent iChatBaseComponent = (paramArrayOfString.length >= 2) ? a(paramICommandListener, paramArrayOfString, 1) : null;
/*    */     
/* 52 */     Matcher matcher = a.matcher(paramArrayOfString[0]);
/* 53 */     if (matcher.matches()) {
/* 54 */       a(paramMinecraftServer, paramICommandListener, paramArrayOfString[0], (iChatBaseComponent == null) ? null : iChatBaseComponent.toPlainText());
/*    */     } else {
/* 56 */       EntityPlayer entityPlayer = paramMinecraftServer.getPlayerList().getPlayer(paramArrayOfString[0]);
/* 57 */       if (entityPlayer == null) {
/* 58 */         throw new ExceptionPlayerNotFound("commands.banip.invalid");
/*    */       }
/*    */       
/* 61 */       a(paramMinecraftServer, paramICommandListener, entityPlayer.A(), (iChatBaseComponent == null) ? null : iChatBaseComponent.toPlainText());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 67 */     if (paramArrayOfString.length == 1) {
/* 68 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/*    */     
/* 71 */     return Collections.emptyList();
/*    */   }
/*    */   
/*    */   protected void a(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String paramString1, @Nullable String paramString2) {
/* 75 */     IpBanEntry ipBanEntry = new IpBanEntry(paramString1, null, paramICommandListener.getName(), null, paramString2);
/* 76 */     paramMinecraftServer.getPlayerList().getIPBans().add(ipBanEntry);
/*    */     
/* 78 */     List<EntityPlayer> list = paramMinecraftServer.getPlayerList().b(paramString1);
/* 79 */     String[] arrayOfString = new String[list.size()];
/* 80 */     byte b = 0;
/*    */     
/* 82 */     for (EntityPlayer entityPlayer : list) {
/* 83 */       entityPlayer.playerConnection.disconnect(new ChatMessage("multiplayer.disconnect.ip_banned", new Object[0]));
/* 84 */       arrayOfString[b++] = entityPlayer.getName();
/*    */     } 
/*    */     
/* 87 */     if (list.isEmpty()) {
/* 88 */       a(paramICommandListener, this, "commands.banip.success", new Object[] { paramString1 });
/*    */     } else {
/* 90 */       a(paramICommandListener, this, "commands.banip.success.players", new Object[] { paramString1, a((Object[])arrayOfString) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandBanIp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */