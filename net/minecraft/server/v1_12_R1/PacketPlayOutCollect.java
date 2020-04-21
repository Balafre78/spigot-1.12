/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutCollect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutCollect() {}
/*    */   
/*    */   public PacketPlayOutCollect(int paramInt1, int paramInt2, int paramInt3) {
/* 17 */     this.a = paramInt1;
/* 18 */     this.b = paramInt2;
/* 19 */     this.c = paramInt3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 24 */     this.a = paramPacketDataSerializer.g();
/* 25 */     this.b = paramPacketDataSerializer.g();
/* 26 */     this.c = paramPacketDataSerializer.g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 31 */     paramPacketDataSerializer.d(this.a);
/* 32 */     paramPacketDataSerializer.d(this.b);
/* 33 */     paramPacketDataSerializer.d(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 38 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutCollect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */