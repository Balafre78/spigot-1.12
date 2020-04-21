/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BossBattleServer
/*     */   extends BossBattle
/*     */ {
/*  15 */   private final Set<EntityPlayer> h = Sets.newHashSet();
/*  16 */   private final Set<EntityPlayer> i = Collections.unmodifiableSet(this.h);
/*     */   public boolean visible = true;
/*     */   
/*     */   public BossBattleServer(IChatBaseComponent paramIChatBaseComponent, BossBattle.BarColor paramBarColor, BossBattle.BarStyle paramBarStyle) {
/*  20 */     super(MathHelper.a(), paramIChatBaseComponent, paramBarColor, paramBarStyle);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgress(float paramFloat) {
/*  25 */     if (paramFloat != this.b) {
/*  26 */       a(paramFloat);
/*  27 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PCT);
/*     */     } 
/*     */   }
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
/*     */   public BossBattle setDarkenSky(boolean paramBoolean) {
/*  49 */     if (paramBoolean != this.e) {
/*  50 */       a(paramBoolean);
/*  51 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
/*     */     } 
/*  53 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossBattle setPlayMusic(boolean paramBoolean) {
/*  58 */     if (paramBoolean != this.f) {
/*  59 */       b(paramBoolean);
/*  60 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
/*     */     } 
/*  62 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossBattle setCreateFog(boolean paramBoolean) {
/*  67 */     if (paramBoolean != this.g) {
/*  68 */       c(paramBoolean);
/*  69 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
/*     */     } 
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IChatBaseComponent paramIChatBaseComponent) {
/*  76 */     if (!Objects.equal(paramIChatBaseComponent, this.title)) {
/*  77 */       super.a(paramIChatBaseComponent);
/*  78 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_NAME);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendUpdate(PacketPlayOutBoss.Action paramAction) {
/*  83 */     if (this.visible) {
/*  84 */       PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss(paramAction, this);
/*  85 */       for (EntityPlayer entityPlayer : this.h) {
/*  86 */         entityPlayer.playerConnection.sendPacket(packetPlayOutBoss);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPlayer(EntityPlayer paramEntityPlayer) {
/*  92 */     if (this.h.add(paramEntityPlayer) && this.visible) {
/*  93 */       paramEntityPlayer.playerConnection.sendPacket(new PacketPlayOutBoss(PacketPlayOutBoss.Action.ADD, this));
/*     */     }
/*     */   }
/*     */   
/*     */   public void removePlayer(EntityPlayer paramEntityPlayer) {
/*  98 */     if (this.h.remove(paramEntityPlayer) && this.visible) {
/*  99 */       paramEntityPlayer.playerConnection.sendPacket(new PacketPlayOutBoss(PacketPlayOutBoss.Action.REMOVE, this));
/*     */     }
/*     */   }
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
/*     */   public void setVisible(boolean paramBoolean) {
/* 116 */     if (paramBoolean != this.visible) {
/* 117 */       this.visible = paramBoolean;
/*     */       
/* 119 */       for (EntityPlayer entityPlayer : this.h) {
/* 120 */         entityPlayer.playerConnection.sendPacket(new PacketPlayOutBoss(paramBoolean ? PacketPlayOutBoss.Action.ADD : PacketPlayOutBoss.Action.REMOVE, this));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection<EntityPlayer> getPlayers() {
/* 126 */     return this.i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BossBattleServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */