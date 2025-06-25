import static org.junit.Assert.assertEquals;

import cn.jll.model.Count;
import org.junit.Test;

/**
 * 测试方法需要添加注解，必须是public, 无参数，没有返回值
 */

public class MyFirstTest {

    @Test
    public void myFirstTest() {

        assertEquals(2, 1+1);
    };


    @Test
    public void countTest() {
        Count count = new Count();

        assertEquals(4, count.add(2,2));
    }
}
