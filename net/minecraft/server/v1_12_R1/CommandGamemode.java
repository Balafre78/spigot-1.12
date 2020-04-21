/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class CommandGamemode
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 12 */     return "gamemode";
/*    */   }
/*    */   
/*    */   public int a() {
/* 16 */     return 2;
/*    */   }
/*    */   
/*    */   public String getUsage(ICommandListener icommandlistener) {
/* 20 */     return "commands.gamemode.usage";
/*    */   }
/*    */   
/*    */   public void execute(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring) throws CommandException {
/* 24 */     if (astring.length <= 0) {
/* 25 */       throw new ExceptionUsage("commands.gamemode.usage", new Object[0]);
/*    */     }
/* 27 */     EnumGamemode enumgamemode = c(icommandlistener, astring[0]);
/* 28 */     EntityPlayer entityplayer = (astring.length >= 2) ? b(minecraftserver, icommandlistener, astring[1]) : a(icommandlistener);
/*    */     
/* 30 */     entityplayer.a(enumgamemode);
/*    */     
/* 32 */     if (entityplayer.playerInteractManager.getGameMode() != enumgamemode) {
/* 33 */       icommandlistener.sendMessage(new ChatComponentText("Failed to set the gamemode of '" + entityplayer.getName() + "'"));
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     ChatMessage chatmessage = new ChatMessage("gameMode." + enumgamemode.b(), new Object[0]);
/*    */     
/* 39 */     if (icommandlistener.getWorld().getGameRules().getBoolean("sendCommandFeedback")) {
/* 40 */       entityplayer.sendMessage(new ChatMessage("gameMode.changed", new Object[] { chatmessage }));
/*    */     }
/*    */     
/* 43 */     if (entityplayer == icommandlistener) {
/* 44 */       a(icommandlistener, this, 1, "commands.gamemode.success.self", new Object[] { chatmessage });
/*    */     } else {
/* 46 */       a(icommandlistener, this, 1, "commands.gamemode.success.other", new Object[] { entityplayer.getName(), chatmessage });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected EnumGamemode c(ICommandListener icommandlistener, String s) throws ExceptionInvalidNumber {
/* 53 */     EnumGamemode enumgamemode = EnumGamemode.a(s, EnumGamemode.NOT_SET);
/*    */     
/* 55 */     return (enumgamemode == EnumGamemode.NOT_SET) ? WorldSettings.a(a(s, 0, (EnumGamemode.values()).length - 2)) : enumgamemode;
/*    */   }
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring, @Nullable BlockPosition blockposition) {
/* 59 */     return (astring.length == 1) ? a(astring, new String[] { "survival", "creative", "adventure", "spectator" }) : ((astring.length == 2) ? a(astring, minecraftserver.getPlayers()) : Collections.<String>emptyList());
/*    */   }
/*    */   
/*    */   public boolean isListStart(String[] astring, int i) {
/* 63 */     return (i == 1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(ICommand o) {
/* 69 */     return a(o);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandGamemode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */