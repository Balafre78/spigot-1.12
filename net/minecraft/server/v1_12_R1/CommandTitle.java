/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class CommandTitle
/*     */   extends CommandAbstract
/*     */ {
/*  22 */   private static final Logger a = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommand() {
/*  27 */     return "title";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  32 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  37 */     return "commands.title.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     IChatBaseComponent iChatBaseComponent;
/*  42 */     if (paramArrayOfString.length < 2) {
/*  43 */       throw new ExceptionUsage("commands.title.usage", new Object[0]);
/*     */     }
/*  45 */     if (paramArrayOfString.length < 3) {
/*  46 */       if ("title".equals(paramArrayOfString[1]) || "subtitle".equals(paramArrayOfString[1]) || "actionbar".equals(paramArrayOfString[1]))
/*  47 */         throw new ExceptionUsage("commands.title.usage.title", new Object[0]); 
/*  48 */       if ("times".equals(paramArrayOfString[1])) {
/*  49 */         throw new ExceptionUsage("commands.title.usage.times", new Object[0]);
/*     */       }
/*     */     } 
/*     */     
/*  53 */     EntityPlayer entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/*  54 */     PacketPlayOutTitle.EnumTitleAction enumTitleAction = PacketPlayOutTitle.EnumTitleAction.a(paramArrayOfString[1]);
/*     */     
/*  56 */     if (enumTitleAction == PacketPlayOutTitle.EnumTitleAction.CLEAR || enumTitleAction == PacketPlayOutTitle.EnumTitleAction.RESET) {
/*  57 */       if (paramArrayOfString.length != 2) {
/*  58 */         throw new ExceptionUsage("commands.title.usage", new Object[0]);
/*     */       }
/*  60 */       PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(enumTitleAction, null);
/*  61 */       entityPlayer.playerConnection.sendPacket(packetPlayOutTitle1);
/*  62 */       a(paramICommandListener, this, "commands.title.success", new Object[0]);
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     if (enumTitleAction == PacketPlayOutTitle.EnumTitleAction.TIMES) {
/*  67 */       if (paramArrayOfString.length != 5) {
/*  68 */         throw new ExceptionUsage("commands.title.usage", new Object[0]);
/*     */       }
/*  70 */       int i = a(paramArrayOfString[2]);
/*  71 */       int j = a(paramArrayOfString[3]);
/*  72 */       int k = a(paramArrayOfString[4]);
/*  73 */       PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(i, j, k);
/*  74 */       entityPlayer.playerConnection.sendPacket(packetPlayOutTitle1);
/*  75 */       a(paramICommandListener, this, "commands.title.success", new Object[0]);
/*     */       
/*     */       return;
/*     */     } 
/*  79 */     if (paramArrayOfString.length < 3) {
/*  80 */       throw new ExceptionUsage("commands.title.usage", new Object[0]);
/*     */     }
/*     */     
/*  83 */     String str = a(paramArrayOfString, 2);
/*     */     
/*     */     try {
/*  86 */       iChatBaseComponent = IChatBaseComponent.ChatSerializer.a(str);
/*  87 */     } catch (JsonParseException jsonParseException) {
/*  88 */       throw a(jsonParseException);
/*     */     } 
/*     */     
/*  91 */     PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(enumTitleAction, ChatComponentUtils.filterForDisplay(paramICommandListener, iChatBaseComponent, entityPlayer));
/*  92 */     entityPlayer.playerConnection.sendPacket(packetPlayOutTitle);
/*  93 */     a(paramICommandListener, this, "commands.title.success", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/*  98 */     if (paramArrayOfString.length == 1) {
/*  99 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*     */     }
/* 101 */     if (paramArrayOfString.length == 2) {
/* 102 */       return a(paramArrayOfString, PacketPlayOutTitle.EnumTitleAction.a());
/*     */     }
/* 104 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 109 */     return (paramInt == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */