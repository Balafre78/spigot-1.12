/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketPlayOutCombatEvent
/*    */   implements Packet<PacketListenerPlayOut> {
/*    */   public EnumCombatEventType a;
/*    */   public int b;
/*    */   public int c;
/*    */   public int d;
/*    */   public IChatBaseComponent e;
/*    */   
/*    */   public enum EnumCombatEventType {
/* 14 */     ENTER_COMBAT,
/* 15 */     END_COMBAT,
/* 16 */     ENTITY_DIED;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutCombatEvent() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayOutCombatEvent(CombatTracker paramCombatTracker, EnumCombatEventType paramEnumCombatEventType) {
/* 31 */     this(paramCombatTracker, paramEnumCombatEventType, true);
/*    */   }
/*    */   
/*    */   public PacketPlayOutCombatEvent(CombatTracker paramCombatTracker, EnumCombatEventType paramEnumCombatEventType, boolean paramBoolean) {
/* 35 */     this.a = paramEnumCombatEventType;
/*    */     
/* 37 */     EntityLiving entityLiving = paramCombatTracker.c();
/*    */     
/* 39 */     switch (null.a[paramEnumCombatEventType.ordinal()]) {
/*    */       case 1:
/* 41 */         this.d = paramCombatTracker.f();
/* 42 */         this.c = (entityLiving == null) ? -1 : entityLiving.getId();
/*    */         break;
/*    */       case 2:
/* 45 */         this.b = paramCombatTracker.h().getId();
/* 46 */         this.c = (entityLiving == null) ? -1 : entityLiving.getId();
/* 47 */         if (paramBoolean) {
/* 48 */           this.e = paramCombatTracker.getDeathMessage(); break;
/*    */         } 
/* 50 */         this.e = new ChatComponentText("");
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 58 */     this.a = paramPacketDataSerializer.<EnumCombatEventType>a(EnumCombatEventType.class);
/*    */     
/* 60 */     if (this.a == EnumCombatEventType.END_COMBAT) {
/* 61 */       this.d = paramPacketDataSerializer.g();
/* 62 */       this.c = paramPacketDataSerializer.readInt();
/* 63 */     } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
/* 64 */       this.b = paramPacketDataSerializer.g();
/* 65 */       this.c = paramPacketDataSerializer.readInt();
/* 66 */       this.e = paramPacketDataSerializer.f();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 72 */     paramPacketDataSerializer.a(this.a);
/*    */     
/* 74 */     if (this.a == EnumCombatEventType.END_COMBAT) {
/* 75 */       paramPacketDataSerializer.d(this.d);
/* 76 */       paramPacketDataSerializer.writeInt(this.c);
/* 77 */     } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
/* 78 */       paramPacketDataSerializer.d(this.b);
/* 79 */       paramPacketDataSerializer.writeInt(this.c);
/* 80 */       paramPacketDataSerializer.a(this.e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 86 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutCombatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */