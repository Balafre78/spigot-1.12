/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ 
/*     */ public class CommandEffect
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  23 */     return "effect";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  28 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  33 */     return "commands.effect.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     MobEffectList mobEffectList;
/*  38 */     if (paramArrayOfString.length < 2) {
/*  39 */       throw new ExceptionUsage("commands.effect.usage", new Object[0]);
/*     */     }
/*     */     
/*  42 */     EntityLiving entityLiving = a(paramMinecraftServer, paramICommandListener, paramArrayOfString[0], EntityLiving.class);
/*     */     
/*  44 */     if ("clear".equals(paramArrayOfString[1])) {
/*  45 */       if (entityLiving.getEffects().isEmpty()) {
/*  46 */         throw new CommandException("commands.effect.failure.notActive.all", new Object[] { entityLiving.getName() });
/*     */       }
/*     */       
/*  49 */       entityLiving.removeAllEffects();
/*  50 */       a(paramICommandListener, this, "commands.effect.success.removed.all", new Object[] { entityLiving.getName() });
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/*  56 */       mobEffectList = MobEffectList.fromId(a(paramArrayOfString[1], 1));
/*  57 */     } catch (ExceptionInvalidNumber exceptionInvalidNumber) {
/*  58 */       mobEffectList = MobEffectList.getByName(paramArrayOfString[1]);
/*     */     } 
/*     */     
/*  61 */     if (mobEffectList == null) {
/*  62 */       throw new ExceptionInvalidNumber("commands.effect.notFound", new Object[] { paramArrayOfString[1] });
/*     */     }
/*     */     
/*  65 */     int i = 600;
/*  66 */     int j = 30;
/*  67 */     int k = 0;
/*     */     
/*  69 */     if (paramArrayOfString.length >= 3) {
/*  70 */       j = a(paramArrayOfString[2], 0, 1000000);
/*  71 */       if (mobEffectList.isInstant()) {
/*  72 */         i = j;
/*     */       } else {
/*  74 */         i = j * 20;
/*     */       } 
/*  76 */     } else if (mobEffectList.isInstant()) {
/*  77 */       i = 1;
/*     */     } 
/*     */     
/*  80 */     if (paramArrayOfString.length >= 4) {
/*  81 */       k = a(paramArrayOfString[3], 0, 255);
/*     */     }
/*     */     
/*  84 */     boolean bool = true;
/*  85 */     if (paramArrayOfString.length >= 5 && 
/*  86 */       "true".equalsIgnoreCase(paramArrayOfString[4])) {
/*  87 */       bool = false;
/*     */     }
/*     */ 
/*     */     
/*  91 */     if (j > 0) {
/*  92 */       MobEffect mobEffect = new MobEffect(mobEffectList, i, k, false, bool);
/*  93 */       entityLiving.addEffect(mobEffect);
/*  94 */       a(paramICommandListener, this, "commands.effect.success", new Object[] { new ChatMessage(mobEffect.f(), new Object[0]), Integer.valueOf(MobEffectList.getId(mobEffectList)), Integer.valueOf(k), entityLiving.getName(), Integer.valueOf(j) });
/*     */       
/*     */       return;
/*     */     } 
/*  98 */     if (entityLiving.hasEffect(mobEffectList)) {
/*  99 */       entityLiving.removeEffect(mobEffectList);
/* 100 */       a(paramICommandListener, this, "commands.effect.success.removed", new Object[] { new ChatMessage(mobEffectList.a(), new Object[0]), entityLiving.getName() });
/*     */     } else {
/* 102 */       throw new CommandException("commands.effect.failure.notActive", new Object[] { new ChatMessage(mobEffectList.a(), new Object[0]), entityLiving.getName() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 108 */     if (paramArrayOfString.length == 1) {
/* 109 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */     }
/* 111 */     if (paramArrayOfString.length == 2) {
/* 112 */       return a(paramArrayOfString, MobEffectList.REGISTRY.keySet());
/*     */     }
/* 114 */     if (paramArrayOfString.length == 5) {
/* 115 */       return a(paramArrayOfString, new String[] { "true", "false" });
/*     */     }
/*     */     
/* 118 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 123 */     return (paramInt == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */