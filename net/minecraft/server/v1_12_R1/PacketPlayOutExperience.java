/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutExperience
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private float a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutExperience() {}
/*    */   
/*    */   public PacketPlayOutExperience(float paramFloat, int paramInt1, int paramInt2) {
/* 17 */     this.a = paramFloat;
/* 18 */     this.b = paramInt1;
/* 19 */     this.c = paramInt2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 24 */     this.a = paramPacketDataSerializer.readFloat();
/* 25 */     this.c = paramPacketDataSerializer.g();
/* 26 */     this.b = paramPacketDataSerializer.g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 31 */     paramPacketDataSerializer.writeFloat(this.a);
/* 32 */     paramPacketDataSerializer.d(this.c);
/* 33 */     paramPacketDataSerializer.d(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 38 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutExperience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */