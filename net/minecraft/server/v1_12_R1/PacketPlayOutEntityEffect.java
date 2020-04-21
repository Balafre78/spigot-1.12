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
/*    */ public class PacketPlayOutEntityEffect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private byte b;
/*    */   private byte c;
/*    */   private int d;
/*    */   private byte e;
/*    */   
/*    */   public PacketPlayOutEntityEffect() {}
/*    */   
/*    */   public PacketPlayOutEntityEffect(int paramInt, MobEffect paramMobEffect) {
/* 27 */     this.a = paramInt;
/* 28 */     this.b = (byte)(MobEffectList.getId(paramMobEffect.getMobEffect()) & 0xFF);
/* 29 */     this.c = (byte)(paramMobEffect.getAmplifier() & 0xFF);
/* 30 */     if (paramMobEffect.getDuration() > 32767) {
/* 31 */       this.d = 32767;
/*    */     } else {
/* 33 */       this.d = paramMobEffect.getDuration();
/*    */     } 
/* 35 */     this.e = 0;
/*    */     
/* 37 */     if (paramMobEffect.isAmbient()) {
/* 38 */       this.e = (byte)(this.e | 0x1);
/*    */     }
/* 40 */     if (paramMobEffect.isShowParticles()) {
/* 41 */       this.e = (byte)(this.e | 0x2);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 47 */     this.a = paramPacketDataSerializer.g();
/* 48 */     this.b = paramPacketDataSerializer.readByte();
/* 49 */     this.c = paramPacketDataSerializer.readByte();
/* 50 */     this.d = paramPacketDataSerializer.g();
/* 51 */     this.e = paramPacketDataSerializer.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 56 */     paramPacketDataSerializer.d(this.a);
/* 57 */     paramPacketDataSerializer.writeByte(this.b);
/* 58 */     paramPacketDataSerializer.writeByte(this.c);
/* 59 */     paramPacketDataSerializer.d(this.d);
/* 60 */     paramPacketDataSerializer.writeByte(this.e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 69 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutEntityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */