/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
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
/*    */ public class CommandDifficulty
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 20 */     return "difficulty";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 25 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 30 */     return "commands.difficulty.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 35 */     if (paramArrayOfString.length <= 0) {
/* 36 */       throw new ExceptionUsage("commands.difficulty.usage", new Object[0]);
/*    */     }
/*    */     
/* 39 */     EnumDifficulty enumDifficulty = e(paramArrayOfString[0]);
/* 40 */     paramMinecraftServer.a(enumDifficulty);
/*    */     
/* 42 */     a(paramICommandListener, this, "commands.difficulty.success", new Object[] { new ChatMessage(enumDifficulty.b(), new Object[0]) });
/*    */   }
/*    */   
/*    */   protected EnumDifficulty e(String paramString) throws ExceptionInvalidNumber {
/* 46 */     if ("peaceful".equalsIgnoreCase(paramString) || "p".equalsIgnoreCase(paramString))
/* 47 */       return EnumDifficulty.PEACEFUL; 
/* 48 */     if ("easy".equalsIgnoreCase(paramString) || "e".equalsIgnoreCase(paramString))
/* 49 */       return EnumDifficulty.EASY; 
/* 50 */     if ("normal".equalsIgnoreCase(paramString) || "n".equalsIgnoreCase(paramString))
/* 51 */       return EnumDifficulty.NORMAL; 
/* 52 */     if ("hard".equalsIgnoreCase(paramString) || "h".equalsIgnoreCase(paramString)) {
/* 53 */       return EnumDifficulty.HARD;
/*    */     }
/* 55 */     return EnumDifficulty.getById(a(paramString, 0, 3));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 61 */     if (paramArrayOfString.length == 1) {
/* 62 */       return a(paramArrayOfString, new String[] { "peaceful", "easy", "normal", "hard" });
/*    */     }
/*    */     
/* 65 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandDifficulty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */