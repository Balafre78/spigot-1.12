/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class CommandTrigger
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  24 */     return "trigger";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  29 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  34 */     return "commands.trigger.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     EntityPlayer entityPlayer;
/*  39 */     if (paramArrayOfString.length < 3) {
/*  40 */       throw new ExceptionUsage("commands.trigger.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  44 */     if (paramICommandListener instanceof EntityPlayer) {
/*  45 */       entityPlayer = (EntityPlayer)paramICommandListener;
/*     */     } else {
/*  47 */       Entity entity = paramICommandListener.f();
/*  48 */       if (entity instanceof EntityPlayer) {
/*  49 */         entityPlayer = (EntityPlayer)entity;
/*     */       } else {
/*  51 */         throw new CommandException("commands.trigger.invalidPlayer", new Object[0]);
/*     */       } 
/*     */     } 
/*     */     
/*  55 */     Scoreboard scoreboard = paramMinecraftServer.getWorldServer(0).getScoreboard();
/*  56 */     ScoreboardObjective scoreboardObjective = scoreboard.getObjective(paramArrayOfString[0]);
/*  57 */     if (scoreboardObjective == null || scoreboardObjective.getCriteria() != IScoreboardCriteria.c) {
/*  58 */       throw new CommandException("commands.trigger.invalidObjective", new Object[] { paramArrayOfString[0] });
/*     */     }
/*     */     
/*  61 */     int i = a(paramArrayOfString[2]);
/*  62 */     if (!scoreboard.b(entityPlayer.getName(), scoreboardObjective)) {
/*  63 */       throw new CommandException("commands.trigger.invalidObjective", new Object[] { paramArrayOfString[0] });
/*     */     }
/*     */     
/*  66 */     ScoreboardScore scoreboardScore = scoreboard.getPlayerScoreForObjective(entityPlayer.getName(), scoreboardObjective);
/*  67 */     if (scoreboardScore.g()) {
/*  68 */       throw new CommandException("commands.trigger.disabled", new Object[] { paramArrayOfString[0] });
/*     */     }
/*     */     
/*  71 */     if ("set".equals(paramArrayOfString[1])) {
/*  72 */       scoreboardScore.setScore(i);
/*  73 */     } else if ("add".equals(paramArrayOfString[1])) {
/*  74 */       scoreboardScore.addScore(i);
/*     */     } else {
/*  76 */       throw new CommandException("commands.trigger.invalidMode", new Object[] { paramArrayOfString[1] });
/*     */     } 
/*     */     
/*  79 */     scoreboardScore.a(true);
/*  80 */     if (entityPlayer.playerInteractManager.isCreative()) {
/*  81 */       a(paramICommandListener, this, "commands.trigger.success", new Object[] { paramArrayOfString[0], paramArrayOfString[1], paramArrayOfString[2] });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/*  87 */     if (paramArrayOfString.length == 1) {
/*  88 */       Scoreboard scoreboard = paramMinecraftServer.getWorldServer(0).getScoreboard();
/*  89 */       ArrayList<String> arrayList = Lists.newArrayList();
/*  90 */       for (ScoreboardObjective scoreboardObjective : scoreboard.getObjectives()) {
/*  91 */         if (scoreboardObjective.getCriteria() == IScoreboardCriteria.c) {
/*  92 */           arrayList.add(scoreboardObjective.getName());
/*     */         }
/*     */       } 
/*  95 */       return a(paramArrayOfString, arrayList.<String>toArray(new String[arrayList.size()]));
/*     */     } 
/*  97 */     if (paramArrayOfString.length == 2) {
/*  98 */       return a(paramArrayOfString, new String[] { "add", "set" });
/*     */     }
/*     */     
/* 101 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */