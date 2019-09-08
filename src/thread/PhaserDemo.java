package thread;

import java.util.concurrent.Phaser;

/**
 * Phaser:Phaser是jdk1.7引入的。Phaser的功能与CountDownLatch和CyclicBarrier有部分重叠，同时也提供了更丰富的语义和更灵活的用法。
 *
 * 适用场景：Phaser顾名思义，与阶段相关。
 *  一个任务可以分为多个阶段，现希望多个线程去处理该批任务，对于每个阶段，多个线程可以并发执行，
 *  但是希望保证只有前面一个阶段的任务完成后才可以开始后面阶段的任务。
 *  这个场景可以适用多个循环屏障（CyclicBarrier）来完成，但是适用CyclicBarrier的缺点在于，需要明确知道有多少阶段，同时并行数也需要提前定好，而无法动态修改。
 *  而Phaser可以同时解决这两个问题
 *
 * @author wangfj
 * @date 2019-09-08 15:18
 */
public class PhaserDemo {

    public static void main(String[] args) {
        int parties = 3;
        int phases = 4;

        Phaser phaser = new Phaser(parties){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("====== Phase : " + phase + " ======");
                return super.onAdvance(phase, registeredParties);
            }
        };

        for (int i = 0; i < parties; i++) {
            int threadId = i;
            new Thread(() -> {
                for(int phase = 0; phase < phases; phase++) {
                    System.out.println(String.format("Thread %s, phase %s", threadId, phase));
                    phaser.arriveAndAwaitAdvance();
                }
            }).start();
        }

        //从上面的结果可以看到，
        // 多个线程必须等到其它线程的同一阶段的任务全部完成才能进行到下一个阶段，
        // 并且每当完成某一阶段任务时，Phaser都会执行其onAdvance方法。
    }
}
