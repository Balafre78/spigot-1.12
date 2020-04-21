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
/*    */ public class CommandSpawnpoint
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 18 */     return "spawnpoint";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 23 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 28 */     return "commands.spawnpoint.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 33 */     if (paramArrayOfString.length > 1 && paramArrayOfString.length < 4) {
/* 34 */       throw new ExceptionUsage("commands.spawnpoint.usage", new Object[0]);
/*    */     }
/*    */     
/* 37 */     EntityPlayer entityPlayer = (paramArrayOfString.length > 0) ? b(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]) : a(paramICommandListener);
/* 38 */     BlockPosition blockPosition = (paramArrayOfString.length > 3) ? a(paramICommandListener, paramArrayOfString, 1, true) : entityPlayer.getChunkCoordinates();
/* 39 */     if (entityPlayer.world != null) {
/* 40 */       entityPlayer.setRespawnPosition(blockPosition, true);
/* 41 */       a(paramICommandListener, this, "commands.spawnpoint.success", new Object[] { entityPlayer.getName(), Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 47 */     if (paramArrayOfString.length == 1)
/* 48 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/* 49 */     if (paramArrayOfString.length > 1 && paramArrayOfString.length <= 4) {
/* 50 */       return a(paramArrayOfString, 1, paramBlockPosition);
/*    */     }
/*    */     
/* 53 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 58 */     return (paramInt == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSpawnpoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */