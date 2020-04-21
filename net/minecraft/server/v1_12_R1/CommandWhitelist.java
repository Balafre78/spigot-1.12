/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
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
/*    */ public class CommandWhitelist
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 20 */     return "whitelist";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 25 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 30 */     return "commands.whitelist.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 35 */     if (paramArrayOfString.length < 1) {
/* 36 */       throw new ExceptionUsage("commands.whitelist.usage", new Object[0]);
/*    */     }
/*    */     
/* 39 */     if ("on".equals(paramArrayOfString[0])) {
/* 40 */       paramMinecraftServer.getPlayerList().setHasWhitelist(true);
/* 41 */       a(paramICommandListener, this, "commands.whitelist.enabled", new Object[0]);
/* 42 */     } else if ("off".equals(paramArrayOfString[0])) {
/* 43 */       paramMinecraftServer.getPlayerList().setHasWhitelist(false);
/* 44 */       a(paramICommandListener, this, "commands.whitelist.disabled", new Object[0]);
/* 45 */     } else if ("list".equals(paramArrayOfString[0])) {
/* 46 */       paramICommandListener.sendMessage(new ChatMessage("commands.whitelist.list", new Object[] { Integer.valueOf((paramMinecraftServer.getPlayerList().getWhitelisted()).length), Integer.valueOf((paramMinecraftServer.getPlayerList().getSeenPlayers()).length) }));
/* 47 */       String[] arrayOfString = paramMinecraftServer.getPlayerList().getWhitelisted();
/* 48 */       paramICommandListener.sendMessage(new ChatComponentText(a((Object[])arrayOfString)));
/* 49 */     } else if ("add".equals(paramArrayOfString[0])) {
/* 50 */       if (paramArrayOfString.length < 2) {
/* 51 */         throw new ExceptionUsage("commands.whitelist.add.usage", new Object[0]);
/*    */       }
/*    */       
/* 54 */       GameProfile gameProfile = paramMinecraftServer.getUserCache().getProfile(paramArrayOfString[1]);
/* 55 */       if (gameProfile == null) {
/* 56 */         throw new CommandException("commands.whitelist.add.failed", new Object[] { paramArrayOfString[1] });
/*    */       }
/* 58 */       paramMinecraftServer.getPlayerList().addWhitelist(gameProfile);
/* 59 */       a(paramICommandListener, this, "commands.whitelist.add.success", new Object[] { paramArrayOfString[1] });
/* 60 */     } else if ("remove".equals(paramArrayOfString[0])) {
/* 61 */       if (paramArrayOfString.length < 2) {
/* 62 */         throw new ExceptionUsage("commands.whitelist.remove.usage", new Object[0]);
/*    */       }
/*    */       
/* 65 */       GameProfile gameProfile = paramMinecraftServer.getPlayerList().getWhitelist().a(paramArrayOfString[1]);
/* 66 */       if (gameProfile == null) {
/* 67 */         throw new CommandException("commands.whitelist.remove.failed", new Object[] { paramArrayOfString[1] });
/*    */       }
/* 69 */       paramMinecraftServer.getPlayerList().removeWhitelist(gameProfile);
/* 70 */       a(paramICommandListener, this, "commands.whitelist.remove.success", new Object[] { paramArrayOfString[1] });
/* 71 */     } else if ("reload".equals(paramArrayOfString[0])) {
/* 72 */       paramMinecraftServer.getPlayerList().reloadWhitelist();
/* 73 */       a(paramICommandListener, this, "commands.whitelist.reloaded", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 79 */     if (paramArrayOfString.length == 1) {
/* 80 */       return a(paramArrayOfString, new String[] { "on", "off", "list", "add", "remove", "reload" });
/*    */     }
/*    */     
/* 83 */     if (paramArrayOfString.length == 2) {
/* 84 */       if ("remove".equals(paramArrayOfString[0]))
/* 85 */         return a(paramArrayOfString, paramMinecraftServer.getPlayerList().getWhitelisted()); 
/* 86 */       if ("add".equals(paramArrayOfString[0])) {
/* 87 */         return a(paramArrayOfString, paramMinecraftServer.getUserCache().a());
/*    */       }
/*    */     } 
/*    */     
/* 91 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandWhitelist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */