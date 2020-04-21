/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ public class CommandStats
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  26 */     return "stats";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  31 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  36 */     return "commands.stats.usage";
/*     */   } public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     boolean bool;
/*     */     byte b;
/*     */     CommandObjectiveExecutor commandObjectiveExecutor;
/*  41 */     if (paramArrayOfString.length < 1) {
/*  42 */       throw new ExceptionUsage("commands.stats.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  46 */     if ("entity".equals(paramArrayOfString[0])) {
/*  47 */       bool = false;
/*  48 */     } else if ("block".equals(paramArrayOfString[0])) {
/*  49 */       bool = true;
/*     */     } else {
/*  51 */       throw new ExceptionUsage("commands.stats.usage", new Object[0]);
/*     */     } 
/*     */ 
/*     */     
/*  55 */     if (bool) {
/*  56 */       if (paramArrayOfString.length < 5) {
/*  57 */         throw new ExceptionUsage("commands.stats.block.usage", new Object[0]);
/*     */       }
/*  59 */       b = 4;
/*     */     } else {
/*  61 */       if (paramArrayOfString.length < 3) {
/*  62 */         throw new ExceptionUsage("commands.stats.entity.usage", new Object[0]);
/*     */       }
/*  64 */       b = 2;
/*     */     } 
/*     */     
/*  67 */     String str = paramArrayOfString[b++];
/*  68 */     if ("set".equals(str)) {
/*  69 */       if (paramArrayOfString.length < b + 3) {
/*  70 */         if (b == 5) {
/*  71 */           throw new ExceptionUsage("commands.stats.block.set.usage", new Object[0]);
/*     */         }
/*  73 */         throw new ExceptionUsage("commands.stats.entity.set.usage", new Object[0]);
/*     */       } 
/*  75 */     } else if ("clear".equals(str)) {
/*  76 */       if (paramArrayOfString.length < b + 1) {
/*  77 */         if (b == 5) {
/*  78 */           throw new ExceptionUsage("commands.stats.block.clear.usage", new Object[0]);
/*     */         }
/*  80 */         throw new ExceptionUsage("commands.stats.entity.clear.usage", new Object[0]);
/*     */       } 
/*     */     } else {
/*  83 */       throw new ExceptionUsage("commands.stats.usage", new Object[0]);
/*     */     } 
/*     */     
/*  86 */     CommandObjectiveExecutor.EnumCommandResult enumCommandResult = CommandObjectiveExecutor.EnumCommandResult.a(paramArrayOfString[b++]);
/*     */     
/*  88 */     if (enumCommandResult == null) {
/*  89 */       throw new CommandException("commands.stats.failed", new Object[0]);
/*     */     }
/*     */     
/*  92 */     World world = paramICommandListener.getWorld();
/*     */     
/*  94 */     if (bool) {
/*  95 */       BlockPosition blockPosition = a(paramICommandListener, paramArrayOfString, 1, false);
/*  96 */       TileEntity tileEntity = world.getTileEntity(blockPosition);
/*     */       
/*  98 */       if (tileEntity == null) {
/*  99 */         throw new CommandException("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*     */       }
/*     */       
/* 102 */       if (tileEntity instanceof TileEntityCommand) {
/* 103 */         commandObjectiveExecutor = ((TileEntityCommand)tileEntity).e();
/* 104 */       } else if (tileEntity instanceof TileEntitySign) {
/* 105 */         commandObjectiveExecutor = ((TileEntitySign)tileEntity).f();
/*     */       } else {
/* 107 */         throw new CommandException("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*     */       } 
/*     */     } else {
/* 110 */       Entity entity = c(paramMinecraftServer, paramICommandListener, paramArrayOfString[1]);
/* 111 */       commandObjectiveExecutor = entity.bA();
/*     */     } 
/*     */     
/* 114 */     if ("set".equals(str)) {
/* 115 */       String str1 = paramArrayOfString[b++];
/* 116 */       String str2 = paramArrayOfString[b];
/* 117 */       if (str1.isEmpty() || str2.isEmpty()) {
/* 118 */         throw new CommandException("commands.stats.failed", new Object[0]);
/*     */       }
/* 120 */       CommandObjectiveExecutor.a(commandObjectiveExecutor, enumCommandResult, str1, str2);
/* 121 */       a(paramICommandListener, this, "commands.stats.success", new Object[] { enumCommandResult.b(), str2, str1 });
/* 122 */     } else if ("clear".equals(str)) {
/* 123 */       CommandObjectiveExecutor.a(commandObjectiveExecutor, enumCommandResult, (String)null, (String)null);
/* 124 */       a(paramICommandListener, this, "commands.stats.cleared", new Object[] { enumCommandResult.b() });
/*     */     } 
/*     */     
/* 127 */     if (bool) {
/* 128 */       BlockPosition blockPosition = a(paramICommandListener, paramArrayOfString, 1, false);
/* 129 */       TileEntity tileEntity = world.getTileEntity(blockPosition);
/* 130 */       tileEntity.update();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 136 */     if (paramArrayOfString.length == 1) {
/* 137 */       return a(paramArrayOfString, new String[] { "entity", "block" });
/*     */     }
/* 139 */     if (paramArrayOfString.length == 2 && "entity".equals(paramArrayOfString[0])) {
/* 140 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */     }
/* 142 */     if (paramArrayOfString.length >= 2 && paramArrayOfString.length <= 4 && "block".equals(paramArrayOfString[0])) {
/* 143 */       return a(paramArrayOfString, 1, paramBlockPosition);
/*     */     }
/* 145 */     if ((paramArrayOfString.length == 3 && "entity".equals(paramArrayOfString[0])) || (paramArrayOfString.length == 5 && "block".equals(paramArrayOfString[0]))) {
/* 146 */       return a(paramArrayOfString, new String[] { "set", "clear" });
/*     */     }
/* 148 */     if ((paramArrayOfString.length == 4 && "entity".equals(paramArrayOfString[0])) || (paramArrayOfString.length == 6 && "block".equals(paramArrayOfString[0]))) {
/* 149 */       return a(paramArrayOfString, CommandObjectiveExecutor.EnumCommandResult.c());
/*     */     }
/* 151 */     if ((paramArrayOfString.length == 6 && "entity".equals(paramArrayOfString[0])) || (paramArrayOfString.length == 8 && "block".equals(paramArrayOfString[0]))) {
/* 152 */       return a(paramArrayOfString, a(paramMinecraftServer));
/*     */     }
/*     */     
/* 155 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   protected List<String> a(MinecraftServer paramMinecraftServer) {
/* 159 */     Collection<ScoreboardObjective> collection = paramMinecraftServer.getWorldServer(0).getScoreboard().getObjectives();
/* 160 */     ArrayList<String> arrayList = Lists.newArrayList();
/*     */     
/* 162 */     for (ScoreboardObjective scoreboardObjective : collection) {
/* 163 */       if (!scoreboardObjective.getCriteria().isReadOnly()) {
/* 164 */         arrayList.add(scoreboardObjective.getName());
/*     */       }
/*     */     } 
/*     */     
/* 168 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 173 */     return (paramArrayOfString.length > 0 && "entity".equals(paramArrayOfString[0]) && paramInt == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */