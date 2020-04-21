/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ 
/*    */ 
/*    */ public enum DimensionManager
/*    */ {
/*  9 */   OVERWORLD(0, "overworld", "", (Class)WorldProviderNormal.class),
/* 10 */   NETHER(-1, "the_nether", "_nether", (Class)WorldProviderHell.class),
/* 11 */   THE_END(1, "the_end", "_end", (Class)WorldProviderTheEnd.class);
/*    */   
/*    */   private final int d;
/*    */   
/*    */   private final String e;
/*    */   private final String f;
/*    */   private final Class<? extends WorldProvider> g;
/*    */   
/*    */   DimensionManager(int paramInt1, String paramString1, String paramString2, Class<? extends WorldProvider> paramClass) {
/* 20 */     this.d = paramInt1;
/* 21 */     this.e = paramString1;
/* 22 */     this.f = paramString2;
/* 23 */     this.g = paramClass;
/*    */   }
/*    */   
/*    */   public int getDimensionID() {
/* 27 */     return this.d;
/*    */   }
/*    */   
/*    */   public String b() {
/* 31 */     return this.e;
/*    */   }
/*    */   
/*    */   public String c() {
/* 35 */     return this.f;
/*    */   }
/*    */   
/*    */   public WorldProvider d() {
/*    */     try {
/* 40 */       Constructor<? extends WorldProvider> constructor = this.g.getConstructor(new Class[0]);
/* 41 */       return constructor.newInstance(new Object[0]);
/* 42 */     } catch (NoSuchMethodException noSuchMethodException) {
/* 43 */       throw new Error("Could not create new dimension", noSuchMethodException);
/* 44 */     } catch (InvocationTargetException invocationTargetException) {
/* 45 */       throw new Error("Could not create new dimension", invocationTargetException);
/* 46 */     } catch (InstantiationException instantiationException) {
/* 47 */       throw new Error("Could not create new dimension", instantiationException);
/* 48 */     } catch (IllegalAccessException illegalAccessException) {
/* 49 */       throw new Error("Could not create new dimension", illegalAccessException);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static DimensionManager a(int paramInt) {
/* 54 */     for (DimensionManager dimensionManager : values()) {
/* 55 */       if (dimensionManager.getDimensionID() == paramInt) {
/* 56 */         return dimensionManager;
/*    */       }
/*    */     } 
/*    */     
/* 60 */     throw new IllegalArgumentException("Invalid dimension id " + paramInt);
/*    */   }
/*    */   
/*    */   public static DimensionManager a(String paramString) {
/* 64 */     for (DimensionManager dimensionManager : values()) {
/* 65 */       if (dimensionManager.b().equals(paramString)) {
/* 66 */         return dimensionManager;
/*    */       }
/*    */     } 
/*    */     
/* 70 */     throw new IllegalArgumentException("Invalid dimension " + paramString);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DimensionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */