package net.minecraft.server.v1_12_R1;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public interface ICommandHandler {
  int a(ICommandListener paramICommandListener, String paramString);
  
  List<String> a(ICommandListener paramICommandListener, String paramString, @Nullable BlockPosition paramBlockPosition);
  
  List<ICommand> a(ICommandListener paramICommandListener);
  
  Map<String, ICommand> getCommands();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ICommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */