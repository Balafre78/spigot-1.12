/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandPardonIP
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "pardon-ip";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener) {
/* 29 */     return (paramMinecraftServer.getPlayerList().getIPBans().isEnabled() && super.canUse(paramMinecraftServer, paramICommandListener));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 34 */     return "commands.unbanip.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 39 */     if (paramArrayOfString.length != 1 || paramArrayOfString[0].length() <= 1) {
/* 40 */       throw new ExceptionUsage("commands.unbanip.usage", new Object[0]);
/*    */     }
/*    */     
/* 43 */     Matcher matcher = CommandBanIp.a.matcher(paramArrayOfString[0]);
/* 44 */     if (matcher.matches()) {
/* 45 */       paramMinecraftServer.getPlayerList().getIPBans().remove(paramArrayOfString[0]);
/* 46 */       a(paramICommandListener, this, "commands.unbanip.success", new Object[] { paramArrayOfString[0] });
/*    */     } else {
/* 48 */       throw new ExceptionInvalidSyntax("commands.unbanip.invalid", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 54 */     if (paramArrayOfString.length == 1) {
/* 55 */       return a(paramArrayOfString, paramMinecraftServer.getPlayerList().getIPBans().getEntries());
/*    */     }
/*    */     
/* 58 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandPardonIP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */