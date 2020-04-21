/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutSetCompression
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private int a;
/*    */   
/*    */   public PacketLoginOutSetCompression() {}
/*    */   
/*    */   public PacketLoginOutSetCompression(int paramInt) {
/* 16 */     this.a = paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 21 */     this.a = paramPacketDataSerializer.g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     paramPacketDataSerializer.d(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginOutListener paramPacketLoginOutListener) {
/* 31 */     paramPacketLoginOutListener.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketLoginOutSetCompression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */