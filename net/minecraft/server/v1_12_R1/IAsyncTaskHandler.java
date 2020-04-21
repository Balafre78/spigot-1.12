package net.minecraft.server.v1_12_R1;

import com.google.common.util.concurrent.ListenableFuture;

public interface IAsyncTaskHandler {
  ListenableFuture<Object> postToMainThread(Runnable paramRunnable);
  
  boolean isMainThread();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IAsyncTaskHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */