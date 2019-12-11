package evfor.fun.skvader.ui.utils.reg_step_view;

import evfor.fun.skvader.R;
import evfor.fun.skvader.utils.callbacks.CallBack;
import butterknife.OnClick;

public class Step4 extends BaseStep{

    private CallBack create, search;

    @OnClick(R.id.step4_search_company)
    void onSearch(){
        if(search!=null)
            search.call();
    }

    @OnClick(R.id.step4_create)
    void onCreate(){
        if(create!=null)
            create.call();
    }

    public void setCallBacks(CallBack create, CallBack search){
        this.create = create; this.search = search;
    }
}
