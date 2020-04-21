/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.UUID;
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
/*    */ public class CommandEntityData
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "entitydata";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 29 */     return "commands.entitydata.usage";
/*    */   }
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*    */     NBTTagCompound nBTTagCompound3;
/* 34 */     if (paramArrayOfString.length < 2) {
/* 35 */       throw new ExceptionUsage("commands.entitydata.usage", new Object[0]);
/*    */     }
/*    */     
/* 38 */     Entity entity = c(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/* 39 */     if (entity instanceof EntityHuman) {
/* 40 */       throw new CommandException("commands.entitydata.noPlayers", new Object[] { entity.getScoreboardDisplayName() });
/*    */     }
/*    */     
/* 43 */     NBTTagCompound nBTTagCompound1 = a(entity);
/* 44 */     NBTTagCompound nBTTagCompound2 = nBTTagCompound1.g();
/*    */ 
/*    */     
/*    */     try {
/* 48 */       nBTTagCompound3 = MojangsonParser.parse(a(paramArrayOfString, 1));
/* 49 */     } catch (MojangsonParseException mojangsonParseException) {
/* 50 */       throw new CommandException("commands.entitydata.tagError", new Object[] { mojangsonParseException.getMessage() });
/*    */     } 
/*    */     
/* 53 */     UUID uUID = entity.getUniqueID();
/* 54 */     nBTTagCompound1.a(nBTTagCompound3);
/* 55 */     entity.a(uUID);
/*    */     
/* 57 */     if (nBTTagCompound1.equals(nBTTagCompound2)) {
/* 58 */       throw new CommandException("commands.entitydata.failed", new Object[] { nBTTagCompound1.toString() });
/*    */     }
/*    */     
/* 61 */     entity.f(nBTTagCompound1);
/*    */     
/* 63 */     a(paramICommandListener, this, "commands.entitydata.success", new Object[] { nBTTagCompound1.toString() });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 68 */     return (paramInt == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandEntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */