package net.minecraft.server.v1_12_R1;

public interface ITileEntityContainer extends INamableTileEntity {
  Container createContainer(PlayerInventory paramPlayerInventory, EntityHuman paramEntityHuman);
  
  String getContainerName();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ITileEntityContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */