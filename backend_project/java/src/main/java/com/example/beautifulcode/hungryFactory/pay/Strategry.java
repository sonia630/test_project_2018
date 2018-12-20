package com.example.beautifulcode.hungryFactory.pay;

import java.math.BigDecimal;

public interface Strategry {
    /**
     * 计算支付金额,通过渠道和商品ID调用各用的银行
     *
     * 1.价格不能或商品名称需要通过数据查询完了再计算而不是通过界面传过去
     * 2.涉及到价格的用 BigDecimal (Double 会有精度的丢失)
     *
     * @param channlId
     * @param goodsId
     * @return
     */
   BigDecimal calRecharge(Integer channlId, Integer goodsId);

}
