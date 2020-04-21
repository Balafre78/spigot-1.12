/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInSteerVehicle
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private float a;
/*    */   private float b;
/*    */   private boolean c;
/*    */   private boolean d;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 29 */     this.a = paramPacketDataSerializer.readFloat();
/* 30 */     this.b = paramPacketDataSerializer.readFloat();
/*    */     
/* 32 */     byte b = paramPacketDataSerializer.readByte();
/* 33 */     this.c = ((b & 0x1) > 0);
/* 34 */     this.d = ((b & 0x2) > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 39 */     paramPacketDataSerializer.writeFloat(this.a);
/* 40 */     paramPacketDataSerializer.writeFloat(this.b);
/*    */     
/* 42 */     byte b = 0;
/* 43 */     if (this.c) {
/* 44 */       b = (byte)(b | 0x1);
/*    */     }
/* 46 */     if (this.d) {
/* 47 */       b = (byte)(b | 0x2);
/*    */     }
/* 49 */     paramPacketDataSerializer.writeByte(b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 54 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public float a() {
/* 58 */     return this.a;
/*    */   }
/*    */   
/*    */   public float b() {
/* 62 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 66 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 70 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInSteerVehicle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */