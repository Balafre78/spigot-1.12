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
/*    */ public class PacketPlayOutBed
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private BlockPosition b;
/*    */   
/*    */   public PacketPlayOutBed() {}
/*    */   
/*    */   public PacketPlayOutBed(EntityHuman paramEntityHuman, BlockPosition paramBlockPosition) {
/* 21 */     this.a = paramEntityHuman.getId();
/* 22 */     this.b = paramBlockPosition;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 27 */     this.a = paramPacketDataSerializer.g();
/* 28 */     this.b = paramPacketDataSerializer.e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 33 */     paramPacketDataSerializer.d(this.a);
/* 34 */     paramPacketDataSerializer.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 39 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */