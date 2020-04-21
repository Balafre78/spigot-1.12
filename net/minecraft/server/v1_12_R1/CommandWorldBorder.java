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
/*     */ public class CommandWorldBorder
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  21 */     return "worldborder";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  26 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  31 */     return "commands.worldborder.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  36 */     if (paramArrayOfString.length < 1) {
/*  37 */       throw new ExceptionUsage("commands.worldborder.usage", new Object[0]);
/*     */     }
/*     */     
/*  40 */     WorldBorder worldBorder = a(paramMinecraftServer);
/*  41 */     if ("set".equals(paramArrayOfString[0])) {
/*  42 */       if (paramArrayOfString.length != 2 && paramArrayOfString.length != 3) {
/*  43 */         throw new ExceptionUsage("commands.worldborder.set.usage", new Object[0]);
/*     */       }
/*     */       
/*  46 */       double d1 = worldBorder.j();
/*  47 */       double d2 = a(paramArrayOfString[1], 1.0D, 6.0E7D);
/*  48 */       long l = (paramArrayOfString.length > 2) ? (a(paramArrayOfString[2], 0L, 9223372036854775L) * 1000L) : 0L;
/*     */       
/*  50 */       if (l > 0L) {
/*  51 */         worldBorder.transitionSizeBetween(d1, d2, l);
/*  52 */         if (d1 > d2) {
/*  53 */           a(paramICommandListener, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d2) }), String.format("%.1f", new Object[] { Double.valueOf(d1) }), Long.toString(l / 1000L) });
/*     */         } else {
/*  55 */           a(paramICommandListener, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d2) }), String.format("%.1f", new Object[] { Double.valueOf(d1) }), Long.toString(l / 1000L) });
/*     */         } 
/*     */       } else {
/*  58 */         worldBorder.setSize(d2);
/*  59 */         a(paramICommandListener, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d2) }), String.format("%.1f", new Object[] { Double.valueOf(d1) }) });
/*     */       } 
/*  61 */     } else if ("add".equals(paramArrayOfString[0])) {
/*  62 */       if (paramArrayOfString.length != 2 && paramArrayOfString.length != 3) {
/*  63 */         throw new ExceptionUsage("commands.worldborder.add.usage", new Object[0]);
/*     */       }
/*     */       
/*  66 */       double d1 = worldBorder.getSize();
/*  67 */       double d2 = d1 + a(paramArrayOfString[1], -d1, 6.0E7D - d1);
/*  68 */       long l = worldBorder.i() + ((paramArrayOfString.length > 2) ? (a(paramArrayOfString[2], 0L, 9223372036854775L) * 1000L) : 0L);
/*     */       
/*  70 */       if (l > 0L) {
/*  71 */         worldBorder.transitionSizeBetween(d1, d2, l);
/*  72 */         if (d1 > d2) {
/*  73 */           a(paramICommandListener, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d2) }), String.format("%.1f", new Object[] { Double.valueOf(d1) }), Long.toString(l / 1000L) });
/*     */         } else {
/*  75 */           a(paramICommandListener, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d2) }), String.format("%.1f", new Object[] { Double.valueOf(d1) }), Long.toString(l / 1000L) });
/*     */         } 
/*     */       } else {
/*  78 */         worldBorder.setSize(d2);
/*  79 */         a(paramICommandListener, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d2) }), String.format("%.1f", new Object[] { Double.valueOf(d1) }) });
/*     */       } 
/*  81 */     } else if ("center".equals(paramArrayOfString[0])) {
/*  82 */       if (paramArrayOfString.length != 3) {
/*  83 */         throw new ExceptionUsage("commands.worldborder.center.usage", new Object[0]);
/*     */       }
/*     */       
/*  86 */       BlockPosition blockPosition = paramICommandListener.getChunkCoordinates();
/*  87 */       double d1 = b(blockPosition.getX() + 0.5D, paramArrayOfString[1], true);
/*  88 */       double d2 = b(blockPosition.getZ() + 0.5D, paramArrayOfString[2], true);
/*     */       
/*  90 */       worldBorder.setCenter(d1, d2);
/*  91 */       a(paramICommandListener, this, "commands.worldborder.center.success", new Object[] { Double.valueOf(d1), Double.valueOf(d2) });
/*  92 */     } else if ("damage".equals(paramArrayOfString[0])) {
/*  93 */       if (paramArrayOfString.length < 2) {
/*  94 */         throw new ExceptionUsage("commands.worldborder.damage.usage", new Object[0]);
/*     */       }
/*     */       
/*  97 */       if ("buffer".equals(paramArrayOfString[1])) {
/*  98 */         if (paramArrayOfString.length != 3) {
/*  99 */           throw new ExceptionUsage("commands.worldborder.damage.buffer.usage", new Object[0]);
/*     */         }
/*     */         
/* 102 */         double d1 = a(paramArrayOfString[2], 0.0D);
/* 103 */         double d2 = worldBorder.getDamageBuffer();
/* 104 */         worldBorder.setDamageBuffer(d1);
/* 105 */         a(paramICommandListener, this, "commands.worldborder.damage.buffer.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d1) }), String.format("%.1f", new Object[] { Double.valueOf(d2) }) });
/* 106 */       } else if ("amount".equals(paramArrayOfString[1])) {
/* 107 */         if (paramArrayOfString.length != 3) {
/* 108 */           throw new ExceptionUsage("commands.worldborder.damage.amount.usage", new Object[0]);
/*     */         }
/*     */         
/* 111 */         double d1 = a(paramArrayOfString[2], 0.0D);
/* 112 */         double d2 = worldBorder.getDamageAmount();
/* 113 */         worldBorder.setDamageAmount(d1);
/* 114 */         a(paramICommandListener, this, "commands.worldborder.damage.amount.success", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d1) }), String.format("%.2f", new Object[] { Double.valueOf(d2) }) });
/*     */       } 
/* 116 */     } else if ("warning".equals(paramArrayOfString[0])) {
/* 117 */       if (paramArrayOfString.length < 2) {
/* 118 */         throw new ExceptionUsage("commands.worldborder.warning.usage", new Object[0]);
/*     */       }
/*     */       
/* 121 */       if ("time".equals(paramArrayOfString[1])) {
/* 122 */         if (paramArrayOfString.length != 3) {
/* 123 */           throw new ExceptionUsage("commands.worldborder.warning.time.usage", new Object[0]);
/*     */         }
/*     */         
/* 126 */         int i = a(paramArrayOfString[2], 0);
/* 127 */         int j = worldBorder.getWarningTime();
/* 128 */         worldBorder.setWarningTime(i);
/* 129 */         a(paramICommandListener, this, "commands.worldborder.warning.time.success", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/* 130 */       } else if ("distance".equals(paramArrayOfString[1])) {
/* 131 */         if (paramArrayOfString.length != 3) {
/* 132 */           throw new ExceptionUsage("commands.worldborder.warning.distance.usage", new Object[0]);
/*     */         }
/*     */         
/* 135 */         int i = a(paramArrayOfString[2], 0);
/* 136 */         int j = worldBorder.getWarningDistance();
/* 137 */         worldBorder.setWarningDistance(i);
/* 138 */         a(paramICommandListener, this, "commands.worldborder.warning.distance.success", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       } 
/* 140 */     } else if ("get".equals(paramArrayOfString[0])) {
/* 141 */       double d = worldBorder.getSize();
/* 142 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, MathHelper.floor(d + 0.5D));
/* 143 */       paramICommandListener.sendMessage(new ChatMessage("commands.worldborder.get.success", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(d) }) }));
/*     */     } else {
/* 145 */       throw new ExceptionUsage("commands.worldborder.usage", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected WorldBorder a(MinecraftServer paramMinecraftServer) {
/* 150 */     return paramMinecraftServer.worldServer[0].getWorldBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 155 */     if (paramArrayOfString.length == 1) {
/* 156 */       return a(paramArrayOfString, new String[] { "set", "center", "damage", "warning", "add", "get" });
/*     */     }
/*     */     
/* 159 */     if (paramArrayOfString.length == 2 && "damage".equals(paramArrayOfString[0]))
/* 160 */       return a(paramArrayOfString, new String[] { "buffer", "amount" }); 
/* 161 */     if (paramArrayOfString.length >= 2 && paramArrayOfString.length <= 3 && "center".equals(paramArrayOfString[0]))
/* 162 */       return b(paramArrayOfString, 1, paramBlockPosition); 
/* 163 */     if (paramArrayOfString.length == 2 && "warning".equals(paramArrayOfString[0])) {
/* 164 */       return a(paramArrayOfString, new String[] { "time", "distance" });
/*     */     }
/*     */     
/* 167 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandWorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */