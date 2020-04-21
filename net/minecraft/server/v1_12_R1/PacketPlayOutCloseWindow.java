/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutCloseWindow
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   
/*    */   public PacketPlayOutCloseWindow() {}
/*    */   
/*    */   public PacketPlayOutCloseWindow(int paramInt) {
/* 17 */     this.a = paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 22 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 27 */     this.a = paramPacketDataSerializer.readUnsignedByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 32 */     paramPacketDataSerializer.writeByte(this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutCloseWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */