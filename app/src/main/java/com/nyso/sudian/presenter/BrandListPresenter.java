package com.nyso.sudian.presenter;

import com.example.test.andlang.andlangutil.BaseLangActivity;
import com.example.test.andlang.andlangutil.BaseLangFragment;
import com.example.test.andlang.andlangutil.BaseLangPresenter;
import com.example.test.andlang.andlangutil.BaseLangViewModel;
import com.google.gson.Gson;
import com.nyso.sudian.model.api.Brand;
import com.nyso.sudian.model.local.BrandListModel;

import java.util.List;

/**
 * Created by Bill56 on 2018-9-10.
 */

public class BrandListPresenter extends BaseLangPresenter<BrandListModel> {

    public static final String TAG_BRAND_LIST_SUCCESS = "TAG_BRAND_LIST_SUCCESS";

    public BrandListPresenter(BaseLangFragment fragment, BaseLangActivity activity, Class<? extends BaseLangViewModel> modelClass) {
        super(fragment, activity, modelClass);
    }

    @Override
    public void initModel() {
        // 模拟初始化几个数据
        String result = "{\"brandMap\":{\"A\":[{\"brandId\":971,\"brandName\":\"A.BY.BOM/艾柏梵\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":797,\"brandName\":\"A'KIN\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/16103844856878590.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":1},{\"brandId\":515,\"brandName\":\"A2\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/12584818366354215.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0}],\"B\":[{\"brandId\":27,\"brandName\":\"BEAUTY BAR/蔚丽吧\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/brandImg/3950041258693759.jpg\",\"brandStory\":null,\"seqNum\":null,\"ifAuthorised\":0},{\"brandId\":507,\"brandName\":\"BEAUTY BUBBLE\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/16098311740287491.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":871,\"brandName\":\"BEAUTY BUFFET\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/17066342147554421.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":839,\"brandName\":\"BEBEFOLIE/贝熙蕾\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/16028851947255765.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":1577,\"brandName\":\"BE BLANCHE\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/13368088757922932.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":973,\"brandName\":\"BEDDYBEAR/杯具熊\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/23469954391155261.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":1689,\"brandName\":\"BEE KIW/碧卡维\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/18120327175675577.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0}],\"O\":[{\"brandId\":1607,\"brandName\":\"OSHADHI/德国O家\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/14590318909767012.jpg\",\"brandStory\":null,\"seqNum\":987,\"ifAuthorised\":0},{\"brandId\":122,\"brandName\":\"OSK\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/brandImg/899441823514583.jpg\",\"brandStory\":null,\"seqNum\":null,\"ifAuthorised\":0},{\"brandId\":673,\"brandName\":\"OSTELIN/奥斯特林\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/12763649563147329.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":237,\"brandName\":\"OXI CLEAN\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/13702429622710223.jpg\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0},{\"brandId\":1708,\"brandName\":\"OYATSU/童星\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"http://image.mihui365.com/bbc/brandImg/18560006996219124.jpg\",\"brandStory\":null,\"seqNum\":666,\"ifAuthorised\":0},{\"brandId\":1174,\"brandName\":\"OZ FARM/澳美滋\",\"count\":null,\"isNew\":null,\"isShow\":null,\"brandLogo\":\"\",\"brandStory\":null,\"seqNum\":1,\"ifAuthorised\":0}]}}";
        Gson gson=new Gson();
        BrandListModel brandListModel = gson.fromJson(result,BrandListModel.class);
        model.setBrandMap(brandListModel.getBrandMap());
        model.notifyData(TAG_BRAND_LIST_SUCCESS);
    }
}
