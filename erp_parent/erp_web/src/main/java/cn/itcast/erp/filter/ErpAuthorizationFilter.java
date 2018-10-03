package cn.itcast.erp.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 自定义授权过滤器
 *
 */
public class ErpAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		//获取主题
		Subject subject = getSubject(request, response);
		///orders.html=perms["采购订单的查询","采购订单的审核","采购订单的确认","采购订单的入库"]
		//mappedValue="采购订单的查询","采购订单的审核","采购订单的确认","采购订单的入库"
        String[] perms = (String[]) mappedValue;

        boolean isPermitted = true;
        if(null == perms || perms.length == 0){
        	return isPermitted;
        }
        if (perms != null && perms.length > 0) {
            /* 原有的代码
             * if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }*/
        	for(String perm : perms){
        		//只要有一个权限，就返回true
        		if(subject.isPermitted(perm)){
        			return true;
        		}
        	}
        }
        return false;
	}

}
