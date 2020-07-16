import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/**
 * @author shimi
 */
public class TestLock {
    public static void main(String[] args) throws Exception {
        File base = new File("D:\\TongESB\\TongESB527\\ESBServer");
        RandomAccessFile lockFile = new RandomAccessFile(new File(base, "lock"), "rw");
        FileLock lock = lockFile.getChannel().tryLock();
        while (true){
            if (lock != null){
                Thread.sleep(1000);
                System.out.println("locking");
            }else {
                System.out.println("lost lock");
            }
        }
    }
}
