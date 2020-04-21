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
/*    */ public class CommandKill
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 18 */     return "kill";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 23 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 28 */     return "commands.kill.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 33 */     if (paramArrayOfString.length == 0) {
/* 34 */       EntityPlayer entityPlayer = a(paramICommandListener);
/* 35 */       entityPlayer.killEntity();
/* 36 */       a(paramICommandListener, this, "commands.kill.successful", new Object[] { entityPlayer.getScoreboardDisplayName() });
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     Entity entity = c(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/* 41 */     entity.killEntity();
/* 42 */     a(paramICommandListener, this, "commands.kill.successful", new Object[] { entity.getScoreboardDisplayName() });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 47 */     return (paramInt == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 52 */     if (paramArrayOfString.length == 1) {
/* 53 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/*    */     
/* 56 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandKill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */