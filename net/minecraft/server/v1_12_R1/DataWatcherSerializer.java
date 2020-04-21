package net.minecraft.server.v1_12_R1;

public interface DataWatcherSerializer<T> {
  void a(PacketDataSerializer paramPacketDataSerializer, T paramT);
  
  T a(PacketDataSerializer paramPacketDataSerializer);
  
  DataWatcherObject<T> a(int paramInt);
  
  T a(T paramT);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataWatcherSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */