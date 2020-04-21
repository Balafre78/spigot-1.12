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
/*    */ public class PacketPlayOutCamera
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   public int a;
/*    */   
/*    */   public PacketPlayOutCamera() {}
/*    */   
/*    */   public PacketPlayOutCamera(Entity paramEntity) {
/* 19 */     this.a = paramEntity.getId();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 24 */     this.a = paramPacketDataSerializer.g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 29 */     paramPacketDataSerializer.d(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 34 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutCamera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */