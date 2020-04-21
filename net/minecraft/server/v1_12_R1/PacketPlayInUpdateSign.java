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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInUpdateSign
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private String[] b;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 27 */     this.a = paramPacketDataSerializer.e();
/* 28 */     this.b = new String[4];
/* 29 */     for (byte b = 0; b < 4; b++) {
/* 30 */       this.b[b] = paramPacketDataSerializer.e(384);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 36 */     paramPacketDataSerializer.a(this.a);
/* 37 */     for (byte b = 0; b < 4; b++) {
/* 38 */       paramPacketDataSerializer.a(this.b[b]);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 44 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition a() {
/* 48 */     return this.a;
/*    */   }
/*    */   
/*    */   public String[] b() {
/* 52 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInUpdateSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */