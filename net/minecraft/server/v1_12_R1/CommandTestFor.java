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
/*    */ public class CommandTestFor
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 22 */     return "testfor";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 27 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 32 */     return "commands.testfor.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 37 */     if (paramArrayOfString.length < 1) {
/* 38 */       throw new ExceptionUsage("commands.testfor.usage", new Object[0]);
/*    */     }
/*    */     
/* 41 */     Entity entity = c(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/* 42 */     NBTTagCompound nBTTagCompound = null;
/* 43 */     if (paramArrayOfString.length >= 2) {
/*    */       try {
/* 45 */         nBTTagCompound = MojangsonParser.parse(a(paramArrayOfString, 1));
/* 46 */       } catch (MojangsonParseException mojangsonParseException) {
/* 47 */         throw new CommandException("commands.testfor.tagError", new Object[] { mojangsonParseException.getMessage() });
/*    */       } 
/*    */     }
/*    */     
/* 51 */     if (nBTTagCompound != null) {
/* 52 */       NBTTagCompound nBTTagCompound1 = a(entity);
/* 53 */       if (!GameProfileSerializer.a(nBTTagCompound, nBTTagCompound1, true)) {
/* 54 */         throw new CommandException("commands.testfor.failure", new Object[] { entity.getName() });
/*    */       }
/*    */     } 
/*    */     
/* 58 */     a(paramICommandListener, this, "commands.testfor.success", new Object[] { entity.getName() });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 63 */     return (paramInt == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 68 */     if (paramArrayOfString.length == 1) {
/* 69 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/* 71 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTestFor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */