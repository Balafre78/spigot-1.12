/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ 
/*    */ public class PacketDecoder
/*    */   extends ByteToMessageDecoder
/*    */ {
/* 17 */   private static final Logger a = LogManager.getLogger();
/* 18 */   private static final Marker b = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.b);
/*    */   
/*    */   private final EnumProtocolDirection c;
/*    */   
/*    */   public PacketDecoder(EnumProtocolDirection paramEnumProtocolDirection) {
/* 23 */     this.c = paramEnumProtocolDirection;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf, List<Object> paramList) throws Exception {
/* 28 */     if (paramByteBuf.readableBytes() == 0) {
/*    */       return;
/*    */     }
/*    */     
/* 32 */     PacketDataSerializer packetDataSerializer = new PacketDataSerializer(paramByteBuf);
/* 33 */     int i = packetDataSerializer.g();
/* 34 */     Packet<?> packet = ((EnumProtocol)paramChannelHandlerContext.channel().attr(NetworkManager.c).get()).a(this.c, i);
/*    */     
/* 36 */     if (packet == null) {
/* 37 */       throw new IOException("Bad packet id " + i);
/*    */     }
/*    */     
/* 40 */     packet.a(packetDataSerializer);
/* 41 */     if (packetDataSerializer.readableBytes() > 0) {
/* 42 */       throw new IOException("Packet " + ((EnumProtocol)paramChannelHandlerContext.channel().attr(NetworkManager.c).get()).a() + "/" + i + " (" + packet.getClass().getSimpleName() + ") was larger than I expected, found " + packetDataSerializer.readableBytes() + " bytes extra whilst reading packet " + i);
/*    */     }
/* 44 */     paramList.add(packet);
/*    */ 
/*    */     
/* 47 */     if (a.isDebugEnabled())
/* 48 */       a.debug(b, " IN: [{}:{}] {}", paramChannelHandlerContext.channel().attr(NetworkManager.c).get(), Integer.valueOf(i), packet.getClass().getName()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */