/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class CommandObjectiveExecutor
/*     */ {
/*  19 */   private static final int a = (EnumCommandResult.values()).length;
/*  20 */   private static final String[] b = new String[a];
/*     */   
/*  22 */   private String[] c = b;
/*  23 */   private String[] d = b;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, EnumCommandResult paramEnumCommandResult, int paramInt) {
/*  29 */     String str2, str1 = this.c[paramEnumCommandResult.a()];
/*  30 */     if (str1 == null) {
/*     */       return;
/*     */     }
/*  33 */     ICommandListener iCommandListener = new ICommandListener(this, paramICommandListener)
/*     */       {
/*     */         public String getName() {
/*  36 */           return this.a.getName();
/*     */         }
/*     */ 
/*     */         
/*     */         public IChatBaseComponent getScoreboardDisplayName() {
/*  41 */           return this.a.getScoreboardDisplayName();
/*     */         }
/*     */ 
/*     */         
/*     */         public void sendMessage(IChatBaseComponent param1IChatBaseComponent) {
/*  46 */           this.a.sendMessage(param1IChatBaseComponent);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean a(int param1Int, String param1String) {
/*  51 */           return true;
/*     */         }
/*     */ 
/*     */         
/*     */         public BlockPosition getChunkCoordinates() {
/*  56 */           return this.a.getChunkCoordinates();
/*     */         }
/*     */ 
/*     */         
/*     */         public Vec3D d() {
/*  61 */           return this.a.d();
/*     */         }
/*     */ 
/*     */         
/*     */         public World getWorld() {
/*  66 */           return this.a.getWorld();
/*     */         }
/*     */ 
/*     */         
/*     */         public Entity f() {
/*  71 */           return this.a.f();
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean getSendCommandFeedback() {
/*  76 */           return this.a.getSendCommandFeedback();
/*     */         }
/*     */ 
/*     */         
/*     */         public void a(CommandObjectiveExecutor.EnumCommandResult param1EnumCommandResult, int param1Int) {
/*  81 */           this.a.a(param1EnumCommandResult, param1Int);
/*     */         }
/*     */ 
/*     */         
/*     */         public MinecraftServer C_() {
/*  86 */           return this.a.C_();
/*     */         }
/*     */       };
/*     */     
/*     */     try {
/*  91 */       str2 = CommandAbstract.f(paramMinecraftServer, iCommandListener, str1);
/*  92 */     } catch (CommandException commandException) {
/*     */       return;
/*     */     } 
/*  95 */     String str3 = this.d[paramEnumCommandResult.a()];
/*  96 */     if (str3 == null) {
/*     */       return;
/*     */     }
/*  99 */     Scoreboard scoreboard = paramICommandListener.getWorld().getScoreboard();
/* 100 */     ScoreboardObjective scoreboardObjective = scoreboard.getObjective(str3);
/* 101 */     if (scoreboardObjective == null) {
/*     */       return;
/*     */     }
/* 104 */     if (!scoreboard.b(str2, scoreboardObjective)) {
/*     */       return;
/*     */     }
/* 107 */     ScoreboardScore scoreboardScore = scoreboard.getPlayerScoreForObjective(str2, scoreboardObjective);
/* 108 */     scoreboardScore.setScore(paramInt);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 112 */     if (!paramNBTTagCompound.hasKeyOfType("CommandStats", 10)) {
/*     */       return;
/*     */     }
/* 115 */     NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("CommandStats");
/* 116 */     for (EnumCommandResult enumCommandResult : EnumCommandResult.values()) {
/* 117 */       String str1 = enumCommandResult.b() + "Name";
/* 118 */       String str2 = enumCommandResult.b() + "Objective";
/* 119 */       if (nBTTagCompound.hasKeyOfType(str1, 8) && nBTTagCompound.hasKeyOfType(str2, 8)) {
/*     */ 
/*     */         
/* 122 */         String str3 = nBTTagCompound.getString(str1);
/* 123 */         String str4 = nBTTagCompound.getString(str2);
/* 124 */         a(this, enumCommandResult, str3, str4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/* 129 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 130 */     for (EnumCommandResult enumCommandResult : EnumCommandResult.values()) {
/* 131 */       String str1 = this.c[enumCommandResult.a()];
/* 132 */       String str2 = this.d[enumCommandResult.a()];
/* 133 */       if (str1 != null && str2 != null) {
/*     */ 
/*     */         
/* 136 */         nBTTagCompound.setString(enumCommandResult.b() + "Name", str1);
/* 137 */         nBTTagCompound.setString(enumCommandResult.b() + "Objective", str2);
/*     */       } 
/* 139 */     }  if (!nBTTagCompound.isEmpty()) {
/* 140 */       paramNBTTagCompound.set("CommandStats", nBTTagCompound);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void a(CommandObjectiveExecutor paramCommandObjectiveExecutor, EnumCommandResult paramEnumCommandResult, @Nullable String paramString1, @Nullable String paramString2) {
/* 145 */     if (paramString1 == null || paramString1.isEmpty() || paramString2 == null || paramString2.isEmpty()) {
/* 146 */       a(paramCommandObjectiveExecutor, paramEnumCommandResult);
/*     */       return;
/*     */     } 
/* 149 */     if (paramCommandObjectiveExecutor.c == b || paramCommandObjectiveExecutor.d == b) {
/* 150 */       paramCommandObjectiveExecutor.c = new String[a];
/* 151 */       paramCommandObjectiveExecutor.d = new String[a];
/*     */     } 
/* 153 */     paramCommandObjectiveExecutor.c[paramEnumCommandResult.a()] = paramString1;
/* 154 */     paramCommandObjectiveExecutor.d[paramEnumCommandResult.a()] = paramString2;
/*     */   }
/*     */   
/*     */   private static void a(CommandObjectiveExecutor paramCommandObjectiveExecutor, EnumCommandResult paramEnumCommandResult) {
/* 158 */     if (paramCommandObjectiveExecutor.c == b || paramCommandObjectiveExecutor.d == b) {
/*     */       return;
/*     */     }
/* 161 */     paramCommandObjectiveExecutor.c[paramEnumCommandResult.a()] = null;
/* 162 */     paramCommandObjectiveExecutor.d[paramEnumCommandResult.a()] = null;
/*     */     
/* 164 */     boolean bool = true;
/* 165 */     for (EnumCommandResult enumCommandResult : EnumCommandResult.values()) {
/* 166 */       if (paramCommandObjectiveExecutor.c[enumCommandResult.a()] != null && paramCommandObjectiveExecutor.d[enumCommandResult.a()] != null) {
/* 167 */         bool = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 171 */     if (bool) {
/* 172 */       paramCommandObjectiveExecutor.c = b;
/* 173 */       paramCommandObjectiveExecutor.d = b;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(CommandObjectiveExecutor paramCommandObjectiveExecutor) {
/* 178 */     for (EnumCommandResult enumCommandResult : EnumCommandResult.values()) {
/* 179 */       a(this, enumCommandResult, paramCommandObjectiveExecutor.c[enumCommandResult.a()], paramCommandObjectiveExecutor.d[enumCommandResult.a()]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumCommandResult
/*     */   {
/* 188 */     SUCCESS_COUNT(0, "SuccessCount"),
/* 189 */     AFFECTED_BLOCKS(1, "AffectedBlocks"),
/* 190 */     AFFECTED_ENTITIES(2, "AffectedEntities"),
/* 191 */     AFFECTED_ITEMS(3, "AffectedItems"),
/* 192 */     QUERY_RESULT(4, "QueryResult");
/*     */     
/*     */     final int f;
/*     */     final String g;
/*     */     
/*     */     EnumCommandResult(int param1Int1, String param1String1) {
/* 198 */       this.f = param1Int1;
/* 199 */       this.g = param1String1;
/*     */     }
/*     */     
/*     */     public int a() {
/* 203 */       return this.f;
/*     */     }
/*     */     
/*     */     public String b() {
/* 207 */       return this.g;
/*     */     }
/*     */     
/*     */     public static String[] c() {
/* 211 */       String[] arrayOfString = new String[(values()).length];
/* 212 */       byte b = 0;
/* 213 */       for (EnumCommandResult enumCommandResult : values()) {
/* 214 */         arrayOfString[b++] = enumCommandResult.b();
/*     */       }
/* 216 */       return arrayOfString;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static EnumCommandResult a(String param1String) {
/* 221 */       for (EnumCommandResult enumCommandResult : values()) {
/* 222 */         if (enumCommandResult.b().equals(param1String)) {
/* 223 */           return enumCommandResult;
/*     */         }
/*     */       } 
/* 226 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandObjectiveExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */