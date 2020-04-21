/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import javax.crypto.Cipher;
/*    */ 
/*    */ public class PacketEncrypter
/*    */   extends MessageToByteEncoder<ByteBuf> {
/*    */   private final PacketEncryptionHandler a;
/*    */   
/*    */   public PacketEncrypter(Cipher paramCipher) {
/* 13 */     this.a = new PacketEncryptionHandler(paramCipher);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf1, ByteBuf paramByteBuf2) throws Exception {
/* 18 */     this.a.a(paramByteBuf1, paramByteBuf2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketEncrypter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */