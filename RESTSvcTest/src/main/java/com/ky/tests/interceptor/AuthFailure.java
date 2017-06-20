package com.ky.tests.interceptor;

/**
 * 权限异常
 * @author jaye
 * @since 2016年8月5日
 */
public class AuthFailure extends Exception
{

    private static final long serialVersionUID = 1L;

    public AuthFailure(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
