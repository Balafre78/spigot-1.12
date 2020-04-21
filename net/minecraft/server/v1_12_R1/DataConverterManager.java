/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterManager
/*     */   implements DataConverter
/*     */ {
/*  16 */   private static final Logger a = LogManager.getLogger();
/*     */   
/*  18 */   private final Map<DataConverterType, List<DataInspector>> b = Maps.newHashMap();
/*  19 */   private final Map<DataConverterType, List<IDataConverter>> c = Maps.newHashMap();
/*     */   
/*     */   private final int d;
/*     */   
/*     */   public DataConverterManager(int paramInt) {
/*  24 */     this.d = paramInt;
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(DataConverterType paramDataConverterType, NBTTagCompound paramNBTTagCompound) {
/*  28 */     byte b = paramNBTTagCompound.hasKeyOfType("DataVersion", 99) ? paramNBTTagCompound.getInt("DataVersion") : -1;
/*  29 */     if (b >= 'ѳ') {
/*  30 */       return paramNBTTagCompound;
/*     */     }
/*     */     
/*  33 */     return a(paramDataConverterType, paramNBTTagCompound, b);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound a(DataConverterType paramDataConverterType, NBTTagCompound paramNBTTagCompound, int paramInt) {
/*  38 */     if (paramInt < this.d) {
/*  39 */       paramNBTTagCompound = b(paramDataConverterType, paramNBTTagCompound, paramInt);
/*  40 */       paramNBTTagCompound = c(paramDataConverterType, paramNBTTagCompound, paramInt);
/*     */     } 
/*  42 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   private NBTTagCompound b(DataConverterType paramDataConverterType, NBTTagCompound paramNBTTagCompound, int paramInt) {
/*  46 */     List<IDataConverter> list = this.c.get(paramDataConverterType);
/*  47 */     if (list != null) {
/*  48 */       for (byte b = 0; b < list.size(); b++) {
/*  49 */         IDataConverter iDataConverter = list.get(b);
/*  50 */         if (iDataConverter.a() > paramInt) {
/*  51 */           paramNBTTagCompound = iDataConverter.a(paramNBTTagCompound);
/*     */         }
/*     */       } 
/*     */     }
/*  55 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   private NBTTagCompound c(DataConverterType paramDataConverterType, NBTTagCompound paramNBTTagCompound, int paramInt) {
/*  59 */     List<DataInspector> list = this.b.get(paramDataConverterType);
/*  60 */     if (list != null) {
/*  61 */       for (byte b = 0; b < list.size(); b++) {
/*  62 */         paramNBTTagCompound = ((DataInspector)list.get(b)).a(this, paramNBTTagCompound, paramInt);
/*     */       }
/*     */     }
/*  65 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   public void a(DataConverterTypes paramDataConverterTypes, DataInspector paramDataInspector) {
/*  69 */     a(paramDataConverterTypes, paramDataInspector);
/*     */   }
/*     */   
/*     */   public void a(DataConverterType paramDataConverterType, DataInspector paramDataInspector) {
/*  73 */     a(this.b, paramDataConverterType).add(paramDataInspector);
/*     */   }
/*     */   
/*     */   public void a(DataConverterType paramDataConverterType, IDataConverter paramIDataConverter) {
/*  77 */     List<IDataConverter> list = a(this.c, paramDataConverterType);
/*  78 */     int i = paramIDataConverter.a();
/*     */     
/*  80 */     if (i > this.d) {
/*  81 */       a.warn("Ignored fix registered for version: {} as the DataVersion of the game is: {}", Integer.valueOf(i), Integer.valueOf(this.d));
/*     */       
/*     */       return;
/*     */     } 
/*  85 */     if (list.isEmpty() || ((IDataConverter)SystemUtils.a((List)list)).a() <= i) {
/*  86 */       list.add(paramIDataConverter);
/*     */     } else {
/*  88 */       for (byte b = 0; b < list.size(); b++) {
/*  89 */         if (((IDataConverter)list.get(b)).a() > i) {
/*  90 */           list.add(b, paramIDataConverter);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private <V> List<V> a(Map<DataConverterType, List<V>> paramMap, DataConverterType paramDataConverterType) {
/*  98 */     List<V> list = paramMap.get(paramDataConverterType);
/*  99 */     if (list == null) {
/* 100 */       list = Lists.newArrayList();
/* 101 */       paramMap.put(paramDataConverterType, list);
/*     */     } 
/* 103 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */