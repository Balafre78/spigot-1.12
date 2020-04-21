/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import java.util.UUID;
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
/*     */ public class DataWatcherRegistry
/*     */ {
/*  19 */   private static final RegistryID<DataWatcherSerializer<?>> o = new RegistryID<>(16);
/*     */   
/*  21 */   public static final DataWatcherSerializer<Byte> a = new DataWatcherSerializer<Byte>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Byte param1Byte) {
/*  24 */         param1PacketDataSerializer.writeByte(param1Byte.byteValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Byte b(PacketDataSerializer param1PacketDataSerializer) {
/*  29 */         return Byte.valueOf(param1PacketDataSerializer.readByte());
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Byte> a(int param1Int) {
/*  34 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Byte a(Byte param1Byte) {
/*  39 */         return param1Byte;
/*     */       }
/*     */     };
/*     */   
/*  43 */   public static final DataWatcherSerializer<Integer> b = new DataWatcherSerializer<Integer>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Integer param1Integer) {
/*  46 */         param1PacketDataSerializer.d(param1Integer.intValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Integer b(PacketDataSerializer param1PacketDataSerializer) {
/*  51 */         return Integer.valueOf(param1PacketDataSerializer.g());
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Integer> a(int param1Int) {
/*  56 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Integer a(Integer param1Integer) {
/*  61 */         return param1Integer;
/*     */       }
/*     */     };
/*     */   
/*  65 */   public static final DataWatcherSerializer<Float> c = new DataWatcherSerializer<Float>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Float param1Float) {
/*  68 */         param1PacketDataSerializer.writeFloat(param1Float.floatValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Float b(PacketDataSerializer param1PacketDataSerializer) {
/*  73 */         return Float.valueOf(param1PacketDataSerializer.readFloat());
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Float> a(int param1Int) {
/*  78 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Float a(Float param1Float) {
/*  83 */         return param1Float;
/*     */       }
/*     */     };
/*     */   
/*  87 */   public static final DataWatcherSerializer<String> d = new DataWatcherSerializer<String>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, String param1String) {
/*  90 */         param1PacketDataSerializer.a(param1String);
/*     */       }
/*     */ 
/*     */       
/*     */       public String b(PacketDataSerializer param1PacketDataSerializer) {
/*  95 */         return param1PacketDataSerializer.e(32767);
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<String> a(int param1Int) {
/* 100 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public String a(String param1String) {
/* 105 */         return param1String;
/*     */       }
/*     */     };
/*     */   
/* 109 */   public static final DataWatcherSerializer<IChatBaseComponent> e = new DataWatcherSerializer<IChatBaseComponent>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, IChatBaseComponent param1IChatBaseComponent) {
/* 112 */         param1PacketDataSerializer.a(param1IChatBaseComponent);
/*     */       }
/*     */ 
/*     */       
/*     */       public IChatBaseComponent b(PacketDataSerializer param1PacketDataSerializer) {
/* 117 */         return param1PacketDataSerializer.f();
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<IChatBaseComponent> a(int param1Int) {
/* 122 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public IChatBaseComponent a(IChatBaseComponent param1IChatBaseComponent) {
/* 127 */         return param1IChatBaseComponent.f();
/*     */       }
/*     */     };
/*     */   
/* 131 */   public static final DataWatcherSerializer<ItemStack> f = new DataWatcherSerializer<ItemStack>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, ItemStack param1ItemStack) {
/* 134 */         param1PacketDataSerializer.a(param1ItemStack);
/*     */       }
/*     */ 
/*     */       
/*     */       public ItemStack b(PacketDataSerializer param1PacketDataSerializer) {
/* 139 */         return param1PacketDataSerializer.k();
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<ItemStack> a(int param1Int) {
/* 144 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public ItemStack a(ItemStack param1ItemStack) {
/* 149 */         return param1ItemStack.cloneItemStack();
/*     */       }
/*     */     };
/*     */   
/* 153 */   public static final DataWatcherSerializer<Optional<IBlockData>> g = new DataWatcherSerializer<Optional<IBlockData>>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Optional<IBlockData> param1Optional) {
/* 156 */         if (param1Optional.isPresent()) {
/* 157 */           param1PacketDataSerializer.d(Block.getCombinedId((IBlockData)param1Optional.get()));
/*     */         } else {
/* 159 */           param1PacketDataSerializer.d(0);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<IBlockData> b(PacketDataSerializer param1PacketDataSerializer) {
/* 165 */         int i = param1PacketDataSerializer.g();
/* 166 */         if (i == 0) {
/* 167 */           return Optional.absent();
/*     */         }
/* 169 */         return Optional.of(Block.getByCombinedId(i));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Optional<IBlockData>> a(int param1Int) {
/* 175 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<IBlockData> a(Optional<IBlockData> param1Optional) {
/* 180 */         return param1Optional;
/*     */       }
/*     */     };
/*     */   
/* 184 */   public static final DataWatcherSerializer<Boolean> h = new DataWatcherSerializer<Boolean>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Boolean param1Boolean) {
/* 187 */         param1PacketDataSerializer.writeBoolean(param1Boolean.booleanValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Boolean b(PacketDataSerializer param1PacketDataSerializer) {
/* 192 */         return Boolean.valueOf(param1PacketDataSerializer.readBoolean());
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Boolean> a(int param1Int) {
/* 197 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Boolean a(Boolean param1Boolean) {
/* 202 */         return param1Boolean;
/*     */       }
/*     */     };
/*     */   
/* 206 */   public static final DataWatcherSerializer<Vector3f> i = new DataWatcherSerializer<Vector3f>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Vector3f param1Vector3f) {
/* 209 */         param1PacketDataSerializer.writeFloat(param1Vector3f.getX());
/* 210 */         param1PacketDataSerializer.writeFloat(param1Vector3f.getY());
/* 211 */         param1PacketDataSerializer.writeFloat(param1Vector3f.getZ());
/*     */       }
/*     */ 
/*     */       
/*     */       public Vector3f b(PacketDataSerializer param1PacketDataSerializer) {
/* 216 */         return new Vector3f(param1PacketDataSerializer.readFloat(), param1PacketDataSerializer.readFloat(), param1PacketDataSerializer.readFloat());
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Vector3f> a(int param1Int) {
/* 221 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Vector3f a(Vector3f param1Vector3f) {
/* 226 */         return param1Vector3f;
/*     */       }
/*     */     };
/*     */   
/* 230 */   public static final DataWatcherSerializer<BlockPosition> j = new DataWatcherSerializer<BlockPosition>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, BlockPosition param1BlockPosition) {
/* 233 */         param1PacketDataSerializer.a(param1BlockPosition);
/*     */       }
/*     */ 
/*     */       
/*     */       public BlockPosition b(PacketDataSerializer param1PacketDataSerializer) {
/* 238 */         return param1PacketDataSerializer.e();
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<BlockPosition> a(int param1Int) {
/* 243 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public BlockPosition a(BlockPosition param1BlockPosition) {
/* 248 */         return param1BlockPosition;
/*     */       }
/*     */     };
/*     */   
/* 252 */   public static final DataWatcherSerializer<Optional<BlockPosition>> k = new DataWatcherSerializer<Optional<BlockPosition>>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Optional<BlockPosition> param1Optional) {
/* 255 */         param1PacketDataSerializer.writeBoolean(param1Optional.isPresent());
/* 256 */         if (param1Optional.isPresent()) {
/* 257 */           param1PacketDataSerializer.a((BlockPosition)param1Optional.get());
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<BlockPosition> b(PacketDataSerializer param1PacketDataSerializer) {
/* 263 */         if (!param1PacketDataSerializer.readBoolean()) {
/* 264 */           return Optional.absent();
/*     */         }
/* 266 */         return Optional.of(param1PacketDataSerializer.e());
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Optional<BlockPosition>> a(int param1Int) {
/* 271 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<BlockPosition> a(Optional<BlockPosition> param1Optional) {
/* 276 */         return param1Optional;
/*     */       }
/*     */     };
/*     */   
/* 280 */   public static final DataWatcherSerializer<EnumDirection> l = new DataWatcherSerializer<EnumDirection>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, EnumDirection param1EnumDirection) {
/* 283 */         param1PacketDataSerializer.a(param1EnumDirection);
/*     */       }
/*     */ 
/*     */       
/*     */       public EnumDirection b(PacketDataSerializer param1PacketDataSerializer) {
/* 288 */         return param1PacketDataSerializer.<EnumDirection>a(EnumDirection.class);
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<EnumDirection> a(int param1Int) {
/* 293 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public EnumDirection a(EnumDirection param1EnumDirection) {
/* 298 */         return param1EnumDirection;
/*     */       }
/*     */     };
/*     */   
/* 302 */   public static final DataWatcherSerializer<Optional<UUID>> m = new DataWatcherSerializer<Optional<UUID>>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, Optional<UUID> param1Optional) {
/* 305 */         param1PacketDataSerializer.writeBoolean(param1Optional.isPresent());
/* 306 */         if (param1Optional.isPresent()) {
/* 307 */           param1PacketDataSerializer.a((UUID)param1Optional.get());
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<UUID> b(PacketDataSerializer param1PacketDataSerializer) {
/* 313 */         if (!param1PacketDataSerializer.readBoolean()) {
/* 314 */           return Optional.absent();
/*     */         }
/* 316 */         return Optional.of(param1PacketDataSerializer.i());
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<Optional<UUID>> a(int param1Int) {
/* 321 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public Optional<UUID> a(Optional<UUID> param1Optional) {
/* 326 */         return param1Optional;
/*     */       }
/*     */     };
/*     */   
/* 330 */   public static final DataWatcherSerializer<NBTTagCompound> n = new DataWatcherSerializer<NBTTagCompound>()
/*     */     {
/*     */       public void a(PacketDataSerializer param1PacketDataSerializer, NBTTagCompound param1NBTTagCompound) {
/* 333 */         param1PacketDataSerializer.a(param1NBTTagCompound);
/*     */       }
/*     */ 
/*     */       
/*     */       public NBTTagCompound b(PacketDataSerializer param1PacketDataSerializer) {
/* 338 */         return param1PacketDataSerializer.j();
/*     */       }
/*     */ 
/*     */       
/*     */       public DataWatcherObject<NBTTagCompound> a(int param1Int) {
/* 343 */         return new DataWatcherObject<>(param1Int, this);
/*     */       }
/*     */ 
/*     */       
/*     */       public NBTTagCompound a(NBTTagCompound param1NBTTagCompound) {
/* 348 */         return param1NBTTagCompound.g();
/*     */       }
/*     */     };
/*     */   
/*     */   static {
/* 353 */     a(a);
/* 354 */     a(b);
/* 355 */     a(c);
/* 356 */     a(d);
/* 357 */     a(e);
/* 358 */     a(f);
/* 359 */     a(h);
/* 360 */     a(i);
/* 361 */     a(j);
/* 362 */     a(k);
/* 363 */     a(l);
/* 364 */     a(m);
/* 365 */     a(g);
/* 366 */     a(n);
/*     */   }
/*     */   
/*     */   public static void a(DataWatcherSerializer<?> paramDataWatcherSerializer) {
/* 370 */     o.c(paramDataWatcherSerializer);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static DataWatcherSerializer<?> a(int paramInt) {
/* 375 */     return o.fromId(paramInt);
/*     */   }
/*     */   
/*     */   public static int b(DataWatcherSerializer<?> paramDataWatcherSerializer) {
/* 379 */     return o.getId(paramDataWatcherSerializer);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataWatcherRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */