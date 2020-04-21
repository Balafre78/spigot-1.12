/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class InsensitiveStringMap<V>
/*    */   implements Map<String, V> {
/* 11 */   private final Map<String, V> a = Maps.newLinkedHashMap();
/*    */ 
/*    */   
/*    */   public int size() {
/* 15 */     return this.a.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 20 */     return this.a.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsKey(Object paramObject) {
/* 25 */     return this.a.containsKey(paramObject.toString().toLowerCase(Locale.ROOT));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsValue(Object paramObject) {
/* 30 */     return this.a.containsKey(paramObject);
/*    */   }
/*    */ 
/*    */   
/*    */   public V get(Object paramObject) {
/* 35 */     return this.a.get(paramObject.toString().toLowerCase(Locale.ROOT));
/*    */   }
/*    */ 
/*    */   
/*    */   public V a(String paramString, V paramV) {
/* 40 */     return this.a.put(paramString.toLowerCase(Locale.ROOT), paramV);
/*    */   }
/*    */ 
/*    */   
/*    */   public V remove(Object paramObject) {
/* 45 */     return this.a.remove(paramObject.toString().toLowerCase(Locale.ROOT));
/*    */   }
/*    */ 
/*    */   
/*    */   public void putAll(Map<? extends String, ? extends V> paramMap) {
/* 50 */     for (Map.Entry<? extends String, ? extends V> entry : paramMap.entrySet()) {
/* 51 */       a((String)entry.getKey(), (V)entry.getValue());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 57 */     this.a.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<String> keySet() {
/* 62 */     return this.a.keySet();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<V> values() {
/* 67 */     return this.a.values();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Map.Entry<String, V>> entrySet() {
/* 72 */     return this.a.entrySet();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InsensitiveStringMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */