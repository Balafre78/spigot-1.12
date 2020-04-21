/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumProtocol
/*     */ {
/* 131 */   HANDSHAKING(-1) { EnumProtocol(int param1Int1) {
/* 132 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketHandshakingInSetProtocol.class);
/*     */     } },
/* 134 */   PLAY(0) { EnumProtocol(int param1Int1) {
/* 135 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSpawnEntity.class);
/* 136 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSpawnEntityExperienceOrb.class);
/* 137 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSpawnEntityWeather.class);
/* 138 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSpawnEntityLiving.class);
/* 139 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSpawnEntityPainting.class);
/* 140 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutNamedEntitySpawn.class);
/* 141 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutAnimation.class);
/* 142 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutStatistic.class);
/* 143 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutBlockBreakAnimation.class);
/* 144 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutTileEntityData.class);
/* 145 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutBlockAction.class);
/* 146 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutBlockChange.class);
/* 147 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutBoss.class);
/* 148 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutServerDifficulty.class);
/* 149 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutTabComplete.class);
/* 150 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutChat.class);
/* 151 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutMultiBlockChange.class);
/* 152 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutTransaction.class);
/* 153 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutCloseWindow.class);
/* 154 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutOpenWindow.class);
/* 155 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutWindowItems.class);
/* 156 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutWindowData.class);
/* 157 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSetSlot.class);
/* 158 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSetCooldown.class);
/* 159 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutCustomPayload.class);
/* 160 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutCustomSoundEffect.class);
/* 161 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutKickDisconnect.class);
/* 162 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityStatus.class);
/* 163 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutExplosion.class);
/* 164 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutUnloadChunk.class);
/* 165 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutGameStateChange.class);
/* 166 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutKeepAlive.class);
/* 167 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutMapChunk.class);
/* 168 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutWorldEvent.class);
/* 169 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutWorldParticles.class);
/* 170 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutLogin.class);
/* 171 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutMap.class);
/* 172 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntity.class);
/* 173 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntity.PacketPlayOutRelEntityMove.class);
/* 174 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook.class);
/* 175 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntity.PacketPlayOutEntityLook.class);
/* 176 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutVehicleMove.class);
/* 177 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutOpenSignEditor.class);
/* 178 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutAbilities.class);
/* 179 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutCombatEvent.class);
/* 180 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutPlayerInfo.class);
/* 181 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutPosition.class);
/* 182 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutBed.class);
/* 183 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutRecipes.class);
/* 184 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityDestroy.class);
/* 185 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutRemoveEntityEffect.class);
/* 186 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutResourcePackSend.class);
/* 187 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutRespawn.class);
/* 188 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityHeadRotation.class);
/* 189 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSelectAdvancementTab.class);
/* 190 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutWorldBorder.class);
/* 191 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutCamera.class);
/* 192 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutHeldItemSlot.class);
/* 193 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutScoreboardDisplayObjective.class);
/* 194 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityMetadata.class);
/* 195 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutAttachEntity.class);
/* 196 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityVelocity.class);
/* 197 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityEquipment.class);
/* 198 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutExperience.class);
/* 199 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutUpdateHealth.class);
/* 200 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutScoreboardObjective.class);
/* 201 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutMount.class);
/* 202 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutScoreboardTeam.class);
/* 203 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutScoreboardScore.class);
/* 204 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutSpawnPosition.class);
/* 205 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutUpdateTime.class);
/* 206 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutTitle.class);
/* 207 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutNamedSoundEffect.class);
/* 208 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutPlayerListHeaderFooter.class);
/* 209 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutCollect.class);
/* 210 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityTeleport.class);
/* 211 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutAdvancements.class);
/* 212 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutUpdateAttributes.class);
/* 213 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketPlayOutEntityEffect.class);
/*     */       
/* 215 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInTeleportAccept.class);
/* 216 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInAutoRecipe.class);
/* 217 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInTabComplete.class);
/* 218 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInChat.class);
/* 219 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInClientCommand.class);
/* 220 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInSettings.class);
/* 221 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInTransaction.class);
/* 222 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInEnchantItem.class);
/* 223 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInWindowClick.class);
/* 224 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInCloseWindow.class);
/* 225 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInCustomPayload.class);
/* 226 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInUseEntity.class);
/* 227 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInKeepAlive.class);
/* 228 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInFlying.class);
/* 229 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInFlying.PacketPlayInPosition.class);
/* 230 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInFlying.PacketPlayInPositionLook.class);
/* 231 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInFlying.PacketPlayInLook.class);
/* 232 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInVehicleMove.class);
/* 233 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInBoatMove.class);
/* 234 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInAbilities.class);
/* 235 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInBlockDig.class);
/* 236 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInEntityAction.class);
/* 237 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInSteerVehicle.class);
/* 238 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInRecipeDisplayed.class);
/* 239 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInResourcePackStatus.class);
/* 240 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInAdvancements.class);
/* 241 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInHeldItemSlot.class);
/* 242 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInSetCreativeSlot.class);
/* 243 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInUpdateSign.class);
/* 244 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInArmAnimation.class);
/* 245 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInSpectate.class);
/* 246 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInUseItem.class);
/* 247 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketPlayInBlockPlace.class);
/*     */     } },
/* 249 */   STATUS(1) { EnumProtocol(int param1Int1) {
/* 250 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketStatusInStart.class);
/* 251 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketStatusOutServerInfo.class);
/* 252 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketStatusInPing.class);
/* 253 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketStatusOutPong.class);
/*     */     } },
/* 255 */   LOGIN(2) { EnumProtocol(int param1Int1) {
/* 256 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketLoginOutDisconnect.class);
/* 257 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketLoginOutEncryptionBegin.class);
/* 258 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketLoginOutSuccess.class);
/* 259 */       a(EnumProtocolDirection.CLIENTBOUND, (Class)PacketLoginOutSetCompression.class);
/*     */       
/* 261 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketLoginInStart.class);
/* 262 */       a(EnumProtocolDirection.SERVERBOUND, (Class)PacketLoginInEncryptionBegin.class);
/*     */     } }
/*     */   ;
/*     */   
/*     */   static {
/* 267 */     e = new EnumProtocol[4];
/* 268 */     f = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 314 */     for (EnumProtocol enumProtocol : values()) {
/* 315 */       int i = enumProtocol.a();
/* 316 */       if (i < -1 || i > 2) {
/* 317 */         throw new Error("Invalid protocol ID " + Integer.toString(i));
/*     */       }
/* 319 */       e[i - -1] = enumProtocol;
/*     */       
/* 321 */       for (EnumProtocolDirection enumProtocolDirection : enumProtocol.h.keySet()) {
/* 322 */         for (Class<? extends Packet<?>> clazz : (Iterable<Class<? extends Packet<?>>>)((BiMap)enumProtocol.h.get(enumProtocolDirection)).values()) {
/* 323 */           if (f.containsKey(clazz) && f.get(clazz) != enumProtocol) {
/* 324 */             throw new Error("Packet " + clazz + " is already assigned to protocol " + f.get(clazz) + " - can't reassign to " + enumProtocol);
/*     */           }
/*     */           
/*     */           try {
/* 328 */             clazz.newInstance();
/* 329 */           } catch (Throwable throwable) {
/* 330 */             throw new Error("Packet " + clazz + " fails instantiation checks! " + clazz);
/*     */           } 
/*     */           
/* 333 */           f.put(clazz, enumProtocol);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Map<EnumProtocolDirection, BiMap<Integer, Class<? extends Packet<?>>>> h = Maps.newEnumMap(EnumProtocolDirection.class); private static final EnumProtocol[] e; private static final Map<Class<? extends Packet<?>>, EnumProtocol> f; private final int g;
/* 340 */   public static EnumProtocol a(int paramInt) { if (paramInt < -1 || paramInt > 2) {
/* 341 */       return null;
/*     */     }
/* 343 */     return e[paramInt - -1]; }
/*     */   EnumProtocol(int paramInt1) { this.g = paramInt1; }
/*     */   protected EnumProtocol a(EnumProtocolDirection paramEnumProtocolDirection, Class<? extends Packet<?>> paramClass) { HashBiMap hashBiMap; BiMap biMap = this.h.get(paramEnumProtocolDirection); if (biMap == null) { hashBiMap = HashBiMap.create(); this.h.put(paramEnumProtocolDirection, hashBiMap); }  if (hashBiMap.containsValue(paramClass)) { String str = paramEnumProtocolDirection + " packet " + paramClass + " is already known to ID " + hashBiMap.inverse().get(paramClass); LogManager.getLogger().fatal(str); throw new IllegalArgumentException(str); }  hashBiMap.put(Integer.valueOf(hashBiMap.size()), paramClass); return this; }
/*     */   public Integer a(EnumProtocolDirection paramEnumProtocolDirection, Packet<?> paramPacket) throws Exception { return (Integer)((BiMap)this.h.get(paramEnumProtocolDirection)).inverse().get(paramPacket.getClass()); }
/* 347 */   @Nullable public Packet<?> a(EnumProtocolDirection paramEnumProtocolDirection, int paramInt) throws IllegalAccessException, InstantiationException { Class<Packet> clazz = (Class)((BiMap)this.h.get(paramEnumProtocolDirection)).get(Integer.valueOf(paramInt)); if (clazz == null) return null;  return clazz.newInstance(); } public int a() { return this.g; } public static EnumProtocol a(Packet<?> paramPacket) { return f.get(paramPacket.getClass()); }
/*     */ 
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */