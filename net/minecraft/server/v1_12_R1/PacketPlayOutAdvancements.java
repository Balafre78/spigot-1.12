/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.IOException;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutAdvancements
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private boolean a;
/*    */   private Map<MinecraftKey, Advancement.SerializedAdvancement> b;
/*    */   private Set<MinecraftKey> c;
/*    */   private Map<MinecraftKey, AdvancementProgress> d;
/*    */   
/*    */   public PacketPlayOutAdvancements() {}
/*    */   
/*    */   public PacketPlayOutAdvancements(boolean paramBoolean, Collection<Advancement> paramCollection, Set<MinecraftKey> paramSet, Map<MinecraftKey, AdvancementProgress> paramMap) {
/* 27 */     this.a = paramBoolean;
/* 28 */     this.b = Maps.newHashMap();
/* 29 */     for (Advancement advancement : paramCollection) {
/* 30 */       this.b.put(advancement.getName(), advancement.a());
/*    */     }
/* 32 */     this.c = paramSet;
/* 33 */     this.d = Maps.newHashMap(paramMap);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 38 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 43 */     this.a = paramPacketDataSerializer.readBoolean();
/* 44 */     this.b = Maps.newHashMap();
/* 45 */     this.c = Sets.newLinkedHashSet();
/* 46 */     this.d = Maps.newHashMap();
/*    */     
/* 48 */     int i = paramPacketDataSerializer.g(); byte b;
/* 49 */     for (b = 0; b < i; b++) {
/* 50 */       MinecraftKey minecraftKey = paramPacketDataSerializer.l();
/* 51 */       Advancement.SerializedAdvancement serializedAdvancement = Advancement.SerializedAdvancement.b(paramPacketDataSerializer);
/* 52 */       this.b.put(minecraftKey, serializedAdvancement);
/*    */     } 
/*    */     
/* 55 */     i = paramPacketDataSerializer.g();
/* 56 */     for (b = 0; b < i; b++) {
/* 57 */       MinecraftKey minecraftKey = paramPacketDataSerializer.l();
/* 58 */       this.c.add(minecraftKey);
/*    */     } 
/*    */     
/* 61 */     i = paramPacketDataSerializer.g();
/* 62 */     for (b = 0; b < i; b++) {
/* 63 */       MinecraftKey minecraftKey = paramPacketDataSerializer.l();
/* 64 */       this.d.put(minecraftKey, AdvancementProgress.b(paramPacketDataSerializer));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 70 */     paramPacketDataSerializer.writeBoolean(this.a);
/*    */     
/* 72 */     paramPacketDataSerializer.d(this.b.size());
/* 73 */     for (Map.Entry<MinecraftKey, Advancement.SerializedAdvancement> entry : this.b.entrySet()) {
/* 74 */       MinecraftKey minecraftKey = (MinecraftKey)entry.getKey();
/* 75 */       Advancement.SerializedAdvancement serializedAdvancement = (Advancement.SerializedAdvancement)entry.getValue();
/* 76 */       paramPacketDataSerializer.a(minecraftKey);
/* 77 */       serializedAdvancement.a(paramPacketDataSerializer);
/*    */     } 
/*    */     
/* 80 */     paramPacketDataSerializer.d(this.c.size());
/* 81 */     for (MinecraftKey minecraftKey : this.c) {
/* 82 */       paramPacketDataSerializer.a(minecraftKey);
/*    */     }
/*    */     
/* 85 */     paramPacketDataSerializer.d(this.d.size());
/* 86 */     for (Map.Entry<MinecraftKey, AdvancementProgress> entry : this.d.entrySet()) {
/* 87 */       paramPacketDataSerializer.a((MinecraftKey)entry.getKey());
/* 88 */       ((AdvancementProgress)entry.getValue()).a(paramPacketDataSerializer);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutAdvancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */