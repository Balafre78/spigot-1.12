/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import io.netty.handler.codec.DecoderException;
/*     */ import io.netty.handler.codec.EncoderException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.locks.ReadWriteLock;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.lang3.ObjectUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class DataWatcher
/*     */ {
/*  21 */   private static final Logger a = LogManager.getLogger();
/*  22 */   private static final Map<Class<? extends Entity>, Integer> b = Maps.newHashMap();
/*     */   private final Entity c;
/*  24 */   private final Map<Integer, Item<?>> d = Maps.newHashMap();
/*  25 */   private final ReadWriteLock e = new ReentrantReadWriteLock();
/*     */   private boolean f = true;
/*     */   private boolean g;
/*     */   
/*     */   public DataWatcher(Entity entity) {
/*  30 */     this.c = entity;
/*     */   }
/*     */   public static <T> DataWatcherObject<T> a(Class<? extends Entity> oclass, DataWatcherSerializer<T> datawatcherserializer) {
/*     */     int i;
/*  34 */     if (a.isDebugEnabled()) {
/*     */       try {
/*  36 */         Class<?> oclass1 = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
/*     */         
/*  38 */         if (!oclass1.equals(oclass)) {
/*  39 */           a.debug("defineId called for: {} from {}", oclass, oclass1, new RuntimeException());
/*     */         }
/*  41 */       } catch (ClassNotFoundException classNotFoundException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     if (b.containsKey(oclass)) {
/*  49 */       i = ((Integer)b.get(oclass)).intValue() + 1;
/*     */     } else {
/*  51 */       int j = 0;
/*  52 */       Class<? extends Entity> oclass2 = oclass;
/*     */       
/*  54 */       while (oclass2 != Entity.class) {
/*  55 */         oclass2 = (Class)oclass2.getSuperclass();
/*  56 */         if (b.containsKey(oclass2)) {
/*  57 */           j = ((Integer)b.get(oclass2)).intValue() + 1;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  62 */       i = j;
/*     */     } 
/*     */     
/*  65 */     if (i > 254) {
/*  66 */       throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 'þ' + ")");
/*     */     }
/*  68 */     b.put(oclass, Integer.valueOf(i));
/*  69 */     return datawatcherserializer.a(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void register(DataWatcherObject<T> datawatcherobject, Object t0) {
/*  74 */     int i = datawatcherobject.a();
/*     */     
/*  76 */     if (i > 254)
/*  77 */       throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 'þ' + ")"); 
/*  78 */     if (this.d.containsKey(Integer.valueOf(i)))
/*  79 */       throw new IllegalArgumentException("Duplicate id value for " + i + "!"); 
/*  80 */     if (DataWatcherRegistry.b(datawatcherobject.b()) < 0) {
/*  81 */       throw new IllegalArgumentException("Unregistered serializer " + datawatcherobject.b() + " for " + i + "!");
/*     */     }
/*  83 */     registerObject(datawatcherobject, t0);
/*     */   }
/*     */ 
/*     */   
/*     */   private <T> void registerObject(DataWatcherObject<T> datawatcherobject, Object t0) {
/*  88 */     Item<T> datawatcher_item = new Item<>(datawatcherobject, (T)t0);
/*     */     
/*  90 */     this.e.writeLock().lock();
/*  91 */     this.d.put(Integer.valueOf(datawatcherobject.a()), datawatcher_item);
/*  92 */     this.f = false;
/*  93 */     this.e.writeLock().unlock();
/*     */   }
/*     */   private <T> Item<T> c(DataWatcherObject<T> datawatcherobject) {
/*     */     Item<T> datawatcher_item;
/*  97 */     this.e.readLock().lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 102 */       datawatcher_item = (Item)this.d.get(Integer.valueOf(datawatcherobject.a()));
/* 103 */     } catch (Throwable throwable) {
/* 104 */       CrashReport crashreport = CrashReport.a(throwable, "Getting synched entity data");
/* 105 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Synched entity data");
/*     */       
/* 107 */       crashreportsystemdetails.a("Data ID", datawatcherobject);
/* 108 */       throw new ReportedException(crashreport);
/*     */     } 
/*     */     
/* 111 */     this.e.readLock().unlock();
/* 112 */     return datawatcher_item;
/*     */   }
/*     */   
/*     */   public <T> T get(DataWatcherObject<T> datawatcherobject) {
/* 116 */     return c(datawatcherobject).b();
/*     */   }
/*     */   
/*     */   public <T> void set(DataWatcherObject<T> datawatcherobject, T t0) {
/* 120 */     Item<T> datawatcher_item = c(datawatcherobject);
/*     */     
/* 122 */     if (ObjectUtils.notEqual(t0, datawatcher_item.b())) {
/* 123 */       datawatcher_item.a(t0);
/* 124 */       this.c.a(datawatcherobject);
/* 125 */       datawatcher_item.a(true);
/* 126 */       this.g = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void markDirty(DataWatcherObject<T> datawatcherobject) {
/* 132 */     (c(datawatcherobject)).c = true;
/* 133 */     this.g = true;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 137 */     return this.g;
/*     */   }
/*     */   
/*     */   public static void a(List<Item<?>> list, PacketDataSerializer packetdataserializer) throws IOException {
/* 141 */     if (list != null) {
/* 142 */       int i = 0;
/*     */       
/* 144 */       for (int j = list.size(); i < j; i++) {
/* 145 */         Item<?> datawatcher_item = list.get(i);
/*     */         
/* 147 */         a(packetdataserializer, datawatcher_item);
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     packetdataserializer.writeByte(255);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<Item<?>> b() {
/* 156 */     ArrayList<Item<?>> arraylist = null;
/*     */     
/* 158 */     if (this.g) {
/* 159 */       this.e.readLock().lock();
/* 160 */       Iterator<Item> iterator = this.d.values().iterator();
/*     */       
/* 162 */       while (iterator.hasNext()) {
/* 163 */         Item datawatcher_item = iterator.next();
/*     */         
/* 165 */         if (datawatcher_item.c()) {
/* 166 */           datawatcher_item.a(false);
/* 167 */           if (arraylist == null) {
/* 168 */             arraylist = Lists.newArrayList();
/*     */           }
/*     */           
/* 171 */           arraylist.add(datawatcher_item.d());
/*     */         } 
/*     */       } 
/*     */       
/* 175 */       this.e.readLock().unlock();
/*     */     } 
/*     */     
/* 178 */     this.g = false;
/* 179 */     return arraylist;
/*     */   }
/*     */   
/*     */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 183 */     this.e.readLock().lock();
/* 184 */     Iterator<Item> iterator = this.d.values().iterator();
/*     */     
/* 186 */     while (iterator.hasNext()) {
/* 187 */       Item<?> datawatcher_item = iterator.next();
/*     */       
/* 189 */       a(packetdataserializer, datawatcher_item);
/*     */     } 
/*     */     
/* 192 */     this.e.readLock().unlock();
/* 193 */     packetdataserializer.writeByte(255);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<Item<?>> c() {
/* 198 */     ArrayList<Item<?>> arraylist = null;
/*     */     
/* 200 */     this.e.readLock().lock();
/*     */ 
/*     */ 
/*     */     
/* 204 */     for (Iterator<Item> iterator = this.d.values().iterator(); iterator.hasNext(); arraylist.add(datawatcher_item.d())) {
/* 205 */       Item datawatcher_item = iterator.next();
/* 206 */       if (arraylist == null) {
/* 207 */         arraylist = Lists.newArrayList();
/*     */       }
/*     */     } 
/*     */     
/* 211 */     this.e.readLock().unlock();
/* 212 */     return arraylist;
/*     */   }
/*     */   
/*     */   private static <T> void a(PacketDataSerializer packetdataserializer, Item<T> datawatcher_item) throws IOException {
/* 216 */     DataWatcherObject<T> datawatcherobject = datawatcher_item.a();
/* 217 */     int i = DataWatcherRegistry.b(datawatcherobject.b());
/*     */     
/* 219 */     if (i < 0) {
/* 220 */       throw new EncoderException("Unknown serializer type " + datawatcherobject.b());
/*     */     }
/* 222 */     packetdataserializer.writeByte(datawatcherobject.a());
/* 223 */     packetdataserializer.d(i);
/* 224 */     datawatcherobject.b().a(packetdataserializer, datawatcher_item.b());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static List<Item<?>> b(PacketDataSerializer packetdataserializer) throws IOException {
/* 230 */     ArrayList<Item<?>> arraylist = null;
/*     */     
/*     */     short short0;
/*     */     
/* 234 */     while ((short0 = packetdataserializer.readUnsignedByte()) != 255) {
/* 235 */       if (arraylist == null) {
/* 236 */         arraylist = Lists.newArrayList();
/*     */       }
/*     */       
/* 239 */       int i = packetdataserializer.g();
/* 240 */       DataWatcherSerializer<?> datawatcherserializer = DataWatcherRegistry.a(i);
/*     */       
/* 242 */       if (datawatcherserializer == null) {
/* 243 */         throw new DecoderException("Unknown serializer type " + i);
/*     */       }
/*     */       
/* 246 */       arraylist.add(new Item(datawatcherserializer.a(short0), datawatcherserializer.a(packetdataserializer)));
/*     */     } 
/*     */     
/* 249 */     return arraylist;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 253 */     return this.f;
/*     */   }
/*     */   
/*     */   public void e() {
/* 257 */     this.g = false;
/* 258 */     this.e.readLock().lock();
/* 259 */     Iterator<Item> iterator = this.d.values().iterator();
/*     */     
/* 261 */     while (iterator.hasNext()) {
/* 262 */       Item datawatcher_item = iterator.next();
/*     */       
/* 264 */       datawatcher_item.a(false);
/*     */     } 
/*     */     
/* 267 */     this.e.readLock().unlock();
/*     */   }
/*     */   
/*     */   public static class Item<T>
/*     */   {
/*     */     private final DataWatcherObject<T> a;
/*     */     private T b;
/*     */     private boolean c;
/*     */     
/*     */     public Item(DataWatcherObject<T> datawatcherobject, T t0) {
/* 277 */       this.a = datawatcherobject;
/* 278 */       this.b = t0;
/* 279 */       this.c = true;
/*     */     }
/*     */     
/*     */     public DataWatcherObject<T> a() {
/* 283 */       return this.a;
/*     */     }
/*     */     
/*     */     public void a(T t0) {
/* 287 */       this.b = t0;
/*     */     }
/*     */     
/*     */     public T b() {
/* 291 */       return this.b;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 295 */       return this.c;
/*     */     }
/*     */     
/*     */     public void a(boolean flag) {
/* 299 */       this.c = flag;
/*     */     }
/*     */     
/*     */     public Item<T> d() {
/* 303 */       return new Item(this.a, this.a.b().a(this.b));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataWatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */