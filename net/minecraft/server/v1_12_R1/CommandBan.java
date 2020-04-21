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
/*    */ 
/*    */ public class CommandBan
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 21 */     return "ban";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 26 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 31 */     return "commands.ban.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener) {
/* 36 */     return (paramMinecraftServer.getPlayerList().getProfileBans().isEnabled() && super.canUse(paramMinecraftServer, paramICommandListener));
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 41 */     if (paramArrayOfString.length < 1 || paramArrayOfString[0].length() <= 0) {
/* 42 */       throw new ExceptionUsage("commands.ban.usage", new Object[0]);
/*    */     }
/*    */     
/* 45 */     GameProfile gameProfile = paramMinecraftServer.getUserCache().getProfile(paramArrayOfString[0]);
/* 46 */     if (gameProfile == null) {
/* 47 */       throw new CommandException("commands.ban.failed", new Object[] { paramArrayOfString[0] });
/*    */     }
/*    */     
/* 50 */     String str = null;
/* 51 */     if (paramArrayOfString.length >= 2) {
/* 52 */       str = a(paramICommandListener, paramArrayOfString, 1).toPlainText();
/*    */     }
/*    */     
/* 55 */     GameProfileBanEntry gameProfileBanEntry = new GameProfileBanEntry(gameProfile, null, paramICommandListener.getName(), null, str);
/* 56 */     paramMinecraftServer.getPlayerList().getProfileBans().add(gameProfileBanEntry);
/*    */     
/* 58 */     EntityPlayer entityPlayer = paramMinecraftServer.getPlayerList().getPlayer(paramArrayOfString[0]);
/* 59 */     if (entityPlayer != null) {
/* 60 */       entityPlayer.playerConnection.disconnect(new ChatMessage("multiplayer.disconnect.banned", new Object[0]));
/*    */     }
/*    */     
/* 63 */     a(paramICommandListener, this, "commands.ban.success", new Object[] { paramArrayOfString[0] });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 68 */     if (paramArrayOfString.length >= 1) {
/* 69 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/*    */     
/* 72 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandBan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */