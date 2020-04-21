/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ public class PacketPlayInUseItem
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private EnumDirection b;
/*    */   private EnumHand c;
/*    */   private float d;
/*    */   private float e;
/*    */   private float f;
/*    */   public long timestamp;
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 18 */     this.timestamp = System.currentTimeMillis();
/* 19 */     this.a = packetdataserializer.e();
/* 20 */     this.b = packetdataserializer.<EnumDirection>a(EnumDirection.class);
/* 21 */     this.c = packetdataserializer.<EnumHand>a(EnumHand.class);
/* 22 */     this.d = packetdataserializer.readFloat();
/* 23 */     this.e = packetdataserializer.readFloat();
/* 24 */     this.f = packetdataserializer.readFloat();
/*    */   }
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 28 */     packetdataserializer.a(this.a);
/* 29 */     packetdataserializer.a(this.b);
/* 30 */     packetdataserializer.a(this.c);
/* 31 */     packetdataserializer.writeFloat(this.d);
/* 32 */     packetdataserializer.writeFloat(this.e);
/* 33 */     packetdataserializer.writeFloat(this.f);
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayIn packetlistenerplayin) {
/* 37 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition a() {
/* 41 */     return this.a;
/*    */   }
/*    */   
/*    */   public EnumDirection b() {
/* 45 */     return this.b;
/*    */   }
/*    */   
/*    */   public EnumHand c() {
/* 49 */     return this.c;
/*    */   }
/*    */   
/*    */   public float d() {
/* 53 */     return this.d;
/*    */   }
/*    */   
/*    */   public float e() {
/* 57 */     return this.e;
/*    */   }
/*    */   
/*    */   public float f() {
/* 61 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInUseItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */