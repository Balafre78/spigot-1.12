/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutDisconnect
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private IChatBaseComponent a;
/*    */   
/*    */   public PacketLoginOutDisconnect() {}
/*    */   
/*    */   public PacketLoginOutDisconnect(IChatBaseComponent paramIChatBaseComponent) {
/* 16 */     this.a = paramIChatBaseComponent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 21 */     this.a = IChatBaseComponent.ChatSerializer.b(paramPacketDataSerializer.e(32767));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     paramPacketDataSerializer.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginOutListener paramPacketLoginOutListener) {
/* 31 */     paramPacketLoginOutListener.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketLoginOutDisconnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */