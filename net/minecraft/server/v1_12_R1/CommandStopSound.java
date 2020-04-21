/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import io.netty.buffer.Unpooled;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandStopSound
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 23 */     return "stopsound";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 28 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 33 */     return "commands.stopsound.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 38 */     if (paramArrayOfString.length < 1 || paramArrayOfString.length > 3) {
/* 39 */       throw new ExceptionUsage(getUsage(paramICommandListener), new Object[0]);
/*    */     }
/*    */     
/* 42 */     byte b = 0;
/* 43 */     EntityPlayer entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[b++]);
/*    */     
/* 45 */     String str1 = "";
/* 46 */     String str2 = "";
/*    */     
/* 48 */     if (paramArrayOfString.length >= 2) {
/* 49 */       String str = paramArrayOfString[b++];
/* 50 */       SoundCategory soundCategory = SoundCategory.a(str);
/*    */       
/* 52 */       if (soundCategory == null) {
/* 53 */         throw new CommandException("commands.stopsound.unknownSoundSource", new Object[] { str });
/*    */       }
/*    */       
/* 56 */       str1 = soundCategory.a();
/*    */     } 
/*    */     
/* 59 */     if (paramArrayOfString.length == 3) {
/* 60 */       str2 = paramArrayOfString[b++];
/*    */     }
/*    */     
/* 63 */     PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
/* 64 */     packetDataSerializer.a(str1);
/* 65 */     packetDataSerializer.a(str2);
/*    */     
/* 67 */     entityPlayer.playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|StopSound", packetDataSerializer));
/*    */     
/* 69 */     if (str1.isEmpty() && str2.isEmpty()) {
/* 70 */       a(paramICommandListener, this, "commands.stopsound.success.all", new Object[] { entityPlayer.getName() });
/* 71 */     } else if (str2.isEmpty()) {
/* 72 */       a(paramICommandListener, this, "commands.stopsound.success.soundSource", new Object[] { str1, entityPlayer.getName() });
/*    */     } else {
/* 74 */       a(paramICommandListener, this, "commands.stopsound.success.individualSound", new Object[] { str2, str1, entityPlayer.getName() });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 80 */     if (paramArrayOfString.length == 1)
/* 81 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers()); 
/* 82 */     if (paramArrayOfString.length == 2)
/* 83 */       return a(paramArrayOfString, SoundCategory.b()); 
/* 84 */     if (paramArrayOfString.length == 3) {
/* 85 */       return a(paramArrayOfString, SoundEffect.a.keySet());
/*    */     }
/*    */     
/* 88 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 93 */     return (paramInt == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandStopSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */