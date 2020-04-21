/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockExplodeEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ 
/*     */ public class Explosion {
/*     */   private final boolean a;
/*  24 */   private final Random c = new Random(); private final boolean b;
/*     */   private final World world;
/*     */   private final double posX;
/*     */   private final double posY;
/*     */   private final double posZ;
/*     */   public final Entity source;
/*     */   private final float size;
/*  31 */   private final List<BlockPosition> blocks = Lists.newArrayList();
/*  32 */   private final Map<EntityHuman, Vec3D> k = Maps.newHashMap();
/*     */   public boolean wasCanceled = false;
/*     */   
/*     */   public Explosion(World world, Entity entity, double d0, double d1, double d2, float f, boolean flag, boolean flag1) {
/*  36 */     this.world = world;
/*  37 */     this.source = entity;
/*  38 */     this.size = (float)Math.max(f, 0.0D);
/*  39 */     this.posX = d0;
/*  40 */     this.posY = d1;
/*  41 */     this.posZ = d2;
/*  42 */     this.a = flag;
/*  43 */     this.b = flag1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/*  48 */     if (this.size < 0.1F) {
/*     */       return;
/*     */     }
/*     */     
/*  52 */     HashSet<BlockPosition> hashset = Sets.newHashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     for (int k = 0; k < 16; k++) {
/*  59 */       for (int m = 0; m < 16; m++) {
/*  60 */         for (int n = 0; n < 16; n++) {
/*  61 */           if (k == 0 || k == 15 || m == 0 || m == 15 || n == 0 || n == 15) {
/*  62 */             double d0 = (k / 15.0F * 2.0F - 1.0F);
/*  63 */             double d1 = (m / 15.0F * 2.0F - 1.0F);
/*  64 */             double d2 = (n / 15.0F * 2.0F - 1.0F);
/*  65 */             double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */             
/*  67 */             d0 /= d3;
/*  68 */             d1 /= d3;
/*  69 */             d2 /= d3;
/*  70 */             float f = this.size * (0.7F + this.world.random.nextFloat() * 0.6F);
/*  71 */             double d4 = this.posX;
/*  72 */             double d5 = this.posY;
/*  73 */             double d6 = this.posZ;
/*     */             
/*  75 */             for (; f > 0.0F; f -= 0.22500001F) {
/*  76 */               BlockPosition blockposition = new BlockPosition(d4, d5, d6);
/*  77 */               IBlockData iblockdata = this.world.getType(blockposition);
/*     */               
/*  79 */               if (iblockdata.getMaterial() != Material.AIR) {
/*  80 */                 float f2 = (this.source != null) ? this.source.a(this, this.world, blockposition, iblockdata) : iblockdata.getBlock().a((Entity)null);
/*     */                 
/*  82 */                 f -= (f2 + 0.3F) * 0.3F;
/*     */               } 
/*     */               
/*  85 */               if (f > 0.0F && (this.source == null || this.source.a(this, this.world, blockposition, iblockdata, f)) && blockposition.getY() < 256 && blockposition.getY() >= 0) {
/*  86 */                 hashset.add(blockposition);
/*     */               }
/*     */               
/*  89 */               d4 += d0 * 0.30000001192092896D;
/*  90 */               d5 += d1 * 0.30000001192092896D;
/*  91 */               d6 += d2 * 0.30000001192092896D;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     this.blocks.addAll(hashset);
/*  99 */     float f3 = this.size * 2.0F;
/*     */     
/* 101 */     int i = MathHelper.floor(this.posX - f3 - 1.0D);
/* 102 */     int j = MathHelper.floor(this.posX + f3 + 1.0D);
/* 103 */     int l = MathHelper.floor(this.posY - f3 - 1.0D);
/* 104 */     int i1 = MathHelper.floor(this.posY + f3 + 1.0D);
/* 105 */     int j1 = MathHelper.floor(this.posZ - f3 - 1.0D);
/* 106 */     int k1 = MathHelper.floor(this.posZ + f3 + 1.0D);
/* 107 */     List<Entity> list = this.world.getEntities(this.source, new AxisAlignedBB(i, l, j1, j, i1, k1));
/* 108 */     Vec3D vec3d = new Vec3D(this.posX, this.posY, this.posZ);
/*     */     
/* 110 */     for (int l1 = 0; l1 < list.size(); l1++) {
/* 111 */       Entity entity = list.get(l1);
/*     */       
/* 113 */       if (!entity.bB()) {
/* 114 */         double d7 = entity.e(this.posX, this.posY, this.posZ) / f3;
/*     */         
/* 116 */         if (d7 <= 1.0D) {
/* 117 */           double d8 = entity.locX - this.posX;
/* 118 */           double d9 = entity.locY + entity.getHeadHeight() - this.posY;
/* 119 */           double d10 = entity.locZ - this.posZ;
/* 120 */           double d11 = MathHelper.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
/*     */           
/* 122 */           if (d11 != 0.0D) {
/* 123 */             d8 /= d11;
/* 124 */             d9 /= d11;
/* 125 */             d10 /= d11;
/* 126 */             double d12 = this.world.a(vec3d, entity.getBoundingBox());
/* 127 */             double d13 = (1.0D - d7) * d12;
/*     */ 
/*     */ 
/*     */             
/* 131 */             CraftEventFactory.entityDamage = this.source;
/* 132 */             entity.forceExplosionKnockback = false;
/* 133 */             boolean wasDamaged = entity.damageEntity(DamageSource.explosion(this), (int)((d13 * d13 + d13) / 2.0D * 7.0D * f3 + 1.0D));
/* 134 */             CraftEventFactory.entityDamage = null;
/* 135 */             if (wasDamaged || entity instanceof EntityTNTPrimed || entity instanceof EntityFallingBlock || entity.forceExplosionKnockback) {
/*     */ 
/*     */ 
/*     */               
/* 139 */               double d14 = d13;
/*     */               
/* 141 */               if (entity instanceof EntityLiving) {
/* 142 */                 d14 = EnchantmentProtection.a((EntityLiving)entity, d13);
/*     */               }
/*     */               
/* 145 */               entity.motX += d8 * d14;
/* 146 */               entity.motY += d9 * d14;
/* 147 */               entity.motZ += d10 * d14;
/* 148 */               if (entity instanceof EntityHuman) {
/* 149 */                 EntityHuman entityhuman = (EntityHuman)entity;
/*     */                 
/* 151 */                 if (!entityhuman.isSpectator() && (!entityhuman.z() || !entityhuman.abilities.isFlying)) {
/* 152 */                   this.k.put(entityhuman, new Vec3D(d8 * d13, d9 * d13, d10 * d13));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 163 */     this.world.a((EntityHuman)null, this.posX, this.posY, this.posZ, SoundEffects.bV, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
/* 164 */     if (this.size >= 2.0F && this.b) {
/* 165 */       this.world.addParticle(EnumParticle.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
/*     */     } else {
/* 167 */       this.world.addParticle(EnumParticle.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     if (this.b) {
/*     */       boolean cancelled; List<Block> bukkitBlocks; float yield;
/* 175 */       CraftWorld craftWorld = this.world.getWorld();
/* 176 */       CraftEntity craftEntity = (this.source == null) ? null : this.source.getBukkitEntity();
/* 177 */       Location location = new Location((World)craftWorld, this.posX, this.posY, this.posZ);
/*     */       
/* 179 */       List<Block> blockList = Lists.newArrayList();
/* 180 */       for (int i1 = this.blocks.size() - 1; i1 >= 0; i1--) {
/* 181 */         BlockPosition cpos = this.blocks.get(i1);
/* 182 */         Block bblock = craftWorld.getBlockAt(cpos.getX(), cpos.getY(), cpos.getZ());
/* 183 */         if (bblock.getType() != Material.AIR) {
/* 184 */           blockList.add(bblock);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       if (craftEntity != null) {
/* 193 */         EntityExplodeEvent event = new EntityExplodeEvent((Entity)craftEntity, location, blockList, 1.0F / this.size);
/* 194 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/* 195 */         cancelled = event.isCancelled();
/* 196 */         bukkitBlocks = event.blockList();
/* 197 */         yield = event.getYield();
/*     */       } else {
/* 199 */         BlockExplodeEvent event = new BlockExplodeEvent(location.getBlock(), blockList, 1.0F / this.size);
/* 200 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/* 201 */         cancelled = event.isCancelled();
/* 202 */         bukkitBlocks = event.blockList();
/* 203 */         yield = event.getYield();
/*     */       } 
/*     */       
/* 206 */       this.blocks.clear();
/*     */       
/* 208 */       for (Block bblock : bukkitBlocks) {
/* 209 */         BlockPosition coords = new BlockPosition(bblock.getX(), bblock.getY(), bblock.getZ());
/* 210 */         this.blocks.add(coords);
/*     */       } 
/*     */       
/* 213 */       if (cancelled) {
/* 214 */         this.wasCanceled = true;
/*     */         
/*     */         return;
/*     */       } 
/* 218 */       Iterator<BlockPosition> iterator = this.blocks.iterator();
/*     */       
/* 220 */       while (iterator.hasNext()) {
/* 221 */         BlockPosition blockposition = iterator.next();
/* 222 */         IBlockData iblockdata = this.world.getType(blockposition);
/* 223 */         Block block = iblockdata.getBlock();
/*     */         
/* 225 */         if (flag) {
/* 226 */           double d0 = (blockposition.getX() + this.world.random.nextFloat());
/* 227 */           double d1 = (blockposition.getY() + this.world.random.nextFloat());
/* 228 */           double d2 = (blockposition.getZ() + this.world.random.nextFloat());
/* 229 */           double d3 = d0 - this.posX;
/* 230 */           double d4 = d1 - this.posY;
/* 231 */           double d5 = d2 - this.posZ;
/* 232 */           double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*     */           
/* 234 */           d3 /= d6;
/* 235 */           d4 /= d6;
/* 236 */           d5 /= d6;
/* 237 */           double d7 = 0.5D / (d6 / this.size + 0.1D);
/*     */           
/* 239 */           d7 *= (this.world.random.nextFloat() * this.world.random.nextFloat() + 0.3F);
/* 240 */           d3 *= d7;
/* 241 */           d4 *= d7;
/* 242 */           d5 *= d7;
/* 243 */           this.world.addParticle(EnumParticle.EXPLOSION_NORMAL, (d0 + this.posX) / 2.0D, (d1 + this.posY) / 2.0D, (d2 + this.posZ) / 2.0D, d3, d4, d5, new int[0]);
/* 244 */           this.world.addParticle(EnumParticle.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
/*     */         } 
/*     */         
/* 247 */         if (iblockdata.getMaterial() != Material.AIR) {
/* 248 */           if (block.a(this))
/*     */           {
/* 250 */             block.dropNaturally(this.world, blockposition, this.world.getType(blockposition), yield, 0);
/*     */           }
/*     */           
/* 253 */           this.world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
/* 254 */           block.wasExploded(this.world, blockposition, this);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 259 */     if (this.a) {
/* 260 */       Iterator<BlockPosition> iterator = this.blocks.iterator();
/*     */       
/* 262 */       while (iterator.hasNext()) {
/* 263 */         BlockPosition blockposition = iterator.next();
/* 264 */         if (this.world.getType(blockposition).getMaterial() == Material.AIR && this.world.getType(blockposition.down()).b() && this.c.nextInt(3) == 0)
/*     */         {
/* 266 */           if (!CraftEventFactory.callBlockIgniteEvent(this.world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this).isCancelled()) {
/* 267 */             this.world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<EntityHuman, Vec3D> b() {
/* 277 */     return this.k;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getSource() {
/* 283 */     return (this.source == null) ? null : ((this.source instanceof EntityTNTPrimed) ? ((EntityTNTPrimed)this.source).getSource() : ((this.source instanceof EntityLiving) ? (EntityLiving)this.source : ((this.source instanceof EntityFireball) ? ((EntityFireball)this.source).shooter : null)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearBlocks() {
/* 288 */     this.blocks.clear();
/*     */   }
/*     */   
/*     */   public List<BlockPosition> getBlocks() {
/* 292 */     return this.blocks;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Explosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */