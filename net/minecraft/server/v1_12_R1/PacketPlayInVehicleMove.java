/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInVehicleMove
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private double a;
/*    */   private double b;
/*    */   private double c;
/*    */   private float d;
/*    */   private float e;
/*    */   
/*    */   public PacketPlayInVehicleMove() {}
/*    */   
/*    */   public PacketPlayInVehicleMove(Entity paramEntity) {
/* 21 */     this.a = paramEntity.locX;
/* 22 */     this.b = paramEntity.locY;
/* 23 */     this.c = paramEntity.locZ;
/* 24 */     this.d = paramEntity.yaw;
/* 25 */     this.e = paramEntity.pitch;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 30 */     this.a = paramPacketDataSerializer.readDouble();
/* 31 */     this.b = paramPacketDataSerializer.readDouble();
/* 32 */     this.c = paramPacketDataSerializer.readDouble();
/* 33 */     this.d = paramPacketDataSerializer.readFloat();
/* 34 */     this.e = paramPacketDataSerializer.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 39 */     paramPacketDataSerializer.writeDouble(this.a);
/* 40 */     paramPacketDataSerializer.writeDouble(this.b);
/* 41 */     paramPacketDataSerializer.writeDouble(this.c);
/* 42 */     paramPacketDataSerializer.writeFloat(this.d);
/* 43 */     paramPacketDataSerializer.writeFloat(this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 48 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public double getX() {
/* 52 */     return this.a;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 56 */     return this.b;
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 60 */     return this.c;
/*    */   }
/*    */   
/*    */   public float getYaw() {
/* 64 */     return this.d;
/*    */   }
/*    */   
/*    */   public float getPitch() {
/* 68 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInVehicleMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */