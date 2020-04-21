/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ import org.spigotmc.TrackingRange;
/*     */ 
/*     */ public class EntityTracker {
/*  13 */   private static final Logger a = LogManager.getLogger();
/*     */   private final WorldServer world;
/*  15 */   private final Set<EntityTrackerEntry> c = Sets.newHashSet();
/*  16 */   public final IntHashMap<EntityTrackerEntry> trackedEntities = new IntHashMap<>();
/*     */   private int e;
/*     */   
/*     */   public EntityTracker(WorldServer worldserver) {
/*  20 */     this.world = worldserver;
/*  21 */     this.e = PlayerChunkMap.getFurthestViewableBlock(worldserver.spigotConfig.viewDistance);
/*     */   }
/*     */   
/*     */   public static long a(double d0) {
/*  25 */     return MathHelper.d(d0 * 4096.0D);
/*     */   }
/*     */   
/*     */   public void track(Entity entity) {
/*  29 */     if (entity instanceof EntityPlayer) {
/*  30 */       addEntity(entity, 512, 2);
/*  31 */       EntityPlayer entityplayer = (EntityPlayer)entity;
/*  32 */       Iterator<EntityTrackerEntry> iterator = this.c.iterator();
/*     */       
/*  34 */       while (iterator.hasNext()) {
/*  35 */         EntityTrackerEntry entitytrackerentry = iterator.next();
/*     */         
/*  37 */         if (entitytrackerentry.b() != entityplayer) {
/*  38 */           entitytrackerentry.updatePlayer(entityplayer);
/*     */         }
/*     */       } 
/*  41 */     } else if (entity instanceof EntityFishingHook) {
/*  42 */       addEntity(entity, 64, 5, true);
/*  43 */     } else if (entity instanceof EntityArrow) {
/*  44 */       addEntity(entity, 64, 20, false);
/*  45 */     } else if (entity instanceof EntitySmallFireball) {
/*  46 */       addEntity(entity, 64, 10, false);
/*  47 */     } else if (entity instanceof EntityFireball) {
/*  48 */       addEntity(entity, 64, 10, true);
/*  49 */     } else if (entity instanceof EntitySnowball) {
/*  50 */       addEntity(entity, 64, 10, true);
/*  51 */     } else if (entity instanceof EntityLlamaSpit) {
/*  52 */       addEntity(entity, 64, 10, false);
/*  53 */     } else if (entity instanceof EntityEnderPearl) {
/*  54 */       addEntity(entity, 64, 10, true);
/*  55 */     } else if (entity instanceof EntityEnderSignal) {
/*  56 */       addEntity(entity, 64, 4, true);
/*  57 */     } else if (entity instanceof EntityEgg) {
/*  58 */       addEntity(entity, 64, 10, true);
/*  59 */     } else if (entity instanceof EntityPotion) {
/*  60 */       addEntity(entity, 64, 10, true);
/*  61 */     } else if (entity instanceof EntityThrownExpBottle) {
/*  62 */       addEntity(entity, 64, 10, true);
/*  63 */     } else if (entity instanceof EntityFireworks) {
/*  64 */       addEntity(entity, 64, 10, true);
/*  65 */     } else if (entity instanceof EntityItem) {
/*  66 */       addEntity(entity, 64, 20, true);
/*  67 */     } else if (entity instanceof EntityMinecartAbstract) {
/*  68 */       addEntity(entity, 80, 3, true);
/*  69 */     } else if (entity instanceof EntityBoat) {
/*  70 */       addEntity(entity, 80, 3, true);
/*  71 */     } else if (entity instanceof EntitySquid) {
/*  72 */       addEntity(entity, 64, 3, true);
/*  73 */     } else if (entity instanceof EntityWither) {
/*  74 */       addEntity(entity, 80, 3, false);
/*  75 */     } else if (entity instanceof EntityShulkerBullet) {
/*  76 */       addEntity(entity, 80, 3, true);
/*  77 */     } else if (entity instanceof EntityBat) {
/*  78 */       addEntity(entity, 80, 3, false);
/*  79 */     } else if (entity instanceof EntityEnderDragon) {
/*  80 */       addEntity(entity, 160, 3, true);
/*  81 */     } else if (entity instanceof IAnimal) {
/*  82 */       addEntity(entity, 80, 3, true);
/*  83 */     } else if (entity instanceof EntityTNTPrimed) {
/*  84 */       addEntity(entity, 160, 10, true);
/*  85 */     } else if (entity instanceof EntityFallingBlock) {
/*  86 */       addEntity(entity, 160, 20, true);
/*  87 */     } else if (entity instanceof EntityHanging) {
/*  88 */       addEntity(entity, 160, 2147483647, false);
/*  89 */     } else if (entity instanceof EntityArmorStand) {
/*  90 */       addEntity(entity, 160, 3, true);
/*  91 */     } else if (entity instanceof EntityExperienceOrb) {
/*  92 */       addEntity(entity, 160, 20, true);
/*  93 */     } else if (entity instanceof EntityAreaEffectCloud) {
/*  94 */       addEntity(entity, 160, 2147483647, true);
/*  95 */     } else if (entity instanceof EntityEnderCrystal) {
/*  96 */       addEntity(entity, 256, 2147483647, false);
/*  97 */     } else if (entity instanceof EntityEvokerFangs) {
/*  98 */       addEntity(entity, 160, 2, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntity(Entity entity, int i, int j) {
/* 104 */     addEntity(entity, i, j, false);
/*     */   }
/*     */   
/*     */   public void addEntity(Entity entity, int i, int j, boolean flag) {
/* 108 */     AsyncCatcher.catchOp("entity track");
/* 109 */     i = TrackingRange.getEntityTrackingRange(entity, i);
/*     */     try {
/* 111 */       if (this.trackedEntities.b(entity.getId())) {
/* 112 */         throw new IllegalStateException("Entity is already tracked!");
/*     */       }
/*     */       
/* 115 */       EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entity, i, this.e, j, flag);
/*     */       
/* 117 */       this.c.add(entitytrackerentry);
/* 118 */       this.trackedEntities.a(entity.getId(), entitytrackerentry);
/* 119 */       entitytrackerentry.scanPlayers(this.world.players);
/* 120 */     } catch (Throwable throwable) {
/* 121 */       CrashReport crashreport = CrashReport.a(throwable, "Adding entity to track");
/* 122 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Entity To Track");
/*     */       
/* 124 */       crashreportsystemdetails.a("Tracking range", String.valueOf(i) + " blocks");
/* 125 */       final int finalI = i;
/* 126 */       crashreportsystemdetails.a("Update interval", new CrashReportCallable<String>() {
/*     */             public String a() throws Exception {
/* 128 */               String s = "Once per " + finalI + " ticks";
/*     */               
/* 130 */               if (finalI == Integer.MAX_VALUE) {
/* 131 */                 s = "Maximum (" + s + ")";
/*     */               }
/*     */               
/* 134 */               return s;
/*     */             }
/*     */             
/*     */             public Object call() throws Exception {
/* 138 */               return a();
/*     */             }
/*     */           });
/* 141 */       entity.appendEntityCrashDetails(crashreportsystemdetails);
/* 142 */       ((EntityTrackerEntry)this.trackedEntities.get(entity.getId())).b().appendEntityCrashDetails(crashreport.a("Entity That Is Already Tracked"));
/*     */       
/*     */       try {
/* 145 */         throw new ReportedException(crashreport);
/* 146 */       } catch (ReportedException reportedexception) {
/* 147 */         a.error("\"Silently\" catching entity tracking error.", reportedexception);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void untrackEntity(Entity entity) {
/* 154 */     AsyncCatcher.catchOp("entity untrack");
/* 155 */     if (entity instanceof EntityPlayer) {
/* 156 */       EntityPlayer entityplayer = (EntityPlayer)entity;
/* 157 */       Iterator<EntityTrackerEntry> iterator = this.c.iterator();
/*     */       
/* 159 */       while (iterator.hasNext()) {
/* 160 */         EntityTrackerEntry entitytrackerentry = iterator.next();
/*     */         
/* 162 */         entitytrackerentry.a(entityplayer);
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     EntityTrackerEntry entitytrackerentry1 = this.trackedEntities.d(entity.getId());
/*     */     
/* 168 */     if (entitytrackerentry1 != null) {
/* 169 */       this.c.remove(entitytrackerentry1);
/* 170 */       entitytrackerentry1.a();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePlayers() {
/* 176 */     ArrayList<EntityPlayer> arraylist = Lists.newArrayList();
/* 177 */     Iterator<EntityTrackerEntry> iterator = this.c.iterator();
/*     */     
/* 179 */     while (iterator.hasNext()) {
/* 180 */       EntityTrackerEntry entitytrackerentry = iterator.next();
/*     */       
/* 182 */       entitytrackerentry.track(this.world.players);
/* 183 */       if (entitytrackerentry.b) {
/* 184 */         Entity entity = entitytrackerentry.b();
/*     */         
/* 186 */         if (entity instanceof EntityPlayer) {
/* 187 */           arraylist.add((EntityPlayer)entity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     for (int i = 0; i < arraylist.size(); i++) {
/* 193 */       EntityPlayer entityplayer = arraylist.get(i);
/* 194 */       Iterator<EntityTrackerEntry> iterator1 = this.c.iterator();
/*     */       
/* 196 */       while (iterator1.hasNext()) {
/* 197 */         EntityTrackerEntry entitytrackerentry1 = iterator1.next();
/*     */         
/* 199 */         if (entitytrackerentry1.b() != entityplayer) {
/* 200 */           entitytrackerentry1.updatePlayer(entityplayer);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/* 208 */     Iterator<EntityTrackerEntry> iterator = this.c.iterator();
/*     */     
/* 210 */     while (iterator.hasNext()) {
/* 211 */       EntityTrackerEntry entitytrackerentry = iterator.next();
/*     */       
/* 213 */       if (entitytrackerentry.b() == entityplayer) {
/* 214 */         entitytrackerentry.scanPlayers(this.world.players); continue;
/*     */       } 
/* 216 */       entitytrackerentry.updatePlayer(entityplayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Entity entity, Packet<?> packet) {
/* 223 */     EntityTrackerEntry entitytrackerentry = this.trackedEntities.get(entity.getId());
/*     */     
/* 225 */     if (entitytrackerentry != null) {
/* 226 */       entitytrackerentry.broadcast(packet);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacketToEntity(Entity entity, Packet<?> packet) {
/* 232 */     EntityTrackerEntry entitytrackerentry = this.trackedEntities.get(entity.getId());
/*     */     
/* 234 */     if (entitytrackerentry != null) {
/* 235 */       entitytrackerentry.broadcastIncludingSelf(packet);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void untrackPlayer(EntityPlayer entityplayer) {
/* 241 */     Iterator<EntityTrackerEntry> iterator = this.c.iterator();
/*     */     
/* 243 */     while (iterator.hasNext()) {
/* 244 */       EntityTrackerEntry entitytrackerentry = iterator.next();
/*     */       
/* 246 */       entitytrackerentry.clear(entityplayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityPlayer entityplayer, Chunk chunk) {
/* 252 */     ArrayList<Entity> arraylist = Lists.newArrayList();
/* 253 */     ArrayList<Entity> arraylist1 = Lists.newArrayList();
/* 254 */     Iterator<EntityTrackerEntry> iterator = this.c.iterator();
/*     */     
/* 256 */     while (iterator.hasNext()) {
/* 257 */       EntityTrackerEntry entitytrackerentry = iterator.next();
/* 258 */       Entity entity = entitytrackerentry.b();
/*     */       
/* 260 */       if (entity != entityplayer && entity.ab == chunk.locX && entity.ad == chunk.locZ) {
/* 261 */         entitytrackerentry.updatePlayer(entityplayer);
/* 262 */         if (entity instanceof EntityInsentient && ((EntityInsentient)entity).getLeashHolder() != null) {
/* 263 */           arraylist.add(entity);
/*     */         }
/*     */         
/* 266 */         if (!entity.bF().isEmpty()) {
/* 267 */           arraylist1.add(entity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (!arraylist.isEmpty()) {
/* 275 */       iterator = (Iterator)arraylist.iterator();
/*     */       
/* 277 */       while (iterator.hasNext()) {
/* 278 */         Entity entity1 = (Entity)iterator.next();
/* 279 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutAttachEntity(entity1, ((EntityInsentient)entity1).getLeashHolder()));
/*     */       } 
/*     */     } 
/*     */     
/* 283 */     if (!arraylist1.isEmpty()) {
/* 284 */       iterator = (Iterator)arraylist1.iterator();
/*     */       
/* 286 */       while (iterator.hasNext()) {
/* 287 */         Entity entity1 = (Entity)iterator.next();
/* 288 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutMount(entity1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i) {
/* 295 */     this.e = (i - 1) * 16;
/* 296 */     Iterator<EntityTrackerEntry> iterator = this.c.iterator();
/*     */     
/* 298 */     while (iterator.hasNext()) {
/* 299 */       EntityTrackerEntry entitytrackerentry = iterator.next();
/*     */       
/* 301 */       entitytrackerentry.a(this.e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */