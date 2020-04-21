/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutNamedEntitySpawn
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private UUID b;
/*    */   private double c;
/*    */   private double d;
/*    */   private double e;
/*    */   private byte f;
/*    */   private byte g;
/*    */   private DataWatcher h;
/*    */   private List<DataWatcher.Item<?>> i;
/*    */   
/*    */   public PacketPlayOutNamedEntitySpawn() {}
/*    */   
/*    */   public PacketPlayOutNamedEntitySpawn(EntityHuman paramEntityHuman) {
/* 30 */     this.a = paramEntityHuman.getId();
/* 31 */     this.b = paramEntityHuman.getProfile().getId();
/* 32 */     this.c = paramEntityHuman.locX;
/* 33 */     this.d = paramEntityHuman.locY;
/* 34 */     this.e = paramEntityHuman.locZ;
/* 35 */     this.f = (byte)(int)(paramEntityHuman.yaw * 256.0F / 360.0F);
/* 36 */     this.g = (byte)(int)(paramEntityHuman.pitch * 256.0F / 360.0F);
/*    */     
/* 38 */     this.h = paramEntityHuman.getDataWatcher();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 43 */     this.a = paramPacketDataSerializer.g();
/* 44 */     this.b = paramPacketDataSerializer.i();
/* 45 */     this.c = paramPacketDataSerializer.readDouble();
/* 46 */     this.d = paramPacketDataSerializer.readDouble();
/* 47 */     this.e = paramPacketDataSerializer.readDouble();
/* 48 */     this.f = paramPacketDataSerializer.readByte();
/* 49 */     this.g = paramPacketDataSerializer.readByte();
/* 50 */     this.i = DataWatcher.b(paramPacketDataSerializer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 55 */     paramPacketDataSerializer.d(this.a);
/* 56 */     paramPacketDataSerializer.a(this.b);
/* 57 */     paramPacketDataSerializer.writeDouble(this.c);
/* 58 */     paramPacketDataSerializer.writeDouble(this.d);
/* 59 */     paramPacketDataSerializer.writeDouble(this.e);
/* 60 */     paramPacketDataSerializer.writeByte(this.f);
/* 61 */     paramPacketDataSerializer.writeByte(this.g);
/* 62 */     this.h.a(paramPacketDataSerializer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 67 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutNamedEntitySpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */