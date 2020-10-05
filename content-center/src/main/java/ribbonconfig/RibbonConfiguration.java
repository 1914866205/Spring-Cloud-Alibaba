package ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName RibbonConfiguration.java
 * @Description TODO
 * @createTime 2020年09月25日 11:13:00
 */
//@Configuration
public class RibbonConfiguration {
    /**
     * 随机轮训
     *
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
