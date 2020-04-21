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
/*    */ public class CommandPardon
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 18 */     return "pardon";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 23 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 28 */     return "commands.unban.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener) {
/* 33 */     return (paramMinecraftServer.getPlayerList().getProfileBans().isEnabled() && super.canUse(paramMinecraftServer, paramICommandListener));
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 38 */     if (paramArrayOfString.length != 1 || paramArrayOfString[0].length() <= 0) {
/* 39 */       throw new ExceptionUsage("commands.unban.usage", new Object[0]);
/*    */     }
/*    */     
/* 42 */     GameProfile gameProfile = paramMinecraftServer.getPlayerList().getProfileBans().a(paramArrayOfString[0]);
/* 43 */     if (gameProfile == null) {
/* 44 */       throw new CommandException("commands.unban.failed", new Object[] { paramArrayOfString[0] });
/*    */     }
/*    */     
/* 47 */     paramMinecraftServer.getPlayerList().getProfileBans().remove(gameProfile);
/* 48 */     a(paramICommandListener, this, "commands.unban.success", new Object[] { paramArrayOfString[0] });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 53 */     if (paramArrayOfString.length == 1) {
/* 54 */       return a(paramArrayOfString, paramMinecraftServer.getPlayerList().getProfileBans().getEntries());
/*    */     }
/*    */     
/* 57 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandPardon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */