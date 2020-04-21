/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.PrivateKey;
/*    */ import javax.crypto.SecretKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginInEncryptionBegin
/*    */   implements Packet<PacketLoginInListener>
/*    */ {
/* 13 */   private byte[] a = new byte[0];
/* 14 */   private byte[] b = new byte[0];
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
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 27 */     this.a = paramPacketDataSerializer.a();
/* 28 */     this.b = paramPacketDataSerializer.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 33 */     paramPacketDataSerializer.a(this.a);
/* 34 */     paramPacketDataSerializer.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginInListener paramPacketLoginInListener) {
/* 39 */     paramPacketLoginInListener.a(this);
/*    */   }
/*    */   
/*    */   public SecretKey a(PrivateKey paramPrivateKey) {
/* 43 */     return MinecraftEncryption.a(paramPrivateKey, this.a);
/*    */   }
/*    */   
/*    */   public byte[] b(PrivateKey paramPrivateKey) {
/* 47 */     if (paramPrivateKey == null) {
/* 48 */       return this.b;
/*    */     }
/* 50 */     return MinecraftEncryption.b(paramPrivateKey, this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketLoginInEncryptionBegin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */