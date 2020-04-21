/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public abstract class MobSpawnerAbstract {
/*  10 */   public int spawnDelay = 20;
/*  11 */   private final List<MobSpawnerData> mobs = Lists.newArrayList();
/*  12 */   private MobSpawnerData spawnData = new MobSpawnerData();
/*     */   private double d;
/*     */   private double e;
/*  15 */   private int minSpawnDelay = 200;
/*  16 */   private int maxSpawnDelay = 800;
/*  17 */   private int spawnCount = 4;
/*     */   private Entity i;
/*  19 */   private int maxNearbyEntities = 6;
/*  20 */   private int requiredPlayerRange = 16;
/*  21 */   private int spawnRange = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MinecraftKey getMobName() {
/*  27 */     String s = this.spawnData.b().getString("id");
/*     */     
/*  29 */     return UtilColor.b(s) ? null : new MinecraftKey(s);
/*     */   }
/*     */   
/*     */   public void setMobName(@Nullable MinecraftKey minecraftkey) {
/*  33 */     if (minecraftkey != null) {
/*  34 */       this.spawnData.b().setString("id", minecraftkey.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean h() {
/*  40 */     BlockPosition blockposition = b();
/*     */     
/*  42 */     return a().isPlayerNearby(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, this.requiredPlayerRange);
/*     */   }
/*     */   
/*     */   public void c() {
/*  46 */     if (!h()) {
/*  47 */       this.e = this.d;
/*     */     } else {
/*  49 */       BlockPosition blockposition = b();
/*     */       
/*  51 */       if ((a()).isClientSide) {
/*  52 */         double d0 = (blockposition.getX() + (a()).random.nextFloat());
/*  53 */         double d1 = (blockposition.getY() + (a()).random.nextFloat());
/*  54 */         double d2 = (blockposition.getZ() + (a()).random.nextFloat());
/*     */         
/*  56 */         a().addParticle(EnumParticle.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/*  57 */         a().addParticle(EnumParticle.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/*  58 */         if (this.spawnDelay > 0) {
/*  59 */           this.spawnDelay--;
/*     */         }
/*     */         
/*  62 */         this.e = this.d;
/*  63 */         this.d = (this.d + (1000.0F / (this.spawnDelay + 200.0F))) % 360.0D;
/*     */       } else {
/*  65 */         if (this.spawnDelay == -1) {
/*  66 */           i();
/*     */         }
/*     */         
/*  69 */         if (this.spawnDelay > 0) {
/*  70 */           this.spawnDelay--;
/*     */           
/*     */           return;
/*     */         } 
/*  74 */         boolean flag = false;
/*     */         
/*  76 */         for (int i = 0; i < this.spawnCount; i++) {
/*  77 */           NBTTagCompound nbttagcompound = this.spawnData.b();
/*  78 */           NBTTagList nbttaglist = nbttagcompound.getList("Pos", 6);
/*  79 */           World world = a();
/*  80 */           int j = nbttaglist.size();
/*  81 */           double d3 = (j >= 1) ? nbttaglist.f(0) : (blockposition.getX() + (world.random.nextDouble() - world.random.nextDouble()) * this.spawnRange + 0.5D);
/*  82 */           double d4 = (j >= 2) ? nbttaglist.f(1) : (blockposition.getY() + world.random.nextInt(3) - 1);
/*  83 */           double d5 = (j >= 3) ? nbttaglist.f(2) : (blockposition.getZ() + (world.random.nextDouble() - world.random.nextDouble()) * this.spawnRange + 0.5D);
/*  84 */           Entity entity = ChunkRegionLoader.a(nbttagcompound, world, d3, d4, d5, false);
/*     */           
/*  86 */           if (entity == null) {
/*     */             return;
/*     */           }
/*     */           
/*  90 */           int k = world.a(entity.getClass(), (new AxisAlignedBB(blockposition.getX(), blockposition.getY(), blockposition.getZ(), (blockposition.getX() + 1), (blockposition.getY() + 1), (blockposition.getZ() + 1))).g(this.spawnRange)).size();
/*     */           
/*  92 */           if (k >= this.maxNearbyEntities) {
/*  93 */             i();
/*     */             
/*     */             return;
/*     */           } 
/*  97 */           EntityInsentient entityinsentient = (entity instanceof EntityInsentient) ? (EntityInsentient)entity : null;
/*     */           
/*  99 */           entity.setPositionRotation(entity.locX, entity.locY, entity.locZ, world.random.nextFloat() * 360.0F, 0.0F);
/* 100 */           if (entityinsentient == null || (entityinsentient.P() && entityinsentient.canSpawn())) {
/* 101 */             if (this.spawnData.b().d() == 1 && this.spawnData.b().hasKeyOfType("id", 8) && entity instanceof EntityInsentient) {
/* 102 */               ((EntityInsentient)entity).prepare(world.D(new BlockPosition(entity)), null);
/*     */             }
/*     */             
/* 105 */             if (entity.world.spigotConfig.nerfSpawnerMobs)
/*     */             {
/* 107 */               entity.fromMobSpawner = true;
/*     */             }
/* 109 */             if (!CraftEventFactory.callSpawnerSpawnEvent(entity, blockposition).isCancelled()) {
/*     */ 
/*     */ 
/*     */               
/* 113 */               ChunkRegionLoader.a(entity, world, CreatureSpawnEvent.SpawnReason.SPAWNER);
/* 114 */               world.triggerEffect(2004, blockposition, 0);
/* 115 */               if (entityinsentient != null) {
/* 116 */                 entityinsentient.doSpawnEffect();
/*     */               }
/*     */               
/* 119 */               flag = true;
/*     */             } 
/*     */           } 
/*     */         } 
/* 123 */         if (flag) {
/* 124 */           i();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void i() {
/* 132 */     if (this.maxSpawnDelay <= this.minSpawnDelay) {
/* 133 */       this.spawnDelay = this.minSpawnDelay;
/*     */     } else {
/* 135 */       int i = this.maxSpawnDelay - this.minSpawnDelay;
/*     */       
/* 137 */       this.spawnDelay = this.minSpawnDelay + (a()).random.nextInt(i);
/*     */     } 
/*     */     
/* 140 */     if (!this.mobs.isEmpty()) {
/* 141 */       a(WeightedRandom.<MobSpawnerData>a((a()).random, this.mobs));
/*     */     }
/*     */     
/* 144 */     a(1);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 148 */     this.spawnDelay = nbttagcompound.getShort("Delay");
/* 149 */     this.mobs.clear();
/* 150 */     if (nbttagcompound.hasKeyOfType("SpawnPotentials", 9)) {
/* 151 */       NBTTagList nbttaglist = nbttagcompound.getList("SpawnPotentials", 10);
/*     */       
/* 153 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 154 */         this.mobs.add(new MobSpawnerData(nbttaglist.get(i)));
/*     */       }
/*     */     } 
/*     */     
/* 158 */     if (nbttagcompound.hasKeyOfType("SpawnData", 10)) {
/* 159 */       a(new MobSpawnerData(1, nbttagcompound.getCompound("SpawnData")));
/* 160 */     } else if (!this.mobs.isEmpty()) {
/* 161 */       a(WeightedRandom.<MobSpawnerData>a((a()).random, this.mobs));
/*     */     } 
/*     */     
/* 164 */     if (nbttagcompound.hasKeyOfType("MinSpawnDelay", 99)) {
/* 165 */       this.minSpawnDelay = nbttagcompound.getShort("MinSpawnDelay");
/* 166 */       this.maxSpawnDelay = nbttagcompound.getShort("MaxSpawnDelay");
/* 167 */       this.spawnCount = nbttagcompound.getShort("SpawnCount");
/*     */     } 
/*     */     
/* 170 */     if (nbttagcompound.hasKeyOfType("MaxNearbyEntities", 99)) {
/* 171 */       this.maxNearbyEntities = nbttagcompound.getShort("MaxNearbyEntities");
/* 172 */       this.requiredPlayerRange = nbttagcompound.getShort("RequiredPlayerRange");
/*     */     } 
/*     */     
/* 175 */     if (nbttagcompound.hasKeyOfType("SpawnRange", 99)) {
/* 176 */       this.spawnRange = nbttagcompound.getShort("SpawnRange");
/*     */     }
/*     */     
/* 179 */     if (a() != null) {
/* 180 */       this.i = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b(NBTTagCompound nbttagcompound) {
/* 186 */     MinecraftKey minecraftkey = getMobName();
/*     */     
/* 188 */     if (minecraftkey == null) {
/* 189 */       return nbttagcompound;
/*     */     }
/* 191 */     nbttagcompound.setShort("Delay", (short)this.spawnDelay);
/* 192 */     nbttagcompound.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
/* 193 */     nbttagcompound.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
/* 194 */     nbttagcompound.setShort("SpawnCount", (short)this.spawnCount);
/* 195 */     nbttagcompound.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
/* 196 */     nbttagcompound.setShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
/* 197 */     nbttagcompound.setShort("SpawnRange", (short)this.spawnRange);
/* 198 */     nbttagcompound.set("SpawnData", this.spawnData.b().g());
/* 199 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 201 */     if (this.mobs.isEmpty()) {
/* 202 */       nbttaglist.add(this.spawnData.a());
/*     */     } else {
/* 204 */       Iterator<MobSpawnerData> iterator = this.mobs.iterator();
/*     */       
/* 206 */       while (iterator.hasNext()) {
/* 207 */         MobSpawnerData mobspawnerdata = iterator.next();
/*     */         
/* 209 */         nbttaglist.add(mobspawnerdata.a());
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     nbttagcompound.set("SpawnPotentials", nbttaglist);
/* 214 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(int i) {
/* 219 */     if (i == 1 && (a()).isClientSide) {
/* 220 */       this.spawnDelay = this.minSpawnDelay;
/* 221 */       return true;
/*     */     } 
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(MobSpawnerData mobspawnerdata) {
/* 228 */     this.spawnData = mobspawnerdata;
/*     */   }
/*     */   
/*     */   public abstract void a(int paramInt);
/*     */   
/*     */   public abstract World a();
/*     */   
/*     */   public abstract BlockPosition b();
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobSpawnerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */