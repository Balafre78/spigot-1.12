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
/*    */ public class CommandSetWorldSpawn
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 18 */     return "setworldspawn";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 23 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 28 */     return "commands.setworldspawn.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*    */     BlockPosition blockPosition;
/* 34 */     if (paramArrayOfString.length == 0) {
/* 35 */       blockPosition = a(paramICommandListener).getChunkCoordinates();
/* 36 */     } else if (paramArrayOfString.length == 3 && paramICommandListener.getWorld() != null) {
/* 37 */       blockPosition = a(paramICommandListener, paramArrayOfString, 0, true);
/*    */     } else {
/* 39 */       throw new ExceptionUsage("commands.setworldspawn.usage", new Object[0]);
/*    */     } 
/*    */     
/* 42 */     paramICommandListener.getWorld().A(blockPosition);
/* 43 */     paramMinecraftServer.getPlayerList().sendAll(new PacketPlayOutSpawnPosition(blockPosition));
/* 44 */     a(paramICommandListener, this, "commands.setworldspawn.success", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 49 */     if (paramArrayOfString.length > 0 && paramArrayOfString.length <= 3) {
/* 50 */       return a(paramArrayOfString, 0, paramBlockPosition);
/*    */     }
/* 52 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSetWorldSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */