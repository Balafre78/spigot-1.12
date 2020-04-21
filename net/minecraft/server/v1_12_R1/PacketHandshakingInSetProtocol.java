/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ public class PacketHandshakingInSetProtocol
/*    */   implements Packet<PacketHandshakingInListener>
/*    */ {
/*    */   private int a;
/*    */   public String hostname;
/*    */   public int port;
/*    */   private EnumProtocol d;
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 15 */     this.a = packetdataserializer.g();
/* 16 */     this.hostname = packetdataserializer.e(32767);
/* 17 */     this.port = packetdataserializer.readUnsignedShort();
/* 18 */     this.d = EnumProtocol.a(packetdataserializer.g());
/*    */   }
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 22 */     packetdataserializer.d(this.a);
/* 23 */     packetdataserializer.a(this.hostname);
/* 24 */     packetdataserializer.writeShort(this.port);
/* 25 */     packetdataserializer.d(this.d.a());
/*    */   }
/*    */   
/*    */   public void a(PacketHandshakingInListener packethandshakinginlistener) {
/* 29 */     packethandshakinginlistener.a(this);
/*    */   }
/*    */   
/*    */   public EnumProtocol a() {
/* 33 */     return this.d;
/*    */   }
/*    */   
/*    */   public int b() {
/* 37 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketHandshakingInSetProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */