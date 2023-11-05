package test;

import org.junit.jupiter.api.Test;
import com.example.service.INumOperate;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Javen
 * @date 2022/5/12
 */
public class INumOperateTest {

    @Test
    public void test() {
        // SPI机制，寻找所有的实现类，顺序执行
        ServiceLoader<INumOperate> loader = ServiceLoader.load(INumOperate.class);
        for (INumOperate numOperate : loader) {
            System.out.println(numOperate.exec(10, 10));
        }
    }
}
