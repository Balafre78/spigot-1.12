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
/*    */ public class CommandSaveAll
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "save-all";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 24 */     return "commands.save.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 29 */     paramICommandListener.sendMessage(new ChatMessage("commands.save.start", new Object[0]));
/*    */     
/* 31 */     if (paramMinecraftServer.getPlayerList() != null) {
/* 32 */       paramMinecraftServer.getPlayerList().savePlayers();
/*    */     }
/*    */     try {
/*    */       byte b;
/* 36 */       for (b = 0; b < paramMinecraftServer.worldServer.length; b++) {
/* 37 */         if (paramMinecraftServer.worldServer[b] != null) {
/* 38 */           WorldServer worldServer = paramMinecraftServer.worldServer[b];
/* 39 */           boolean bool = worldServer.savingDisabled;
/* 40 */           worldServer.savingDisabled = false;
/* 41 */           worldServer.save(true, null);
/* 42 */           worldServer.savingDisabled = bool;
/*    */         } 
/*    */       } 
/* 45 */       if (paramArrayOfString.length > 0 && "flush".equals(paramArrayOfString[0])) {
/* 46 */         paramICommandListener.sendMessage(new ChatMessage("commands.save.flushStart", new Object[0]));
/* 47 */         for (b = 0; b < paramMinecraftServer.worldServer.length; b++) {
/* 48 */           if (paramMinecraftServer.worldServer[b] != null) {
/* 49 */             WorldServer worldServer = paramMinecraftServer.worldServer[b];
/* 50 */             boolean bool = worldServer.savingDisabled;
/* 51 */             worldServer.savingDisabled = false;
/* 52 */             worldServer.flushSave();
/* 53 */             worldServer.savingDisabled = bool;
/*    */           } 
/*    */         } 
/* 56 */         paramICommandListener.sendMessage(new ChatMessage("commands.save.flushEnd", new Object[0]));
/*    */       } 
/* 58 */     } catch (ExceptionWorldConflict exceptionWorldConflict) {
/* 59 */       a(paramICommandListener, this, "commands.save.failed", new Object[] { exceptionWorldConflict.getMessage() });
/*    */       
/*    */       return;
/*    */     } 
/* 63 */     a(paramICommandListener, this, "commands.save.success", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 68 */     if (paramArrayOfString.length == 1) {
/* 69 */       return a(paramArrayOfString, new String[] { "flush" });
/*    */     }
/* 71 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSaveAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */