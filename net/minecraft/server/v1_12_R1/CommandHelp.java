/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandHelp
/*     */   extends CommandAbstract
/*     */ {
/*  28 */   private static final String[] a = new String[] { "Yolo", "Ask for help on twitter", "/deop @p", "Scoreboard deleted, commands blocked", "Contact helpdesk for help", "/testfornoob @p", "/trigger warning", "Oh my god, it's full of stats", "/kill @p[name=!Searge]", "Have you tried turning it off and on again?", "Sorry, no help today" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private final Random b = new Random();
/*     */ 
/*     */   
/*     */   public String getCommand() {
/*  46 */     return "help";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  51 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  56 */     return "commands.help.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getAliases() {
/*  61 */     return Arrays.asList(new String[] { "?" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  66 */     if (paramICommandListener instanceof CommandBlockListenerAbstract) {
/*  67 */       paramICommandListener.sendMessage((new ChatComponentText("Searge says: ")).a(a[this.b.nextInt(a.length) % a.length]));
/*     */       
/*     */       return;
/*     */     } 
/*  71 */     List<ICommand> list = a(paramICommandListener, paramMinecraftServer);
/*  72 */     byte b1 = 7;
/*  73 */     int i = (list.size() - 1) / 7;
/*  74 */     byte b2 = 0;
/*     */     
/*     */     try {
/*  77 */       b2 = (paramArrayOfString.length == 0) ? 0 : (a(paramArrayOfString[0], 1, i + 1) - 1);
/*  78 */     } catch (ExceptionInvalidNumber exceptionInvalidNumber) {
/*     */       
/*  80 */       Map<String, ICommand> map = a(paramMinecraftServer);
/*  81 */       ICommand iCommand = map.get(paramArrayOfString[0]);
/*     */       
/*  83 */       if (iCommand != null)
/*     */       {
/*  85 */         throw new ExceptionUsage(iCommand.getUsage(paramICommandListener), new Object[0]); } 
/*  86 */       if (MathHelper.a(paramArrayOfString[0], -1) != -1 || MathHelper.a(paramArrayOfString[0], -2) != -2) {
/*  87 */         throw exceptionInvalidNumber;
/*     */       }
/*  89 */       throw new ExceptionUnknownCommand();
/*     */     } 
/*     */ 
/*     */     
/*  93 */     int j = Math.min((b2 + 1) * 7, list.size());
/*     */     
/*  95 */     ChatMessage chatMessage = new ChatMessage("commands.help.header", new Object[] { Integer.valueOf(b2 + 1), Integer.valueOf(i + 1) });
/*  96 */     chatMessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
/*  97 */     paramICommandListener.sendMessage(chatMessage);
/*     */     
/*  99 */     for (int k = b2 * 7; k < j; k++) {
/* 100 */       ICommand iCommand = list.get(k);
/*     */       
/* 102 */       ChatMessage chatMessage1 = new ChatMessage(iCommand.getUsage(paramICommandListener), new Object[0]);
/* 103 */       chatMessage1.getChatModifier().setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/" + iCommand.getCommand() + " "));
/* 104 */       paramICommandListener.sendMessage(chatMessage1);
/*     */     } 
/*     */     
/* 107 */     if (b2 == 0) {
/* 108 */       ChatMessage chatMessage1 = new ChatMessage("commands.help.footer", new Object[0]);
/* 109 */       chatMessage1.getChatModifier().setColor(EnumChatFormat.GREEN);
/* 110 */       paramICommandListener.sendMessage(chatMessage1);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected List<ICommand> a(ICommandListener paramICommandListener, MinecraftServer paramMinecraftServer) {
/* 115 */     List<ICommand> list = paramMinecraftServer.getCommandHandler().a(paramICommandListener);
/* 116 */     Collections.sort(list);
/* 117 */     return list;
/*     */   }
/*     */   
/*     */   protected Map<String, ICommand> a(MinecraftServer paramMinecraftServer) {
/* 121 */     return paramMinecraftServer.getCommandHandler().getCommands();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 126 */     if (paramArrayOfString.length == 1) {
/* 127 */       Set<String> set = a(paramMinecraftServer).keySet();
/* 128 */       return a(paramArrayOfString, set.<String>toArray(new String[set.size()]));
/*     */     } 
/*     */     
/* 131 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandHelp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */