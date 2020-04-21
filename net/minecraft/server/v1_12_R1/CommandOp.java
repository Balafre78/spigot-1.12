/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandOp
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 19 */     return "op";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 24 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 29 */     return "commands.op.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 34 */     if (paramArrayOfString.length != 1 || paramArrayOfString[0].length() <= 0) {
/* 35 */       throw new ExceptionUsage("commands.op.usage", new Object[0]);
/*    */     }
/*    */     
/* 38 */     GameProfile gameProfile = paramMinecraftServer.getUserCache().getProfile(paramArrayOfString[0]);
/* 39 */     if (gameProfile == null) {
/* 40 */       throw new CommandException("commands.op.failed", new Object[] { paramArrayOfString[0] });
/*    */     }
/*    */     
/* 43 */     paramMinecraftServer.getPlayerList().addOp(gameProfile);
/* 44 */     a(paramICommandListener, this, "commands.op.success", new Object[] { paramArrayOfString[0] });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 49 */     if (paramArrayOfString.length == 1) {
/* 50 */       String str = paramArrayOfString[paramArrayOfString.length - 1];
/* 51 */       ArrayList<String> arrayList = Lists.newArrayList();
/*    */       
/* 53 */       for (GameProfile gameProfile : paramMinecraftServer.K()) {
/* 54 */         if (!paramMinecraftServer.getPlayerList().isOp(gameProfile) && a(str, gameProfile.getName())) {
/* 55 */           arrayList.add(gameProfile.getName());
/*    */         }
/*    */       } 
/*    */       
/* 59 */       return arrayList;
/*    */     } 
/*    */     
/* 62 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandOp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */