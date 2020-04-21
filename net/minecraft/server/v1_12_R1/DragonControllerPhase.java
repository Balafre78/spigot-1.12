/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ public class DragonControllerPhase<T extends IDragonController>
/*    */ {
/*  9 */   private static DragonControllerPhase<?>[] l = (DragonControllerPhase<?>[])new DragonControllerPhase[0];
/* 10 */   public static final DragonControllerPhase<DragonControllerHold> a = a(DragonControllerHold.class, "HoldingPattern");
/* 11 */   public static final DragonControllerPhase<DragonControllerStrafe> b = a(DragonControllerStrafe.class, "StrafePlayer");
/* 12 */   public static final DragonControllerPhase<DragonControllerLandingFly> c = a(DragonControllerLandingFly.class, "LandingApproach");
/* 13 */   public static final DragonControllerPhase<DragonControllerLanding> d = a(DragonControllerLanding.class, "Landing");
/* 14 */   public static final DragonControllerPhase<DragonControllerFly> e = a(DragonControllerFly.class, "Takeoff");
/* 15 */   public static final DragonControllerPhase<DragonControllerLandedFlame> f = a(DragonControllerLandedFlame.class, "SittingFlaming");
/* 16 */   public static final DragonControllerPhase<DragonControllerLandedSearch> g = a(DragonControllerLandedSearch.class, "SittingScanning");
/* 17 */   public static final DragonControllerPhase<DragonControllerLandedAttack> h = a(DragonControllerLandedAttack.class, "SittingAttacking");
/* 18 */   public static final DragonControllerPhase<DragonControllerCharge> i = a(DragonControllerCharge.class, "ChargingPlayer");
/* 19 */   public static final DragonControllerPhase<DragonControllerDying> j = a(DragonControllerDying.class, "Dying");
/* 20 */   public static final DragonControllerPhase<DragonControllerHover> k = a(DragonControllerHover.class, "Hover");
/*    */   
/*    */   private final Class<? extends IDragonController> m;
/*    */   private final int n;
/*    */   private final String o;
/*    */   
/*    */   private DragonControllerPhase(int paramInt, Class<? extends IDragonController> paramClass, String paramString) {
/* 27 */     this.n = paramInt;
/* 28 */     this.m = paramClass;
/* 29 */     this.o = paramString;
/*    */   }
/*    */   
/*    */   public IDragonController a(EntityEnderDragon paramEntityEnderDragon) {
/*    */     try {
/* 34 */       Constructor<? extends IDragonController> constructor = a();
/* 35 */       return constructor.newInstance(new Object[] { paramEntityEnderDragon });
/* 36 */     } catch (Exception exception) {
/* 37 */       throw new Error(exception);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected Constructor<? extends IDragonController> a() throws NoSuchMethodException {
/* 42 */     return this.m.getConstructor(new Class[] { EntityEnderDragon.class });
/*    */   }
/*    */   
/*    */   public int b() {
/* 46 */     return this.n;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     return this.o + " (#" + this.n + ")";
/*    */   }
/*    */   
/*    */   public static DragonControllerPhase<?> getById(int paramInt) {
/* 55 */     if (paramInt < 0 || paramInt >= l.length) {
/* 56 */       return a;
/*    */     }
/* 58 */     return l[paramInt];
/*    */   }
/*    */   
/*    */   public static int c() {
/* 62 */     return l.length;
/*    */   }
/*    */   
/*    */   private static <T extends IDragonController> DragonControllerPhase<T> a(Class<T> paramClass, String paramString) {
/* 66 */     DragonControllerPhase<IDragonController> dragonControllerPhase = new DragonControllerPhase<>(l.length, paramClass, paramString);
/* 67 */     l = (DragonControllerPhase<?>[])Arrays.<DragonControllerPhase>copyOf((DragonControllerPhase[])l, l.length + 1);
/* 68 */     l[dragonControllerPhase.b()] = dragonControllerPhase;
/* 69 */     return (DragonControllerPhase)dragonControllerPhase;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerPhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */