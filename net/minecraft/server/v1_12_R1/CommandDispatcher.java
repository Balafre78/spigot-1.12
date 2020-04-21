/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class CommandDispatcher extends CommandHandler implements ICommandDispatcher {
/*     */   private final MinecraftServer a;
/*     */   
/*     */   public CommandDispatcher(MinecraftServer minecraftserver) {
/*  10 */     this.a = minecraftserver;
/*  11 */     a(new CommandTime());
/*  12 */     a(new CommandGamemode());
/*  13 */     a(new CommandDifficulty());
/*  14 */     a(new CommandGamemodeDefault());
/*  15 */     a(new CommandKill());
/*  16 */     a(new CommandToggleDownfall());
/*  17 */     a(new CommandWeather());
/*  18 */     a(new CommandXp());
/*  19 */     a(new CommandTp());
/*  20 */     a(new CommandTeleport());
/*  21 */     a(new CommandGive());
/*  22 */     a(new CommandReplaceItem());
/*  23 */     a(new CommandStats());
/*  24 */     a(new CommandEffect());
/*  25 */     a(new CommandEnchant());
/*  26 */     a(new CommandParticle());
/*  27 */     a(new CommandMe());
/*  28 */     a(new CommandSeed());
/*  29 */     a(new CommandHelp());
/*  30 */     a(new CommandDebug());
/*  31 */     a(new CommandTell());
/*  32 */     a(new CommandSay());
/*  33 */     a(new CommandSpawnpoint());
/*  34 */     a(new CommandSetWorldSpawn());
/*  35 */     a(new CommandGamerule());
/*  36 */     a(new CommandClear());
/*  37 */     a(new CommandTestFor());
/*  38 */     a(new CommandSpreadPlayers());
/*  39 */     a(new CommandPlaySound());
/*  40 */     a(new CommandScoreboard());
/*  41 */     a(new CommandExecute());
/*  42 */     a(new CommandTrigger());
/*  43 */     a(new CommandAdvancement());
/*  44 */     a(new CommandRecipe());
/*  45 */     a(new CommandSummon());
/*  46 */     a(new CommandSetBlock());
/*  47 */     a(new CommandFill());
/*  48 */     a(new CommandClone());
/*  49 */     a(new CommandTestForBlocks());
/*  50 */     a(new CommandBlockData());
/*  51 */     a(new CommandTestForBlock());
/*  52 */     a(new CommandTellRaw());
/*  53 */     a(new CommandWorldBorder());
/*  54 */     a(new CommandTitle());
/*  55 */     a(new CommandEntityData());
/*  56 */     a(new CommandStopSound());
/*  57 */     a(new CommandLocate());
/*  58 */     a(new CommandReload());
/*  59 */     a(new CommandFunction());
/*  60 */     if (minecraftserver.aa()) {
/*  61 */       a(new CommandOp());
/*  62 */       a(new CommandDeop());
/*  63 */       a(new CommandStop());
/*  64 */       a(new CommandSaveAll());
/*  65 */       a(new CommandSaveOff());
/*  66 */       a(new CommandSaveOn());
/*  67 */       a(new CommandBanIp());
/*  68 */       a(new CommandPardonIP());
/*  69 */       a(new CommandBan());
/*  70 */       a(new CommandBanList());
/*  71 */       a(new CommandPardon());
/*  72 */       a(new CommandKick());
/*  73 */       a(new CommandList());
/*  74 */       a(new CommandWhitelist());
/*  75 */       a(new CommandIdleTimeout());
/*     */     } else {
/*  77 */       a(new CommandPublish());
/*     */     } 
/*     */     
/*  80 */     CommandAbstract.a(this);
/*     */   }
/*     */   
/*     */   public void a(ICommandListener icommandlistener, ICommand icommand, int i, String s, Object... aobject) {
/*  84 */     boolean flag = true;
/*  85 */     MinecraftServer minecraftserver = this.a;
/*     */     
/*  87 */     if (!icommandlistener.getSendCommandFeedback()) {
/*  88 */       flag = false;
/*     */     }
/*     */     
/*  91 */     ChatMessage chatmessage = new ChatMessage("chat.type.admin", new Object[] { icommandlistener.getName(), new ChatMessage(s, aobject) });
/*     */     
/*  93 */     chatmessage.getChatModifier().setColor(EnumChatFormat.GRAY);
/*  94 */     chatmessage.getChatModifier().setItalic(Boolean.valueOf(true));
/*  95 */     if (flag) {
/*  96 */       Iterator<EntityPlayer> iterator = minecraftserver.getPlayerList().v().iterator();
/*     */       
/*  98 */       while (iterator.hasNext()) {
/*  99 */         EntityHuman entityhuman = iterator.next();
/*     */         
/* 101 */         if (entityhuman != icommandlistener && minecraftserver.getPlayerList().isOp(entityhuman.getProfile()) && icommand.canUse(this.a, icommandlistener)) {
/* 102 */           boolean flag1 = (icommandlistener instanceof MinecraftServer && this.a.s());
/* 103 */           boolean flag2 = (icommandlistener instanceof RemoteControlCommandListener && this.a.r());
/*     */           
/* 105 */           if (flag1 || flag2 || (!(icommandlistener instanceof RemoteControlCommandListener) && !(icommandlistener instanceof MinecraftServer))) {
/* 106 */             entityhuman.sendMessage(chatmessage);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     if (icommandlistener != minecraftserver && minecraftserver.worldServer[0].getGameRules().getBoolean("logAdminCommands") && !SpigotConfig.silentCommandBlocks) {
/* 113 */       minecraftserver.sendMessage(chatmessage);
/*     */     }
/*     */     
/* 116 */     boolean flag3 = minecraftserver.worldServer[0].getGameRules().getBoolean("sendCommandFeedback");
/*     */     
/* 118 */     if (icommandlistener instanceof CommandBlockListenerAbstract) {
/* 119 */       flag3 = ((CommandBlockListenerAbstract)icommandlistener).n();
/*     */     }
/*     */     
/* 122 */     if (((i & 0x1) != 1 && flag3) || icommandlistener instanceof MinecraftServer) {
/* 123 */       icommandlistener.sendMessage(new ChatMessage(s, aobject));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected MinecraftServer a() {
/* 129 */     return this.a;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */