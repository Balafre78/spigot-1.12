/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketPlayInArmAnimation
/*    */   implements Packet<PacketListenerPlayIn> {
/*    */   private EnumHand a;
/*    */   
/*    */   public PacketPlayInArmAnimation() {}
/*    */   
/*    */   public PacketPlayInArmAnimation(EnumHand enumhand) {
/* 12 */     this.a = enumhand;
/*    */   }
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 16 */     this.a = packetdataserializer.<EnumHand>a(EnumHand.class);
/*    */   }
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 20 */     packetdataserializer.a(this.a);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 24 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public EnumHand a() {
/* 28 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInArmAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */