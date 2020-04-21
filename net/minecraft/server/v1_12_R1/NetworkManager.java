/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Queues;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.SimpleChannelInboundHandler;
/*     */ import io.netty.channel.epoll.EpollEventLoopGroup;
/*     */ import io.netty.channel.local.LocalEventLoopGroup;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.util.AttributeKey;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.Queue;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.crypto.SecretKey;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ 
/*     */ public class NetworkManager
/*     */   extends SimpleChannelInboundHandler<Packet<?>> {
/*  33 */   private static final Logger g = LogManager.getLogger();
/*  34 */   public static final Marker a = MarkerManager.getMarker("NETWORK");
/*  35 */   public static final Marker b = MarkerManager.getMarker("NETWORK_PACKETS", a);
/*  36 */   public static final AttributeKey<EnumProtocol> c = AttributeKey.valueOf("protocol");
/*  37 */   public static final LazyInitVar<NioEventLoopGroup> d = new LazyInitVar<NioEventLoopGroup>() {
/*     */       protected NioEventLoopGroup a() {
/*  39 */         return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Client IO #%d").setDaemon(true).build());
/*     */       }
/*     */       
/*     */       protected Object init() {
/*  43 */         return a();
/*     */       }
/*     */     };
/*  46 */   public static final LazyInitVar<EpollEventLoopGroup> e = new LazyInitVar<EpollEventLoopGroup>() {
/*     */       protected EpollEventLoopGroup a() {
/*  48 */         return new EpollEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
/*     */       }
/*     */       
/*     */       protected Object init() {
/*  52 */         return a();
/*     */       }
/*     */     };
/*  55 */   public static final LazyInitVar<LocalEventLoopGroup> f = new LazyInitVar<LocalEventLoopGroup>() {
/*     */       protected LocalEventLoopGroup a() {
/*  57 */         return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
/*     */       }
/*     */       
/*     */       protected Object init() {
/*  61 */         return a();
/*     */       }
/*     */     };
/*     */   private final EnumProtocolDirection h;
/*  65 */   private final Queue<QueuedPacket> i = Queues.newConcurrentLinkedQueue();
/*  66 */   private final ReentrantReadWriteLock j = new ReentrantReadWriteLock();
/*     */   
/*     */   public Channel channel;
/*     */   
/*     */   public SocketAddress l;
/*     */   public UUID spoofedUUID;
/*     */   public Property[] spoofedProfile;
/*     */   public boolean preparing = true;
/*     */   private PacketListener m;
/*     */   private IChatBaseComponent n;
/*     */   private boolean o;
/*     */   private boolean p;
/*     */   
/*     */   public NetworkManager(EnumProtocolDirection enumprotocoldirection) {
/*  80 */     this.h = enumprotocoldirection;
/*     */   }
/*     */   
/*     */   public void channelActive(ChannelHandlerContext channelhandlercontext) throws Exception {
/*  84 */     super.channelActive(channelhandlercontext);
/*  85 */     this.channel = channelhandlercontext.channel();
/*  86 */     this.l = this.channel.remoteAddress();
/*     */     
/*  88 */     this.preparing = false;
/*     */ 
/*     */     
/*     */     try {
/*  92 */       setProtocol(EnumProtocol.HANDSHAKING);
/*  93 */     } catch (Throwable throwable) {
/*  94 */       g.fatal(throwable);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProtocol(EnumProtocol enumprotocol) {
/* 100 */     this.channel.attr(c).set(enumprotocol);
/* 101 */     this.channel.config().setAutoRead(true);
/* 102 */     g.debug("Enabled auto read");
/*     */   }
/*     */   
/*     */   public void channelInactive(ChannelHandlerContext channelhandlercontext) throws Exception {
/* 106 */     close(new ChatMessage("disconnect.endOfStream", new Object[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public void exceptionCaught(ChannelHandlerContext channelhandlercontext, Throwable throwable) throws Exception {
/*     */     ChatMessage chatmessage;
/* 112 */     if (throwable instanceof io.netty.handler.timeout.TimeoutException) {
/* 113 */       chatmessage = new ChatMessage("disconnect.timeout", new Object[0]);
/*     */     } else {
/* 115 */       chatmessage = new ChatMessage("disconnect.genericReason", new Object[] { "Internal Exception: " + throwable });
/*     */     } 
/*     */     
/* 118 */     g.debug(chatmessage.toPlainText(), throwable);
/* 119 */     close(chatmessage);
/* 120 */     if (MinecraftServer.getServer().isDebugging()) throwable.printStackTrace(); 
/*     */   }
/*     */   
/*     */   protected void a(ChannelHandlerContext channelhandlercontext, Packet<?> packet) throws Exception {
/* 124 */     if (this.channel.isOpen()) {
/*     */       try {
/* 126 */         packet.a(this.m);
/* 127 */       } catch (CancelledPacketHandleException cancelledPacketHandleException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPacketListener(PacketListener packetlistener) {
/* 135 */     Validate.notNull(packetlistener, "packetListener", new Object[0]);
/* 136 */     g.debug("Set listener of {} to {}", this, packetlistener);
/* 137 */     this.m = packetlistener;
/*     */   }
/*     */   
/*     */   public void sendPacket(Packet<?> packet) {
/* 141 */     if (isConnected()) {
/* 142 */       m();
/* 143 */       a(packet, (GenericFutureListener<? extends Future<? super Void>>[])null);
/*     */     } else {
/* 145 */       this.j.writeLock().lock();
/*     */       
/*     */       try {
/* 148 */         this.i.add(new QueuedPacket(packet, (GenericFutureListener<? extends Future<? super Void>>[])new GenericFutureListener[0]));
/*     */       } finally {
/* 150 */         this.j.writeLock().unlock();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacket(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> genericfuturelistener, GenericFutureListener... agenericfuturelistener) {
/* 157 */     if (isConnected()) {
/* 158 */       m();
/* 159 */       a(packet, (GenericFutureListener<? extends Future<? super Void>>[])ArrayUtils.add((Object[])agenericfuturelistener, 0, genericfuturelistener));
/*     */     } else {
/* 161 */       this.j.writeLock().lock();
/*     */       
/*     */       try {
/* 164 */         this.i.add(new QueuedPacket(packet, (GenericFutureListener<? extends Future<? super Void>>[])ArrayUtils.add((Object[])agenericfuturelistener, 0, genericfuturelistener)));
/*     */       } finally {
/* 166 */         this.j.writeLock().unlock();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(final Packet<?> packet, @Nullable final GenericFutureListener[] agenericfuturelistener) {
/* 173 */     final EnumProtocol enumprotocol = EnumProtocol.a(packet);
/* 174 */     final EnumProtocol enumprotocol1 = (EnumProtocol)this.channel.attr(c).get();
/*     */     
/* 176 */     if (enumprotocol1 != enumprotocol) {
/* 177 */       g.debug("Disabled auto read");
/* 178 */       this.channel.config().setAutoRead(false);
/*     */     } 
/*     */     
/* 181 */     if (this.channel.eventLoop().inEventLoop()) {
/* 182 */       if (enumprotocol != enumprotocol1) {
/* 183 */         setProtocol(enumprotocol);
/*     */       }
/*     */       
/* 186 */       ChannelFuture channelfuture = this.channel.writeAndFlush(packet);
/*     */       
/* 188 */       if (agenericfuturelistener != null) {
/* 189 */         channelfuture.addListeners(agenericfuturelistener);
/*     */       }
/*     */       
/* 192 */       channelfuture.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
/*     */     } else {
/* 194 */       this.channel.eventLoop().execute(new Runnable() {
/*     */             public void run() {
/* 196 */               if (enumprotocol != enumprotocol1) {
/* 197 */                 NetworkManager.this.setProtocol(enumprotocol);
/*     */               }
/*     */               
/* 200 */               ChannelFuture channelfuture = NetworkManager.this.channel.writeAndFlush(packet);
/*     */               
/* 202 */               if (agenericfuturelistener != null) {
/* 203 */                 channelfuture.addListeners(agenericfuturelistener);
/*     */               }
/*     */               
/* 206 */               channelfuture.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void m() {
/* 214 */     if (this.channel != null && this.channel.isOpen()) {
/* 215 */       this.j.readLock().lock();
/*     */       
/*     */       try {
/* 218 */         while (!this.i.isEmpty()) {
/* 219 */           QueuedPacket networkmanager_queuedpacket = this.i.poll();
/*     */           
/* 221 */           a(networkmanager_queuedpacket.a, networkmanager_queuedpacket.b);
/*     */         } 
/*     */       } finally {
/* 224 */         this.j.readLock().unlock();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/* 231 */     m();
/* 232 */     if (this.m instanceof ITickable) {
/* 233 */       ((ITickable)this.m).e();
/*     */     }
/*     */     
/* 236 */     if (this.channel != null) {
/* 237 */       this.channel.flush();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public SocketAddress getSocketAddress() {
/* 243 */     return this.l;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close(IChatBaseComponent ichatbasecomponent) {
/* 248 */     this.preparing = false;
/*     */     
/* 250 */     if (this.channel.isOpen()) {
/* 251 */       this.channel.close();
/* 252 */       this.n = ichatbasecomponent;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocal() {
/* 258 */     return !(!(this.channel instanceof io.netty.channel.local.LocalChannel) && !(this.channel instanceof io.netty.channel.local.LocalServerChannel));
/*     */   }
/*     */   
/*     */   public void a(SecretKey secretkey) {
/* 262 */     this.o = true;
/* 263 */     this.channel.pipeline().addBefore("splitter", "decrypt", (ChannelHandler)new PacketDecrypter(MinecraftEncryption.a(2, secretkey)));
/* 264 */     this.channel.pipeline().addBefore("prepender", "encrypt", (ChannelHandler)new PacketEncrypter(MinecraftEncryption.a(1, secretkey)));
/*     */   }
/*     */   
/*     */   public boolean isConnected() {
/* 268 */     return (this.channel != null && this.channel.isOpen());
/*     */   }
/*     */   
/*     */   public boolean h() {
/* 272 */     return (this.channel == null);
/*     */   }
/*     */   
/*     */   public PacketListener i() {
/* 276 */     return this.m;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent j() {
/* 280 */     return this.n;
/*     */   }
/*     */   
/*     */   public void stopReading() {
/* 284 */     this.channel.config().setAutoRead(false);
/*     */   }
/*     */   
/*     */   public void setCompressionLevel(int i) {
/* 288 */     if (i >= 0) {
/* 289 */       if (this.channel.pipeline().get("decompress") instanceof PacketDecompressor) {
/* 290 */         ((PacketDecompressor)this.channel.pipeline().get("decompress")).a(i);
/*     */       } else {
/* 292 */         this.channel.pipeline().addBefore("decoder", "decompress", (ChannelHandler)new PacketDecompressor(i));
/*     */       } 
/*     */       
/* 295 */       if (this.channel.pipeline().get("compress") instanceof PacketCompressor) {
/* 296 */         ((PacketCompressor)this.channel.pipeline().get("compress")).a(i);
/*     */       } else {
/* 298 */         this.channel.pipeline().addBefore("encoder", "compress", (ChannelHandler)new PacketCompressor(i));
/*     */       } 
/*     */     } else {
/* 301 */       if (this.channel.pipeline().get("decompress") instanceof PacketDecompressor) {
/* 302 */         this.channel.pipeline().remove("decompress");
/*     */       }
/*     */       
/* 305 */       if (this.channel.pipeline().get("compress") instanceof PacketCompressor) {
/* 306 */         this.channel.pipeline().remove("compress");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleDisconnection() {
/* 313 */     if (this.channel != null && !this.channel.isOpen()) {
/* 314 */       if (this.p) {
/* 315 */         g.warn("handleDisconnection() called twice");
/*     */       } else {
/* 317 */         this.p = true;
/* 318 */         if (j() != null) {
/* 319 */           i().a(j());
/* 320 */         } else if (i() != null) {
/* 321 */           i().a(new ChatMessage("multiplayer.disconnect.generic", new Object[0]));
/*     */         } 
/* 323 */         this.i.clear();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void channelRead0(ChannelHandlerContext channelhandlercontext, Packet<?> object) throws Exception {
/* 330 */     a(channelhandlercontext, object);
/*     */   }
/*     */   
/*     */   static class QueuedPacket
/*     */   {
/*     */     private final Packet<?> a;
/*     */     private final GenericFutureListener<? extends Future<? super Void>>[] b;
/*     */     
/*     */     public QueuedPacket(Packet<?> packet, GenericFutureListener... agenericfuturelistener) {
/* 339 */       this.a = packet;
/* 340 */       this.b = (GenericFutureListener<? extends Future<? super Void>>[])agenericfuturelistener;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketAddress getRawAddress() {
/* 347 */     return this.channel.remoteAddress();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NetworkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */