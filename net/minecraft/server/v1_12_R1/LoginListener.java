/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.math.BigInteger;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.security.PrivateKey;
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.logging.Level;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.crypto.SecretKey;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.Waitable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
/*     */ import org.bukkit.event.player.PlayerPreLoginEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoginListener
/*     */   implements PacketLoginInListener, ITickable
/*     */ {
/*     */   public class LoginHandler
/*     */   {
/*     */     public void fireEvents() throws Exception {
/* 265 */       String playerName = LoginListener.this.i.getName();
/* 266 */       InetAddress address = ((InetSocketAddress)LoginListener.this.networkManager.getSocketAddress()).getAddress();
/* 267 */       UUID uniqueId = LoginListener.this.i.getId();
/* 268 */       final CraftServer server = LoginListener.this.server.server;
/*     */       
/* 270 */       AsyncPlayerPreLoginEvent asyncEvent = new AsyncPlayerPreLoginEvent(playerName, address, uniqueId);
/* 271 */       server.getPluginManager().callEvent((Event)asyncEvent);
/*     */       
/* 273 */       if ((PlayerPreLoginEvent.getHandlerList().getRegisteredListeners()).length != 0) {
/* 274 */         final PlayerPreLoginEvent event = new PlayerPreLoginEvent(playerName, address, uniqueId);
/* 275 */         if (asyncEvent.getResult() != PlayerPreLoginEvent.Result.ALLOWED) {
/* 276 */           event.disallow(asyncEvent.getResult(), asyncEvent.getKickMessage());
/*     */         }
/* 278 */         Waitable<PlayerPreLoginEvent.Result> waitable = new Waitable<PlayerPreLoginEvent.Result>()
/*     */           {
/*     */             protected PlayerPreLoginEvent.Result evaluate() {
/* 281 */               server.getPluginManager().callEvent((Event)event);
/* 282 */               return event.getResult();
/*     */             }
/*     */           };
/* 285 */         LoginListener.this.server.processQueue.add(waitable);
/* 286 */         if (waitable.get() != PlayerPreLoginEvent.Result.ALLOWED) {
/* 287 */           LoginListener.this.disconnect(event.getKickMessage());
/*     */           
/*     */           return;
/*     */         } 
/* 291 */       } else if (asyncEvent.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
/* 292 */         LoginListener.this.disconnect(asyncEvent.getKickMessage());
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 297 */       LoginListener.c.info("UUID of player {} is {}", LoginListener.this.i.getName(), LoginListener.this.i.getId());
/* 298 */       LoginListener.this.g = LoginListener.EnumProtocolState.READY_TO_ACCEPT;
/*     */     }
/*     */   }
/*     */   private static final AtomicInteger b = new AtomicInteger(0);
/*     */   private static final Logger c = LogManager.getLogger(); private static final Random random = new Random(); private final byte[] e = new byte[4]; private final MinecraftServer server; public final NetworkManager networkManager; private EnumProtocolState g; private int h; private GameProfile i; private final String j; private SecretKey loginKey; private EntityPlayer l; public String hostname = ""; public LoginListener(MinecraftServer minecraftserver, NetworkManager networkmanager) { this.g = EnumProtocolState.HELLO; this.j = ""; this.server = minecraftserver; this.networkManager = networkmanager; random.nextBytes(this.e); } public void e() { if (this.g == EnumProtocolState.READY_TO_ACCEPT) { b(); } else if (this.g == EnumProtocolState.DELAY_ACCEPT) { EntityPlayer entityplayer = this.server.getPlayerList().a(this.i.getId()); if (entityplayer == null) { this.g = EnumProtocolState.READY_TO_ACCEPT; this.server.getPlayerList().a(this.networkManager, this.l); this.l = null; }  }  if (this.h++ == 600) disconnect(new ChatMessage("multiplayer.disconnect.slow_login", new Object[0]));  } @Deprecated public void disconnect(String s) { try { IChatBaseComponent ichatbasecomponent = new ChatComponentText(s); c.info("Disconnecting {}: {}", c(), s); this.networkManager.sendPacket(new PacketLoginOutDisconnect(ichatbasecomponent)); this.networkManager.close(ichatbasecomponent); } catch (Exception exception) { c.error("Error whilst disconnecting player", exception); }  } public void disconnect(IChatBaseComponent ichatbasecomponent) { try { c.info("Disconnecting {}: {}", c(), ichatbasecomponent.toPlainText()); this.networkManager.sendPacket(new PacketLoginOutDisconnect(ichatbasecomponent)); this.networkManager.close(ichatbasecomponent); } catch (Exception exception) { c.error("Error whilst disconnecting player", exception); }  } public void initUUID() { UUID uuid; if (this.networkManager.spoofedUUID != null) { uuid = this.networkManager.spoofedUUID; } else { uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.i.getName()).getBytes(StandardCharsets.UTF_8)); }  this.i = new GameProfile(uuid, this.i.getName()); if (this.networkManager.spoofedProfile != null) { Property[] arrayOfProperty; int i; byte b; for (i = (arrayOfProperty = this.networkManager.spoofedProfile).length, b = 0; b < i; ) { Property property = arrayOfProperty[b]; this.i.getProperties().put(property.getName(), property); b++; }  }  } public void b() { EntityPlayer s = this.server.getPlayerList().attemptLogin(this, this.i, this.hostname); if (s != null) { this.g = EnumProtocolState.ACCEPTED; if (this.server.aG() >= 0 && !this.networkManager.isLocal()) this.networkManager.sendPacket(new PacketLoginOutSetCompression(this.server.aG()), (GenericFutureListener<? extends Future<? super Void>>)new ChannelFutureListener() {
/*     */               public void a(ChannelFuture channelfuture) throws Exception { LoginListener.this.networkManager.setCompressionLevel(LoginListener.this.server.aG()); } public void operationComplete(ChannelFuture future) throws Exception { a(future); }
/* 304 */             },  (GenericFutureListener<? extends Future<? super Void>>[])new GenericFutureListener[0]);  this.networkManager.sendPacket(new PacketLoginOutSuccess(this.i)); EntityPlayer entityplayer = this.server.getPlayerList().a(this.i.getId()); if (entityplayer != null) { this.g = EnumProtocolState.DELAY_ACCEPT; this.l = this.server.getPlayerList().processLogin(this.i, s); } else { this.server.getPlayerList().a(this.networkManager, this.server.getPlayerList().processLogin(this.i, s)); }  }  } public void a(IChatBaseComponent ichatbasecomponent) { c.info("{} lost connection: {}", c(), ichatbasecomponent.toPlainText()); } protected GameProfile a(GameProfile gameprofile) { UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + gameprofile.getName()).getBytes(StandardCharsets.UTF_8));
/*     */     
/* 306 */     return new GameProfile(uuid, gameprofile.getName()); } public String c() { return (this.i != null) ? (this.i + " (" + this.networkManager.getSocketAddress() + ")") : String.valueOf(this.networkManager.getSocketAddress()); } public void a(PacketLoginInStart packetlogininstart) { Validate.validState((this.g == EnumProtocolState.HELLO), "Unexpected hello packet", new Object[0]); this.i = packetlogininstart.a(); if (this.server.getOnlineMode() && !this.networkManager.isLocal()) { this.g = EnumProtocolState.KEY; this.networkManager.sendPacket(new PacketLoginOutEncryptionBegin("", this.server.O().getPublic(), this.e)); } else { (new Thread("User Authenticator #" + b.incrementAndGet()) {
/*     */           public void run() { try { LoginListener.this.initUUID(); (new LoginListener.LoginHandler()).fireEvents(); LoginListener.this.g = LoginListener.EnumProtocolState.READY_TO_ACCEPT; } catch (Exception ex) { LoginListener.this.disconnect("Failed to verify username!"); LoginListener.this.server.server.getLogger().log(Level.WARNING, "Exception verifying " + LoginListener.this.i.getName(), ex); }  }
/*     */         }).start(); }  } public void a(PacketLoginInEncryptionBegin packetlogininencryptionbegin) { Validate.validState((this.g == EnumProtocolState.KEY), "Unexpected key packet", new Object[0]); PrivateKey privatekey = this.server.O().getPrivate(); if (!Arrays.equals(this.e, packetlogininencryptionbegin.b(privatekey))) throw new IllegalStateException("Invalid nonce!");  this.loginKey = packetlogininencryptionbegin.a(privatekey); this.g = EnumProtocolState.AUTHENTICATING; this.networkManager.a(this.loginKey); (new Thread("User Authenticator #" + b.incrementAndGet()) {
/*     */         public void run() { GameProfile gameprofile = LoginListener.this.i; try { String s = (new BigInteger(MinecraftEncryption.a("", LoginListener.this.server.O().getPublic(), LoginListener.this.loginKey))).toString(16); LoginListener.this.i = LoginListener.this.server.az().hasJoinedServer(new GameProfile(null, gameprofile.getName()), s, a()); if (LoginListener.this.i != null) { if (!LoginListener.this.networkManager.isConnected()) return;  (new LoginListener.LoginHandler()).fireEvents(); } else if (LoginListener.this.server.R()) { LoginListener.c.warn("Failed to verify username but will let them in anyway!"); LoginListener.this.i = LoginListener.this.a(gameprofile); LoginListener.this.g = LoginListener.EnumProtocolState.READY_TO_ACCEPT; } else { LoginListener.this.disconnect(new ChatMessage("multiplayer.disconnect.unverified_username", new Object[0])); LoginListener.c.error("Username '{}' tried to join with an invalid session", gameprofile.getName()); }  } catch (AuthenticationUnavailableException authenticationUnavailableException) { if (LoginListener.this.server.R()) { LoginListener.c.warn("Authentication servers are down but will let them in anyway!"); LoginListener.this.i = LoginListener.this.a(gameprofile); LoginListener.this.g = LoginListener.EnumProtocolState.READY_TO_ACCEPT; } else { LoginListener.this.disconnect(new ChatMessage("multiplayer.disconnect.authservers_down", new Object[0])); LoginListener.c.error("Couldn't verify username because servers are unavailable"); }  } catch (Exception exception) { LoginListener.this.disconnect("Failed to verify username!"); LoginListener.this.server.server.getLogger().log(Level.WARNING, "Exception verifying " + gameprofile.getName(), exception); }  } @Nullable private InetAddress a() { SocketAddress socketaddress = LoginListener.this.networkManager.getSocketAddress(); return (LoginListener.this.server.ac() && socketaddress instanceof InetSocketAddress) ? ((InetSocketAddress)socketaddress).getAddress() : null; }
/*     */       }).start(); } enum EnumProtocolState {
/* 311 */     HELLO, KEY, AUTHENTICATING, READY_TO_ACCEPT, DELAY_ACCEPT, ACCEPTED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LoginListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */