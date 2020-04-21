/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftEquipmentSlot;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class EntityArmorStand
/*     */   extends EntityLiving {
/*  19 */   private static final Vector3f br = new Vector3f(0.0F, 0.0F, 0.0F);
/*  20 */   private static final Vector3f bs = new Vector3f(0.0F, 0.0F, 0.0F);
/*  21 */   private static final Vector3f bt = new Vector3f(-10.0F, 0.0F, -10.0F);
/*  22 */   private static final Vector3f bu = new Vector3f(-15.0F, 0.0F, 10.0F);
/*  23 */   private static final Vector3f bv = new Vector3f(-1.0F, 0.0F, -1.0F);
/*  24 */   private static final Vector3f bw = new Vector3f(1.0F, 0.0F, 1.0F);
/*  25 */   public static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.a);
/*  26 */   public static final DataWatcherObject<Vector3f> b = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.i);
/*  27 */   public static final DataWatcherObject<Vector3f> c = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.i);
/*  28 */   public static final DataWatcherObject<Vector3f> d = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.i);
/*  29 */   public static final DataWatcherObject<Vector3f> e = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.i);
/*  30 */   public static final DataWatcherObject<Vector3f> f = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.i);
/*  31 */   public static final DataWatcherObject<Vector3f> g = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.i);
/*  32 */   private static final Predicate<Entity> bx = new Predicate() {
/*     */       public boolean a(@Nullable Entity entity) {
/*  34 */         return (entity instanceof EntityMinecartAbstract && ((EntityMinecartAbstract)entity).v() == EntityMinecartAbstract.EnumMinecartType.RIDEABLE);
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  38 */         return a((Entity)object);
/*     */       }
/*     */     };
/*     */   private final NonNullList<ItemStack> by;
/*     */   private final NonNullList<ItemStack> bz;
/*     */   private boolean bA;
/*     */   public long h;
/*     */   private int bB;
/*     */   private boolean bC;
/*     */   public Vector3f headPose;
/*     */   public Vector3f bodyPose;
/*     */   public Vector3f leftArmPose;
/*     */   public Vector3f rightArmPose;
/*     */   public Vector3f leftLegPose;
/*     */   public Vector3f rightLegPose;
/*     */   
/*     */   public EntityArmorStand(World world) {
/*  55 */     super(world);
/*  56 */     this.by = NonNullList.a(2, ItemStack.a);
/*  57 */     this.bz = NonNullList.a(4, ItemStack.a);
/*  58 */     this.headPose = br;
/*  59 */     this.bodyPose = bs;
/*  60 */     this.leftArmPose = bt;
/*  61 */     this.rightArmPose = bu;
/*  62 */     this.leftLegPose = bv;
/*  63 */     this.rightLegPose = bw;
/*  64 */     this.noclip = isNoGravity();
/*  65 */     setSize(0.5F, 1.975F);
/*     */   }
/*     */   
/*     */   public EntityArmorStand(World world, double d0, double d1, double d2) {
/*  69 */     this(world);
/*  70 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public final void setSize(float f, float f1) {
/*  74 */     double d0 = this.locX;
/*  75 */     double d1 = this.locY;
/*  76 */     double d2 = this.locZ;
/*  77 */     float f2 = isMarker() ? 0.0F : (isBaby() ? 0.5F : 1.0F);
/*     */     
/*  79 */     super.setSize(f * f2, f1 * f2);
/*  80 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public boolean cC() {
/*  84 */     return (super.cC() && !isNoGravity());
/*     */   }
/*     */   
/*     */   protected void i() {
/*  88 */     super.i();
/*  89 */     this.datawatcher.register(a, Byte.valueOf((byte)0));
/*  90 */     this.datawatcher.register(b, br);
/*  91 */     this.datawatcher.register(c, bs);
/*  92 */     this.datawatcher.register(d, bt);
/*  93 */     this.datawatcher.register(e, bu);
/*  94 */     this.datawatcher.register(f, bv);
/*  95 */     this.datawatcher.register(g, bw);
/*     */   }
/*     */   
/*     */   public Iterable<ItemStack> aO() {
/*  99 */     return this.by;
/*     */   }
/*     */   
/*     */   public Iterable<ItemStack> getArmorItems() {
/* 103 */     return this.bz;
/*     */   }
/*     */   
/*     */   public ItemStack getEquipment(EnumItemSlot enumitemslot) {
/* 107 */     switch (enumitemslot.a()) {
/*     */       case HAND:
/* 109 */         return this.by.get(enumitemslot.b());
/*     */       
/*     */       case null:
/* 112 */         return this.bz.get(enumitemslot.b());
/*     */     } 
/*     */     
/* 115 */     return ItemStack.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 120 */     switch (enumitemslot.a()) {
/*     */       case HAND:
/* 122 */         a_(itemstack);
/* 123 */         this.by.set(enumitemslot.b(), itemstack);
/*     */         break;
/*     */       
/*     */       case null:
/* 127 */         a_(itemstack);
/* 128 */         this.bz.set(enumitemslot.b(), itemstack);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(int i, ItemStack itemstack) {
/*     */     EnumItemSlot enumitemslot;
/* 136 */     if (i == 98) {
/* 137 */       enumitemslot = EnumItemSlot.MAINHAND;
/* 138 */     } else if (i == 99) {
/* 139 */       enumitemslot = EnumItemSlot.OFFHAND;
/* 140 */     } else if (i == 100 + EnumItemSlot.HEAD.b()) {
/* 141 */       enumitemslot = EnumItemSlot.HEAD;
/* 142 */     } else if (i == 100 + EnumItemSlot.CHEST.b()) {
/* 143 */       enumitemslot = EnumItemSlot.CHEST;
/* 144 */     } else if (i == 100 + EnumItemSlot.LEGS.b()) {
/* 145 */       enumitemslot = EnumItemSlot.LEGS;
/*     */     } else {
/* 147 */       if (i != 100 + EnumItemSlot.FEET.b()) {
/* 148 */         return false;
/*     */       }
/*     */       
/* 151 */       enumitemslot = EnumItemSlot.FEET;
/*     */     } 
/*     */     
/* 154 */     if (!itemstack.isEmpty() && !EntityInsentient.b(enumitemslot, itemstack) && enumitemslot != EnumItemSlot.HEAD) {
/* 155 */       return false;
/*     */     }
/* 157 */     setSlot(enumitemslot, itemstack);
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 163 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItemList(EntityArmorStand.class, new String[] { "ArmorItems", "HandItems" }));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 167 */     super.b(nbttagcompound);
/* 168 */     NBTTagList nbttaglist = new NBTTagList();
/*     */ 
/*     */ 
/*     */     
/* 172 */     for (Iterator<ItemStack> iterator = this.bz.iterator(); iterator.hasNext(); nbttaglist.add(nbttagcompound1)) {
/* 173 */       ItemStack itemstack = iterator.next();
/*     */       
/* 175 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 176 */       if (!itemstack.isEmpty()) {
/* 177 */         itemstack.save(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */     
/* 181 */     nbttagcompound.set("ArmorItems", nbttaglist);
/* 182 */     NBTTagList nbttaglist1 = new NBTTagList();
/*     */ 
/*     */ 
/*     */     
/* 186 */     for (Iterator<ItemStack> iterator1 = this.by.iterator(); iterator1.hasNext(); nbttaglist1.add(nbttagcompound2)) {
/* 187 */       ItemStack itemstack1 = iterator1.next();
/*     */       
/* 189 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/* 190 */       if (!itemstack1.isEmpty()) {
/* 191 */         itemstack1.save(nbttagcompound2);
/*     */       }
/*     */     } 
/*     */     
/* 195 */     nbttagcompound.set("HandItems", nbttaglist1);
/* 196 */     nbttagcompound.setBoolean("Invisible", isInvisible());
/* 197 */     nbttagcompound.setBoolean("Small", isSmall());
/* 198 */     nbttagcompound.setBoolean("ShowArms", hasArms());
/* 199 */     nbttagcompound.setInt("DisabledSlots", this.bB);
/* 200 */     nbttagcompound.setBoolean("NoBasePlate", hasBasePlate());
/* 201 */     if (isMarker()) {
/* 202 */       nbttagcompound.setBoolean("Marker", isMarker());
/*     */     }
/*     */     
/* 205 */     nbttagcompound.set("Pose", C());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 209 */     super.a(nbttagcompound);
/*     */ 
/*     */ 
/*     */     
/* 213 */     if (nbttagcompound.hasKeyOfType("ArmorItems", 9)) {
/* 214 */       NBTTagList nbttaglist = nbttagcompound.getList("ArmorItems", 10);
/*     */       
/* 216 */       for (int i = 0; i < this.bz.size(); i++) {
/* 217 */         this.bz.set(i, new ItemStack(nbttaglist.get(i)));
/*     */       }
/*     */     } 
/*     */     
/* 221 */     if (nbttagcompound.hasKeyOfType("HandItems", 9)) {
/* 222 */       NBTTagList nbttaglist = nbttagcompound.getList("HandItems", 10);
/*     */       
/* 224 */       for (int i = 0; i < this.by.size(); i++) {
/* 225 */         this.by.set(i, new ItemStack(nbttaglist.get(i)));
/*     */       }
/*     */     } 
/*     */     
/* 229 */     setInvisible(nbttagcompound.getBoolean("Invisible"));
/* 230 */     setSmall(nbttagcompound.getBoolean("Small"));
/* 231 */     setArms(nbttagcompound.getBoolean("ShowArms"));
/* 232 */     this.bB = nbttagcompound.getInt("DisabledSlots");
/* 233 */     setBasePlate(nbttagcompound.getBoolean("NoBasePlate"));
/* 234 */     setMarker(nbttagcompound.getBoolean("Marker"));
/* 235 */     this.bC = !isMarker();
/* 236 */     this.noclip = isNoGravity();
/* 237 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Pose");
/*     */     
/* 239 */     g(nbttagcompound1);
/*     */   }
/*     */   
/*     */   private void g(NBTTagCompound nbttagcompound) {
/* 243 */     NBTTagList nbttaglist = nbttagcompound.getList("Head", 5);
/*     */     
/* 245 */     setHeadPose(nbttaglist.isEmpty() ? br : new Vector3f(nbttaglist));
/* 246 */     NBTTagList nbttaglist1 = nbttagcompound.getList("Body", 5);
/*     */     
/* 248 */     setBodyPose(nbttaglist1.isEmpty() ? bs : new Vector3f(nbttaglist1));
/* 249 */     NBTTagList nbttaglist2 = nbttagcompound.getList("LeftArm", 5);
/*     */     
/* 251 */     setLeftArmPose(nbttaglist2.isEmpty() ? bt : new Vector3f(nbttaglist2));
/* 252 */     NBTTagList nbttaglist3 = nbttagcompound.getList("RightArm", 5);
/*     */     
/* 254 */     setRightArmPose(nbttaglist3.isEmpty() ? bu : new Vector3f(nbttaglist3));
/* 255 */     NBTTagList nbttaglist4 = nbttagcompound.getList("LeftLeg", 5);
/*     */     
/* 257 */     setLeftLegPose(nbttaglist4.isEmpty() ? bv : new Vector3f(nbttaglist4));
/* 258 */     NBTTagList nbttaglist5 = nbttagcompound.getList("RightLeg", 5);
/*     */     
/* 260 */     setRightLegPose(nbttaglist5.isEmpty() ? bw : new Vector3f(nbttaglist5));
/*     */   }
/*     */   
/*     */   private NBTTagCompound C() {
/* 264 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 266 */     if (!br.equals(this.headPose)) {
/* 267 */       nbttagcompound.set("Head", this.headPose.a());
/*     */     }
/*     */     
/* 270 */     if (!bs.equals(this.bodyPose)) {
/* 271 */       nbttagcompound.set("Body", this.bodyPose.a());
/*     */     }
/*     */     
/* 274 */     if (!bt.equals(this.leftArmPose)) {
/* 275 */       nbttagcompound.set("LeftArm", this.leftArmPose.a());
/*     */     }
/*     */     
/* 278 */     if (!bu.equals(this.rightArmPose)) {
/* 279 */       nbttagcompound.set("RightArm", this.rightArmPose.a());
/*     */     }
/*     */     
/* 282 */     if (!bv.equals(this.leftLegPose)) {
/* 283 */       nbttagcompound.set("LeftLeg", this.leftLegPose.a());
/*     */     }
/*     */     
/* 286 */     if (!bw.equals(this.rightLegPose)) {
/* 287 */       nbttagcompound.set("RightLeg", this.rightLegPose.a());
/*     */     }
/*     */     
/* 290 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public boolean isCollidable() {
/* 294 */     return false;
/*     */   }
/*     */   
/*     */   protected void C(Entity entity) {}
/*     */   
/*     */   protected void cB() {
/* 300 */     List<Entity> list = this.world.getEntities(this, getBoundingBox(), bx);
/*     */     
/* 302 */     for (int i = 0; i < list.size(); i++) {
/* 303 */       Entity entity = list.get(i);
/*     */       
/* 305 */       if (h(entity) <= 0.2D) {
/* 306 */         entity.collide(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, Vec3D vec3d, EnumHand enumhand) {
/* 313 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 315 */     if (!isMarker() && itemstack.getItem() != Items.NAME_TAG) {
/* 316 */       if (!this.world.isClientSide && !entityhuman.isSpectator()) {
/* 317 */         EnumItemSlot enumitemslot = EntityInsentient.d(itemstack);
/*     */         
/* 319 */         if (itemstack.isEmpty()) {
/* 320 */           EnumItemSlot enumitemslot1 = a(vec3d);
/* 321 */           EnumItemSlot enumitemslot2 = c(enumitemslot1) ? enumitemslot : enumitemslot1;
/*     */           
/* 323 */           if (a(enumitemslot2)) {
/* 324 */             a(entityhuman, enumitemslot2, itemstack, enumhand);
/*     */           }
/*     */         } else {
/* 327 */           if (c(enumitemslot)) {
/* 328 */             return EnumInteractionResult.FAIL;
/*     */           }
/*     */           
/* 331 */           if (enumitemslot.a() == EnumItemSlot.Function.HAND && !hasArms()) {
/* 332 */             return EnumInteractionResult.FAIL;
/*     */           }
/*     */           
/* 335 */           a(entityhuman, enumitemslot, itemstack, enumhand);
/*     */         } 
/*     */         
/* 338 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/* 340 */       return EnumInteractionResult.SUCCESS;
/*     */     } 
/*     */     
/* 343 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EnumItemSlot a(Vec3D vec3d) {
/* 348 */     EnumItemSlot enumitemslot = EnumItemSlot.MAINHAND;
/* 349 */     boolean flag = isSmall();
/* 350 */     double d0 = flag ? (vec3d.y * 2.0D) : vec3d.y;
/* 351 */     EnumItemSlot enumitemslot1 = EnumItemSlot.FEET;
/*     */     
/* 353 */     if (d0 >= 0.1D && d0 < 0.1D + (flag ? 0.8D : 0.45D) && a(enumitemslot1)) {
/* 354 */       enumitemslot = EnumItemSlot.FEET;
/* 355 */     } else if (d0 >= 0.9D + (flag ? 0.3D : 0.0D) && d0 < 0.9D + (flag ? 1.0D : 0.7D) && a(EnumItemSlot.CHEST)) {
/* 356 */       enumitemslot = EnumItemSlot.CHEST;
/* 357 */     } else if (d0 >= 0.4D && d0 < 0.4D + (flag ? 1.0D : 0.8D) && a(EnumItemSlot.LEGS)) {
/* 358 */       enumitemslot = EnumItemSlot.LEGS;
/* 359 */     } else if (d0 >= 1.6D && a(EnumItemSlot.HEAD)) {
/* 360 */       enumitemslot = EnumItemSlot.HEAD;
/*     */     } 
/*     */     
/* 363 */     return enumitemslot;
/*     */   }
/*     */   
/*     */   private boolean c(EnumItemSlot enumitemslot) {
/* 367 */     return ((this.bB & 1 << enumitemslot.c()) != 0);
/*     */   }
/*     */   
/*     */   private void a(EntityHuman entityhuman, EnumItemSlot enumitemslot, ItemStack itemstack, EnumHand enumhand) {
/* 371 */     ItemStack itemstack1 = getEquipment(enumitemslot);
/*     */     
/* 373 */     if ((itemstack1.isEmpty() || (this.bB & 1 << enumitemslot.c() + 8) == 0) && (
/* 374 */       !itemstack1.isEmpty() || (this.bB & 1 << enumitemslot.c() + 16) == 0)) {
/*     */ 
/*     */       
/* 377 */       CraftItemStack craftItemStack1 = CraftItemStack.asCraftMirror(itemstack1);
/* 378 */       CraftItemStack craftItemStack2 = CraftItemStack.asCraftMirror(itemstack);
/*     */       
/* 380 */       Player player = (Player)entityhuman.getBukkitEntity();
/* 381 */       ArmorStand self = (ArmorStand)getBukkitEntity();
/*     */       
/* 383 */       EquipmentSlot slot = CraftEquipmentSlot.getSlot(enumitemslot);
/* 384 */       PlayerArmorStandManipulateEvent armorStandManipulateEvent = new PlayerArmorStandManipulateEvent(player, self, (ItemStack)craftItemStack2, (ItemStack)craftItemStack1, slot);
/* 385 */       this.world.getServer().getPluginManager().callEvent((Event)armorStandManipulateEvent);
/*     */       
/* 387 */       if (armorStandManipulateEvent.isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 392 */       if (entityhuman.abilities.canInstantlyBuild && itemstack1.isEmpty() && !itemstack.isEmpty()) {
/* 393 */         ItemStack itemstack2 = itemstack.cloneItemStack();
/* 394 */         itemstack2.setCount(1);
/* 395 */         setSlot(enumitemslot, itemstack2);
/* 396 */       } else if (!itemstack.isEmpty() && itemstack.getCount() > 1) {
/* 397 */         if (itemstack1.isEmpty()) {
/* 398 */           ItemStack itemstack2 = itemstack.cloneItemStack();
/* 399 */           itemstack2.setCount(1);
/* 400 */           setSlot(enumitemslot, itemstack2);
/* 401 */           itemstack.subtract(1);
/*     */         } 
/*     */       } else {
/* 404 */         setSlot(enumitemslot, itemstack);
/* 405 */         entityhuman.a(enumhand, itemstack1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 413 */     if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f)) {
/* 414 */       return false;
/*     */     }
/*     */     
/* 417 */     if (!this.world.isClientSide && !this.dead) {
/* 418 */       if (DamageSource.OUT_OF_WORLD.equals(damagesource)) {
/* 419 */         killEntity();
/* 420 */         return false;
/* 421 */       }  if (!isInvulnerable(damagesource) && !this.bA && !isMarker()) {
/* 422 */         if (damagesource.isExplosion()) {
/* 423 */           F();
/* 424 */           killEntity();
/* 425 */           return false;
/* 426 */         }  if (DamageSource.FIRE.equals(damagesource)) {
/* 427 */           if (isBurning()) {
/* 428 */             a(0.15F);
/*     */           } else {
/* 430 */             setOnFire(5);
/*     */           } 
/*     */           
/* 433 */           return false;
/* 434 */         }  if (DamageSource.BURN.equals(damagesource) && getHealth() > 0.5F) {
/* 435 */           a(4.0F);
/* 436 */           return false;
/*     */         } 
/* 438 */         boolean flag = "arrow".equals(damagesource.p());
/* 439 */         boolean flag1 = "player".equals(damagesource.p());
/*     */         
/* 441 */         if (!flag1 && !flag) {
/* 442 */           return false;
/*     */         }
/* 444 */         if (damagesource.i() instanceof EntityArrow) {
/* 445 */           damagesource.i().die();
/*     */         }
/*     */         
/* 448 */         if (damagesource.getEntity() instanceof EntityHuman && !((EntityHuman)damagesource.getEntity()).abilities.mayBuild)
/* 449 */           return false; 
/* 450 */         if (damagesource.u()) {
/* 451 */           H();
/* 452 */           D();
/* 453 */           killEntity();
/* 454 */           return false;
/*     */         } 
/* 456 */         long i = this.world.getTime();
/*     */         
/* 458 */         if (i - this.h > 5L && !flag) {
/* 459 */           this.world.broadcastEntityEffect(this, (byte)32);
/* 460 */           this.h = i;
/*     */         } else {
/* 462 */           E();
/* 463 */           D();
/* 464 */           killEntity();
/*     */         } 
/*     */         
/* 467 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 472 */       return false;
/*     */     } 
/*     */     
/* 475 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void D() {
/* 480 */     if (this.world instanceof WorldServer) {
/* 481 */       ((WorldServer)this.world).a(EnumParticle.BLOCK_DUST, this.locX, this.locY + this.length / 1.5D, this.locZ, 10, (this.width / 4.0F), (this.length / 4.0F), (this.width / 4.0F), 0.05D, new int[] { Block.getCombinedId(Blocks.PLANKS.getBlockData()) });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(float f) {
/* 487 */     float f1 = getHealth();
/*     */     
/* 489 */     f1 -= f;
/* 490 */     if (f1 <= 0.5F) {
/* 491 */       F();
/* 492 */       killEntity();
/*     */     } else {
/* 494 */       setHealth(f1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void E() {
/* 500 */     this.drops.add(CraftItemStack.asBukkitCopy(new ItemStack(Items.ARMOR_STAND)));
/* 501 */     F();
/*     */   }
/*     */   
/*     */   private void F() {
/* 505 */     H();
/*     */ 
/*     */     
/*     */     int i;
/*     */     
/* 510 */     for (i = 0; i < this.by.size(); i++) {
/* 511 */       ItemStack itemstack = this.by.get(i);
/* 512 */       if (!itemstack.isEmpty()) {
/* 513 */         this.drops.add(CraftItemStack.asBukkitCopy(itemstack));
/* 514 */         this.by.set(i, ItemStack.a);
/*     */       } 
/*     */     } 
/*     */     
/* 518 */     for (i = 0; i < this.bz.size(); i++) {
/* 519 */       ItemStack itemstack = this.bz.get(i);
/* 520 */       if (!itemstack.isEmpty()) {
/* 521 */         this.drops.add(CraftItemStack.asBukkitCopy(itemstack));
/* 522 */         this.bz.set(i, ItemStack.a);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void H() {
/* 529 */     this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.j, bK(), 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float g(float f, float f1) {
/* 533 */     this.aO = this.lastYaw;
/* 534 */     this.aN = this.yaw;
/* 535 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 539 */     return isBaby() ? (this.length * 0.5F) : (this.length * 0.9F);
/*     */   }
/*     */   
/*     */   public double aF() {
/* 543 */     return isMarker() ? 0.0D : 0.10000000149011612D;
/*     */   }
/*     */   
/*     */   public void a(float f, float f1, float f2) {
/* 547 */     if (!isNoGravity()) {
/* 548 */       super.a(f, f1, f2);
/*     */     }
/*     */   }
/*     */   
/*     */   public void h(float f) {
/* 553 */     this.aO = this.lastYaw = f;
/* 554 */     this.aQ = this.aP = f;
/*     */   }
/*     */   
/*     */   public void setHeadRotation(float f) {
/* 558 */     this.aO = this.lastYaw = f;
/* 559 */     this.aQ = this.aP = f;
/*     */   }
/*     */   
/*     */   public void B_() {
/* 563 */     super.B_();
/* 564 */     Vector3f vector3f = this.datawatcher.<Vector3f>get(b);
/*     */     
/* 566 */     if (!this.headPose.equals(vector3f)) {
/* 567 */       setHeadPose(vector3f);
/*     */     }
/*     */     
/* 570 */     Vector3f vector3f1 = this.datawatcher.<Vector3f>get(c);
/*     */     
/* 572 */     if (!this.bodyPose.equals(vector3f1)) {
/* 573 */       setBodyPose(vector3f1);
/*     */     }
/*     */     
/* 576 */     Vector3f vector3f2 = this.datawatcher.<Vector3f>get(d);
/*     */     
/* 578 */     if (!this.leftArmPose.equals(vector3f2)) {
/* 579 */       setLeftArmPose(vector3f2);
/*     */     }
/*     */     
/* 582 */     Vector3f vector3f3 = this.datawatcher.<Vector3f>get(e);
/*     */     
/* 584 */     if (!this.rightArmPose.equals(vector3f3)) {
/* 585 */       setRightArmPose(vector3f3);
/*     */     }
/*     */     
/* 588 */     Vector3f vector3f4 = this.datawatcher.<Vector3f>get(f);
/*     */     
/* 590 */     if (!this.leftLegPose.equals(vector3f4)) {
/* 591 */       setLeftLegPose(vector3f4);
/*     */     }
/*     */     
/* 594 */     Vector3f vector3f5 = this.datawatcher.<Vector3f>get(g);
/*     */     
/* 596 */     if (!this.rightLegPose.equals(vector3f5)) {
/* 597 */       setRightLegPose(vector3f5);
/*     */     }
/*     */     
/* 600 */     boolean flag = isMarker();
/*     */     
/* 602 */     if (this.bC != flag) {
/* 603 */       a(flag);
/* 604 */       this.i = !flag;
/* 605 */       this.bC = flag;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(boolean flag) {
/* 611 */     if (flag) {
/* 612 */       setSize(0.0F, 0.0F);
/*     */     } else {
/* 614 */       setSize(0.5F, 1.975F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void G() {
/* 620 */     setInvisible(this.bA);
/*     */   }
/*     */   
/*     */   public void setInvisible(boolean flag) {
/* 624 */     this.bA = flag;
/* 625 */     super.setInvisible(flag);
/*     */   }
/*     */   
/*     */   public boolean isBaby() {
/* 629 */     return isSmall();
/*     */   }
/*     */   
/*     */   public void killEntity() {
/* 633 */     CraftEventFactory.callEntityDeathEvent(this, this.drops);
/* 634 */     die();
/*     */   }
/*     */   
/*     */   public boolean bB() {
/* 638 */     return isInvisible();
/*     */   }
/*     */   
/*     */   public EnumPistonReaction o_() {
/* 642 */     return isMarker() ? EnumPistonReaction.IGNORE : super.o_();
/*     */   }
/*     */   
/*     */   public void setSmall(boolean flag) {
/* 646 */     this.datawatcher.set(a, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(a)).byteValue(), 1, flag)));
/* 647 */     setSize(0.5F, 1.975F);
/*     */   }
/*     */   
/*     */   public boolean isSmall() {
/* 651 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setArms(boolean flag) {
/* 655 */     this.datawatcher.set(a, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(a)).byteValue(), 4, flag)));
/*     */   }
/*     */   
/*     */   public boolean hasArms() {
/* 659 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x4) != 0);
/*     */   }
/*     */   
/*     */   public void setBasePlate(boolean flag) {
/* 663 */     this.datawatcher.set(a, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(a)).byteValue(), 8, flag)));
/*     */   }
/*     */   
/*     */   public boolean hasBasePlate() {
/* 667 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x8) != 0);
/*     */   }
/*     */   
/*     */   public void setMarker(boolean flag) {
/* 671 */     this.datawatcher.set(a, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(a)).byteValue(), 16, flag)));
/* 672 */     setSize(0.5F, 1.975F);
/*     */   }
/*     */   
/*     */   public boolean isMarker() {
/* 676 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x10) != 0);
/*     */   }
/*     */   
/*     */   private byte a(byte b0, int i, boolean flag) {
/* 680 */     if (flag) {
/* 681 */       b0 = (byte)(b0 | i);
/*     */     } else {
/* 683 */       b0 = (byte)(b0 & (i ^ 0xFFFFFFFF));
/*     */     } 
/*     */     
/* 686 */     return b0;
/*     */   }
/*     */   
/*     */   public void setHeadPose(Vector3f vector3f) {
/* 690 */     this.headPose = vector3f;
/* 691 */     this.datawatcher.set(b, vector3f);
/*     */   }
/*     */   
/*     */   public void setBodyPose(Vector3f vector3f) {
/* 695 */     this.bodyPose = vector3f;
/* 696 */     this.datawatcher.set(c, vector3f);
/*     */   }
/*     */   
/*     */   public void setLeftArmPose(Vector3f vector3f) {
/* 700 */     this.leftArmPose = vector3f;
/* 701 */     this.datawatcher.set(d, vector3f);
/*     */   }
/*     */   
/*     */   public void setRightArmPose(Vector3f vector3f) {
/* 705 */     this.rightArmPose = vector3f;
/* 706 */     this.datawatcher.set(e, vector3f);
/*     */   }
/*     */   
/*     */   public void setLeftLegPose(Vector3f vector3f) {
/* 710 */     this.leftLegPose = vector3f;
/* 711 */     this.datawatcher.set(f, vector3f);
/*     */   }
/*     */   
/*     */   public void setRightLegPose(Vector3f vector3f) {
/* 715 */     this.rightLegPose = vector3f;
/* 716 */     this.datawatcher.set(g, vector3f);
/*     */   }
/*     */   
/*     */   public Vector3f u() {
/* 720 */     return this.headPose;
/*     */   }
/*     */   
/*     */   public Vector3f w() {
/* 724 */     return this.bodyPose;
/*     */   }
/*     */   
/*     */   public boolean isInteractable() {
/* 728 */     return (super.isInteractable() && !isMarker());
/*     */   }
/*     */   
/*     */   public EnumMainHand getMainHand() {
/* 732 */     return EnumMainHand.RIGHT;
/*     */   }
/*     */   
/*     */   protected SoundEffect e(int i) {
/* 736 */     return SoundEffects.k;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 741 */     return SoundEffects.l;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect cf() {
/* 746 */     return SoundEffects.j;
/*     */   }
/*     */   
/*     */   public void onLightningStrike(EntityLightning entitylightning) {}
/*     */   
/*     */   public boolean cR() {
/* 752 */     return false;
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 756 */     if (a.equals(datawatcherobject)) {
/* 757 */       setSize(0.5F, 1.975F);
/*     */     }
/*     */     
/* 760 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public boolean cS() {
/* 764 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */