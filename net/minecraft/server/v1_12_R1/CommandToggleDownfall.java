/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandToggleDownfall
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 12 */     return "toggledownfall";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 17 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 22 */     return "commands.downfall.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 27 */     a(paramMinecraftServer);
/* 28 */     a(paramICommandListener, this, "commands.downfall.success", new Object[0]);
/*    */   }
/*    */   
/*    */   protected void a(MinecraftServer paramMinecraftServer) {
/* 32 */     WorldData worldData = paramMinecraftServer.worldServer[0].getWorldData();
/*    */     
/* 34 */     worldData.setStorm(!worldData.hasStorm());
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandToggleDownfall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */