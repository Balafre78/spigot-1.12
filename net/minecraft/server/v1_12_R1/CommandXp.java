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
/*    */ public class CommandXp
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "xp";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 29 */     return "commands.xp.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 34 */     if (paramArrayOfString.length <= 0) {
/* 35 */       throw new ExceptionUsage("commands.xp.usage", new Object[0]);
/*    */     }
/*    */     
/* 38 */     String str = paramArrayOfString[0];
/* 39 */     boolean bool1 = (str.endsWith("l") || str.endsWith("L")) ? true : false;
/* 40 */     if (bool1 && str.length() > 1) {
/* 41 */       str = str.substring(0, str.length() - 1);
/*    */     }
/* 43 */     int i = a(str);
/*    */     
/* 45 */     boolean bool2 = (i < 0) ? true : false;
/* 46 */     if (bool2) {
/* 47 */       i *= -1;
/*    */     }
/*    */     
/* 50 */     EntityPlayer entityPlayer = (paramArrayOfString.length > 1) ? b(paramMinecraftServer, paramICommandListener, paramArrayOfString[1]) : a(paramICommandListener);
/* 51 */     if (bool1) {
/* 52 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, entityPlayer.expLevel);
/* 53 */       if (bool2) {
/* 54 */         entityPlayer.levelDown(-i);
/* 55 */         a(paramICommandListener, this, "commands.xp.success.negative.levels", new Object[] { Integer.valueOf(i), entityPlayer.getName() });
/*    */       } else {
/* 57 */         entityPlayer.levelDown(i);
/* 58 */         a(paramICommandListener, this, "commands.xp.success.levels", new Object[] { Integer.valueOf(i), entityPlayer.getName() });
/*    */       } 
/*    */     } else {
/* 61 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, entityPlayer.expTotal);
/* 62 */       if (bool2) {
/* 63 */         throw new CommandException("commands.xp.failure.widthdrawXp", new Object[0]);
/*    */       }
/* 65 */       entityPlayer.giveExp(i);
/* 66 */       a(paramICommandListener, this, "commands.xp.success", new Object[] { Integer.valueOf(i), entityPlayer.getName() });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 73 */     if (paramArrayOfString.length == 2) {
/* 74 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/*    */     
/* 77 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 82 */     return (paramInt == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */