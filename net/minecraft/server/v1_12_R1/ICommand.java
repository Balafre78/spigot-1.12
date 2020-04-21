package net.minecraft.server.v1_12_R1;

import java.util.List;
import javax.annotation.Nullable;

public interface ICommand extends Comparable<ICommand> {
  String getCommand();
  
  String getUsage(ICommandListener paramICommandListener);
  
  List<String> getAliases();
  
  void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException;
  
  boolean canUse(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener);
  
  List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition);
  
  boolean isListStart(String[] paramArrayOfString, int paramInt);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ICommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */