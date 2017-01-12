package item66;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

// 適切に同期された協調的スレッド終了
class StopThread {
  private static boolean stopRequested;

  private static void execute() throws InterruptedException {
    Thread backgroudThread = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while (!stopRequested()) {
          i++;
        }
      }
    });
    backgroudThread.start();

    TimeUnit.SECONDS.sleep(1);
    requestStop();
  }

  // 読み込みと書き込み両方が同期されていなければ意味がない
  private static synchronized void requestStop() {
    stopRequested = true;
  }

  private static synchronized boolean stopRequested() {
    return stopRequested;
  }

  // java.util.concurrentパッケージのクラスを活用する
  private static final AtomicLong nextSerialNum = new AtomicLong();

  public static long generateSerialNumber() {
    return nextSerialNum.getAndIncrement();
  }
}
