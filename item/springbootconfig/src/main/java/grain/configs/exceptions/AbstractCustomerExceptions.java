package grain.configs.exceptions;


import grain.configs.ResultInfo;

public abstract class AbstractCustomerExceptions extends RuntimeException {
    protected ResultInfo resultInfo ;

    public AbstractCustomerExceptions(ResultInfo resultInfo) {
        super(resultInfo.getDesc());
        this.resultInfo = resultInfo;
    }

    /**
     * 获取标准异常执行返回结果
     * @return 字符串
     * @see ResultInfo .json()
     */
    public String getReturn() {
        return resultInfo.json();
    }
    /**
     * 获取标准异常执行返回结果包装类
     * @return 包装类
     * @see ResultInfo
     */
    public ResultInfo getResultInfo() {
        return resultInfo;
    }
}
