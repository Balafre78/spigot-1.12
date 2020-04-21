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
/*    */ public class PacketPlayOutCustomPayload
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private String a;
/*    */   private PacketDataSerializer b;
/*    */   
/*    */   public PacketPlayOutCustomPayload() {}
/*    */   
/*    */   public PacketPlayOutCustomPayload(String paramString, PacketDataSerializer paramPacketDataSerializer) {
/* 29 */     this.a = paramString;
/* 30 */     this.b = paramPacketDataSerializer;
/*    */     
/* 32 */     if (paramPacketDataSerializer.writerIndex() > 1048576) {
/* 33 */       throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 39 */     this.a = paramPacketDataSerializer.e(20);
/* 40 */     int i = paramPacketDataSerializer.readableBytes();
/* 41 */     if (i < 0 || i > 1048576) {
/* 42 */       throw new IOException("Payload may not be larger than 1048576 bytes");
/*    */     }
/* 44 */     this.b = new PacketDataSerializer(paramPacketDataSerializer.readBytes(i));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 49 */     paramPacketDataSerializer.a(this.a);
/* 50 */     paramPacketDataSerializer.writeBytes(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 55 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */