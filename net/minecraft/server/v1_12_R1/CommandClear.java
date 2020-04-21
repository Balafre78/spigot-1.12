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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandClear
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 23 */     return "clear";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 28 */     return "commands.clear.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 33 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 38 */     EntityPlayer entityPlayer = (paramArrayOfString.length == 0) ? a(paramICommandListener) : b(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/* 39 */     Item item = (paramArrayOfString.length >= 2) ? a(paramICommandListener, paramArrayOfString[1]) : null;
/* 40 */     boolean bool1 = (paramArrayOfString.length >= 3) ? a(paramArrayOfString[2], -1) : true;
/* 41 */     boolean bool2 = (paramArrayOfString.length >= 4) ? a(paramArrayOfString[3], -1) : true;
/*    */     
/* 43 */     NBTTagCompound nBTTagCompound = null;
/* 44 */     if (paramArrayOfString.length >= 5) {
/*    */       try {
/* 46 */         nBTTagCompound = MojangsonParser.parse(a(paramArrayOfString, 4));
/* 47 */       } catch (MojangsonParseException mojangsonParseException) {
/* 48 */         throw new CommandException("commands.clear.tagError", new Object[] { mojangsonParseException.getMessage() });
/*    */       } 
/*    */     }
/*    */     
/* 52 */     if (paramArrayOfString.length >= 2 && item == null) {
/* 53 */       throw new CommandException("commands.clear.failure", new Object[] { entityPlayer.getName() });
/*    */     }
/*    */     
/* 56 */     int i = entityPlayer.inventory.a(item, bool1, bool2, nBTTagCompound);
/* 57 */     entityPlayer.defaultContainer.b();
/* 58 */     if (!entityPlayer.abilities.canInstantlyBuild) {
/* 59 */       entityPlayer.broadcastCarriedItem();
/*    */     }
/*    */     
/* 62 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, i);
/* 63 */     if (i == 0) {
/* 64 */       throw new CommandException("commands.clear.failure", new Object[] { entityPlayer.getName() });
/*    */     }
/*    */     
/* 67 */     if (!bool2) {
/* 68 */       paramICommandListener.sendMessage(new ChatMessage("commands.clear.testing", new Object[] { entityPlayer.getName(), Integer.valueOf(i) }));
/*    */     } else {
/* 70 */       a(paramICommandListener, this, "commands.clear.success", new Object[] { entityPlayer.getName(), Integer.valueOf(i) });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 76 */     if (paramArrayOfString.length == 1) {
/* 77 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/* 79 */     if (paramArrayOfString.length == 2) {
/* 80 */       return a(paramArrayOfString, Item.REGISTRY.keySet());
/*    */     }
/*    */     
/* 83 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 88 */     return (paramInt == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandClear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */