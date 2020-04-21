/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityTeleport
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private double b;
/*    */   private double c;
/*    */   private double d;
/*    */   private byte e;
/*    */   private byte f;
/*    */   private boolean g;
/*    */   
/*    */   public PacketPlayOutEntityTeleport() {}
/*    */   
/*    */   public PacketPlayOutEntityTeleport(Entity paramEntity) {
/* 22 */     this.a = paramEntity.getId();
/* 23 */     this.b = paramEntity.locX;
/* 24 */     this.c = paramEntity.locY;
/* 25 */     this.d = paramEntity.locZ;
/* 26 */     this.e = (byte)(int)(paramEntity.yaw * 256.0F / 360.0F);
/* 27 */     this.f = (byte)(int)(paramEntity.pitch * 256.0F / 360.0F);
/* 28 */     this.g = paramEntity.onGround;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 33 */     this.a = paramPacketDataSerializer.g();
/* 34 */     this.b = paramPacketDataSerializer.readDouble();
/* 35 */     this.c = paramPacketDataSerializer.readDouble();
/* 36 */     this.d = paramPacketDataSerializer.readDouble();
/* 37 */     this.e = paramPacketDataSerializer.readByte();
/* 38 */     this.f = paramPacketDataSerializer.readByte();
/* 39 */     this.g = paramPacketDataSerializer.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 44 */     paramPacketDataSerializer.d(this.a);
/* 45 */     paramPacketDataSerializer.writeDouble(this.b);
/* 46 */     paramPacketDataSerializer.writeDouble(this.c);
/* 47 */     paramPacketDataSerializer.writeDouble(this.d);
/* 48 */     paramPacketDataSerializer.writeByte(this.e);
/* 49 */     paramPacketDataSerializer.writeByte(this.f);
/* 50 */     paramPacketDataSerializer.writeBoolean(this.g);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 55 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutEntityTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */