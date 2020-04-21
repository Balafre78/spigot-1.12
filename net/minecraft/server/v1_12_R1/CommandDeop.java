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
/*    */ public class CommandDeop
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 18 */     return "deop";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 23 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 28 */     return "commands.deop.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 33 */     if (paramArrayOfString.length != 1 || paramArrayOfString[0].length() <= 0) {
/* 34 */       throw new ExceptionUsage("commands.deop.usage", new Object[0]);
/*    */     }
/*    */     
/* 37 */     GameProfile gameProfile = paramMinecraftServer.getPlayerList().getOPs().a(paramArrayOfString[0]);
/* 38 */     if (gameProfile == null) {
/* 39 */       throw new CommandException("commands.deop.failed", new Object[] { paramArrayOfString[0] });
/*    */     }
/*    */     
/* 42 */     paramMinecraftServer.getPlayerList().removeOp(gameProfile);
/* 43 */     a(paramICommandListener, this, "commands.deop.success", new Object[] { paramArrayOfString[0] });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 48 */     if (paramArrayOfString.length == 1) {
/* 49 */       return a(paramArrayOfString, paramMinecraftServer.getPlayerList().n());
/*    */     }
/*    */     
/* 52 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandDeop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */