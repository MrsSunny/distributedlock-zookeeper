package org.sms.lock;

import org.sms.entity.Doctor;

/**
 * @author Sunny
 */
public class DistributedLockTest {
  
  public static void main(String[] args) {
    final Doctor doctor = new Doctor("汪洋", "doctor11588");
    for (int i = 0; i < 10; i++) {
      new Thread() {
        public void run() {
          try {
            DistributedLock distributedLock = new DistributedLock();
            distributedLock.connectZk("127.0.0.1:2181", doctor.getId());
            distributedLock.lock();
            System.out.println(doctor.getName() + "正在给" + Thread.currentThread().getName() + "看病，看完病就释放锁");
            Thread.sleep(1000 * 3);
            System.out.println(Thread.currentThread().getName() + "的病已经看完了，下个病人准备");
            System.out.println(Thread.currentThread().getName() + "释放锁");
            distributedLock.relaseLock();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }.start();
    }
  }
}