/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ 
/*     */ public class EntityItem extends Entity {
/*  14 */   private static final Logger b = LogManager.getLogger();
/*  15 */   private static final DataWatcherObject<ItemStack> c = DataWatcher.a((Class)EntityItem.class, DataWatcherRegistry.f);
/*     */   private int age;
/*     */   public int pickupDelay;
/*     */   private int f;
/*     */   private String g;
/*     */   private String h;
/*     */   public float a;
/*  22 */   private int lastTick = MinecraftServer.currentTick - 1;
/*     */   
/*     */   public EntityItem(World world, double d0, double d1, double d2) {
/*  25 */     super(world);
/*  26 */     this.f = 5;
/*  27 */     this.a = (float)(Math.random() * Math.PI * 2.0D);
/*  28 */     setSize(0.25F, 0.25F);
/*  29 */     setPosition(d0, d1, d2);
/*  30 */     this.yaw = (float)(Math.random() * 360.0D);
/*  31 */     this.motX = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/*  32 */     this.motY = 0.20000000298023224D;
/*  33 */     this.motZ = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/*     */   }
/*     */   
/*     */   public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
/*  37 */     this(world, d0, d1, d2);
/*  38 */     setItemStack(itemstack);
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  42 */     return false;
/*     */   }
/*     */   
/*     */   public EntityItem(World world) {
/*  46 */     super(world);
/*  47 */     this.f = 5;
/*  48 */     this.a = (float)(Math.random() * Math.PI * 2.0D);
/*  49 */     setSize(0.25F, 0.25F);
/*  50 */     setItemStack(ItemStack.a);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  54 */     getDataWatcher().register(c, ItemStack.a);
/*     */   }
/*     */   
/*     */   public void B_() {
/*  58 */     if (getItemStack().isEmpty()) {
/*  59 */       die();
/*     */     } else {
/*  61 */       super.B_();
/*     */       
/*  63 */       int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/*  64 */       if (this.pickupDelay != 32767) this.pickupDelay -= elapsedTicks; 
/*  65 */       if (this.age != -32768) this.age += elapsedTicks; 
/*  66 */       this.lastTick = MinecraftServer.currentTick;
/*     */ 
/*     */       
/*  69 */       this.lastX = this.locX;
/*  70 */       this.lastY = this.locY;
/*  71 */       this.lastZ = this.locZ;
/*  72 */       double d0 = this.motX;
/*  73 */       double d1 = this.motY;
/*  74 */       double d2 = this.motZ;
/*     */       
/*  76 */       if (!isNoGravity()) {
/*  77 */         this.motY -= 0.03999999910593033D;
/*     */       }
/*     */       
/*  80 */       if (this.world.isClientSide) {
/*  81 */         this.noclip = false;
/*     */       } else {
/*  83 */         this.noclip = i(this.locX, ((getBoundingBox()).b + (getBoundingBox()).e) / 2.0D, this.locZ);
/*     */       } 
/*     */       
/*  86 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*  87 */       boolean flag = !((int)this.lastX == (int)this.locX && (int)this.lastY == (int)this.locY && (int)this.lastZ == (int)this.locZ);
/*     */       
/*  89 */       if (flag || this.ticksLived % 25 == 0) {
/*  90 */         if (this.world.getType(new BlockPosition(this)).getMaterial() == Material.LAVA) {
/*  91 */           this.motY = 0.20000000298023224D;
/*  92 */           this.motX = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*  93 */           this.motZ = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*  94 */           a(SoundEffects.bR, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
/*     */         } 
/*     */         
/*  97 */         if (!this.world.isClientSide) {
/*  98 */           x();
/*     */         }
/*     */       } 
/*     */       
/* 102 */       float f = 0.98F;
/*     */       
/* 104 */       if (this.onGround) {
/* 105 */         f = (this.world.getType(new BlockPosition(MathHelper.floor(this.locX), MathHelper.floor((getBoundingBox()).b) - 1, MathHelper.floor(this.locZ))).getBlock()).frictionFactor * 0.98F;
/*     */       }
/*     */       
/* 108 */       this.motX *= f;
/* 109 */       this.motY *= 0.9800000190734863D;
/* 110 */       this.motZ *= f;
/* 111 */       if (this.onGround) {
/* 112 */         this.motY *= -0.5D;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       aq();
/* 122 */       if (!this.world.isClientSide) {
/* 123 */         double d3 = this.motX - d0;
/* 124 */         double d4 = this.motY - d1;
/* 125 */         double d5 = this.motZ - d2;
/* 126 */         double d6 = d3 * d3 + d4 * d4 + d5 * d5;
/*     */         
/* 128 */         if (d6 > 0.01D) {
/* 129 */           this.impulse = true;
/*     */         }
/*     */       } 
/*     */       
/* 133 */       if (!this.world.isClientSide && this.age >= this.world.spigotConfig.itemDespawnRate) {
/*     */         
/* 135 */         if (CraftEventFactory.callItemDespawnEvent(this).isCancelled()) {
/* 136 */           this.age = 0;
/*     */           
/*     */           return;
/*     */         } 
/* 140 */         die();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/* 150 */     int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
/* 151 */     if (this.pickupDelay != 32767) this.pickupDelay -= elapsedTicks; 
/* 152 */     if (this.age != -32768) this.age += elapsedTicks; 
/* 153 */     this.lastTick = MinecraftServer.currentTick;
/*     */ 
/*     */     
/* 156 */     if (!this.world.isClientSide && this.age >= this.world.spigotConfig.itemDespawnRate) {
/*     */       
/* 158 */       if (CraftEventFactory.callItemDespawnEvent(this).isCancelled()) {
/* 159 */         this.age = 0;
/*     */         
/*     */         return;
/*     */       } 
/* 163 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void x() {
/* 170 */     double radius = this.world.spigotConfig.itemMerge;
/* 171 */     Iterator<EntityItem> iterator = this.world.<EntityItem>a(EntityItem.class, getBoundingBox().grow(radius, radius, radius)).iterator();
/*     */ 
/*     */     
/* 174 */     while (iterator.hasNext()) {
/* 175 */       EntityItem entityitem = iterator.next();
/*     */       
/* 177 */       a(entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(EntityItem entityitem) {
/* 183 */     if (entityitem == this)
/* 184 */       return false; 
/* 185 */     if (entityitem.isAlive() && isAlive()) {
/* 186 */       ItemStack itemstack = getItemStack();
/* 187 */       ItemStack itemstack1 = entityitem.getItemStack();
/*     */       
/* 189 */       if (this.pickupDelay != 32767 && entityitem.pickupDelay != 32767) {
/* 190 */         if (this.age != -32768 && entityitem.age != -32768) {
/* 191 */           if (itemstack1.getItem() != itemstack.getItem())
/* 192 */             return false; 
/* 193 */           if ((itemstack1.hasTag() ^ itemstack.hasTag()) != 0)
/* 194 */             return false; 
/* 195 */           if (itemstack1.hasTag() && !itemstack1.getTag().equals(itemstack.getTag()))
/* 196 */             return false; 
/* 197 */           if (itemstack1.getItem() == null)
/* 198 */             return false; 
/* 199 */           if (itemstack1.getItem().k() && itemstack1.getData() != itemstack.getData())
/* 200 */             return false; 
/* 201 */           if (itemstack1.getCount() < itemstack.getCount())
/* 202 */             return entityitem.a(this); 
/* 203 */           if (itemstack1.getCount() + itemstack.getCount() > itemstack1.getMaxStackSize()) {
/* 204 */             return false;
/*     */           }
/*     */           
/* 207 */           if (CraftEventFactory.callItemMergeEvent(entityitem, this).isCancelled()) return false; 
/* 208 */           itemstack.add(itemstack1.getCount());
/* 209 */           this.pickupDelay = Math.max(entityitem.pickupDelay, this.pickupDelay);
/* 210 */           this.age = Math.min(entityitem.age, this.age);
/* 211 */           setItemStack(itemstack);
/* 212 */           entityitem.die();
/*     */           
/* 214 */           return true;
/*     */         } 
/*     */         
/* 217 */         return false;
/*     */       } 
/*     */       
/* 220 */       return false;
/*     */     } 
/*     */     
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void j() {
/* 228 */     this.age = 4800;
/*     */   }
/*     */   
/*     */   public boolean aq() {
/* 232 */     if (this.world.a(getBoundingBox(), Material.WATER, this)) {
/* 233 */       if (!this.inWater && !this.justCreated) {
/* 234 */         ar();
/*     */       }
/*     */       
/* 237 */       this.inWater = true;
/*     */     } else {
/* 239 */       this.inWater = false;
/*     */     } 
/*     */     
/* 242 */     return this.inWater;
/*     */   }
/*     */   
/*     */   protected void burn(int i) {
/* 246 */     damageEntity(DamageSource.FIRE, i);
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 250 */     if (isInvulnerable(damagesource))
/* 251 */       return false; 
/* 252 */     if (!getItemStack().isEmpty() && getItemStack().getItem() == Items.NETHER_STAR && damagesource.isExplosion()) {
/* 253 */       return false;
/*     */     }
/*     */     
/* 256 */     if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f)) {
/* 257 */       return false;
/*     */     }
/*     */     
/* 260 */     ax();
/* 261 */     this.f = (int)(this.f - f);
/* 262 */     if (this.f <= 0) {
/* 263 */       die();
/*     */     }
/*     */     
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 271 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItem(EntityItem.class, new String[] { "Item" }));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 275 */     nbttagcompound.setShort("Health", (short)this.f);
/* 276 */     nbttagcompound.setShort("Age", (short)this.age);
/* 277 */     nbttagcompound.setShort("PickupDelay", (short)this.pickupDelay);
/* 278 */     if (n() != null) {
/* 279 */       nbttagcompound.setString("Thrower", this.g);
/*     */     }
/*     */     
/* 282 */     if (l() != null) {
/* 283 */       nbttagcompound.setString("Owner", this.h);
/*     */     }
/*     */     
/* 286 */     if (!getItemStack().isEmpty()) {
/* 287 */       nbttagcompound.set("Item", getItemStack().save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 293 */     this.f = nbttagcompound.getShort("Health");
/* 294 */     this.age = nbttagcompound.getShort("Age");
/* 295 */     if (nbttagcompound.hasKey("PickupDelay")) {
/* 296 */       this.pickupDelay = nbttagcompound.getShort("PickupDelay");
/*     */     }
/*     */     
/* 299 */     if (nbttagcompound.hasKey("Owner")) {
/* 300 */       this.h = nbttagcompound.getString("Owner");
/*     */     }
/*     */     
/* 303 */     if (nbttagcompound.hasKey("Thrower")) {
/* 304 */       this.g = nbttagcompound.getString("Thrower");
/*     */     }
/*     */     
/* 307 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");
/*     */     
/* 309 */     setItemStack(new ItemStack(nbttagcompound1));
/* 310 */     if (getItemStack().isEmpty()) {
/* 311 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(EntityHuman entityhuman) {
/* 317 */     if (!this.world.isClientSide) {
/* 318 */       ItemStack itemstack = getItemStack();
/* 319 */       Item item = itemstack.getItem();
/* 320 */       int i = itemstack.getCount();
/*     */ 
/*     */       
/* 323 */       int canHold = entityhuman.inventory.canHold(itemstack);
/* 324 */       int remaining = i - canHold;
/*     */       
/* 326 */       if (this.pickupDelay <= 0 && canHold > 0) {
/* 327 */         itemstack.setCount(canHold);
/*     */         
/* 329 */         PlayerPickupItemEvent playerEvent = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), (Item)getBukkitEntity(), remaining);
/* 330 */         playerEvent.setCancelled(!entityhuman.canPickUpLoot);
/* 331 */         this.world.getServer().getPluginManager().callEvent((Event)playerEvent);
/* 332 */         if (playerEvent.isCancelled()) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 337 */         EntityPickupItemEvent entityEvent = new EntityPickupItemEvent((LivingEntity)entityhuman.getBukkitEntity(), (Item)getBukkitEntity(), remaining);
/* 338 */         entityEvent.setCancelled(!entityhuman.canPickUpLoot);
/* 339 */         this.world.getServer().getPluginManager().callEvent((Event)entityEvent);
/* 340 */         if (entityEvent.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 344 */         itemstack.setCount(canHold + remaining);
/*     */ 
/*     */         
/* 347 */         this.pickupDelay = 0;
/*     */       } 
/*     */ 
/*     */       
/* 351 */       if (this.pickupDelay == 0 && (this.h == null || 6000 - this.age <= 200 || this.h.equals(entityhuman.getName())) && entityhuman.inventory.pickup(itemstack)) {
/* 352 */         entityhuman.receive(this, i);
/* 353 */         if (itemstack.isEmpty()) {
/* 354 */           die();
/* 355 */           itemstack.setCount(i);
/*     */         } 
/*     */         
/* 358 */         entityhuman.a(StatisticList.d(item), i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 365 */     return hasCustomName() ? getCustomName() : LocaleI18n.get("item." + getItemStack().a());
/*     */   }
/*     */   
/*     */   public boolean bd() {
/* 369 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity b(int i) {
/* 374 */     Entity entity = super.b(i);
/*     */     
/* 376 */     if (!this.world.isClientSide && entity instanceof EntityItem) {
/* 377 */       ((EntityItem)entity).x();
/*     */     }
/*     */     
/* 380 */     return entity;
/*     */   }
/*     */   
/*     */   public ItemStack getItemStack() {
/* 384 */     return getDataWatcher().<ItemStack>get(c);
/*     */   }
/*     */   
/*     */   public void setItemStack(ItemStack itemstack) {
/* 388 */     getDataWatcher().set(c, itemstack);
/* 389 */     getDataWatcher().markDirty(c);
/*     */   }
/*     */   
/*     */   public String l() {
/* 393 */     return this.h;
/*     */   }
/*     */   
/*     */   public void d(String s) {
/* 397 */     this.h = s;
/*     */   }
/*     */   
/*     */   public String n() {
/* 401 */     return this.g;
/*     */   }
/*     */   
/*     */   public void e(String s) {
/* 405 */     this.g = s;
/*     */   }
/*     */   
/*     */   public void q() {
/* 409 */     this.pickupDelay = 10;
/*     */   }
/*     */   
/*     */   public void r() {
/* 413 */     this.pickupDelay = 0;
/*     */   }
/*     */   
/*     */   public void s() {
/* 417 */     this.pickupDelay = 32767;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 421 */     this.pickupDelay = i;
/*     */   }
/*     */   
/*     */   public boolean t() {
/* 425 */     return (this.pickupDelay > 0);
/*     */   }
/*     */   
/*     */   public void v() {
/* 429 */     this.age = -6000;
/*     */   }
/*     */   
/*     */   public void w() {
/* 433 */     s();
/* 434 */     this.age = this.world.spigotConfig.itemDespawnRate - 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */