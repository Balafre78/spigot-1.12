package net.minecraft.server.v1_12_R1;

import java.io.IOException;

public interface Packet<T extends PacketListener> {
  void a(PacketDataSerializer paramPacketDataSerializer) throws IOException;
  
  void b(PacketDataSerializer paramPacketDataSerializer) throws IOException;
  
  void a(T paramT);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Packet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */