package com.britesnow.snow.web.renderer.freemarker;

import static com.britesnow.snow.web.renderer.freemarker.FreemarkerUtil.getDataModel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.britesnow.snow.web.RequestContext;
import com.britesnow.snow.web.handler.FreemarkerMethodHandlerRef;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FreemarkerMethodProxy  implements TemplateMethodModelEx{
    static private Logger logger = LoggerFactory.getLogger(FreemarkerMethodProxy.class);
    
    static final String FREEMARKER_METHOD_ARGUMENTS = "freemarkerMethodArguements";
    
    private String name;
    private FreemarkerMethodHandlerRef ref;
    
    public FreemarkerMethodProxy(String name, FreemarkerMethodHandlerRef ref){
        this.name = name;
        this.ref = ref;
    }
    
    public String getName(){
        return name;
    }
    
    
    @Override
    public Object exec(List args) throws TemplateModelException {
        
        RequestContext rc = getDataModel("_r.rc", RequestContext.class);
        
        rc.setAttribute(FREEMARKER_METHOD_ARGUMENTS,args);
        Object result = null;
        try{
            result = ref.invoke(rc);
        }catch(Exception e){
            logger.error(e.getMessage());
        }finally{
            rc.removeAttribute(FREEMARKER_METHOD_ARGUMENTS);
        }
        
        // TODO Auto-generated method stub
        return result;
    }
    
    

}
