/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandGamerule
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 14 */     return "gamerule";
/*    */   }
/*    */   
/*    */   public int a() {
/* 18 */     return 2;
/*    */   }
/*    */   
/*    */   public String getUsage(ICommandListener icommandlistener) {
/* 22 */     return "commands.gamerule.usage";
/*    */   }
/*    */   public void execute(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring) throws CommandException {
/*    */     String s2;
/* 26 */     GameRules gamerules = icommandlistener.getWorld().getGameRules();
/* 27 */     String s = (astring.length > 0) ? astring[0] : "";
/* 28 */     String s1 = (astring.length > 1) ? a(astring, 1) : "";
/*    */     
/* 30 */     switch (astring.length) {
/*    */       case 0:
/* 32 */         icommandlistener.sendMessage(new ChatComponentText(a((Object[])gamerules.getGameRules())));
/*    */         return;
/*    */       
/*    */       case 1:
/* 36 */         if (!gamerules.contains(s)) {
/* 37 */           throw new CommandException("commands.gamerule.norule", new Object[] { s });
/*    */         }
/*    */         
/* 40 */         s2 = gamerules.get(s);
/*    */         
/* 42 */         icommandlistener.sendMessage((new ChatComponentText(s)).a(" = ").a(s2));
/* 43 */         icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, gamerules.c(s));
/*    */         return;
/*    */     } 
/*    */     
/* 47 */     if (gamerules.a(s, GameRules.EnumGameRuleType.BOOLEAN_VALUE) && !"true".equals(s1) && !"false".equals(s1)) {
/* 48 */       throw new CommandException("commands.generic.boolean.invalid", new Object[] { s1 });
/*    */     }
/*    */     
/* 51 */     gamerules.set(s, s1);
/* 52 */     a(gamerules, s, minecraftserver);
/* 53 */     a(icommandlistener, this, "commands.gamerule.success", new Object[] { s, s1 });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a(GameRules gamerules, String s, MinecraftServer minecraftserver) {
/* 59 */     if ("reducedDebugInfo".equals(s)) {
/* 60 */       int i = gamerules.getBoolean(s) ? 22 : 23;
/* 61 */       Iterator<EntityPlayer> iterator = minecraftserver.getPlayerList().v().iterator();
/*    */       
/* 63 */       while (iterator.hasNext()) {
/* 64 */         EntityPlayer entityplayer = iterator.next();
/*    */         
/* 66 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityStatus(entityplayer, (byte)i));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring, @Nullable BlockPosition blockposition) {
/* 73 */     if (astring.length == 1) {
/* 74 */       return a(astring, a(minecraftserver).getGameRules());
/*    */     }
/* 76 */     if (astring.length == 2) {
/* 77 */       GameRules gamerules = a(minecraftserver);
/*    */       
/* 79 */       if (gamerules.a(astring[0], GameRules.EnumGameRuleType.BOOLEAN_VALUE)) {
/* 80 */         return a(astring, new String[] { "true", "false" });
/*    */       }
/*    */       
/* 83 */       if (gamerules.a(astring[0], GameRules.EnumGameRuleType.FUNCTION)) {
/* 84 */         return a(astring, minecraftserver.aL().d().keySet());
/*    */       }
/*    */     } 
/*    */     
/* 88 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   private GameRules a(MinecraftServer minecraftserver) {
/* 93 */     return minecraftserver.getWorldServer(0).getGameRules();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(ICommand o) {
/* 99 */     return a(o);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandGamerule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */