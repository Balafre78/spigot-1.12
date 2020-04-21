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
/*    */ public class PacketPlayInCustomPayload
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private String a;
/*    */   private PacketDataSerializer b;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 42 */     this.a = paramPacketDataSerializer.e(20);
/* 43 */     int i = paramPacketDataSerializer.readableBytes();
/* 44 */     if (i < 0 || i > 32767) {
/* 45 */       throw new IOException("Payload may not be larger than 32767 bytes");
/*    */     }
/* 47 */     this.b = new PacketDataSerializer(paramPacketDataSerializer.readBytes(i));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 52 */     paramPacketDataSerializer.a(this.a);
/* 53 */     paramPacketDataSerializer.writeBytes(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 58 */     paramPacketListenerPlayIn.a(this);
/* 59 */     if (this.b != null) {
/* 60 */       this.b.release();
/*    */     }
/*    */   }
/*    */   
/*    */   public String a() {
/* 65 */     return this.a;
/*    */   }
/*    */   
/*    */   public PacketDataSerializer b() {
/* 69 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */