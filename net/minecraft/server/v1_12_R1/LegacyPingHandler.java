/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class LegacyPingHandler
/*     */   extends ChannelInboundHandlerAdapter {
/*  16 */   private static final Logger a = LogManager.getLogger();
/*     */   
/*     */   private final ServerConnection b;
/*     */ 
/*     */   
/*     */   public LegacyPingHandler(ServerConnection paramServerConnection) {
/*  22 */     this.b = paramServerConnection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelRead(ChannelHandlerContext paramChannelHandlerContext, Object paramObject) throws Exception {
/*  27 */     ByteBuf byteBuf = (ByteBuf)paramObject;
/*     */     
/*  29 */     byteBuf.markReaderIndex();
/*     */     
/*  31 */     boolean bool = true; 
/*     */     try { String str1; int k; boolean bool1; int j, m; String str2; ByteBuf byteBuf1;
/*  33 */       if (byteBuf.readUnsignedByte() != 254) {
/*     */         return;
/*     */       }
/*     */       
/*  37 */       InetSocketAddress inetSocketAddress = (InetSocketAddress)paramChannelHandlerContext.channel().remoteAddress();
/*  38 */       MinecraftServer minecraftServer = this.b.d();
/*     */       
/*  40 */       int i = byteBuf.readableBytes();
/*  41 */       switch (i) {
/*     */         case 0:
/*  43 */           a.debug("Ping: (<1.3.x) from {}:{}", inetSocketAddress.getAddress(), Integer.valueOf(inetSocketAddress.getPort()));
/*     */           
/*  45 */           str1 = String.format("%s§%d§%d", new Object[] { minecraftServer.getMotd(), Integer.valueOf(minecraftServer.H()), Integer.valueOf(minecraftServer.I()) });
/*  46 */           a(paramChannelHandlerContext, a(str1));
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/*  51 */           if (byteBuf.readUnsignedByte() != 1) {
/*     */             return;
/*     */           }
/*     */           
/*  55 */           a.debug("Ping: (1.4-1.5.x) from {}:{}", inetSocketAddress.getAddress(), Integer.valueOf(inetSocketAddress.getPort()));
/*     */           
/*  57 */           str1 = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(127), minecraftServer.getVersion(), minecraftServer.getMotd(), Integer.valueOf(minecraftServer.H()), Integer.valueOf(minecraftServer.I()) });
/*  58 */           a(paramChannelHandlerContext, a(str1));
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/*  63 */           k = (byteBuf.readUnsignedByte() == 1) ? 1 : 0;
/*  64 */           k &= (byteBuf.readUnsignedByte() == 250) ? 1 : 0;
/*  65 */           bool1 = k & "MC|PingHost".equals(new String(byteBuf.readBytes(byteBuf.readShort() * 2).array(), StandardCharsets.UTF_16BE));
/*  66 */           m = byteBuf.readUnsignedShort();
/*  67 */           j = bool1 & ((byteBuf.readUnsignedByte() >= 73) ? 1 : 0);
/*  68 */           j &= (3 + (byteBuf.readBytes(byteBuf.readShort() * 2).array()).length + 4 == m) ? 1 : 0;
/*  69 */           j &= (byteBuf.readInt() <= 65535) ? 1 : 0;
/*  70 */           j &= (byteBuf.readableBytes() == 0) ? 1 : 0;
/*     */           
/*  72 */           if (j == 0) {
/*     */             return;
/*     */           }
/*     */           
/*  76 */           a.debug("Ping: (1.6) from {}:{}", inetSocketAddress.getAddress(), Integer.valueOf(inetSocketAddress.getPort()));
/*     */           
/*  78 */           str2 = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(127), minecraftServer.getVersion(), minecraftServer.getMotd(), Integer.valueOf(minecraftServer.H()), Integer.valueOf(minecraftServer.I()) });
/*  79 */           byteBuf1 = a(str2);
/*     */           try {
/*  81 */             a(paramChannelHandlerContext, byteBuf1);
/*     */           } finally {
/*  83 */             byteBuf1.release();
/*     */           } 
/*     */           break;
/*     */       } 
/*  87 */       byteBuf.release();
/*  88 */       bool = false; }
/*  89 */     catch (RuntimeException runtimeException) {  }
/*     */     finally
/*  91 */     { if (bool) {
/*  92 */         byteBuf.resetReaderIndex();
/*  93 */         paramChannelHandlerContext.channel().pipeline().remove("legacy_query");
/*  94 */         paramChannelHandlerContext.fireChannelRead(paramObject);
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   private void a(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf) {
/* 100 */     paramChannelHandlerContext.pipeline().firstContext().writeAndFlush(paramByteBuf).addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
/*     */   }
/*     */   
/*     */   private ByteBuf a(String paramString) {
/* 104 */     ByteBuf byteBuf = Unpooled.buffer();
/* 105 */     byteBuf.writeByte(255);
/*     */     
/* 107 */     char[] arrayOfChar = paramString.toCharArray();
/* 108 */     byteBuf.writeShort(arrayOfChar.length);
/* 109 */     for (char c : arrayOfChar) {
/* 110 */       byteBuf.writeChar(c);
/*     */     }
/*     */     
/* 113 */     return byteBuf;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LegacyPingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */