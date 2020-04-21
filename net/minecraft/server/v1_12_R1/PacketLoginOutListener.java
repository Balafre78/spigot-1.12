package net.minecraft.server.v1_12_R1;

public interface PacketLoginOutListener extends PacketListener {
  void a(PacketLoginOutEncryptionBegin paramPacketLoginOutEncryptionBegin);
  
  void a(PacketLoginOutSuccess paramPacketLoginOutSuccess);
  
  void a(PacketLoginOutDisconnect paramPacketLoginOutDisconnect);
  
  void a(PacketLoginOutSetCompression paramPacketLoginOutSetCompression);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketLoginOutListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */