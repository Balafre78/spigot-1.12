package net.minecraft.server.v1_12_R1;

public interface IMinecraftServer {
  int a(String paramString, int paramInt);
  
  String a(String paramString1, String paramString2);
  
  void a(String paramString, Object paramObject);
  
  void a();
  
  String b();
  
  String d_();
  
  int e_();
  
  String f_();
  
  String getVersion();
  
  int H();
  
  int I();
  
  String[] getPlayers();
  
  String S();
  
  String getPlugins();
  
  String executeRemoteCommand(String paramString);
  
  boolean isDebugging();
  
  void info(String paramString);
  
  void warning(String paramString);
  
  void g(String paramString);
  
  void h(String paramString);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IMinecraftServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */