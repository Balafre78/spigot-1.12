/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.UUID;
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
/*     */ public class PacketPlayOutBoss
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private UUID a;
/*     */   private Action b;
/*     */   private IChatBaseComponent c;
/*     */   private float d;
/*     */   private BossBattle.BarColor e;
/*     */   private BossBattle.BarStyle f;
/*     */   private boolean g;
/*     */   private boolean h;
/*     */   private boolean i;
/*     */   
/*     */   public PacketPlayOutBoss() {}
/*     */   
/*     */   public PacketPlayOutBoss(Action paramAction, BossBattle paramBossBattle) {
/*  31 */     this.b = paramAction;
/*  32 */     this.a = paramBossBattle.d();
/*  33 */     this.c = paramBossBattle.e();
/*  34 */     this.d = paramBossBattle.getProgress();
/*  35 */     this.e = paramBossBattle.g();
/*  36 */     this.f = paramBossBattle.h();
/*  37 */     this.g = paramBossBattle.i();
/*  38 */     this.h = paramBossBattle.j();
/*  39 */     this.i = paramBossBattle.k();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  44 */     this.a = paramPacketDataSerializer.i();
/*  45 */     this.b = paramPacketDataSerializer.<Action>a(Action.class);
/*     */     
/*  47 */     switch (null.a[this.b.ordinal()]) {
/*     */       case 1:
/*  49 */         this.c = paramPacketDataSerializer.f();
/*  50 */         this.d = paramPacketDataSerializer.readFloat();
/*  51 */         this.e = paramPacketDataSerializer.<BossBattle.BarColor>a(BossBattle.BarColor.class);
/*  52 */         this.f = paramPacketDataSerializer.<BossBattle.BarStyle>a(BossBattle.BarStyle.class);
/*  53 */         a(paramPacketDataSerializer.readUnsignedByte());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/*  58 */         this.d = paramPacketDataSerializer.readFloat();
/*     */         break;
/*     */       case 4:
/*  61 */         this.c = paramPacketDataSerializer.f();
/*     */         break;
/*     */       case 5:
/*  64 */         this.e = paramPacketDataSerializer.<BossBattle.BarColor>a(BossBattle.BarColor.class);
/*  65 */         this.f = paramPacketDataSerializer.<BossBattle.BarStyle>a(BossBattle.BarStyle.class);
/*     */         break;
/*     */       case 6:
/*  68 */         a(paramPacketDataSerializer.readUnsignedByte());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(int paramInt) {
/*  74 */     this.g = ((paramInt & 0x1) > 0);
/*  75 */     this.h = ((paramInt & 0x2) > 0);
/*  76 */     this.i = ((paramInt & 0x2) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  81 */     paramPacketDataSerializer.a(this.a);
/*  82 */     paramPacketDataSerializer.a(this.b);
/*     */     
/*  84 */     switch (null.a[this.b.ordinal()]) {
/*     */       case 1:
/*  86 */         paramPacketDataSerializer.a(this.c);
/*  87 */         paramPacketDataSerializer.writeFloat(this.d);
/*  88 */         paramPacketDataSerializer.a(this.e);
/*  89 */         paramPacketDataSerializer.a(this.f);
/*  90 */         paramPacketDataSerializer.writeByte(j());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/*  95 */         paramPacketDataSerializer.writeFloat(this.d);
/*     */         break;
/*     */       case 4:
/*  98 */         paramPacketDataSerializer.a(this.c);
/*     */         break;
/*     */       case 5:
/* 101 */         paramPacketDataSerializer.a(this.e);
/* 102 */         paramPacketDataSerializer.a(this.f);
/*     */         break;
/*     */       case 6:
/* 105 */         paramPacketDataSerializer.writeByte(j());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int j() {
/* 111 */     int i = 0;
/* 112 */     if (this.g) {
/* 113 */       i |= 0x1;
/*     */     }
/* 115 */     if (this.h) {
/* 116 */       i |= 0x2;
/*     */     }
/* 118 */     if (this.i) {
/* 119 */       i |= 0x2;
/*     */     }
/* 121 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 126 */     paramPacketListenerPlayOut.a(this);
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
/*     */   public enum Action
/*     */   {
/* 166 */     ADD,
/* 167 */     REMOVE,
/* 168 */     UPDATE_PCT,
/* 169 */     UPDATE_NAME,
/* 170 */     UPDATE_STYLE,
/* 171 */     UPDATE_PROPERTIES;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */