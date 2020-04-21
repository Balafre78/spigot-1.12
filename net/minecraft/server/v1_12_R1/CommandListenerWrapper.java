/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class CommandListenerWrapper
/*     */   implements ICommandListener
/*     */ {
/*     */   public final ICommandListener base;
/*     */   @Nullable
/*     */   private final Vec3D b;
/*     */   @Nullable
/*     */   private final BlockPosition c;
/*     */   @Nullable
/*     */   private final Integer d;
/*     */   @Nullable
/*     */   private final Entity e;
/*     */   @Nullable
/*     */   private final Boolean f;
/*     */   
/*     */   public CommandListenerWrapper(ICommandListener paramICommandListener, @Nullable Vec3D paramVec3D, @Nullable BlockPosition paramBlockPosition, @Nullable Integer paramInteger, @Nullable Entity paramEntity, @Nullable Boolean paramBoolean) {
/*  22 */     this.base = paramICommandListener;
/*  23 */     this.b = paramVec3D;
/*  24 */     this.c = paramBlockPosition;
/*  25 */     this.d = paramInteger;
/*  26 */     this.e = paramEntity;
/*  27 */     this.f = paramBoolean;
/*     */   }
/*     */   
/*     */   public static CommandListenerWrapper a(ICommandListener paramICommandListener) {
/*  31 */     if (paramICommandListener instanceof CommandListenerWrapper) {
/*  32 */       return (CommandListenerWrapper)paramICommandListener;
/*     */     }
/*  34 */     return new CommandListenerWrapper(paramICommandListener, null, null, null, null, null);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(Entity paramEntity, Vec3D paramVec3D) {
/*  38 */     if (this.e == paramEntity && Objects.equals(this.b, paramVec3D)) {
/*  39 */       return this;
/*     */     }
/*  41 */     return new CommandListenerWrapper(this.base, paramVec3D, new BlockPosition(paramVec3D), this.d, paramEntity, this.f);
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper a(int paramInt) {
/*  45 */     if (this.d != null && this.d.intValue() <= paramInt) {
/*  46 */       return this;
/*     */     }
/*  48 */     return new CommandListenerWrapper(this.base, this.b, this.c, Integer.valueOf(paramInt), this.e, this.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandListenerWrapper a(boolean paramBoolean) {
/*  53 */     if (this.f != null && (!this.f.booleanValue() || paramBoolean)) {
/*  54 */       return this;
/*     */     }
/*  56 */     return new CommandListenerWrapper(this.base, this.b, this.c, this.d, this.e, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper i() {
/*  60 */     if (this.b != null) {
/*  61 */       return this;
/*     */     }
/*  63 */     return new CommandListenerWrapper(this.base, d(), getChunkCoordinates(), this.d, this.e, this.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  68 */     if (this.e != null) {
/*  69 */       return this.e.getName();
/*     */     }
/*  71 */     return this.base.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/*  76 */     if (this.e != null) {
/*  77 */       return this.e.getScoreboardDisplayName();
/*     */     }
/*  79 */     return this.base.getScoreboardDisplayName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(IChatBaseComponent paramIChatBaseComponent) {
/*  84 */     if (this.f != null && !this.f.booleanValue()) {
/*     */       return;
/*     */     }
/*  87 */     this.base.sendMessage(paramIChatBaseComponent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int paramInt, String paramString) {
/*  92 */     if (this.d != null && this.d.intValue() < paramInt) {
/*  93 */       return false;
/*     */     }
/*  95 */     return this.base.a(paramInt, paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition getChunkCoordinates() {
/* 100 */     if (this.c != null) {
/* 101 */       return this.c;
/*     */     }
/* 103 */     if (this.e != null) {
/* 104 */       return this.e.getChunkCoordinates();
/*     */     }
/* 106 */     return this.base.getChunkCoordinates();
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3D d() {
/* 111 */     if (this.b != null) {
/* 112 */       return this.b;
/*     */     }
/* 114 */     if (this.e != null) {
/* 115 */       return this.e.d();
/*     */     }
/* 117 */     return this.base.d();
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/* 122 */     if (this.e != null) {
/* 123 */       return this.e.getWorld();
/*     */     }
/* 125 */     return this.base.getWorld();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity f() {
/* 131 */     if (this.e != null) {
/* 132 */       return this.e.f();
/*     */     }
/* 134 */     return this.base.f();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getSendCommandFeedback() {
/* 139 */     if (this.f != null) {
/* 140 */       return this.f.booleanValue();
/*     */     }
/* 142 */     return this.base.getSendCommandFeedback();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(CommandObjectiveExecutor.EnumCommandResult paramEnumCommandResult, int paramInt) {
/* 147 */     if (this.e != null) {
/* 148 */       this.e.a(paramEnumCommandResult, paramInt);
/*     */       return;
/*     */     } 
/* 151 */     this.base.a(paramEnumCommandResult, paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MinecraftServer C_() {
/* 157 */     return this.base.C_();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandListenerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */