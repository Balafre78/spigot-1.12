/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Random;
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
/*    */ public class CommandWeather
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 21 */     return "weather";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 26 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 31 */     return "commands.weather.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 36 */     if (paramArrayOfString.length < 1 || paramArrayOfString.length > 2) {
/* 37 */       throw new ExceptionUsage("commands.weather.usage", new Object[0]);
/*    */     }
/*    */     
/* 40 */     int i = (300 + (new Random()).nextInt(600)) * 20;
/* 41 */     if (paramArrayOfString.length >= 2) {
/* 42 */       i = a(paramArrayOfString[1], 1, 1000000) * 20;
/*    */     }
/*    */     
/* 45 */     WorldServer worldServer = paramMinecraftServer.worldServer[0];
/* 46 */     WorldData worldData = worldServer.getWorldData();
/*    */     
/* 48 */     if ("clear".equalsIgnoreCase(paramArrayOfString[0])) {
/* 49 */       worldData.i(i);
/* 50 */       worldData.setWeatherDuration(0);
/* 51 */       worldData.setThunderDuration(0);
/* 52 */       worldData.setStorm(false);
/* 53 */       worldData.setThundering(false);
/* 54 */       a(paramICommandListener, this, "commands.weather.clear", new Object[0]);
/* 55 */     } else if ("rain".equalsIgnoreCase(paramArrayOfString[0])) {
/* 56 */       worldData.i(0);
/* 57 */       worldData.setWeatherDuration(i);
/* 58 */       worldData.setThunderDuration(i);
/* 59 */       worldData.setStorm(true);
/* 60 */       worldData.setThundering(false);
/* 61 */       a(paramICommandListener, this, "commands.weather.rain", new Object[0]);
/* 62 */     } else if ("thunder".equalsIgnoreCase(paramArrayOfString[0])) {
/* 63 */       worldData.i(0);
/* 64 */       worldData.setWeatherDuration(i);
/* 65 */       worldData.setThunderDuration(i);
/* 66 */       worldData.setStorm(true);
/* 67 */       worldData.setThundering(true);
/* 68 */       a(paramICommandListener, this, "commands.weather.thunder", new Object[0]);
/*    */     } else {
/* 70 */       throw new ExceptionUsage("commands.weather.usage", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 76 */     if (paramArrayOfString.length == 1) {
/* 77 */       return a(paramArrayOfString, new String[] { "clear", "rain", "thunder" });
/*    */     }
/*    */     
/* 80 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandWeather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */