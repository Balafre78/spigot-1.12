/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutOpenSignEditor
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private BlockPosition a;
/*    */   
/*    */   public PacketPlayOutOpenSignEditor() {}
/*    */   
/*    */   public PacketPlayOutOpenSignEditor(BlockPosition paramBlockPosition) {
/* 16 */     this.a = paramBlockPosition;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 21 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     this.a = paramPacketDataSerializer.e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 31 */     paramPacketDataSerializer.a(this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutOpenSignEditor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */