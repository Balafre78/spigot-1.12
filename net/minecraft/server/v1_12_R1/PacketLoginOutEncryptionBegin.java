/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.PublicKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutEncryptionBegin
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private String a;
/*    */   private PublicKey b;
/*    */   private byte[] c;
/*    */   
/*    */   public PacketLoginOutEncryptionBegin() {}
/*    */   
/*    */   public PacketLoginOutEncryptionBegin(String paramString, PublicKey paramPublicKey, byte[] paramArrayOfbyte) {
/* 20 */     this.a = paramString;
/* 21 */     this.b = paramPublicKey;
/* 22 */     this.c = paramArrayOfbyte;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 27 */     this.a = paramPacketDataSerializer.e(20);
/* 28 */     this.b = MinecraftEncryption.a(paramPacketDataSerializer.a());
/* 29 */     this.c = paramPacketDataSerializer.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 34 */     paramPacketDataSerializer.a(this.a);
/* 35 */     paramPacketDataSerializer.a(this.b.getEncoded());
/* 36 */     paramPacketDataSerializer.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginOutListener paramPacketLoginOutListener) {
/* 41 */     paramPacketLoginOutListener.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketLoginOutEncryptionBegin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */