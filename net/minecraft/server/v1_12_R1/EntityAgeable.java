/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public abstract class EntityAgeable extends EntityCreature {
/*   7 */   private static final DataWatcherObject<Boolean> bx = DataWatcher.a((Class)EntityAgeable.class, DataWatcherRegistry.h);
/*     */   protected int a;
/*     */   protected int b;
/*     */   protected int c;
/*  11 */   private float by = -1.0F;
/*     */   
/*     */   private float bz;
/*     */   
/*     */   public boolean ageLocked;
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/*  19 */     super.inactiveTick();
/*  20 */     if (this.world.isClientSide || this.ageLocked) {
/*     */       
/*  22 */       a(isBaby());
/*     */     } else {
/*     */       
/*  25 */       int i = getAge();
/*     */       
/*  27 */       if (i < 0) {
/*     */         
/*  29 */         i++;
/*  30 */         setAgeRaw(i);
/*  31 */       } else if (i > 0) {
/*     */         
/*  33 */         i--;
/*  34 */         setAgeRaw(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable(World world) {
/*  41 */     super(world);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public abstract EntityAgeable createChild(EntityAgeable paramEntityAgeable);
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/*  48 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  50 */     if (itemstack.getItem() == Items.SPAWN_EGG) {
/*  51 */       if (!this.world.isClientSide) {
/*  52 */         Class<?> oclass = EntityTypes.b.get(ItemMonsterEgg.h(itemstack));
/*     */         
/*  54 */         if (oclass != null && getClass() == oclass) {
/*  55 */           EntityAgeable entityageable = createChild(this);
/*     */           
/*  57 */           if (entityageable != null) {
/*  58 */             entityageable.setAgeRaw(-24000);
/*  59 */             entityageable.setPositionRotation(this.locX, this.locY, this.locZ, 0.0F, 0.0F);
/*  60 */             this.world.addEntity(entityageable, CreatureSpawnEvent.SpawnReason.SPAWNER_EGG);
/*  61 */             if (itemstack.hasName()) {
/*  62 */               entityageable.setCustomName(itemstack.getName());
/*     */             }
/*     */             
/*  65 */             if (!entityhuman.abilities.canInstantlyBuild) {
/*  66 */               itemstack.subtract(1);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  72 */       return true;
/*     */     } 
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(ItemStack itemstack, Class<? extends Entity> oclass) {
/*  79 */     if (itemstack.getItem() != Items.SPAWN_EGG) {
/*  80 */       return false;
/*     */     }
/*  82 */     Class<? extends Entity> oclass1 = EntityTypes.b.get(ItemMonsterEgg.h(itemstack));
/*     */     
/*  84 */     return (oclass1 != null && oclass == oclass1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  89 */     super.i();
/*  90 */     this.datawatcher.register(bx, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public int getAge() {
/*  94 */     return this.world.isClientSide ? (((Boolean)this.datawatcher.<Boolean>get(bx)).booleanValue() ? -1 : 1) : this.a;
/*     */   }
/*     */   
/*     */   public void setAge(int i, boolean flag) {
/*  98 */     int j = getAge();
/*  99 */     int k = j;
/*     */     
/* 101 */     j += i * 20;
/* 102 */     if (j > 0) {
/* 103 */       j = 0;
/* 104 */       if (k < 0) {
/* 105 */         p();
/*     */       }
/*     */     } 
/*     */     
/* 109 */     int l = j - k;
/*     */     
/* 111 */     setAgeRaw(j);
/* 112 */     if (flag) {
/* 113 */       this.b += l;
/* 114 */       if (this.c == 0) {
/* 115 */         this.c = 40;
/*     */       }
/*     */     } 
/*     */     
/* 119 */     if (getAge() == 0) {
/* 120 */       setAgeRaw(this.b);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAge(int i) {
/* 126 */     setAge(i, false);
/*     */   }
/*     */   
/*     */   public void setAgeRaw(int i) {
/* 130 */     this.datawatcher.set(bx, Boolean.valueOf((i < 0)));
/* 131 */     this.a = i;
/* 132 */     a(isBaby());
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 136 */     super.b(nbttagcompound);
/* 137 */     nbttagcompound.setInt("Age", getAge());
/* 138 */     nbttagcompound.setInt("ForcedAge", this.b);
/* 139 */     nbttagcompound.setBoolean("AgeLocked", this.ageLocked);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 143 */     super.a(nbttagcompound);
/* 144 */     setAgeRaw(nbttagcompound.getInt("Age"));
/* 145 */     this.b = nbttagcompound.getInt("ForcedAge");
/* 146 */     this.ageLocked = nbttagcompound.getBoolean("AgeLocked");
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 150 */     if (bx.equals(datawatcherobject)) {
/* 151 */       a(isBaby());
/*     */     }
/*     */     
/* 154 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public void n() {
/* 158 */     super.n();
/* 159 */     if (this.world.isClientSide || this.ageLocked) {
/* 160 */       if (this.c > 0) {
/* 161 */         if (this.c % 4 == 0) {
/* 162 */           this.world.addParticle(EnumParticle.VILLAGER_HAPPY, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width, this.locY + 0.5D + (this.random.nextFloat() * this.length), this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */         
/* 165 */         this.c--;
/*     */       } 
/*     */     } else {
/* 168 */       int i = getAge();
/*     */       
/* 170 */       if (i < 0) {
/* 171 */         i++;
/* 172 */         setAgeRaw(i);
/* 173 */         if (i == 0) {
/* 174 */           p();
/*     */         }
/* 176 */       } else if (i > 0) {
/* 177 */         i--;
/* 178 */         setAgeRaw(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void p() {}
/*     */   
/*     */   public boolean isBaby() {
/* 187 */     return (getAge() < 0);
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 191 */     a(flag ? 0.5F : 1.0F);
/*     */   }
/*     */   
/*     */   public final void setSize(float f, float f1) {
/* 195 */     boolean flag = (this.by > 0.0F);
/*     */     
/* 197 */     this.by = f;
/* 198 */     this.bz = f1;
/* 199 */     if (!flag) {
/* 200 */       a(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void a(float f) {
/* 206 */     super.setSize(this.by * f, this.bz * f);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityAgeable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */