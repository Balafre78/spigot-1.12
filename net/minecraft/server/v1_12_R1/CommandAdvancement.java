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
/*     */ public class CommandAdvancement
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  24 */     return "advancement";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  29 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  34 */     return "commands.advancement.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  39 */     if (paramArrayOfString.length < 1) {
/*  40 */       throw new ExceptionUsage("commands.advancement.usage", new Object[0]);
/*     */     }
/*     */     
/*  43 */     Action action = Action.a(paramArrayOfString[0]);
/*  44 */     if (action != null) {
/*  45 */       if (paramArrayOfString.length < 3) {
/*  46 */         throw action.a();
/*     */       }
/*  48 */       EntityPlayer entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[1]);
/*  49 */       Filter filter = Filter.a(paramArrayOfString[2]);
/*  50 */       if (filter == null) {
/*  51 */         throw action.a();
/*     */       }
/*  53 */       a(paramMinecraftServer, paramICommandListener, paramArrayOfString, entityPlayer, action, filter);
/*  54 */     } else if ("test".equals(paramArrayOfString[0])) {
/*  55 */       if (paramArrayOfString.length == 3) {
/*  56 */         a(paramICommandListener, b(paramMinecraftServer, paramICommandListener, paramArrayOfString[1]), a(paramMinecraftServer, paramArrayOfString[2]));
/*  57 */       } else if (paramArrayOfString.length == 4) {
/*  58 */         a(paramICommandListener, b(paramMinecraftServer, paramICommandListener, paramArrayOfString[1]), a(paramMinecraftServer, paramArrayOfString[2]), paramArrayOfString[3]);
/*     */       } else {
/*  60 */         throw new ExceptionUsage("commands.advancement.test.usage", new Object[0]);
/*     */       } 
/*     */     } else {
/*  63 */       throw new ExceptionUsage("commands.advancement.usage", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, EntityPlayer paramEntityPlayer, Action paramAction, Filter paramFilter) throws CommandException {
/*  68 */     if (paramFilter == Filter.EVERYTHING) {
/*  69 */       if (paramArrayOfString.length == 3) {
/*  70 */         int i = paramAction.a(paramEntityPlayer, paramMinecraftServer.getAdvancementData().c());
/*  71 */         if (i == 0) {
/*  72 */           throw paramFilter.a(paramAction, new Object[] { paramEntityPlayer.getName() });
/*     */         }
/*  74 */         paramFilter.a(paramICommandListener, this, paramAction, new Object[] { paramEntityPlayer.getName(), Integer.valueOf(i) });
/*     */         
/*     */         return;
/*     */       } 
/*  78 */       throw paramFilter.a(paramAction);
/*     */     } 
/*     */ 
/*     */     
/*  82 */     if (paramArrayOfString.length < 4) {
/*  83 */       throw paramFilter.a(paramAction);
/*     */     }
/*     */     
/*  86 */     Advancement advancement = a(paramMinecraftServer, paramArrayOfString[3]);
/*  87 */     if (paramFilter == Filter.ONLY && paramArrayOfString.length == 5) {
/*  88 */       String str = paramArrayOfString[4];
/*  89 */       if (!advancement.getCriteria().keySet().contains(str)) {
/*  90 */         throw new CommandException("commands.advancement.criterionNotFound", new Object[] { advancement.getName(), paramArrayOfString[4] });
/*     */       }
/*  92 */       if (paramAction.a(paramEntityPlayer, advancement, str)) {
/*  93 */         a(paramICommandListener, this, paramAction.d + ".criterion.success", new Object[] { advancement.getName(), paramEntityPlayer.getName(), str });
/*     */       } else {
/*  95 */         throw new CommandException(paramAction.d + ".criterion.failed", new Object[] { advancement.getName(), paramEntityPlayer.getName(), str });
/*     */       } 
/*  97 */     } else if (paramArrayOfString.length == 4) {
/*  98 */       List<Advancement> list = a(advancement, paramFilter);
/*  99 */       int i = paramAction.a(paramEntityPlayer, list);
/* 100 */       if (i == 0) {
/* 101 */         throw paramFilter.a(paramAction, new Object[] { advancement.getName(), paramEntityPlayer.getName() });
/*     */       }
/* 103 */       paramFilter.a(paramICommandListener, this, paramAction, new Object[] { advancement.getName(), paramEntityPlayer.getName(), Integer.valueOf(i) });
/*     */     } else {
/*     */       
/* 106 */       throw paramFilter.a(paramAction);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(Advancement paramAdvancement, List<Advancement> paramList) {
/* 111 */     for (Advancement advancement : paramAdvancement.e()) {
/* 112 */       paramList.add(advancement);
/* 113 */       a(advancement, paramList);
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<Advancement> a(Advancement paramAdvancement, Filter paramFilter) {
/* 118 */     ArrayList<Advancement> arrayList = Lists.newArrayList();
/* 119 */     if (paramFilter.h) {
/* 120 */       Advancement advancement = paramAdvancement.b();
/* 121 */       while (advancement != null) {
/* 122 */         arrayList.add(advancement);
/* 123 */         advancement = advancement.b();
/*     */       } 
/*     */     } 
/* 126 */     arrayList.add(paramAdvancement);
/* 127 */     if (paramFilter.i) {
/* 128 */       a(paramAdvancement, arrayList);
/*     */     }
/* 130 */     return arrayList;
/*     */   }
/*     */   
/*     */   private void a(ICommandListener paramICommandListener, EntityPlayer paramEntityPlayer, Advancement paramAdvancement, String paramString) throws CommandException {
/* 134 */     AdvancementDataPlayer advancementDataPlayer = paramEntityPlayer.getAdvancementData();
/* 135 */     CriterionProgress criterionProgress = advancementDataPlayer.getProgress(paramAdvancement).getCriterionProgress(paramString);
/* 136 */     if (criterionProgress == null)
/* 137 */       throw new CommandException("commands.advancement.criterionNotFound", new Object[] { paramAdvancement.getName(), paramString }); 
/* 138 */     if (!criterionProgress.a()) {
/* 139 */       throw new CommandException("commands.advancement.test.criterion.notDone", new Object[] { paramEntityPlayer.getName(), paramAdvancement.getName(), paramString });
/*     */     }
/* 141 */     a(paramICommandListener, this, "commands.advancement.test.criterion.success", new Object[] { paramEntityPlayer.getName(), paramAdvancement.getName(), paramString });
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(ICommandListener paramICommandListener, EntityPlayer paramEntityPlayer, Advancement paramAdvancement) throws CommandException {
/* 146 */     AdvancementProgress advancementProgress = paramEntityPlayer.getAdvancementData().getProgress(paramAdvancement);
/* 147 */     if (!advancementProgress.isDone()) {
/* 148 */       throw new CommandException("commands.advancement.test.advancement.notDone", new Object[] { paramEntityPlayer.getName(), paramAdvancement.getName() });
/*     */     }
/* 150 */     a(paramICommandListener, this, "commands.advancement.test.advancement.success", new Object[] { paramEntityPlayer.getName(), paramAdvancement.getName() });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 156 */     if (paramArrayOfString.length == 1) {
/* 157 */       return a(paramArrayOfString, new String[] { "grant", "revoke", "test" });
/*     */     }
/*     */     
/* 160 */     Action action = Action.a(paramArrayOfString[0]);
/* 161 */     if (action != null) {
/* 162 */       if (paramArrayOfString.length == 2) {
/* 163 */         return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */       }
/*     */       
/* 166 */       if (paramArrayOfString.length == 3) {
/* 167 */         return a(paramArrayOfString, Filter.f);
/*     */       }
/*     */       
/* 170 */       Filter filter = Filter.a(paramArrayOfString[2]);
/* 171 */       if (filter != null && filter != Filter.EVERYTHING) {
/* 172 */         if (paramArrayOfString.length == 4) {
/* 173 */           return a(paramArrayOfString, a(paramMinecraftServer));
/*     */         }
/* 175 */         if (paramArrayOfString.length == 5 && filter == Filter.ONLY) {
/* 176 */           Advancement advancement = paramMinecraftServer.getAdvancementData().a(new MinecraftKey(paramArrayOfString[3]));
/* 177 */           if (advancement != null) {
/* 178 */             return a(paramArrayOfString, advancement.getCriteria().keySet());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 184 */     if ("test".equals(paramArrayOfString[0])) {
/* 185 */       if (paramArrayOfString.length == 2) {
/* 186 */         return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */       }
/*     */       
/* 189 */       if (paramArrayOfString.length == 3) {
/* 190 */         return a(paramArrayOfString, a(paramMinecraftServer));
/*     */       }
/*     */       
/* 193 */       if (paramArrayOfString.length == 4) {
/* 194 */         Advancement advancement = paramMinecraftServer.getAdvancementData().a(new MinecraftKey(paramArrayOfString[2]));
/* 195 */         if (advancement != null) {
/* 196 */           return a(paramArrayOfString, advancement.getCriteria().keySet());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   private List<MinecraftKey> a(MinecraftServer paramMinecraftServer) {
/* 205 */     ArrayList<MinecraftKey> arrayList = Lists.newArrayList();
/* 206 */     for (Advancement advancement : paramMinecraftServer.getAdvancementData().c()) {
/* 207 */       arrayList.add(advancement.getName());
/*     */     }
/* 209 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 214 */     return (paramArrayOfString.length > 1 && ("grant".equals(paramArrayOfString[0]) || "revoke".equals(paramArrayOfString[0]) || "test".equals(paramArrayOfString[0])) && paramInt == 1);
/*     */   }
/*     */   
/*     */   public static Advancement a(MinecraftServer paramMinecraftServer, String paramString) throws CommandException {
/* 218 */     Advancement advancement = paramMinecraftServer.getAdvancementData().a(new MinecraftKey(paramString));
/* 219 */     if (advancement == null) {
/* 220 */       throw new CommandException("commands.advancement.advancementNotFound", new Object[] { paramString });
/*     */     }
/* 222 */     return advancement;
/*     */   }
/*     */   
/*     */   enum Action {
/* 226 */     GRANT("grant"),
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
/* 244 */     REVOKE("revoke");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final String c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final String d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Action(String param1String1) {
/* 268 */       this.c = param1String1;
/* 269 */       this.d = "commands.advancement." + param1String1;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     static Action a(String param1String) {
/* 274 */       for (Action action : values()) {
/* 275 */         if (action.c.equals(param1String)) {
/* 276 */           return action;
/*     */         }
/*     */       } 
/* 279 */       return null;
/*     */     }
/*     */     
/*     */     CommandException a() {
/* 283 */       return new CommandException(this.d + ".usage", new Object[0]);
/*     */     }
/*     */     
/*     */     public int a(EntityPlayer param1EntityPlayer, Iterable<Advancement> param1Iterable) {
/* 287 */       byte b = 0;
/* 288 */       for (Advancement advancement : param1Iterable) {
/* 289 */         if (a(param1EntityPlayer, advancement)) {
/* 290 */           b++;
/*     */         }
/*     */       } 
/* 293 */       return b;
/*     */     }
/*     */     
/*     */     protected abstract boolean a(EntityPlayer param1EntityPlayer, Advancement param1Advancement);
/*     */     
/*     */     protected abstract boolean a(EntityPlayer param1EntityPlayer, Advancement param1Advancement, String param1String);
/*     */   }
/*     */   
/*     */   enum Filter {
/* 302 */     ONLY("only", false, false),
/* 303 */     THROUGH("through", true, true),
/* 304 */     FROM("from", false, true),
/* 305 */     UNTIL("until", true, false),
/* 306 */     EVERYTHING("everything", true, true);
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
/* 321 */     static final String[] f = new String[(values()).length];
/* 322 */     final String g; final boolean h; static { for (byte b = 0; b < (values()).length; b++)
/* 323 */         f[b] = (values()[b]).g;  } final boolean i; Filter(String param1String1, boolean param1Boolean1, boolean param1Boolean2) {
/*     */       this.g = param1String1;
/*     */       this.h = param1Boolean1;
/*     */       this.i = param1Boolean2;
/*     */     } CommandException a(CommandAdvancement.Action param1Action, Object... param1VarArgs) {
/* 328 */       return new CommandException(param1Action.d + "." + this.g + ".failed", param1VarArgs);
/*     */     }
/*     */     
/*     */     CommandException a(CommandAdvancement.Action param1Action) {
/* 332 */       return new CommandException(param1Action.d + "." + this.g + ".usage", new Object[0]);
/*     */     }
/*     */     
/*     */     void a(ICommandListener param1ICommandListener, CommandAdvancement param1CommandAdvancement, CommandAdvancement.Action param1Action, Object... param1VarArgs) {
/* 336 */       CommandAbstract.a(param1ICommandListener, param1CommandAdvancement, param1Action.d + "." + this.g + ".success", param1VarArgs);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     static Filter a(String param1String) {
/* 341 */       for (Filter filter : values()) {
/* 342 */         if (filter.g.equals(param1String)) {
/* 343 */           return filter;
/*     */         }
/*     */       } 
/* 346 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandAdvancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */