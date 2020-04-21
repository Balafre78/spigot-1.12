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
/*    */ public class CommandTime
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 20 */     return "time";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 25 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 30 */     return "commands.time.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 35 */     if (paramArrayOfString.length > 1) {
/* 36 */       if ("set".equals(paramArrayOfString[0])) {
/*    */         int i;
/* 38 */         if ("day".equals(paramArrayOfString[1])) {
/* 39 */           i = 1000;
/* 40 */         } else if ("night".equals(paramArrayOfString[1])) {
/* 41 */           i = 13000;
/*    */         } else {
/* 43 */           i = a(paramArrayOfString[1], 0);
/*    */         } 
/* 45 */         a(paramMinecraftServer, i);
/* 46 */         a(paramICommandListener, this, "commands.time.set", new Object[] { Integer.valueOf(i) }); return;
/*    */       } 
/* 48 */       if ("add".equals(paramArrayOfString[0])) {
/* 49 */         int i = a(paramArrayOfString[1], 0);
/* 50 */         b(paramMinecraftServer, i);
/* 51 */         a(paramICommandListener, this, "commands.time.added", new Object[] { Integer.valueOf(i) }); return;
/*    */       } 
/* 53 */       if ("query".equals(paramArrayOfString[0])) {
/* 54 */         if ("daytime".equals(paramArrayOfString[1])) {
/* 55 */           int i = (int)(paramICommandListener.getWorld().getDayTime() % 24000L);
/* 56 */           paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, i);
/* 57 */           a(paramICommandListener, this, "commands.time.query", new Object[] { Integer.valueOf(i) }); return;
/*    */         } 
/* 59 */         if ("day".equals(paramArrayOfString[1])) {
/* 60 */           int i = (int)(paramICommandListener.getWorld().getDayTime() / 24000L % 2147483647L);
/* 61 */           paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, i);
/* 62 */           a(paramICommandListener, this, "commands.time.query", new Object[] { Integer.valueOf(i) }); return;
/*    */         } 
/* 64 */         if ("gametime".equals(paramArrayOfString[1])) {
/* 65 */           int i = (int)(paramICommandListener.getWorld().getTime() % 2147483647L);
/* 66 */           paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, i);
/* 67 */           a(paramICommandListener, this, "commands.time.query", new Object[] { Integer.valueOf(i) });
/*    */           
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/* 73 */     throw new ExceptionUsage("commands.time.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 78 */     if (paramArrayOfString.length == 1)
/* 79 */       return a(paramArrayOfString, new String[] { "set", "add", "query" }); 
/* 80 */     if (paramArrayOfString.length == 2 && "set".equals(paramArrayOfString[0]))
/* 81 */       return a(paramArrayOfString, new String[] { "day", "night" }); 
/* 82 */     if (paramArrayOfString.length == 2 && "query".equals(paramArrayOfString[0])) {
/* 83 */       return a(paramArrayOfString, new String[] { "daytime", "gametime", "day" });
/*    */     }
/* 85 */     return Collections.emptyList();
/*    */   }
/*    */   
/*    */   protected void a(MinecraftServer paramMinecraftServer, int paramInt) {
/* 89 */     for (byte b = 0; b < paramMinecraftServer.worldServer.length; b++) {
/* 90 */       paramMinecraftServer.worldServer[b].setDayTime(paramInt);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void b(MinecraftServer paramMinecraftServer, int paramInt) {
/* 95 */     for (byte b = 0; b < paramMinecraftServer.worldServer.length; b++) {
/* 96 */       WorldServer worldServer = paramMinecraftServer.worldServer[b];
/* 97 */       worldServer.setDayTime(worldServer.getDayTime() + paramInt);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */