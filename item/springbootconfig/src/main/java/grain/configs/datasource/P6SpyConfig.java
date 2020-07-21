package grain.configs.datasource;

import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.spy.P6LoadableOptions;
import com.p6spy.engine.spy.P6SpyFactory;
import com.p6spy.engine.spy.option.P6OptionsRepository;

public class P6SpyConfig extends P6SpyFactory {
    @Override
    public P6LoadableOptions getOptions(P6OptionsRepository optionsRepository) {
        return super.getOptions(optionsRepository);
    }

    @Override
    public JdbcEventListener getJdbcEventListener() {
        return super.getJdbcEventListener();
    }
}
