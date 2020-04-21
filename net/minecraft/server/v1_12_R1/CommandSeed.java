/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSeed
/*    */   extends CommandAbstract
/*    */ {
/*    */   public boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener) {
/* 14 */     return (paramMinecraftServer.R() || super.canUse(paramMinecraftServer, paramICommandListener));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommand() {
/* 19 */     return "seed";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 29 */     return "commands.seed.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 34 */     World world = (paramICommandListener instanceof EntityHuman) ? ((EntityHuman)paramICommandListener).world : paramMinecraftServer.getWorldServer(0);
/* 35 */     paramICommandListener.sendMessage(new ChatMessage("commands.seed.success", new Object[] { Long.valueOf(world.getSeed()) }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */