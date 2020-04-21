/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
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
/*     */ public class CommandScoreboard
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  45 */     return "scoreboard";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  50 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  55 */     return "commands.scoreboard.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  60 */     if (b(paramMinecraftServer, paramICommandListener, paramArrayOfString)) {
/*     */       return;
/*     */     }
/*     */     
/*  64 */     if (paramArrayOfString.length < 1) {
/*  65 */       throw new ExceptionUsage("commands.scoreboard.usage", new Object[0]);
/*     */     }
/*     */     
/*  68 */     if ("objectives".equalsIgnoreCase(paramArrayOfString[0])) {
/*  69 */       if (paramArrayOfString.length == 1)
/*  70 */         throw new ExceptionUsage("commands.scoreboard.objectives.usage", new Object[0]); 
/*  71 */       if ("list".equalsIgnoreCase(paramArrayOfString[1])) {
/*  72 */         a(paramICommandListener, paramMinecraftServer);
/*  73 */       } else if ("add".equalsIgnoreCase(paramArrayOfString[1])) {
/*  74 */         if (paramArrayOfString.length >= 4) {
/*  75 */           a(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/*  77 */           throw new ExceptionUsage("commands.scoreboard.objectives.add.usage", new Object[0]);
/*     */         } 
/*  79 */       } else if ("remove".equalsIgnoreCase(paramArrayOfString[1])) {
/*  80 */         if (paramArrayOfString.length == 3) {
/*  81 */           a(paramICommandListener, paramArrayOfString[2], paramMinecraftServer);
/*     */         } else {
/*  83 */           throw new ExceptionUsage("commands.scoreboard.objectives.remove.usage", new Object[0]);
/*     */         } 
/*  85 */       } else if ("setdisplay".equalsIgnoreCase(paramArrayOfString[1])) {
/*  86 */         if (paramArrayOfString.length == 3 || paramArrayOfString.length == 4) {
/*  87 */           i(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/*  89 */           throw new ExceptionUsage("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
/*     */         } 
/*     */       } else {
/*  92 */         throw new ExceptionUsage("commands.scoreboard.objectives.usage", new Object[0]);
/*     */       } 
/*  94 */     } else if ("players".equalsIgnoreCase(paramArrayOfString[0])) {
/*  95 */       if (paramArrayOfString.length == 1)
/*  96 */         throw new ExceptionUsage("commands.scoreboard.players.usage", new Object[0]); 
/*  97 */       if ("list".equalsIgnoreCase(paramArrayOfString[1])) {
/*  98 */         if (paramArrayOfString.length <= 3) {
/*  99 */           j(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 101 */           throw new ExceptionUsage("commands.scoreboard.players.list.usage", new Object[0]);
/*     */         } 
/* 103 */       } else if ("add".equalsIgnoreCase(paramArrayOfString[1])) {
/* 104 */         if (paramArrayOfString.length >= 5) {
/* 105 */           k(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 107 */           throw new ExceptionUsage("commands.scoreboard.players.add.usage", new Object[0]);
/*     */         } 
/* 109 */       } else if ("remove".equalsIgnoreCase(paramArrayOfString[1])) {
/* 110 */         if (paramArrayOfString.length >= 5) {
/* 111 */           k(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 113 */           throw new ExceptionUsage("commands.scoreboard.players.remove.usage", new Object[0]);
/*     */         } 
/* 115 */       } else if ("set".equalsIgnoreCase(paramArrayOfString[1])) {
/* 116 */         if (paramArrayOfString.length >= 5) {
/* 117 */           k(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 119 */           throw new ExceptionUsage("commands.scoreboard.players.set.usage", new Object[0]);
/*     */         } 
/* 121 */       } else if ("reset".equalsIgnoreCase(paramArrayOfString[1])) {
/* 122 */         if (paramArrayOfString.length == 3 || paramArrayOfString.length == 4) {
/* 123 */           l(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 125 */           throw new ExceptionUsage("commands.scoreboard.players.reset.usage", new Object[0]);
/*     */         } 
/* 127 */       } else if ("enable".equalsIgnoreCase(paramArrayOfString[1])) {
/* 128 */         if (paramArrayOfString.length == 4) {
/* 129 */           m(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 131 */           throw new ExceptionUsage("commands.scoreboard.players.enable.usage", new Object[0]);
/*     */         } 
/* 133 */       } else if ("test".equalsIgnoreCase(paramArrayOfString[1])) {
/* 134 */         if (paramArrayOfString.length == 5 || paramArrayOfString.length == 6) {
/* 135 */           n(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 137 */           throw new ExceptionUsage("commands.scoreboard.players.test.usage", new Object[0]);
/*     */         } 
/* 139 */       } else if ("operation".equalsIgnoreCase(paramArrayOfString[1])) {
/* 140 */         if (paramArrayOfString.length == 7) {
/* 141 */           o(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 143 */           throw new ExceptionUsage("commands.scoreboard.players.operation.usage", new Object[0]);
/*     */         } 
/* 145 */       } else if ("tag".equalsIgnoreCase(paramArrayOfString[1])) {
/* 146 */         if (paramArrayOfString.length >= 4) {
/* 147 */           a(paramMinecraftServer, paramICommandListener, paramArrayOfString, 2);
/*     */         } else {
/* 149 */           throw new ExceptionUsage("commands.scoreboard.players.tag.usage", new Object[0]);
/*     */         } 
/*     */       } else {
/* 152 */         throw new ExceptionUsage("commands.scoreboard.players.usage", new Object[0]);
/*     */       } 
/* 154 */     } else if ("teams".equalsIgnoreCase(paramArrayOfString[0])) {
/* 155 */       if (paramArrayOfString.length == 1)
/* 156 */         throw new ExceptionUsage("commands.scoreboard.teams.usage", new Object[0]); 
/* 157 */       if ("list".equalsIgnoreCase(paramArrayOfString[1])) {
/* 158 */         if (paramArrayOfString.length <= 3) {
/* 159 */           e(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 161 */           throw new ExceptionUsage("commands.scoreboard.teams.list.usage", new Object[0]);
/*     */         } 
/* 163 */       } else if ("add".equalsIgnoreCase(paramArrayOfString[1])) {
/* 164 */         if (paramArrayOfString.length >= 3) {
/* 165 */           b(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 167 */           throw new ExceptionUsage("commands.scoreboard.teams.add.usage", new Object[0]);
/*     */         } 
/* 169 */       } else if ("remove".equalsIgnoreCase(paramArrayOfString[1])) {
/* 170 */         if (paramArrayOfString.length == 3) {
/* 171 */           d(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 173 */           throw new ExceptionUsage("commands.scoreboard.teams.remove.usage", new Object[0]);
/*     */         } 
/* 175 */       } else if ("empty".equalsIgnoreCase(paramArrayOfString[1])) {
/* 176 */         if (paramArrayOfString.length == 3) {
/* 177 */           h(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 179 */           throw new ExceptionUsage("commands.scoreboard.teams.empty.usage", new Object[0]);
/*     */         } 
/* 181 */       } else if ("join".equalsIgnoreCase(paramArrayOfString[1])) {
/* 182 */         if (paramArrayOfString.length >= 4 || (paramArrayOfString.length == 3 && paramICommandListener instanceof EntityHuman)) {
/* 183 */           f(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 185 */           throw new ExceptionUsage("commands.scoreboard.teams.join.usage", new Object[0]);
/*     */         } 
/* 187 */       } else if ("leave".equalsIgnoreCase(paramArrayOfString[1])) {
/* 188 */         if (paramArrayOfString.length >= 3 || paramICommandListener instanceof EntityHuman) {
/* 189 */           g(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 191 */           throw new ExceptionUsage("commands.scoreboard.teams.leave.usage", new Object[0]);
/*     */         } 
/* 193 */       } else if ("option".equalsIgnoreCase(paramArrayOfString[1])) {
/* 194 */         if (paramArrayOfString.length == 4 || paramArrayOfString.length == 5) {
/* 195 */           c(paramICommandListener, paramArrayOfString, 2, paramMinecraftServer);
/*     */         } else {
/* 197 */           throw new ExceptionUsage("commands.scoreboard.teams.option.usage", new Object[0]);
/*     */         } 
/*     */       } else {
/* 200 */         throw new ExceptionUsage("commands.scoreboard.teams.usage", new Object[0]);
/*     */       } 
/*     */     } else {
/* 203 */       throw new ExceptionUsage("commands.scoreboard.usage", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean b(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 208 */     byte b = -1;
/* 209 */     for (byte b1 = 0; b1 < paramArrayOfString.length; b1++) {
/* 210 */       if (isListStart(paramArrayOfString, b1))
/*     */       {
/*     */         
/* 213 */         if ("*".equals(paramArrayOfString[b1]))
/* 214 */           if (b < 0) {
/* 215 */             b = b1;
/*     */           } else {
/* 217 */             throw new CommandException("commands.scoreboard.noMultiWildcard", new Object[0]);
/*     */           }  
/*     */       }
/*     */     } 
/* 221 */     if (b < 0) {
/* 222 */       return false;
/*     */     }
/* 224 */     ArrayList arrayList = Lists.newArrayList(a(paramMinecraftServer).getPlayers());
/* 225 */     String str = paramArrayOfString[b];
/* 226 */     ArrayList<String> arrayList1 = Lists.newArrayList();
/*     */     
/* 228 */     for (String str1 : arrayList) {
/* 229 */       paramArrayOfString[b] = str1;
/*     */       
/*     */       try {
/* 232 */         execute(paramMinecraftServer, paramICommandListener, paramArrayOfString);
/* 233 */         arrayList1.add(str1);
/* 234 */       } catch (CommandException commandException) {
/* 235 */         ChatMessage chatMessage = new ChatMessage(commandException.getMessage(), commandException.getArgs());
/* 236 */         chatMessage.getChatModifier().setColor(EnumChatFormat.RED);
/* 237 */         paramICommandListener.sendMessage(chatMessage);
/*     */       } 
/*     */     } 
/* 240 */     paramArrayOfString[b] = str;
/*     */     
/* 242 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ENTITIES, arrayList1.size());
/* 243 */     if (arrayList1.isEmpty()) {
/* 244 */       throw new ExceptionUsage("commands.scoreboard.allMatchesFailed", new Object[0]);
/*     */     }
/* 246 */     return true;
/*     */   }
/*     */   
/*     */   protected Scoreboard a(MinecraftServer paramMinecraftServer) {
/* 250 */     return paramMinecraftServer.getWorldServer(0).getScoreboard();
/*     */   }
/*     */   
/*     */   protected ScoreboardObjective a(String paramString, boolean paramBoolean, MinecraftServer paramMinecraftServer) throws CommandException {
/* 254 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 255 */     ScoreboardObjective scoreboardObjective = scoreboard.getObjective(paramString);
/*     */     
/* 257 */     if (scoreboardObjective == null)
/* 258 */       throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[] { paramString }); 
/* 259 */     if (paramBoolean && scoreboardObjective.getCriteria().isReadOnly()) {
/* 260 */       throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[] { paramString });
/*     */     }
/*     */     
/* 263 */     return scoreboardObjective;
/*     */   }
/*     */   
/*     */   protected ScoreboardTeam a(String paramString, MinecraftServer paramMinecraftServer) throws CommandException {
/* 267 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 268 */     ScoreboardTeam scoreboardTeam = scoreboard.getTeam(paramString);
/*     */     
/* 270 */     if (scoreboardTeam == null) {
/* 271 */       throw new CommandException("commands.scoreboard.teamNotFound", new Object[] { paramString });
/*     */     }
/*     */     
/* 274 */     return scoreboardTeam;
/*     */   }
/*     */   
/*     */   protected void a(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 278 */     String str1 = paramArrayOfString[paramInt++];
/* 279 */     String str2 = paramArrayOfString[paramInt++];
/* 280 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 281 */     IScoreboardCriteria iScoreboardCriteria = IScoreboardCriteria.criteria.get(str2);
/*     */     
/* 283 */     if (iScoreboardCriteria == null) {
/* 284 */       throw new ExceptionUsage("commands.scoreboard.objectives.add.wrongType", new Object[] { str2 });
/*     */     }
/* 286 */     if (scoreboard.getObjective(str1) != null) {
/* 287 */       throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[] { str1 });
/*     */     }
/* 289 */     if (str1.length() > 16) {
/* 290 */       throw new ExceptionInvalidSyntax("commands.scoreboard.objectives.add.tooLong", new Object[] { str1, Integer.valueOf(16) });
/*     */     }
/* 292 */     if (str1.isEmpty()) {
/* 293 */       throw new ExceptionUsage("commands.scoreboard.objectives.add.usage", new Object[0]);
/*     */     }
/*     */     
/* 296 */     if (paramArrayOfString.length > paramInt) {
/* 297 */       String str = a(paramICommandListener, paramArrayOfString, paramInt).toPlainText();
/*     */       
/* 299 */       if (str.length() > 32) {
/* 300 */         throw new ExceptionInvalidSyntax("commands.scoreboard.objectives.add.displayTooLong", new Object[] { str, Integer.valueOf(32) });
/*     */       }
/* 302 */       if (str.isEmpty()) {
/* 303 */         scoreboard.registerObjective(str1, iScoreboardCriteria);
/*     */       } else {
/* 305 */         scoreboard.registerObjective(str1, iScoreboardCriteria).setDisplayName(str);
/*     */       } 
/*     */     } else {
/* 308 */       scoreboard.registerObjective(str1, iScoreboardCriteria);
/*     */     } 
/*     */     
/* 311 */     a(paramICommandListener, this, "commands.scoreboard.objectives.add.success", new Object[] { str1 });
/*     */   }
/*     */   
/*     */   protected void b(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 315 */     String str = paramArrayOfString[paramInt++];
/* 316 */     Scoreboard scoreboard = a(paramMinecraftServer);
/*     */     
/* 318 */     if (scoreboard.getTeam(str) != null) {
/* 319 */       throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[] { str });
/*     */     }
/* 321 */     if (str.length() > 16) {
/* 322 */       throw new ExceptionInvalidSyntax("commands.scoreboard.teams.add.tooLong", new Object[] { str, Integer.valueOf(16) });
/*     */     }
/* 324 */     if (str.isEmpty()) {
/* 325 */       throw new ExceptionUsage("commands.scoreboard.teams.add.usage", new Object[0]);
/*     */     }
/*     */     
/* 328 */     if (paramArrayOfString.length > paramInt) {
/* 329 */       String str1 = a(paramICommandListener, paramArrayOfString, paramInt).toPlainText();
/* 330 */       if (str1.length() > 32) {
/* 331 */         throw new ExceptionInvalidSyntax("commands.scoreboard.teams.add.displayTooLong", new Object[] { str1, Integer.valueOf(32) });
/*     */       }
/* 333 */       if (str1.isEmpty()) {
/* 334 */         scoreboard.createTeam(str);
/*     */       } else {
/* 336 */         scoreboard.createTeam(str).setDisplayName(str1);
/*     */       } 
/*     */     } else {
/* 339 */       scoreboard.createTeam(str);
/*     */     } 
/*     */     
/* 342 */     a(paramICommandListener, this, "commands.scoreboard.teams.add.success", new Object[] { str });
/*     */   }
/*     */   
/*     */   protected void c(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 346 */     ScoreboardTeam scoreboardTeam = a(paramArrayOfString[paramInt++], paramMinecraftServer);
/* 347 */     if (scoreboardTeam == null) {
/*     */       return;
/*     */     }
/* 350 */     String str1 = paramArrayOfString[paramInt++].toLowerCase(Locale.ROOT);
/*     */     
/* 352 */     if (!"color".equalsIgnoreCase(str1) && !"friendlyfire".equalsIgnoreCase(str1) && !"seeFriendlyInvisibles".equalsIgnoreCase(str1) && !"nametagVisibility".equalsIgnoreCase(str1) && !"deathMessageVisibility".equalsIgnoreCase(str1) && !"collisionRule".equalsIgnoreCase(str1)) {
/* 353 */       throw new ExceptionUsage("commands.scoreboard.teams.option.usage", new Object[0]);
/*     */     }
/*     */     
/* 356 */     if (paramArrayOfString.length == 4) {
/* 357 */       if ("color".equalsIgnoreCase(str1))
/* 358 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(EnumChatFormat.a(true, false)) }); 
/* 359 */       if ("friendlyfire".equalsIgnoreCase(str1) || "seeFriendlyInvisibles".equalsIgnoreCase(str1))
/* 360 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(Arrays.asList(new String[] { "true", "false" })) }); 
/* 361 */       if ("nametagVisibility".equalsIgnoreCase(str1) || "deathMessageVisibility".equalsIgnoreCase(str1))
/* 362 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(ScoreboardTeamBase.EnumNameTagVisibility.a()) }); 
/* 363 */       if ("collisionRule".equalsIgnoreCase(str1)) {
/* 364 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(ScoreboardTeamBase.EnumTeamPush.a()) });
/*     */       }
/*     */       
/* 367 */       throw new ExceptionUsage("commands.scoreboard.teams.option.usage", new Object[0]);
/*     */     } 
/*     */     
/* 370 */     String str2 = paramArrayOfString[paramInt];
/*     */     
/* 372 */     if ("color".equalsIgnoreCase(str1)) {
/* 373 */       EnumChatFormat enumChatFormat = EnumChatFormat.b(str2);
/* 374 */       if (enumChatFormat == null || enumChatFormat.isFormat()) {
/* 375 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(EnumChatFormat.a(true, false)) });
/*     */       }
/* 377 */       scoreboardTeam.setColor(enumChatFormat);
/* 378 */       scoreboardTeam.setPrefix(enumChatFormat.toString());
/* 379 */       scoreboardTeam.setSuffix(EnumChatFormat.RESET.toString());
/* 380 */     } else if ("friendlyfire".equalsIgnoreCase(str1)) {
/* 381 */       if (!"true".equalsIgnoreCase(str2) && !"false".equalsIgnoreCase(str2)) {
/* 382 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(Arrays.asList(new String[] { "true", "false" })) });
/*     */       }
/* 384 */       scoreboardTeam.setAllowFriendlyFire("true".equalsIgnoreCase(str2));
/* 385 */     } else if ("seeFriendlyInvisibles".equalsIgnoreCase(str1)) {
/* 386 */       if (!"true".equalsIgnoreCase(str2) && !"false".equalsIgnoreCase(str2)) {
/* 387 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(Arrays.asList(new String[] { "true", "false" })) });
/*     */       }
/* 389 */       scoreboardTeam.setCanSeeFriendlyInvisibles("true".equalsIgnoreCase(str2));
/* 390 */     } else if ("nametagVisibility".equalsIgnoreCase(str1)) {
/* 391 */       ScoreboardTeamBase.EnumNameTagVisibility enumNameTagVisibility = ScoreboardTeamBase.EnumNameTagVisibility.a(str2);
/* 392 */       if (enumNameTagVisibility == null) {
/* 393 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(ScoreboardTeamBase.EnumNameTagVisibility.a()) });
/*     */       }
/* 395 */       scoreboardTeam.setNameTagVisibility(enumNameTagVisibility);
/* 396 */     } else if ("deathMessageVisibility".equalsIgnoreCase(str1)) {
/* 397 */       ScoreboardTeamBase.EnumNameTagVisibility enumNameTagVisibility = ScoreboardTeamBase.EnumNameTagVisibility.a(str2);
/* 398 */       if (enumNameTagVisibility == null) {
/* 399 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(ScoreboardTeamBase.EnumNameTagVisibility.a()) });
/*     */       }
/* 401 */       scoreboardTeam.setDeathMessageVisibility(enumNameTagVisibility);
/* 402 */     } else if ("collisionRule".equalsIgnoreCase(str1)) {
/* 403 */       ScoreboardTeamBase.EnumTeamPush enumTeamPush = ScoreboardTeamBase.EnumTeamPush.a(str2);
/* 404 */       if (enumTeamPush == null) {
/* 405 */         throw new ExceptionUsage("commands.scoreboard.teams.option.noValue", new Object[] { str1, a(ScoreboardTeamBase.EnumTeamPush.a()) });
/*     */       }
/* 407 */       scoreboardTeam.setCollisionRule(enumTeamPush);
/*     */     } 
/*     */     
/* 410 */     a(paramICommandListener, this, "commands.scoreboard.teams.option.success", new Object[] { str1, scoreboardTeam.getName(), str2 });
/*     */   }
/*     */   
/*     */   protected void d(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 414 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 415 */     ScoreboardTeam scoreboardTeam = a(paramArrayOfString[paramInt], paramMinecraftServer);
/* 416 */     if (scoreboardTeam == null) {
/*     */       return;
/*     */     }
/*     */     
/* 420 */     scoreboard.removeTeam(scoreboardTeam);
/* 421 */     a(paramICommandListener, this, "commands.scoreboard.teams.remove.success", new Object[] { scoreboardTeam.getName() });
/*     */   }
/*     */   
/*     */   protected void e(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 425 */     Scoreboard scoreboard = a(paramMinecraftServer);
/*     */     
/* 427 */     if (paramArrayOfString.length > paramInt) {
/* 428 */       ScoreboardTeam scoreboardTeam = a(paramArrayOfString[paramInt], paramMinecraftServer);
/* 429 */       if (scoreboardTeam == null) {
/*     */         return;
/*     */       }
/*     */       
/* 433 */       Collection<String> collection = scoreboardTeam.getPlayerNameSet();
/* 434 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, collection.size());
/* 435 */       if (collection.isEmpty()) {
/* 436 */         throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[] { scoreboardTeam.getName() });
/*     */       }
/* 438 */       ChatMessage chatMessage = new ChatMessage("commands.scoreboard.teams.list.player.count", new Object[] { Integer.valueOf(collection.size()), scoreboardTeam.getName() });
/* 439 */       chatMessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
/* 440 */       paramICommandListener.sendMessage(chatMessage);
/* 441 */       paramICommandListener.sendMessage(new ChatComponentText(a(collection.toArray())));
/*     */     } else {
/*     */       
/* 444 */       Collection<ScoreboardTeam> collection = scoreboard.getTeams();
/*     */       
/* 446 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, collection.size());
/* 447 */       if (collection.isEmpty()) {
/* 448 */         throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
/*     */       }
/* 450 */       ChatMessage chatMessage = new ChatMessage("commands.scoreboard.teams.list.count", new Object[] { Integer.valueOf(collection.size()) });
/* 451 */       chatMessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
/* 452 */       paramICommandListener.sendMessage(chatMessage);
/*     */       
/* 454 */       for (ScoreboardTeam scoreboardTeam : collection) {
/* 455 */         paramICommandListener.sendMessage(new ChatMessage("commands.scoreboard.teams.list.entry", new Object[] { scoreboardTeam.getName(), scoreboardTeam.getDisplayName(), Integer.valueOf(scoreboardTeam.getPlayerNameSet().size()) }));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void f(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 462 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 463 */     String str = paramArrayOfString[paramInt++];
/* 464 */     HashSet<String> hashSet1 = Sets.newHashSet();
/* 465 */     HashSet<String> hashSet2 = Sets.newHashSet();
/*     */     
/* 467 */     if (paramICommandListener instanceof EntityHuman && paramInt == paramArrayOfString.length) {
/* 468 */       String str1 = a(paramICommandListener).getName();
/*     */       
/* 470 */       if (scoreboard.addPlayerToTeam(str1, str)) {
/* 471 */         hashSet1.add(str1);
/*     */       } else {
/* 473 */         hashSet2.add(str1);
/*     */       } 
/*     */     } else {
/* 476 */       while (paramInt < paramArrayOfString.length) {
/* 477 */         String str1 = paramArrayOfString[paramInt++];
/* 478 */         if (PlayerSelector.isPattern(str1)) {
/* 479 */           List<Entity> list = d(paramMinecraftServer, paramICommandListener, str1);
/* 480 */           for (Entity entity : list) {
/* 481 */             String str3 = f(paramMinecraftServer, paramICommandListener, entity.bn());
/* 482 */             if (scoreboard.addPlayerToTeam(str3, str)) {
/* 483 */               hashSet1.add(str3); continue;
/*     */             } 
/* 485 */             hashSet2.add(str3);
/*     */           } 
/*     */           continue;
/*     */         } 
/* 489 */         String str2 = f(paramMinecraftServer, paramICommandListener, str1);
/* 490 */         if (scoreboard.addPlayerToTeam(str2, str)) {
/* 491 */           hashSet1.add(str2); continue;
/*     */         } 
/* 493 */         hashSet2.add(str2);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 499 */     if (!hashSet1.isEmpty()) {
/* 500 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ENTITIES, hashSet1.size());
/* 501 */       a(paramICommandListener, this, "commands.scoreboard.teams.join.success", new Object[] { Integer.valueOf(hashSet1.size()), str, a(hashSet1.toArray((Object[])new String[hashSet1.size()])) });
/*     */     } 
/* 503 */     if (!hashSet2.isEmpty()) {
/* 504 */       throw new CommandException("commands.scoreboard.teams.join.failure", new Object[] { Integer.valueOf(hashSet2.size()), str, a(hashSet2.toArray(new String[hashSet2.size()])) });
/*     */     }
/*     */   }
/*     */   
/*     */   protected void g(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 509 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 510 */     HashSet<String> hashSet1 = Sets.newHashSet();
/* 511 */     HashSet<String> hashSet2 = Sets.newHashSet();
/*     */     
/* 513 */     if (paramICommandListener instanceof EntityHuman && paramInt == paramArrayOfString.length) {
/* 514 */       String str = a(paramICommandListener).getName();
/*     */       
/* 516 */       if (scoreboard.removePlayerFromTeam(str)) {
/* 517 */         hashSet1.add(str);
/*     */       } else {
/* 519 */         hashSet2.add(str);
/*     */       } 
/*     */     } else {
/* 522 */       while (paramInt < paramArrayOfString.length) {
/* 523 */         String str1 = paramArrayOfString[paramInt++];
/* 524 */         if (PlayerSelector.isPattern(str1)) {
/* 525 */           List<Entity> list = d(paramMinecraftServer, paramICommandListener, str1);
/* 526 */           for (Entity entity : list) {
/* 527 */             String str = f(paramMinecraftServer, paramICommandListener, entity.bn());
/* 528 */             if (scoreboard.removePlayerFromTeam(str)) {
/* 529 */               hashSet1.add(str); continue;
/*     */             } 
/* 531 */             hashSet2.add(str);
/*     */           } 
/*     */           continue;
/*     */         } 
/* 535 */         String str2 = f(paramMinecraftServer, paramICommandListener, str1);
/* 536 */         if (scoreboard.removePlayerFromTeam(str2)) {
/* 537 */           hashSet1.add(str2); continue;
/*     */         } 
/* 539 */         hashSet2.add(str2);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 545 */     if (!hashSet1.isEmpty()) {
/* 546 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ENTITIES, hashSet1.size());
/* 547 */       a(paramICommandListener, this, "commands.scoreboard.teams.leave.success", new Object[] { Integer.valueOf(hashSet1.size()), a(hashSet1.toArray((Object[])new String[hashSet1.size()])) });
/*     */     } 
/* 549 */     if (!hashSet2.isEmpty()) {
/* 550 */       throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[] { Integer.valueOf(hashSet2.size()), a(hashSet2.toArray(new String[hashSet2.size()])) });
/*     */     }
/*     */   }
/*     */   
/*     */   protected void h(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 555 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 556 */     ScoreboardTeam scoreboardTeam = a(paramArrayOfString[paramInt], paramMinecraftServer);
/* 557 */     if (scoreboardTeam == null) {
/*     */       return;
/*     */     }
/*     */     
/* 561 */     ArrayList arrayList = Lists.newArrayList(scoreboardTeam.getPlayerNameSet());
/* 562 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ENTITIES, arrayList.size());
/* 563 */     if (arrayList.isEmpty()) {
/* 564 */       throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[] { scoreboardTeam.getName() });
/*     */     }
/*     */     
/* 567 */     for (String str : arrayList) {
/* 568 */       scoreboard.removePlayerFromTeam(str, scoreboardTeam);
/*     */     }
/*     */     
/* 571 */     a(paramICommandListener, this, "commands.scoreboard.teams.empty.success", new Object[] { Integer.valueOf(arrayList.size()), scoreboardTeam.getName() });
/*     */   }
/*     */   
/*     */   protected void a(ICommandListener paramICommandListener, String paramString, MinecraftServer paramMinecraftServer) throws CommandException {
/* 575 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 576 */     ScoreboardObjective scoreboardObjective = a(paramString, false, paramMinecraftServer);
/*     */     
/* 578 */     scoreboard.unregisterObjective(scoreboardObjective);
/*     */     
/* 580 */     a(paramICommandListener, this, "commands.scoreboard.objectives.remove.success", new Object[] { paramString });
/*     */   }
/*     */   
/*     */   protected void a(ICommandListener paramICommandListener, MinecraftServer paramMinecraftServer) throws CommandException {
/* 584 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 585 */     Collection<ScoreboardObjective> collection = scoreboard.getObjectives();
/*     */     
/* 587 */     if (collection.isEmpty()) {
/* 588 */       throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
/*     */     }
/* 590 */     ChatMessage chatMessage = new ChatMessage("commands.scoreboard.objectives.list.count", new Object[] { Integer.valueOf(collection.size()) });
/* 591 */     chatMessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
/* 592 */     paramICommandListener.sendMessage(chatMessage);
/*     */     
/* 594 */     for (ScoreboardObjective scoreboardObjective : collection) {
/* 595 */       paramICommandListener.sendMessage(new ChatMessage("commands.scoreboard.objectives.list.entry", new Object[] { scoreboardObjective.getName(), scoreboardObjective.getDisplayName(), scoreboardObjective.getCriteria().getName() }));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 601 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 602 */     String str = paramArrayOfString[paramInt++];
/* 603 */     int i = Scoreboard.getSlotForName(str);
/* 604 */     ScoreboardObjective scoreboardObjective = null;
/*     */     
/* 606 */     if (paramArrayOfString.length == 4) {
/* 607 */       scoreboardObjective = a(paramArrayOfString[paramInt], false, paramMinecraftServer);
/*     */     }
/*     */     
/* 610 */     if (i < 0) {
/* 611 */       throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[] { str });
/*     */     }
/*     */     
/* 614 */     scoreboard.setDisplaySlot(i, scoreboardObjective);
/*     */     
/* 616 */     if (scoreboardObjective != null) {
/* 617 */       a(paramICommandListener, this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[] { Scoreboard.getSlotName(i), scoreboardObjective.getName() });
/*     */     } else {
/* 619 */       a(paramICommandListener, this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[] { Scoreboard.getSlotName(i) });
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void j(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 624 */     Scoreboard scoreboard = a(paramMinecraftServer);
/*     */     
/* 626 */     if (paramArrayOfString.length > paramInt) {
/* 627 */       String str = f(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt]);
/* 628 */       Map<ScoreboardObjective, ScoreboardScore> map = scoreboard.getPlayerObjectives(str);
/*     */       
/* 630 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, map.size());
/* 631 */       if (map.isEmpty()) {
/* 632 */         throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[] { str });
/*     */       }
/* 634 */       ChatMessage chatMessage = new ChatMessage("commands.scoreboard.players.list.player.count", new Object[] { Integer.valueOf(map.size()), str });
/* 635 */       chatMessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
/* 636 */       paramICommandListener.sendMessage(chatMessage);
/*     */       
/* 638 */       for (ScoreboardScore scoreboardScore : map.values()) {
/* 639 */         paramICommandListener.sendMessage(new ChatMessage("commands.scoreboard.players.list.player.entry", new Object[] { Integer.valueOf(scoreboardScore.getScore()), scoreboardScore.getObjective().getDisplayName(), scoreboardScore.getObjective().getName() }));
/*     */       } 
/*     */     } else {
/*     */       
/* 643 */       Collection<String> collection = scoreboard.getPlayers();
/*     */       
/* 645 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, collection.size());
/* 646 */       if (collection.isEmpty()) {
/* 647 */         throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
/*     */       }
/* 649 */       ChatMessage chatMessage = new ChatMessage("commands.scoreboard.players.list.count", new Object[] { Integer.valueOf(collection.size()) });
/* 650 */       chatMessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
/* 651 */       paramICommandListener.sendMessage(chatMessage);
/* 652 */       paramICommandListener.sendMessage(new ChatComponentText(a(collection.toArray())));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void k(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 658 */     String str1 = paramArrayOfString[paramInt - 1];
/* 659 */     int i = paramInt;
/* 660 */     String str2 = f(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt++]);
/* 661 */     if (str2.length() > 40) {
/* 662 */       throw new ExceptionInvalidSyntax("commands.scoreboard.players.name.tooLong", new Object[] { str2, Integer.valueOf(40) });
/*     */     }
/* 664 */     ScoreboardObjective scoreboardObjective = a(paramArrayOfString[paramInt++], true, paramMinecraftServer);
/* 665 */     int j = "set".equalsIgnoreCase(str1) ? a(paramArrayOfString[paramInt++]) : a(paramArrayOfString[paramInt++], 0);
/*     */     
/* 667 */     if (paramArrayOfString.length > paramInt) {
/* 668 */       Entity entity = c(paramMinecraftServer, paramICommandListener, paramArrayOfString[i]);
/*     */       try {
/* 670 */         NBTTagCompound nBTTagCompound1 = MojangsonParser.parse(a(paramArrayOfString, paramInt));
/* 671 */         NBTTagCompound nBTTagCompound2 = a(entity);
/* 672 */         if (!GameProfileSerializer.a(nBTTagCompound1, nBTTagCompound2, true)) {
/* 673 */           throw new CommandException("commands.scoreboard.players.set.tagMismatch", new Object[] { str2 });
/*     */         }
/* 675 */       } catch (MojangsonParseException mojangsonParseException) {
/* 676 */         throw new CommandException("commands.scoreboard.players.set.tagError", new Object[] { mojangsonParseException.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/* 680 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 681 */     ScoreboardScore scoreboardScore = scoreboard.getPlayerScoreForObjective(str2, scoreboardObjective);
/* 682 */     if ("set".equalsIgnoreCase(str1)) {
/* 683 */       scoreboardScore.setScore(j);
/* 684 */     } else if ("add".equalsIgnoreCase(str1)) {
/* 685 */       scoreboardScore.addScore(j);
/*     */     } else {
/* 687 */       scoreboardScore.removeScore(j);
/*     */     } 
/*     */     
/* 690 */     a(paramICommandListener, this, "commands.scoreboard.players.set.success", new Object[] { scoreboardObjective.getName(), str2, Integer.valueOf(scoreboardScore.getScore()) });
/*     */   }
/*     */   
/*     */   protected void l(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 694 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 695 */     String str = f(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt++]);
/*     */     
/* 697 */     if (paramArrayOfString.length > paramInt) {
/* 698 */       ScoreboardObjective scoreboardObjective = a(paramArrayOfString[paramInt++], false, paramMinecraftServer);
/* 699 */       scoreboard.resetPlayerScores(str, scoreboardObjective);
/* 700 */       a(paramICommandListener, this, "commands.scoreboard.players.resetscore.success", new Object[] { scoreboardObjective.getName(), str });
/*     */     } else {
/* 702 */       scoreboard.resetPlayerScores(str, null);
/* 703 */       a(paramICommandListener, this, "commands.scoreboard.players.reset.success", new Object[] { str });
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void m(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 708 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 709 */     String str = e(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt++]);
/* 710 */     if (str.length() > 40) {
/* 711 */       throw new ExceptionInvalidSyntax("commands.scoreboard.players.name.tooLong", new Object[] { str, Integer.valueOf(40) });
/*     */     }
/* 713 */     ScoreboardObjective scoreboardObjective = a(paramArrayOfString[paramInt], false, paramMinecraftServer);
/* 714 */     if (scoreboardObjective.getCriteria() != IScoreboardCriteria.c) {
/* 715 */       throw new CommandException("commands.scoreboard.players.enable.noTrigger", new Object[] { scoreboardObjective.getName() });
/*     */     }
/* 717 */     ScoreboardScore scoreboardScore = scoreboard.getPlayerScoreForObjective(str, scoreboardObjective);
/* 718 */     scoreboardScore.a(false);
/* 719 */     a(paramICommandListener, this, "commands.scoreboard.players.enable.success", new Object[] { scoreboardObjective.getName(), str });
/*     */   }
/*     */   
/*     */   protected void n(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 723 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 724 */     String str = f(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt++]);
/* 725 */     if (str.length() > 40) {
/* 726 */       throw new ExceptionInvalidSyntax("commands.scoreboard.players.name.tooLong", new Object[] { str, Integer.valueOf(40) });
/*     */     }
/* 728 */     ScoreboardObjective scoreboardObjective = a(paramArrayOfString[paramInt++], false, paramMinecraftServer);
/* 729 */     if (!scoreboard.b(str, scoreboardObjective)) {
/* 730 */       throw new CommandException("commands.scoreboard.players.test.notFound", new Object[] { scoreboardObjective.getName(), str });
/*     */     }
/*     */     
/* 733 */     int i = paramArrayOfString[paramInt].equals("*") ? Integer.MIN_VALUE : a(paramArrayOfString[paramInt]);
/* 734 */     paramInt++;
/* 735 */     int j = (paramInt >= paramArrayOfString.length || paramArrayOfString[paramInt].equals("*")) ? Integer.MAX_VALUE : a(paramArrayOfString[paramInt], i);
/*     */     
/* 737 */     ScoreboardScore scoreboardScore = scoreboard.getPlayerScoreForObjective(str, scoreboardObjective);
/* 738 */     if (scoreboardScore.getScore() < i || scoreboardScore.getScore() > j) {
/* 739 */       throw new CommandException("commands.scoreboard.players.test.failed", new Object[] { Integer.valueOf(scoreboardScore.getScore()), Integer.valueOf(i), Integer.valueOf(j) });
/*     */     }
/* 741 */     a(paramICommandListener, this, "commands.scoreboard.players.test.success", new Object[] { Integer.valueOf(scoreboardScore.getScore()), Integer.valueOf(i), Integer.valueOf(j) });
/*     */   }
/*     */   
/*     */   protected void o(ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt, MinecraftServer paramMinecraftServer) throws CommandException {
/* 745 */     Scoreboard scoreboard = a(paramMinecraftServer);
/* 746 */     String str1 = f(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt++]);
/* 747 */     ScoreboardObjective scoreboardObjective1 = a(paramArrayOfString[paramInt++], true, paramMinecraftServer);
/*     */     
/* 749 */     String str2 = paramArrayOfString[paramInt++];
/* 750 */     String str3 = f(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt++]);
/* 751 */     ScoreboardObjective scoreboardObjective2 = a(paramArrayOfString[paramInt], false, paramMinecraftServer);
/*     */     
/* 753 */     if (str1.length() > 40) {
/* 754 */       throw new ExceptionInvalidSyntax("commands.scoreboard.players.name.tooLong", new Object[] { str1, Integer.valueOf(40) });
/*     */     }
/* 756 */     if (str3.length() > 40) {
/* 757 */       throw new ExceptionInvalidSyntax("commands.scoreboard.players.name.tooLong", new Object[] { str3, Integer.valueOf(40) });
/*     */     }
/*     */     
/* 760 */     ScoreboardScore scoreboardScore1 = scoreboard.getPlayerScoreForObjective(str1, scoreboardObjective1);
/* 761 */     if (!scoreboard.b(str3, scoreboardObjective2)) {
/* 762 */       throw new CommandException("commands.scoreboard.players.operation.notFound", new Object[] { scoreboardObjective2.getName(), str3 });
/*     */     }
/* 764 */     ScoreboardScore scoreboardScore2 = scoreboard.getPlayerScoreForObjective(str3, scoreboardObjective2);
/*     */     
/* 766 */     if ("+=".equals(str2)) {
/* 767 */       scoreboardScore1.setScore(scoreboardScore1.getScore() + scoreboardScore2.getScore());
/* 768 */     } else if ("-=".equals(str2)) {
/* 769 */       scoreboardScore1.setScore(scoreboardScore1.getScore() - scoreboardScore2.getScore());
/* 770 */     } else if ("*=".equals(str2)) {
/* 771 */       scoreboardScore1.setScore(scoreboardScore1.getScore() * scoreboardScore2.getScore());
/* 772 */     } else if ("/=".equals(str2)) {
/* 773 */       if (scoreboardScore2.getScore() != 0) {
/* 774 */         scoreboardScore1.setScore(scoreboardScore1.getScore() / scoreboardScore2.getScore());
/*     */       }
/* 776 */     } else if ("%=".equals(str2)) {
/* 777 */       if (scoreboardScore2.getScore() != 0) {
/* 778 */         scoreboardScore1.setScore(scoreboardScore1.getScore() % scoreboardScore2.getScore());
/*     */       }
/* 780 */     } else if ("=".equals(str2)) {
/* 781 */       scoreboardScore1.setScore(scoreboardScore2.getScore());
/* 782 */     } else if ("<".equals(str2)) {
/* 783 */       scoreboardScore1.setScore(Math.min(scoreboardScore1.getScore(), scoreboardScore2.getScore()));
/* 784 */     } else if (">".equals(str2)) {
/* 785 */       scoreboardScore1.setScore(Math.max(scoreboardScore1.getScore(), scoreboardScore2.getScore()));
/* 786 */     } else if ("><".equals(str2)) {
/* 787 */       int i = scoreboardScore1.getScore();
/* 788 */       scoreboardScore1.setScore(scoreboardScore2.getScore());
/* 789 */       scoreboardScore2.setScore(i);
/*     */     } else {
/* 791 */       throw new CommandException("commands.scoreboard.players.operation.invalidOperation", new Object[] { str2 });
/*     */     } 
/* 793 */     a(paramICommandListener, this, "commands.scoreboard.players.operation.success", new Object[0]);
/*     */   }
/*     */   
/*     */   protected void a(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, int paramInt) throws CommandException {
/* 797 */     String str1 = f(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt]);
/* 798 */     Entity entity = c(paramMinecraftServer, paramICommandListener, paramArrayOfString[paramInt++]);
/* 799 */     String str2 = paramArrayOfString[paramInt++];
/*     */     
/* 801 */     Set<String> set = entity.getScoreboardTags();
/* 802 */     if ("list".equals(str2)) {
/* 803 */       if (!set.isEmpty()) {
/* 804 */         ChatMessage chatMessage = new ChatMessage("commands.scoreboard.players.tag.list", new Object[] { str1 });
/* 805 */         chatMessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
/* 806 */         paramICommandListener.sendMessage(chatMessage);
/* 807 */         paramICommandListener.sendMessage(new ChatComponentText(a(set.toArray())));
/*     */       } 
/* 809 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, set.size());
/*     */       return;
/*     */     } 
/* 812 */     if (paramArrayOfString.length < 5) {
/* 813 */       throw new ExceptionUsage("commands.scoreboard.players.tag.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/* 817 */     String str3 = paramArrayOfString[paramInt++];
/*     */     
/* 819 */     if (paramArrayOfString.length > paramInt) {
/*     */       try {
/* 821 */         NBTTagCompound nBTTagCompound1 = MojangsonParser.parse(a(paramArrayOfString, paramInt));
/* 822 */         NBTTagCompound nBTTagCompound2 = a(entity);
/* 823 */         if (!GameProfileSerializer.a(nBTTagCompound1, nBTTagCompound2, true)) {
/* 824 */           throw new CommandException("commands.scoreboard.players.tag.tagMismatch", new Object[] { str1 });
/*     */         }
/* 826 */       } catch (MojangsonParseException mojangsonParseException) {
/* 827 */         throw new CommandException("commands.scoreboard.players.tag.tagError", new Object[] { mojangsonParseException.getMessage() });
/*     */       } 
/*     */     }
/*     */     
/* 831 */     if ("add".equals(str2)) {
/* 832 */       if (!entity.addScoreboardTag(str3)) {
/* 833 */         throw new CommandException("commands.scoreboard.players.tag.tooMany", new Object[] { Integer.valueOf(1024) });
/*     */       }
/* 835 */       a(paramICommandListener, this, "commands.scoreboard.players.tag.success.add", new Object[] { str3 });
/* 836 */     } else if ("remove".equals(str2)) {
/* 837 */       if (!entity.removeScoreboardTag(str3)) {
/* 838 */         throw new CommandException("commands.scoreboard.players.tag.notFound", new Object[] { str3 });
/*     */       }
/* 840 */       a(paramICommandListener, this, "commands.scoreboard.players.tag.success.remove", new Object[] { str3 });
/*     */     } else {
/* 842 */       throw new ExceptionUsage("commands.scoreboard.players.tag.usage", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 848 */     if (paramArrayOfString.length == 1) {
/* 849 */       return a(paramArrayOfString, new String[] { "objectives", "players", "teams" });
/*     */     }
/*     */     
/* 852 */     if ("objectives".equalsIgnoreCase(paramArrayOfString[0])) {
/* 853 */       if (paramArrayOfString.length == 2) {
/* 854 */         return a(paramArrayOfString, new String[] { "list", "add", "remove", "setdisplay" });
/*     */       }
/*     */       
/* 857 */       if ("add".equalsIgnoreCase(paramArrayOfString[1])) {
/* 858 */         if (paramArrayOfString.length == 4) {
/* 859 */           Set<String> set = IScoreboardCriteria.criteria.keySet();
/* 860 */           return a(paramArrayOfString, set);
/*     */         } 
/* 862 */       } else if ("remove".equalsIgnoreCase(paramArrayOfString[1])) {
/* 863 */         if (paramArrayOfString.length == 3) {
/* 864 */           return a(paramArrayOfString, a(false, paramMinecraftServer));
/*     */         }
/* 866 */       } else if ("setdisplay".equalsIgnoreCase(paramArrayOfString[1])) {
/* 867 */         if (paramArrayOfString.length == 3)
/* 868 */           return a(paramArrayOfString, Scoreboard.h()); 
/* 869 */         if (paramArrayOfString.length == 4) {
/* 870 */           return a(paramArrayOfString, a(false, paramMinecraftServer));
/*     */         }
/*     */       } 
/* 873 */     } else if ("players".equalsIgnoreCase(paramArrayOfString[0])) {
/* 874 */       if (paramArrayOfString.length == 2) {
/* 875 */         return a(paramArrayOfString, new String[] { "set", "add", "remove", "reset", "list", "enable", "test", "operation", "tag" });
/*     */       }
/*     */       
/* 878 */       if ("set".equalsIgnoreCase(paramArrayOfString[1]) || "add".equalsIgnoreCase(paramArrayOfString[1]) || "remove".equalsIgnoreCase(paramArrayOfString[1]) || "reset".equalsIgnoreCase(paramArrayOfString[1])) {
/* 879 */         if (paramArrayOfString.length == 3)
/* 880 */           return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/* 881 */         if (paramArrayOfString.length == 4) {
/* 882 */           return a(paramArrayOfString, a(true, paramMinecraftServer));
/*     */         }
/* 884 */       } else if ("enable".equalsIgnoreCase(paramArrayOfString[1])) {
/* 885 */         if (paramArrayOfString.length == 3)
/* 886 */           return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/* 887 */         if (paramArrayOfString.length == 4) {
/* 888 */           return a(paramArrayOfString, b(paramMinecraftServer));
/*     */         }
/* 890 */       } else if ("list".equalsIgnoreCase(paramArrayOfString[1]) || "test".equalsIgnoreCase(paramArrayOfString[1])) {
/* 891 */         if (paramArrayOfString.length == 3)
/* 892 */           return a(paramArrayOfString, a(paramMinecraftServer).getPlayers()); 
/* 893 */         if (paramArrayOfString.length == 4 && "test".equalsIgnoreCase(paramArrayOfString[1])) {
/* 894 */           return a(paramArrayOfString, a(false, paramMinecraftServer));
/*     */         }
/* 896 */       } else if ("operation".equalsIgnoreCase(paramArrayOfString[1])) {
/* 897 */         if (paramArrayOfString.length == 3)
/* 898 */           return a(paramArrayOfString, a(paramMinecraftServer).getPlayers()); 
/* 899 */         if (paramArrayOfString.length == 4)
/* 900 */           return a(paramArrayOfString, a(true, paramMinecraftServer)); 
/* 901 */         if (paramArrayOfString.length == 5)
/* 902 */           return a(paramArrayOfString, new String[] { "+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><" }); 
/* 903 */         if (paramArrayOfString.length == 6)
/* 904 */           return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/* 905 */         if (paramArrayOfString.length == 7) {
/* 906 */           return a(paramArrayOfString, a(false, paramMinecraftServer));
/*     */         }
/* 908 */       } else if ("tag".equalsIgnoreCase(paramArrayOfString[1])) {
/* 909 */         if (paramArrayOfString.length == 3)
/* 910 */           return a(paramArrayOfString, a(paramMinecraftServer).getPlayers()); 
/* 911 */         if (paramArrayOfString.length == 4) {
/* 912 */           return a(paramArrayOfString, new String[] { "add", "remove", "list" });
/*     */         }
/*     */       } 
/* 915 */     } else if ("teams".equalsIgnoreCase(paramArrayOfString[0])) {
/* 916 */       if (paramArrayOfString.length == 2) {
/* 917 */         return a(paramArrayOfString, new String[] { "add", "remove", "join", "leave", "empty", "list", "option" });
/*     */       }
/*     */       
/* 920 */       if ("join".equalsIgnoreCase(paramArrayOfString[1])) {
/* 921 */         if (paramArrayOfString.length == 3)
/* 922 */           return a(paramArrayOfString, a(paramMinecraftServer).getTeamNames()); 
/* 923 */         if (paramArrayOfString.length >= 4)
/* 924 */           return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/*     */       } else {
/* 926 */         if ("leave".equalsIgnoreCase(paramArrayOfString[1]))
/* 927 */           return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/* 928 */         if ("empty".equalsIgnoreCase(paramArrayOfString[1]) || "list".equalsIgnoreCase(paramArrayOfString[1]) || "remove".equalsIgnoreCase(paramArrayOfString[1])) {
/* 929 */           if (paramArrayOfString.length == 3) {
/* 930 */             return a(paramArrayOfString, a(paramMinecraftServer).getTeamNames());
/*     */           }
/* 932 */         } else if ("option".equalsIgnoreCase(paramArrayOfString[1])) {
/* 933 */           if (paramArrayOfString.length == 3)
/* 934 */             return a(paramArrayOfString, a(paramMinecraftServer).getTeamNames()); 
/* 935 */           if (paramArrayOfString.length == 4)
/* 936 */             return a(paramArrayOfString, new String[] { "color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility", "collisionRule" }); 
/* 937 */           if (paramArrayOfString.length == 5) {
/* 938 */             if ("color".equalsIgnoreCase(paramArrayOfString[3]))
/* 939 */               return a(paramArrayOfString, EnumChatFormat.a(true, false)); 
/* 940 */             if ("nametagVisibility".equalsIgnoreCase(paramArrayOfString[3]) || "deathMessageVisibility".equalsIgnoreCase(paramArrayOfString[3]))
/* 941 */               return a(paramArrayOfString, ScoreboardTeamBase.EnumNameTagVisibility.a()); 
/* 942 */             if ("collisionRule".equalsIgnoreCase(paramArrayOfString[3]))
/* 943 */               return a(paramArrayOfString, ScoreboardTeamBase.EnumTeamPush.a()); 
/* 944 */             if ("friendlyfire".equalsIgnoreCase(paramArrayOfString[3]) || "seeFriendlyInvisibles".equalsIgnoreCase(paramArrayOfString[3])) {
/* 945 */               return a(paramArrayOfString, new String[] { "true", "false" });
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 951 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   protected List<String> a(boolean paramBoolean, MinecraftServer paramMinecraftServer) {
/* 955 */     Collection<ScoreboardObjective> collection = a(paramMinecraftServer).getObjectives();
/* 956 */     ArrayList<String> arrayList = Lists.newArrayList();
/*     */     
/* 958 */     for (ScoreboardObjective scoreboardObjective : collection) {
/* 959 */       if (!paramBoolean || !scoreboardObjective.getCriteria().isReadOnly()) {
/* 960 */         arrayList.add(scoreboardObjective.getName());
/*     */       }
/*     */     } 
/*     */     
/* 964 */     return arrayList;
/*     */   }
/*     */   
/*     */   protected List<String> b(MinecraftServer paramMinecraftServer) {
/* 968 */     Collection<ScoreboardObjective> collection = a(paramMinecraftServer).getObjectives();
/* 969 */     ArrayList<String> arrayList = Lists.newArrayList();
/*     */     
/* 971 */     for (ScoreboardObjective scoreboardObjective : collection) {
/* 972 */       if (scoreboardObjective.getCriteria() == IScoreboardCriteria.c) {
/* 973 */         arrayList.add(scoreboardObjective.getName());
/*     */       }
/*     */     } 
/*     */     
/* 977 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 982 */     if ("players".equalsIgnoreCase(paramArrayOfString[0])) {
/* 983 */       if (paramArrayOfString.length > 1 && "operation".equalsIgnoreCase(paramArrayOfString[1])) {
/* 984 */         return (paramInt == 2 || paramInt == 5);
/*     */       }
/* 986 */       return (paramInt == 2);
/* 987 */     }  if ("teams".equalsIgnoreCase(paramArrayOfString[0])) {
/* 988 */       return (paramInt == 2);
/*     */     }
/*     */     
/* 991 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */