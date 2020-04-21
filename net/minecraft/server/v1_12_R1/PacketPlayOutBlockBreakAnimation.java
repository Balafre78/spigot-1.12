/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutBlockBreakAnimation
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private BlockPosition b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutBlockBreakAnimation() {}
/*    */   
/*    */   public PacketPlayOutBlockBreakAnimation(int paramInt1, BlockPosition paramBlockPosition, int paramInt2) {
/* 19 */     this.a = paramInt1;
/* 20 */     this.b = paramBlockPosition;
/* 21 */     this.c = paramInt2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     this.a = paramPacketDataSerializer.g();
/* 27 */     this.b = paramPacketDataSerializer.e();
/* 28 */     this.c = paramPacketDataSerializer.readUnsignedByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 33 */     paramPacketDataSerializer.d(this.a);
/* 34 */     paramPacketDataSerializer.a(this.b);
/* 35 */     paramPacketDataSerializer.writeByte(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 40 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutBlockBreakAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */