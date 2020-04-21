/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutMap
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private byte b;
/*    */   private boolean c;
/*    */   private MapIcon[] d;
/*    */   private int e;
/*    */   private int f;
/*    */   private int g;
/*    */   private int h;
/*    */   private byte[] i;
/*    */   
/*    */   public PacketPlayOutMap() {}
/*    */   
/*    */   public PacketPlayOutMap(int paramInt1, byte paramByte, boolean paramBoolean, Collection<MapIcon> paramCollection, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 28 */     this.a = paramInt1;
/* 29 */     this.b = paramByte;
/* 30 */     this.c = paramBoolean;
/* 31 */     this.d = paramCollection.<MapIcon>toArray(new MapIcon[paramCollection.size()]);
/* 32 */     this.e = paramInt2;
/* 33 */     this.f = paramInt3;
/* 34 */     this.g = paramInt4;
/* 35 */     this.h = paramInt5;
/*    */     
/* 37 */     this.i = new byte[paramInt4 * paramInt5];
/* 38 */     for (byte b = 0; b < paramInt4; b++) {
/* 39 */       for (byte b1 = 0; b1 < paramInt5; b1++) {
/* 40 */         this.i[b + b1 * paramInt4] = paramArrayOfbyte[paramInt2 + b + (paramInt3 + b1) * 128];
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 47 */     this.a = paramPacketDataSerializer.g();
/* 48 */     this.b = paramPacketDataSerializer.readByte();
/* 49 */     this.c = paramPacketDataSerializer.readBoolean();
/* 50 */     this.d = new MapIcon[paramPacketDataSerializer.g()];
/* 51 */     for (byte b = 0; b < this.d.length; b++) {
/* 52 */       short s = (short)paramPacketDataSerializer.readByte();
/* 53 */       this.d[b] = new MapIcon(MapIcon.Type.a((byte)(s >> 4 & 0xF)), paramPacketDataSerializer.readByte(), paramPacketDataSerializer.readByte(), (byte)(s & 0xF));
/*    */     } 
/* 55 */     this.g = paramPacketDataSerializer.readUnsignedByte();
/* 56 */     if (this.g > 0) {
/* 57 */       this.h = paramPacketDataSerializer.readUnsignedByte();
/* 58 */       this.e = paramPacketDataSerializer.readUnsignedByte();
/* 59 */       this.f = paramPacketDataSerializer.readUnsignedByte();
/* 60 */       this.i = paramPacketDataSerializer.a();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 66 */     paramPacketDataSerializer.d(this.a);
/* 67 */     paramPacketDataSerializer.writeByte(this.b);
/* 68 */     paramPacketDataSerializer.writeBoolean(this.c);
/* 69 */     paramPacketDataSerializer.d(this.d.length);
/* 70 */     for (MapIcon mapIcon : this.d) {
/* 71 */       paramPacketDataSerializer.writeByte((mapIcon.getType() & 0xF) << 4 | mapIcon.getRotation() & 0xF);
/* 72 */       paramPacketDataSerializer.writeByte(mapIcon.getX());
/* 73 */       paramPacketDataSerializer.writeByte(mapIcon.getY());
/*    */     } 
/* 75 */     paramPacketDataSerializer.writeByte(this.g);
/* 76 */     if (this.g > 0) {
/* 77 */       paramPacketDataSerializer.writeByte(this.h);
/* 78 */       paramPacketDataSerializer.writeByte(this.e);
/* 79 */       paramPacketDataSerializer.writeByte(this.f);
/* 80 */       paramPacketDataSerializer.a(this.i);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 86 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */