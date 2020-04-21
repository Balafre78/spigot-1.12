/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutPlayerInfo
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private EnumPlayerInfoAction a;
/*  20 */   private final List<PlayerInfoData> b = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PacketPlayOutPlayerInfo(EnumPlayerInfoAction paramEnumPlayerInfoAction, EntityPlayer... paramVarArgs) {
/*  27 */     this.a = paramEnumPlayerInfoAction;
/*     */     
/*  29 */     for (EntityPlayer entityPlayer : paramVarArgs) {
/*  30 */       this.b.add(new PlayerInfoData(this, entityPlayer.getProfile(), entityPlayer.ping, entityPlayer.playerInteractManager.getGameMode(), entityPlayer.getPlayerListName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public PacketPlayOutPlayerInfo(EnumPlayerInfoAction paramEnumPlayerInfoAction, Iterable<EntityPlayer> paramIterable) {
/*  35 */     this.a = paramEnumPlayerInfoAction;
/*     */     
/*  37 */     for (EntityPlayer entityPlayer : paramIterable) {
/*  38 */       this.b.add(new PlayerInfoData(this, entityPlayer.getProfile(), entityPlayer.ping, entityPlayer.playerInteractManager.getGameMode(), entityPlayer.getPlayerListName()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  44 */     this.a = paramPacketDataSerializer.<EnumPlayerInfoAction>a(EnumPlayerInfoAction.class);
/*     */     
/*  46 */     int i = paramPacketDataSerializer.g();
/*  47 */     for (byte b = 0; b < i; b++) {
/*  48 */       int k; byte b1; GameProfile gameProfile = null;
/*  49 */       int j = 0;
/*  50 */       EnumGamemode enumGamemode = null;
/*  51 */       IChatBaseComponent iChatBaseComponent = null;
/*     */       
/*  53 */       switch (null.a[this.a.ordinal()]) {
/*     */         case 1:
/*  55 */           gameProfile = new GameProfile(paramPacketDataSerializer.i(), paramPacketDataSerializer.e(16));
/*  56 */           k = paramPacketDataSerializer.g();
/*  57 */           for (b1 = 0; b1 < k; b1++) {
/*  58 */             String str1 = paramPacketDataSerializer.e(32767);
/*  59 */             String str2 = paramPacketDataSerializer.e(32767);
/*     */             
/*  61 */             if (paramPacketDataSerializer.readBoolean()) {
/*  62 */               gameProfile.getProperties().put(str1, new Property(str1, str2, paramPacketDataSerializer.e(32767)));
/*     */             } else {
/*  64 */               gameProfile.getProperties().put(str1, new Property(str1, str2));
/*     */             } 
/*     */           } 
/*  67 */           enumGamemode = EnumGamemode.getById(paramPacketDataSerializer.g());
/*  68 */           j = paramPacketDataSerializer.g();
/*  69 */           if (paramPacketDataSerializer.readBoolean()) {
/*  70 */             iChatBaseComponent = paramPacketDataSerializer.f();
/*     */           }
/*     */           break;
/*     */         case 2:
/*  74 */           gameProfile = new GameProfile(paramPacketDataSerializer.i(), null);
/*  75 */           enumGamemode = EnumGamemode.getById(paramPacketDataSerializer.g());
/*     */           break;
/*     */         case 3:
/*  78 */           gameProfile = new GameProfile(paramPacketDataSerializer.i(), null);
/*  79 */           j = paramPacketDataSerializer.g();
/*     */           break;
/*     */         case 4:
/*  82 */           gameProfile = new GameProfile(paramPacketDataSerializer.i(), null);
/*  83 */           if (paramPacketDataSerializer.readBoolean()) {
/*  84 */             iChatBaseComponent = paramPacketDataSerializer.f();
/*     */           }
/*     */           break;
/*     */         case 5:
/*  88 */           gameProfile = new GameProfile(paramPacketDataSerializer.i(), null);
/*     */           break;
/*     */       } 
/*     */       
/*  92 */       this.b.add(new PlayerInfoData(this, gameProfile, j, enumGamemode, iChatBaseComponent));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  98 */     paramPacketDataSerializer.a(this.a);
/*     */     
/* 100 */     paramPacketDataSerializer.d(this.b.size());
/* 101 */     for (PlayerInfoData playerInfoData : this.b) {
/* 102 */       switch (null.a[this.a.ordinal()]) {
/*     */         case 1:
/* 104 */           paramPacketDataSerializer.a(playerInfoData.a().getId());
/* 105 */           paramPacketDataSerializer.a(playerInfoData.a().getName());
/* 106 */           paramPacketDataSerializer.d(playerInfoData.a().getProperties().size());
/* 107 */           for (Property property : playerInfoData.a().getProperties().values()) {
/* 108 */             paramPacketDataSerializer.a(property.getName());
/* 109 */             paramPacketDataSerializer.a(property.getValue());
/* 110 */             if (property.hasSignature()) {
/* 111 */               paramPacketDataSerializer.writeBoolean(true);
/* 112 */               paramPacketDataSerializer.a(property.getSignature()); continue;
/*     */             } 
/* 114 */             paramPacketDataSerializer.writeBoolean(false);
/*     */           } 
/*     */           
/* 117 */           paramPacketDataSerializer.d(playerInfoData.c().getId());
/* 118 */           paramPacketDataSerializer.d(playerInfoData.b());
/*     */           
/* 120 */           if (playerInfoData.d() == null) {
/* 121 */             paramPacketDataSerializer.writeBoolean(false); continue;
/*     */           } 
/* 123 */           paramPacketDataSerializer.writeBoolean(true);
/* 124 */           paramPacketDataSerializer.a(playerInfoData.d());
/*     */ 
/*     */         
/*     */         case 2:
/* 128 */           paramPacketDataSerializer.a(playerInfoData.a().getId());
/* 129 */           paramPacketDataSerializer.d(playerInfoData.c().getId());
/*     */         
/*     */         case 3:
/* 132 */           paramPacketDataSerializer.a(playerInfoData.a().getId());
/* 133 */           paramPacketDataSerializer.d(playerInfoData.b());
/*     */         
/*     */         case 4:
/* 136 */           paramPacketDataSerializer.a(playerInfoData.a().getId());
/* 137 */           if (playerInfoData.d() == null) {
/* 138 */             paramPacketDataSerializer.writeBoolean(false); continue;
/*     */           } 
/* 140 */           paramPacketDataSerializer.writeBoolean(true);
/* 141 */           paramPacketDataSerializer.a(playerInfoData.d());
/*     */ 
/*     */         
/*     */         case 5:
/* 145 */           paramPacketDataSerializer.a(playerInfoData.a().getId());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 153 */     paramPacketListenerPlayOut.a(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumPlayerInfoAction
/*     */   {
/* 165 */     ADD_PLAYER,
/* 166 */     UPDATE_GAME_MODE,
/* 167 */     UPDATE_LATENCY,
/* 168 */     UPDATE_DISPLAY_NAME,
/* 169 */     REMOVE_PLAYER;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 175 */     return MoreObjects.toStringHelper(this)
/* 176 */       .add("action", this.a)
/* 177 */       .add("entries", this.b)
/* 178 */       .toString();
/*     */   }
/*     */   public PacketPlayOutPlayerInfo() {}
/*     */   
/*     */   public class PlayerInfoData { private final int b;
/*     */     private final EnumGamemode c;
/*     */     private final GameProfile d;
/*     */     private final IChatBaseComponent e;
/*     */     
/*     */     public PlayerInfoData(PacketPlayOutPlayerInfo this$0, GameProfile param1GameProfile, int param1Int, @Nullable EnumGamemode param1EnumGamemode, @Nullable IChatBaseComponent param1IChatBaseComponent) {
/* 188 */       this.d = param1GameProfile;
/* 189 */       this.b = param1Int;
/* 190 */       this.c = param1EnumGamemode;
/* 191 */       this.e = param1IChatBaseComponent;
/*     */     }
/*     */     
/*     */     public GameProfile a() {
/* 195 */       return this.d;
/*     */     }
/*     */     
/*     */     public int b() {
/* 199 */       return this.b;
/*     */     }
/*     */     
/*     */     public EnumGamemode c() {
/* 203 */       return this.c;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public IChatBaseComponent d() {
/* 208 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 213 */       return MoreObjects.toStringHelper(this)
/* 214 */         .add("latency", this.b)
/* 215 */         .add("gameMode", this.c)
/* 216 */         .add("profile", this.d)
/* 217 */         .add("displayName", (this.e == null) ? null : IChatBaseComponent.ChatSerializer.a(this.e))
/* 218 */         .toString();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutPlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */