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
/*    */ public class CommandFunction
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 23 */     return "function";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 28 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 33 */     return "commands.function.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 38 */     if (paramArrayOfString.length != 1 && paramArrayOfString.length != 3) {
/* 39 */       throw new ExceptionUsage("commands.function.usage", new Object[0]);
/*    */     }
/*    */     
/* 42 */     MinecraftKey minecraftKey = new MinecraftKey(paramArrayOfString[0]);
/* 43 */     CustomFunction customFunction = paramMinecraftServer.aL().a(minecraftKey);
/* 44 */     if (customFunction == null) {
/* 45 */       throw new CommandException("commands.function.unknown", new Object[] { minecraftKey });
/*    */     }
/*    */     
/* 48 */     if (paramArrayOfString.length == 3) {
/* 49 */       boolean bool1; String str = paramArrayOfString[1];
/*    */       
/* 51 */       if ("if".equals(str)) {
/* 52 */         bool1 = true;
/* 53 */       } else if ("unless".equals(str)) {
/* 54 */         bool1 = false;
/*    */       } else {
/* 56 */         throw new ExceptionUsage("commands.function.usage", new Object[0]);
/*    */       } 
/* 58 */       boolean bool2 = false;
/*    */       try {
/* 60 */         bool2 = !d(paramMinecraftServer, paramICommandListener, paramArrayOfString[2]).isEmpty() ? true : false;
/* 61 */       } catch (ExceptionEntityNotFound exceptionEntityNotFound) {}
/*    */       
/* 63 */       if (bool1 != bool2) {
/* 64 */         throw new CommandException("commands.function.skipped", new Object[] { minecraftKey });
/*    */       }
/*    */     } 
/* 67 */     int i = paramMinecraftServer.aL().a(customFunction, CommandListenerWrapper.a(paramICommandListener).i().a(2).a(false));
/*    */     
/* 69 */     a(paramICommandListener, this, "commands.function.success", new Object[] { minecraftKey, Integer.valueOf(i) });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 74 */     if (paramArrayOfString.length == 1) {
/* 75 */       return a(paramArrayOfString, paramMinecraftServer.aL().d().keySet());
/*    */     }
/* 77 */     if (paramArrayOfString.length == 2) {
/* 78 */       return a(paramArrayOfString, new String[] { "if", "unless" });
/*    */     }
/* 80 */     if (paramArrayOfString.length == 3) {
/* 81 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/* 83 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */