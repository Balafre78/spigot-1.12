/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.gson.Gson;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class HandshakeListener implements PacketHandshakingInListener {
/*  10 */   private static final Gson gson = new Gson();
/*     */   
/*  12 */   private static final HashMap<InetAddress, Long> throttleTracker = new HashMap<>();
/*  13 */   private static int throttleCounter = 0;
/*     */   
/*     */   private final MinecraftServer a;
/*     */   
/*     */   private final NetworkManager b;
/*     */   
/*     */   public HandshakeListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
/*  20 */     this.a = minecraftserver;
/*  21 */     this.b = networkmanager;
/*     */   }
/*     */   
/*     */   public void a(PacketHandshakingInSetProtocol packethandshakinginsetprotocol) {
/*  25 */     switch (packethandshakinginsetprotocol.a()) {
/*     */       case LOGIN:
/*  27 */         this.b.setProtocol(EnumProtocol.LOGIN);
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/*  32 */           long currentTime = System.currentTimeMillis();
/*  33 */           long connectionThrottle = (MinecraftServer.getServer()).server.getConnectionThrottle();
/*  34 */           InetAddress address = ((InetSocketAddress)this.b.getSocketAddress()).getAddress();
/*     */           
/*  36 */           synchronized (throttleTracker) {
/*  37 */             if (throttleTracker.containsKey(address) && !"127.0.0.1".equals(address.getHostAddress()) && currentTime - ((Long)throttleTracker.get(address)).longValue() < connectionThrottle) {
/*  38 */               throttleTracker.put(address, Long.valueOf(currentTime));
/*  39 */               ChatMessage chatmessage = new ChatMessage("Connection throttled! Please wait before reconnecting.", new Object[0]);
/*  40 */               this.b.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  41 */               this.b.close(chatmessage);
/*     */               
/*     */               return;
/*     */             } 
/*  45 */             throttleTracker.put(address, Long.valueOf(currentTime));
/*  46 */             throttleCounter++;
/*  47 */             if (throttleCounter > 200) {
/*  48 */               throttleCounter = 0;
/*     */ 
/*     */               
/*  51 */               Iterator<Map.Entry<InetAddress, Long>> iter = throttleTracker.entrySet().iterator();
/*  52 */               while (iter.hasNext()) {
/*  53 */                 Map.Entry<InetAddress, Long> entry = iter.next();
/*  54 */                 if (((Long)entry.getValue()).longValue() > connectionThrottle) {
/*  55 */                   iter.remove();
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*  60 */         } catch (Throwable t) {
/*  61 */           LogManager.getLogger().debug("Failed to check connection throttle", t);
/*     */         } 
/*     */ 
/*     */         
/*  65 */         if (packethandshakinginsetprotocol.b() > 335) {
/*  66 */           ChatMessage chatmessage = new ChatMessage(MessageFormat.format(SpigotConfig.outdatedServerMessage.replaceAll("'", "''"), new Object[] { "1.12" }), new Object[0]);
/*  67 */           this.b.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  68 */           this.b.close(chatmessage);
/*  69 */         } else if (packethandshakinginsetprotocol.b() < 335) {
/*  70 */           ChatMessage chatmessage = new ChatMessage(MessageFormat.format(SpigotConfig.outdatedClientMessage.replaceAll("'", "''"), new Object[] { "1.12" }), new Object[0]);
/*  71 */           this.b.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  72 */           this.b.close(chatmessage);
/*     */         } else {
/*  74 */           this.b.setPacketListener(new LoginListener(this.a, this.b));
/*     */           
/*  76 */           if (SpigotConfig.bungee) {
/*  77 */             String[] split = packethandshakinginsetprotocol.hostname.split("\000");
/*  78 */             if (split.length == 3 || split.length == 4) {
/*  79 */               packethandshakinginsetprotocol.hostname = split[0];
/*  80 */               this.b.l = new InetSocketAddress(split[1], ((InetSocketAddress)this.b.getSocketAddress()).getPort());
/*  81 */               this.b.spoofedUUID = UUIDTypeAdapter.fromString(split[2]);
/*     */             } else {
/*     */               
/*  84 */               ChatMessage chatmessage = new ChatMessage("If you wish to use IP forwarding, please enable it in your BungeeCord config as well!", new Object[0]);
/*  85 */               this.b.sendPacket(new PacketLoginOutDisconnect(chatmessage));
/*  86 */               this.b.close(chatmessage);
/*     */               return;
/*     */             } 
/*  89 */             if (split.length == 4)
/*     */             {
/*  91 */               this.b.spoofedProfile = (Property[])gson.fromJson(split[3], Property[].class);
/*     */             }
/*     */           } 
/*     */           
/*  95 */           ((LoginListener)this.b.i()).hostname = String.valueOf(packethandshakinginsetprotocol.hostname) + ":" + packethandshakinginsetprotocol.port;
/*     */         } 
/*     */         return;
/*     */       
/*     */       case STATUS:
/* 100 */         this.b.setProtocol(EnumProtocol.STATUS);
/* 101 */         this.b.setPacketListener(new PacketStatusListener(this.a, this.b));
/*     */         return;
/*     */     } 
/*     */     
/* 105 */     throw new UnsupportedOperationException("Invalid intention " + packethandshakinginsetprotocol.a());
/*     */   }
/*     */   
/*     */   public void a(IChatBaseComponent ichatbasecomponent) {}
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\HandshakeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */