/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import io.netty.handler.codec.DecoderException;
/*    */ import java.util.List;
/*    */ import java.util.zip.Inflater;
/*    */ 
/*    */ 
/*    */ public class PacketDecompressor
/*    */   extends ByteToMessageDecoder
/*    */ {
/*    */   private final Inflater a;
/*    */   private int b;
/*    */   
/*    */   public PacketDecompressor(int paramInt) {
/* 19 */     this.b = paramInt;
/* 20 */     this.a = new Inflater();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf, List<Object> paramList) throws Exception {
/* 25 */     if (paramByteBuf.readableBytes() == 0) {
/*    */       return;
/*    */     }
/*    */     
/* 29 */     PacketDataSerializer packetDataSerializer = new PacketDataSerializer(paramByteBuf);
/* 30 */     int i = packetDataSerializer.g();
/*    */     
/* 32 */     if (i == 0)
/* 33 */     { paramList.add(packetDataSerializer.readBytes(packetDataSerializer.readableBytes())); }
/* 34 */     else { if (i < this.b)
/* 35 */         throw new DecoderException("Badly compressed packet - size of " + i + " is below server threshold of " + this.b); 
/* 36 */       if (i > 2097152) {
/* 37 */         throw new DecoderException("Badly compressed packet - size of " + i + " is larger than protocol maximum of " + 2097152);
/*    */       }
/* 39 */       byte[] arrayOfByte1 = new byte[packetDataSerializer.readableBytes()];
/* 40 */       packetDataSerializer.readBytes(arrayOfByte1);
/* 41 */       this.a.setInput(arrayOfByte1);
/*    */       
/* 43 */       byte[] arrayOfByte2 = new byte[i];
/* 44 */       this.a.inflate(arrayOfByte2);
/* 45 */       paramList.add(Unpooled.wrappedBuffer(arrayOfByte2));
/*    */       
/* 47 */       this.a.reset(); }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(int paramInt) {
/* 56 */     this.b = paramInt;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketDecompressor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */