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
/*     */ public class CommandPlaySound
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  22 */     return "playsound";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  27 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  32 */     return "commands.playsound.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  37 */     if (paramArrayOfString.length < 2) {
/*  38 */       throw new ExceptionUsage(getUsage(paramICommandListener), new Object[0]);
/*     */     }
/*     */     
/*  41 */     byte b = 0;
/*  42 */     String str1 = paramArrayOfString[b++];
/*  43 */     String str2 = paramArrayOfString[b++];
/*  44 */     SoundCategory soundCategory = SoundCategory.a(str2);
/*  45 */     if (soundCategory == null) {
/*  46 */       throw new CommandException("commands.playsound.unknownSoundSource", new Object[] { str2 });
/*     */     }
/*  48 */     EntityPlayer entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[b++]);
/*     */     
/*  50 */     Vec3D vec3D = paramICommandListener.d();
/*     */     
/*  52 */     double d1 = (paramArrayOfString.length > b) ? b(vec3D.x, paramArrayOfString[b++], true) : vec3D.x;
/*  53 */     double d2 = (paramArrayOfString.length > b) ? b(vec3D.y, paramArrayOfString[b++], 0, 0, false) : vec3D.y;
/*  54 */     double d3 = (paramArrayOfString.length > b) ? b(vec3D.z, paramArrayOfString[b++], true) : vec3D.z;
/*     */     
/*  56 */     double d4 = (paramArrayOfString.length > b) ? a(paramArrayOfString[b++], 0.0D, 3.4028234663852886E38D) : 1.0D;
/*  57 */     double d5 = (paramArrayOfString.length > b) ? a(paramArrayOfString[b++], 0.0D, 2.0D) : 1.0D;
/*  58 */     double d6 = (paramArrayOfString.length > b) ? a(paramArrayOfString[b], 0.0D, 1.0D) : 0.0D;
/*     */     
/*  60 */     double d7 = (d4 > 1.0D) ? (d4 * 16.0D) : 16.0D;
/*  61 */     double d8 = entityPlayer.e(d1, d2, d3);
/*     */     
/*  63 */     if (d8 > d7) {
/*  64 */       if (d6 <= 0.0D) {
/*  65 */         throw new CommandException("commands.playsound.playerTooFar", new Object[] { entityPlayer.getName() });
/*     */       }
/*     */       
/*  68 */       double d9 = d1 - entityPlayer.locX;
/*  69 */       double d10 = d2 - entityPlayer.locY;
/*  70 */       double d11 = d3 - entityPlayer.locZ;
/*  71 */       double d12 = Math.sqrt(d9 * d9 + d10 * d10 + d11 * d11);
/*     */       
/*  73 */       if (d12 > 0.0D) {
/*  74 */         d1 = entityPlayer.locX + d9 / d12 * 2.0D;
/*  75 */         d2 = entityPlayer.locY + d10 / d12 * 2.0D;
/*  76 */         d3 = entityPlayer.locZ + d11 / d12 * 2.0D;
/*     */       } 
/*     */       
/*  79 */       d4 = d6;
/*     */     } 
/*     */     
/*  82 */     entityPlayer.playerConnection.sendPacket(new PacketPlayOutCustomSoundEffect(str1, soundCategory, d1, d2, d3, (float)d4, (float)d5));
/*  83 */     a(paramICommandListener, this, "commands.playsound.success", new Object[] { str1, entityPlayer.getName() });
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/*  88 */     if (paramArrayOfString.length == 1)
/*  89 */       return a(paramArrayOfString, SoundEffect.a.keySet()); 
/*  90 */     if (paramArrayOfString.length == 2)
/*  91 */       return a(paramArrayOfString, SoundCategory.b()); 
/*  92 */     if (paramArrayOfString.length == 3)
/*  93 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/*  94 */     if (paramArrayOfString.length > 3 && paramArrayOfString.length <= 6) {
/*  95 */       return a(paramArrayOfString, 3, paramBlockPosition);
/*     */     }
/*     */     
/*  98 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 103 */     return (paramInt == 2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandPlaySound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */