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
/*    */ public class CommandLocate
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "locate";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 29 */     return "commands.locate.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 34 */     if (paramArrayOfString.length != 1) {
/* 35 */       throw new ExceptionUsage("commands.locate.usage", new Object[0]);
/*    */     }
/*    */     
/* 38 */     String str = paramArrayOfString[0];
/* 39 */     BlockPosition blockPosition = paramICommandListener.getWorld().a(str, paramICommandListener.getChunkCoordinates(), false);
/* 40 */     if (blockPosition != null) {
/* 41 */       paramICommandListener.sendMessage(new ChatMessage("commands.locate.success", new Object[] { str, Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getZ()) }));
/*    */     } else {
/* 43 */       throw new CommandException("commands.locate.failure", new Object[] { str });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 49 */     if (paramArrayOfString.length == 1) {
/* 50 */       return a(paramArrayOfString, new String[] { "Stronghold", "Monument", "Village", "Mansion", "EndCity", "Fortress", "Temple", "Mineshaft" });
/*    */     }
/*    */     
/* 53 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandLocate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */