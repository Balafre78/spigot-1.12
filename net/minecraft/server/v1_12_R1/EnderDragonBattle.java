/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.ContiguousSet;
/*     */ import com.google.common.collect.DiscreteDomain;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Range;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnderDragonBattle
/*     */ {
/*  57 */   private static final Logger a = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static final Predicate<EntityPlayer> b = Predicates.and(IEntitySelector.a, IEntitySelector.a(0.0D, 128.0D, 0.0D, 192.0D));
/*     */   
/*  68 */   private final BossBattleServer c = (BossBattleServer)(new BossBattleServer(new ChatMessage("entity.EnderDragon.name", new Object[0]), BossBattle.BarColor.PINK, BossBattle.BarStyle.PROGRESS)).setPlayMusic(true).c(true);
/*     */   private final WorldServer d;
/*  70 */   private final List<Integer> e = Lists.newArrayList();
/*     */   private final ShapeDetector f;
/*     */   private int g;
/*     */   private int h;
/*     */   private int i;
/*     */   private int j;
/*     */   private boolean k;
/*     */   private boolean l;
/*     */   private UUID m;
/*     */   private boolean n = true;
/*     */   private BlockPosition o;
/*     */   private EnumDragonRespawn p;
/*     */   private int q;
/*     */   private List<EntityEnderCrystal> r;
/*     */   
/*     */   public EnderDragonBattle(WorldServer paramWorldServer, NBTTagCompound paramNBTTagCompound) {
/*  86 */     this.d = paramWorldServer;
/*  87 */     if (paramNBTTagCompound.hasKeyOfType("DragonKilled", 99)) {
/*  88 */       if (paramNBTTagCompound.b("DragonUUID")) {
/*  89 */         this.m = paramNBTTagCompound.a("DragonUUID");
/*     */       }
/*  91 */       this.k = paramNBTTagCompound.getBoolean("DragonKilled");
/*  92 */       this.l = paramNBTTagCompound.getBoolean("PreviouslyKilled");
/*  93 */       if (paramNBTTagCompound.getBoolean("IsRespawning")) {
/*  94 */         this.p = EnumDragonRespawn.START;
/*     */       }
/*     */       
/*  97 */       if (paramNBTTagCompound.hasKeyOfType("ExitPortalLocation", 10)) {
/*  98 */         this.o = GameProfileSerializer.c(paramNBTTagCompound.getCompound("ExitPortalLocation"));
/*     */       }
/*     */     } else {
/* 101 */       this.k = true;
/* 102 */       this.l = true;
/*     */     } 
/*     */     
/* 105 */     if (paramNBTTagCompound.hasKeyOfType("Gateways", 9)) {
/* 106 */       NBTTagList nBTTagList = paramNBTTagCompound.getList("Gateways", 3);
/* 107 */       for (byte b = 0; b < nBTTagList.size(); b++) {
/* 108 */         this.e.add(Integer.valueOf(nBTTagList.c(b)));
/*     */       }
/*     */     } else {
/* 111 */       this.e.addAll((Collection<? extends Integer>)ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(20)), DiscreteDomain.integers()));
/* 112 */       Collections.shuffle(this.e, new Random(paramWorldServer.getSeed()));
/*     */     } 
/*     */     
/* 115 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 162 */       .f = ShapeDetectorBuilder.a().a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  " }).a(new String[] { "       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       " }).a('#', ShapeDetectorBlock.a(BlockPredicate.a(Blocks.BEDROCK))).b();
/*     */   }
/*     */   
/*     */   public NBTTagCompound a() {
/* 166 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */     
/* 168 */     if (this.m != null) {
/* 169 */       nBTTagCompound.a("DragonUUID", this.m);
/*     */     }
/*     */     
/* 172 */     nBTTagCompound.setBoolean("DragonKilled", this.k);
/* 173 */     nBTTagCompound.setBoolean("PreviouslyKilled", this.l);
/*     */     
/* 175 */     if (this.o != null) {
/* 176 */       nBTTagCompound.set("ExitPortalLocation", GameProfileSerializer.a(this.o));
/*     */     }
/*     */     
/* 179 */     NBTTagList nBTTagList = new NBTTagList();
/* 180 */     for (Iterator<Integer> iterator = this.e.iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 181 */       nBTTagList.add(new NBTTagInt(i)); }
/*     */     
/* 183 */     nBTTagCompound.set("Gateways", nBTTagList);
/*     */     
/* 185 */     return nBTTagCompound;
/*     */   }
/*     */   
/*     */   public void b() {
/* 189 */     this.c.setVisible(!this.k);
/*     */     
/* 191 */     if (++this.j >= 20) {
/* 192 */       j();
/* 193 */       this.j = 0;
/*     */     } 
/*     */     
/* 196 */     if (!this.c.getPlayers().isEmpty()) {
/* 197 */       if (this.n) {
/* 198 */         a.info("Scanning for legacy world dragon fight...");
/* 199 */         i();
/* 200 */         this.n = false;
/*     */         
/* 202 */         boolean bool = g();
/* 203 */         if (bool) {
/* 204 */           a.info("Found that the dragon has been killed in this world already.");
/* 205 */           this.l = true;
/*     */         } else {
/* 207 */           a.info("Found that the dragon has not yet been killed in this world.");
/* 208 */           this.l = false;
/* 209 */           a(false);
/*     */         } 
/*     */         
/* 212 */         List<Entity> list = this.d.a((Class)EntityEnderDragon.class, IEntitySelector.a);
/* 213 */         if (list.isEmpty()) {
/* 214 */           this.k = true;
/*     */         } else {
/* 216 */           EntityEnderDragon entityEnderDragon = (EntityEnderDragon)list.get(0);
/* 217 */           this.m = entityEnderDragon.getUniqueID();
/* 218 */           a.info("Found that there's a dragon still alive ({})", entityEnderDragon);
/* 219 */           this.k = false;
/*     */           
/* 221 */           if (!bool) {
/* 222 */             a.info("But we didn't have a portal, let's remove it.");
/* 223 */             entityEnderDragon.die();
/* 224 */             this.m = null;
/*     */           } 
/*     */         } 
/*     */         
/* 228 */         if (!this.l && this.k)
/*     */         {
/* 230 */           this.k = false;
/*     */         }
/*     */       } 
/*     */       
/* 234 */       if (this.p != null) {
/* 235 */         if (this.r == null) {
/* 236 */           this.p = null;
/* 237 */           e();
/*     */         } 
/* 239 */         this.p.a(this.d, this, this.r, this.q++, this.o);
/*     */       } 
/*     */       
/* 242 */       if (!this.k) {
/* 243 */         if (this.m == null || ++this.g >= 1200) {
/* 244 */           i();
/* 245 */           List<Entity> list = this.d.a((Class)EntityEnderDragon.class, IEntitySelector.a);
/* 246 */           if (list.isEmpty()) {
/* 247 */             a.debug("Haven't seen the dragon, respawning it");
/* 248 */             m();
/*     */           } else {
/* 250 */             a.debug("Haven't seen our dragon, but found another one to use.");
/* 251 */             this.m = ((EntityEnderDragon)list.get(0)).getUniqueID();
/*     */           } 
/* 253 */           this.g = 0;
/*     */         } 
/*     */         
/* 256 */         if (++this.i >= 100) {
/* 257 */           k();
/* 258 */           this.i = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(EnumDragonRespawn paramEnumDragonRespawn) {
/* 265 */     if (this.p == null) {
/* 266 */       throw new IllegalStateException("Dragon respawn isn't in progress, can't skip ahead in the animation.");
/*     */     }
/*     */     
/* 269 */     this.q = 0;
/* 270 */     if (paramEnumDragonRespawn == EnumDragonRespawn.END) {
/* 271 */       this.p = null;
/* 272 */       this.k = false;
/* 273 */       EntityEnderDragon entityEnderDragon = m();
/*     */       
/* 275 */       for (EntityPlayer entityPlayer : this.c.getPlayers()) {
/* 276 */         CriterionTriggers.m.a(entityPlayer, entityEnderDragon);
/*     */       }
/*     */     } else {
/* 279 */       this.p = paramEnumDragonRespawn;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean g() {
/* 284 */     for (byte b = -8; b <= 8; b++) {
/* 285 */       for (byte b1 = -8; b1 <= 8; b1++) {
/* 286 */         Chunk chunk = this.d.getChunkAt(b, b1);
/* 287 */         for (TileEntity tileEntity : chunk.getTileEntities().values()) {
/* 288 */           if (tileEntity instanceof TileEntityEnderPortal) {
/* 289 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     return false;
/*     */   }
/*     */   @Nullable
/*     */   private ShapeDetector.ShapeDetectorCollection h() {
/*     */     int i;
/* 300 */     for (i = -8; i <= 8; i++) {
/* 301 */       for (byte b = -8; b <= 8; b++) {
/* 302 */         Chunk chunk = this.d.getChunkAt(i, b);
/* 303 */         for (TileEntity tileEntity : chunk.getTileEntities().values()) {
/* 304 */           if (tileEntity instanceof TileEntityEnderPortal) {
/* 305 */             ShapeDetector.ShapeDetectorCollection shapeDetectorCollection = this.f.a(this.d, tileEntity.getPosition());
/* 306 */             if (shapeDetectorCollection != null) {
/* 307 */               BlockPosition blockPosition = shapeDetectorCollection.a(3, 3, 3).getPosition();
/* 308 */               if (this.o == null && blockPosition.getX() == 0 && blockPosition.getZ() == 0) {
/* 309 */                 this.o = blockPosition;
/*     */               }
/* 311 */               return shapeDetectorCollection;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 318 */     i = this.d.getHighestBlockYAt(WorldGenEndTrophy.a).getY();
/*     */     
/* 320 */     for (int j = i; j >= 0; j--) {
/* 321 */       ShapeDetector.ShapeDetectorCollection shapeDetectorCollection = this.f.a(this.d, new BlockPosition(WorldGenEndTrophy.a.getX(), j, WorldGenEndTrophy.a.getZ()));
/* 322 */       if (shapeDetectorCollection != null) {
/* 323 */         if (this.o == null) {
/* 324 */           this.o = shapeDetectorCollection.a(3, 3, 3).getPosition();
/*     */         }
/* 326 */         return shapeDetectorCollection;
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     return null;
/*     */   }
/*     */   
/*     */   private void i() {
/* 334 */     for (byte b = -8; b <= 8; b++) {
/* 335 */       for (byte b1 = -8; b1 <= 8; b1++) {
/* 336 */         this.d.getChunkAt(b, b1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void j() {
/* 342 */     HashSet<EntityPlayer> hashSet = Sets.newHashSet();
/* 343 */     for (EntityPlayer entityPlayer : this.d.<EntityPlayer>b(EntityPlayer.class, b)) {
/* 344 */       this.c.addPlayer(entityPlayer);
/* 345 */       hashSet.add(entityPlayer);
/*     */     } 
/* 347 */     HashSet hashSet1 = Sets.newHashSet(this.c.getPlayers());
/* 348 */     hashSet1.removeAll(hashSet);
/* 349 */     for (EntityPlayer entityPlayer : hashSet1) {
/* 350 */       this.c.removePlayer(entityPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */   private void k() {
/* 355 */     this.i = 0;
/* 356 */     this.h = 0;
/*     */     
/* 358 */     for (WorldGenEnder.Spike spike : BiomeTheEndDecorator.a(this.d)) {
/* 359 */       this.h += this.d.<EntityEnderCrystal>a(EntityEnderCrystal.class, spike.f()).size();
/*     */     }
/*     */     
/* 362 */     a.debug("Found {} end crystals still alive", Integer.valueOf(this.h));
/*     */   }
/*     */   
/*     */   public void a(EntityEnderDragon paramEntityEnderDragon) {
/* 366 */     if (paramEntityEnderDragon.getUniqueID().equals(this.m)) {
/* 367 */       this.c.setProgress(0.0F);
/* 368 */       this.c.setVisible(false);
/* 369 */       a(true);
/* 370 */       l();
/*     */       
/* 372 */       if (!this.l) {
/* 373 */         this.d.setTypeUpdate(this.d.getHighestBlockYAt(WorldGenEndTrophy.a), Blocks.DRAGON_EGG.getBlockData());
/*     */       }
/*     */       
/* 376 */       this.l = true;
/* 377 */       this.k = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void l() {
/* 382 */     if (this.e.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 386 */     int i = ((Integer)this.e.remove(this.e.size() - 1)).intValue();
/* 387 */     int j = (int)(96.0D * Math.cos(2.0D * (-3.141592653589793D + 0.15707963267948966D * i)));
/* 388 */     int k = (int)(96.0D * Math.sin(2.0D * (-3.141592653589793D + 0.15707963267948966D * i)));
/* 389 */     a(new BlockPosition(j, 75, k));
/*     */   }
/*     */   
/*     */   private void a(BlockPosition paramBlockPosition) {
/* 393 */     this.d.triggerEffect(3000, paramBlockPosition, 0);
/* 394 */     (new WorldGenEndGateway()).generate(this.d, new Random(), paramBlockPosition);
/*     */   }
/*     */   
/*     */   private void a(boolean paramBoolean) {
/* 398 */     WorldGenEndTrophy worldGenEndTrophy = new WorldGenEndTrophy(paramBoolean);
/*     */     
/* 400 */     if (this.o == null) {
/* 401 */       this.o = this.d.q(WorldGenEndTrophy.a).down();
/* 402 */       while (this.d.getType(this.o).getBlock() == Blocks.BEDROCK && this.o.getY() > this.d.getSeaLevel()) {
/* 403 */         this.o = this.o.down();
/*     */       }
/*     */     } 
/*     */     
/* 407 */     worldGenEndTrophy.generate(this.d, new Random(), this.o);
/*     */   }
/*     */   
/*     */   private EntityEnderDragon m() {
/* 411 */     this.d.getChunkAtWorldCoords(new BlockPosition(0, 128, 0));
/* 412 */     EntityEnderDragon entityEnderDragon = new EntityEnderDragon(this.d);
/* 413 */     entityEnderDragon.getDragonControllerManager().setControllerPhase(DragonControllerPhase.a);
/* 414 */     entityEnderDragon.setPositionRotation(0.0D, 128.0D, 0.0D, this.d.random.nextFloat() * 360.0F, 0.0F);
/* 415 */     this.d.addEntity(entityEnderDragon);
/* 416 */     this.m = entityEnderDragon.getUniqueID();
/* 417 */     return entityEnderDragon;
/*     */   }
/*     */   
/*     */   public void b(EntityEnderDragon paramEntityEnderDragon) {
/* 421 */     if (paramEntityEnderDragon.getUniqueID().equals(this.m)) {
/* 422 */       this.c.setProgress(paramEntityEnderDragon.getHealth() / paramEntityEnderDragon.getMaxHealth());
/* 423 */       this.g = 0;
/* 424 */       if (paramEntityEnderDragon.hasCustomName()) {
/* 425 */         this.c.a(paramEntityEnderDragon.getScoreboardDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public int c() {
/* 431 */     return this.h;
/*     */   }
/*     */   
/*     */   public void a(EntityEnderCrystal paramEntityEnderCrystal, DamageSource paramDamageSource) {
/* 435 */     if (this.p != null && this.r.contains(paramEntityEnderCrystal)) {
/* 436 */       a.debug("Aborting respawn sequence");
/* 437 */       this.p = null;
/* 438 */       this.q = 0;
/* 439 */       f();
/* 440 */       a(true);
/*     */     } else {
/* 442 */       k();
/* 443 */       Entity entity = this.d.getEntity(this.m);
/* 444 */       if (entity instanceof EntityEnderDragon) {
/* 445 */         ((EntityEnderDragon)entity).a(paramEntityEnderCrystal, new BlockPosition(paramEntityEnderCrystal), paramDamageSource);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 451 */     return this.l;
/*     */   }
/*     */   
/*     */   public void e() {
/* 455 */     if (this.k && this.p == null) {
/* 456 */       BlockPosition blockPosition1 = this.o;
/* 457 */       if (blockPosition1 == null) {
/* 458 */         a.debug("Tried to respawn, but need to find the portal first.");
/* 459 */         ShapeDetector.ShapeDetectorCollection shapeDetectorCollection = h();
/* 460 */         if (shapeDetectorCollection == null) {
/* 461 */           a.debug("Couldn't find a portal, so we made one.");
/* 462 */           a(true);
/*     */         } else {
/* 464 */           a.debug("Found the exit portal & temporarily using it.");
/*     */         } 
/* 466 */         blockPosition1 = this.o;
/*     */       } 
/*     */       
/* 469 */       ArrayList<EntityEnderCrystal> arrayList = Lists.newArrayList();
/* 470 */       BlockPosition blockPosition2 = blockPosition1.up(1);
/* 471 */       for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 472 */         List<EntityEnderCrystal> list = this.d.a(EntityEnderCrystal.class, new AxisAlignedBB(blockPosition2.shift(enumDirection, 2)));
/* 473 */         if (list.isEmpty()) {
/*     */           return;
/*     */         }
/* 476 */         arrayList.addAll(list);
/*     */       } 
/*     */       
/* 479 */       a.debug("Found all crystals, respawning dragon.");
/* 480 */       a(arrayList);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(List<EntityEnderCrystal> paramList) {
/* 485 */     if (this.k && this.p == null) {
/* 486 */       ShapeDetector.ShapeDetectorCollection shapeDetectorCollection = h();
/* 487 */       while (shapeDetectorCollection != null) {
/* 488 */         for (byte b = 0; b < this.f.c(); b++) {
/* 489 */           for (byte b1 = 0; b1 < this.f.b(); b1++) {
/* 490 */             for (byte b2 = 0; b2 < this.f.a(); b2++) {
/* 491 */               ShapeDetectorBlock shapeDetectorBlock = shapeDetectorCollection.a(b, b1, b2);
/* 492 */               if (shapeDetectorBlock.a().getBlock() == Blocks.BEDROCK || shapeDetectorBlock.a().getBlock() == Blocks.END_PORTAL) {
/* 493 */                 this.d.setTypeUpdate(shapeDetectorBlock.getPosition(), Blocks.END_STONE.getBlockData());
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 498 */         shapeDetectorCollection = h();
/*     */       } 
/*     */       
/* 501 */       this.p = EnumDragonRespawn.START;
/* 502 */       this.q = 0;
/* 503 */       a(false);
/* 504 */       this.r = paramList;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void f() {
/* 509 */     for (WorldGenEnder.Spike spike : BiomeTheEndDecorator.a(this.d)) {
/* 510 */       List<EntityEnderCrystal> list = this.d.a(EntityEnderCrystal.class, spike.f());
/* 511 */       for (EntityEnderCrystal entityEnderCrystal : list) {
/* 512 */         entityEnderCrystal.setInvulnerable(false);
/* 513 */         entityEnderCrystal.setBeamTarget((BlockPosition)null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnderDragonBattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */