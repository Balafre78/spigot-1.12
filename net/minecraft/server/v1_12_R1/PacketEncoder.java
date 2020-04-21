/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import java.io.IOException;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ 
/*    */ public class PacketEncoder
/*    */   extends MessageToByteEncoder<Packet<?>>
/*    */ {
/* 16 */   private static final Logger a = LogManager.getLogger();
/* 17 */   private static final Marker b = MarkerManager.getMarker("PACKET_SENT", NetworkManager.b);
/*    */   
/*    */   private final EnumProtocolDirection c;
/*    */   
/*    */   public PacketEncoder(EnumProtocolDirection paramEnumProtocolDirection) {
/* 22 */     this.c = paramEnumProtocolDirection;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ChannelHandlerContext paramChannelHandlerContext, Packet<?> paramPacket, ByteBuf paramByteBuf) throws Exception {
/* 27 */     EnumProtocol enumProtocol = (EnumProtocol)paramChannelHandlerContext.channel().attr(NetworkManager.c).get();
/* 28 */     if (enumProtocol == null) {
/* 29 */       throw new RuntimeException("ConnectionProtocol unknown: " + paramPacket.toString());
/*    */     }
/* 31 */     Integer integer = enumProtocol.a(this.c, paramPacket);
/*    */ 
/*    */     
/* 34 */     if (a.isDebugEnabled()) {
/* 35 */       a.debug(b, "OUT: [{}:{}] {}", paramChannelHandlerContext.channel().attr(NetworkManager.c).get(), integer, paramPacket.getClass().getName());
/*    */     }
/*    */     
/* 38 */     if (integer == null) {
/* 39 */       throw new IOException("Can't serialize unregistered packet");
/*    */     }
/*    */     
/* 42 */     PacketDataSerializer packetDataSerializer = new PacketDataSerializer(paramByteBuf);
/* 43 */     packetDataSerializer.d(integer.intValue());
/*    */     
/*    */     try {
/* 46 */       paramPacket.b(packetDataSerializer);
/* 47 */     } catch (Throwable throwable) {
/* 48 */       a.error(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */