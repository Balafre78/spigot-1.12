/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInBoatMove
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private boolean a;
/*    */   private boolean b;
/*    */   
/*    */   public PacketPlayInBoatMove() {}
/*    */   
/*    */   public PacketPlayInBoatMove(boolean paramBoolean1, boolean paramBoolean2) {
/* 17 */     this.a = paramBoolean1;
/* 18 */     this.b = paramBoolean2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 23 */     this.a = paramPacketDataSerializer.readBoolean();
/* 24 */     this.b = paramPacketDataSerializer.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 29 */     paramPacketDataSerializer.writeBoolean(this.a);
/* 30 */     paramPacketDataSerializer.writeBoolean(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 35 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 39 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 43 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInBoatMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */