/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonParseException;
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
/*    */ public class CommandTellRaw
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 21 */     return "tellraw";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 26 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 31 */     return "commands.tellraw.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 36 */     if (paramArrayOfString.length < 2) {
/* 37 */       throw new ExceptionUsage("commands.tellraw.usage", new Object[0]);
/*    */     }
/*    */     
/* 40 */     EntityPlayer entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/* 41 */     String str = a(paramArrayOfString, 1);
/*    */     
/*    */     try {
/* 44 */       IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a(str);
/* 45 */       entityPlayer.sendMessage(ChatComponentUtils.filterForDisplay(paramICommandListener, iChatBaseComponent, entityPlayer));
/* 46 */     } catch (JsonParseException jsonParseException) {
/* 47 */       throw a(jsonParseException);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 53 */     if (paramArrayOfString.length == 1) {
/* 54 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/*    */     
/* 57 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 62 */     return (paramInt == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTellRaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */