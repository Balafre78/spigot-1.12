/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
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
/*     */ public abstract class EntityIllagerWizard
/*     */   extends EntityIllagerAbstract
/*     */ {
/*  18 */   private static final DataWatcherObject<Byte> c = DataWatcher.a((Class)EntityIllagerWizard.class, DataWatcherRegistry.a);
/*     */   
/*     */   protected int b;
/*  21 */   private Spell bx = Spell.NONE;
/*     */   
/*     */   public EntityIllagerWizard(World paramWorld) {
/*  24 */     super(paramWorld);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  29 */     super.i();
/*     */     
/*  31 */     this.datawatcher.register(c, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/*  36 */     super.a(paramNBTTagCompound);
/*     */     
/*  38 */     this.b = paramNBTTagCompound.getInt("SpellTicks");
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/*  43 */     super.b(paramNBTTagCompound);
/*     */     
/*  45 */     paramNBTTagCompound.setInt("SpellTicks", this.b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dn() {
/*  57 */     if (this.world.isClientSide) {
/*  58 */       return (((Byte)this.datawatcher.<Byte>get(c)).byteValue() > 0);
/*     */     }
/*  60 */     return (this.b > 0);
/*     */   }
/*     */   
/*     */   public void setSpell(Spell paramSpell) {
/*  64 */     this.bx = paramSpell;
/*  65 */     this.datawatcher.set(c, Byte.valueOf((byte)Spell.a(paramSpell)));
/*     */   }
/*     */   
/*     */   public Spell getSpell() {
/*  69 */     if (!this.world.isClientSide) {
/*  70 */       return this.bx;
/*     */     }
/*  72 */     return Spell.a(((Byte)this.datawatcher.<Byte>get(c)).byteValue());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/*  77 */     super.M();
/*     */     
/*  79 */     if (this.b > 0) {
/*  80 */       this.b--;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/*  86 */     super.B_();
/*     */     
/*  88 */     if (this.world.isClientSide && dn()) {
/*  89 */       Spell spell = getSpell();
/*  90 */       double d1 = Spell.b(spell)[0];
/*  91 */       double d2 = Spell.b(spell)[1];
/*  92 */       double d3 = Spell.b(spell)[2];
/*     */ 
/*     */       
/*  95 */       float f1 = this.aN * 0.017453292F + MathHelper.cos(this.ticksLived * 0.6662F) * 0.25F;
/*  96 */       float f2 = MathHelper.cos(f1);
/*  97 */       float f3 = MathHelper.sin(f1);
/*     */       
/*  99 */       this.world.addParticle(EnumParticle.SPELL_MOB, this.locX + f2 * 0.6D, this.locY + 1.8D, this.locZ + f3 * 0.6D, d1, d2, d3, new int[0]);
/* 100 */       this.world.addParticle(EnumParticle.SPELL_MOB, this.locX - f2 * 0.6D, this.locY + 1.8D, this.locZ - f3 * 0.6D, d1, d2, d3, new int[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int dp() {
/* 105 */     return this.b;
/*     */   }
/*     */   
/*     */   protected abstract SoundEffect dm();
/*     */   
/*     */   public class b extends PathfinderGoal {
/*     */     public b(EntityIllagerWizard this$0) {
/* 112 */       a(3);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 117 */       return (this.b.dp() > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 122 */       super.c();
/* 123 */       EntityIllagerWizard.a(this.b).p();
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 128 */       super.d();
/* 129 */       this.b.setSpell(EntityIllagerWizard.Spell.NONE);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 134 */       if (this.b.getGoalTarget() != null)
/* 135 */         this.b.getControllerLook().a(this.b.getGoalTarget(), this.b.O(), this.b.N()); 
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class c extends PathfinderGoal {
/*     */     protected int c;
/*     */     protected int d;
/*     */     
/*     */     protected c(EntityIllagerWizard this$0) {}
/*     */     
/*     */     public boolean a() {
/* 146 */       if (this.e.getGoalTarget() == null) {
/* 147 */         return false;
/*     */       }
/* 149 */       if (this.e.dn())
/*     */       {
/* 151 */         return false;
/*     */       }
/* 153 */       if (this.e.ticksLived < this.d) {
/* 154 */         return false;
/*     */       }
/* 156 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 161 */       return (this.e.getGoalTarget() != null && this.c > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 166 */       this.c = m();
/* 167 */       this.e.b = f();
/* 168 */       this.d = this.e.ticksLived + i();
/* 169 */       SoundEffect soundEffect = k();
/* 170 */       if (soundEffect != null) {
/* 171 */         this.e.a(soundEffect, 1.0F, 1.0F);
/*     */       }
/* 173 */       this.e.setSpell(l());
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 178 */       this.c--;
/* 179 */       if (this.c == 0) {
/* 180 */         j();
/* 181 */         this.e.a(this.e.dm(), 1.0F, 1.0F);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected abstract void j();
/*     */     
/*     */     protected int m() {
/* 188 */       return 20;
/*     */     }
/*     */     
/*     */     protected abstract int f();
/*     */     
/*     */     protected abstract int i();
/*     */     
/*     */     @Nullable
/*     */     protected abstract SoundEffect k();
/*     */     
/*     */     protected abstract EntityIllagerWizard.Spell l();
/*     */   }
/*     */   
/*     */   public enum Spell {
/* 202 */     NONE(0, 0.0D, 0.0D, 0.0D),
/* 203 */     SUMMON_VEX(1, 0.7D, 0.7D, 0.8D),
/* 204 */     FANGS(2, 0.4D, 0.3D, 0.35D),
/* 205 */     WOLOLO(3, 0.7D, 0.5D, 0.2D),
/* 206 */     DISAPPEAR(4, 0.3D, 0.3D, 0.8D),
/* 207 */     BLINDNESS(5, 0.1D, 0.1D, 0.2D);
/*     */     
/*     */     private final int g;
/*     */     
/*     */     private final double[] h;
/*     */     
/*     */     Spell(int param1Int1, double param1Double1, double param1Double2, double param1Double3) {
/* 214 */       this.g = param1Int1;
/* 215 */       this.h = new double[] { param1Double1, param1Double2, param1Double3 };
/*     */     }
/*     */     
/*     */     public static Spell a(int param1Int) {
/* 219 */       for (Spell spell : values()) {
/* 220 */         if (param1Int == spell.g) {
/* 221 */           return spell;
/*     */         }
/*     */       } 
/* 224 */       return NONE;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityIllagerWizard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */