/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageDecoder;
/*    */ import java.util.List;
/*    */ import javax.crypto.Cipher;
/*    */ 
/*    */ public class PacketDecrypter
/*    */   extends MessageToMessageDecoder<ByteBuf> {
/*    */   private final PacketEncryptionHandler a;
/*    */   
/*    */   public PacketDecrypter(Cipher paramCipher) {
/* 14 */     this.a = new PacketEncryptionHandler(paramCipher);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf, List<Object> paramList) throws Exception {
/* 19 */     paramList.add(this.a.a(paramChannelHandlerContext, paramByteBuf));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketDecrypter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */