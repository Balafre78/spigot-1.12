/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketPlayInBlockPlace
/*    */   implements Packet<PacketListenerPlayIn> {
/*    */   private EnumHand a;
/*    */   public long timestamp;
/*    */   
/*    */   public PacketPlayInBlockPlace() {}
/*    */   
/*    */   public PacketPlayInBlockPlace(EnumHand enumhand) {
/* 13 */     this.a = enumhand;
/*    */   }
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 17 */     this.timestamp = System.currentTimeMillis();
/* 18 */     this.a = packetdataserializer.<EnumHand>a(EnumHand.class);
/*    */   }
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 22 */     packetdataserializer.a(this.a);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 26 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public EnumHand a() {
/* 30 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInBlockPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */