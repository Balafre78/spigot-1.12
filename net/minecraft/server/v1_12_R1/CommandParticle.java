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
/*     */ public class CommandParticle
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  22 */     return "particle";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  27 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  32 */     return "commands.particle.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     EntityPlayer entityPlayer;
/*  37 */     if (paramArrayOfString.length < 8) {
/*  38 */       throw new ExceptionUsage("commands.particle.usage", new Object[0]);
/*     */     }
/*     */     
/*  41 */     boolean bool1 = false;
/*  42 */     EnumParticle enumParticle = EnumParticle.a(paramArrayOfString[0]);
/*     */     
/*  44 */     if (enumParticle == null) {
/*  45 */       throw new CommandException("commands.particle.notFound", new Object[] { paramArrayOfString[0] });
/*     */     }
/*     */     
/*  48 */     String str = paramArrayOfString[0];
/*  49 */     Vec3D vec3D = paramICommandListener.d();
/*  50 */     double d1 = (float)b(vec3D.x, paramArrayOfString[1], true);
/*  51 */     double d2 = (float)b(vec3D.y, paramArrayOfString[2], true);
/*  52 */     double d3 = (float)b(vec3D.z, paramArrayOfString[3], true);
/*  53 */     double d4 = (float)c(paramArrayOfString[4]);
/*  54 */     double d5 = (float)c(paramArrayOfString[5]);
/*  55 */     double d6 = (float)c(paramArrayOfString[6]);
/*  56 */     double d7 = (float)c(paramArrayOfString[7]);
/*     */     
/*  58 */     int i = 0;
/*  59 */     if (paramArrayOfString.length > 8) {
/*  60 */       i = a(paramArrayOfString[8], 0);
/*     */     }
/*     */     
/*  63 */     boolean bool2 = false;
/*  64 */     if (paramArrayOfString.length > 9 && "force".equals(paramArrayOfString[9])) {
/*  65 */       bool2 = true;
/*     */     }
/*     */ 
/*     */     
/*  69 */     if (paramArrayOfString.length > 10) {
/*  70 */       entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[10]);
/*     */     } else {
/*  72 */       entityPlayer = null;
/*     */     } 
/*     */     
/*  75 */     int[] arrayOfInt = new int[enumParticle.d()];
/*  76 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/*  77 */       if (paramArrayOfString.length > 11 + b) {
/*     */         try {
/*  79 */           arrayOfInt[b] = Integer.parseInt(paramArrayOfString[11 + b]);
/*  80 */         } catch (NumberFormatException numberFormatException) {
/*  81 */           throw new CommandException("commands.particle.invalidParam", new Object[] { paramArrayOfString[11 + b] });
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  86 */     World world = paramICommandListener.getWorld();
/*  87 */     if (world instanceof WorldServer) {
/*  88 */       WorldServer worldServer = (WorldServer)world;
/*  89 */       if (entityPlayer == null) {
/*  90 */         worldServer.a(enumParticle, bool2, d1, d2, d3, i, d4, d5, d6, d7, arrayOfInt);
/*     */       } else {
/*  92 */         worldServer.a(entityPlayer, enumParticle, bool2, d1, d2, d3, i, d4, d5, d6, d7, arrayOfInt);
/*     */       } 
/*  94 */       a(paramICommandListener, this, "commands.particle.success", new Object[] { str, Integer.valueOf(Math.max(i, 1)) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 100 */     if (paramArrayOfString.length == 1)
/* 101 */       return a(paramArrayOfString, EnumParticle.a()); 
/* 102 */     if (paramArrayOfString.length > 1 && paramArrayOfString.length <= 4)
/* 103 */       return a(paramArrayOfString, 1, paramBlockPosition); 
/* 104 */     if (paramArrayOfString.length == 10)
/* 105 */       return a(paramArrayOfString, new String[] { "normal", "force" }); 
/* 106 */     if (paramArrayOfString.length == 11) {
/* 107 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */     }
/* 109 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 114 */     return (paramInt == 10);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */